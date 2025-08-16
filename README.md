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
 
<svg width="1000" height="700" xmlns="http://www.w3.org/2000/svg">
  <!-- Background -->
  <rect width="1000" height="700" fill="#f8f9fa"/>
  
  <!-- Actors -->
  <rect x="50" y="50" width="120" height="60" fill="#e3f2fd" stroke="#1976d2" stroke-width="2" rx="5"/>
  <text x="110" y="85" text-anchor="middle" font-family="Arial, sans-serif" font-size="14" font-weight="bold">USER</text>
  
  <rect x="250" y="50" width="120" height="60" fill="#e8f5e9" stroke="#388e3c" stroke-width="2" rx="5"/>
  <text x="310" y="85" text-anchor="middle" font-family="Arial, sans-serif" font-size="14" font-weight="bold">CLIENT APP</text>
  
  <rect x="450" y="50" width="120" height="60" fill="#fff3e0" stroke="#f57c00" stroke-width="2" rx="5"/>
  <text x="510" y="85" text-anchor="middle" font-family="Arial, sans-serif" font-size="14" font-weight="bold">AUTH SERVER</text>
  
  <rect x="650" y="50" width="120" height="60" fill="#fce4ec" stroke="#c2185b" stroke-width="2" rx="5"/>
  <text x="710" y="85" text-anchor="middle" font-family="Arial, sans-serif" font-size="14" font-weight="bold">RESOURCE SERVER</text>
  
  <!-- Vertical lifelines -->
  <line x1="110" y1="110" x2="110" y2="650" stroke="#666" stroke-width="2" stroke-dasharray="5,5"/>
  <line x1="310" y1="110" x2="310" y2="650" stroke="#666" stroke-width="2" stroke-dasharray="5,5"/>
  <line x1="510" y1="110" x2="510" y2="650" stroke="#666" stroke-width="2" stroke-dasharray="5,5"/>
  <line x1="710" y1="110" x2="710" y2="650" stroke="#666" stroke-width="2" stroke-dasharray="5,5"/>
  
  <!-- Step 1: User clicks login -->
  <line x1="110" y1="140" x2="310" y2="140" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="210" y="135" text-anchor="middle" font-family="Arial, sans-serif" font-size="12" fill="#333">1. Clicks "Login"</text>
  
  <!-- Step 2: Redirect to authorization URL -->
  <line x1="310" y1="170" x2="110" y2="170" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="210" y="165" text-anchor="middle" font-family="Arial, sans-serif" font-size="11" fill="#333">2. Redirect to authorization URL</text>
  <text x="210" y="185" text-anchor="middle" font-family="Arial, sans-serif" font-size="10" fill="#666">/oauth/authorize?response_type=code&client_id=...&state=...</text>
  
  <!-- Step 3: User redirected to auth server -->
  <line x1="110" y1="210" x2="510" y2="210" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="310" y="205" text-anchor="middle" font-family="Arial, sans-serif" font-size="12" fill="#333">3. GET /oauth/authorize</text>
  
  <!-- Step 4: Auth server shows login/consent page -->
  <line x1="510" y1="240" x2="110" y2="240" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="310" y="235" text-anchor="middle" font-family="Arial, sans-serif" font-size="12" fill="#333">4. Login/Consent page</text>
  
  <!-- Step 5: User provides credentials and consent -->
  <line x1="110" y1="270" x2="510" y2="270" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="310" y="265" text-anchor="middle" font-family="Arial, sans-serif" font-size="12" fill="#333">5. POST credentials & consent</text>
  
  <!-- Step 6: Auth server redirects with authorization code -->
  <line x1="510" y1="300" x2="110" y2="300" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="310" y="295" text-anchor="middle" font-family="Arial, sans-serif" font-size="11" fill="#333">6. Redirect with auth code</text>
  <text x="310" y="315" text-anchor="middle" font-family="Arial, sans-serif" font-size="10" fill="#666">redirect_uri?code=AUTH_CODE&state=...</text>
  
  <!-- Step 7: Browser redirects to client with code -->
  <line x1="110" y1="340" x2="310" y2="340" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="210" y="335" text-anchor="middle" font-family="Arial, sans-serif" font-size="12" fill="#333">7. GET callback with code</text>
  
  <!-- Step 8: Client exchanges code for tokens -->
  <line x1="310" y1="370" x2="510" y2="370" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="410" y="365" text-anchor="middle" font-family="Arial, sans-serif" font-size="11" fill="#333">8. POST /oauth/token</text>
  <text x="410" y="385" text-anchor="middle" font-family="Arial, sans-serif" font-size="10" fill="#666">grant_type=authorization_code, code=..., client_id, client_secret</text>
  
  <!-- Step 9: Auth server returns tokens -->
  <line x1="510" y1="410" x2="310" y2="410" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="410" y="405" text-anchor="middle" font-family="Arial, sans-serif" font-size="11" fill="#333">9. Return access & refresh tokens</text>
  <text x="410" y="425" text-anchor="middle" font-family="Arial, sans-serif" font-size="10" fill="#666">{"access_token": "...", "refresh_token": "...", "token_type": "Bearer"}</text>
  
  <!-- Step 10: Client makes API call to resource server -->
  <line x1="310" y1="460" x2="710" y2="460" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="510" y="455" text-anchor="middle" font-family="Arial, sans-serif" font-size="11" fill="#333">10. API call with Bearer token</text>
  <text x="510" y="475" text-anchor="middle" font-family="Arial, sans-serif" font-size="10" fill="#666">GET /api/resource Authorization: Bearer ACCESS_TOKEN</text>
  
  <!-- Step 11: Resource server returns data -->
  <line x1="710" y1="500" x2="310" y2="500" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="510" y="495" text-anchor="middle" font-family="Arial, sans-serif" font-size="12" fill="#333">11. Return protected resource</text>
  <text x="510" y="515" text-anchor="middle" font-family="Arial, sans-serif" font-size="10" fill="#666">200 OK + JSON response</text>
  
  <!-- Step 12: Client shows result to user -->
  <line x1="310" y1="540" x2="110" y2="540" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
  <text x="210" y="535" text-anchor="middle" font-family="Arial, sans-serif" font-size="12" fill="#333">12. Display result</text>
  
  <!-- Security notes -->
  <rect x="50" y="580" width="900" height="80" fill="#f1f8e9" stroke="#689f38" stroke-width="1" rx="5"/>
  <text x="70" y="600" font-family="Arial, sans-serif" font-size="12" font-weight="bold" fill="#2e7d32">Security Best Practices:</text>
  <text x="70" y="620" font-family="Arial, sans-serif" font-size="11" fill="#2e7d32">• Use HTTPS for all communications</text>
  <text x="70" y="635" font-family="Arial, sans-serif" font-size="11" fill="#2e7d32">• Include state parameter for CSRF protection</text>
  <text x="70" y="650" font-family="Arial, sans-serif" font-size="11" fill="#2e7d32">• Store client_secret securely on server-side only</text>
  
  <text x="450" y="620" font-family="Arial, sans-serif" font-size="11" fill="#2e7d32">• Validate redirect_uri against registered values</text>
  <text x="450" y="635" font-family="Arial, sans-serif" font-size="11" fill="#2e7d32">• Use short-lived access tokens and long-lived refresh tokens</text>
  <text x="450" y="650" font-family="Arial, sans-serif" font-size="11" fill="#2e7d32">• Implement proper token storage and rotation</text>
  
  <!-- Arrow marker definition -->
  <defs>
    <marker id="arrowhead" markerWidth="10" markerHeight="7" refX="9" refY="3.5" orient="auto">
      <polygon points="0 0, 10 3.5, 0 7" fill="#333"/>
    </marker>
  </defs>
</svg>
 
