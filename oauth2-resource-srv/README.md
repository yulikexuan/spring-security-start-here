# Implementing a Resource Server using JWTs

### The Steps to Test
1. Fetching challenge code and verifier code
   - ` http://localhost:7077/codes/challenge `
     ``` 
     {
         "challenge": "B_8SRzsypSCfTjZOYaJy_jFcyjaMPoD-IEHfQz67CVk",
         "verifier": "BKb_RRJ6LE51uNPGKJJ9WfwoEK2xIkp18lV3zJMuLLM"
     }
     ```
2. Use the Authorization Endpoint to Get an Authorization Code (in Browser)
   - ` http://localhost:7777/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://www.manning.com/authorized&code_challenge=B_8SRzsypSCfTjZOYaJy_jFcyjaMPoD-IEHfQz67CVk&code_challenge_method=S256 `
   - For Chrome, login in with ` yul/yul ` 
   - Find out the Location Header from the HttpResponse
   - The authorization code is here 
     - ` https://www.manning.com/authorized?code=97hwuqz7mGdB3IClnyX4J6kJBIyirYVSifGtRznorRc2Fv4GOGx77IzlBgjM--l4czuUMz4Pn8fTfCTvBDllrL7hNOi1LJrrs287mYIEwgTaviRCzwyIN3d0WgP7Dgya `
3. To Request the New Access Token 
    - The URL: ' http://localhost:7777/oauth2/token '
    - HTTP Method: POST
    - Data Body: x-www-form-urlencoded
    - ` client_id=client `
    - ` redirect_uri=https://www.manning.com/authorized `
    - ` grant_type=authorization_code `
    - ` code=97hwuqz7mGdB3IClnyX4J6kJBIyirYVSifGtRznorRc2Fv4GOGx77IzlBgjM--l4czuUMz4Pn8fTfCTvBDllrL7hNOi1LJrrs287mYIEwgTaviRCzwyIN3d0WgP7Dgya `
    - ` code_verifier=BKb_RRJ6LE51uNPGKJJ9WfwoEK2xIkp18lV3zJMuLLM `
        - _the verifier based on which the challenge that the client sent at authorization was created_
    - client:secret / Y2xpZW50OnNlY3JldA==

    ``` 
    {
        "access_token": "eyJraWQiOiI3Y2E1NGVjZC1lZGI5LTQyNTQtYmUwNy04ODRkOTYwNDUxNzgiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ5dWwiLCJhdWQiOiJjbGllbnQiLCJuYmYiOjE3MDg2OTc2OTMsInNjb3BlIjpbIm9wZW5pZCJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0Ojc3NzciLCJleHAiOjE3MDg3NDA4OTMsImlhdCI6MTcwODY5NzY5MywianRpIjoiYTU3OTQ0ZGMtMGQ4Yi00OGM0LWE5OTctM2U3YWUxMjNmZWU3In0.UaRwHzfmTHhLPERgilv7RUCYqrURRYfCUzJtsDtvBBw0NG6dIJqFepbJRhxbVDP_W5hSpeu5lra7sIqxWY6ucvDXu_YaT103FkHnscGSyQqScpmXxDto1o7YIhWFIM9ZPEMwHLuT2Wi9-d_ZyF2Iz-r4uUVzaUCAl2zpoAv6om_36KvktJaiLny302YClm4V9ZZRlgByKy2-Kpq_O9BuFftIUqZXMrUR7RID74oz8FRKbWH2Lz7e0qIvfPP87KE-3hY6pteADbqRaE2qaCcLrL1eqIdfSTSH2aRtrHRdtOLRXVmaU31J0uOz8CCb2wqzZ4JLWU0I6qXuFmVRjNEMTw",
        "scope": "openid",
        "id_token": "eyJraWQiOiI3Y2E1NGVjZC1lZGI5LTQyNTQtYmUwNy04ODRkOTYwNDUxNzgiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ5dWwiLCJhdWQiOiJjbGllbnQiLCJhenAiOiJjbGllbnQiLCJhdXRoX3RpbWUiOjE3MDg2OTc2MTEsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6Nzc3NyIsImV4cCI6MTcwODY5OTQ5MywiaWF0IjoxNzA4Njk3NjkzLCJqdGkiOiJhYjkwODEwMi0xYzI0LTQ0NTctYjZkZS1iZmE0ZGEwY2Q4NDkiLCJzaWQiOiJYSjROZVNELWppSlRuamc0Y1pQNFRPNDVHU2xyRXRBZTlQSWd6bTdaWENFIn0.P4axVh0rDWd89bV3DDcHHhbPFkJ_pE7DrQiBlXBg9Vt9JUGzumZRMT2-yhoH5UnCcEAoY6VeKHdxvakpfg-UFqq1ix7IKIJCToFv6iSeoitfXREnCl8SGRWiLUOtVTB8Ro6fT-370Nv2G8OSnXTOIvhKYxFWU3JNQGocFJISItmrmsr55AVaKECSPLFlFUdFngYoj91G5D7MF4g6KomHrDHXyuZQ-jw0te7VPAaohQnqPF2BSmD89eUSN8aeWa0GcHBW8zGGt78JY7QS3l1HF3SEfQpDn0vnTNaC3xc6rrFyMhYA7WbWEBVMWZSCZHcybW_-RA_TJOp-nY8QBW3ftA",
        "token_type": "Bearer",
        "expires_in": 43200
    }
    ```
