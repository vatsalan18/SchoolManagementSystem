# SchoolManagementSystem
**Overview** : 
The SchoolManagementSystem is designed using Spring Boot framework, to manage the subject, teacher and students related institutional information. The application implements role-based (admin & viewer) access to the appliction for performing CRUD operations against entities like subject, teacher and students. It implements major security measures at various levels.

**Features** : 
1. Authentication Two-Factor Authentication
An extra layer of authentication for secure login.

2. Role based - Authorization
Admin users can manage students, teachers, and subjects.
Viewers can only view the information

3. CSRF tokenization

4.The user will be logged out in some minutes of inactivity

5. Ensures a single active session per user.

6. Input field Validation

7. Parameterized query to avoid SQL injection

8. Session management

**Technology Stack** : 
1. Backend - Java V 23, Spring Boot Framework V 3.3.5 
2. Frontend - Thymeleaf templates, HTML, CSS 
3. Database - MySql V 8.0.33
4. Security - Spring Security
5. Build Tool - Maven 

**Setup and Installation** : 
1. Clone the repository
git clone https://github.com/vatsalan18/SchoolManagementSystem.git

2. Configure the database
spring.datasource.url=jdbc:mysql://localhost:3306/schoolmanagementsystem
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

3. Build the application and start server
mvn clean install
mvn spring-boot:run

4. Access the application on your browser
   http://localhost:8082/login
