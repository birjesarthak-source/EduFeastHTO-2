# EduFest — College Event & Exam Room Management System

## Stack
Java 17, Spring Boot 3.4.5, Maven, MySQL, Spring Data JPA/Hibernate, Thymeleaf, Bootstrap 5.

## 1. Create database
Open MySQL:
```sql
CREATE DATABASE college_event_manager;
```

## 2. Configure password
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:YOUR_PASSWORD}
```
For a quick local run, replace `YOUR_PASSWORD`. For GitHub, use environment variables instead.

## 3. Run
```bash
mvn clean spring-boot:run
```

Open:
http://localhost:10000

The application uses port `10000` by default (`server.port=${PORT:10000}`).
If you set the `PORT` environment variable, open that port instead. After
changing templates or styles, restart the running Spring Boot application and
hard-refresh the browser (`Ctrl+F5`) to load the updated page and stylesheet.

Demo login:
- Email: admin@edufest.local
- Password: admin123

## 4. Main flow
Classrooms → Students → Events → Auto Allocate

For exams:
Students → Exams → Generate Seating → Find Exam Room

## Notes
Hibernate creates/updates tables from the entities. `schema.sql` documents the database bootstrap and `data.sql` inserts sample data after JPA initialization.
