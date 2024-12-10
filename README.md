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
school (Project Root Directory)
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.school
│   │   │       ├── configuration
│   │   │       │   └── (Configuration classes, e.g., SecurityConfig)
│   │   │       ├── controller
│   │   │       │   └── (Controller classes, e.g., AuthController, StudentController, SubjectController, TeacherController)
│   │   │       ├── DTO
│   │   │       │   └── (Data Transfer Object classes, e.g., StudentDTO, TeacherDTO, SubjectDTO, UserDTO)
│   │   │       ├── entity
│   │   │       │   └── (Entity classes for database mapping, e.g., Student, Subject, Teacher, User, UserAuth)
│   │   │       ├── repository
│   │   │       │   └── (JPA Repository interfaces, e.g., StudentRepository, SubjectRepository, TeacherRepository, UserRepository, UserAuthRepository)
│   │   │       ├── service
│   │   │       │   └── (Service implementation classes, e.g., StudentService, etc)
│   │   │       └── serviceInterface
│   │   │           └── (Service interface classes, e.g., StudentServiceInterface, etc.)
│   │   ├── resources
│   │   │   ├── templates
│   │   │   │   ├── dashboard.html
│   │   │   │   ├── loginPage.html
│   │   │   │   ├── student-form.html
│   │   │   │   ├── student-list.html
│   │   │   │   ├── subject-form.html
│   │   │   │   ├── teacher-form.html
│   │   │   │   ├── teacher-list.html
│   │   │   │   └── two-factor.html
│   │   │   └── application.properties
├── JRE System Library [JavaSE-23]
├── Maven Dependencies
├── pom.xml
├── README.md
└── target

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
