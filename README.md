# APPLICANT TRACKING SYSTEM (ATS) BACKEND 

## Overview
This is a backend API for an Applicant Tracking System.
It allows users to manage and track job postings and applications with authentication and role-based access.

The system is designed on a layered architecture and focuses on scalability, security and maintainability.

## Tech Stack

- Java
- SpringBoot
- PostgresSQL
- Docker
- JWT Authentication

## Features

- User registration and login
- JWT-based authentication
- Role-based access control
- Job posting management
- Application tracking
- RESTful API design

## Project Structure

The application follows a layered architecture:

- Controller: Handles HTTP requests and responses
- Service: Contains business logic
- Repository: Handles database interactions
- Model: Defines data structures 

## How to Run

### Using Docker

1. Clone the repository:
    For bash;
    git clone https://github.com/JesseAltraide/ATS.git
    cd ATS

2. Build the container
    docker-compose up --build
    Application starts on http://localhost:9090

## API Endpoints

### User
- POST /auth/register
- POST /auth/login

### Candidate
- POST /candidate/apply
- GET /candidate/allJobs

### Recruiter
- POST /recruiter/create/job
- GET /recruiter/getAllJobs

### Admin 
- GET /admin/user/all
- GET /admin/jobs/all

## Example Usage

### Login

POST /auth/login

Request Body:
json
{
  "email": "user@example.com",
  "password": "password123"
}



## Environment Variables

The following environment variables are required:

- DB_URL
- DB_USERNAME
- DB_PASSWORD
- JWT_SECRET

## Status

This project is actively being developed and improved.
