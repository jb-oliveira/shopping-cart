#!/bin/sh
mvn clean compile flyway:migrate  -Dflyway.user=api_test_user -Dflyway.password=api_test_user -Dflyway.url=jdbc:postgresql://localhost:5432/api_test -Dflyway.driver=org.postgresql.Driver

