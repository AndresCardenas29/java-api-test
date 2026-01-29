# 🍎 CITRIMAC - Documentación del Proyecto

## Plataforma E-commerce de Frutas y Verduras

Bienvenido al proyecto **Citrimac**, una plataforma completa de comercio electrónico especializada en la venta de frutas y verduras frescas. Esta documentación proporciona todo lo necesario para desarrollar, implementar y lanzar la plataforma.

---

## 📚 Índice de Documentación

### 1. [CITRIMAC_PLANNING.md](./CITRIMAC_PLANNING.md) - Plan Maestro del Proyecto
**Tamaño:** 40KB | **Secciones:** 16

Este documento es la guía principal del proyecto. Contiene:

- ✅ **Resumen Ejecutivo:** Visión general del proyecto
- ✅ **Objetivos:** Metas generales y específicas
- ✅ **Alcance Completo:** 9 módulos principales detallados
- ✅ **Arquitectura del Sistema:** Diagramas y descripción de capas
- ✅ **Modelo de Datos:** Diagrama ER con 12 entidades
- ✅ **Funcionalidades por Módulo:** Especificaciones detalladas
- ✅ **Diseño UX/UI:** Principios y paleta de colores
- ✅ **Stack Tecnológico:** Todas las tecnologías a utilizar
- ✅ **Fases de Desarrollo:** 12 fases en 20 semanas
- ✅ **Cronograma:** Timeline mes a mes
- ✅ **Riesgos y Mitigaciones:** 9 riesgos analizados
- ✅ **KPIs:** Métricas de éxito
- ✅ **Presupuesto:** Estimación completa
- ✅ **Equipo:** Roles y responsabilidades

**¿Cuándo usar este documento?**
- Al inicio del proyecto para entender el alcance completo
- Para presentaciones a stakeholders
- Como referencia durante la planificación de sprints
- Para estimar recursos y presupuestos

---

### 2. [CITRIMAC_TECHNICAL_SPECS.md](./CITRIMAC_TECHNICAL_SPECS.md) - Especificaciones Técnicas
**Tamaño:** 32KB | **Endpoints:** 45+ | **Tablas:** 12

Este documento es para desarrolladores. Contiene:

- ✅ **API REST Endpoints:** Documentación completa con ejemplos
  - Autenticación y usuarios (7 endpoints)
  - Productos (6 endpoints)
  - Categorías (2 endpoints)
  - Carrito de compras (5 endpoints)
  - Pedidos (4 endpoints)
  - Direcciones (2 endpoints)
  - Pagos (2 endpoints)
  - Dashboard Admin (2 endpoints)
  
- ✅ **Base de Datos:**
  - Scripts SQL completos para crear todas las tablas
  - Índices optimizados
  - Relaciones y claves foráneas
  
- ✅ **Configuración de Seguridad:**
  - Spring Security setup
  - JWT implementation
  - CORS configuration
  
- ✅ **Integración de Stripe:**
  - Payment intents
  - Webhooks
  - Manejo de errores
  
- ✅ **Sistema de Caché:** Redis configuration
- ✅ **Manejo de Archivos:** Cloudinary integration
- ✅ **Variables de Entorno:** Configuración completa

**¿Cuándo usar este documento?**
- Durante la implementación del backend
- Para configurar la base de datos
- Al integrar la pasarela de pagos
- Como referencia para el frontend

---

### 3. [CITRIMAC_UX_UI_DESIGN.md](./CITRIMAC_UX_UI_DESIGN.md) - Diseño UX/UI
**Tamaño:** 61KB | **Wireframes:** 10 | **Flujos:** 3

Este documento es para diseñadores y desarrolladores frontend. Contiene:

- ✅ **Wireframes ASCII Detallados:**
  1. Landing Page
  2. Login/Registro
  3. Catálogo de Productos
  4. Detalle de Producto
  5. Carrito de Compras
  6. Checkout (Multi-paso)
  7. Dashboard de Usuario
  8. Mis Pedidos
  9. Dashboard de Administrador
  10. Gestión de Productos (Admin)
  
- ✅ **Flujos de Usuario:**
  - Registro e Inicio de Sesión completo
  - Proceso de Compra (11 pasos)
  - Gestión Administrativa de Productos
  
