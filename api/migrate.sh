#!/bin/sh
mvn clean compile flyway:migrate  -Dflyway.user=api_user -Dflyway.password=api_user -Dflyway.url=jdbc:postgresql://localhost:5432/api -Dflyway.driver=org.postgresql.Driver

