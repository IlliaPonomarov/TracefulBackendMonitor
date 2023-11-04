# Traceable Backend Monitor (TBM) - Project Documentation

![TBM Logo](/img/tbm-logo.png)

Traceable Backend Monitor (TBM) is a powerful backend monitoring tool designed to log REST/SOAP/Kafka communications. This documentation provides an overview of the project, its features, setup instructions, and usage guidelines.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)
- [OpenAPI 3 Documentation](#openapi-3-documentation)
- [Dockerfile](#dockerfile)
- [Docker](#docker)
- [Kubernetes](#kubernetes)
- [Traceable Backend Monitor (TBM) - yaml](#traceable-backend-monitor-tbm---yaml)
- [MongoDB yaml](#mongodb-yaml)
- [License](#license)

## Introduction

Traceable Backend Monitor (TBM) is a Spring Boot-based backend monitoring solution that logs communication events between various components in your application. It facilitates tracking interactions between client-backend, backend-backend, and backend-client communication. TBM uses MongoDB to store communication logs and Kafka for real-time log streaming.

## Features

- Logs communication events between client-backend, backend-backend, and backend-client.
- Stores logs in MongoDB for later analysis.
- Utilizes Kafka for real-time communication log streaming.
- Offers an OpenAPI 3 documentation for the exposed endpoints.
- Provides comprehensive unit and integration testing with Mockito, JUnit, and more.

## Technologies Used

- Spring Boot 3
- Kafka
- MongoDB
- OpenAPI 3
- Mockito
- JUnit
- Docker
- Kubernetes

## Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/yourusername/tbm-backend-monitor.git
   cd tbm-backend-monitor

2. Install Maven dependencies:

   ```sh
   mvn install
   ```
## Usage
### Kubernetes
1. Clone the repository:

   ```sh
   git clone  https://github.com/Paninies/k8s-inv.git
   ```
   2. Move to the cloned repository:

   3. Start minikube:

   ```sh minikube start```
   4. Generate pods and services:

   ```sh kubectl apply -f path/to/cloned/k8s/repo```
   5. Get url of backend-monitor service:

   ```sh minikube service tbm --url```
   6. Get mongodb url:

   ```sh minikube service mongodb --url```

2. Install Maven dependencies:

   ```sh
   mvn clean install
   mvn package
   ```
3. Run the application:

   ```sh java -jar target/tbm-backend-monitor-0.0.1-SNAPSHOT.jar```
4. Access the application at `http://localhost:8444/`

## Testing
```
sh mvn test
```

## OpenAPI 3 Documentation
The OpenAPI 3 documentation is available at `http://localhost:8444/swagger-ui.html`

## Dockerfile
```agsl
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
```

## Docker
1.Build the Docker image:

   ```sh
   docker build -t tbm .
   ```
  If you don't wnna use k8s you can run single docker container with mongodb and backend-monitor
  ```sh
2. docker run -d -p 27017:27017 --name mongodb mongo
3. docker run -d -p 8444:8444 --name tbm --link mongodb:mongo tbm

   ```

## Kubernetes

### Traceable Backend Monitor (TBM) - yaml:
```
apiVersion: apps/v1
kind: Deployment
metadata:
name: tbm
labels:
app: tbm
spec:
replicas: 2
selector:
matchLabels:
app: tbm # Match the same label as in template.labels
template:
metadata:
labels:
app: tbm
spec:
containers:
- name: tbm
image: grapetruedocker/tbm:1.0.0
env:
- name: SPRING_DATA_MONGODB_URI
value: mongodb://mongo:27017/tbm
ports:
- containerPort: 8444
imagePullPolicy: Always
---
apiVersion: v1
kind: Service
metadata:
name: tbm
spec:
selector:
app: tbm
ports:
- protocol: TCP
port: 8444
targetPort: 8444
type: LoadBalancer

```

### MongoDB yaml
```
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
name: mongo-pvc
spec:
accessModes:
- ReadWriteOnce
resources:
requests:
storage: 256Mi
---
apiVersion: v1
kind: Service
metadata:
name: mongo
spec:
selector:
app: mongo
ports:
- port: 27017
targetPort: 27017
---
apiVersion: apps/v1
kind: Deployment
metadata:
name: mongo
spec:
selector:
matchLabels:
app: mongo
template:
metadata:
labels:
app: mongo
spec:
containers:
- name: mongo
image: mongo
ports:
- containerPort: 27017
volumeMounts:
- name: storage
mountPath: /data/db
volumes:
- name: storage
persistentVolumeClaim:
claimName: mongo-pvc
```

## License
MIT License © 2021 [GrapeTrue]
