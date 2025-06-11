👨‍💻 Author
Nikhil Baskar
Java Backend Developer | Spring Boot | REST APIs | SQL Server
github profile: https://github.com/nikkibaskar

# Employee Management - Spring Boot Backend

This is a Spring Boot backend demo project for managing Employees and Departments.
## 🔧 Features

- RESTful APIs for Employee and Department CRUD operations
- Spring Data JPA with SQL Server
- Logging with SLF4J and Log4j
- Exception Handling 
- HTTP Basic Authentication using Spring Security
- Unit testing with JUnit and Mockito
- Swagger UI for API testing and documentation

To get Started

### 1. Clone the repository
```bash
git clone https://github.com/nikkibaskar/employee-management-springboot.git
cd employee-management-springboot

note:

configure properties file in path: src/main/resources/application.properties

Update using:
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=YourDB
spring.datasource.username=your_username
spring.datasource.password=your_password


run using bash: mvn spring-boot:run


Access API docs using: http://localhost:8080/swagger-ui/index.html


| Username | Password |
|----------|----------|
| admin    | admin123 |
