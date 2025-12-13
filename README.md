# 📋 TP Travaux Pratiques sur JUnit & Intégration Continue

**Auteur :** Monsef RH  
**Module :** Qualité Logiciel  
**Responsable :** BOUARIFI Walid  
**Année universitaire :** 2025-2026  

---

## 📌 Description du projet

Ce repository regroupe tous les exercices du TP JUnit (Exercices 1 à 5) ainsi qu'un **pipeline d'intégration continue complet avec Jenkins**. L'objectif est de démontrer la mise en place d'une chaîne CI/CD automatisée pour assurer la qualité et la fiabilité du code.

---

## 🎯 Exercices implémentés

### **Exercice 1 : Application de conversion de devises (MAD ↔ EUR)**
- **Classes** : `ExchangeRate`, `CurrencyConverter`, `Main`
- **Tests** : 8 tests unitaires
  - Montant zéro, négatif
  - Conversions bidirectionnelles
  - Taux invalides
- **Status** : ✅ Tous les tests passent

### **Exercice 2 : Gestion de bibliothèque – Classe Livre**
- **Classes** : `Livre`, `LivreTest`
- **Fonctionnalités** : Validations, getters/setters, equals/hashCode
- **Tests** : 21 tests unitaires
  - Création et validation
  - Cas limites (chaînes vides, null)
  - Performance et encapsulation
- **Status** : ✅ Tous les tests passent

### **Exercice 3 : Extension de Livre avec LocalDate**
- **Classes** : `LivreAvecDate`, `LivreAvecDateTest`
- **Validation** : Date de publication (1000 → année actuelle)
- **Tests** : 12 tests unitaires
  - Dates valides/invalides
  - Cas limites temporels
- **Status** : ✅ Tous les tests passent

### **Exercice 4 : Couverture de code avec JaCoCo**
- **Plugin** : Configuré dans `pom.xml`
- **Rapport** : Généré dans `target/site/jacoco/index.html`
- **Fonctionnalités** : Mesure de couverture de code
- **Status** : ✅ Intégré au pipeline

### **Exercice 5 : Gestion de fichiers avec FileManager**
- **Classes** : `FileManager`, `FileManagerTest`
- **Fonctionnalités** : Lecture de fichier + gestion d'exceptions
- **Tests** : 4 tests unitaires
  - Fichier existant
  - Fichier inexistant
  - Chemin invalide
- **Status** : ✅ Tous les tests passent

### **📊 Résumé des Tests**
- **Total** : 54 tests unitaires
- **Résultats** : ✅ **0 failures, 0 errors, 0 skipped**
- **Temps d'exécution** : ~47 secondes

---

## 🚀 Pipeline d'Intégration Continue (Jenkins)

### **Technologies utilisées**
| Technologie | Utilisation |
|-------------|------------|
| **Jenkins** | Orchestration du pipeline CI/CD (Docker) |
| **Maven** | Build, compilation et exécution des tests |
| **GitHub** | Gestionnaire de contrôle de version |
| **Git** | Clone automatique du repository |
| **JaCoCo** | Analyse de couverture de code |
| **SonarQube** | Analyse statique et qualité du code |
| **JUnit 5** | Framework de tests unitaires |
| **Docker** | Containerisation des services |

### **Architecture du Pipeline**

```
[Git Push] 
    ↓
[GitHub Webhook] 
    ↓
[Jenkins Trigger] 
    ↓
[Stage 1: Checkout] → git clone
    ↓
[Stage 2: Build] → mvn clean compile
    ↓
[Stage 3: Test] → mvn test (54 tests)
    ↓
[Stage 4: Code Coverage] → mvn jacoco:report
    ↓
[Stage 5: SonarQube] → mvn sonar:sonar
    ↓
[Stage 6: Package] → mvn package
    ↓
[Build SUCCESS] ✅
```

### **Étapes du Pipeline**

#### **1. Checkout (Clone du Repository)**
```bash
git clone https://github.com/MonsefRH/Jenkins-TP3.git
```
- Clone automatique via webhook GitHub

#### **2. Build (Compilation)**
```bash
mvn clean compile
```
- Compilation de 7 fichiers source Java
- Target JDK: 21

#### **3. Exécution des Tests**
```bash
mvn test
```
- 54 tests unitaires exécutés
- 0 échecs, 0 erreurs
- Rapport généré dans `target/surefire-reports/`

