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

