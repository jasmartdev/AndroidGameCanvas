echo *** define flag ***
set DEFINES=
if "%USE_SPLASH_SCREEN%"=="1" set DEFINES=%DEFINES% -D USE_SPLASH_SCREEN
if "%USE_RESET_TOUCH%"=="1" set DEFINES=%DEFINES% -D USE_RESET_TOUCH
if "%USE_BG_BUFFER%"=="1" set DEFINES=%DEFINES% -D USE_BG_BUFFER