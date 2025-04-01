# 📝 Tutoriel CRUD étape par étape : comprendre et construire une API REST avec Spring Boot

Ce document est conçu comme un **cours pas à pas pour les élèves** qui découvrent comment créer un CRUD avec Spring Boot et PostgreSQL. On y explique **chaque concept et chaque étape**, en commençant par l'entité.

---

## ✅ Qu'est-ce qu'une entité ?

Une entité est une **classe Java** qui correspond à une **table dans une base de données**.

Par exemple :
```java
@Entity
@Table(name = "livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur;
}
```

### 🧠 Explication :
- `@Entity` : dit à Spring que cette classe représente une table en base.
- `@Table(name = "livre")` : nom de la table dans la base.
- `@Id` : indique la clé primaire.
- `@GeneratedValue(...)` : auto-incrémentation de l'ID.
- Les attributs deviennent les **colonnes** de la table.

---

## 🏛️ Comment cette entité est créée dans la BDD ?

Spring Boot utilise **Hibernate** (ORM) pour créer la table automatiquement grâce à cette ligne dans le fichier `application.properties` :
```properties
spring.jpa.hibernate.ddl-auto=update
```

Cela veut dire :
- ✅ Si la table n'existe pas, elle est créée automatiquement
- ✅ Si elle existe, elle est mise à jour si l'entité change (ajout de colonnes, etc.)

Tu n'as **pas besoin d'écrire la requête SQL de création** de table (`CREATE TABLE`) : Spring le fait pour toi !

---

## ❓ Comment sont faites les requêtes avec Spring ?

Spring utilise des **requêtes préparées automatiquement** via les interfaces `JpaRepository`.

Par exemple, si tu écris :
```java
public interface LivreRepository extends JpaRepository<Livre, Long> {
}
```

Tu as déjà accès à :
- `findAll()` → SELECT * FROM livre;
- `findById(1L)` → SELECT * FROM livre WHERE id = ?;
- `save(livre)` → INSERT INTO livre (...) VALUES (...);
- `deleteById(1L)` → DELETE FROM livre WHERE id = ?;

### 🤝 Et les requêtes préparées ?
Ces requêtes utilisent des `?` à la place de valeurs pour éviter les **injections SQL**.
Par exemple :
```sql
SELECT * FROM livre WHERE id = ?
```

Spring se charge de **lier les paramètres automatiquement** (comme `id = 1`) sans risque pour la sécurité.

---

## 🔧 Récapitulatif de la création d'un CRUD - ÉTAPE 1

1. Créer la classe `Livre` avec `@Entity`
2. Ajouter `spring.jpa.hibernate.ddl-auto=update` pour créer la table automatiquement
3. Créer le `LivreRepository` qui hérite de `JpaRepository`
4. Spring gère tout seul les requêtes SQL avec des requêtes préparées
5. Aucun besoin d'écrire `CREATE TABLE` ou `INSERT INTO` : tout est automatisé !

---

## ⚙️ Étape 2 : Le Service — Logique métier

### 📦 Qu'est-ce qu'un service ?
Un **service** est une classe qui contient la logique métier, c’est-à-dire ce que l’application doit faire. Il fait le lien entre le contrôleur (les requêtes HTTP) et la base de données (via le repository).

### Exemple avec l'entité `Auteur`
```java
@Service
public class AuteurServiceImpl implements AuteurService {

    private final AuteurRepository repository;

    public AuteurServiceImpl(AuteurRepository repository) {
        this.repository = repository;
    }

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

### 🧠 Explication de chaque méthode CRUD :
- `getAll()` → SELECT * FROM auteur
- `getById(Long id)` → SELECT * FROM auteur WHERE id = ?
- `create(Auteur auteur)` → INSERT INTO auteur (nom) VALUES (?)
- `update(Long id, Auteur auteur)` → UPDATE auteur SET nom = ? WHERE id = ?
- `delete(Long id)` → DELETE FROM auteur WHERE id = ?

Chaque méthode utilise le repository qui fait appel aux **requêtes SQL générées automatiquement** par Spring.

---

## 🌐 Étape 3 : Le Contrôleur REST — Exposer l'API

Le contrôleur REST permet de **lier une URL à une méthode Java**. Il reçoit les requêtes HTTP (GET, POST, etc.) et appelle les méthodes du service.

### Exemple :
```java
@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {

    private final AuteurService service;

    public AuteurController(AuteurService service) {
        this.service = service;
    }

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

### 🧠 Explication de chaque route :
- `@GetMapping` → correspond à une requête HTTP GET (récupération de données)
- `@PostMapping` → correspond à HTTP POST (création)
- `@PutMapping` → correspond à HTTP PUT (modification)
- `@DeleteMapping` → correspond à HTTP DELETE (suppression)

### 📌 Exemple d'appel avec Postman ou curl :
```http
GET     http://localhost:8081/api/auteurs
GET     http://localhost:8081/api/auteurs/1
POST    http://localhost:8081/api/auteurs
PUT     http://localhost:8081/api/auteurs/1
DELETE  http://localhost:8081/api/auteurs/1
```

Grâce au contrôleur, ton application devient une vraie API REST utilisable depuis une interface front ou un outil de test !

