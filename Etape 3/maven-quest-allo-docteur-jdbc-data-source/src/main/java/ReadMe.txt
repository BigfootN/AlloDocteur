Question 1

Une injection SQL permet de detourner une requete en modifiant les informations de celle-ci
afin d'alterer les informations envoyees ou recues.

Question 2

INNER JOIN: Retourne toutes les lignes ayant de la tableA et la tableB ayant repondant
a la condition souhaitee.

LEFT JOIN: Meme chose que INNER JOIN mais retourne toutes les lignes de la tableA:

SELECT colonne1, colonne2, ... FROM tableA LEFT JOIN tableB ON <condition>

RIGHT JOIN: Meme chose que LEFT JOIN mais a droite

Jointure Totale: Retourne toutes lignes des deux tables.

N.B: Pour LEFT JOIN, RIGHT JOIN et Jointure Totale, les colonnes qui repondent pas
a la condition ont pour valeur NULL.

Question 3

La connection retournee par DataSource.getConnection(), lorsque l'on souhaitera la
fermer, ne le sera pas, contrairement a DriverManager.getConnection();

Donc, si on a besoin d'un "connection pool" (un cache de connections) alors on utilisera DriverManager, sin DataSource.