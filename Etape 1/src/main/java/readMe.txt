Question 1

 - La methode toString permet d'afficher des informations sur l'objet en question (proprites, valeurs, ...)
 - La methode equals permet de comparer l'instance d'une classe / objet avec un autre : si ils sont egaux ou non
 - La methode hashCode permet permet d'envoyer un code / une valeur unique d'un objet. Elle sert notamment a pouvoir stocker un objet dans la classe HashTable.

Question 2

 - La classe ArrayList implemente l'interface List. Mais il est, de maniere generale, preferable de renvoyer une List plutot que tout autre classe l'implementant,
ce qui permet de pouvoir utiliser toute classe implementant List.

- Les deux interfaces sont quasiment identiques, la difference pres que HashMap est une sous interface de Map. Comme dit precedemment, il est donc preferable, de
maniere generale, d'utiliser Map plutot que HashMap quand il s'agit d'un argument de methode ou de son renvoi.

- HashSet est une classe implementant l'interface Set.

- L'interface List est une collection d'objets non ordonnees. L'utilisateur peut y acceder grace aux index. L'interface Set est une collection d'elements uniques,
ordonnee ou non.