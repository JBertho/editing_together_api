#!/usr/bin/env bash
mv main.java CompileCode.java
javac CompileCode.java
ret=$?
if [ $ret -ne 0 ]
then
  exit 2
fi
ulimit -s 100
timeout --signal=SIGTERM 10 java CompileCode
exit $?
