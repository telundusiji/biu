#!/usr/bin/env bash

hosts=("biu51" "biu52" "biu53" "biu54" "biu55")

if [ $# -ne 2 ];
then
    exit
fi

SOURCE_PATH=$1
TARGET_PATH=$2

CURRENT_HOST=`hostname`

for host in ${hosts[*]}
do
  if [ $CURRENT_HOST != $host ];
  then
    echo -e "\033[32m ---$host---$SOURCE_PATH---$TARGET_PATH \033[0m"
    scp -r $SOURCE_PATH root@$host:$TARGET_PATH
  fi
done