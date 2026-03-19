FROM openjdk:27-ea-jdk
ADD target/ATS.jar ATS.jar
ENTRYPOINT ["java", "-jar", "/ATS.jar"]