@echo off
setlocal enabledelayedexpansion
cd /d "%~dp0"
set MODULES=%1
if "%MODULES%"=="" set MODULES=srp ocp lsp isp dip
for %%m in (%MODULES%) do (
  if exist target-plain\%%m rmdir /s /q target-plain\%%m
  mkdir target-plain\%%m
  dir /s /b %%m\src\main\java\*.java > target-plain\%%m\sources.txt
  javac -d target-plain\%%m @target-plain\%%m\sources.txt
  java -cp target-plain\%%m com.solid.%%m.Main
  echo.
  echo ----------------------------------------
  echo.
)
