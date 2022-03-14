# Simple Spring Boot application

This application demonstrates usage of:
- JHipster "best practices"
  - yaml configuration
  - file structure
  - JPA relationships...

- Spring MVC
- Spring Security
- Spring Data JPA
  
- H2
  - embedded
- PostgreSQL
  - Docker image
- Swagger

---
Problem: \
"I want fetch only student entities and no relationships while fetching ALL students, \
but while fetching single student I want to fetch Student relationships too(Subjects)"

Solutions:
- Projections:
  - in order to optimise queries there are "lightweight" projections of domain objects
- Another approach:
  - static fetch = FetchType.LAZY is unable to achieve this dynamic behaviour at runtime, \
   but in combination with @EntityGraph(attributePaths = "...") it can be done