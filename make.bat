@echo off
cls
echo ******* wiki make app *******

echo ******* call config.bat *******
call config.bat
call define.bat
call define_init.bat

set BUILD_MODE=%1

if "%BUILD_MODE%"=="release" (
	echo *** preprocess source ***
	if exist tmp rd /s /q tmp
	md tmp
	copy "%MASTER_SRC_PATH%"\* tmp\ > NUL
    cd tmp
    for %%i in (*.java) do (
        "%CPP_PATH%\cpp.exe" -C -P %DEFINES% %%i %SRC_PATH%\%%i
    )
	cd %~dp0
	echo *** build release ***
	del %PRJ_DIR%\bin\*.apk
	call ant release
	echo *** sign key ***
	%JAVA_HOME%\bin\jarsigner.exe -verbose -keystore %KEY_ALIAS%_KEY.keystore -storepass %KEY_PASS% -keypass %KEY_PASS% %PRJ_DIR%\bin\%PRJ_NAME%-release-unsigned.apk %KEY_ALIAS%
)
if "%BUILD_MODE%"=="debug" (
	del *.apk
	echo *** preprocess source ***
	if exist tmp rd /s /q tmp
	md tmp
	copy "%MASTER_SRC_PATH%"\* tmp\ > NUL
    cd tmp
    for %%i in (*.java) do (
        "%CPP_PATH%\cpp.exe" -C -P %DEFINES% %%i %SRC_PATH%\%%i
    )
	cd %~dp0
	echo *** build debug ***
	call ant debug
)
if "%BUILD_MODE%"=="clean" (
	echo *** build clean ***
	rmdir tmp /s /q
	call ant clean
)
if "%BUILD_MODE%"=="emu" (
	echo *** start emulator %ANDROID_EMU% ***
	call %ANDROID_SDK_PATH%\tools\emulator -avd %ANDROID_EMU%
)
if "%BUILD_MODE%"=="install" (
	echo *** install app ***
	if exist %PRJ_DIR%\bin\%PRJ_NAME%-debug.apk (
		call %ANDROID_SDK_PATH%\platform-tools\adb install -r %PRJ_DIR%\bin\%PRJ_NAME%-debug.apk
	) else (
		call %ANDROID_SDK_PATH%\platform-tools\adb install -r %PRJ_DIR%\bin\%PRJ_NAME%-release-unsigned.apk
	)
)
if "%BUILD_MODE%"=="uninstall" (
	echo *** uninstall app ***
	call %ANDROID_SDK_PATH%\platform-tools\adb uninstall %PACK_NAME%
)
if "%BUILD_MODE%"=="log" (
	echo *** adb logcat ***
	call %ADB_PATH%\adb logcat
)
if "%BUILD_MODE%"=="logc" (
	echo *** adb logcat ***
	call %ADB_PATH%\adb logcat -c
)
if "%BUILD_MODE%"=="key" (
	echo *** make key for sign key ***
	%JAVA_HOME%\bin\keytool.exe -genkey -v -keystore %KEY_ALIAS%_KEY.keystore -alias %KEY_ALIAS% -keyalg RSA -keysize 2048 -validity 10000
)

