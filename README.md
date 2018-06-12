# auction
The project Auction is developed using Spring Boot Thymeleaf, Spring Security.
## Application Initialization 
Class BootstrapDataPopulator creates:
Default roles ROLE_USER, ROLE_ADMIN.
Default users user and admin. Password for user is user and for admin is admin.
## Capabilities
User can register.
User can create Requests. Entities User and Request have @OneToMany relationship,
User can create requests of two types such as sell request and buy request.
Requests can be processed clicking on Process. 
For sell request is found matching buy request using parameters product, price, itemsCount, type.
## Configuration
App configured in the file application.properties
Application requires MYSQL DB.
## Start up
Start auction Spring Boot web app using Terminal
$ mvn spring-boot:run
Access http://localhost:8080
