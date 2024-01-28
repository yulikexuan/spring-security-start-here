# Simple Security Configuration

## Basic Authentication and Authorization

### Call with Predefined Credential
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u yul:yul http://localhost:7070/greeting/hello `
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u yul:yul http://localhost:7070/greeting/bonjour `
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u joel:joel http://localhost:7070/greeting/hello `
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u joel:joel http://localhost:7070/greeting/bonjour `
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u berry:berry http://localhost:7070/greeting/hello `

### Call ` @Async ` Endpoint - USE ` SecurityContextHolder.MODE_INHERITABLETHREADLOCAL `
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u tecuser:tecuser http://localhost:7070/greeting/bye `

### Call Endpoint running self managed thread
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u tecsys:tecsys http://localhost:7070/greeting/ciao `

### Concurrency Support of Spring Security 6 
- Running on `SecurityContextHolder.MODE_INHERITABLETHREADLOCAL` 
- ` DelegatingSecurityContextCallable ` & ` DelegatingSecurityContextCallable<V>` not Needed