# Resource Server (Spring Security) - CouponAPI Integration

This project is a Spring Security resource server that integrates with the CouponAPI. It demonstrates secure API access and JWT-based authentication.

## Features

- OAuth2/JWT authentication
- Secure endpoints for coupon management
- Integration with external CouponAPI

## Prerequisites

- Java 17+
- Maven
- CouponAPI running and accessible

## Setup

1. **Clone the repository**
    ```bash
    git clone <repo-url>
    cd resourceserverlatest
    ```

2. **Configure application properties**

    Update `src/main/resources/application.prperties` with:
    - CouponAPI base URL
    - OAuth2/JWT settings

3. **Build and run**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## API Endpoints
| Method | Endpoint                       | Description                        | Required Role      |
|--------|------------------------------- |------------------------------------|--------------------|
| GET    | `/couponapi/coupons?code={code}` | Get coupon by code                 | `USER`, `ADMIN`    |
| POST   | `/couponapi/coupons`             | Create coupon by code (in payload) | `ADMIN`            |


All endpoints require a valid JWT token.

## Example Usage

```bash
curl -H "Authorization: Bearer <token>" http://localhost:8090/couponapi/coupons?code={code}
