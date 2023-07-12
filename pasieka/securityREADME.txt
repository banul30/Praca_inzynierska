W PasiekaApplication są tworzeni przykładowi użytkownicy z rolami (w WebSecurityConfig widać jakie zapytania mogą wysyłać poszczególni użytkownicy)

Wysłanie zapytania (Postman)
1. POST na http://localhost:8080/api/tests/sec/login
W body zapytania wybierasz x-www-form-urlencoded
podajesz dwa parametry key value :  username  (mail wybranego użytkownika)  i password (hasło pass)

2. Zwrotka będzie 404
Wchodzisz w headery odpowiedzi i znajdujesz
access_token i refresh_token

3. Wysyłasz wybrane zapytanie
Dodając w headers klucz Authorization z value w formacie 'Bearer xxx' gdzie xxx to twój access_token



Odświeżenie tokenu (Postman)
tak jak pkt.3  GET http://localhost:8080/api/tests/sec/token/refresh
I w headers zapytania dodajesz klucz Authorization z value w formacie 'Bearer xxx' gdzie xxx to twój refresh_token
