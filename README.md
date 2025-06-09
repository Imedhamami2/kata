# Kata Foo Bar Quix

Projet Java Spring Boot résolvant le kata de transformation de nombres selon les règles FOO/BAR/QUIX.

## 🚀 Technologies
- Java 17
- Maven
- Spring Boot (Web, Batch)
- Lombok + SLF4J
- JUnit 5
- Swagger UI

## 📦 Build & Exécution

```bash
./mvnw clean install
./mvnw spring-boot:run
```

## 🔬 Tests

```bash
./mvnw test
```

Inclut :
- ✅ Tests unitaires
- ✅ Tests REST avec MockMvc
- ✅ Test d'intégration

## 🧠 Règles de transformation

| Règle                        | Résultat         |
|-----------------------------|------------------|
| divisible par 3             | FOO              |
| divisible par 5             | BAR              |
| contient 3 (gauche à droite)| FOO              |
| contient 5 (gauche à droite)| BAR              |
| contient 7 (gauche à droite)| QUIX             |
| divisible > contient        | priorité         |

## 📂 API REST

- **GET /transform/{number}**  
  Retourne la transformation d’un nombre entier.

## 📈 Swagger

Accessible à [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🪵 Logs

- Console + Fichier `logs/application.log`
- Rotation journalière, 30 jours max, 100MB

