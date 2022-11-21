package pl.dgadecki.sonarqubeintroduction.common;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Objects;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class BaseIntegrationTest {

    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:14.0");

    private static final RabbitMQContainer RABBIT_MQ_CONTAINER = new RabbitMQContainer("rabbitmq:3.8-management")
            // Exchanges
            .withExchange(RabbitMQConstants.NOTIFICATION_EXCHANGE_NAME, RabbitMQConstants.FANOUT_EXCHANGE_TYPE)
            // Queues
            .withQueue(RabbitMQConstants.EMAIL_NOTIFICATION_QUEUE_NAME)
            .withQueue(RabbitMQConstants.SMS_NOTIFICATION_QUEUE_NAME)
            // Bindings
            .withBinding(RabbitMQConstants.NOTIFICATION_EXCHANGE_NAME, RabbitMQConstants.EMAIL_NOTIFICATION_QUEUE_NAME)
            .withBinding(RabbitMQConstants.NOTIFICATION_EXCHANGE_NAME, RabbitMQConstants.SMS_NOTIFICATION_QUEUE_NAME);

    @DynamicPropertySource
    static void configureApplication(DynamicPropertyRegistry dynamicPropertyRegistry) {
        Startables.deepStart(POSTGRE_SQL_CONTAINER, RABBIT_MQ_CONTAINER).join();

        // PostgreSQL
        dynamicPropertyRegistry.add("spring.datasource.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", POSTGRE_SQL_CONTAINER::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", POSTGRE_SQL_CONTAINER::getPassword);

        // RabbitMQ
        dynamicPropertyRegistry.add("spring.rabbitmq.host", RABBIT_MQ_CONTAINER::getHost);
        dynamicPropertyRegistry.add("spring.rabbitmq.port", RABBIT_MQ_CONTAINER::getAmqpPort);
        dynamicPropertyRegistry.add("spring.rabbitmq.username", RABBIT_MQ_CONTAINER::getAdminUsername);
        dynamicPropertyRegistry.add("spring.rabbitmq.password", RABBIT_MQ_CONTAINER::getAdminPassword);
    }

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    @Autowired
    protected MockMvc mockMvc;

    /**
     * Returns the number of messages in the queue with the name given as parameter.
     *
     * @param queue the name of the queue for which number of messages will be returned
     * @return number of messages in the queue
     */
    protected int getMessageCount(String queue) {
        ChannelCallback<Integer> callback = c -> c.queueDeclare(queue, true, false, false, null).getMessageCount();
        return Objects.requireNonNull(rabbitTemplate.execute(callback));
    }
}
