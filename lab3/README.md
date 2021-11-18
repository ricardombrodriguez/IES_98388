# LAB 03



## Accessing databases in SpringBoot

**JPA**, or **Java Persitance API**, is the Java ORM (object-relational mapping) standard for storing, accessing, and managing Java objects in a relational database. 

There are several frameworks that implement the JPA specification, such as the Hibernate framework. Because of their intertwined history, Hibernate and JPA are frequently conflated. However, like the Java Servlet specification, JPA has spawned many compatible tools and frameworks; Hibernate is just one of them. Hibernate is an ORM library for Java. 

Spring Data uses and enhances the JPA. When you use Spring Data, your Java code is independent from the specific database implementation.



#### Data persistance in Java

From a programming perspective, the ORM layer is an adapter layer: it adapts the language of object graphs to the language of SQL and relational tables. The ORM layer allows object-oriented developers to build software that persists data without ever leaving the object-oriented paradigm.

When you use JPA, you create a map from the datastore to your application's data model objects. Instead of defining how objects are saved and retrieved, you define the mapping between objects and your database, then invoke JPA to persist them. If you're using a relational database, much of the actual connection between your application code and the database will then be handled by JDBC, the Java Database Connectivity API.



## Spring Boot CRUD Application with Thymeleaf

The implementation of DAO layers that provide CRUD functionality on JPA entities can be a repetitive, time-consuming task that we want to avoid in most cases. Luckily, Spring Boot makes it easy to create CRUD applications through a layer of standard JPA-based CRUD repositories.

For this project, we will develop a Spring Boot application that allows us to add, update or delete users.

#### Dependencies

Dependencies needed: Thymeleaf, Spring Data JPA, Spring Web, H2 database and Spring Boot Validation (we can use Spring Initializr to generate the project for us).


#### The domain layer

Now that all the project dependencies are already in place, let's now implement a naive domain layer (one single class which will be responsible for modeling *User* entities). Note the annotations!

- @Entity -> Specifies that the class is an entity. This annotation is applied to the entity class. Therefore, the JPA implementation, which is Hibernate, in this case, will be able to perform CRUD operations on the domain entities. 
- @Id -> identifies the primary key of the entity.
- @GeneratedValue -> Provides for the specification of generation strategies for the values of primary keys.
- @NotBlank -> The annotated element must not be null and must contain at least one non-whitespace character. Accepts CharSequence. This implies that we can use Hibernate Validator for validating the constrained fields before persisting or updating an entity in the database.

The *User* entity class has three attributes (id,name,email), plus the getter and setter methods associated with them.


#### Repository layer

Spring Data JPA allows us to implement JPA-based repositories with minimal fuss.

Spring Data JPA is a key component of Spring Boot's spring-boot-starter-data-jpa that makes it easy to add CRUD functionality through a powerful layer of abstraction placed on top of a JPA implementation. This abstraction layer allows us to access the persistence layer without having to provide our own DAO implementations from scratch.

To provide our application with basic CRUD functionality on User objects all that we need to do is to extend the CrudRepository interface (interface for generic CRUD operations on a repository for a specific type) and Spring Data JPA will provide implementations for the repository's CRUD methods for us.

The repository is identified by the @Repository annotation. 


#### Controller layer and View layer

Thanks to the layer of abstraction that spring-boot-starter-data-jpa places on top of the underlying JPA implementation, we can easily add some CRUD functionality to our web application through a basic web tier.

In our case, a single controller class will suffice for handling GET and POST HTTP requests and then map them to calls to our UserRepository implementation.

The methods in the *UserController* allows the web users to do operations in the model (User entity) and present those business logic changes in the View (html files).

The HTML files must be added inside the main/resources/templates/ folder. Each possible User-related operation should have its own view. For example, the user create operation has its own HTML page (add-user.html). In addition, a *index.html* file is the main page that connects with other HTML files.

#### Running the application

To run the application, we can type ```./mvnw spring-boot:run``` inside the project folder and then access http://localhost:8080.

#### Questions (b)

**The “UserController” class gets an instance of “userRepository” through its constructor; how is this new repository instantiated?**
- The UserRepository is initialized because UserController has the @Autowired annotation. This annotation marks a constructor as to be autowired by Spring's dependency injection facilities. That being said, the constructor injects an instance of UserRepository on the UserController object for later use.

