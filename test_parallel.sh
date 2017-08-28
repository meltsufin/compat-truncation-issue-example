#!/bin/bash

url="$1"
count="$2"

if [ "$url" == "" -o "$count" == "" ]; then
  echo "Usage: $0 <url> <count>"
  exit
fi

for (( i=1; i<=$count; i++ )); do
  echo "$i: fetching $url"
  curl -I -s $url | grep HTTP/1.1 &
#  waitSeconds=`echo "scale=5; $RANDOM / 32767 / 10" | bc`
#  echo waitSeconds = $waitSeconds
#  sleep $waitSeconds
done
wait
