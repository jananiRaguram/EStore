// package eStoreSearch;

// import static org.junit.Assert.*;
// import java.util.ArrayList;
// import java.util.Scanner;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import org.junit.Before;
// import org.junit.Test;



// public class FileInOutTest{
//     private ArrayList<Products> testList;
    
//       /** 
//      * @param fileName
//      * @return ArrayList<String>
//      */
//     //same as the one in FileInOutbut changed to ArrayList<String> to use for JUnit test
//     public ArrayList<String> addFromFileTest(String fileName){
//         ArrayList<String> productList = new ArrayList<>();
//         String productID = "";
//         String description = "";
//         String price = "";
//         String year = "";
//         String authors = "";
//         String publisher = "";
//         String make = "";
        
//         Scanner fileIn = null;
//         try {
//             fileIn = new Scanner(new FileInputStream(fileName));
//         } catch (FileNotFoundException e) {
//             System.out.println("File " + fileName + " not found or couldn't be opened");
//             System.exit(0);
//         }
//         while (fileIn.hasNextLine()) {
//             String line = fileIn.nextLine();
//             String[] onlyType = line.split("[\"=]+");
//             String type = onlyType[2];

//             if (type.equalsIgnoreCase("book")) { // books
//                 int i = 0;
//                 while(i < 6){
//                     line = fileIn.nextLine();
//                     if(i == 0){
//                         type = line.replace("Product ID = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         productID = element;
//                     }else if(i == 1){
//                         type = line.replace("Description = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         description = element;
//                     }else if(i == 2){
//                         type = line.replace("Price = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         price =  element;
//                     }else if(i == 3){
//                         type = line.replace("Year = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         year =  element;
//                     }else if(i == 4){
//                         type = line.replace("Author = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         authors = element;
//                     }else if(i == 5){
//                         type = line.replace("Publisher = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         publisher = element;
//                     }
//                     i++;
//                 }
//                 productList.add(productID);
//                 productList.add(description);
//                 productList.add(price);
//                 productList.add(year);
//                 productList.add(authors);
//                 productList.add(publisher);
//             }else if(type.equalsIgnoreCase("electronics")){
//                 int i = 0;
//                 while(i < 5){
//                     line = fileIn.nextLine();

//                     if(i == 0){
//                         type = line.replace("Product ID = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         productID = element;
//                     }else if(i == 1){
//                         type = line.replace("Description = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         description = element;
//                     }else if(i == 2){
//                         type = line.replace("Price = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         price =  element;
//                     }else if(i == 3){
//                         type = line.replace("Year = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         year =  element;
//                     }else if(i == 4){
//                         type = line.replace("Make = ", "");
//                         String noQuotes = type.replaceAll("^\"+|\"+$", ""); // get rid of quotes
//                         String element = noQuotes.replace("\\","");
//                         make = element;
//                     }
//                     i++;
//                 }
//                 productList.add(productID);
//                 productList.add(description);
//                 productList.add(price);
//                 productList.add(year);
//                 productList.add(make);
//             }
//         }
//         fileIn.close();

//         return productList;
//     }

    
//     @Before
//     public void createProductList(){
//         ArrayList<Products> testList = new ArrayList<>();
//         testList.add(new Books("000025", "Absolute Java", "199.95","2015", "Walter Savitch, Kenrich Mock", "Pearson" ));
//         testList.add(new Electronics("000107", "MacBook Air 11\" Intel Dual-Core i5 1.6GHz", "1099.99","2013" , "Apple Inc."));
//         this.testList = testList;
//     }

  
//     @Test 
//     public void testWritingToFile(){
//         FileInOut test = new FileInOut();
//         test.printToFile("JUnitTestOutput.txt", testList);
//     }

//     @Test
//     public void testReadFile(){
//         ArrayList<String> fileList = addFromFileTest("originalList.txt");
//         ArrayList<String> newTestList = new ArrayList<>();
//         newTestList.add("000025");
//         newTestList.add("Absolute Java");
//         newTestList.add("199.95");
//         newTestList.add("2015");
//         newTestList.add("Walter Savitch, Kenrich Mock");
//         newTestList.add("Pearson");
//         newTestList.add("000107");
//         newTestList.add("MacBook Air 11\" Intel Dual-Core i5 1.6GHz");
//         newTestList.add("1099.99");
//         newTestList.add("2013");
//         newTestList.add("Apple Inc.");
//         assertEquals(newTestList, fileList);

//     }
// }
