# ✨ Tutoriel Complet : Créer un CRUD avec Spring Boot, PostgreSQL et Docker 🚀

Ce tutoriel vous guide à travers toutes les étapes pour créer une application CRUD complète avec **Spring Boot**, **PostgreSQL** et **Docker**. Idéal pour les débutants souhaitant apprendre les bases d'une architecture simple et propre.

---

## 🔢 Prérequis

- Java 17
- Maven
- Docker
- IDE (IntelliJ, VS Code... PAS CURSOR !!!!!!!!)

---

## ✅ Objectif du projet

Créer une application de gestion d'auteurs, avec les opérations suivantes :
- Créer un auteur
- Lire tous les auteurs ou un auteur spécifique
- Mettre à jour un auteur
- Supprimer un auteur

---

## 📁 Structure du projet

```
crud-demo/
├── auteur-service/
│   ├── src/main/java/com/example/auteur_service/
│   │   ├── controller/         # Contrôleurs REST
│   │   ├── entity/             # Entités JPA
│   │   ├── repository/         # Interfaces d'accès aux données
│   │   ├── service/            # Logique métier
│   │   └── AuteurServiceApplication.java
│   ├── application.properties
├── docker-compose.yml
└── README.md
```

---

## 🏛️ Création de la base de données PostgreSQL (via Docker)

```yaml
docker-compose.yml
```
```yaml
version: '3.8'

services:
  auteur-db:
    image: postgres:15
    container_name: auteur-db
    environment:
      POSTGRES_DB: auteurdb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5433:5432"
    volumes:
      - auteur-data:/var/lib/postgresql/data
    networks:
      - library-network

  auteur-service:
    build:
      context: ./auteur-service
    container_name: auteur-service
    ports:
      - "8081:8081"
    depends_on:
      - auteur-db
    networks:
      - library-network

volumes:
  auteur-data:

networks:
  library-network:
    driver: bridge
```

---

## 📆 Configuration de Spring Boot

```properties
# application.properties (ou application-docker.properties)
server.port=8081
spring.application.name=auteur-service
spring.datasource.url=jdbc:postgresql://auteur-db:5432/auteurdb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 📚 Création de l'entité Auteur

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
}
```

Cette classe représente une table "auteur" dans la base de données. Chaque attribut devient une colonne.

---

## 📂 Repository : Accès aux données

```java
public interface AuteurRepository extends JpaRepository<Auteur, Long> {
}
```

JPA fournit automatiquement toutes les opérations CRUD.

---

## 🧱 Service : Logique métier

```java
public interface AuteurService {
    List<Auteur> getAll();
    Auteur getById(Long id);
    Auteur create(Auteur auteur);
    Auteur update(Long id, Auteur auteur);
    void delete(Long id);
}
```

```java
@Service
@RequiredArgsConstructor
public class AuteurServiceImpl implements AuteurService {

    private final AuteurRepository repository;

    public List<Auteur> getAll() {
        return repository.findAll();
    }

    public Auteur getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Auteur create(Auteur auteur) {
        return repository.save(auteur);
    }

    public Auteur update(Long id, Auteur auteur) {
        Auteur existing = getById(id);
        if (existing == null) return null;
        existing.setNom(auteur.getNom());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
```

---

## 🚗 Contrôleur REST

```java
@RestController
@RequestMapping("/api/auteurs")
@RequiredArgsConstructor
public class AuteurController {

    private final AuteurService service;

    @GetMapping
    public List<Auteur> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Auteur getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Auteur create(@RequestBody Auteur auteur) {
        return service.create(auteur);
    }

    @PutMapping("/{id}")
    public Auteur update(@PathVariable Long id, @RequestBody Auteur auteur) {
        return service.update(id, auteur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
```

---

## 🎓 Test de l'API

Utilisez **Postman**, **Insomnia** ou **curl** pour tester les routes :

- `GET http://localhost:8081/api/auteurs`
- `GET http://localhost:8081/api/auteurs/1`
- `POST http://localhost:8081/api/auteurs` avec JSON body :
```json
{
  "nom": "Victor Hugo"
}
```
- `PUT http://localhost:8081/api/auteurs/1` avec JSON body :
```json
{
  "nom": "Molière"
}
```
- `DELETE http://localhost:8081/api/auteurs/1`

---

## ⚡ Lancer l'application avec Docker

```bash
docker-compose up --build
```

L'API sera disponible sur : `http://localhost:8081/api/auteurs`

---

## 🚀 Prochaines étapes

- Ajouter des validations (ex: `@NotBlank` sur `nom`)
- Ajouter Swagger pour documenter l'API
- Ajouter une entité `Livre` reliée à `Auteur`
- Intégration avec une gateway, Eureka, etc.

---

## 😊 Merci !

N'hésitez pas à adapter ce projet selon vos besoins ! Bon apprentissage ☕