**List the methods invoked in the “userRepository” object by the “UserController”. Where are these methods defined?**
- The methods are findAll(), save(), findById(), delete(). These userRepository methods are defined in the CrudRepository class, which the UserRepository extends.

**Where is the data being saved?**
- The data is being saved using the h2 database, thanks to the h2 dependency we added in our project. H2 database is an open-source lightweight Java database. It can be embedded in Java applications or run in the client-server mode. Mainly, H2 database can be configured to run as inmemory database, which means that data will not persist on the disk.

**Where is the rule for the “not empty” email address defined?**
- The "not empty" rule is defined on the User entity class, with the annotation @NotBlank when we declare the attribute. The annotated elements must not be null and must contain at least one non-whitespace character, otherwise they're invalid.



**Postman**

We can use the Postman utility to test our applications endpoints. We choose the HTTP method (GET, POST, ...), the type of the data and how we want it to be presented.

Example: http://localhost:8080/api/v1/employees

In the beginning, the list of employees is empty, so we must insert some rows.



Insert:

![image-20211116151508998](/home/ricardo/snap/typora/42/.config/Typora/typora-user-images/image-20211116151508998.png)



GET BY ID:

![image-20211116151609180](/home/ricardo/snap/typora/42/.config/Typora/typora-user-images/image-20211116151609180.png)

GET EMPLOYEE BY EMAIL:

![image-20211116151814043](/home/ricardo/snap/typora/42/.config/Typora/typora-user-images/image-20211116151814043.png)



GET ALL EMPLOYEES:

![image-20211116151640191](/home/ricardo/snap/typora/42/.config/Typora/typora-user-images/image-20211116151640191.png)



We can do the rest of the operations mapped in the controller (update and delete employee).

That being said, POSTMAN is very useful to test our REST API.



# Review questions



#### A) Explain the differences between the RestController and Controller components used in different parts of this lab.



The @Controller annotation is used to mark classes as Spring MVC controller. This one is used with a *@RequestMapping* annotation for request handling methods. Note that @GetMapping and @PostMapping annotations, very used in *lab03/ex1/*, are composed annotations that act as a shortcut for @RequestMapping with a specific HTTP method (GET, POST).

On the other side, @RestControllers is a specialized version of the @Controller annotation that does nothing more than adding the @Controller and @ResponseBody annotations. Besides that, it also converts the response to JSON/XML automatically.



#### B) Create a visualization of the Spring Boot layers (UML diagram or similar), displaying the key abstractions in the solution of 3.3, in particular: entities, repositories, services and REST controllers. Describe the role of the elements modeled in the diagram.



#### C) Explain the annotations @Table, @Column, @Id found in the Employee entity.



- @Table - Specifies the primary table for the annotated entity. If no Table annotation is specified for an entity class, the default values apply (in other words, the name of the class). In this lab, *Employees* table represents the *Employee* class.
- @Column - Specifies the mapped column for a field. If no Column annotation is specified, the default values apply (name of the attribute). An *Employee* has many attributes. Each one of them constitutes a column in the *Employees* table ("first_name", "last_name", etc...).
- @Id - Specifies the primary key of an entity. The field or property to which the Id annotation is normally an auto-generated incremented integer (just like it's in this lab). The mapped column for the primary key of the entity is assumed to be the primary key of the primary table. If no Column annotation is specified, the primary key column name is assumed to be the name of the primary key property or field!



#### D) Explain the use of the annotation @AutoWired (in the Rest Controller class)



In Spring, using autowiring instead of creating objects in our own, lets Spring create them, which can be very helpful. 

```
@Autowired
private MovieRepository movieRepository;
private QuoteRepository quoteRepository;
```

In the example above, the @Autowired annotation marks a field as to be autowired by Spring's dependency injection facilities.

If we mark some class as a component, during initialization spring creates an instance of this class in its context. Later, when we want to use the object marked with @Autowired, Spring will inject this earlier created instance into our program. That being said, autowiring is a way of letting Spring resolve dependencies automatically instead of doing it manually, which is very time costly.







