# Repository Level Security

### [Expression-Based Access Control](https://docs.spring.io/spring-security/reference/5.8/servlet/authorization/expression-based.html)


### ` @Query("SELECT p FROM Product p WHERE p.name LIKE %:text% AND p.owner=?#{authentication.principal.username}") `
```
$ curl -w @curlw -u yul:yul http://localhost:7070/products/milk                                                
[{"id":6,"name":"oak milk","owner":"yul"}]
Status: 200
Total: 0.272591 s
Content-Type: application/json

$ curl -w @curlw -u joel:joel http://localhost:7070/products/milk
[{"id":2,"name":"oak milk","owner":"joel"}]
Status: 200
Total: 0.010380 s
Content-Type: application/json

$ curl -w @curlw -u zac:zac http://localhost:7070/products/milk
[]
Status: 200
Total: 0.008648 s
Content-Type: application/json
```