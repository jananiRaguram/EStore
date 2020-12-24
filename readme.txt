Author: Janani Raguram 1092071
Project: CIS2430 A3


run: gradle build                              

    *** if file in included:                    (you have to copy the list.txt file to the same folder as the one below)
        java eStoreSearch.Menu list.txt         (in the folder EStore/build/classes/java/main) 
                                                

    or run (after gradle build) with:
        gradle run --args=list.txt              (in EStore)


    *** if file not included:
        java eStoreSearch.Menu                 (in the folder EStore/build/classes/java/main) 

    or run (after gradle build) with:
        gradle run                             (in EStore)
                   


Program description:
The program simulates an e-store, where the user can add and search for products.
If the file is given in the the command line, it will be loaded into the program and the user can add to that list. If not, a new file will be created and the user can add to the created file. 
The program starts with the home page popping up as a welome page for the user, informing them about how to use the program.
The user can use the 'Commands' menue to 'add product', or 'search product'. 

Add:
If the user wants to 'add product' the adding page will be shown and the use can use the 'Type' drop down menu to choose if they want to add a book or electronic along with it's product specific fields.  
For each product type a 6-digit product ID, description, and a 4-digit year is required. The other fields are optional to the user and can leave them blank.
After filling in the fields to add the product the user can press the 'add' button and the added list of will be shown on the 'messages' board. 
To clear the form and the message board, the user can press the 'reset' button. 

Search: 
If the user wants to 'search product' the search page will be shown and the use can use the form to fill in the fields they want to search for and press the 'search' button. If all the fields are empty,
the entire product list stored in the program will be printed to the 'search results' message board
To clear the form and the message board, the user can press the 'reset' button. 

Quit:
To exit from the program the user can use the 'quit' option from the 'commands' dropdown menu or the normal exit button for the window.


Assumptions:
The input file is included at the start of the program that it's formated correctly, with no repeated product ID's, correct range for the year, all fields are included included the optional ones. 
When searching for a year if the user doesn't put a dash in the year, it's assumed to be treated as a start year. If the user doesn't enter a productID, description, or time 
period for the search, the program will print all the product entries. 



Additional Assumtions for Search:
When a user enters all three search items, the search for the product starts from matching the description keys then another search is done to find if the productID matches and the time range match.
If the entered productID and the year is not in the proper range, it will be considered as 'product not found'. 
For searches with only the product ID/time range and the description, the description will be searched first to find its matches then match the product ID/time range. 
If both don't match then it's considered 'product not found'. 
For any of these cases if there are no matches to the description then it's considered as 'product not found'. 



Error Checks:
The program won't take input that are not formated correctly, so the user will be promted through the 'message' board to enter the correct format each time.


Add checks:
The productID, description, and year are mandetory for the user to enter. 
For the productID the user must enter 6-digit productID consisting of only numbers.
The year must be a 4-digit number from 1000-9999

search checks:
The start year must be of lower value than the end year. If not a message will be shown to the user indicating them of the mistake. 

Outputs:
After each time the user adds a product a summary of what they added will be printed out to the 'message' board. If they didn't enter anything for the optional fields "N\A" will be printed next to that field.
When the presses 'quit' or presses the exit button to teminate the program, all the products stored will be written to the same file used during input. Any additional products will be formatted the same as the loaded products, so the same
file can be used again.

If the file was not included during the start of the program the file 'createdListFile.txt' will be created and the list will be stored there. 

