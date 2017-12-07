## Tâches qui restent à faire

* Faire fichiers XML de test
* Analyser les fichiers avec Parseur
* Remplir les objets avec les données
* Vérification des objets de données (dates etc)
* Traitement des données
* Diaporama
* Rapport

## Répartition

Tâche   |   Affectation | Difficulté  | Enchainement  | Entrée | Sortie | Etat
--------|---------------|-------------|---------------|--------|--------|--------|
Fichiers de test                                                | Smaïl       | Facile   | Aucun        | Fichiers XML | Validation ou non par le XSD | Terminé
Structures/méthodes pour stocker les doc reçus (Objet Document) | Christopher | Moyenne | En même temps que le parseur | Appels aux méthodes de stockage des messages reçu | Objets Java Document | Terminé
Analyse des fichiers/Parseur                                    | Christopher | Difficile | Après avoir fait les fichiers de tests | Fichiers XML | Appels aux méthodes de gestion de donnée | Terminé
Verification                                                    | Clément     | Facile      | Après analyse des fichiers  | Objets Java | Validation ou non | Terminé
Base de donnée du serveur                                       | Smaïl       | Facile      | Après la création de l'objet document  | Objet Document | BDD | Terminé
Traitement des données                                          | Clément, Dimitri | Moyenne | Après vérification | Objets java | Traitement | Terminé
Diapo                                                           | Smaïl            | Facile  | Aucun | Néant   | Diapo | Terminé
Rapport                                                         | Tout le monde    | Facile | Aucun | Néant |Rapport | Terminé