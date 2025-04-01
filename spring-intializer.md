# 📦 README - Dépendances Maven pour un projet Spring Boot

Ce fichier explique toutes les dépendances ajoutées dans le `pom.xml` d’un microservice Spring Boot, pourquoi elles sont nécessaires, et leur utilité dans le contexte d’un CRUD avec PostgreSQL.

---

## 🔧 Fichier utilisé : `pom.xml`
Basé sur Spring Boot **3.4.4** et Java **17**.

```xml
<properties>
    <java.version>17</java.version>
</properties>
```

---

## ✅ Dépendances essentielles

### 1. `spring-boot-starter-data-jpa`
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
📌 **Pourquoi ?**
- Permet de gérer les entités et les requêtes SQL automatiquement via JPA (Java Persistence API)
- Utilise Hibernate pour la communication avec la base de données
- Fournit `JpaRepository` avec toutes les méthodes CRUD (`findAll()`, `save()`, `deleteById()`, etc.)

---

### 2. `spring-boot-starter-web`
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
📌 **Pourquoi ?**
- Permet de créer des **API REST**
- Fournit les annotations `@RestController`, `@GetMapping`, etc.
- Démarre automatiquement un serveur web (Tomcat intégré)

---

### 3. `spring-boot-devtools`
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```
📌 **Pourquoi ?**
- Améliore le confort de développement : rechargement automatique des fichiers modifiés
- Parfait pour l’environnement de développement (ne pas utiliser en production)

---

### 4. `postgresql`
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```
📌 **Pourquoi ?**
- Permet à Spring Boot de se connecter à une base de données PostgreSQL
- Le driver JDBC nécessaire est inclus ici

---

### 5. `lombok`
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```
📌 **Pourquoi ?**
- Réduit le code boilerplate avec les annotations comme :
  - `@Getter`, `@Setter`
  - `@NoArgsConstructor`, `@AllArgsConstructor`
  - `@Builder`, `@Data`
- Améliore la lisibilité du code sans compromettre la structure

⚠️ Nécessite un plugin dans l’IDE pour fonctionner correctement.

---

### 6. `spring-boot-starter-test`
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
📌 **Pourquoi ?**
- Fournit tous les outils pour écrire des tests unitaires et d’intégration
- Intègre JUnit 5, Mockito, AssertJ, Spring Test, etc.

---

## ⚙️ Plugins de compilation

### 1. `maven-compiler-plugin`
Utilisé pour configurer l’annotation processing de Lombok :
```xml
<annotationProcessorPaths>
    <path>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </path>
</annotationProcessorPaths>
```

### 2. `spring-boot-maven-plugin`
Permet de builder un `.jar` exécutable avec Spring Boot.

---

## 🧪 À retenir
- Les dépendances sont **le cœur du projet Spring** : elles activent les fonctionnalités automatiquement.
- Grâce à Spring Boot Starter, on peut **ajouter beaucoup de puissance avec peu de configuration**.
