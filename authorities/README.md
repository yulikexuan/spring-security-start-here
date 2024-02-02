# Simple Security Configuration

## Basic Authorization

### Call with Predefined Credential
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u yul:yul http://localhost:7070/greeting/hello `
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u yul:yul http://localhost:7070/greeting/writing `

- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u joel:joel http://localhost:7070/greeting/hello `
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u joel:joel http://localhost:7070/greeting/writing `

- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u zac:zac http://localhost:7070/greeting/hello `
- ` curl -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' -u zac:zac http://localhost:7070/greeting/writing `