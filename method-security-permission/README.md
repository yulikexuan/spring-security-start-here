# Method Level Security

### [Expression-Based Access Control](https://docs.spring.io/spring-security/reference/5.8/servlet/authorization/expression-based.html)


### Applying Method Security Permission
- ` curl -w @curlw -u yul:yul http://localhost:7070/documents/abc123 `
    ``` 
    {"owner":"zac"}
    Status: 200
    Total: 0.020463 s
    Content-Type: application/json
    ```
- ` curl -w @curlw -u joel:joel http://localhost:7070/documents/qwe123 `
    ``` 
    {"owner":"joel"}}
    Status: 200
    Total: 0.006582 s
    Content-Type: application/json
    ```
- ` curl -w @curlw -u zac:zac http://localhost:7070/documents/asd555 `
    ``` 
    {"owner":"zac"}}
    Status: 200
    Total: 0.007064 s
    Content-Type: application/json
    ```
- ` curl -w @curlw -u yul:yul http://localhost:7070/documents/asd555 `
    ```
    {"owner":"zac"}}
    Status: 200
    Total: 0.007064 s
    Content-Type: application/json
    ```
- ` curl -w @curlw -u zac:zac http://localhost:7070/documents/qwe123 `
    ```
    >>> Error Name: AccessDeniedException
    >>> Error Message: Access Denied
    Status: 400
    Total: 0.006062 s
    Content-Type: text/plain;charset=UTF-8
    ```