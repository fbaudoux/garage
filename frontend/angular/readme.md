



Pour utiliser le front, il faut utiliser la commande :
docker-compose up --build -d

Pour développer le front angular , il faut se placer dans le répertoire angular
docker build -t frontdev -f DockerfileDev .
docker run -v "$PWD/garage":"/usr/src/app" --rm  -it --rm -it $(docker build -q -f DockerfileDev .) bash

On arrive dans un bash du conteneur et on peut executer



