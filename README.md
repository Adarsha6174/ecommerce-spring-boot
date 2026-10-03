# E-Commerce Application - Complete REST API

A full-featured e-commerce REST API built with Spring Boot 4.1, featuring user authentication, product management, shopping cart, orders, and reviews with JWT token-based security.

## Features

### 1. **Authentication & Authorization**
   - User registration and login with email
   - JWT token-based authentication
   - Role-based access control (ADMIN, USER)
   - Secure password encryption using BCrypt

### 2. **User Management**
   - User registration with validation
   - User profile management
   - Role assignment
   - Account enable/disable

### 3. **Product Management**
   - Create, read, update, delete products
   - Product categorization
   - Product search by name/keyword
   - Filter products by category
   - Pagination support
   - Product inventory tracking

### 4. **Category Management**
   - CRUD operations for product categories
   - Category-based product filtering

### 5. **Shopping Cart**
   - Add/remove items from cart
   - Update item quantities
   - View cart with total amount calculation
   - Clear entire cart
   - Cart persistence per user

### 6. **Order Management**
   - Create orders from cart
   - Order status tracking (PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED)
   - View user orders with pagination
   - Cancel orders
   - Inventory management on order creation/cancellation
   - Order history

### 7. **Product Reviews & Ratings**
   - Add reviews with ratings (1-5)
   - View product reviews with pagination
   - View user reviews
   - Update/delete reviews
   - User-based review tracking

### 8. **Inventory Management**
   - Track product availability
   - Reserve quantities for orders
   - Release quantities when orders are cancelled
   - Real-time inventory updates

### 9. **API Documentation**
   - Swagger UI for interactive API documentation
   - OpenAPI 3.0 specification
   - Detailed endpoint descriptions

## Technology Stack

- **Java 27**
- **Spring Boot 4.1.1**
- **Spring Data JPA**
- **Spring Security**
- **JWT (JSON Web Token)**
- **MySQL 8.0**
- **Lombok** (for code generation)
- **OpenAPI 3.0 / Swagger UI**
- **Maven** (build tool)

## Project Structure

```
ecommerce/
├── src/main/java/com/example/ecommerce/
│   ├── config/              # Configuration classes
│   │   ├── SecurityConfig.java
│   │   └── OpenAPIConfig.java
│   ├── controller/          # REST controllers
│   │   ├── AuthController.java
│   │   ├── ProductController.java
│   │   ├── CategoryController.java
│   │   ├── CartController.java
│   │   ├── OrderController.java
│   │   └── ReviewController.java
│   ├── dto/                 # Data Transfer Objects
│   │   ├── LoginRequest.java
│   │   ├── LoginResponse.java
│   │   ├── RegisterRequest.java
│   │   ├── ProductDTO.java
│   │   ├── CartDTO.java
│   │   ├── OrderDTO.java
│   │   └── ReviewDTO.java
│   ├── entity/              # JPA entities
│   │   ├── User.java
│   │   ├── Role.java
│   │   ├── Product.java
│   │   ├── Category.java
│   │   ├── Cart.java
│   │   ├── CartItem.java
│   │   ├── Order.java
│   │   ├── OrderItem.java
│   │   ├── Inventory.java
│   │   ├── Review.java
│   │   └── OrderStatus.java
│   ├── exception/           # Exception handling
│   │   ├── GlobalExceptionHandler.java
│   │   ├── ErrorResponse.java
│   │   ├── ResourceNotFoundException.java
│   │   └── InvalidInputException.java
│   ├── repository/          # Data access layer
│   │   ├── UserRepository.java
│   │   ├── ProductRepository.java
│   │   ├── CategoryRepository.java
│   │   ├── OrderRepository.java
│   │   ├── ReviewRepository.java
│   │   └── CartRepository.java
│   ├── security/            # Security-related classes
│   │   ├── JwtTokenProvider.java
│   │   ├── JwtAuthenticationFilter.java
│   │   └── JwtAuthenticationEntryPoint.java
│   └── service/             # Business logic
│       ├── UserService.java
│       ├── AuthService.java
│       ├── ProductService.java
│       ├── CategoryService.java
│       ├── CartService.java
│       ├── OrderService.java
│       ├── InventoryService.java
│       └── ReviewService.java
└── src/main/resources/
    └── application.properties
```

