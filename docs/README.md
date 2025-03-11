# ACE Task Manager User Guide

ACE is a command-line task manager designed to help you manage your tasks efficiently. Whether it’s tracking tasks, deadlines, or events, ACE provides simple commands to keep you organized and productive


## Features

1. Add tasks as to-dos, deadlines, and events
2. Mark tasks as done or unmark them
3. Delete tasks that you no longer need to accomplish
4. View all tasks or search for tasks using keywords
5. Local tasks storage which saves your tasks to your local machine every time you close the application
6. Exit the application smoothly by keying in 'bye'


## Creating Tasks

Simply enter a task description for a To-Do such as:
```
Buy groceries
```

Additionally, you can specify the due date of the task to create a Deadline, or its start and end times for an Event:
```
Finish CS2113 weekly quizzes /by 2359hrs
```
```
Dinner with mom /from tomorrow 6pm /to 9pm
```

## Commands

Below are the commands you can use to interact with ACE:


### List Tasks
```
list
```  
View your task list. This is how your task list may look like with some tasks:
```
________________________________________________________________________________

list
>
1. [T][ ] Buy groceries
2. [D][ ] Finish CS2113 weekly quizzes (by: 2359hrs)
3. [E][ ] Dinner with mom (from: tomorrow 6pm to: 8pm)

________________________________________________________________________________
```

### Find Tasks
```
find [keyword]
```  
Input this command followed by a keyword in your tasks to search for matching tasks

### Mark Task as Complete
```
mark [task ID]
```  
Input this command followed by the desired task ID to mark it as complete

### Unmark Task
```
unmark [task ID]
```  
Input this command followed by the desired task ID to unmark it

### Delete Task
```
delete [task ID]
```  
Input this command followed by the desired task ID to delete it

### Exit the Application
```
bye
```  
Exit the ACE task manager application


## Prerequisites

- JDK 17 (Ensure you have Java Development Kit 17 installed to run the application)
- ACE JAR file (Ensure you have the compiled JAR file to run the application)


## Setup Instructions

1. Download the ACE JAR file to your local machine
2. Open a command prompt or terminal in the directory where the JAR file is located
3. Run the application using the following command:
```
java -jar Ace.jar
```
4. If the setup is correct, you should see the following output:

```
________________________________________________________________________________

Hello! I am...
 _____   _____   _____
|  _  | |  __ | |  ___|
| |_| | | |     | |___ 
|  _  | | |     |  ___|
| | | | | |___  | |___
|_| |_| |_____| |_____|

How can I assist you?


COMMANDS: 

list: View task list
find: Input this command followed by a keyword in your tasks
mark: Input this command followed by the desired task ID to mark it as complete
unmark: Input this command followed by the desired task ID to unmark it
delete: Input this command followed by the desired task ID to delete it
bye: Exit the program


________________________________________________________________________________
```
