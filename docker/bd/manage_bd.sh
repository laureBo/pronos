#!/bin/bash

echo "MENU"
echo "1 : creer les dossiers pour le mount"
echo "2 : creer l image docker pour la bd"
echo "3 : lancer l'image docker pour la bd"

read answer

case "$answer" in "1")
	mkdir mount
	mkdir mount/logs
	mkdir mount/database
	mkdir mount/config
esac

case "$answer" in "2")
	echo "Creation de l image postgrespronos"
	sudo docker build -t postgrespronos .
	echo "Fin de creation image"
esac

case "$answer" in "3")
	echo "Lancement de l image postgrespronos"
	sudo docker run \
	-d --restart unless-stopped \
	--name postgrespronos \
	-p 5432:5432  \
	--mount type=bind,source="$(pwd)"/mount/config,target=/etc/postgresql \
	--mount type=bind,source="$(pwd)"/mount/logs,target=/var/log/postgresql \
	--mount type=bind,source="$(pwd)"/mount/database,target=/var/lib/postgresql \
	postgrespronos &	
	echo "Fin de start bd"
esac