## Setup Instructions

### Prerequisites
- Java 27 or higher
- MySQL 8.0+
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ecommerce
   ```

2. **Create MySQL Database**
   ```sql
   CREATE DATABASE ecommerce_db;
   ```

3. **Configure Database Connection**
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. **Build the Project**
   ```bash
   mvn clean build
   ```

5. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

   The application will start at `http://localhost:8080`

6. **Access API Documentation**
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - API Docs: `http://localhost:8080/v3/api-docs`

## API Endpoints

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "password123",
  "phone": "1234567890"
}

Response: 201 Created
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phone": "1234567890",
  "enabled": true,
  "roles": [
    {
      "id": 1,
      "name": "ROLE_USER"
    }
  ]
}
```

#### Login User
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}

Response: 200 OK
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "message": "Login successful"
}
```

### Product Endpoints

#### Get All Products
```http
GET /api/products
Authorization: Bearer <token>

Response: 200 OK
[
  {
    "id": 1,
    "name": "Laptop",
    "price": 999.99,
    "description": "High-performance laptop",
    "sku": "LAPTOP001",
    "brand": "Dell",
    "active": true,
    "categoryId": 1,
    "categoryName": "Electronics",
    "availableQuantity": 10
  }
]
```

#### Search Products
```http
GET /api/products/search?keyword=laptop&page=0&size=10
Authorization: Bearer <token>

Response: 200 OK
{
  "content": [...],
  "pageable": {...},
  "totalElements": 5,
  "totalPages": 1
}
```

#### Get Product by Category
```http
GET /api/products/category/1?page=0&size=10
Authorization: Bearer <token>
```

#### Create Product (Admin Only)
```http
POST /api/products
Authorization: Bearer <admin_token>
Content-Type: application/json

{
  "name": "Laptop",
  "price": 999.99,
  "description": "High-performance laptop",
  "sku": "LAPTOP001",
  "brand": "Dell",
  "imageUrl": "https://example.com/laptop.jpg",
  "active": true,
  "category": {
    "id": 1
  }
}

Response: 201 Created
```

#### Update Product (Admin Only)
```http
PUT /api/products/1
Authorization: Bearer <admin_token>
Content-Type: application/json

{
  "name": "Laptop Updated",
  "price": 1099.99,
  ...
}
```

#### Delete Product (Admin Only)
```http
DELETE /api/products/1
Authorization: Bearer <admin_token>

Response: 204 No Content
```

### Category Endpoints

#### Get All Categories
```http
GET /api/categories

Response: 200 OK
[
  {
    "id": 1,
    "name": "Electronics",
    "description": "Electronic devices"
  }
]
```

#### Create Category (Admin Only)
```http
POST /api/categories
Authorization: Bearer <admin_token>
Content-Type: application/json

{
  "name": "Electronics",
  "description": "Electronic devices"
}

Response: 201 Created
```

### Cart Endpoints

#### Get Cart
```http
GET /api/cart
Authorization: Bearer <token>

Response: 200 OK
{
  "id": 1,
  "items": [
    {
      "id": 1,
      "productId": 1,
      "productName": "Laptop",
      "quantity": 2,
      "price": 999.99,
      "subtotal": 1999.98
    }
  ],
  "totalAmount": 1999.98
}
```

#### Add to Cart
```http
POST /api/cart/add?productId=1&quantity=2
Authorization: Bearer <token>

Response: 200 OK
{cart object}
```

#### Update Cart Item
```http
PUT /api/cart/update?productId=1&quantity=3
Authorization: Bearer <token>

Response: 200 OK
{cart object}
```

#### Remove from Cart
```http
DELETE /api/cart/remove/1
Authorization: Bearer <token>

Response: 200 OK
{cart object}
```

#### Clear Cart
```http
DELETE /api/cart/clear
Authorization: Bearer <token>

Response: 200 OK
{cart object}
```

### Order Endpoints

