# Method Level Security

### [Expression-Based Access Control](https://docs.spring.io/spring-security/reference/5.8/servlet/authorization/expression-based.html)


### ` @PreFilter("filterObject.owner == authentication.name") `
- ` curl -w @curlw -u yul:yul http://localhost:7070/sell `
    ``` 
    [{"name":"Coffee Bean","owner":"yul"}]
    Status: 200
    Total: 0.208167 s
    Content-Type: application/json
    ```
- ` curl -w @curlw -u joel:joel http://localhost:7070/sell `
    ``` 
    [{"name":"Candy","owner":"joel"}]
    Status: 200
    Total: 0.017629 s
    Content-Type: application/json
    ```
- ` curl -w @curlw -u zac:zac http://localhost:7070/sell `
    ``` 
    [{"name":"Book","owner":"zac"}]
    Status: 200
    Total: 0.534984 s
    Content-Type: application/json
    ```

### ` @PostFilter("filterObject.owner == authentication.principal.username") `
- ` curl -w @curlw -u yul:yul http://localhost:7070/products `
    ``` 
    [{"name":"Coffee Bean","owner":"yul"}]
    Status: 200
    Total: 0.208167 s
    Content-Type: application/json
    ```
- ` curl -w @curlw -u joel:joel http://localhost:7070/products `
    ``` 
    [{"name":"Candy","owner":"joel"}]
    Status: 200
    Total: 0.017629 s
    Content-Type: application/json
    ```
- ` curl -w @curlw -u zac:zac http://localhost:7070/products `
    ``` 
    [{"name":"Book","owner":"zac"}]
    Status: 200
    Total: 0.534984 s
    Content-Type: application/json
    ```