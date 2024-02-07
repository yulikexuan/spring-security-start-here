## Customize CSRF

- ` curl -w @curlw -u yul:yul -H "X-IDENTIFIER:971110" http://localhost:7070/hello ` 
    ``` 
    >>> GET - Hello! [TOKEN]: 79be9b26-477e-4e81-a7e3-d9f078517a95
    Status: 200                           
    Total: 0.029624 s                     
    Content-Type: text/plain;charset=UTF-8
    ```

- ` curl -w @curlw -u yul:yul -H "X-IDENTIFIER:971110" -H "X-CSRF-TOKEN:79be9b26-477e-4e81-a7e3-d9f078517a95" -X POST http://localhost:7070/hello ` 
    ``` 
    >>> POST - Hello! [TOKEN]: 79be9b26-477e-4e81-a7e3-d9f078517a95
    Status: 200                           
    Total: 0.040525 s
    Content-Type: text/plain;charset=UTF-8 
    ```
- ` curl -w @curlw -u yul:yul -H "X-IDENTIFIER:971110"  -X POST http://localhost:7070/hello `
    ``` 
    Status: 401
    Total: 0.016392 s
    Content-Type:
    ```
  
- ` curl -w @curlw -u yul:yul -X POST http://localhost:7070/ciao `
    ``` 
    >>> POST - Ciao! [TOKEN]: 4654f3fd-c90d-4ddb-8af5-8a975016ae40
    Status: 200
    Total: 0.013795 s
    Content-Type: text/plain;charset=UTF-8
    ```