#### Create Order
```http
POST /api/orders?shippingAddress=123%20Main%20St&billingAddress=123%20Main%20St
Authorization: Bearer <token>

Response: 201 Created
{
  "id": 1,
  "orderNumber": "ORD-uuid",
  "status": "PENDING",
  "totalAmount": 1999.98,
  "discountAmount": 0,
  "shippingAddress": "123 Main St",
  "billingAddress": "123 Main St",
  "items": [...]
}
```

#### Get Order
```http
GET /api/orders/1
Authorization: Bearer <token>

Response: 200 OK
{order object}
```

#### Get My Orders
```http
GET /api/orders?page=0&size=10
Authorization: Bearer <token>

Response: 200 OK
{
  "content": [...],
  "totalElements": 5,
  "totalPages": 1
}
```

#### Update Order Status (Admin Only)
```http
PUT /api/orders/1/status?status=SHIPPED
Authorization: Bearer <admin_token>

Response: 200 OK
{order object}
```

#### Cancel Order
```http
DELETE /api/orders/1/cancel
Authorization: Bearer <token>

Response: 204 No Content
```

### Review Endpoints

#### Add Review
```http
POST /api/reviews/product/1
Authorization: Bearer <token>
Content-Type: application/json

{
  "rating": 5,
  "comment": "Great product!"
}

Response: 201 Created
```

#### Get Product Reviews
```http
GET /api/reviews/product/1?page=0&size=10

Response: 200 OK
[
  {
    "id": 1,
    "productId": 1,
    "userId": 1,
    "userName": "John Doe",
    "rating": 5,
    "comment": "Great product!"
  }
]
```

#### Get User Reviews
```http
GET /api/reviews/user/1?page=0&size=10

Response: 200 OK
```

#### Update Review
```http
PUT /api/reviews/1
Authorization: Bearer <token>
Content-Type: application/json

{
  "rating": 4,
  "comment": "Good product"
}

Response: 200 OK
```

#### Delete Review
```http
DELETE /api/reviews/1
Authorization: Bearer <token>

Response: 204 No Content
```

## Authentication

All protected endpoints require a Bearer token in the Authorization header:

```http
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9...
```

To obtain a token, login using the `/api/auth/login` endpoint.

## Database Schema

### Tables
- `users` - User account information
- `roles` - User roles (ADMIN, USER)
- `user_roles` - User-to-role mapping
- `products` - Product catalog
- `categories` - Product categories
- `inventory` - Product inventory tracking
- `carts` - Shopping carts
- `cart_items` - Items in shopping carts
- `orders` - Customer orders
- `order_items` - Items in orders
- `reviews` - Product reviews

## Error Handling

All error responses follow this format:

```json
{
  "status": 400,
  "message": "Invalid input",
  "error": "Bad Request",
  "timestamp": "2024-01-15T10:30:00",
  "path": "/api/products"
}
```

### HTTP Status Codes
- `200` - OK
- `201` - Created
- `204` - No Content
- `400` - Bad Request
- `401` - Unauthorized
- `403` - Forbidden
- `404` - Not Found
- `500` - Internal Server Error

## Security Features

- JWT Token-based authentication
- Password encryption using BCrypt
- Role-based access control
- CORS configuration
- HTTPS ready
- Secured endpoints with @PreAuthorize annotations
- Stateless authentication

## Testing with Postman

1. Import the API endpoints into Postman
2. Set environment variable for token after login
3. Use the token in Authorization header for protected requests
4. Test all CRUD operations

## Running the Application

### Development Mode
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/ecommerce-0.0.1-SNAPSHOT.jar
```

## Database Initialization

The application uses Hibernate's `ddl-auto=update` to automatically create/update tables on startup. Ensure:
1. MySQL is running
2. Database `ecommerce_db` exists
3. Database credentials are correct in `application.properties`

## Future Enhancements

- Payment gateway integration (Stripe, PayPal)
- Email notifications for orders
- Advanced search filters and facets
- Product recommendations
- User wishlist
- Admin dashboard
- Analytics and reporting
- Multi-currency support
- Promotional codes and discounts

## Contributing

1. Create a feature branch
2. Make your changes
3. Submit a pull request

## License

This project is licensed under the MIT License.

## Support

For issues or questions, please create an issue in the repository or contact the development team.

---

**Created**: 2024
**Version**: 1.0.0
**Status**: Active Development
