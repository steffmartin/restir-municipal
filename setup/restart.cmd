@echo off
timeout /t 10 /nobreak > NUL

set "tempDir=%TEMP%"
set "sourceFile=%tempDir%\SAART.jar"
set "destinationFile=.\SAART.jar"
move /y "%sourceFile%" "%destinationFile%"

start "Sistema SAART" "SAART.lnk"
