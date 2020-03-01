# dojo - Level0

Dans cette branche, on trouve le projet SpringBoot (2.2.5) avec un unique endpoint /ok 
et le test unitaire associé.

On peut quand même remarquer que 3 packages sont à la racine de l'application

* infrastructure
* application
* domain

Cela dénote l'intention de mettre en place une architecture hexagonale avec les dépendances comme suit :

infrastructure -> application -> domain -> ne dépend de rien
