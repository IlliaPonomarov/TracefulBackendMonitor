
# spring boot app
# build stage
FROM openjdk:17-alpine as build

WORKDIR /app

COPY . /app


RUN apk update && apk upgrade
RUN apk add maven
RUN mvn clean package -DskipTests

# production stage
FROM openjdk:17-alpine
COPY --from=build /app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
