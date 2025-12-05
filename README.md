# 🛍️ E-Commerce Backend API

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge\&logo=springboot\&logoColor=white)
![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge\&logo=mysql\&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge\&logo=apachemaven\&logoColor=white)

> Una **API RESTful robusta y escalable** diseñada para potenciar plataformas modernas de comercio electrónico. Construida con **Spring Boot 3**, seguridad con **JWT**, verificación OTP y un ecosistema completo multi-rol (Cliente, Vendedor y Administrador).

---

## 🚀 Visión General

Este backend no es solo un catálogo y un carrito: es un **ecosistema E-Commerce completo** con autenticación avanzada, manejo de pedidos, reportes de ventas y un sistema extensible pensado para escalar.

La arquitectura modular y limpia facilita mantenimiento, nuevas features y soporte a múltiples interfaces (web, móvil, marketplaces externos, etc.).

---

## ✨ Características Principales

### 🔐 Seguridad y Autenticación

* **JWT (JSON Web Token):** Autenticación sin estado con acceso seguro.
  *Fuente: `JwtProvider.java`*
* **OTP vía Email:** Registro/login mediante códigos temporales enviados por correo.
  *Fuente: `AuthServiceImpl.java`*
* **RBAC (Control de Acceso por Roles):** Roles soportados: `ROLE_CUSTOMER`, `ROLE_SELLER`, `ROLE_ADMIN`.
  *Fuente: `AppConfig.java`*

---

### 🛒 Experiencia de Compra

* Catálogo avanzado con soporte para **tallas**, **colores**, **categorías jerárquicas** e **imágenes múltiples**.
* **Carrito de Compras Persistente** y **Lista de Deseos**.
  *Fuentes: `Cart.java`, `WishList.java`*
* **Cupones inteligentes:** descuentos por porcentaje, montos mínimos y fechas.
* **Reseñas con fotos:** Calificaciones y evidencias visuales.

---

### 📦 Sistema de Pedidos y Pagos

* Flujo de órdenes: `PENDING → CONFIRMED → SHIPPED → DELIVERED → CANCELLED`.
  *Fuente: `OrderStatus.java`*
* Preparado para integración con **Stripe** y **Razorpay**.
  *Fuente: `PaymentMethod.java`*
* Gestión de direcciones, facturación y métodos de pago.

---

### 🏢 Panel de Vendedores

* Perfiles de vendedor con información fiscal, bancaria y direcciones de recolección.
  *Fuente: `Seller.java`*
* **Reportes de ventas:** ganancias, ventas totales, reembolsos y transacciones.
  *Fuente: `SellerReport.java`*

---

## 🛠️ Tecnologías y Arquitectura

| Componente              | Tecnología              |
| ----------------------- | ----------------------- |
| **Framework principal** | Spring Boot 3.4.5       |
| **Lenguaje**            | Java 21                 |
| **Base de datos**       | MySQL + Spring Data JPA |
| **Seguridad**           | Spring Security + JJWT  |
| **Email / OTP**         | JavaMailSender          |
| **Utilidades**          | Lombok, Validation API  |
| **Build Tool**          | Maven                   |

Arquitectura basada en **capas limpias**: Controller → Service → Repository → Model.

---

## 📂 Estructura del Proyecto

```bash
src/main/java/e_commerce/
├── config/          # Configuraciones de seguridad (JWT, CORS) y contexto
├── controller/      # Controladores REST (Entradas de la API)
├── domain/          # Enums, constantes y modelos de dominio
├── model/           # Entidades JPA mapeadas a la base de datos
├── repository/      # Acceso a datos (Spring Data JPA)
├── service/         # Interfaces de servicios
│   └── impl/        # Implementaciones de lógica de negocio
├── response/        # DTOs para respuestas (AuthResponse, etc.)
└── utils/           # Generación de OTP, helpers varios
```

---

## ⚙️ Configuración e Instalación

### 1️⃣ Prerrequisitos

* JDK **21+**
* MySQL Server
* Maven (opcional, incluye `mvnw` wrapper)

### 2️⃣ Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/backend-ecommerce.git
cd backend-ecommerce
```

### 3️⃣ Configurar credenciales (`application.properties`)

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=tu_usuario_mysql
spring.datasource.password=tu_password_mysql

# Email
spring.mail.host=smtp.gmail.com
spring.mail.username=tu_email@gmail.com
spring.mail.password=tu_password_de_aplicacion
```

> ⚠️ Cambiar valores por tus credenciales reales. No usar en producción sin variables de entorno.

### 4️⃣ Ejecutar la aplicación

**Windows:**

```bash
mvnw spring-boot:run
```

**Linux/Mac:**

```bash
./mvnw spring-boot:run
```

Servidor disponible en: **[http://localhost:3000](http://localhost:3000)**.

---

## 🔌 Endpoints Destacados

| Método | Endpoint                      | Descripción               | Rol      |
| ------ | ----------------------------- | ------------------------- | -------- |
| POST   | `/auth/signup`                | Registrar usuario + token | Público  |
| POST   | `/auth/sent/login-signup-otp` | Enviar OTP al correo      | Público  |
| GET    | `/api/products`               | Listar catálogo           | Público  |
| POST   | `/api/cart/add`               | Agregar al carrito        | Cliente  |
| POST   | `/api/orders/`                | Crear orden               | Cliente  |
| GET    | `/api/seller/report`          | Reporte de ventas         | Vendedor |

---

## 🤝 Contribución

1. Haz un **Fork** del repositorio.
2. Crea una rama: `git checkout -b feature/NuevaFeature`.
3. Realiza cambios y commits descriptivos.
4. Haz push: `git push origin feature/NuevaFeature`.
5. Abre un Pull Request.

¡Todas las contribuciones son bienvenidas!

---

## 📄 Licencia

Distribuido bajo la licencia **MIT**. Ver archivo `LICENSE` para detalles.

---

💛 **Desarrollado con pasión, Java y arquitectura limpia.**
