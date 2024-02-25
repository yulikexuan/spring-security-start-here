# Implementing OAuth2 Client

### Implementing authentication with a Common Provider

- Knowing that the Authentication Details are in the Security Context 
  - Inject the Authentication Object as a Method Parameter
  - Get it from the security context anywhere in the app 
    - ` SecurityContextHolder.getContext().getAuthentication() `
  - Use ` pre/post ` annotations as discussed in chapters 11 and 12 