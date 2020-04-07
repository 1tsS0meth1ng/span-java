# Soccer League Manager
## Requirements
Java version 10
## Run Details
1. Download the code.
2. Open Terminal within the span-project directory.
3. Run the script by entering  and hitting enter.

# Screens
## Main View
```
Welcome the the league manager
1. View a list of the current leagues
2. Add a league
3. View a select league
4. Quit
Input the number of the menu item which you would like to interact with: 
```

### 1. View a list of the current leagues
Prints a list of the registered leagues.  
If no leagues are registered "No tournaments registered" will be displayed.

### 2. Add a league
Allows the user to add a league.  
Inputs:
1. Requests a league name.

### 3. View a select league
Navigates to a leagues page after the user inputs a valid league name.  
Inputs:
1. Requests a league name.

If an invalid league name is input "Invalid tournament name" will be output.

### 4. Quit
Ends the program execution.

## Tournament Management Screen
```
Welcome to Demo's page
1. Import matches
2. View Leader Board
3. Go back
Input the number of the menu item which you would like to interact with:
```

### 1. Import matches
Imports values from a file to populate the matches.  

Inputs:
1. Requests file directory.

If the file cannot be accessed "The given file cannot be accessed" will be displayed.

### 2. View Leader Board
Displays the current leader board with the current data.
Examples:  
If empty:
```
Leader Board:
------------------------------------------------------
```
If not empty (test data used):  
```
Leader Board:
1. Tarantulas 6 pts
2. Lions 5 pts
3. Snakes 1 pts
4. FC Awesome 1 pts
5. Grouches 0 pts
------------------------------------------------------
```
### 3. Go back
Returns to the main view.
