#!/usr/bin/env bash

hosts=("biu51" "biu52" "biu53" "biu54" "biu55")

if [ $# -eq 0 ];
then
    exit
fi

COMMAND=$@



for host in ${hosts[*]}
do
  echo -e "\033[32m ---$host---$COMMAND \033[0m"
  ssh root@$host "$COMMAND"
done


