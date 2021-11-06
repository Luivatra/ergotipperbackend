FROM gradle:6.9.1-jdk11 as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle bootJar --stacktrace

FROM adoptopenjdk:11-jre
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/ergotipperbackend-0.1.jar .
CMD ["java", "-jar", "ergotipperbackend-0.1.jar"]