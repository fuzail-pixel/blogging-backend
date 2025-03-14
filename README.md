# blogging-backend
Spring Boot backend for a blogging system with AI summarization and AWS deployment.


# Blogging Backend

## Overview
A backend service built using **Spring Boot** that allows users to create, retrieve, and manage blogs. It includes an **AI-powered text summarization** feature and is designed for **AWS deployment**.

## Features
- **RESTful API** for blog management (Create, Read, Update, Delete)
- **Pagination support** for fetching blogs efficiently
- **AI-powered blog summarization** using OpenAI API
- **Database integration** using PostgreSQL/MySQL
- **Dockerized application** for easy deployment
- **AWS deployment** (EC2, Lambda, API Gateway)
- **Optional Features:** JWT-based authentication, Redis caching, AWS S3 for image storage

## Tech Stack
- **Backend:** Java, Spring Boot, Spring Data JPA
- **Database:** PostgreSQL / MySQL
- **AI Integration:** OpenAI API / spaCy (via REST call)
- **Deployment:** Docker, AWS (EC2 / Lambda, API Gateway, S3)
- **Caching (Optional):** Redis

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/fuzail-pixel/blogging-backend.git
   cd blogging-backend
   ```
2. Configure **database settings** in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
3. Add **OpenAI API key** to environment variables:
   ```sh
   export OPENAI_API_KEY=your_api_key
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
### **Blog Management**
- `POST /api/blogs` – Create a new blog
- `GET /api/blogs?page=1&size=10` – Fetch all blogs (paginated)
- `GET /api/blogs/{id}` – Get a specific blog by ID
- `PUT /api/blogs/{id}` – Update a blog
- `DELETE /api/blogs/{id}` – Delete a blog

### **AI Integration**
- `POST /api/blogs/{id}/summary` – Generate a summary using OpenAI

## AWS Deployment
- **Dockerize the application:**
   ```sh
   docker build -t blogging-backend .
   docker run -p 8080:8080 blogging-backend
   ```
- **Deploy to AWS EC2 or Lambda** (steps to be added)



## Contributors
- **Fuzail Rahman** ([@fuzail-pixel](https://github.com/fuzail-pixel))



