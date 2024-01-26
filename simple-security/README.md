# Simple Security Configuration

## Basic Authentication and Authorization

### Call with Predefined Credential
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u yul:yul http://localhost:7070/greeting/hello `
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u joel:joel http://localhost:7070/greeting/hello `
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u berry:berry http://localhost:7070/greeting/hello `
