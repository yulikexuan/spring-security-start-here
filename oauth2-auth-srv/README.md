# Implementing a Basic Authentication using JWTs

### The Main Components to Set Up for Authorization Server 
- The configuration filter for protocol endpoints
- The authentication configuration filter
- The user details management components
- The client details management
- The key-pairs
- The general app settings

### Running the Authorization Code Grant Type
1. Check the endpoints that the authorization server exposes 
    - Call the OpenID configuration endpoint to discover the endpoints the 
      authorization server exposes 
      - ` curl -w @curlw http://localhost:7777/.well-known/openid-configuration `
    - "authorization_endpoint": "http://localhost:7777/oauth2/authorize"
      - A client will redirect the user to here
    - "token_endpoint": "http://localhost:7777/oauth2/token"
      - The token endpoint the client will call to request an access token 
    - "jwks_uri": "http://localhost:7777/oauth2/jwks"
      - The key set endpoint a resource server will call to get the public keys 
        it can use to validate tokens 
    - "introspection_endpoint": "http://localhost:7777/oauth2/introspect"

2. Start oauth2-challenge-verifier app 
    - URL: ` curl -w @curlw -u yul:yul http://localhost:7077/codes/challenge `
      ``` 
      {
          "challenge":"WOxPOzqnw7S_RavQatVOZoEpZ2FqjyPVM0IIzv5kgWw",
          "verifier":"sNIAlGC3P-3zriudYOu_sU_T78hR_AJdCNf1EmXgcO4"
      }
      ```

3. Use the Authorization Endpoint to Get an Authorization Code 
   i. To Act like a Client 
     - This client authenticates the user  
       and sends Request to the Authorization Server with this URL: 
       ``` 
       http://localhost:7777/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://www.manning.com/authorized&code_challenge=WOxPOzqnw7S_RavQatVOZoEpZ2FqjyPVM0IIzv5kgWw&code_challenge_method=S256 
       ```
   ii. On the Login Page, enter username and password, then login 
     - Response Header "Location" is
       ``` 
       https://www.manning.com/authorized?code=4FGhRJlJRs9f_Abhu4UCjlGYhRht5V73HxE6Zq5-Aag-WOZ8UEpx8dsplIkdIr3Rgzr7HQT3TULmYwJmkjLZQCiyozIFeSQGR38OgifjqhiXZwUK0N9aV1NGJb-aXour
       ```

4. Once the client has the authorization code, it can request an access token 
   - The URL: ' http://localhost:7777/oauth2/token '
   - HTTP Method: POST
   - Data Body: x-www-form-urlencoded
   - ` client_id=client `
   - ` redirect_uri=https://www.manning.com/authorized `
   - ` grant_type=authorization_code `
   - ` code=CMUBouHi9xCFCogABtcqJtg7he3PHeM8eceK64qlC3iIA8wKGBle4SWqVvpdWcaJ6vJdXmaSUZeLrHgnOt2NNlD7l5ELwCeThZ1HvkwuJc19Wo48jilywDteE2XUj13K `
   - ` code_verifier=sNIAlGC3P-3zriudYOu_sU_T78hR_AJdCNf1EmXgcO4 `
     - _the verifier based on which the challenge that the client sent at authorization was created_
   - client:secret / Y2xpZW50OnNlY3JldA==

   ``` 
   {
    "access_token": "eyJraWQiOiIyNGQwZDRkYS0xYWMzLTRjMDItOTU4Yy05Y2YwZmY3N2QyOTMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ5dWwiLCJhdWQiOiJjbGllbnQiLCJuYmYiOjE3MDg1MzIwODMsInNjb3BlIjpbIm9wZW5pZCJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0Ojc3NzciLCJleHAiOjE3MDg1MzIzODMsImlhdCI6MTcwODUzMjA4MywianRpIjoiNjU5YTRlZTctZjJmYy00NDY2LTk4MTgtNjA4YWRjNzQwNmVkIn0.gyvnPlgpuI6U7fzF4Zg-nYvajFxGiVZHgSgTA6tkzQlCOI0aXOge1JDahxNsRQ_BmOHU_HIU1RfhT_-BxLGoVeATAj5Xa2BJfWoS30e5F2jH9suVNYskJbv0K-JK2tVCnJB1ZhNoT7KOHMTOhcpjUgHaMz-RpWN48llH7a5aoCyVJNjuP7QH7RLyBy9tlYpZZNaT028IvhBKcEgVaWU5wftvuJtWi6WxXvKkFpfrxuXs4RKFQhmGLHEtTlmQuHFKOsgmw4ac8xOZs_VSc_azudY07z_Y9S6r2lg70FQz2q79uT5qXLe54VnxJnjx_QkUNuV2ma86UU7gfsfwDbUmyQ",
    "scope": "openid",
    "id_token": "eyJraWQiOiIyNGQwZDRkYS0xYWMzLTRjMDItOTU4Yy05Y2YwZmY3N2QyOTMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ5dWwiLCJhdWQiOiJjbGllbnQiLCJhenAiOiJjbGllbnQiLCJhdXRoX3RpbWUiOjE3MDg1MzIwMDgsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6Nzc3NyIsImV4cCI6MTcwODUzMzg4MywiaWF0IjoxNzA4NTMyMDgzLCJqdGkiOiI4ZWNmZTI4NC1iNThkLTQwYWUtYTMyZS00ZDg3N2E0MDM4N2EiLCJzaWQiOiIteS13SVgwS3AwS3lyUGgteXlqVTRqeVVHVmJpdGxIQ1RDcXBtVTMwZXFvIn0.i9HTuVT1cUpuruk0Pnh7ZS1_-NGa9ZjvLJem1CXUSIfYjAW5ZW8BSzKtLBVd-mi7iDsgt3TIEdE0FY3sJ1menrXcbs6d_lwtSpGWwjBECB64AilywK5D-mfMYNn5DxSfA2vgkT8ZFeyIeN7SuoavgVVI0tmQd1VQhyL0sbKeoWe4em_61mGx1hWz7NMZe7O9uua5WHZisfuttgXNGunPHiL-aQnArJbGZ4y_6lNDv3Oi8cJ9PTULwlpzjhDP8OIfFuMmH_i2_UXcfEHmm8JB6KhEs9v73XeRB_-6uEiX4Wp6JAFg8wcUeKkuoe5ObAFmlLiiQDbmZ9_YOfToCoC-yg",
    "token_type": "Bearer",
    "expires_in": 299
   }
   ```

