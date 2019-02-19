@ECHO OFF

::adminLoop.cmd
:loop
ECHO.
ECHO OCSP HeartBeat %COMPUTERNAME%
ECHO.
type admincheck.txt
Java OCSPCheck > admincheck.txt
timeout /t 15 /nobreak
CLS
goto :loop