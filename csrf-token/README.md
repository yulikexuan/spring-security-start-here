# Authorization

## Ant Fileset Wildcards
- ` * ` The asterisk stands for zero or more characters excluding directory separators 
- ` ** ` Double asterisks are used to represent directory trees. It can match zero or more directories 
- ` ? ` The question mark stands for exactly one character and does not match directory separators 


### Config

``` 
static final String VIDEO_URL_PATTERN = ".*/(us|uk|ca)+/(en|fr).*";

@Bean
RegexRequestMatcher videoRequestMatcher() {
    return new RegexRequestMatcher(VIDEO_URL_PATTERN, HttpMethod.GET.name());
}
    
http.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(c -> c
        .requestMatchers(HttpMethod.GET, "/a/b/**")
        .hasAnyRole("MANAGER", "ADMIN")
        .requestMatchers(HttpMethod.GET, "/a")
        .permitAll()
        .anyRequest()
        .denyAll());
```

### cURL Output Format
- ` --write-out `: ` @curlw ` (in current path) 

### cURL Commands
- ` curl -w @curlw -u yul:yul http://localhost:7070/a `             200
- ` curl -w @curlw -u yul:yul http://localhost:7070/a/b `           200
- ` curl -w @curlw -u yul:yul http://localhost:7070/a/b/c `         200
- ` curl -w @curlw -u yul:yul http://localhost:7070/a/b/c/d `       200
- ` curl -w @curlw -u yul:yul -X POST http://localhost:7070/a `     403
- ` curl -w @curlw -u yul:yul -X PUT http://localhost:7070/a `      403

- ` curl -w @curlw -u joel:joel http://localhost:7070/a `           200
- ` curl -w @curlw -u joel:joel http://localhost:7070/a/b `         200
- ` curl -w @curlw -u joel:joel http://localhost:7070/a/b/c `       200
- ` curl -w @curlw -u joel:joel http://localhost:7070/a/b/c/d `     200
- ` curl -w @curlw -u joel:joel -X POST http://localhost:7070/a `   403
- ` curl -w @curlw -u joel:joel -X PUT http://localhost:7070/a `    403

- ` curl -w @curlw -u zac:zac http://localhost:7070/a `             200
- ` curl -w @curlw -u zac:zac http://localhost:7070/a/b `           403
- ` curl -w @curlw -u zac:zac http://localhost:7070/a/b/c `         403
- ` curl -w @curlw -u zac:zac http://localhost:7070/a/b/c/d `       403
- ` curl -w @curlw -u zac:zac -X POST http://localhost:7070/a `     403
- ` curl -w @curlw -u zac:zac -X PUT http://localhost:7070/a `      403

- ` curl -w @curlw -u yul:123456 http://localhost:7070/a `          401
- ` curl -w @curlw -u joel:123456 http://localhost:7070/a `         401
- ` curl -w @curlw -u zac:123456 http://localhost:7070/a `          401

### cURL Use Path Variable
- ` curl -w @curlw -u yul:yul http://localhost:7070/products/1234 ` 200
- ` curl -w @curlw -u yul:yul http://localhost:7070/products/av14 ` 403

### Make Sure to NOT have Any Space Between 
  - the name of the parameter
  - the colon (:)
  - and the regex 

### Common Expressions used for Path Matching with MVC Matchers
- curl -w @curlw -u yul:yul http://localhost:7070/video/ca/fr     200
- curl -w @curlw -u yul:yul http://localhost:7070/video/ca/en     200
- curl -w @curlw -u yul:yul http://localhost:7070/video/uk/en     200
- curl -w @curlw -u yul:yul http://localhost:7070/video/us/fr     200
- curl -w @curlw -u yul:yul http://localhost:7070/video/us/cn     403
- curl -w @curlw -u yul:yul http://localhost:7070/video/tw/cn     403
- curl -w @curlw -u joel:joel http://localhost:7070/video/ca/fr   403
- curl -w @curlw -u zac:zac http://localhost:7070/video/ca/en     403
- curl -w @'curlw' -u joel:joel http://localhost:7070/email/yu.li@tecsys.com 200
- curl -w @'curlw' -u yul:yul http://localhost:7070/email/yu.li@tecsys.com 403
- curl -w @'curlw' -u zac:zac http://localhost:7070/email/yu.li@tecsys.com 403




