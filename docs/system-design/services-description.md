## Auth-Service
Purpose:
```text
Системный сервис. 
Выполняет функцию аутентификации пользователей.
```
Path: 
```text
/auth
```
Methods:
```text
GET /auth/
Responses:
HTTP 200 - ok
HTTP 401 - unauthorized
Headers: X-User-Id

POST /auth/login
Request:
RequestBody: 
{
  "login": "String",
  "password": "String"
}
Response:
HTTP 200 - ok
HTTP 401 - unauthorized
Headers: X-User-Id
Cookie: auth

POST /auth/register
Request:
RequestBody: 
{
  "login": "String",
  "password": "String"
  "name": "String"
}
Response:
HTTP 200 - ok
HTTP 401 - unauthorized
HTTP 400 - bad request
Headers: X-User-Id
Cookie: auth
```

## User-Service
Purpose:
```text
Сервис управления пользователями:
- добавление пользователя
- проверка наличия пользователя
- редактирование пользователя
```
Path:
```text
/users
```
Methods:
```text
POST /users?login={login}&password={password}
Responses:
HTTP 200 - ok
ResponseBody: 
{
    id: UUID,
    login: "login",
    password: "password",
    name: "name"
}
HTTP 404 - not found
Response body: empty

POST /users
RequestBody 
{
    login: "login",
    password: "password",
    name: "name"
}
Responses:
HTTP 200 - ok
ResponseBody: 
UUID

PUT /users/me
Headers: X-User-Id
RequestBody 
{
    password: "password",
    name: "name"
}
Responses:
HTTP 200 - ok
HTTP 400 - bad request
```

## Advertisement-Service
Purpose:
```text
Сервис управления объявлениями:
- просмотр объявлений
- добавление объявление
- редактирование объявления
```
Path:
```text
/advertisements
```
Methods:
```text
GET /advertisements
Responses:
HTTP 200 - ok
ResponseBody: [{
    id: UUID,
    header: "String"
    price: numeric
}]

GET /advertisements/my
Headers: X-User-Id
Responses:
HTTP 200 - ok
ResponseBody: [{
    id: UUID,
    header: "String"
    price: numeric
}]

POST /advertisements
Headers: X-User-Id
RequestBody: {
    header: "String",
    price: numeric
}
Responses:
HTTP 200 - ok
UUID

PUT /advertisements/{id}
Headers: X-User-Id
RequestBody: {
    header: "String",
    description" "String",
    price: numeric
}
Responses:
HTTP 200 - ok

```

## Order-Service
Purpose:
```text
Сервис управления сделками:
- просмотр сделок
- добавление сделок
- редактирование сделок
```
Path:
```text
/orders
```
Methods:
```text
GET /orders/my
Headers: X-User-Id
Responses:
HTTP 200 - ok
ResponseBody: {
    id: UUID,
    advertisementId: UUID,
    status: ["ACTIVE", "SUCCESS", "CANCELLED"]
}

GET /orders/{id}
Headers: X-User-Id
Responses:
HTTP 200 - ok
ResponseBody: {
    advertisementId: UUID,
    buyerId: UUID,
    chat: [ChatItem: { 
            messageDateTime: DateTime,
            participant: uuid,
            message    
    }],
    status: ["ACTIVE", "SUCCESS", "CANCELLED"]
}

POST /orders
Headers: X-User-Id
RequestBody: {
    advertisementId: UUID,
    message: String,
}
Responses:
HTTP 200 - ok
ResponseBody: UUID
```