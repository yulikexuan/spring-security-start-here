# Simple Security Configuration

## Basic Authentication and Authorization

### Call with Predefined Credential
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -H "Authorization:5c3ca56a0ee6c619" http://localhost:7070/greeting/hello `
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" http://localhost:7070/greeting/hello `
