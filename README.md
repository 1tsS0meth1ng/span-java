# Soccer League Manager
## Requirements
Java version 10  
Maven version 3.6.3

## General Steps 
1. Download the code.
2. Open Terminal within the span-project directory.

## Main Run Details
3. Run the script by entering  "java -jar span-java-1.0-SNAPSHOT" and hitting enter.

## Unit test
3. Run the script by entering "mvn clean test" and hitting enter.

# Screens
## Main View
```
1. Import a file
2. Display the leader board
3. Quit
Enter in the number of your selection:
```

### 1. Import matches
Imports values from a file to populate the matches played by the teams.  

Inputs:
1. Requests file directory.  
#### Example 1:
Correct data entered:
```
--------------------------------------------------------------------
1. Import a file
2. Display the leader board
3. Quit
Enter in the number of your selection:
1
Please enter the path of the file:
C:\SoccerLeagueTest.txt
File successfully imported!
```

#### Example 2:
Incorrect data entered:  
```
--------------------------------------------------------------------
1. Import a file
2. Display the leader board
3. Quit
Enter in the number of your selection:
1
Please enter the path of the file:
C:/NotThere.txt
Sorry that file could not be found.
```

If the file cannot be accessed "The given file cannot be accessed" will be displayed.

### 2. View Leader Board
Displays the current leader board with the current data.
Examples:  
If empty:
```
--------------------------------------------------------------------
1. Import a file
2. Display the leader board
3. Quit
Enter in the number of your selection:
2
No matches have been recorded
```
If not empty (test data used):  
```
--------------------------------------------------------------------
1. Import a file
2. Display the leader board
3. Quit
Enter in the number of your selection:
2
1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pts
```
### 3. Go back
Inputs:
1. Requests whether or the user is sure they want to quit. 
If 'y' is entered 
```
--------------------------------------------------------------------
1. Import a file
2. Display the leader board
3. Quit
Enter in the number of your selection:
3
Are you sure you want to exit?(y/n):
y
Have a fantastic day
```
If 'y' is entered 
```
--------------------------------------------------------------------
1. Import a file
2. Display the leader board
3. Quit
Enter in the number of your selection:
3
Are you sure you want to exit?(y/n):
n
(program execution continues)
```

If a value other than 'y' or 'n' is entered 
```
--------------------------------------------------------------------
1. Import a file
2. Display the leader board
3. Quit
Enter in the number of your selection:
3
Are you sure you want to exit?(y/n):
dsaodas
Sorry that is an invalid input
Are you sure you want to exit?(y/n):
```

