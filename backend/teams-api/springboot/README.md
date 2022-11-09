L'API est ici développée en Java avec SpringBoot 2 

Pour utiliser l'API, il faut utiliser la commande :
docker-compose up --build -d
et l'API sera disponible sur le port 8082


Pour développer l'API, il faut utiliser la commande :

docker run -p 8082:8082 -v "$PWD/src":"/app/src"  --rm -it $(docker build -q -f DockerfileDev .) bash

On arrive dans un bash du conteneur et on peut executer 
./startDev.sh

on a les logs du serveurs dans le conteneur et on a la surveillance du code source avec redémmarage de springboot auto