Teste Tinnova

API de veículos + exercícios.

Requisitos:
- Docker
- Java 21
- Spring Boot 3.3.3
- Gradle 8.9

API DE VEÍCULOS

Subir o banco (PostgreSQL)
docker compose up -d

Conexão usada pela aplicação:
- URL: jdbc:postgresql://localhost:5432/veiculosdb
- User: tinnova
- Password: tinnova

Rodar a aplicação
./gradlew bootRun

Classe principal: com.tinnova.veiculos.Application

Swagger
- http://localhost:8080/swagger-ui/index.html

EXERCÍCIOS
 
- Cada um possui uma classe principal para execução.
- Todos encontram-se no pacote: com.tinnova.exercicios
