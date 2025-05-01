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
---
# Government Schemes Covered

## SNAP (Supplemental Nutrition Assistance Program) 

   - Provides food assistance to low-income individuals and families, enabling them to purchase nutritious food. Eligibility is determined based on income and family size, with the aim of reducing hunger, 
    especially among children and the elderly.

## CCAP (Child Care Assistance Program) 

   - Assists low-income families with childcare costs, allowing parents to maintain employment. The program ensures children receive safe, educational care, promoting family stability and child welfare.

## Medicaid

   - Provides medical assistance to low-income individuals who are uninsured. Covers essential healthcare services such as doctor visits, prescriptions, and emergencies, improving access to healthcare for 
   underserved populations.

## Medicare

   - Provides healthcare for seniors aged 65 and older, covering hospital care, doctor visits, and prescriptions. Ensures elderly citizens receive the healthcare services they need as they age.

## CAJW (Citizen Assistance for Jobless Youth)

   - Supports unemployed youth with financial aid, career counseling, and training programs to help them re-enter the workforce and secure stable employment.

## QHP (Qualified Health Plan) 

   - A commercial health insurance plan that citizens can purchase through the marketplace. It covers preventative care, emergency services, and outpatient care, offering comprehensive health coverage to those 
     not eligible for government programs.

![Home Page](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Plans-Package.png)

---

## Microservices Architecture
1. **Config Server**: Centralized configuration management for microservices.
2. **Eureka Server**: Service registry for discovering and registering microservices.

   ![Eureka server](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Eureka-Server.png)
   
5. **API Gateway**: Manages routing for microservices, providing a unified entry point for clients.
7. **Admin Server**: Monitors and manages the various microservices, including health checks and application status.

   ![Admin server](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/admin-server.png)

## API Development
- **RESTful APIs**: A comprehensive set of RESTful APIs were developed to manage user authentication, profile management, application submissions, eligibility determination, and more.
- **Swagger**: Used for API documentation, providing an interactive interface for testing APIs.
- **Postman**: Extensively used to test API endpoints, validate responses, and simulate client-server interactions.


## 🎯 Features
   ---  
1. **User Management Module (UM)**
     - Implemented user authentication with login and signup functionality, including an account activation process where a temporary password is sent to the user’s email for account activation.

     - Developed profile management features, allowing users to update and manage their personal information securely.
     
   ![Register/Lgin](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/User-Register.png)

---
     
2. **Citizen Registration Module (CR)**
     - Developed and implemented a citizen onboarding system to enable registration for various government health and insurance plans.

   ![User Registrations](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Citizen%20Registration.png)

---

 3. **Insurance ID Generation**
      - Implemented logic to generate unique Insurance IDs or Case Numbers based on Application IDs to streamline tracking and identification of insurance applications.

   ![User Registrations](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/GenerateCaseNo.png)

---
   

 4. **Plan Selection**
      - Retrieved all available plan details from the backend and displayed them on the frontend, allowing users to view, select a suitable plan, and link it to their Insurance ID.

 ![User Registrations](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Plan-Selection.png)

---

     
5. **Data Collection Module (DC)**
      - Collected comprehensive user information including KYC details, income status, number of children, and education background, to support eligibility assessment for insurance plans.

<p align="center">
  <img src="https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Income-Details.png" alt="User Data 1" width="90%" />
   <br>
  <img src="https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Child.png" alt="User Data 2" width="90%" />
   <br>
  <img src="https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Education-Details.png" alt="User Data 2" width="90%" /> 
</p>

---

6. **Fetch Citizen All Given Data**
     - Retrieved all submitted citizen details based on Case Number to support verification, plan selection, and policy issuance processes.
    
     ![Citizen Details](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Citizen-All-Details.png)   

---
     
7. **Eligibility Determination Module (ED)**
      - Assessed citizen eligibility by checking selected plans against criteria such as income, number of children, and education. If the criteria were met, users proceeded to the payment option; otherwise, an 
     error message was displayed with an option to exit.

   ![Eligibility](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Eligibility-Check.png)

---
     
8. **Payment Module**
     – Enabled seamless transition to the payment stage for citizens whose data met the selected plan's eligibility criteria, ensuring smooth processing of payments and plan activation

 ![Eligibility](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Payment-Details.png)


 ![Eligibility](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Payment-Submission.png)

---

9. **Payment Confirmation Email**.
     - implemented automated email notifications to confirm successful payment and provide citizens with details of their selected insurance plan and next steps.

   ![benfit](https://github.com/boiniraj/Insurance-System-Backend/blob/main/ISH-IMAGES/Payment%20Succus%20email.png)

---
     
10. **Reports Module**
      - Generate various reports such as daily, weekly, and monthly status reports, and citizen approval/rejection reports.

## Installation Instructions

### Prerequisites
- Java 17
- Spring Boot
- Microservices
- Docker
- Render (for deployment)
- MySQL (for database)
- Git
- GitHub

### Steps to Install

1. **Clone the repository:**
   ```bash
   git clone https://github.com/boiniraj/Insurance-System-Backend.git
   cd ISH-Project

### API Testing (Postman):
- Import the Postman collection provided in the /postman directory for testing various API endpoints.
- View API documentation and test endpoints using Swagger UI available at /swagger-ui.html.

### Future Improvements
- Security: Implement JWT-based authentication for secure access.
- Scalability: Further optimize microservices for handling larger data loads.
