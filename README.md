# Job Portal Backend System (REST API)

A Job Portal Backend System built using **Java + Spring Boot + Spring Security + JWT + MySQL**.  
This project provides secure REST APIs for user authentication, job posting, company management, and job applications.

---

## ✅ Features

### 🔐 Authentication & Authorization
- User Registration
- User Login with **JWT Token**
- **Role-Based Access Control** (USER / ADMIN)
- Password encryption using **BCrypt**

### 🏢 Company Module
- Add new company (Admin)
- Update company details (Admin)
- Delete company (Admin)
- View all companies

### 💼 Job Module
- Create job posting (Admin)
- Update job posting (Admin)
- Delete job posting (Admin)
- View all jobs
- Search/filter jobs (title, location, company, jobType)

### 📩 Application Module
- Apply for a job (User)
- View user applications (User)
- View all applications (Admin)
- Approve / Reject application (Admin)

### ✅ Extra
- Clean layered architecture:  
  **Controller → Service → Repository**
- DTO usage for request/response
- Global exception handling
- Proper response structure with HTTP status codes
- Postman Collection included

---

## 🛠 Tech Stack

- **Java**
- **Spring Boot**
- **Spring Security**
- **JWT Authentication**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Maven**
- **Postman** (API testing)

---

## 📂 Project Structure

src/main/java/com/example/jobportal
│
├── controller
├── service
│ └── impl
├── repository
├── entity
├── dto
├── security
├── exception
└── config

---

## ⚙️ Setup & Run (Local)

### ✅ 1) Clone the Repository
```bash
git clone https://github.com/nikhilgobbur3/job-portal-servlet-jsp.git
cd job-portal-servlet-jsp
