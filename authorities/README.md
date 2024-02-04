# Authorization

### Config
``` 
http.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(c -> c
        .requestMatchers(HttpMethod.POST, "/a")        // yul
        .hasRole("ADMIN") 
        .requestMatchers(HttpMethod.GET, "/a/b")
        .hasRole("MANAGER")                            // joel
        .requestMatchers(HttpMethod.GET, "/a/b/c")
        .hasAnyRole("ADMIN", "MANAGER")
        .requestMatchers(HttpMethod.GET, "/a")         // zac (EMPLOYEE)
        .permitAll()
        .anyRequest()
        .denyAll());
```

### 
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u yul:yul http://localhost:7070/a `             200
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u yul:yul -X POST http://localhost:7070/a `     200
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u yul:yul http://localhost:7070/a/b/c `         200
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u yul:yul -X PUT http://localhost:7070/a `      403

- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u joel:joel http://localhost:7070/a `           200
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u joel:joel -X POST http://localhost:7070/a `   403
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u joel:joel http://localhost:7070/a/b `         200
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u joel:joel http://localhost:7070/a/b/c `       200 
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u joel:joel -X PUT http://localhost:7070/a `    403

- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u zac:zac http://localhost:7070/a `             200
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u zac:zac -X POST http://localhost:7070/a `     403
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u zac:zac http://localhost:7070/a/b `           403
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u zac:zac http://localhost:7070/a/b/c `         403
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u zac:zac -X PUT http://localhost:7070/a `      403

- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u yul:12234567 http://localhost:7070/a `        401