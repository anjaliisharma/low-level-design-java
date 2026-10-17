#!/usr/bin/env bash
# Compiles and runs SOLID demos using only the JDK.
set -e
cd "$(dirname "$0")"
MODULES=${1:-"srp ocp lsp isp dip"}
for m in $MODULES; do
  out="target-plain/$m"
  rm -rf "$out"; mkdir -p "$out"
  javac -d "$out" $(find "$m/src/main/java" -name "*.java")
  java -cp "$out" "com.solid.$m.Main"
  echo; echo "----------------------------------------"; echo
done
