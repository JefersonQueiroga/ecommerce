# E-commerce API com Spring Boot e Keycloak

## 📝 Descrição
API REST para um sistema de e-commerce com autenticação OAuth2 usando Keycloak. O projeto utiliza Spring Boot 3, PostgreSQL para persistência de dados e Swagger para documentação da API.

## 🛠️ Tecnologias Utilizadas
- Java 17
- Spring Boot 3.3.5
- Spring Security com OAuth2
- PostgreSQL
- Flyway para migrations
- Keycloak para autenticação e autorização
- MapStruct para mapeamento de objetos
- Lombok para redução de boilerplate
- Swagger/OpenAPI para documentação

## 🚀 Como Executar

### Pré-requisitos
- JDK 17
- Maven
- PostgreSQL
- Docker (para Keycloak)
- Git

### Configuração do Ambiente
1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/ecommerce.git
```

2. Configure o arquivo `application.properties`
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
Edite o arquivo com suas configurações locais.

3. Inicie o Keycloak com Docker
```bash
docker-compose up -d
```

4. Compile e execute o projeto
```bash
mvn spring-boot:run
```

## 🔐 Configuração do Keycloak

1. Acesse o console admin do Keycloak
2. Crie um novo realm chamado "ecommerce"
3. Configure um client para o frontend
4. Configure os identity providers (Google, GitHub)
5. Crie as roles necessárias (USER, ADMIN)

## 📚 Documentação da API
A documentação da API está disponível através do Swagger UI:
```
http://localhost:8080/api-docs
```

## 🏗️ Estrutura do Projeto
```
src/
├── main/
│   ├── java/
│   │   └── br/ifrn/edu/jeferson/ecommerce/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── security/
│   │       └── service/
│   └── resources/
│       ├── db/migration/
│       └── application.properties
```

## 💡 Features
- Autenticação OAuth2 com Keycloak
- Integração com provedores sociais (Google, GitHub)
- Gerenciamento de usuários
- Controle de acesso baseado em roles
- Migrations com Flyway
- Documentação automática com OpenAPI

## 🤝 Contribuição
Contribuições são bem-vindas! Por favor, leia as diretrizes de contribuição antes de submeter um Pull Request.

## 📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
```