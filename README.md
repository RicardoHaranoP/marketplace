# Marketplace

Aplicação backend para cadastro e consulta de clientes, construída com Spring Boot. Os dados são persistidos em MySQL e a API REST é exposta pelo Spring Data REST no formato HAL.

## Funcionalidades

- Exposição dos recursos de clientes por meio do Spring Data REST.
- Consulta paginada e ordenada de clientes.
- Busca de clientes cujo primeiro nome começa com um texto, sem diferenciar maiúsculas e minúsculas.
- Projeção HAL `except`, que retorna primeiro nome, sobrenome e endereço.
- Associação um-para-um entre cliente e endereço. A persistência do cliente também persiste o endereço associado.
- Validação de campos do cliente, incluindo nome obrigatório e e-mail obrigatório com formato válido.
- Eventos de criação, atualização e exclusão de clientes registrados pelo handler da aplicação.
- Verificação de saúde da aplicação pelo Actuator.

O método `deleteById` do repositório está explicitamente desabilitado para exposição REST.

## Tecnologias e dependências

- **Java 25**: versão de compilação configurada no Maven.
- **Spring Boot 3.4.5**: inicialização e configuração da aplicação.
- **spring-boot-starter-web**: servidor HTTP e suporte web.
- **spring-boot-starter-data-jpa**: persistência JPA e integração com Hibernate.
- **spring-boot-starter-data-rest**: exposição dos repositórios como recursos REST em HAL.
- **spring-data-rest-hal-explorer**: interface para navegar pela API HAL.
- **spring-boot-starter-validation**: validação com Jakarta Bean Validation.
- **spring-boot-starter-actuator**: endpoints operacionais, incluindo health.
- **Lombok 1.18.48**: geração de métodos repetitivos, como getters e setters; configurado também como processador de anotações.
- **MySQL Connector/J**: conexão JDBC com o MySQL.
- **spring-boot-docker-compose**: integração que inicia o serviço definido em `compose.yml` durante a execução da aplicação.

As versões dos starters Spring Boot são gerenciadas pelo BOM do Spring Boot definido no `pom.xml`.

## Requisitos

- JDK 25 instalado e configurado no `PATH`.
- Maven instalado.
- Docker Desktop instalado e em execução, usando o engine Linux.

## Executar

No PowerShell, entre na pasta `marketplace` e execute:

```powershell
mvn spring-boot:run
```

O Spring Boot inicia o serviço MySQL definido em `compose.yml` automaticamente. Na primeira execução, o Docker pode precisar baixar a imagem `mysql:9.6`. Quando a aplicação estiver pronta, o servidor HTTP estará disponível em `http://localhost:8080`.

Para parar a aplicação, pressione `Ctrl+C` no terminal em que o Maven está rodando. A configuração Compose usa `start-only`, então o contêiner do banco pode continuar ativo após a aplicação parar.

## API

A raiz `http://localhost:8080/` apresenta os links HAL da API e o HAL Explorer, quando disponível. O recurso de clientes é publicado em `/customers`.

Exemplos de chamadas:

```http
GET http://localhost:8080/customers
GET http://localhost:8080/customers/{uuid}
GET http://localhost:8080/customers/search/findByFirstNameStartingWithIgnoreCase?firstName=ana
GET http://localhost:8080/customers?projection=except
GET http://localhost:8080/actuator/health
```

Para criar um cliente, envie um `POST` para `/customers` com `Content-Type: application/json`. Exemplo:

```json
{
  "firstName": "Ana",
  "lastName": "Silva",
  "email": "ana@example.com",
  "address": {
    "street": "Rua das Flores, 10",
    "postalCode": "01000-000",
    "city": "Sao Paulo",
    "state": "SP"
  }
}
```

Use o UUID retornado ou o link `self` na resposta HAL para consultar um cliente específico. Para os nomes exatos dos links e parâmetros, consulte a resposta HAL da raiz ou do recurso `/customers`.

## Banco de dados

O `compose.yml` inicia o MySQL 9.6 com estes parâmetros de desenvolvimento:

- Banco: `registration`
- Usuário: `app`
- Senha: `app`
- Senha do usuário root: `root`
- Porta no computador: `3307`
- Porta do MySQL no contêiner: `3306`
- Volume persistente: `registration-database-data`

Para ferramentas instaladas no computador, conecte-se a `localhost:3307`. Esses dados de acesso são apenas para desenvolvimento local; use credenciais seguras fora desse ambiente.

## Configurações

O arquivo `src/main/resources/application.properties` configura o nome da aplicação, a criação/atualização do schema com `spring.jpa.hibernate.ddl-auto=update`, a exibição de SQL e os detalhes do health endpoint. Como `management.endpoint.health.show-details=always` expõe detalhes de saúde, essa configuração deve ser revista antes de disponibilizar a aplicação em um ambiente compartilhado ou de produção.