- ✅ **Paleta de Colores:**
  - Verde Principal (#4CAF50) - Frescura
  - Naranja (#FF9800) - Cítricos y energía
  - Rojo (#F44336) - CTAs y ofertas
  - Neutros completos
  
- ✅ **Guía de Componentes:**
  - Botones (4 variantes)
  - Tarjetas de producto
  - Formularios
  - Badges y etiquetas
  
- ✅ **Responsive Design:**
  - Breakpoints definidos
  - Adaptaciones mobile
  
- ✅ **Accesibilidad:**
  - Directrices WCAG 2.1 AA
  - Ejemplos de código accesible

**¿Cuándo usar este documento?**
- Al crear diseños en Figma/Adobe XD
- Durante la implementación del frontend
- Para garantizar consistencia visual
- Como referencia de UX patterns

---

## 🚀 Inicio Rápido

### Para Gerentes de Proyecto
1. Lee **CITRIMAC_PLANNING.md** completo
2. Revisa el cronograma y presupuesto
3. Forma el equipo según las especificaciones
4. Presenta el plan a stakeholders

### Para Desarrolladores Backend
1. Revisa la arquitectura en **CITRIMAC_PLANNING.md**
2. Estudia los endpoints en **CITRIMAC_TECHNICAL_SPECS.md**
3. Ejecuta los scripts SQL para crear la base de datos
4. Implementa los servicios según la especificación

### Para Desarrolladores Frontend
1. Estudia los wireframes en **CITRIMAC_UX_UI_DESIGN.md**
2. Revisa los endpoints en **CITRIMAC_TECHNICAL_SPECS.md**
3. Implementa componentes según la guía de diseño
4. Sigue los flujos de usuario especificados

### Para Diseñadores UX/UI
1. Estudia **CITRIMAC_UX_UI_DESIGN.md** completo
2. Crea prototipos de alta fidelidad en Figma
3. Usa la paleta de colores definida
4. Sigue las directrices de accesibilidad

---

## 📊 Resumen del Proyecto

| Aspecto | Detalle |
|---------|---------|
| **Nombre** | Citrimac |
| **Tipo** | Plataforma E-commerce |
| **Nicho** | Frutas y Verduras Frescas |
| **Duración** | 20 semanas (~5 meses) |
| **Equipo** | 8 personas |
| **Backend** | Java 21, Spring Boot 3.5.7 |
| **Frontend** | React 18 / Vue 3, TypeScript |
| **Base de Datos** | PostgreSQL 15, Redis |
| **Pagos** | Stripe |
| **Presupuesto** | $110K - $170K |

---

## 🎯 Módulos Principales

1. **Landing Page** - Página de inicio atractiva con productos destacados
2. **Autenticación** - Login, registro, recuperación de contraseña
3. **Catálogo** - Productos con búsqueda, filtros y paginación
4. **Carrito** - Persistente con modificación de cantidades
5. **Checkout** - Proceso de compra en 4 pasos
6. **Pagos** - Integración con Stripe
7. **Dashboard Usuario** - Perfil, pedidos, direcciones
8. **Dashboard Admin** - Gestión completa del negocio
9. **Notificaciones** - Emails transaccionales

---

## 📈 Fases de Desarrollo

```
Mes 1: Configuración + Backend Core
Mes 2: Backend Pedidos + Pagos + Diseño UI
Mes 3: Frontend Catálogo + Carrito + Checkout
Mes 4: Dashboards (Usuario + Admin)
Mes 5: Testing + Despliegue + Lanzamiento
```

---

## 🛠️ Stack Tecnológico Completo

### Backend
- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL 15
- Redis
- Maven

### Frontend
- React 18 / Vue 3
- TypeScript
- Tailwind CSS / Material-UI
- Redux Toolkit / Pinia
- Axios
- React Router / Vue Router

### Servicios Externos
- Stripe (Pagos)
- SendGrid (Emails)
- Cloudinary (Imágenes)

### DevOps
- Docker
- GitHub Actions
- AWS / GCP / Azure

---

## 📞 Contacto y Soporte

Para preguntas sobre esta documentación o el proyecto:

- **Repositorio:** [AndresCardenas29/java-api-test](https://github.com/AndresCardenas29/java-api-test)
- **Documentación Técnica:** Ver sección de Issues
- **Contribuciones:** Pull Requests bienvenidos

---

## 📝 Notas Importantes

### Estructura de Documentos
Cada documento está diseñado para ser independiente pero complementario:
- **PLANNING** → Visión general y estrategia
- **TECHNICAL_SPECS** → Detalles de implementación
- **UX_UI_DESIGN** → Experiencia de usuario

### Actualizaciones
Esta documentación debe actualizarse cuando:
- Se aprueben cambios en el alcance
- Se modifique la arquitectura
- Se agreguen nuevas funcionalidades
- Se cambien tecnologías

### Versionamiento
- **Versión Actual:** 1.0
- **Fecha:** Enero 2026
- **Estado:** ✅ Documentación Completa

---

## 🎓 Recursos Adicionales

### Para Aprender Más
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev/)
- [Stripe API Reference](https://stripe.com/docs/api)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

### Herramientas Recomendadas
- **Diseño:** Figma, Adobe XD
- **API Testing:** Postman, Insomnia
- **IDE:** IntelliJ IDEA, VS Code
- **DB Management:** DBeaver, pgAdmin

---

## ✅ Checklist de Inicio

Antes de comenzar el desarrollo, asegúrate de:

- [ ] Leer toda la documentación
- [ ] Aprobar el plan con stakeholders
- [ ] Formar el equipo completo
- [ ] Configurar repositorios Git
- [ ] Setup de entornos de desarrollo
- [ ] Acceso a servicios externos (Stripe, etc.)
- [ ] Crear prototipos en Figma
- [ ] Definir sprints y milestones

---

## 🌟 Características Destacadas

- ✅ **Completo:** Todos los aspectos cubiertos
- ✅ **Detallado:** Especificaciones técnicas exhaustivas
- ✅ **Práctico:** Ejemplos de código y APIs
- ✅ **Visual:** Wireframes y diagramas
- ✅ **Realista:** Presupuestos y timelines estimados
- ✅ **Escalable:** Arquitectura preparada para crecimiento

---

## 📄 Licencia

Este proyecto y su documentación son propiedad de Citrimac y están sujetos a los términos de uso definidos por la organización.

---

**Última actualización:** Enero 29, 2026  
**Versión de documentación:** 1.0  
**Estado del proyecto:** 📋 Planificación Completa

---

¡Éxito en el desarrollo de Citrimac! 🍎🥕🍊
