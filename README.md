API de Jeu de Pays

Démarrer une Partie

    Méthode: POST
    URL: http://localhost:8080/api/games/start
    Body:

json

{
  "username": "player1"
}


Proposer une Capitale

    Méthode: POST
    URL: http://localhost:8080/api/games/{id}
        Remplacez {id} par l'ID du pays reçu dans la réponse de la route /start.
    Body:

json

{
  "username": "player1",
  "capital": "Kyiv"
}



Obtenir le Score d'un Joueur

    Méthode: GET
    URL: http://localhost:8080/api/games/score/{username}
        Remplacez {username} par le nom d'utilisateur du joueur (par exemple, http://localhost:8080/api/games/score/player1)


Fonctionnalités Supplémentaires
Sensibilité à la Casse

La vérification des capitales n'est pas sensible à la casse pour faciliter l'utilisation.
Éviter les Questions Consécutives Identiques

La logique empêche de poser la même question plusieurs fois de suite pour améliorer l'expérience de jeu.
Tests Unitaires

Des tests JUnit sont inclus pour vérifier la logique de devinette de capitale et assurer son bon fonctionnement.
