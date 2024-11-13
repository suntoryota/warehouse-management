# Warehouse Management System

## Tech Stack
- Spring Boot 3.2.0
- Java 17
- H2 Database
- Maven

## Features
- Admin Management (CRUD, Authentication)
- Product Category Management
- Product Management
- Transaction Management (Stock In/Out)
- Transaction History

### 1. Admin Management
#### Endpoints:
```
POST /api/auth/register
- Register new admin
Request:
{
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "password": "string",
    "birthDate": "YYYY-MM-DD",
    "gender": "MALE/FEMALE"
}

POST /api/auth/login
- Admin login
Request:
{
    "email": "string",
    "password": "string"
}

POST /api/auth/logout
- Admin logout

GET /api/admins
- Get all admins

GET /api/admins/{id}
- Get admin by ID

PUT /api/admins/{id}
- Update admin data
Request:
{
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "password": "string" (optional),
    "birthDate": "YYYY-MM-DD",
    "gender": "MALE/FEMALE"
}

DELETE /api/admins/{id}
- Delete admin
```

### 2. Category Management
#### Endpoints:
```
POST /api/categories
- Create new category
Request:
{
    "name": "string",
    "description": "string"
}

GET /api/categories
- Get all categories

GET /api/categories/{id}
- Get category by ID

PUT /api/categories/{id}
- Update category
Request:
{
    "name": "string",
    "description": "string"
}

DELETE /api/categories/{id}
- Delete category
```

### 3. Product Management
#### Endpoints:
```
POST /api/products
- Create new product
Request:
{
    "name": "string",
    "description": "string",
    "imageUrl": "string",
    "categoryId": "string",
    "stock": number
}

GET /api/products
- Get all products

GET /api/products/{id}
- Get product by ID

PUT /api/products/{id}
- Update product
Request:
{
    "name": "string",
    "description": "string",
    "imageUrl": "string",
    "categoryId": "string",
    "stock": number
}

DELETE /api/products/{id}
- Delete product
```

### 4. Transaction Management
#### Endpoints:
```
POST /api/transactions
- Create new transaction
Request:
{
    "type": "STOCK_IN/STOCK_OUT",
    "details": [
        {
            "productId": "string",
            "quantity": number
        }
    ]
}

GET /api/transactions
- Get all transactions history
```

## Response Format
```json
Success Response:
{
    "code": 200,
    "status": "SUCCESS",
    "message": "Success",
    "data": {}
}

Error Response:
{
    "code": number,
    "status": "ERROR",
    "message": "Error message",
    "data": null
}
```

## Error Codes
- 400: Bad Request
- 401: Unauthorized
- 403: Forbidden
- 404: Not Found
- 500: Internal Server Error

## How to Run
1. Make sure you have Java 17 and Maven installed
2. Configure database in `application.properties`
3. Run: `mvn spring-boot:run`
4. Access API at: `http://localhost:8080`
5. Access H2 Console (if using H2): `http://localhost:8082/h2-console`

## Database Design
```
Admin:
- id (Long)
- firstName (String)
- lastName (String)
- email (String, unique)
- password (String)
- birthDate (Date)
- gender (Enum)

Category:
- id (Long)
- name (String)
- description (String)

Product:
- id (Long)
- name (String)
- description (String)
- imageUrl (String)
- stock (Integer)
- category (Category)

Transaction:
- id (Long)
- type (Enum: STOCK_IN/STOCK_OUT)
- transactionDate (DateTime)

TransactionDetail:
- id (Long)
- transaction (Transaction)
- product (Product)
- quantity (Integer)
```

## Features Implemented
1. Admin Management
    - ✅ CRUD Operations
    - ✅ Login/Logout
    - ✅ Profile Update
2. Category Management
    - ✅ CRUD Operations
3. Product Management
    - ✅ CRUD Operations
    - ✅ Stock Management
4. Transaction Management
    - ✅ Stock In/Out
    - ✅ Multiple Products per Transaction
    - ✅ Stock Validation
5. Transaction History
    - ✅ List All Transactions