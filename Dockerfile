FROM openjdk:11
LABEL maintainer="vinaygoswami"
WORKDIR /app
ADD target/devops-0.0.1-SNAPSHOT.jar devops-spring-boot.jar
ENTRYPOINT ["java", "-jar", "devops-spring-boot.jar"]