4. Access the Resource Endpoint with JWT
   - ` cd /c/dev/projects/spring-security-start-here `
   - ` curl -v -w @curlw http://localhost:7090/resource/demo -H 'Authorization: Bearer eyJraWQiOiI3Y2E1NGVjZC1lZGI5LTQyNTQtYmUwNy04ODRkOTYwNDUxNzgiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ5dWwiLCJhdWQiOiJjbGllbnQiLCJuYmYiOjE3MDg2OTc2OTMsInNjb3BlIjpbIm9wZW5pZCJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0Ojc3NzciLCJleHAiOjE3MDg3NDA4OTMsImlhdCI6MTcwODY5NzY5MywianRpIjoiYTU3OTQ0ZGMtMGQ4Yi00OGM0LWE5OTctM2U3YWUxMjNmZWU3In0.UaRwHzfmTHhLPERgilv7RUCYqrURRYfCUzJtsDtvBBw0NG6dIJqFepbJRhxbVDP_W5hSpeu5lra7sIqxWY6ucvDXu_YaT103FkHnscGSyQqScpmXxDto1o7YIhWFIM9ZPEMwHLuT2Wi9-d_ZyF2Iz-r4uUVzaUCAl2zpoAv6om_36KvktJaiLny302YClm4V9ZZRlgByKy2-Kpq_O9BuFftIUqZXMrUR7RID74oz8FRKbWH2Lz7e0qIvfPP87KE-3hY6pteADbqRaE2qaCcLrL1eqIdfSTSH2aRtrHRdtOLRXVmaU31J0uOz8CCb2wqzZ4JLWU0I6qXuFmVRjNEMTw' `


### Using Customized JWT Tokens

1. Change the Authorization Server to Add the Custom Claim to the Access Token
   ``` 
   class Ssia2JwtTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
   
       @Override
       public void customize(JwtEncodingContext jwtEncodingContext) {
   
           final OAuth2TokenType tokenType = jwtEncodingContext.getTokenType();
           final JwtClaimsSet.Builder claimsBuilder = jwtEncodingContext.getClaims();
   
           if (ACCESS_TOKEN.equals(tokenType)) {
   
               final var authorities = jwtEncodingContext.getPrincipal()
                       .getAuthorities();
   
               final var roles = AuthorityUtils.authorityListToSet(authorities)
                       .stream()
                       .map(a -> a.replaceFirst("^ROLE_", ""))
                       .collect(ImmutableSet.toImmutableSet());
   
               claimsBuilder.claims(claims -> {
                   claims.put("roles", roles);
                   claims.put("priority", "HIGH");
               });
           }
       }
   }
   ```

2. Change the Resource Server to read the Custom Claim, 
   - and store it in the security context 

3. Implement an Authorization Rule that uses the Custom Claim


### Configuring Token Validation through Introspection

> #### Spring Security only Supports JWTs or Opaque Tokens NOT Both At the Same Time

1. Make Sure that the Authorization Server Recognizes the Resource Server As a Client 
   - The Resource Server Needs Client Credentials Registered on the Authorization Server Side
   ``` 
     // we now have a set of credentials on authorizatoin server that 
     //   our resource server can use to call the introspection endpoint 
     //   that the authorization server exposes
     static List<RegisteredClient> registeredClients(
         @NonNull final TokenSettings tokenSettings) {

        final var registeredClient = ... ;

        final var resourceServer =
             RegisteredClient.withId(RESOURCE_SERVER_REGISTRATION_ID)
                     .clientId(RESOURCE_SERVER_ID)
                     .clientSecret(RESOURCE_SERVER_SECRET)
                     .clientAuthenticationMethod(CLIENT_SECRET_BASIC)
                     .authorizationGrantType(CLIENT_CREDENTIALS)
                     .build();

         return List.of(registeredClient, resourceServer);
     } 
   ```

2. Configure Authentication on the Resource Server Side to Use Introspection
   - The three essential values needed for introspection
     - The introspection URI that the authorization server exposes allows 
       the resource server to validate tokens
     - The resource server client ID allows the resource server to identify 
       itself when calling the introspection endpoint
     - The resource server client secret that the resource server uses together 
       with its client ID to authenticate when sending requests to the 
       introspection endpoint
     ``` 
     server.port=7090
     introspectionUri=http://localhost:8080/oauth2/introspect
     resourceserver.clientID=resource_server
     resourceserver.secret=resource_server_secret
     ```

3. Obtain an Access Token from the Authorization Server

4. Use a Demo Endpoint to Prove that the Configuration Works the Way we Expect 
   - with the Access Token we got in Step 3


### Implementing Multi-Tenant Systems