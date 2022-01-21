@if "%DEBUG%"=="" @echo off

if "%OS%"=="Windows_NT" setlocal EnableDelayedExpansion

set ARGS=%*
call gradlew --quiet --console=plain installDist && call "build\install\simple\bin\simple" %ARGS%

if "%OS%"=="Windows_NT" endlocal
