# E-Commerce Application - Complete Setup & Testing Guide

## Quick Start Guide

### Prerequisites
Before starting, ensure you have the following installed:
- **Java 27** or higher (Download from oracle.com)
- **MySQL 8.0** or higher (Download from mysql.com)
- **Maven 3.6** or higher (Download from maven.apache.org)
- **Git** (for cloning repository)
- **Postman** (for API testing - optional but recommended)

### Step 1: Database Setup

1. **Start MySQL Server**
   ```bash
   # On Windows
   net start MySQL80
   
   # On macOS
   brew services start mysql
   
   # On Linux
   sudo systemctl start mysql
   ```

2. **Create Database**
   ```bash
   mysql -u root -p
   ```
   
   Enter your MySQL password when prompted, then run:
   ```sql
   CREATE DATABASE ecommerce_db;
   SHOW DATABASES;
   ```

3. **Exit MySQL**
   ```sql
   EXIT;
   ```

### Step 2: Configure Database Connection

1. Open `src/main/resources/application.properties`

2. Update database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
   spring.datasource.username=root
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```

### Step 3: Build the Application

1. **Navigate to project directory**
   ```bash
   cd ecommerce
   ```

2. **Clean and build**
   ```bash
   mvn clean install
   ```

3. **Or just build (skip tests)**
   ```bash
   mvn clean package -DskipTests
   ```

### Step 4: Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

You should see output like:
```
2024-01-15 10:30:00.000  INFO 12345 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http)
2024-01-15 10:30:00.000  INFO 12345 --- [main] c.e.e.EcommerceApplication              : Started EcommerceApplication in 5.123 seconds
```

### Step 5: Initialize Sample Data (Optional)

1. Open MySQL client:
   ```bash
   mysql -u root -p ecommerce_db
   ```

2. Run the SQL initialization script:
   ```bash
   SOURCE src/main/resources/init-data.sql;
   ```

3. Test users created:
   - Email: `admin@ecommerce.com` | Password: `password123` | Role: ADMIN
   - Email: `john@example.com` | Password: `password123` | Role: USER
   - Email: `jane@example.com` | Password: `password123` | Role: USER

## Testing the Application

### Method 1: Using Swagger UI

1. Open browser: `http://localhost:8080/swagger-ui.html`
2. You'll see all available endpoints with descriptions
3. Click "Try it out" on any endpoint to test

### Method 2: Using Postman

1. **Import Collection**
   - Open Postman
   - Click "Import"
   - Select `Ecommerce-API-Collection.postman_collection.json`

2. **Set Environment Variables**
   - Set `base_url` to `http://localhost:8080`
   - You'll need to update `token` and `admin_token` after login

3. **Test Authentication**
   - Go to Authentication → Login User
   - Click Send
   - Copy the token from response
   - Paste it in the `token` variable

### Method 3: Using cURL

**Login**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

**Get Products**
```bash
curl -X GET http://localhost:8080/api/products \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Complete Testing Workflow

### 1. User Registration
```bash
POST /api/auth/register
{
  "firstName": "Test",
  "lastName": "User",
  "email": "test@example.com",
  "password": "testpass123",
  "phone": "9876543210"
}
```
Response: 201 Created with user object

### 2. User Login
```bash
POST /api/auth/login
{
  "email": "test@example.com",
  "password": "testpass123"
}
```
Response: 200 OK with JWT token

### 3. Browse Products
```bash
GET /api/products
Authorization: Bearer {JWT_TOKEN}
```

### 4. Search Products
```bash
GET /api/products/search?keyword=laptop&page=0&size=10
Authorization: Bearer {JWT_TOKEN}
```

### 5. Add to Cart
```bash
POST /api/cart/add?productId=1&quantity=2
Authorization: Bearer {JWT_TOKEN}
```

### 6. View Cart
```bash
GET /api/cart
Authorization: Bearer {JWT_TOKEN}
```

### 7. Create Order
```bash
POST /api/orders?shippingAddress=123%20Main%20St&billingAddress=123%20Main%20St
Authorization: Bearer {JWT_TOKEN}
```

### 8. Add Review
```bash
POST /api/reviews/product/1
Authorization: Bearer {JWT_TOKEN}
{
  "rating": 5,
  "comment": "Excellent product!"
}
```

## Common Issues & Solutions

### Issue: MySQL Connection Failed
**Solution:**
- Check MySQL is running: `mysql -u root -p`
- Verify username/password in application.properties
- Ensure database exists: `SHOW DATABASES;`

### Issue: Port 8080 already in use
**Solution:**
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>

# Or use a different port in application.properties
server.port=8081
```

