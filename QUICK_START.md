# Quick Reference Guide - E-Commerce API

## 🚀 Start the Application

```bash
# Navigate to project directory
cd ecommerce

# Build and run
mvn spring-boot:run
```

Application runs at: `http://localhost:8080`

---

## 📖 Access Documentation

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs
- **Full Documentation**: Read `README.md`
- **Setup Guide**: Read `SETUP_GUIDE.md`
- **Implementation Details**: Read `IMPLEMENTATION_SUMMARY.md`

---

## 🔑 Default Test Credentials

| Email | Password | Role |
|-------|----------|------|
| admin@ecommerce.com | password123 | ADMIN |
| john@example.com | password123 | USER |
| jane@example.com | password123 | USER |

*Note: Run `init-data.sql` to load these test users*

---

## 📋 Essential API Endpoints

### Authentication
```
POST /api/auth/register   - Register new user
POST /api/auth/login      - Login user (returns JWT token)
```

### Products
```
GET    /api/products              - Get all products
GET    /api/products/{id}         - Get product by ID
POST   /api/products              - Create product (Admin)
PUT    /api/products/{id}         - Update product (Admin)
DELETE /api/products/{id}         - Delete product (Admin)
GET    /api/products/search?keyword=xyz - Search products
```

### Shopping
```
GET    /api/cart                  - View cart
POST   /api/cart/add              - Add to cart
PUT    /api/cart/update           - Update item quantity
DELETE /api/cart/remove/{id}      - Remove from cart
DELETE /api/cart/clear            - Clear cart
```

### Orders
```
POST   /api/orders                - Create order
GET    /api/orders/{id}           - Get order details
GET    /api/orders                - Get my orders (paginated)
DELETE /api/orders/{id}/cancel    - Cancel order
```

### Reviews
```
POST   /api/reviews/product/{productId} - Add review
GET    /api/reviews/product/{productId} - Get product reviews
PUT    /api/reviews/{id}                - Update review
DELETE /api/reviews/{id}               - Delete review
```

---

## 🧪 Testing with Postman

1. Import: `Ecommerce-API-Collection.postman_collection.json`
2. Login to get token
3. Update `token` variable with JWT
4. Test endpoints

---

## 📊 Database

**Create database:**
```bash
mysql -u root -p
CREATE DATABASE ecommerce_db;
```

**Load sample data:**
```bash
mysql -u root -p ecommerce_db < src/main/resources/init-data.sql
```

**Connection details in `application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

---

## 🔑 Getting JWT Token

### Using cURL
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "john@example.com", "password": "password123"}'
```

### Using Token in Requests
```bash
curl -X GET http://localhost:8080/api/products \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 📁 Key Files

| File | Purpose |
|------|---------|
| `pom.xml` | Maven dependencies |
| `application.properties` | Configuration |
| `README.md` | Full documentation |
| `SETUP_GUIDE.md` | Setup instructions |
| `IMPLEMENTATION_SUMMARY.md` | What was built |
| `init-data.sql` | Sample data |
| `Ecommerce-API-Collection.postman_collection.json` | Postman tests |

---

## 🛑 Common Issues

**MySQL Connection Failed**
- Check MySQL is running: `mysql -u root -p`
- Verify credentials in `application.properties`

**Port 8080 in Use**
- Change port in `application.properties`: `server.port=8081`

**No Endpoints Found**
- Ensure application started successfully
- Check logs for errors
- Verify Spring Security configuration

**Authorization Errors**
- Login first to get token
- Include `Authorization: Bearer {token}` header
- Check user role has required permissions

---

## 💡 Project Structure

```
ecommerce/
├── src/main/java/com/example/ecommerce/
│   ├── controller/    → REST endpoints
│   ├── service/       → Business logic
│   ├── entity/        → Database models
│   ├── repository/    → Data access
│   ├── security/      → JWT & auth
│   ├── config/        → Spring config
│   ├── dto/           → Data objects
│   └── exception/     → Error handling
├── src/main/resources/
│   ├── application.properties
│   └── init-data.sql
├── README.md
├── SETUP_GUIDE.md
├── IMPLEMENTATION_SUMMARY.md
└── pom.xml
```

---

## ✅ Features Checklist

- ✅ User Registration & Login with JWT
- ✅ Product Management (CRUD)
- ✅ Category Management
- ✅ Shopping Cart
- ✅ Order Management
- ✅ Order Status Tracking
- ✅ Product Reviews & Ratings
- ✅ Inventory Management
- ✅ Search & Pagination
- ✅ Role-Based Access Control
- ✅ API Documentation (Swagger)
- ✅ Global Exception Handling
- ✅ Secure Password Storage
- ✅ Error Responses

---

## 🔗 Useful Links

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Guide](https://spring.io/projects/spring-security)
- [JWT (jjwt) Documentation](https://github.com/jwtk/jjwt)
- [OpenAPI/Swagger](https://swagger.io/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Postman Documentation](https://www.postman.com/product/what-is-postman/)

---

## 📞 Next Steps

1. ✅ Start the application
2. ✅ Access Swagger UI at `/swagger-ui.html`
3. ✅ Login with test credentials
4. ✅ Test endpoints using Postman collection
5. ✅ Review code and documentation
6. ✅ Deploy to your server

---

## 🎯 You're Ready!

The E-Commerce REST API is fully functional and production-ready. 

**Happy coding! 🚀**
