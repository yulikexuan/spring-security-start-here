# Simple Security Configuration

## Basic Authentication and Authorization

### Call with Predefined Credential
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u yul:password http://localhost:7070/greeting/hello `
