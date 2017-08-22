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
done
wait
