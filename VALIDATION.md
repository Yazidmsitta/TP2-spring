# Vérifications de livraison

Vérifications effectuées le 13 octobre 2026 avec le JDK Microsoft 17.0.20.1 et les dépendances Spring 7.0.9.

## Exécutions vérifiées

Les classes principales produites ont été exécutées avec le classpath Spring résolu. Les trois processus se sont terminés avec le code de sortie 0.

### Profil dev

```text
Profil : dev
DAO disponibles : daoMemoire
Service : MetierImpl
Source : mémoire (développement)
Résultat : 20.0
```

### Profil prod

```text
Profil : prod
DAO disponibles : daoApi
Service : MetierImpl
Source : API distante simulée
Résultat : 50.0
```

### Profil qualifier

```text
Profil : qualifier
DAO disponibles : daoApi, daoMemoire
Service : MetierQualifie
Source : API distante simulée
Résultat : 50.0
```

Le lancement du scénario `qualifier` avec le plugin Maven `exec:java` a également réussi.

## Limite de la vérification Maven

La commande complète `mvn clean test` n'a pas abouti dans l'environnement de préparation. Le JDK a signalé `AccessDeniedException` puis `Cannot close compiler resources` lors de la fermeture de fichiers JAR de dépendances. Les classes principales ont été produites et leur exécution a été vérifiée, mais les tests JUnit n'ont pas pu être exécutés.

Les cinq tests sont fournis dans `src/test/java/tp/SpringInjectionTest.java`. Ils doivent être lancés avec `mvn clean test` ou depuis IntelliJ. Aucun succès de ces tests n'est revendiqué dans cette livraison.

## Contenu livré

Les sources, le fichier Maven, le TP complet dans le README et ce relevé de vérification. Les dossiers de compilation, les caches Maven et les réglages propres à la machine ne sont pas inclus.
