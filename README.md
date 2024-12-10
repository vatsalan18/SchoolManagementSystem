# SchoolManagementSystem
**Overview** : 
The SchoolManagementSystem is designed using Spring Boot framework, to manage the subject, teacher and students related institutional information. The application implements role-based (admin & viewer) access to the appliction for performing CRUD operations against entities like subject, teacher and students. It implements major security measures at various levels.

**Features & Security Objectives** : 
1. Authentication Two-Factor Authentication
An extra layer of authentication for secure login.
2. Role based - Authorization
Admin users can manage students, teachers, and subjects.
Viewers can only view the information
3. CSRF tokenization
4. The user will be logged out in some minutes of inactivity
5. Ensures a single active session per user.
6. Input field Validation
7. Parameterized query to avoid SQL injection
8. Session management

**Project Structure** 

![image](https://github.com/user-attachments/assets/853542d8-8c6c-47dc-8968-ebb6c10ae0b3)


**Folder and File Descriptions**
- **configuration**: Contains configuration classes like `SecurityConfig` to manage application-level security.
- **controller**: Contains controller classes for handling user interactions (e.g., `AuthController` for login/authentication).
- **entity**: Contains entity classes for mapping database tables to Java objects.
- **repository**: JPA repository interfaces for database operations.
- **service**: Service layer classes containing business logic.
- **resources/templates**: HTML templates for the frontend (e.g., `dashboard.html` for the user dashboard).


**Technology Stack** : 
1. Backend - Java V 23, Spring Boot Framework V 3.3.5 
2. Frontend - Thymeleaf templates, HTML, CSS 
3. Database - MySql V 8.0.33
4. Security - Spring Security
5. Build Tool - Maven 

**Setup, Installation** : 
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

**Usage Guidelines**
1. Login as an admin user.
2. On Dashboard there are 3 modules Student, Subject and Teacher
3. Select any one of the modules (e.g. Subject)
4. User will be redirected to a page with Add new subject option.
5. Click on the Add new subject.
6. Enter all the details related to new subject to be added for the syllabus
7. Click on Save button.
8. Newly added subject will be displayed in the Subject list.
9. There will be option to Edit and Delete the added data from the subject list
10. Click on Edit option to update details.
11. Update and save the changes.
12. User will be able to see the updated data.
13. On Delete, the user is prompted for confirmation. On confirmation the data gets deleted from the table.
14. Similarly from the Dasboard other modules like Teacher and Students can be add, edit delete.
15. The user with viewer roles can only view the data added by admin.

**Security Improvements**
The key security implementations done for the project is at every basic stage of the application with a view to protect the sensitive data of the users. The include authentication using MFA, authrorization using role based accessibilty, active session and cookied managment, no parallel logins, CSRF token, parametirzed queries to avoid SQL injection attacks, session logout incase of user inactivity and user input validation.

**Testing Process**
SAST tool Sonarcube was used to perform testing along with manual security checks of the features implemented.

**Contributions**
A basic school managment system is built to perform CRUD operations safely using secure framework provided by Spring boot.

**References**

[1]baeldung, “Learn Spring Boot | Baeldung,” www.baeldung.com, Jun. 12, 2018. https://www.baeldung.com/spring-boot (accessed Nov. 12, 2024).
[2]Raja Anbazhagan, “Spring Security | SpringHow,” SpringHow, Dec. 14, 2020. https://springhow.com/spring-security/ (accessed Nov. 15, 2024).
[3]M. Repo, “Maven Repository: Search/Browse/Explore,” mvnrepository.com. https://mvnrepository.com/ (accessed Nov. 15, 2024).
[4]Spring, “Spring Projects,” Spring.io, 2019. https://spring.io/projects/spring-boot (accessed Nov. 27, 2024).
[5]“Getting Started | Securing a Web Application,” Getting Started | Securing a Web Application. https://spring.io/guides/gs/securing-web (accessed Dec. 01, 2024).
[6]“Tutorial: Using Thymeleaf,” www.thymeleaf.org. https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html (accessed Dec. 01, 2024).
[7]GeeksforGeeks, “JPA CRUD,” GeeksforGeeks, Apr. 02, 2024. https://www.geeksforgeeks.org/jpa-crud/ (accessed Dec. 05, 2024).
