# 🍎 CITRIMAC - Plan Completo de Desarrollo
## Plataforma E-commerce de Frutas y Verduras

---

## 📋 Índice
1. [Resumen Ejecutivo](#resumen-ejecutivo)
2. [Objetivos del Proyecto](#objetivos-del-proyecto)
3. [Alcance del Proyecto](#alcance-del-proyecto)
4. [Arquitectura del Sistema](#arquitectura-del-sistema)
5. [Modelo de Datos](#modelo-de-datos)
6. [Módulos y Funcionalidades](#módulos-y-funcionalidades)
7. [Diseño UX/UI](#diseño-uxui)
8. [Stack Tecnológico](#stack-tecnológico)
9. [Fases de Desarrollo](#fases-de-desarrollo)
10. [Cronograma Estimado](#cronograma-estimado)
11. [Riesgos y Mitigaciones](#riesgos-y-mitigaciones)

---

## 1. Resumen Ejecutivo

**Citrimac** es una plataforma de comercio electrónico especializada en la venta de frutas y verduras frescas. El sistema permitirá a los usuarios navegar por un catálogo de productos, realizar compras en línea con pasarela de pagos integrada, y gestionar sus pedidos a través de un dashboard personalizado. Los administradores contarán con herramientas completas para gestionar el inventario, productos, pedidos y usuarios.

---

## 2. Objetivos del Proyecto

### Objetivos Generales
- Crear una plataforma e-commerce completa y funcional para la venta de frutas y verduras
- Proporcionar una experiencia de usuario intuitiva y atractiva
- Facilitar la gestión administrativa del negocio mediante un dashboard robusto
- Implementar un sistema de pagos seguro y confiable

### Objetivos Específicos
- Desarrollar un landing page atractivo que comunique el valor de Citrimac
- Implementar sistema de autenticación seguro (registro, login, recuperación de contraseña)
- Crear catálogo de productos con búsqueda, filtros y categorías
- Desarrollar flujo de compra completo (carrito, checkout, confirmación)
- Integrar pasarela de pagos con múltiples métodos de pago
- Implementar dashboard de usuario para seguimiento de pedidos
- Desarrollar dashboard administrativo para gestión del negocio
- Garantizar diseño responsive y accesible (UX/UI)

---

## 3. Alcance del Proyecto

### Módulos Incluidos

#### 3.1 Landing Page
- Hero section con propuesta de valor
- Sección de productos destacados
- Beneficios y características
- Testimonios de clientes
- Newsletter subscription
- Footer con información de contacto

#### 3.2 Sistema de Autenticación
- Registro de usuarios
- Login (email/contraseña)
- Recuperación de contraseña
- Verificación de email
- OAuth (opcional: Google, Facebook)
- Gestión de sesiones

#### 3.3 Catálogo de Productos
- Listado de productos con paginación
- Búsqueda por nombre
- Filtros (categoría, precio, disponibilidad)
- Vista de detalle de producto
- Imágenes múltiples por producto
- Información nutricional
- Stock disponible

#### 3.4 Sistema de Compras
- Carrito de compras persistente
- Modificación de cantidades
- Cálculo automático de totales
- Costos de envío
- Proceso de checkout multi-paso
- Resumen de orden antes de pagar

#### 3.5 Pasarela de Pagos
- Integración con procesadores de pago (Stripe/PayPal)
- Múltiples métodos de pago:
  - Tarjetas de crédito/débito
  - PayPal
  - Transferencias bancarias
- Confirmación de pago
- Generación de comprobantes

#### 3.6 Dashboard de Usuario
- Perfil de usuario editable
- Historial de pedidos
- Seguimiento de pedidos en tiempo real
- Facturas descargables
- Direcciones guardadas
- Métodos de pago guardados
- Favoritos/Lista de deseos

#### 3.7 Dashboard de Administrador
- Panel de control con métricas
- Gestión de productos (CRUD completo):
  - Crear/Editar/Eliminar productos
  - Gestión de imágenes
  - Control de stock
  - Precios y descuentos
- Gestión de categorías
- Gestión de pedidos:
  - Visualización de pedidos
  - Cambio de estados
  - Generación de reportes
- Gestión de usuarios
- Reportes y estadísticas:
  - Ventas por período
  - Productos más vendidos
  - Ingresos totales
- Gestión de inventario y alertas de stock bajo

#### 3.8 Sistema de Notificaciones
- Email de confirmación de registro
- Email de confirmación de pedido
- Actualizaciones de estado de pedido
- Notificaciones de ofertas (opcional)

---

## 4. Arquitectura del Sistema

### 4.1 Arquitectura General

```
┌─────────────────────────────────────────────────────────────┐
│                       FRONTEND LAYER                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │   Landing    │  │  User Web    │  │  Admin Web   │     │
│  │     Page     │  │     App      │  │    Panel     │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
│         React/Vue/Angular + TypeScript                      │
└─────────────────────────────────────────────────────────────┘
                            │
                            │ HTTPS / REST API
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                       BACKEND LAYER                          │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              API Gateway / Load Balancer              │  │
│  └──────────────────────────────────────────────────────┘  │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐    │
│  │    Auth      │  │   Products   │  │    Orders    │    │
│  │   Service    │  │   Service    │  │   Service    │    │
│  └──────────────┘  └──────────────┘  └──────────────┘    │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐    │
│  │   Payment    │  │     User     │  │    Email     │    │
│  │   Service    │  │   Service    │  │   Service    │    │
│  └──────────────┘  └──────────────┘  └──────────────┘    │
│         Spring Boot + Java 21                               │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                    PERSISTENCE LAYER                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │  PostgreSQL  │  │     Redis    │  │   S3/Blob    │     │
│  │   Database   │  │    Cache     │  │   Storage    │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                  EXTERNAL SERVICES                           │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │    Stripe    │  │   SendGrid   │  │   Cloudinary │     │
│  │   Payment    │  │    Email     │  │    Images    │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
└─────────────────────────────────────────────────────────────┘
```

### 4.2 Capas de la Aplicación

#### Backend (Spring Boot)
```
com.citrimac.ecommerce/
├── config/                 # Configuraciones (Security, CORS, etc.)
├── controller/             # REST Controllers
│   ├── AuthController.java
│   ├── ProductController.java
│   ├── OrderController.java
│   ├── UserController.java
│   └── AdminController.java
├── service/               # Lógica de negocio
│   ├── AuthService.java
│   ├── ProductService.java
│   ├── OrderService.java
│   ├── PaymentService.java
│   └── EmailService.java
├── repository/            # Acceso a datos (JPA)
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   ├── OrderRepository.java
│   └── CategoryRepository.java
├── entity/               # Entidades JPA
│   ├── User.java
│   ├── Product.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── Category.java
│   ├── Payment.java
│   └── Address.java
├── dto/                  # Data Transfer Objects
│   ├── request/
│   └── response/
├── security/             # Configuración de seguridad
│   ├── JwtTokenProvider.java
│   ├── UserDetailsServiceImpl.java
│   └── SecurityConfig.java
├── exception/            # Manejo de excepciones
│   └── GlobalExceptionHandler.java
└── util/                 # Utilidades
    └── Constants.java
```

#### Frontend (React/Vue)
```
citrimac-frontend/
├── public/               # Archivos estáticos
├── src/
│   ├── components/      # Componentes reutilizables
│   │   ├── common/      # Botones, inputs, modals
│   │   ├── layout/      # Header, Footer, Sidebar
│   │   └── product/     # ProductCard, ProductList
│   ├── pages/           # Páginas principales
│   │   ├── Landing.jsx
│   │   ├── Login.jsx
│   │   ├── Register.jsx
│   │   ├── Products.jsx
│   │   ├── ProductDetail.jsx
│   │   ├── Cart.jsx
│   │   ├── Checkout.jsx
│   │   ├── UserDashboard.jsx
│   │   └── AdminDashboard.jsx
│   ├── services/        # API calls
│   │   ├── api.js
│   │   ├── authService.js
│   │   ├── productService.js
│   │   └── orderService.js
│   ├── context/         # Context API (State Management)
│   │   ├── AuthContext.jsx
│   │   └── CartContext.jsx
│   ├── hooks/           # Custom hooks
│   ├── utils/           # Funciones utilitarias
│   ├── styles/          # CSS/SCSS
│   └── App.jsx
```

---

## 5. Modelo de Datos

### 5.1 Diagrama de Entidad-Relación

```
┌──────────────┐         ┌──────────────┐         ┌──────────────┐
│    User      │         │   Address    │         │    Order     │
├──────────────┤         ├──────────────┤         ├──────────────┤
│ id (PK)      │────────<│ id (PK)      │         │ id (PK)      │
│ firstName    │    1:N  │ userId (FK)  │    1:N  │ userId (FK)  │──┐
│ lastName     │         │ street       │<────────│ addressId(FK)│  │
│ email (UK)   │         │ city         │         │ orderNumber  │  │
│ password     │         │ state        │         │ status       │  │
│ phone        │         │ zipCode      │         │ totalAmount  │  │
│ role         │         │ country      │         │ createdAt    │  │
│ active       │         │ isDefault    │         │ updatedAt    │  │
│ createdAt    │         └──────────────┘         └──────────────┘  │
│ updatedAt    │                                           │         │
└──────────────┘                                           │         │
                                                           │ 1       │
                                                           │         │
                                                           ▼ N       │
                                                  ┌──────────────┐  │
                                                  │  OrderItem   │  │
                                                  ├──────────────┤  │
                                                  │ id (PK)      │  │
                                                  │ orderId (FK) │──┘
                                                  │ productId(FK)│──┐
                                                  │ quantity     │  │
                                                  │ price        │  │
                                                  │ subtotal     │  │
                                                  └──────────────┘  │
                                                           │         │
                                                           │ N       │
                                                           ▼ 1       │
┌──────────────┐         ┌──────────────┐         ┌──────────────┐  │
│  Category    │         │   Product    │         │  ProductImage│  │
├──────────────┤    1:N  ├──────────────┤    1:N  ├──────────────┤  │
│ id (PK)      │────────>│ id (PK)      │<────────│ id (PK)      │  │
│ name         │         │ categoryId(FK)│<────────│ productId(FK)│──┘
│ description  │         │ name         │         │ imageUrl     │
│ imageUrl     │         │ description  │         │ isPrimary    │
│ active       │         │ price        │         └──────────────┘
└──────────────┘         │ stock        │
                         │ unit         │
                         │ sku          │         ┌──────────────┐
                         │ weight       │    1:1  │   Payment    │
                         │ nutritionInfo│<────────├──────────────┤
                         │ active       │         │ id (PK)      │
                         │ createdAt    │         │ orderId (FK) │
                         │ updatedAt    │         │ amount       │
                         └──────────────┘         │ method       │
                                                  │ status       │
                                                  │ transactionId│
                                                  │ createdAt    │
                                                  └──────────────┘
```

### 5.2 Descripción de Entidades

#### **User** (Usuario)
```java
- id: Long (PK, Auto-increment)
- firstName: String (not null, 2-50 chars)
- lastName: String (not null, 2-50 chars)
- email: String (not null, unique, valid email)
- password: String (not null, hashed)
- phone: String (max 15 chars)
- role: Enum (USER, ADMIN)
- active: Boolean (default true)
- createdAt: Timestamp
- updatedAt: Timestamp
```

#### **Product** (Producto)
```java
- id: Long (PK, Auto-increment)
- categoryId: Long (FK -> Category)
- name: String (not null)
- description: Text
- price: BigDecimal (not null, >0)
- stock: Integer (not null, >=0)
- unit: String (kg, unidad, manojo, etc.)
- sku: String (unique)
- weight: BigDecimal
- nutritionInfo: JSON/Text (opcional)
- active: Boolean (default true)
- featured: Boolean (default false)
- discount: Integer (0-100)
- createdAt: Timestamp
- updatedAt: Timestamp
```

#### **Category** (Categoría)
```java
- id: Long (PK, Auto-increment)
- name: String (not null, unique)
- description: Text
- imageUrl: String
- slug: String (unique)
- active: Boolean (default true)
- displayOrder: Integer
```

#### **Order** (Pedido)
```java
- id: Long (PK, Auto-increment)
- userId: Long (FK -> User)
- addressId: Long (FK -> Address)
- orderNumber: String (unique, auto-generated)
- status: Enum (PENDING, CONFIRMED, PROCESSING, SHIPPED, DELIVERED, CANCELLED)
- totalAmount: BigDecimal (not null)
- subtotal: BigDecimal
- shippingCost: BigDecimal
- tax: BigDecimal
- notes: Text
- createdAt: Timestamp
- updatedAt: Timestamp
```

#### **OrderItem** (Ítem de Pedido)
```java
- id: Long (PK, Auto-increment)
- orderId: Long (FK -> Order)
- productId: Long (FK -> Product)
- quantity: Integer (not null, >0)
- price: BigDecimal (precio al momento de la compra)
- subtotal: BigDecimal (quantity * price)
```

#### **Payment** (Pago)
```java
- id: Long (PK, Auto-increment)
- orderId: Long (FK -> Order, unique)
- amount: BigDecimal (not null)
- method: Enum (CREDIT_CARD, DEBIT_CARD, PAYPAL, BANK_TRANSFER)
- status: Enum (PENDING, COMPLETED, FAILED, REFUNDED)
- transactionId: String (payment gateway transaction ID)
- metadata: JSON (información adicional del gateway)
- createdAt: Timestamp
```

#### **Address** (Dirección)
```java
- id: Long (PK, Auto-increment)
- userId: Long (FK -> User)
- recipientName: String
- phone: String
- street: String (not null)
- city: String (not null)
- state: String (not null)
- zipCode: String (not null)
- country: String (not null)
- isDefault: Boolean (default false)
```

#### **ProductImage** (Imagen de Producto)
```java
- id: Long (PK, Auto-increment)
- productId: Long (FK -> Product)
- imageUrl: String (not null)
- isPrimary: Boolean (default false)
- displayOrder: Integer
```

---

## 6. Módulos y Funcionalidades

### 6.1 Landing Page

#### Funcionalidades
- **Hero Section**
  - Banner principal con CTA (Call To Action)
  - Imagen atractiva de frutas y verduras frescas
  - Botón "Comprar Ahora" / "Ver Productos"
  
- **Productos Destacados**
  - Carrusel con productos en oferta
  - Tarjetas de producto con imagen, nombre y precio
  - Botón "Agregar al Carrito" rápido

- **Sección de Beneficios**
  - Entrega rápida
  - Productos frescos garantizados
  - Precios competitivos
  - Iconos ilustrativos

- **Cómo Funciona**
  - Pasos del proceso de compra (Elige → Compra → Recibe)
  
- **Testimonios**
  - Opiniones de clientes satisfechos
  - Sistema de calificación (estrellas)

- **Newsletter**
  - Formulario de suscripción
  - Beneficios de suscribirse

- **Footer**
  - Enlaces rápidos
  - Redes sociales
  - Información de contacto
  - Métodos de pago aceptados

### 6.2 Sistema de Autenticación

#### Registro de Usuario
```
Campos del Formulario:
- Nombre
- Apellido
- Email
- Teléfono
- Contraseña (con validación de fortaleza)
- Confirmar contraseña
- Aceptar términos y condiciones
```

**Validaciones:**
- Email único en el sistema
- Contraseña: mínimo 8 caracteres, mayúsculas, minúsculas, números
- Verificación de email mediante token enviado por correo

#### Login
```
Campos del Formulario:
- Email
- Contraseña
- Recordarme (opcional)
```

**Características:**
- Autenticación mediante JWT
- Sesión persistente con refresh tokens
- Protección contra fuerza bruta (rate limiting)

#### Recuperación de Contraseña
- Solicitud mediante email
- Token temporal de recuperación
- Formulario de nueva contraseña

### 6.3 Catálogo de Productos

#### Vista de Listado
**Layout:**
- Grid responsive (4 columnas desktop, 2 tablet, 1 móvil)
- Tarjetas de producto con:
  - Imagen principal
  - Nombre del producto
  - Precio
  - Unidad de medida
  - Badge de "Nuevo" u "Oferta"
  - Stock disponible
  - Botón "Agregar al Carrito"
  - Botón "Ver Detalles"

**Filtros y Búsqueda:**
- Barra de búsqueda con autocompletado
- Filtro por categoría (frutas, verduras, orgánicos, etc.)
- Filtro por rango de precio
- Filtro por disponibilidad
- Ordenamiento: más vendidos, precio (menor-mayor), nuevos

**Paginación:**
- 12/24/48 productos por página
- Paginación numérica o scroll infinito

#### Vista de Detalle
**Información del Producto:**
- Galería de imágenes (con zoom)
- Nombre del producto
- Precio actual y precio anterior (si hay descuento)
- Descripción detallada
- Información nutricional
- Origen/Productor
- Peso/Unidad de medida
- Stock disponible
- Selector de cantidad
- Botón "Agregar al Carrito"
- Botón "Agregar a Favoritos"

**Productos Relacionados:**
- Carrusel de productos similares

### 6.4 Carrito de Compras

#### Funcionalidades
- **Vista de Carrito:**
  - Listado de productos agregados
  - Imagen miniatura de cada producto
  - Nombre y características
  - Precio unitario
  - Selector de cantidad (+/-)
  - Subtotal por producto
  - Botón eliminar producto
  
- **Resumen:**
  - Subtotal de productos
  - Costo de envío
  - Descuentos aplicados
  - Total a pagar
  - Botón "Proceder al Checkout"

- **Persistencia:**
  - Para usuarios logueados: guardado en base de datos
  - Para usuarios anónimos: localStorage con opción de migrar al login

### 6.5 Proceso de Checkout

#### Paso 1: Dirección de Entrega
- Selección de dirección guardada o nueva
- Formulario de dirección completo
- Validación de campos
- Opción "Guardar esta dirección"

#### Paso 2: Método de Envío
- Opciones de envío:
  - Envío estándar (2-3 días)
  - Envío express (24 horas)
  - Retiro en tienda
- Costo de envío calculado dinámicamente

#### Paso 3: Método de Pago
- Selección de método:
  - Tarjeta de crédito/débito
  - PayPal
  - Transferencia bancaria
- Formulario de pago integrado (Stripe Elements)
- Opción "Guardar método de pago"

#### Paso 4: Revisión y Confirmación
- Resumen completo del pedido
- Dirección de entrega
- Método de pago seleccionado
- Desglose de costos
- Botón "Confirmar Pedido"

#### Paso 5: Confirmación
- Mensaje de éxito
- Número de orden generado
- Resumen del pedido
- Email de confirmación enviado
- Botón "Ver Mi Pedido"

### 6.6 Dashboard de Usuario

#### Menú Lateral
- Mi Perfil
- Mis Pedidos
- Direcciones
- Métodos de Pago
- Lista de Deseos
- Configuración
- Cerrar Sesión

#### Sección: Mi Perfil
- Información personal editable
- Cambio de contraseña
- Preferencias de notificaciones

#### Sección: Mis Pedidos
**Lista de Pedidos:**
- Tabla con pedidos ordenados por fecha
- Columnas:
  - Número de orden
  - Fecha
  - Total
  - Estado (con badge de color)
  - Acciones (Ver detalle, Descargar factura)

**Detalle de Pedido:**
- Información completa del pedido
- Productos comprados
- Dirección de entrega
- Método de pago
- Timeline de estados
- Número de seguimiento (si aplica)

#### Sección: Direcciones
- Lista de direcciones guardadas
- Marcar dirección como predeterminada
- Editar/Eliminar direcciones
- Agregar nueva dirección

#### Sección: Lista de Deseos
- Grid de productos favoritos
- Botón rápido "Agregar al Carrito"
- Notificaciones de cambios de precio

### 6.7 Dashboard de Administrador

#### Panel Principal (Overview)
**Métricas en Tiempo Real:**
- Ventas del día/semana/mes
- Número de pedidos pendientes
- Productos con stock bajo
- Nuevos usuarios registrados
- Gráficos de ventas (líneas/barras)

#### Gestión de Productos
**Lista de Productos:**
- Tabla con todos los productos
- Columnas:
  - Imagen
  - SKU
  - Nombre
  - Categoría
  - Precio
  - Stock
  - Estado (Activo/Inactivo)
  - Acciones (Editar, Eliminar, Clonar)
- Búsqueda y filtros avanzados
- Botón "Agregar Producto"

**Formulario de Producto:**
- Información básica
- Imágenes (drag & drop, múltiples)
- Precio y descuentos
- Inventario (stock, SKU)
- SEO (meta description, keywords)
- Categoría y tags
- Información nutricional (opcional)

#### Gestión de Categorías
- Lista de categorías
- Orden de visualización (drag & drop)
- Agregar/Editar/Eliminar categorías
- Asignar imagen a categoría

#### Gestión de Pedidos
**Lista de Pedidos:**
- Tabla con todos los pedidos
- Filtros por estado, fecha, monto
- Búsqueda por número de orden o cliente
- Columnas:
  - Número de orden
  - Cliente
  - Fecha
  - Total
  - Estado
  - Acciones

**Detalle de Pedido:**
- Información completa del pedido
- Datos del cliente
- Productos del pedido
- Historial de cambios de estado
- Botones de acción:
  - Cambiar estado
  - Imprimir factura
  - Enviar notificación
  - Cancelar pedido (con razón)

#### Gestión de Usuarios
- Lista de usuarios registrados
- Búsqueda y filtros
- Ver detalles de usuario
- Activar/Desactivar usuarios
- Historial de compras por usuario

#### Reportes y Estadísticas
**Reportes Disponibles:**
- Ventas por período
- Productos más vendidos
- Clientes frecuentes
- Análisis de inventario
- Reportes de ingresos
- Exportación a CSV/Excel/PDF

**Gráficos:**
- Ventas en el tiempo (líneas)
- Distribución por categoría (pastel)
- Comparativas mes a mes (barras)

#### Configuración del Sistema
- Configuración general de la tienda
- Métodos de pago
- Opciones de envío
- Impuestos
- Notificaciones por email
- Integración con pasarelas de pago

---

## 7. Diseño UX/UI

### 7.1 Principios de Diseño

#### Usabilidad
- **Simplicidad:** Interfaz limpia y sin elementos innecesarios
- **Consistencia:** Componentes y patrones reutilizables
- **Retroalimentación:** Confirmaciones visuales de acciones
- **Prevención de errores:** Validaciones en tiempo real
- **Accesibilidad:** WCAG 2.1 nivel AA

#### Diseño Visual
- **Paleta de Colores:**
  - Principal: Verde (#4CAF50) - Representa frescura y naturaleza
  - Secundario: Naranja (#FF9800) - Cítricos y energía
  - Acento: Rojo (#F44336) - Llamados a la acción
  - Neutros: Grises para textos y fondos
  
- **Tipografía:**
  - Headings: Poppins (Bold, SemiBold)
  - Body: Roboto (Regular, Medium)
  - Tamaño base: 16px
  - Escala modular para jerarquía

- **Iconografía:**
  - Línea de iconos consistente (Material Icons o FontAwesome)
  - Tamaños: 24px (estándar), 48px (destacados)

- **Espaciado:**
  - Sistema de 8px (8, 16, 24, 32, 48, 64)
  - Márgenes y paddings consistentes

### 7.2 Componentes Clave

#### Navegación Principal
- Logo de Citrimac (izquierda)
- Barra de búsqueda (centro)
- Iconos: Cuenta, Favoritos, Carrito (derecha)
- Menú de categorías (sticky)

#### Tarjeta de Producto
- Imagen responsive con hover effect
- Badge de oferta/nuevo
- Información clara de precio
- Botón CTA prominente
- Indicador de stock

#### Formularios
- Labels claros
- Validación en tiempo real
- Mensajes de error descriptivos
- Estados de carga (spinners)
- Confirmaciones de éxito

#### Modales
- Confirmaciones de acciones
- Vista rápida de producto
- Login/Registro rápido

### 7.3 Responsive Design

#### Breakpoints
- Mobile: < 768px
- Tablet: 768px - 1024px
- Desktop: > 1024px
- Large Desktop: > 1440px

#### Adaptaciones Mobile
- Menú hamburguesa
- Grid de 1 columna para productos
- Formularios apilados verticalmente
- Botones táctiles de tamaño adecuado (min 44x44px)
- Carrito como drawer lateral

---

## 8. Stack Tecnológico

### 8.1 Backend

#### Core
- **Java 21** - Lenguaje de programación
- **Spring Boot 3.5.7** - Framework principal
- **Spring Data JPA** - ORM y persistencia
- **Spring Security** - Autenticación y autorización
- **Spring Validation** - Validación de datos

#### Base de Datos
- **PostgreSQL 15** - Base de datos principal
- **Redis** - Cache y sesiones
- **Flyway/Liquibase** - Migraciones de BD

#### Seguridad
- **JWT (JSON Web Tokens)** - Autenticación stateless
- **BCrypt** - Hash de contraseñas
- **Spring Security OAuth2** - OAuth social login

#### APIs Externas
- **Stripe API** - Pasarela de pagos
- **SendGrid/Mailgun** - Envío de emails
- **Cloudinary** - Almacenamiento de imágenes

#### Testing
- **JUnit 5** - Testing unitario
- **Mockito** - Mocking
- **TestContainers** - Testing de integración
- **REST Assured** - Testing de API

#### Herramientas
- **Maven** - Gestión de dependencias
- **Lombok** - Reducción de boilerplate
- **MapStruct** - Mapping de DTOs
- **Swagger/OpenAPI** - Documentación de API

### 8.2 Frontend

#### Core
- **React 18** o **Vue 3** - Framework JavaScript
- **TypeScript** - Tipado estático
- **Vite** - Build tool y dev server

#### Gestión de Estado
- **Redux Toolkit** (React) o **Pinia** (Vue)
- **React Query/TanStack Query** - Cache de datos

#### UI Framework
- **Material-UI** o **Ant Design** - Componentes UI
- **Tailwind CSS** - Utility-first CSS
- **Styled Components** - CSS-in-JS (opcional)

#### Formularios
- **React Hook Form** o **Formik** - Gestión de formularios
- **Yup** o **Zod** - Validación de esquemas

#### HTTP Client
- **Axios** - Cliente HTTP con interceptores

#### Routing
- **React Router 6** o **Vue Router 4**

#### Testing
- **Jest** - Testing framework
- **React Testing Library** - Testing de componentes
- **Cypress** - E2E testing

### 8.3 DevOps

#### Contenedores
- **Docker** - Containerización
- **Docker Compose** - Orquestación local

#### CI/CD
- **GitHub Actions** - Integración continua
- **ArgoCD** (opcional) - Despliegue continuo

#### Hosting
- **AWS/GCP/Azure** - Cloud provider
  - Backend: EC2/App Engine/App Service
  - Database: RDS/Cloud SQL/Azure Database
  - Storage: S3/Cloud Storage/Blob Storage
- **Vercel/Netlify** - Frontend (alternativa)

#### Monitoreo
- **Prometheus + Grafana** - Métricas
- **ELK Stack** - Logs centralizados
- **Sentry** - Error tracking

---

## 9. Fases de Desarrollo

### Fase 1: Configuración Inicial (Semana 1)
**Objetivos:**
- Configurar repositorios (backend y frontend)
- Setup de entorno de desarrollo
- Configuración de CI/CD básico
- Diseño de base de datos

**Entregables:**
- Repositorios configurados
- Docker Compose para desarrollo local
- Diagrama de base de datos finalizado
- README con instrucciones de setup

### Fase 2: Backend - Core (Semanas 2-4)
**Objetivos:**
- Implementar entidades y repositorios
- Sistema de autenticación (JWT)
- CRUD de usuarios
- CRUD de productos
- CRUD de categorías
- Validaciones y manejo de errores

**Entregables:**
- API de autenticación funcional
- API de productos funcional
- Tests unitarios (>70% coverage)
- Documentación OpenAPI/Swagger

### Fase 3: Backend - Sistema de Pedidos (Semanas 5-6)
**Objetivos:**
- Implementar carrito de compras
- Sistema de pedidos
- Cálculo de totales y envío
- Estados de pedidos

**Entregables:**
- API de carrito funcional
- API de pedidos funcional
- Tests de integración
- Documentación actualizada

### Fase 4: Integración de Pagos (Semana 7)
**Objetivos:**
- Integrar Stripe/PayPal
- Webhooks para confirmación de pagos
- Manejo de errores de pago
- Generación de comprobantes

**Entregables:**
- Integración de pasarela funcional
- Documentación de flujo de pagos
- Tests de integración con Stripe Test Mode

### Fase 5: Frontend - Landing y Autenticación (Semanas 8-9)
**Objetivos:**
- Diseño UI/UX completo (Figma/Adobe XD)
- Landing page responsive
- Páginas de login/registro
- Recuperación de contraseña

**Entregables:**
- Diseños en Figma aprobados
- Landing page implementada
- Sistema de autenticación funcional en frontend
- Responsive design validado

### Fase 6: Frontend - Catálogo y Carrito (Semanas 10-11)
**Objetivos:**
- Página de listado de productos
- Página de detalle de producto
- Funcionalidad de búsqueda y filtros
- Carrito de compras
- Persistencia de carrito

**Entregables:**
- Catálogo de productos funcional
- Carrito de compras operativo
- Integración completa con backend

### Fase 7: Frontend - Checkout y Dashboard Usuario (Semanas 12-13)
**Objetivos:**
- Proceso de checkout multi-paso
- Integración de formulario de pago
- Dashboard de usuario
- Historial de pedidos
- Gestión de perfil y direcciones

**Entregables:**
- Flujo de compra completo funcional
- Dashboard de usuario operativo
- Tests E2E del flujo de compra

### Fase 8: Frontend - Dashboard Admin (Semanas 14-15)
**Objetivos:**
- Panel de métricas y estadísticas
- Gestión de productos (CRUD completo)
- Gestión de pedidos
- Gestión de usuarios
- Generación de reportes

**Entregables:**
- Dashboard administrativo completo
- Todas las funcionalidades CRUD operativas
- Reportes y gráficos funcionales

### Fase 9: Sistema de Notificaciones (Semana 16)
**Objetivos:**
- Templates de emails
- Notificaciones de registro
- Notificaciones de pedidos
- Notificaciones de cambios de estado

**Entregables:**
- Sistema de emails funcional
- Templates diseñados y probados
- Integración con SendGrid/Mailgun

### Fase 10: Testing y QA (Semanas 17-18)
**Objetivos:**
- Testing completo de todas las funcionalidades
- Pruebas de carga y rendimiento
- Pruebas de seguridad
- Corrección de bugs
- Optimización de rendimiento

**Entregables:**
- Reporte de testing completo
- Bugs críticos resueltos
- Métricas de rendimiento aceptables

### Fase 11: Despliegue y Documentación (Semana 19)
**Objetivos:**
- Configuración de servidores de producción
- Despliegue de backend y frontend
- Configuración de dominio y SSL
- Documentación de usuario final
- Documentación técnica completa

**Entregables:**
- Aplicación en producción
- Dominio configurado (citrimac.com)
- Manuales de usuario
- Documentación técnica

### Fase 12: Capacitación y Lanzamiento (Semana 20)
**Objetivos:**
- Capacitación de administradores
- Carga inicial de productos
- Marketing de lanzamiento
- Monitoreo post-lanzamiento

**Entregables:**
- Administradores capacitados
- Catálogo inicial cargado
- Aplicación en vivo
- Plan de monitoreo activo

---

## 10. Cronograma Estimado

### Timeline General: 20 Semanas (~5 Meses)

```
Mes 1 (Semanas 1-4):
├── Semana 1: Configuración inicial y diseño de BD
├── Semana 2: Backend - Autenticación y usuarios
├── Semana 3: Backend - Productos y categorías
└── Semana 4: Backend - Continuación y testing

Mes 2 (Semanas 5-8):
├── Semana 5: Backend - Sistema de pedidos (Parte 1)
├── Semana 6: Backend - Sistema de pedidos (Parte 2)
├── Semana 7: Backend - Integración de pagos
└── Semana 8: Frontend - Diseño UI/UX y Landing

Mes 3 (Semanas 9-12):
├── Semana 9: Frontend - Autenticación
├── Semana 10: Frontend - Catálogo de productos
├── Semana 11: Frontend - Carrito de compras
└── Semana 12: Frontend - Checkout (Parte 1)

Mes 4 (Semanas 13-16):
├── Semana 13: Frontend - Checkout (Parte 2) y Dashboard Usuario
├── Semana 14: Frontend - Dashboard Admin (Parte 1)
├── Semana 15: Frontend - Dashboard Admin (Parte 2)
└── Semana 16: Sistema de notificaciones

Mes 5 (Semanas 17-20):
├── Semana 17: Testing y QA (Parte 1)
├── Semana 18: Testing y QA (Parte 2)
├── Semana 19: Despliegue y documentación
└── Semana 20: Capacitación y lanzamiento
```

### Distribución de Esfuerzo por Rol

| Rol                    | Semanas | % del Proyecto |
|------------------------|---------|----------------|
| Backend Developer      | 10      | 40%            |
| Frontend Developer     | 12      | 45%            |
| UI/UX Designer         | 4       | 10%            |
| QA Engineer            | 3       | 10%            |
| DevOps Engineer        | 2       | 5%             |
| Project Manager        | 20      | 20%            |

**Nota:** Los porcentajes pueden superponerse ya que algunos roles trabajan en paralelo.

---

## 11. Riesgos y Mitigaciones

### Riesgos Técnicos

#### 1. Integración de Pasarela de Pagos
**Riesgo:** Complejidad en la integración, problemas con webhooks, manejo de errores.
**Probabilidad:** Media
**Impacto:** Alto
**Mitigación:**
- Usar SDK oficial de Stripe/PayPal
- Implementar modo de prueba exhaustivo
- Documentar todos los casos de error
- Tener plan B con pasarela alternativa

#### 2. Rendimiento con Múltiples Usuarios Concurrentes
**Riesgo:** Lentitud o caídas del sistema bajo carga.
**Probabilidad:** Media
**Impacto:** Alto
**Mitigación:**
- Implementar caching con Redis
- Optimizar queries de base de datos
- Realizar pruebas de carga
- Configurar auto-scaling en cloud

#### 3. Seguridad de Datos de Usuarios y Pagos
**Riesgo:** Vulnerabilidades de seguridad, filtración de datos.
**Probabilidad:** Baja
**Impacto:** Crítico
**Mitigación:**
- Seguir mejores prácticas de seguridad (OWASP)
- Auditorías de seguridad
- Encriptación de datos sensibles
- Cumplimiento PCI-DSS para pagos
- Tests de penetración

#### 4. Problemas de Inventario (Stock)
**Riesgo:** Venta de productos sin stock, inconsistencias.
**Probabilidad:** Media
**Impacto:** Medio
**Mitigación:**
- Transacciones atómicas para reserva de stock
- Sistema de alertas de stock bajo
- Reconciliación periódica de inventario

### Riesgos de Proyecto

#### 5. Retrasos en el Cronograma
**Riesgo:** Subestimación de complejidad, cambios en alcance.
**Probabilidad:** Alta
**Impacto:** Medio
**Mitigación:**
- Buffer de tiempo del 20% en cada fase
- Sprints ágiles con revisiones semanales
- Priorización clara de funcionalidades (MVP primero)
- Comunicación constante con stakeholders

#### 6. Cambios de Requerimientos
**Riesgo:** Solicitudes de nuevas funcionalidades durante desarrollo.
**Probabilidad:** Media
**Impacto:** Medio
**Mitigación:**
- Proceso formal de gestión de cambios
- Evaluación de impacto en cronograma y costo
- Documentación clara de alcance inicial
- Fases de desarrollo claramente definidas

#### 7. Disponibilidad de Recursos
**Riesgo:** Falta de desarrolladores o diseñadores disponibles.
**Probabilidad:** Baja
**Impacto:** Alto
**Mitigación:**
- Contratar equipo con anticipación
- Documentación exhaustiva para facilitar onboarding
- Code reviews y pair programming
- Transferencia de conocimiento continua

### Riesgos de Negocio

#### 8. Adopción por Parte de Usuarios
**Riesgo:** Baja adopción inicial de la plataforma.
**Probabilidad:** Media
**Impacto:** Alto
**Mitigación:**
- Investigación de mercado previa
- UX/UI intuitivo y atractivo
- Estrategia de marketing pre-lanzamiento
- Programa de incentivos iniciales
- Beta testing con usuarios reales

#### 9. Competencia
**Riesgo:** Plataformas similares en el mercado.
**Probabilidad:** Alta
**Impacto:** Medio
**Mitigación:**
- Diferenciación clara (frescura garantizada, rapidez)
- Calidad del producto y servicio
- Precios competitivos
- Programa de fidelización

---

## 12. Métricas de Éxito

### KPIs Técnicos
- **Disponibilidad:** 99.5% uptime
- **Tiempo de respuesta:** <300ms para el 95% de requests
- **Cobertura de tests:** >80% backend, >70% frontend
- **Bugs críticos:** 0 en producción
- **Tiempo de carga:** <3 segundos (página inicial)

### KPIs de Negocio
- **Tasa de conversión:** >2% (visitantes → compradores)
- **Carrito abandonado:** <60%
- **Valor promedio de orden:** Definir objetivo según mercado
- **Usuarios registrados:** Objetivo mensual
- **Tasa de retención:** >30% mes a mes
- **NPS (Net Promoter Score):** >50

### KPIs de Usuario
- **Satisfacción:** >4.5/5 estrellas
- **Tiempo promedio de compra:** <5 minutos
- **Tasa de error en checkout:** <1%

---

## 13. Consideraciones Adicionales

### Escalabilidad Futura
- Integración con proveedores (API B2B)
- App móvil nativa (iOS/Android)
- Sistema de suscripciones recurrentes
- Programa de referidos
- Marketplace (múltiples vendedores)
- IA para recomendaciones personalizadas
- Chatbot de atención al cliente

### Cumplimiento Legal
- **GDPR/LOPD:** Protección de datos personales
- **Términos y Condiciones:** Claros y accesibles
- **Política de Privacidad**
- **Política de Cookies**
- **Política de Devoluciones**
- **Facturación Electrónica:** Cumplimiento fiscal

### Sostenibilidad
- Opción de empaques ecológicos
- Compensación de huella de carbono
- Alianza con productores locales
- Reducción de desperdicio

---

## 14. Contacto y Equipo

### Equipo Recomendado

| Rol                    | Cantidad | Responsabilidades                           |
|------------------------|----------|---------------------------------------------|
| Project Manager        | 1        | Coordinación, cronograma, comunicación      |
| Backend Developer      | 2        | API REST, base de datos, lógica de negocio |
| Frontend Developer     | 2        | Interfaces de usuario, integración de API   |
| UI/UX Designer         | 1        | Diseño visual, experiencia de usuario       |
| QA Engineer            | 1        | Testing, control de calidad                 |
| DevOps Engineer        | 1        | Infraestructura, CI/CD, monitoreo           |

**Total: 8 personas**

---

## 15. Presupuesto Estimado

### Desarrollo (5 meses)

| Concepto                      | Costo Estimado     |
|-------------------------------|--------------------|
| Equipo de Desarrollo          | $80,000 - $120,000 |
| Infraestructura (Cloud)       | $500 - $1,000/mes  |
| Licencias y Herramientas      | $1,000 - $2,000    |
| Servicios Externos (APIs)     | $500/mes           |
| Diseño UI/UX                  | $5,000 - $10,000   |
| Testing y QA                  | $5,000 - $8,000    |
| Contingencia (20%)            | $18,000 - $28,000  |
| **Total Estimado**            | **$110,000 - $170,000** |

### Operación Mensual (Post-lanzamiento)

| Concepto                      | Costo Mensual      |
|-------------------------------|--------------------|
| Hosting y Cloud               | $500 - $1,500      |
| Servicios de Email            | $100 - $300        |
| Pasarela de Pagos (comisión)  | 2.9% + $0.30/transacción |
| Mantenimiento y Soporte       | $2,000 - $5,000    |
| Marketing                     | Variable           |
| **Total Mensual**             | **$2,600 - $7,100+** |

**Nota:** Los costos son estimados y pueden variar según ubicación geográfica, nivel de experiencia del equipo y alcance específico.

---

## 16. Conclusión

Este plan detalla una hoja de ruta completa para el desarrollo de **Citrimac**, una plataforma e-commerce moderna y escalable para la venta de frutas y verduras. El proyecto está estructurado en fases claras con entregables específicos, utilizando tecnologías actuales y mejores prácticas de desarrollo.

El éxito del proyecto dependerá de:
1. **Planificación rigurosa:** Seguir el cronograma y gestionar cambios apropiadamente
2. **Calidad técnica:** Tests exhaustivos, código limpio, arquitectura sólida
3. **Experiencia de usuario:** Diseño intuitivo y atractivo
4. **Seguridad:** Protección de datos y transacciones seguras
5. **Iteración continua:** Feedback de usuarios y mejoras constantes

Con este plan como guía, el equipo de desarrollo tendrá claridad sobre los objetivos, alcance, tecnologías y pasos necesarios para llevar **Citrimac** desde la concepción hasta el lanzamiento exitoso.

---

## 📞 Próximos Pasos

1. **Validar y aprobar este plan** con todos los stakeholders
2. **Formar el equipo de desarrollo** con los roles especificados
3. **Configurar el entorno de desarrollo** (Fase 1)
4. **Crear prototipos de diseño** para validación temprana
5. **Iniciar Sprint 1** de desarrollo backend

---

**Documento creado:** Enero 2026  
**Versión:** 1.0  
**Proyecto:** Citrimac E-commerce Platform
