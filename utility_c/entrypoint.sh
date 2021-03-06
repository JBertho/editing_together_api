#!/usr/bin/env bash
gcc main.c -o exec
ret=$?
if [ $ret -ne 0 ]
then
  exit 2
fi
ulimit -s 240
timeout --signal=SIGTERM 5 ./exec 
exit $?
