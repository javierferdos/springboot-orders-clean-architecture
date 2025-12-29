# springboot-orders-clean-architecture
Sistema API REST desarrollado con Spring Boot para la gestión de productos y pedidos, aplicando una arquitectura limpia con separación por capas (controller, service, repository, dto, entity) y buenas prácticas de desarrollo backend.

Este proyecto demuestra dominio de construcción de servicios REST, mapeo de entidades, DTOs, manejo de excepciones, validaciones y persistencia con JPA/Hibernate.

Características principales

✔ Gestión de productos
✔ Gestión de pedidos y detalles de pedido
✔ Uso de DTOs para entrada y salida
✔ Arquitectura organizada por capas
✔ Manejo global de errores con @ControllerAdvice
✔ Respuestas tipadas con ResponseEntity
✔ Persistencia mediante Spring Data JPA
✔ Endpoints REST simples y claros

JSON para crear producto
{
"nombre": "teclado",
"precio": 80000,
"Stock" : 10
}

JSON para crear pedido
{
"productos": [
{
"id": 1,
"cantidad": 2
},
{
"id": 2,
"cantidad": 4
}
]
}