#### **4. Analyse de Couverture (JaCoCo)**
```bash
mvn jacoco:report
```
- Génération du rapport de couverture
- Disponible dans `target/site/jacoco/index.html`

#### **5. Analyse Statique (SonarQube)**
```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=ConversionDevisesCI \
  -Dsonar.host.url=http://sonarqube:9000 \
  -Dsonar.login=<token>
```
- Analyse de qualité du code
- Détection de bugs et vulnérabilités
- Métriques de maintenabilité

#### **6. Package (Création du JAR)**
```bash
mvn package
```
- Génération du JAR : `TP_test-1.0-SNAPSHOT.jar`
- Disponible dans `target/`

---

## 🐳 Lancement rapide avec Docker

### **Prérequis**
- Docker installé et en cours d'exécution
- Ports 8080, 9000, 50000 disponibles

### **1. Lancer Jenkins**
```bash
docker run -d \
  -p 8080:8080 \
  -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  --name jenkins \
  jenkins/jenkins:lts-jdk21

# Installer Maven
docker exec -u root jenkins bash -c "apt-get update && apt-get install -y maven"
docker restart jenkins
```

### **2. Lancer SonarQube**
```bash
docker run -d \
  --name sonarqube \
  -p 9000:9000 \
  sonarqube:lts-community
```
---

## ⚙️ Configuration Jenkins

### **Créer un nouveau job**
1. New Item → Freestyle project
2. **Nom** : `ConversionDevisesCI`
3. **Source Code Management** :
   - Repository URL : `https://github.com/MonsefRH/Jenkins-TP3.git`
   - Branch : `main`

4. **Build Triggers** :
   - ✅ GitHub hook trigger for GITScm polling
   
5. **Build Steps** :
   ```bash
   mvn clean verify
   ```

6. **Post-build Actions** :
   - Publish JUnit test result report
     - Test report XMLs : `**/target/surefire-reports/*.xml`
   - Publish HTML reports (JaCoCo)
     - HTML directory : `target/site/jacoco`
     - Index page : `index.html`
     - Report name : `JaCoCo Coverage`

---

## 🔗 Configuration GitHub Webhook

### **Étapes**
1. Aller sur GitHub → Settings → Webhooks
2. Click "Add webhook"
3. **Payload URL** : `http://your-jenkins-url:8080/github-webhook/`
4. **Content type** : `application/json`
5. **Events** : Sélectionner "Just the push event"
6. **Active** : ✅ Checked
7. Click "Add webhook"

À chaque push sur `main`, Jenkins déclenche automatiquement le build.

---

## 📊 Résultats et Rapports

### **Build Status**
```
✅ BUILD SUCCESS

Finished: SUCCESS
Total time: 47.315 s
```

### **Résultats des Tests**
```
Tests run: 54
Failures: 0
Errors: 0
Skipped: 0
```

### **Rapports Disponibles**

#### **1. Rapport JUnit**
- **Localisation** : Jenkins Dashboard → Build → Test Result
- **Détails** : Résultats de chaque test

#### **2. Rapport JaCoCo (Couverture)**
- **Localisation** : `target/site/jacoco/index.html`
- **Contient** :
  - % de couverture par classe
  - Couverture de ligne (line coverage)
  - Couverture de branche (branch coverage)

#### **3. Rapport SonarQube**
- **Localisation** : http://sonarqube:9000
- **Contient** :
  - Qualité du code (grade A-E)
  - Bugs et vulnérabilités
  - Smells (mauvaises pratiques)
  - Maintenabilité

### **Examples** 
Jenkins
<img width="1902" height="873" alt="Screenshot 2025-12-13 011136" src="https://github.com/user-attachments/assets/30381118-6c54-4f63-bbb5-d9c08f07398a" />
<img width="1441" height="769" alt="Screenshot 2025-12-13 011056" src="https://github.com/user-attachments/assets/3d0a2565-3a2f-4c21-8a97-2ea0e051cec4" />
SonarQube
<img width="1899" height="871" alt="Screenshot 2025-12-13 023833" src="https://github.com/user-attachments/assets/1376da2a-5c02-4b0f-9d2f-146cc5431fdc" />
<img width="1904" height="874" alt="Screenshot 2025-12-13 023808" src="https://github.com/user-attachments/assets/1aee168f-6e3a-44db-afca-1911d92a164b" />
---

