# OAuth2 Authorization & Resource Server Demo

This repository demonstrates a **Spring Boot 3.5.4** project with **Spring Security 6.x** implementing:

1. **OAuth2 Authorization Server**  
2. **Resource Server** using JWT authentication  

---

## Project Structure

- **Authorization Server**  
  - Responsible for issuing **Authorization Code** and **Access Tokens**.
  - Supports OAuth2 / OIDC flow.  
  - JWT tokens include a `roles` claim for user authorities (`USER`, `ADMIN`).  
  - Can be tested using [OIDC Debugger](https://oidcdebugger.com/debug).  

- **Resource Server**  
  - Protects `/couponapi` endpoints.  
  - Validates JWT tokens issued by the Authorization Server.  
  - Supports the following endpoints:
    - `GET /couponapi/coupons/{code}` → accessible by `USER` or `ADMIN`
    - `POST /couponapi/coupons` → accessible by `ADMIN` only  

---

## How to Run

1. **Build the project**
   ```bash
   mvn clean install

 ## OAUTH FLOW using AUTH_CODE Grant type: 
 
![oauth_flow_corrected (1)](https://github.com/user-attachments/assets/c610fe6c-a32c-4d3b-b291-d0cf31e16806)
