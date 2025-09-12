# 🛒 Flipkart-like Microservices (Spring Boot)

This document describes the **project structure** and **sample API payloads** for our microservices:
- Product Service
- Order Service
- Payment Service

---

## 📂 1. Product Service

### Project Structure
```
src/main/java/productService/
│
├── config/                
│   ├── SwaggerConfig.java
│   ├── RedisConfig.java
│   └── SecurityConfig.java
│
├── constants/             
│   ├── ProductCategory.java
│   └── ProductStatus.java
│
├── controller/            
│   └── ProductController.java
│
├── dtos/                  
│   ├── ProductRequest.java
│   ├── ProductResponse.java
│   ├── ProductAttributeRequest.java
│   ├── ProductAttributeResponse.java
│   └── PagedResponse.java
│
├── exception/             
│   └── GlobalExceptionHandler.java
│
├── mapper/                
│   └── ProductMapper.java
│
├── model/                 
│   ├── Product.java
│   ├── ProductAttribute.java
│   └── ProductImage.java
│
├── repository/            
│   └── ProductRepository.java
│
├── service/               
│   └── ProductService.java
│
├── utils/                 
│   └── PageMapper.java
│
└── ProductServiceApplication.java
```

### Sample Payloads

#### Create Product Request
```json
{
  "sku": "SKU-IPHONE15",
  "name": "iPhone 15 Pro",
  "description": "Latest Apple iPhone 15 Pro with 128GB storage",
  "category": "ELECTRONICS",
  "price": 899.99,
  "currency": "USD",
  "brand": "Apple",
  "productAttributes": [
    { "name": "Color", "value": "Red" },
    { "name": "Storage", "value": "128GB" }
  ],
  "imageUrls": [
    { "url": "https://cdn.flipkart.com/iphone15-front.jpg", "isPrimary": true },
    { "url": "https://cdn.flipkart.com/iphone15-back.jpg", "isPrimary": false }
  ]
}
```

#### Update Product Request
```json
{
  "sku": "SKU-IPHONE17",
  "name": "iPhone 17 Pro",
  "productAttributes": [
    { "name": "Color", "value": "Silver" },
    { "name": "Storage", "value": "256GB" }
  ],
  "imageUrls": [
    { "url": "https://mys3.com/ajeetiphone17.jpg", "isPrimary": true },
    { "url": "https://cdn.flipkart.com/iphone17-back.jpg", "isPrimary": false }
  ]
}
```

#### Product Response
```json
{
  "id": 1,
  "sku": "SKU-IPHONE15",
  "name": "iPhone 15 Pro",
  "category": "ELECTRONICS",
  "status": "ACTIVE",
  "price": 899.99,
  "currency": "USD",
  "brand": "Apple",
  "productAttributes": [
    { "name": "Color", "value": "Red" },
    { "name": "Storage", "value": "128GB" }
  ],
  "imageUrls": [
    { "url": "https://cdn.flipkart.com/iphone15-front.jpg", "isPrimary": true },
    { "url": "https://cdn.flipkart.com/iphone15-back.jpg", "isPrimary": false }
  ]
}
```

---

## 📂 2. Order Service

### Project Structure
```
src/main/java/orderService/
│
├── controller/
│   └── OrderController.java
│
├── dtos/
│   ├── OrderRequest.java
│   ├── OrderResponse.java
│   └── OrderItemDto.java
│
├── exception/
│   └── GlobalExceptionHandler.java
│
├── mapper/
│   └── OrderMapper.java
│
├── model/
│   ├── Order.java
│   └── OrderItem.java
│
├── repository/
│   └── OrderRepository.java
│
├── service/
│   └── OrderService.java
│
└── OrderServiceApplication.java
```

### Sample Payloads

#### Create Order Request
```json
{
  "userId": 101,
  "items": [
    { "productId": 1, "quantity": 2 },
    { "productId": 2, "quantity": 1 }
  ],
  "shippingAddress": "Bangalore, India"
}
```

#### Order Response
```json
{
  "id": 5001,
  "userId": 101,
  "items": [
    { "productId": 1, "quantity": 2, "price": 899.99 },
    { "productId": 2, "quantity": 1, "price": 799.99 }
  ],
  "totalAmount": 2599.97,
  "status": "PLACED",
  "createdAt": "2025-09-06T12:30:00Z"
}
```

---

## 📂 3. Payment Service

### Project Structure
```
src/main/java/productService/
│
├── config/                
│   ├── SwaggerConfig.java
│   ├── RedisConfig.java
│   └── SecurityConfig.java
│
├── constants/             
│   ├── ProductCategory.java
│   └── ProductStatus.java
│
├── controller/            
│   └── ProductController.java
│
├── dtos/                  
│   ├── ProductRequest.java
│   ├── ProductResponse.java
│   ├── ProductAttributeRequest.java
│   ├── ProductAttributeResponse.java
│   └── PagedResponse.java
│
├── exception/             
│   └── GlobalExceptionHandler.java
│
├── mapper/                
│   └── ProductMapper.java
│
├── model/                 
│   ├── Product.java
│   ├── ProductAttribute.java
│   └── ProductImage.java
│
├── repository/            
│   └── ProductRepository.java
│
├── service/               
│   ├── command/
│   │   ├── ProductCommandService.java
│   │   └── ProductCommandServiceImpl.java
│   │
│   ├── event/
│   │   ├── ProductEventService.java
│   │   └── ProductEventServiceImpl.java
│   │
│   └── query/
│       ├── ProductQueryService.java
│       └── ProductQueryServiceImpl.java
│
├── utils/                 
│   └── PageMapper.java
│
└── ProductServiceApplication.java

```

### Sample Payloads

#### Create Payment Request
```json
{
  "orderId": 5001,
  "amount": 2599.97,
  "paymentMethod": "CREDIT_CARD",
  "transactionId": "TXN-12345"
}
```

#### Payment Response
```json
{
  "id": 9001,
  "orderId": 5001,
  "amount": 2599.97,
  "paymentMethod": "CREDIT_CARD",
  "status": "SUCCESS",
  "transactionId": "TXN-12345",
  "createdAt": "2025-09-06T12:35:00Z"
}
```

---

## 🚀 Setup
1. Clone repo:  
   ```bash
   git clone https://github.com/your-org/flipkart-clone.git
   ```
2. Navigate into service folder:  
   ```bash
   cd product-service
   ```
3. Run service:  
   ```bash
   ./gradlew bootRun
   ```


## 🚀 Setup Kafka
1. Install Kafka:
   ```bash
   brew install kafka
   ```
2. To run kafka without zookeeper:
   ```bash
   /opt/homebrew/opt/kafka/bin/kafka-server-start /opt/homebrew/etc/kafka/server.properties
   ```
3. Or run as a background service:
   ```bash
   brew services start kafka
   ```
4. Create a topic:
   ```bash
   /opt/homebrew/opt/kafka/bin/kafka-topics --create --topic product-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
   ```
  