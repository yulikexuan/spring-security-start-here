# Method Level Security

### [Expression-Based Access Control](https://docs.spring.io/spring-security/reference/5.8/servlet/authorization/expression-based.html)

### Before Applying Method Security
- ` curl -w @curlw -u yul:yul http://localhost:7070/hello ` 
    ``` 
    >>> Hello, Fantastico
    Status: 200                           
    Total: 0.004400 s                     
    Content-Type: text/plain;charset=UTF-8
    ```

### After Applying Method Security - with ` @PreAuthorize("hasRole('ADMIN')") `
- ` curl -w @curlw -u yul:yul http://localhost:7070/hello ` 
    ``` 
    >>> Hello, Fantastico
    Status: 200                           
    Total: 0.004400 s                     
    Content-Type: text/plain;charset=UTF-8
    ```
- ` curl -w @curlw -u joel:joel http://localhost:7070/hello `
    ``` 
    >>> Error Name: AccessDeniedException
    >>> Error Message: Access Denied     
    Status: 400                           
    Total: 0.147796 s                     
    Content-Type: text/plain;charset=UTF-8
    ```

### After Applying Method Security with 
   ``` @PreAuthorize("#name == authentication.principal.ussername") ```
- ` curl -w @curlw -u yul:yul http://localhost:7070/secret/names/yul ` 
    ``` 
    ["Energico","Perfecto"]
    Status: 200
    Total: 0.170869 s
    Content-Type: application/json
    ```
- ` curl -w @curlw -u yul:yul http://localhost:7070/secret/names/joel `
    ``` 
    >>> Error Name: AccessDeniedException
    >>> Error Message: Access Denied
    Status: 400
    Total: 0.039606 s
    Content-Type: text/plain;charset=UTF-8
    ```

### After Applying Method Security with 
``` @PostAuthorize("returnObject.roles.contains('MANAGER')") ```
- ` curl -w @curlw -u yul:yul http://localhost:7070/book/details/yul `
    ``` 
    {"name":"Yu Li","books":["Spring Start Here","Spring in Action 6th."],"roles":["ADMIN","MANAGER"]}
    Status: 200                   
    Total: 0.151723 s             
    Content-Type: application/json
    ```
- ` curl -w @curlw -u yul:yul http://localhost:7070/book/details/joel `
    ``` 
    {"name":"Joel P.","books":["Beautiful Paris","Karamazov Brothers"],"roles":["MANAGER","EMPLOYEE"]}
    Status: 200                   
    Total: 0.006671 s             
    Content-Type: application/json
    ```
  
- ` curl -w @curlw -u yul:yul http://localhost:7070/book/details/zac `
    ``` 
    >>> Error Name: AccessDeniedException
    >>> Error Message: Access Denied     
    Status: 400                           
    Total: 0.016419 s                     
    Content-Type: text/plain;charset=UTF-8
    ```