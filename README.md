TODO list:

kafka - zookeeper

https://spring.io/guides
https://spring.io/guides#topical-guides

https://spring.io/guides/gs/accessing-mongodb-data-rest/
https://spring.io/guides/gs/accessing-neo4j-data-rest/
https://spring.io/guides/gs/rest-hateoas/
https://spring.io/guides/gs/cloud-circuit-breaker/
https://web.archive.org/web/20210414191759/https://spring.io/guides/gs/spring-boot-kubernetes/
https://spring.io/guides/gs/spring-cloud-stream/
https://spring.io/guides/gs/gateway/
https://spring.io/guides/gs/routing-and-filtering/
https://spring.io/guides/gs/service-registration-and-discovery/
https://spring.io/guides/gs/spring-boot-docker/
https://spring.io/guides/gs/authenticating-ldap/


Spring Cloud Contract Tests - what value do they provide to the producer application? Write a small POC for this.

sending email from a springboot application

single sign-on with spring security OAuth 2
spring-security-oauth

springboot Apache Kafka messaging
spring-cloud-stream
spring-cloud-stream-binder-kafka

spring-session

Spring Expression Language

Eureka - service registration and discovery

https://github.com/in28minutes/pcf-crash-course-with-spring-boot

https://github.com/in28minutes/spring-microservices-v2

Spring with Akka - actor and service models

https://en.wikipedia.org/wiki/OpenResty

https://www.theregister.com/2016/09/20/wtf_is_openresty_the_worlds_fifthmostused_web_server_thats_what/

Scaling a docker service in AWS ECS.

https://spring.io/guides/gs/spring-boot-docker/

Concurrency and parallelism - what are the differences? Need to do more research about this.

nginx

wiremock

asciidoctor
spring-restdocs
spring-restdocs-asciidoctor

spring-auto-restdocs-json-doclet
gradle jsonDoclet task

org.json:json
org.webjars:json-stringify

spring-data-redis

snakeyaml

groovy springboot integration

dealing with JSON inside a springboot application

springfox-swagger2
springfox-swagger-ui

MapStruct and JMapper java bean mappings

chef server and chef environment

Do a POC using lua language

mocha test framework


------------------

Implement circuit breaker pattern
- in Java
- in Haskell
Using a counter in local cache
- keep track of the http responses from the backend services; if the http status is 500 or 503 or 5XX, increment a counter in local cache; if the counter reaches 5 (or any arbitrary value, open the circuit breaker)
- wait for a given time period (again, another arbitrary value and close the circuit breaker)

------------------

implement local cache using Haskell
- maintaining key-value pairs
- maintaining multiple cache stores in a single cache instance (e.g. one for data from a backend service, one for a custom counter, one for something else, etc.)

------------------

Thread safe operations in Haskell
- If we have to increment a counter value is cache and also retrieve it for very fast transactions, how to make sure that it will be thread safe?

------------------

Generate swagger documentation
- In Haskell for an API
https://www.youtube.com/watch?v=rK5vykk1tbo

------------------

XML to json
- And vice versa in Haskell
- if we are working with a SOAP service, the object that the service is going to get will be in XML format.
- if we need to invoke RESTful services in the backend, how to transform the data and invoke the backend services?

------------------

Learn how to do these in Haskell:
- decode jwt; extract information (fields, etc.) out of it
- validate jwt against a custom key file
- inject values for fields based on environment
- inject secrets like database userids, passwords
- oauth server and client applications
- how to build a docker image and deploy it in cloud environments
- sql and no sql database connectivity applications
- change logging levels based on environment
- Go throught the list of features offered by spring framework and learn how to implement the solutions in Haskell
- xml to json and json to xml

------------------

How does Haskell handle garbage collection? Not just theoretically? How does it compare to other languages like C++ and Java in a production environment?

------------------

Is nginx used with other languages?

Can we write AWS lambdas in C or Lua or Rust?

In Rust, do we have to create types for everything?

------------------

What are webpack alternatives for other languages? What is the need for it? How do other languages manage without it?

------------------

Swing UI applications - https://en.wikipedia.org/wiki/Swing_(Java)#:~:text=Swing%20is%20a%20GUI%20widget,Abstract%20Window%20Toolkit%20(AWT).

------------------