### Issue: JWT Token Expired
**Solution:**
- Login again to get a new token
- Token expiration is set to 24 hours (86400000 ms)

### Issue: Authorization Errors
**Solution:**
- Ensure Bearer token is included correctly: `Authorization: Bearer {token}`
- Check user role has required permissions
- Admin operations require ADMIN role

## Database Tables

After first run, these tables are created automatically:

- `users` - User accounts
- `roles` - Role definitions
- `user_roles` - User-role mappings
- `products` - Product catalog
- `categories` - Product categories
- `inventory` - Stock tracking
- `carts` - Shopping carts
- `cart_items` - Cart items
- `orders` - Customer orders
- `order_items` - Order line items
- `reviews` - Product reviews

## Build & Deployment

### Development Build
```bash
mvn clean install
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/ecommerce-0.0.1-SNAPSHOT.jar
```

### Docker Deployment (Optional)

Create `Dockerfile`:
```dockerfile
FROM openjdk:27-slim
WORKDIR /app
COPY target/ecommerce-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
```

Build and run:
```bash
docker build -t ecommerce:1.0 .
docker run -p 8080:8080 ecommerce:1.0
```

## Monitoring & Logging

### View Application Logs
By default, logs are in console output. To save to file:

Update `application.properties`:
```properties
logging.file.name=logs/ecommerce.log
logging.level.root=INFO
logging.level.com.example.ecommerce=DEBUG
```

### Check Database Changes
```sql
SELECT * FROM users;
SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM reviews;
```

## Project Structure Overview

```
ecommerce/
├── src/main/java/com/example/ecommerce/
│   ├── config/           → Spring Security, OpenAPI configuration
│   ├── controller/       → REST endpoints
│   ├── dto/              → Data transfer objects
│   ├── entity/           → JPA entities
│   ├── exception/        → Exception handling
│   ├── repository/       → Database access
│   ├── security/         → JWT & security filters
│   └── service/          → Business logic
├── src/main/resources/
│   ├── application.properties  → Configuration
│   └── init-data.sql          → Sample data
├── pom.xml              → Maven dependencies
├── README.md            → Full documentation
└── Ecommerce-API-Collection.postman_collection.json
```

## API Response Format

### Success Response
```json
{
  "id": 1,
  "name": "Product Name",
  "price": 99.99,
  ...
}
```

### Error Response
```json
{
  "status": 400,
  "message": "Invalid input",
  "error": "Bad Request",
  "timestamp": "2024-01-15T10:30:00",
  "path": "/api/products"
}
```

## Performance Tips

1. Use pagination for large datasets:
   - `?page=0&size=20`

2. Create database indexes on frequently searched columns:
   ```sql
   CREATE INDEX idx_user_email ON users(email);
   CREATE INDEX idx_product_name ON products(name);
   ```

3. Enable query caching in MySQL for better performance

4. Use connection pooling (HikariCP - already configured)

## Security Checklist

- ✅ Passwords encrypted with BCrypt
- ✅ JWT tokens with expiration
- ✅ Role-based access control
- ✅ CORS properly configured
- ✅ Input validation on all endpoints
- ✅ SQL injection protection (using JPA)
- ✅ HTTPS ready (use reverse proxy in production)

## Next Steps

1. Test all endpoints using Postman collection
2. Review logs for any errors
3. Create additional test cases
4. Deploy to production server
5. Set up CI/CD pipeline
6. Monitor application performance

## Support & Help

For issues:
1. Check application logs for error details
2. Verify database connection
3. Ensure all dependencies are installed
4. Review API documentation in Swagger UI
5. Check GitHub issues if applicable

---

**Happy Testing! 🚀**
