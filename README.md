# Starscore - Backend

É uma API Rest desenvolvida usando Java, Java Spring, PostgresSQL como o banco de dados, e Spring Security e JWT para controle de autenticação.

Para ver o frontend da aplicação visite aqui.

## Pré-requisitos

Antes de começar, verifique se você atende aos seguintes requisitos:
- Java JDK 8 ou superior instalado.
- PostgresSQL instalado e configurado.

## Configuração

Para configurar e executar este projeto localmente, siga estas etapas:

1. Clone este repositório.
2. Configure o banco de dados PostgreSQL.
3. Abra o arquivo `application.properties` localizado em `target/classes` e configure as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com base na sua configuração do PostgreSQL.
4. Crie as tabelas do banco disponíveis no arquivo `create_tables.sql`.

## Uso

1. Execute.
2. A aplicação estará disponível em <a>http://localhost:8080</a>.

## API Endpoints
Autenticação:
```
POST /auth/register - Registro do usuário.

POST /auth/login - Login do usuário.
```

Usuário:
```
PUT /user/{userId} - Alterar login ou senha.

DELETE /user/{userId} - Exclusão da conta.
```

Filmes:
```
GET /movie - Lista de todos os filmes.
```

Lista de filmes do usuário:
```
GET /movie/watchlist/{userId} - Lista de todos os filmes salvos pelo usuário.

POST /movie/watchlist - Salvar um filme na lista do usuário.

PUT /movie/favorite/{watchlistId} - Alterar situação "Favorito" do filme da lista, aceita apenas FALSO ou TRUE.

PUT /movie/watched/{watchlistId} - Alterar situação "Assistido" do filme da lista, aceita apenas FALSO ou TRUE, também insere a data que o filme foi visto caso seja TRUE.

DELETE movie/watchlist/{userId}/{movieId} - Deletar um filme da lista do usuário.
```

Avaliações:
```
GET /movie/rating/{userId} - Lista de todas as avaliações de filmes feitas pelo usuário.

GET /movie/movieratings/{movieId} - Lista de todas as avaliações do filme.

POST /movie/rating - Avaliar um filme.

PUT /movie/rating/{rateId} - Alterar a avaliação.

DELETE movie/rating/{rateId} - Deletar a avaliação.
```

## Deploy

Caso queira ver a aplicação completa funcionando, visite <a>aqui</a>.
