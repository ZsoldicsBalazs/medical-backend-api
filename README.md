# 💼 Patient Management Backend API

## 📌 Project Overview

**Title:** Patient Management Backend API  
**Technologies:** Spring Boot, Spring Security (JWT), JPA/Hibernate, PostgreSQL, Gradle  
**Authentication:** JSON Web Tokens (JWT)  
**Frontend:** Angular application (consumes this API)

## 🔧 General Description

This project is a backend application developed using **Spring Boot**, designed to efficiently manage medical data in a clinic or medical office. It provides a series of **REST endpoints** for managing:

- 🧍 Patients  
- 👨‍⚕️ Doctors  
- 📅 Appointments  
- 🧪 Medical Procedures

## 🔐 Authentication & Security

- Implements **JWT Authentication** for secure access control to API resources  
- Supports **user roles** (e.g., admin, doctor,patient etc.)  
- Protects sensitive endpoints from unauthorized access

## 🔗 Key Features

- Full **CRUD operations** for patients, doctors, and appointments  
- Associate medical procedures with doctors  
- Search appointments using filters like **patient CNP, name, or phoneNr**  
- Handles **Many-to-Many relationships** (e.g., Doctor ↔ Procedures)  
- Provides **filtering and pagination** services for large datasets

## 📦 Data Persistence

- Data is stored in a **PostgreSQL** database using **Spring Data JPA**  

## 🌐 Frontend Integration

The **Angular frontend** application consumes the backend API for displaying, filtering, and managing data through a user-friendly interface.

