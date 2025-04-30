# ISH (Insurance System for Health)

## Project Overview
ISH (Insurance System for Health) is a fully integrated online platform that allows citizens to apply for various health and insurance plans tailored to their life situations and profiles. The system evaluates citizens' eligibility based on their provided information, enabling a more efficient and streamlined application process.

## 🌐 Live Website
🚀 **Experience Insurance System for Health!**
- **🔗 Live Website:** [Insurance System for Health](https://boiniraj.github.io/Insurance-System-for-Health-Frontend/index.html)

---
<br>

![Home Page](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/home.png)

---

## Technology Stack
- **Frontend**: HTML,Css, JavaScript
- **Backend**: Java,Spring Boot, Rest Apis, SQL Microservices
- **Microservices Architecture**: Config Server, Eureka Server, API Gateway, Admin Server
- **Containerization**: Docker
- **Cloud**: AWS for infrastructure management
- **Version Control**: GitHub
- **Testing**: JUnit for unit testing
- **API Documentation & Testing**: Swagger, Postman

## Key Features
- Citizens can apply for health and insurance plans online.
- The system determines eligibility based on user-provided data.
- Approved citizens are provided with monthly benefits based on their selected plans.

## Government Schemes Covered
- **SNAP**: A food assistance program for low-income individuals.
- **CCAP**: A childcare assistance program for low-income families.
- **Medicaid**: A health insurance plan for citizens with limited income and resources.
- **Medicare**: Health insurance for citizens over 65 years old.
- **QHP**: A commercial health insurance plan that citizens can purchase.

## Microservices Architecture
1. **Config Server**: Centralized configuration management for microservices.
2. **Eureka Server**: Service registry for discovering and registering microservices.

   ![Eureka server](/images/eureka.png "eureka")
   
5. **API Gateway**: Manages routing for microservices, providing a unified entry point for clients.
7. **Admin Server**: Monitors and manages the various microservices, including health checks and application status.

   ![Admin server](/images/Admin.png "Admin")

## API Development
- **RESTful APIs**: A comprehensive set of RESTful APIs were developed to manage user authentication, profile management, application submissions, eligibility determination, and more.
- **Swagger**: Used for API documentation, providing an interactive interface for testing APIs.
- **Postman**: Extensively used to test API endpoints, validate responses, and simulate client-server interactions.


## Modules
     
2. **User Management Module (UM)**
   - User authentication (Login/Signup)
   - Profile management
   - Forgot password and dashboard functionality
     
   ![Register/Lgin](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/User-Register.png)
     
3. **Citizen Registration Module (AR)**
   - Citizen onboarding for various plans

   ![User Registrations](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Citizen%20Registration.png)

   **Insurance Id Generation**
   - We Generate Insurance ID or Case No Based On Application Id

   ![User Registrations](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/GenerateCaseNo.png)
   

 **Plan Selection**
 - we give Case No and Select the Plan

 ![User Registrations](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Plan-Selection.png)

     
5. **Data Collection Module (DC)**
   - Collect KYC (Know Your Customer) information, certificates, and documents

<p align="center">
  <img src="https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Income-Details.png" alt="User Data 1" width="60%" />
   <br>
  <img src="https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Child.png" alt="User Data 2" width="60%" />
   <br>
  <img src="https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Education-Details.png" alt="User Data 2" width="60%" /> 
</p>

**Fetch Citizen All Given Data**
  - Fetch Citizen Given Details Based On CaseNo
    
     ![Citizen Details](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Citizen-All-Details.png)   

  

   
    
     
5. **Eligibility Determination Module (ED)**
   - Match citizen data against plan rules to determine eligibility

   ![Eligibility](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Eligibility-Check.png)
     
6. **Payment Module**
  -  If the citizen's data satisfies the plan rules, the system proceeds to the payment stage

 ![Eligibility](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Payment-Details.png)


 ![Eligibility](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Payment-Submission.png)





7. **Payment Confirmation Email**
   – A confirmation email is sent to citizens after successful payment.

   ![benfit](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Payment%20Succus%20email.png)
     
8. **Reports Module**
   - Generate various reports such as daily, weekly, and monthly status reports, and citizen approval/rejection reports.

## Installation Instructions

### Prerequisites
- Java 17
- Spring Boot
- Docker
- AWS Account (for deployment)
- MySQL (for database)

### Steps to Install

1. **Clone the repository:**
   ```bash
   git clone https://github.com/boiniraj/Insurance-System-for-Health.git
   cd ISH-Project

### API Testing (Postman):
- Import the Postman collection provided in the /postman directory for testing various API endpoints.
- View API documentation and test endpoints using Swagger UI available at /swagger-ui.html.

### Future Improvements
- Frontend Development: Enhance the UI/UX of the frontend using React.
- Security: Implement JWT-based authentication for secure access.
- Scalability: Further optimize microservices for handling larger data loads.
