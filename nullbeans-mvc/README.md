# spring-boots/nullbeans-mvc

Stuff inspired by [nullbeans.com](https://nullbeans.com/)

1. [How to create a CRUD REST API in Spring Boot](https://nullbeans.com/how-to-create-a-crud-rest-api-in-spring-boot/)
2. [Using PUT vs PATCH when Building a REST API in Spring](https://nullbeans.com/using-put-vs-patch-when-building-a-rest-api-in-spring/)
3. [How to validate REST calls in Spring Boot](https://nullbeans.com/how-to-validate-rest-calls-in-spring-boot/)
4. [The Java Bean Validation Cheat Sheet](https://nullbeans.com/the-java-bean-validation-cheet-sheet/)
5. [How to use Java Bean Validation in Spring Boot](https://nullbeans.com/how-to-use-java-bean-validation-in-spring-boot/)

Note: The REST DTOs and JSON are in the 'REST model'.

I made changes according to what I've read from the RFCs or stackoverflow. All hail stackoverflow!

Recall that Java Bean Validation has changed since I first used it. There is
the JSR-303. The Hibernate Validator gives you more constraints. And there is a JSP
describing writing custom constraints.

Since Spring Boot 2.3, bean validation requires this dependency
```xml
<dependency> 
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-validation</artifactId> 
</dependency>
```

Java 15+ lets you use `var` in lambda expressions, and you can apply these constraints on that
`var` like `Stream.of(stuff).map(@NotNull var x -> x.fx())`


For **Spring Boot Devtools** to work in IntelliJ you have to have 'Build project automatically' turned on.
Where this is done depends on the version of IntelliJ.