@echo off
setlocal enabledelayedexpansion

set "arguments="

for %%i in (%*) do (
    set "arguments=!arguments! %%i"
)

echo Versioner-Q
groovy -cp %~dp0 %~dp0/versioner-q.groovy %arguments:~1%