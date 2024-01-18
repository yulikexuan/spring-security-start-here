# Simple Security Configuration

## Basic Authentication and Authorization

### Call without the Authorization Header
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" http://localhost:7070/greeting/hello `

### Call without the Default Credential
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type: application/json" -u user:022edf2f-4347-427f-b0be-2a76321b88bb http://localhost:7070/greeting/hello `

### Encode Credential String in Base64
- ` echo -n user:022edf2f-4347-427f-b0be-2a76321b88bb | base64 `
- Then has response: ` dXNlcjowMjJlZGYyZi00MzQ3LTQyN2YtYjBiZS0yYTc2MzIxYjg4YmI= `

### Use the Base64-Encoded code as the value of the Authorization Header
- ` curl  -v -w '\nStatus: %{http_code}\nTotal: %{time_total} s\n' GET -H "Content-Type:application/json" -H "Authorization: BASIC dXNlcjowMjJlZGYyZi00MzQ3LTQyN2YtYjBiZS0yYTc2MzIxYjg4YmI=" http://localhost:7070/greeting/hello `