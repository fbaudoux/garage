Dojo - Level 2

Dans ce niveau, je viens ajouter une api REST pour la création d'équipes et de membres d'équipes.
C'est une API très simple avec quelques endpoints.
On pourra noter :

* la communication du controller vers l' application service. On peut notamment voir la création des objets Request et Response pour chaque méthode de l'application service.

* la communication de l'application service vers le domain service.

* les tests pour le controller et le domain service

On introduit également la notion d'Entity ( pas en  terme d'ORM pour le moment, mais en terme de DDD), par contre il n'y a pas de repository. Le domain service cache cette notion pour le moment et stocke tout en mémoire dans des ConcurrentHashmap.
C'est un choix volontaire car le choix de la technologie sous jacente pour le stockage amène des questions et des possibilités très nombreuses. Cela sera fait dans un level dédié.

