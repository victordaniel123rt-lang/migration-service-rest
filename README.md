# Migration Service REST

## 📋 Descripción

El presente repositorio es un ejemplo común de una arquitectura por capas, la cual usaremos como ejemplo para migrarla a una arquitectura donde nuestro dominio quede desacoplado de las tecnologías que utilizamos aquí: **SPRING DATA JPA**, **ORACLE DB**, **SPRING**. La idea es obtener un resultado similar a una **arquitectura Hexagonal**.

## 🎯 Objetivo

Demostrar cómo refactorizar una arquitectura tradicional por capas hacia una arquitectura hexagonal (puertos y adaptadores), mejorando:

- **Desacoplamiento**: Separar la lógica de negocio de los detalles técnicos
- **Testabilidad**: Facilitar las pruebas unitarias e integración
- **Mantenibilidad**: Código más limpio y fácil de entender
- **Flexibilidad**: Poder cambiar tecnologías sin afectar el dominio

## 🏗️ Arquitectura Actual

La arquitectura actual sigue el patrón de capas tradicional:

```
┌─────────────────────────────────┐
│   Capa de Presentación (REST)   │
├─────────────────────────────────┤
│   Capa de Aplicación (Service)  │
├─────────────────────────────────┤
│   Capa de Dominio (Entities)    │
├─────────────────────────────────┤
│   Capa de Persistencia (DAO)    │
├─────────────────────────────────┤
│   Base de Datos (ORACLE)        │
└─────────────────────────────────┘
```

## 🎯 Arquitectura Hexagonal (Destino)

La arquitectura hexagonal separa claramente el dominio de los adaptadores:

```
┌──────────────────────────────────────────────────────┐
│                   ADAPTADORES ENTRADA                │
│        (Controllers, Eventos, APIs Externas)         │
└──────────────────────────────────┬───────────────────┘
                                   │
                    ┌──────────────▼──────────────┐
                    │  PUERTOS DE ENTRADA         │
                    │  (Interfaces de Entrada)    │
                    └──────────────┬──────────────┘
                                   │
        ┌──────────────────────────▼───────────────────────────┐
        │                  NÚCLEO (DOMINIO)                    │
        │      (Lógica de Negocio - Independiente)             │
        │                                                       │
        │  • Entidades de Dominio                              │
        │  • Casos de Uso                                      │
        │  • Reglas de Negocio                                 │
        └──────────────────────────┬──────────────────────────┘
                                   │
                    ┌──────────────▼──────────────┐
                    │  PUERTOS DE SALIDA          │
                    │  (Interfaces de Salida)     │
                    └──────────────┬──────────────┘
                                   │
┌──────────────────────────────────▼───────────────────┐
│                   ADAPTADORES SALIDA                 │
│    (Persistencia, Bases de Datos, Sistemas Externos) │
└──────────────────────────────────────────────────────┘
```

## 🛠️ Tecnologías

- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Acceso a Datos**: Spring Data JPA
- **Base de Datos**: Oracle Database
- **Patrón REST**: Spring MVC

## 📦 Estructura del Proyecto

```
migration-service-rest/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── domain/              # Lógica de negocio (Dominio)
│   │   │       ├── application/         # Casos de uso (Aplicación)
│   │   │       ├── infrastructure/      # Implementaciones técnicas
│   │   │       │   ├── persistence/     # Adaptadores de persistencia
│   │   │       │   └── config/          # Configuración
│   │   │       └── api/                 # Adaptadores REST
│   │   └── resources/
│   │       └── application.yml          # Configuración de la aplicación
│   └── test/                            # Tests
├── pom.xml                              # Dependencias Maven
└── README.md
```

## 🚀 Instalación y Uso

### Requisitos Previos

- Java 11 o superior
- Maven 3.6 o superior
- Oracle Database (configurado y accesible)

### Configuración

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/victordaniel123rt-lang/migration-service-rest.git
   cd migration-service-rest
   ```

2. **Configurar la base de datos** en `src/main/resources/application.yml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:oracle:thin:@localhost:1521:xe
       username: usuario
       password: contraseña
     jpa:
       hibernate:
         ddl-auto: validate
   ```

3. **Compilar el proyecto**:
   ```bash
   mvn clean install
   ```

4. **Ejecutar la aplicación**:
   ```bash
   mvn spring-boot:run
   ```

## 📚 Ejemplo de API REST

### Obtener recurso

```bash
GET /api/v1/recurso/{id}
```

**Respuesta**:
```json
{
  "id": 1,
  "nombre": "Ejemplo",
  "estado": "activo"
}
```

## 🔄 Migración de Arquitectura

### Fases de Migración

1. **Fase 1**: Identificar y crear interfaces de puertos
2. **Fase 2**: Refactorizar la lógica de negocio al dominio
3. **Fase 3**: Implementar adaptadores para persistencia
4. **Fase 4**: Desacoplar los controladores REST
5. **Fase 5**: Agregar tests para validar la arquitectura

## 🧪 Tests

Ejecutar todas las pruebas:

```bash
mvn test
```

Ejecutar pruebas específicas:

```bash
mvn test -Dtest=NombreDelTest
```

## 📖 Referencias

- [Arquitectura Hexagonal - Alistair Cockburn](https://alistair.cockburn.us/hexagonal-architecture/)
- [Domain-Driven Design - Eric Evans](https://www.domainlanguage.com/ddd/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Clean Architecture - Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

## 🤝 Contribuir

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más detalles.

## 👨‍💻 Autor

**victordaniel123rt-lang**

## 📞 Contacto y Soporte

Para preguntas o problemas, por favor abre un issue en el repositorio.

---

**Nota**: Este repositorio es un ejemplo educativo de refactorización arquitectónica. No es código de producción.
