Ubiq-API
========
Base URL : --

1. Register User
----
- URL : 
    - /user/register
- Header :
    - Content-Type : application/json
- Body Content:
    - nama
    - secret
    - lon
    - lat
    - deviceId
        - Example:
        {
            "nama" : "nama saya",
            "secret" : "tidakadayangtahu",
            "lon" : "131.3",
            "lat" : "6.5",
            "deviceId":"xyz"
        }

2. Upload Base64 Image
----
- URL : 
    - /upload
- Header :
    - Content-Type : application/json
- Body Content:
    - base64
    - ext
    - deviceId
        - Example:
        {
            "base64":"BYEjN0xoNmQQAAAABJRU5ErkJggg=="
            "ext":"png"
            "deviceId":"xyz"
        }