package eStoreSearch;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.PrintWriter;

public class FileInOut{

    
   
    
    /** 
     * @param fileName
     * @param pArrayList
     * @return ArrayList<Products>
     * @throws FileNotFoundException
     */
    public ArrayList<Products> addFromFile(String fileName, ArrayList<Products> pArrayList ) throws FileNotFoundException{
        ArrayList<Products> productList = new ArrayList<>();

        String productID = "";
        String description = "";
        String price = "";
        String year = "";
        String authors = "";
        String publisher = "";
        String make = "";
        
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File " + fileName + " not found or couldn't be opened. Close window to exit");
        }
        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            String[] onlyType = line.split("[\"=]+");
            String type = onlyType[2];

            if (type.equalsIgnoreCase("book")) { // books
                int i = 0;
                while(i < 6){
                    line = fileIn.nextLine();
                    if(i == 0){
                        type = line.replace("Product ID = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        productID = element;
                    }else if(i == 1){
                        type = line.replace("Description = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        description = element;
                    }else if(i == 2){
                        type = line.replace("Price = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        price =  element;
                    }else if(i == 3){
                        type = line.replace("Year = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        year =  element;
                    }else if(i == 4){
                        type = line.replace("Author = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        authors = element;
                    }else if(i == 5){
                        type = line.replace("Publisher = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        publisher = element;
                    }
                    i++;
                }
                Books book = new Books(productID, description, price, year, authors, publisher);
                productList.add(book);

            }else if(type.equalsIgnoreCase("electronics")){
                int i = 0;
                while(i < 5){
                    line = fileIn.nextLine();

                    if(i == 0){
                        type = line.replace("Product ID = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        productID = element;
                    }else if(i == 1){
                        type = line.replace("Description = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        description = element;
                    }else if(i == 2){
                        type = line.replace("Price = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        price =  element;
                    }else if(i == 3){
                        type = line.replace("Year = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        year =  element;
                    }else if(i == 4){
                        type = line.replace("Make = ", "");
                        String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
                        String element = noQuotes.replace("\\","");
                        make = element;
                    }
                    i++;
                }
                Electronics elec = new Electronics(productID, description, price, year, make);
                productList.add(elec);

            }
        }
        fileIn.close();
        return productList;
    }

    
    /** 
     * @throws Exception
     */
    public void createEmptyFile()throws Exception{
        try{
            File file = new File("createdListFile.txt");
            file.createNewFile();
            System.err.println("File created to store list: " + file);
        }catch(Exception e){
            throw new Exception("Error creating file 'createdListFile.txt'. Close window to exit");
           
        }
    }


 
    
    /** 
     * @param fileName
     * @param productList
     * @throws FileNotFoundException
     */
    public void printToFile(String fileName, ArrayList<Products> productList) throws FileNotFoundException {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream(fileName, false));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error opening file 'productList.txt'. Close window to exit");
         
        }
        for (int i = 0; i< productList.size(); i++) {
            outputStream.println(productList.get(i).toString());
        }
        outputStream.close();
        System.err.println("List stored in file " + fileName );
    }

  
}
