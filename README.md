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

```
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
```

---

## 🚀 Getting Started

### ✅ Prerequisites

Java 17

Maven 3.8+

MongoDB (running locally or connection string)

Postman (for API testing)

---

### 🏗 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/jwt-auth.git
   cd jwt-auth
   ```

2. **Build the project**
   ```
      mvn clean install
      ```
3. **Run the application**
   ```
   mvn spring-boot:run
   ```

5.  **Access the application**
   ```
http://localhost:8080
```
---

### ⚙️ Configuration

Edit src/main/resources/application.properties:

```

# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/jwt

# JWT Secret & Expiration
jwt.secret=YourStrongSecretKeyHere
jwt.expiration=3600000               # Access Token Validity (1 hour)
jwt.refreshExpiration=604800000      # Refresh Token Validity (7 days)

```






