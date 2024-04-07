# Agrix

![soja](./images/soja.jpg)

### Agrix é um sistema de gerenciamento de fazendas, que permite o controle de plantações, fertilização e usuarios.

#### Funcionalidades:

- Cadastro e manipulação de usuarios
- Cadastro e manipulação de fazendas
- Cadastro e manipulação de plantações
- Cadastro e manipulação de fertilização

#### Tecnologias utilizadas:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

- Spring Boot
- Spring Data
- Spring Security
- Maven

#### Como rodar o projeto:

Existe um container generico para rodar o mysql, para rodar o container execute o comando:

```bash
# builda a imagem
docker build -t <nome-da-imagem> -f Dockerfile .

# roda o container
docker run -d -p 3306:3306 <nome-da-imagem> --name <nome-do-container
# Caso queira alterar a configurações genericas do container, altere o arquivo `Dockerfile`.
```

As informações de conexão com o banco de dados estão no arquivo `application.properties` dentro de `src/main/resources` altere baseado no seu banco de dados.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agrix?createDatabaseIfNotExist=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

api.security.token.secret=${JWT_SECRET:r1r2l1l2trianguloquadradobolaXcimabaixoesquerdadireita}
```

Para rodar o projeto execute o comando:

```bash
# instale as dependencias
mvn install
# execute o projeto
mvn spring-boot:run

# shortway
mvn install -Dskiptests -Dcheckstyle.skip && mvn spring-boot:run
```

#### Documentação:

A documentação da API pode ser acessada em `http://localhost:8080/swagger-ui/index.html` após rodar o projeto.
