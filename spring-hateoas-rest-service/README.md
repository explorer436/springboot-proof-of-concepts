Hypermedia is an important aspect of REST. It lets you build services that decouple client and server to a large extent and let them evolve independently. The representations returned for REST resources contain not only data but also links to related resources. Thus, the design of the representations is crucial to the design of the overall service.

https://en.wikipedia.org/wiki/HATEOAS

To model the greeting representation, create a resource representation class. As the _links property is a fundamental property of the representation model, Spring HATEOAS ships with a base class (called RepresentationModel) that lets you add instances of Link and ensures that they are rendered as shown earlier.

Create a plain old java object that extends RepresentationModel and adds the field and accessor for the content as well as a constructor. Take a look at Greeting.java.

@JsonCreator: Signals how Jackson can create an instance of this POJO.

@JsonProperty: Marks the field into which Jackson should put this constructor argument.

Spring will use the Jackson JSON library to automatically marshal instances of type Greeting into JSON.

The GreetingController does not specify GET vs. PUT, POST, and so forth, because @RequestMapping maps all HTTP operations by default. Use @GetMapping("/greeting") if you want to specifically call out that it uses GET mapping.

The most interesting part of the method implementation is how you create the link that points to the controller method and how you add it to the representation model. Both linkTo(…) and methodOn(…) are static methods on ControllerLinkBuilder that let you fake a method invocation on the controller. The returned LinkBuilder will have inspected the controller method’s mapping annotation to build up exactly the URI to which the method is mapped.

Spring HATEOAS respects various X-FORWARDED- headers. If you put a Spring HATEOAS service behind a proxy and properly configure it with X-FORWARDED-HOST headers, the resulting links will be properly formatted.

The call to withSelfRel() creates a Link instance that you add to the Greeting representation model.

Use this command to start the application in local computer: `./mvnw clean && ./mvnw spring-boot:run`
For testing, visit the following links in a browser:
1. http://localhost:8080/greeting
1. http://localhost:8080/greeting?name=testUser

References:

https://spring.io/guides/gs/rest-hateoas/