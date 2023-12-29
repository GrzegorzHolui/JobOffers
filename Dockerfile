FROM openjdk:17-jdk-alpine
COPY ./target/offer.jar ./offer.jar
ENTRYPOINT ["java","-jar","/offer.jar"]