### Running the Client Credentials Grant Type
   - The URL: ' http://localhost:7777/oauth2/token '
   - HTTP Method: POST
   - Data Body: x-www-form-urlencoded
   - ` grant_type=client_credentials `
   - ` scope=openid `
   - client:secret / Y2xpZW50OnNlY3JldA==

   ``` 
   {
       "access_token": "eyJraWQiOiI3MDFmNTJjNS0zMThiLTQ4OGQtOGU4MS1mMDFhOTY5ZmRlYWUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJjbGllbnQiLCJhdWQiOiJjbGllbnQiLCJuYmYiOjE3MDg2MDY5OTgsInNjb3BlIjpbIm9wZW5pZCJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0Ojc3NzciLCJleHAiOjE3MDg2MDcyOTgsImlhdCI6MTcwODYwNjk5OCwianRpIjoiY2Q2YjdiMGItMTMxOC00N2VjLWEzYjAtNTllN2RiMmIxZDkzIn0.ZhJ7AuzACOpz6pNUFDUIwNUFPQlbCujQA5adGCmDGjcyiJuco5VTy9KXErJkSqKP25tB55bNlFX1kZcFKze3gzd1yN1n-j9-MHemTKKcjK89u5dUxlrCikZJvp3Fl8Z9NohP6JD_duJP68ODCaAxo3OYCOynvRn7Cio2gJ1y0HlYAKOQC4dpHcEiMIw69W4MY5nxcfX3VThBFUKVgKFZnvPL67kNNszb9EFdjFUqiH8oZAeaoiEe0coe8RWzKFpCwCKpPD31acMcnL8H2ItXwSRgnpKBIa-vmD_4Fd0QLvp2YFxWn2_E9hyV56x47efkNwTfRa10aoQqZKaHVkI3KQ",
        "scope": "openid",
        "token_type": "Bearer",
        "expires_in": 299
   }
   ```

### Using Opaque Tokens & Introspection
1. Applying Token Settings of REFERENCE token format 
   - The URL: ' http://localhost:7777/oauth2/token '
   - HTTP Method: POST
   - Data Body: x-www-form-urlencoded
   - ` grant_type=client_credentials `
   - ` scope=openid `
   - client:secret / Y2xpZW50OnNlY3JldA==

   ``` 
   {
       "access_token": "YTpXmpUkgDIg27S2B1jd400qljtHI1jAGwrtWsU1BdHbpxUl9YNhn2SnUjomSVBZlgw3tbkwH7HpTQB2GN8tw23zxtR6nlU9gVn8NgyV_4NP38GtBvm2M2XeTfCX9AHX",
       "scope": "openid",
       "token_type": "Bearer",
       "expires_in": 299
   }
   ```
2. Introspection
   - The URL: ' http://localhost:7777/oauth2/introspect '
   - HTTP Method: POST
   - Data Body: x-www-form-urlencoded
   - ` token=YTpXmpUkgDIg27S2B1jd400qljtHI1jAGwrtWsU1BdHbpxUl9YNhn2SnUjomSVBZlgw3tbkwH7HpTQB2GN8tw23zxtR6nlU9gVn8NgyV_4NP38GtBvm2M2XeTfCX9AHX `
   - client:secret / Y2xpZW50OnNlY3JldA==

   ``` 
   {
    "active": true,
    "sub": "client",
    "aud": [ "client" ],
    "nbf": 1708609278,
    "scope": "openid",
    "iss": "http://localhost:7777",
    "exp": 1708609578,
    "iat": 1708609278,
    "jti": "63cedd61-9611-4e08-932b-89cd9e3ed7ce",
    "client_id": "client",
    "token_type": "Bearer"
   }
   ```

### Revoking Tokens
- The URL: ' http://localhost:7777/oauth2/revoke '
- HTTP Method: POST
- Data Body: x-www-form-urlencoded
- ` token=YTpXmpUkgDIg27S2B1jd400qljtHI1jAGwrtWsU1BdHbpxUl9YNhn2SnUjomSVBZlgw3tbkwH7HpTQB2GN8tw23zxtR6nlU9gVn8NgyV_4NP38GtBvm2M2XeTfCX9AHX `
- client:secret / Y2xpZW50OnNlY3JldA==
