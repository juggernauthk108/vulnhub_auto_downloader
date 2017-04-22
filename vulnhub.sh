#!/bin/bash
touch new old dif
while :
do
	curl -L -s www.vulnhub.com | grep \".*.torrent\" | sed 's/.torrent//g' | cut -f2 -d\"  > new
	comm -3 new old > dif
	for i in `cat dif`
	do
		wget $i
	done  
	mv new old
	sleep 15m
done

