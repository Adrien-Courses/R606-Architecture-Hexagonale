# Library TP

Projet Spring Boot en architecture hexagonale.

## Structure

- `domain` : modele metier, exceptions, ports
- `application` : cas d'usage
- `adapter/in/web` : controleurs REST + DTO
- `adapter/out/persistence` : adaptateurs JPA

## Prerequis

- Java 25
- Maven
- Docker

## Demarrer MySQL

```bash
docker compose up -d
```

## Lancer l'application

```bash
mvn spring-boot:run
```

L'application utilise MySQL par defaut :

- Base : `librarydb`
- Utilisateur : `library_user`
- Mot de passe : `library_password`
- Port : `3306`

## Exemples d'appels

```bash
curl -X POST http://localhost:8080/authors \
  -H "Content-Type: application/json" \
  -d '{"name":"Ursula K. Le Guin"}'
```

```bash
curl -X POST http://localhost:8080/authors/<authorId>/books \
  -H "Content-Type: application/json" \
  -d '{"isbn":"9780000000001","title":"A new book"}'
```

```bash
curl http://localhost:8080/authors/<authorId>/books
```

```bash
curl -X POST http://localhost:8080/books/9780061054884/borrow
```
