Question 1

IoC (Inversion of Control) est une forme d'injection de dependances.

Ex: on cree une classe qui en a besoin d'une autre (un editeur de texte qui
aurait besoin d'un spellchecker, une interface qui aurait besoin d'un UI).

Question 2

Component: Indique que la classe est un composant spring
Repository: Traduit les exceptions en DataAccessibleException
Service: Partie logique et business. Cette annotation ne fait pas grand chose de plus
que Component, mais sert plus d'indication.