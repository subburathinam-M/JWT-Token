# 🔐 JWT Authentication System with Spring Boot & MongoDB


A secure and robust authentication system built using **Java**, **Spring Boot**, **MongoDB**, and **JWT**, with role-based access control and refresh token handling.

---

## 🧰 Tech Stack

- 💻 Java 17  
- 🚀 Spring Boot  
- 🛢 MongoDB  
- 🔐 JWT (Access + Refresh Tokens)  
- 📄 Swagger (API Documentation)  
- 🧪 Postman (API Testing)  
- 🛠 Maven (Build Tool)

---

## 🌟 Features

✅ JWT Authentication with Access & Refresh Tokens  
🔒 Role-Based Authorization (ADMIN / USER)  
📦 Stateless Security (No Session Stored)  
🔄 Refresh Token Endpoint  
🧂 Password Encryption using BCrypt  
📄 Swagger API Docs  
🗃 MongoDB for User Storage  
🚫 Custom Exception Handling
🛡️ Password Encryption (BCrypt)

---

## 📂 Project Structure

'''
jwt-auth/
├── src/
│   ├── main/
│   │   ├── java/com/jwt/jwt/
│   │   │   ├── config/        # Security & Swagger config
│   │   │   ├── controller/    # REST APIs
│   │   │   ├── dto/           # Request/Response objects
│   │   │   ├── entity/        # Data models
│   │   │   ├── exception/     # Custom error handling
│   │   │   ├── repository/    # MongoDB queries
│   │   │   ├── response/      # API response formats
│   │   │   ├── service/       # Business logic
│   │   │   └── utils/         # JWT utilities
│   │   └── resources/
│   │       ├── application.properties # Config
│   │       └── logback-spring.xml    # Logging
├── .gitignore
├── pom.xml
└── README.md
'''
