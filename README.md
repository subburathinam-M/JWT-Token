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

4.  **Access the application**
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
---

### 📚 API Documentation

# 📄 Swagger UI
```
🔗 http://localhost:8080/swagger-ui.html
```

# 📬 Postman Collection

```

| Method | Endpoint       | Description               |
|--------|----------------|---------------------------|
| POST   | /register      | Register new user         |
| POST   | /login         | Login and get tokens      |
| POST   | /refresh       | Get new access token      |
| GET    | /allusers      | Get all users (auth)      |

```

---

# 🛠️ Example Requests

1. Register a User

```

POST http://localhost:8080/register
Content-Type: application/json

{
  "username": "admin",
  "email": "admin@example.com",
  "password": "admin123",
  "role": "ADMIN"
}

```

2. Login and Get Tokens

```

POST http://localhost:8080/login
Content-Type: application/json

{
  "email": "admin@example.com",
  "password": "admin123"
}

```

Response:

```

{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

```

3. Access Protected Route

```
GET http://localhost:8080/allusers
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```
---


## ✨ Full Authentication Flow diagram 

### 🔑 1. Login Flow

![Login Flow](images/LoginFlow.png)

### 📈 1.1 Sequence Diagram

```mermaid
sequenceDiagram
    👤 Client->>+🖥️ Server: POST /login {email, password}
    🖥️ Server->>+🗄️ DB: Verify credentials
    🗄️ DB-->>-🖥️ Server: User data
    🖥️ Server->>🖥️ Server: Generate tokens
    🖥️ Server-->>-👤 Client: {accessToken, refreshToken}
```


### 🔐 Modified Flow with Role

```mermaid
sequenceDiagram
    Client->>Server: POST /login (email, password)
    Server->>DB: Verify credentials
    DB->>Server: User data (email, role)
    Server->>Server: Generate tokens (with role)
    Server->>Client: {accessToken, refreshToken} (both contain role)

    Note over Client: Access token expires
    Client->>Server: POST /refresh (refreshToken)
    Server->>Server: Verify token + extract role
    Server->>Client: New tokens (with original role)
```

### 🔐2. Access Token Usage Flow

![Access Token Usage](images/AccessTokenUsage.png)

### 📈 2.1 Sequence Diagram

```mermaid
sequenceDiagram
    👤 Client->>+🖥️ Server: GET /protected (🔐 Bearer accessToken)
    🖥️ Server->>🖥️ Server: Validate JWT
    🖥️ Server-->>-👤 Client: Protected data
```






---

# 🧑‍💻 Development

Build and Run Tests

```
mvn clean package
```
Code Formatting

```
mvn spotless:apply
```
Dependency Tree
```
mvn dependency:tree
```
---


# 🤝 Contributing

1.Fork the project

2.Create your feature branch (git checkout -b feature/AmazingFeature)

3.Commit your changes (git commit -m 'Add some AmazingFeature')

4.Push to the branch (git push origin feature/AmazingFeature)

5.Open a Pull Request

---

# 📜 License

Distributed under the MIT License. See LICENSE for more information.

---
# 📧 Contact

Subburathinam M – subburathinam720@gmail.com

🔗 [GitHub Profile](https://github.com/subburathinam-M)


---

# 🙏 Acknowledgments

Spring Security

JJWT Library

MongoDB

Swagger

---

Made with ❤️ in Java ☕








