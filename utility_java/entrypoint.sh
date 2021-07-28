#!/usr/bin/env bash
mv main.java Main.java
javac Main.java
ret=$?
if [ $ret -ne 0 ]
then
  exit 2
fi
ulimit -s 240
timeout --signal=SIGTERM 10 java Main
exit $?
