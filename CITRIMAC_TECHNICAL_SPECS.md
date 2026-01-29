# 🔧 CITRIMAC - Especificaciones Técnicas Detalladas

## Índice
1. [API Endpoints](#api-endpoints)
2. [Modelo de Base de Datos SQL](#modelo-de-base-de-datos-sql)
3. [Configuración de Seguridad](#configuración-de-seguridad)
4. [Integración de Pasarela de Pagos](#integración-de-pasarela-de-pagos)
5. [Sistema de Caché](#sistema-de-caché)
6. [Manejo de Archivos](#manejo-de-archivos)

---

## 1. API Endpoints

### 1.1 Autenticación y Usuarios

#### POST /api/auth/register
Registrar nuevo usuario.

**Request Body:**
```json
{
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@example.com",
  "password": "SecurePass123!",
  "phone": "1234567890"
}
```

**Response (201):**
```json
{
  "id": 1,
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@example.com",
  "phone": "1234567890",
  "role": "USER",
  "active": true,
  "createdAt": "2026-01-29T10:00:00Z"
}
```

---

#### POST /api/auth/login
Iniciar sesión.

**Request Body:**
```json
{
  "email": "juan@example.com",
  "password": "SecurePass123!"
}
```

**Response (200):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 3600,
  "user": {
    "id": 1,
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan@example.com",
    "role": "USER"
  }
}
```

---

#### POST /api/auth/refresh
Renovar token de acceso.

**Request Body:**
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Response (200):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 3600
}
```

---

#### POST /api/auth/forgot-password
Solicitar recuperación de contraseña.

**Request Body:**
```json
{
  "email": "juan@example.com"
}
```

**Response (200):**
```json
{
  "message": "Se ha enviado un email con instrucciones para recuperar tu contraseña"
}
```

---

#### POST /api/auth/reset-password
Restablecer contraseña con token.

**Request Body:**
```json
{
  "token": "abc123def456",
  "newPassword": "NewSecurePass123!"
}
```

**Response (200):**
```json
{
  "message": "Contraseña actualizada exitosamente"
}
```

---

#### GET /api/users/me
Obtener perfil del usuario autenticado.

**Headers:** `Authorization: Bearer {token}`

**Response (200):**
```json
{
  "id": 1,
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@example.com",
  "phone": "1234567890",
  "role": "USER",
  "active": true,
  "createdAt": "2026-01-29T10:00:00Z",
  "updatedAt": "2026-01-29T10:00:00Z"
}
```

---

#### PUT /api/users/me
Actualizar perfil del usuario.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "firstName": "Juan Carlos",
  "lastName": "Pérez García",
  "phone": "0987654321"
}
```

**Response (200):**
```json
{
  "id": 1,
  "firstName": "Juan Carlos",
  "lastName": "Pérez García",
  "email": "juan@example.com",
  "phone": "0987654321",
  "updatedAt": "2026-01-29T11:00:00Z"
}
```

---

### 1.2 Productos

#### GET /api/products
Obtener lista de productos con paginación y filtros.

**Query Parameters:**
- `page` (opcional, default: 0): Número de página
- `size` (opcional, default: 12): Elementos por página
- `category` (opcional): ID de categoría
- `search` (opcional): Búsqueda por nombre
- `minPrice` (opcional): Precio mínimo
- `maxPrice` (opcional): Precio máximo
- `sort` (opcional): Campo de ordenamiento (name, price, createdAt)
- `direction` (opcional): Dirección (asc, desc)

**Response (200):**
```json
{
  "content": [
    {
      "id": 1,
      "name": "Manzanas Rojas",
      "description": "Manzanas frescas de alta calidad",
      "price": 2.99,
      "stock": 100,
      "unit": "kg",
      "sku": "FRU-MAN-001",
      "category": {
        "id": 1,
        "name": "Frutas"
      },
      "primaryImage": "https://cdn.citrimac.com/products/manzanas.jpg",
      "images": [
        {
          "id": 1,
          "imageUrl": "https://cdn.citrimac.com/products/manzanas.jpg",
          "isPrimary": true
        }
      ],
      "discount": 0,
      "featured": true,
      "active": true
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 12,
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    }
  },
  "totalPages": 5,
  "totalElements": 60,
  "last": false,
  "first": true,
  "numberOfElements": 12
}
```

---

#### GET /api/products/{id}
Obtener detalle de un producto.

**Response (200):**
```json
{
  "id": 1,
  "name": "Manzanas Rojas",
  "description": "Manzanas frescas de alta calidad cultivadas en huertos locales",
  "price": 2.99,
  "stock": 100,
  "unit": "kg",
  "sku": "FRU-MAN-001",
  "weight": 1.0,
  "nutritionInfo": {
    "calories": 52,
    "carbs": "14g",
    "protein": "0.3g",
    "fat": "0.2g",
    "fiber": "2.4g"
  },
  "category": {
    "id": 1,
    "name": "Frutas",
    "slug": "frutas"
  },
  "images": [
    {
      "id": 1,
      "imageUrl": "https://cdn.citrimac.com/products/manzanas-1.jpg",
      "isPrimary": true,
      "displayOrder": 1
    },
    {
      "id": 2,
      "imageUrl": "https://cdn.citrimac.com/products/manzanas-2.jpg",
      "isPrimary": false,
      "displayOrder": 2
    }
  ],
  "discount": 0,
  "featured": true,
  "active": true,
  "createdAt": "2026-01-20T10:00:00Z",
  "updatedAt": "2026-01-29T10:00:00Z"
}
```

---

#### POST /api/admin/products
Crear nuevo producto (Admin).

**Headers:** `Authorization: Bearer {admin-token}`

**Request Body:**
```json
{
  "name": "Tomates Cherry",
  "description": "Tomates cherry orgánicos",
  "price": 3.99,
  "stock": 50,
  "unit": "kg",
  "sku": "VER-TOM-002",
  "weight": 0.5,
  "categoryId": 2,
  "nutritionInfo": {
    "calories": 18,
    "carbs": "3.9g",
    "protein": "0.9g",
    "fat": "0.2g"
  },
  "featured": false,
  "discount": 10
}
```

**Response (201):**
```json
{
  "id": 25,
  "name": "Tomates Cherry",
  "sku": "VER-TOM-002",
  "price": 3.99,
  "stock": 50,
  "createdAt": "2026-01-29T12:00:00Z"
}
```

---

#### PUT /api/admin/products/{id}
Actualizar producto (Admin).

**Headers:** `Authorization: Bearer {admin-token}`

**Request Body:**
```json
{
  "name": "Tomates Cherry Orgánicos",
  "price": 4.49,
  "stock": 75
}
```

**Response (200):**
```json
{
  "id": 25,
  "name": "Tomates Cherry Orgánicos",
  "price": 4.49,
  "stock": 75,
  "updatedAt": "2026-01-29T13:00:00Z"
}
```

---

#### DELETE /api/admin/products/{id}
Eliminar producto (soft delete) (Admin).

**Headers:** `Authorization: Bearer {admin-token}`

**Response (204):** No Content

---

### 1.3 Categorías

#### GET /api/categories
Obtener todas las categorías activas.

**Response (200):**
```json
[
  {
    "id": 1,
    "name": "Frutas",
    "description": "Frutas frescas de temporada",
    "slug": "frutas",
    "imageUrl": "https://cdn.citrimac.com/categories/frutas.jpg",
    "displayOrder": 1,
    "productCount": 25
  },
  {
    "id": 2,
    "name": "Verduras",
    "description": "Verduras frescas y orgánicas",
    "slug": "verduras",
    "imageUrl": "https://cdn.citrimac.com/categories/verduras.jpg",
    "displayOrder": 2,
    "productCount": 30
  }
]
```

---

#### POST /api/admin/categories
Crear nueva categoría (Admin).

**Headers:** `Authorization: Bearer {admin-token}`

**Request Body:**
```json
{
  "name": "Orgánicos",
  "description": "Productos 100% orgánicos certificados",
  "slug": "organicos",
  "imageUrl": "https://cdn.citrimac.com/categories/organicos.jpg",
  "displayOrder": 3
}
```

**Response (201):**
```json
{
  "id": 3,
  "name": "Orgánicos",
  "slug": "organicos",
  "active": true,
  "createdAt": "2026-01-29T12:00:00Z"
}
```

---

### 1.4 Carrito de Compras

#### GET /api/cart
Obtener carrito del usuario autenticado.

**Headers:** `Authorization: Bearer {token}`

**Response (200):**
```json
{
  "items": [
    {
      "id": 1,
      "product": {
        "id": 1,
        "name": "Manzanas Rojas",
        "price": 2.99,
        "primaryImage": "https://cdn.citrimac.com/products/manzanas.jpg",
        "stock": 100,
        "unit": "kg"
      },
      "quantity": 2,
      "subtotal": 5.98
    },
    {
      "id": 2,
      "product": {
        "id": 5,
        "name": "Plátanos",
        "price": 1.49,
        "primaryImage": "https://cdn.citrimac.com/products/platanos.jpg",
        "stock": 80,
        "unit": "kg"
      },
      "quantity": 1,
      "subtotal": 1.49
    }
  ],
  "subtotal": 7.47,
  "itemCount": 3
}
```

---

#### POST /api/cart/items
Agregar producto al carrito.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "productId": 1,
  "quantity": 2
}
```

**Response (201):**
```json
{
  "id": 1,
  "product": {
    "id": 1,
    "name": "Manzanas Rojas",
    "price": 2.99
  },
  "quantity": 2,
  "subtotal": 5.98
}
```

---

#### PUT /api/cart/items/{itemId}
Actualizar cantidad de un ítem en el carrito.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "quantity": 3
}
```

**Response (200):**
```json
{
  "id": 1,
  "quantity": 3,
  "subtotal": 8.97
}
```

---

#### DELETE /api/cart/items/{itemId}
Eliminar ítem del carrito.

**Headers:** `Authorization: Bearer {token}`

**Response (204):** No Content

---

#### DELETE /api/cart
Vaciar carrito completo.

**Headers:** `Authorization: Bearer {token}`

**Response (204):** No Content

---

### 1.5 Pedidos

#### POST /api/orders
Crear nuevo pedido.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "addressId": 1,
  "shippingMethod": "STANDARD",
  "paymentMethod": "CREDIT_CARD",
  "notes": "Entregar en la mañana"
}
```

**Response (201):**
```json
{
  "id": 100,
  "orderNumber": "ORD-2026-0100",
  "status": "PENDING",
  "items": [
    {
      "id": 1,
      "product": {
        "id": 1,
        "name": "Manzanas Rojas"
      },
      "quantity": 2,
      "price": 2.99,
      "subtotal": 5.98
    }
  ],
  "subtotal": 5.98,
  "shippingCost": 3.50,
  "tax": 0.75,
  "totalAmount": 10.23,
  "shippingAddress": {
    "street": "Av. Principal 123",
    "city": "Madrid",
    "state": "Madrid",
    "zipCode": "28001",
    "country": "España"
  },
  "createdAt": "2026-01-29T14:00:00Z"
}
```

---

#### GET /api/orders
Obtener pedidos del usuario autenticado.

**Headers:** `Authorization: Bearer {token}`

**Query Parameters:**
- `page` (opcional, default: 0)
- `size` (opcional, default: 10)
- `status` (opcional): Filtrar por estado

**Response (200):**
```json
{
  "content": [
    {
      "id": 100,
      "orderNumber": "ORD-2026-0100",
      "status": "PROCESSING",
      "totalAmount": 10.23,
      "itemCount": 2,
      "createdAt": "2026-01-29T14:00:00Z"
    }
  ],
  "totalElements": 5,
  "totalPages": 1
}
```

---

#### GET /api/orders/{orderNumber}
Obtener detalle de pedido.

**Headers:** `Authorization: Bearer {token}`

**Response (200):**
```json
{
  "id": 100,
  "orderNumber": "ORD-2026-0100",
  "status": "PROCESSING",
  "items": [
    {
      "product": {
        "id": 1,
        "name": "Manzanas Rojas",
        "primaryImage": "https://cdn.citrimac.com/products/manzanas.jpg"
      },
      "quantity": 2,
      "price": 2.99,
      "subtotal": 5.98
    }
  ],
  "subtotal": 5.98,
  "shippingCost": 3.50,
  "tax": 0.75,
  "totalAmount": 10.23,
  "shippingAddress": {
    "recipientName": "Juan Pérez",
    "phone": "1234567890",
    "street": "Av. Principal 123",
    "city": "Madrid",
    "state": "Madrid",
    "zipCode": "28001",
    "country": "España"
  },
  "payment": {
    "method": "CREDIT_CARD",
    "status": "COMPLETED",
    "transactionId": "ch_3abc123"
  },
  "timeline": [
    {
      "status": "PENDING",
      "timestamp": "2026-01-29T14:00:00Z"
    },
    {
      "status": "CONFIRMED",
      "timestamp": "2026-01-29T14:05:00Z"
    },
    {
      "status": "PROCESSING",
      "timestamp": "2026-01-29T14:30:00Z"
    }
  ],
  "notes": "Entregar en la mañana",
  "createdAt": "2026-01-29T14:00:00Z",
  "updatedAt": "2026-01-29T14:30:00Z"
}
```

---

#### PATCH /api/admin/orders/{id}/status
Actualizar estado de pedido (Admin).

**Headers:** `Authorization: Bearer {admin-token}`

**Request Body:**
```json
{
  "status": "SHIPPED",
  "trackingNumber": "TRK123456789"
}
```

**Response (200):**
```json
{
  "id": 100,
  "orderNumber": "ORD-2026-0100",
  "status": "SHIPPED",
  "trackingNumber": "TRK123456789",
  "updatedAt": "2026-01-29T15:00:00Z"
}
```

---

### 1.6 Direcciones

#### GET /api/addresses
Obtener direcciones del usuario.

**Headers:** `Authorization: Bearer {token}`

**Response (200):**
```json
[
  {
    "id": 1,
    "recipientName": "Juan Pérez",
    "phone": "1234567890",
    "street": "Av. Principal 123",
    "city": "Madrid",
    "state": "Madrid",
    "zipCode": "28001",
    "country": "España",
    "isDefault": true
  },
  {
    "id": 2,
    "recipientName": "Juan Pérez",
    "phone": "0987654321",
    "street": "Calle Secundaria 456",
    "city": "Barcelona",
    "state": "Cataluña",
    "zipCode": "08001",
    "country": "España",
    "isDefault": false
  }
]
```

---

#### POST /api/addresses
Crear nueva dirección.

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "recipientName": "Juan Pérez",
  "phone": "1234567890",
  "street": "Av. Principal 123, Apto 5B",
  "city": "Madrid",
  "state": "Madrid",
  "zipCode": "28001",
  "country": "España",
  "isDefault": true
}
```

**Response (201):**
```json
{
  "id": 3,
  "recipientName": "Juan Pérez",
  "street": "Av. Principal 123, Apto 5B",
  "city": "Madrid",
  "isDefault": true,
  "createdAt": "2026-01-29T16:00:00Z"
}
```

---

### 1.7 Pagos

#### POST /api/payments/create-payment-intent
Crear intención de pago (Stripe).

**Headers:** `Authorization: Bearer {token}`

**Request Body:**
```json
{
  "orderId": 100,
  "amount": 10.23,
  "currency": "EUR"
}
```

**Response (200):**
```json
{
  "clientSecret": "pi_3abc123_secret_xyz789",
  "paymentIntentId": "pi_3abc123",
  "amount": 1023,
  "currency": "eur"
}
```

---

#### POST /api/payments/webhook
Webhook de Stripe (procesamiento interno).

**Headers:** `Stripe-Signature: {signature}`

**Request Body (Stripe Event):**
```json
{
  "type": "payment_intent.succeeded",
  "data": {
    "object": {
      "id": "pi_3abc123",
      "amount": 1023,
      "status": "succeeded"
    }
  }
}
```

**Response (200):**
```json
{
  "received": true
}
```

---

### 1.8 Admin Dashboard

#### GET /api/admin/dashboard/stats
Obtener estadísticas del dashboard (Admin).

**Headers:** `Authorization: Bearer {admin-token}`

**Response (200):**
```json
{
  "today": {
    "sales": 1250.50,
    "orders": 45,
    "newUsers": 12
  },
  "thisWeek": {
    "sales": 8750.25,
    "orders": 320,
    "newUsers": 78
  },
  "thisMonth": {
    "sales": 35000.00,
    "orders": 1200,
    "newUsers": 250
  },
  "lowStockProducts": 8,
  "pendingOrders": 15
}
```

---

#### GET /api/admin/reports/sales
Reporte de ventas (Admin).

**Headers:** `Authorization: Bearer {admin-token}`

**Query Parameters:**
- `startDate`: Fecha inicio (ISO 8601)
- `endDate`: Fecha fin (ISO 8601)
- `groupBy` (opcional): day, week, month

**Response (200):**
```json
{
  "period": {
    "startDate": "2026-01-01",
    "endDate": "2026-01-31"
  },
  "totalSales": 35000.00,
  "totalOrders": 1200,
  "averageOrderValue": 29.17,
  "salesByDay": [
    {
      "date": "2026-01-01",
      "sales": 1500.00,
      "orders": 52
    },
    {
      "date": "2026-01-02",
      "sales": 1800.00,
      "orders": 61
    }
  ],
  "topProducts": [
    {
      "productId": 1,
      "productName": "Manzanas Rojas",
      "quantitySold": 500,
      "revenue": 1495.00
    }
  ]
}
```

---

## 2. Modelo de Base de Datos SQL

### 2.1 Script de Creación de Tablas

```sql
-- Tabla de Usuarios
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_active ON users(active);

-- Tabla de Tokens de Refresh
CREATE TABLE refresh_tokens (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    token VARCHAR(500) NOT NULL UNIQUE,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_refresh_tokens_user_id ON refresh_tokens(user_id);
CREATE INDEX idx_refresh_tokens_token ON refresh_tokens(token);

-- Tabla de Categorías
CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    slug VARCHAR(100) NOT NULL UNIQUE,
    image_url VARCHAR(500),
    display_order INT NOT NULL DEFAULT 0,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_categories_slug ON categories(slug);
CREATE INDEX idx_categories_active ON categories(active);

-- Tabla de Productos
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT NOT NULL REFERENCES categories(id),
    name VARCHAR(200) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    unit VARCHAR(20) NOT NULL DEFAULT 'kg',
    sku VARCHAR(50) UNIQUE,
    weight DECIMAL(10, 2),
    nutrition_info JSONB,
    featured BOOLEAN NOT NULL DEFAULT FALSE,
    discount INT NOT NULL DEFAULT 0 CHECK (discount >= 0 AND discount <= 100),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_products_category_id ON products(category_id);
CREATE INDEX idx_products_sku ON products(sku);
CREATE INDEX idx_products_active ON products(active);
CREATE INDEX idx_products_featured ON products(featured);
CREATE INDEX idx_products_price ON products(price);

-- Tabla de Imágenes de Productos
CREATE TABLE product_images (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
    image_url VARCHAR(500) NOT NULL,
    is_primary BOOLEAN NOT NULL DEFAULT FALSE,
    display_order INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_product_images_product_id ON product_images(product_id);

-- Tabla de Direcciones
CREATE TABLE addresses (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    recipient_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    street VARCHAR(200) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    is_default BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_addresses_user_id ON addresses(user_id);

-- Tabla de Pedidos
CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    address_id BIGINT NOT NULL REFERENCES addresses(id),
    order_number VARCHAR(50) NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    subtotal DECIMAL(10, 2) NOT NULL,
    shipping_cost DECIMAL(10, 2) NOT NULL DEFAULT 0,
    tax DECIMAL(10, 2) NOT NULL DEFAULT 0,
    total_amount DECIMAL(10, 2) NOT NULL,
    notes TEXT,
    tracking_number VARCHAR(100),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_orders_order_number ON orders(order_number);
CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_orders_created_at ON orders(created_at DESC);

-- Tabla de Ítems de Pedidos
CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES products(id),
    quantity INT NOT NULL CHECK (quantity > 0),
    price DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_order_items_order_id ON order_items(order_id);
CREATE INDEX idx_order_items_product_id ON order_items(product_id);

-- Tabla de Pagos
CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL UNIQUE REFERENCES orders(id),
    amount DECIMAL(10, 2) NOT NULL,
    method VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    transaction_id VARCHAR(200),
    metadata JSONB,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_payments_order_id ON payments(order_id);
CREATE INDEX idx_payments_transaction_id ON payments(transaction_id);
CREATE INDEX idx_payments_status ON payments(status);

-- Tabla de Carrito (opcional, si se persiste en BD)
CREATE TABLE cart_items (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES products(id),
    quantity INT NOT NULL CHECK (quantity > 0),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, product_id)
);

CREATE INDEX idx_cart_items_user_id ON cart_items(user_id);

-- Tabla de Lista de Deseos
CREATE TABLE wishlist (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES products(id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, product_id)
);

CREATE INDEX idx_wishlist_user_id ON wishlist(user_id);

-- Tabla de Historial de Estados de Pedidos
CREATE TABLE order_status_history (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL,
    notes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_order_status_history_order_id ON order_status_history(order_id);
```

---

## 3. Configuración de Seguridad

### 3.1 Configuración de Spring Security

```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Públicos
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/products/**").permitAll()
                .requestMatchers("/api/categories/**").permitAll()
                .requestMatchers("/api/payments/webhook").permitAll()
                
                // Usuario autenticado
                .requestMatchers("/api/cart/**").authenticated()
                .requestMatchers("/api/orders/**").authenticated()
                .requestMatchers("/api/addresses/**").authenticated()
                .requestMatchers("/api/users/me/**").authenticated()
                
                // Solo admin
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                
                // Resto requiere autenticación
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter(), 
                UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://citrimac.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### 3.2 Configuración JWT

```java
@Component
public class JwtTokenProvider {
    
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration}")
    private long jwtExpiration; // 1 hora
    
    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration; // 7 días
    
    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities());
        
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
    
    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
```

---

## 4. Integración de Pasarela de Pagos

### 4.1 Stripe Integration

```java
@Service
public class StripePaymentService {
    
    @Value("${stripe.api.key}")
    private String stripeApiKey;
    
    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }
    
    public PaymentIntent createPaymentIntent(Long orderId, BigDecimal amount) 
            throws StripeException {
        
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount.multiply(new BigDecimal(100)).longValue()); // centavos
        params.put("currency", "eur");
        params.put("metadata", Map.of("orderId", orderId.toString()));
        
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        
        return paymentIntent;
    }
    
    public void handleWebhook(String payload, String sigHeader) 
            throws SignatureVerificationException {
        
        String endpointSecret = "whsec_..."; // De Stripe Dashboard
        
        Event event = Webhook.constructEvent(
            payload, sigHeader, endpointSecret
        );
        
        if ("payment_intent.succeeded".equals(event.getType())) {
            PaymentIntent paymentIntent = (PaymentIntent) event.getData().getObject();
            handlePaymentSuccess(paymentIntent);
        } else if ("payment_intent.payment_failed".equals(event.getType())) {
            PaymentIntent paymentIntent = (PaymentIntent) event.getData().getObject();
            handlePaymentFailure(paymentIntent);
        }
    }
    
    private void handlePaymentSuccess(PaymentIntent paymentIntent) {
        String orderIdStr = paymentIntent.getMetadata().get("orderId");
        Long orderId = Long.parseLong(orderIdStr);
        
        // Actualizar estado del pago y pedido
        // Enviar email de confirmación
    }
}
```

---

## 5. Sistema de Caché

### 5.1 Configuración de Redis

```java
@Configuration
@EnableCaching
public class RedisConfig {
    
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofHours(1))
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new GenericJackson2JsonRedisSerializer()));
        
        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(config)
            .build();
    }
}
```

### 5.2 Uso de Caché

```java
@Service
public class ProductService {
    
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
    
    @Cacheable(value = "products-list", 
               key = "#categoryId + '_' + #page + '_' + #size")
    public Page<Product> getProducts(Long categoryId, int page, int size) {
        // Implementación
    }
    
    @CacheEvict(value = "products", key = "#id")
    public Product updateProduct(Long id, ProductRequest request) {
        // Implementación
    }
    
    @CacheEvict(value = {"products", "products-list"}, allEntries = true)
    public Product createProduct(ProductRequest request) {
        // Implementación
    }
}
```

---

## 6. Manejo de Archivos

### 6.1 Integración con Cloudinary

```java
@Service
public class CloudinaryService {
    
    private final Cloudinary cloudinary;
    
    public CloudinaryService(
            @Value("${cloudinary.cloud-name}") String cloudName,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret) {
        
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret));
    }
    
    public String uploadImage(MultipartFile file, String folder) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(
            file.getBytes(),
            ObjectUtils.asMap(
                "folder", "citrimac/" + folder,
                "resource_type", "auto"
            )
        );
        
        return uploadResult.get("url").toString();
    }
    
    public void deleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
```

---

## 7. Variables de Entorno

```properties
# application.properties

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/citrimac
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.cache.type=redis

# JWT
jwt.secret=${JWT_SECRET}
jwt.expiration=3600000
jwt.refresh-expiration=604800000

# Stripe
stripe.api.key=${STRIPE_API_KEY}
stripe.webhook.secret=${STRIPE_WEBHOOK_SECRET}

# Cloudinary
cloudinary.cloud-name=${CLOUDINARY_CLOUD_NAME}
cloudinary.api-key=${CLOUDINARY_API_KEY}
cloudinary.api-secret=${CLOUDINARY_API_SECRET}

# Email
spring.mail.host=smtp.sendgrid.net
spring.mail.port=587
spring.mail.username=apikey
spring.mail.password=${SENDGRID_API_KEY}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Server
server.port=8080
server.error.include-message=always
```

---

**Documento Técnico - Citrimac E-commerce Platform**  
**Versión:** 1.0  
**Fecha:** Enero 2026
