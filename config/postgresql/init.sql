-- Create user and database for SonarQube
CREATE USER sonarqube WITH ENCRYPTED PASSWORD 'sonarqube';
CREATE DATABASE sonarqube;
GRANT ALL PRIVILEGES ON DATABASE sonarqube TO sonarqube;
