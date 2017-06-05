
### Java Spring REST API with OAuth2.

## Technological stack
- Java 8
- Postgres 9.6
- Hibernate 5.2
- Spring MVC, Boot, Security 4.3
- Spring OAuth 2
- Maven 3

## Deploy and configuration
- Create database in Postgres and restore from dump `shopDump.sql`
- Edit `application.properties` in resources accordingly to your database settings
- Run application as Spring Boot app in IDE
- Alternatively you can build a jar using `mvn clean install spring-boot:repackage` 
 and run it by `java -jar shopAPI.jar`

## Authentication, authorization and credentials
 - In database dump there is already one admin user: `admin:abc123`
 - For client's authorization use default HTTP authorization header `Authorization: Basic dHJ1c3RlZDphYmMxMjM=
 or trusted:secret login/password combination` 
 - For simplicity OAuth default tables located in the same schema
 - You can edit access and refresh token validity in `oauth_client_details` table
 - To access REST API secured actions you have to provide access token in request params e.g.
 ``access_token=c15de2f5-d7ec-4948-82ac-07ba2d1fb27d``
 But without Authorization header.

## REST actions

### Authentication

- Obtain access token
``POST oauth/token?grant_type=password&username=admin&password=abc123``
You must provide client's authorization headers
- Refresh access token
``POST oauth/token?grant_type=refresh_token&refresh_token=<REFRESH_TOKEN>``
You must provide client's authorization headers

### Cart Controller Actions

- addProductToCart - public
``POST /api/shop/cart/{cartId}/add/{productId}``
- createCart - public 
``POST /api/shop/cart/customer/{customerId}``

### Order Controller Actions

- createOrder - public
``POST /api/shop/customer/{id}/order``
- listAllOrders - admin
``GET /api/shop/orders``

### Customer Controller Actions

- createCustomer - admin
``POST /api/shop/customers/{name}``

### Product Controller Actions

- listAllProducts - public
``GET /api/shop/products``
- getProduct - admin
``GET /api/shop/products/{id}``
- createProduct - admin
``POST /api/shop/products``
- updateProduct - admin
``PUT /api/shop/products/{id}``
- deleteProduct - admin
``DELETE /api/shop/products/{id}``

 