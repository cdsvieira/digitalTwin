# Digital Twin
 
## How to deploy

1 - Run `mvn clean package` to generate the .jar

2 - Run `docker build -t api-docker-image .` to build the image

3 - Run `docker-compose up` to launch both the service and database containers

## API contract

Spring api-docs generated :

 - openAPI: http://localhost:9091/v3/api-docs

 - swagger-ui: http://localhost:9091/swagger-ui/index.html

