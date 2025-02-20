@ECHO OFF

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM Compile all Java files manually specifying directories
javac -cp ..\src\main\java -Xlint:none -d ..\bin ^
    ..\src\main\java\Ace.java ^
    ..\src\main\java\ui\UI.java ^
    ..\src\main\java\commands\Commands.java ^
    ..\src\main\java\data_storage\TaskFileHandler.java ^
    ..\src\main\java\error_handling\InvalidTaskException.java ^
    ..\src\main\java\error_handling\MissingKeywordException.java ^
    ..\src\main\java\error_handling\MissingTaskIndexException.java ^
    ..\src\main\java\lib\Deadline.java ^
    ..\src\main\java\lib\Event.java ^
    ..\src\main\java\lib\ToDo.java ^
    ..\src\main\java\lib\Task.java ^
    ..\src\main\java\lib\TaskManager.java ^
    ..\src\main\java\lib\TaskType.java ^
    ..\src\main\java\menu\Menu.java ^
    ..\src\main\java\messages\Messages.java 



IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program, feed commands from input.txt file and redirect output to ACTUAL.TXT
java -classpath ..\bin Ace < input.txt > ACTUAL.TXT

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
