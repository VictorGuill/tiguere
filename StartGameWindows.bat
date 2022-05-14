::=====================================================================
:: This game uses UTF-8 characters not available in CMD by default.
::
:: To guarantee UTF-8 is used in the CMD, this script changes your 
:: current code page before playing and restores it back on exit.
::
::
:: If you want to see your current code page, use 'chcp' in a CMD.
:: More information about windows code pages below:
:: https://docs.microsoft.com/en-us/windows/win32/intl/code-page-identifiers
::=====================================================================


@echo off

::Gets 'chcp' output and stores the number in %_cp_%
for /f "tokens=2 delims=:" %%G in ('chcp') do Set _cp_=%%G

::Change CMD code page to UTF-8
chcp 65001

::Run jar file with UTF-8
java -Dfile.encoding=UTF-8 -jar "./dist/GameJava.jar"

::Restore original user code page on CMD
chcp %_cp_%
@pause