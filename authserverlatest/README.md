# AuthServer README
## Quick Start Guide

## Overview

This project is an **OAuth2 Authorization Server** built with Spring Security. It provides secure authentication and authorization for client applications using the OAuth2 protocol.

## Features

- OAuth2 Authorization Code, Client Credentials, and Password Grant support
- JWT token issuance and validation
- Customizable user authentication
- Secure endpoints for token management

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+

### Running the Server

```bash
mvn clean spring-boot:run
```

The server will start on `http://localhost:8080`.

### Steps to Obtain Authorization Code and Token

1. **Access the Login Page:**

    Open your browser and navigate to:

    ```
    http://localhost:8080/login
    ```

2. **Authenticate:**

    Enter your username and password to log in.

3. **Authorize the Client:**

    Use a tool like [oidcdebugger.com](https://oidcdebugger.com/) to initiate the OAuth2 Authorization Code flow:

    - Set the **Authorization Endpoint** to `http://localhost:8080/oauth2/authorize`
    - Enter your `client_id`, `redirect_uri`, and desired `scope`
    - Click "Send Request" and complete the login if prompted

4. **Retrieve the Authorization Code:**

    After successful authentication, you will be redirected to your `redirect_uri` with a `code` parameter in the URL.

5. **Exchange Authorization Code for Token:**

    Issue a POST request to the token endpoint:

    ```
    POST http://localhost:8080/oauth2/token
    Content-Type: application/x-www-form-urlencoded

    grant_type=authorization_code&code=AUTH_CODE&redirect_uri=REDIRECT_URI&client_id=CLIENT_ID&client_secret=CLIENT_SECRET
    ```

    Replace `AUTH_CODE`, `REDIRECT_URI`, `CLIENT_ID`, and `CLIENT_SECRET` with your actual values.

### JWT Token Details

The issued JWT access token typically contains:

- `sub`: Subject (user identifier)
- `aud`: Audience (intended recipient)
- `iss`: Issuer (authorization server)
- `exp`: Expiration time
- `iat`: Issued at time
- `scope`: Granted scopes
- `authorities` or `roles`: User roles/authorities

Additional custom claims may be included based on your configuration.

### OAuth2 Endpoints

- **Authorization Endpoint:** `/oauth2/authorize`
- **Token Endpoint:** `/oauth2/token`

## Configuration

Configure clients, users, and token settings in `application.properties` or via Java configuration.

## References

- [Spring Authorization Server Docs](https://docs.spring.io/spring-authorization-server/docs/current/reference/html/)
- [OAuth 2.0 Specification](https://oauth.net/2/)

