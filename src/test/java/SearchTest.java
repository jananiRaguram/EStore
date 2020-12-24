// package eStoreSearch;
// import java.util.ArrayList;

// //all methods are the same as one in EStoreSearch but modified for boolean
// //done for readbility purposes in EStoreSearch

// public class SearchTest{
//    /** 
//      * @param bookList
//      * @param elecList
//      * @param userProductID
//      */
//     public boolean findID(ArrayList<Products> productList, String userProductID){
//         for(Products product : productList){
//             if( (product.getProductID()).equals(userProductID)){
//                 System.out.println("Product found:");
//                 System.out.println(product.toString());
//                 return true;
//             }
//         }
//         System.out.println("no products found");
//         return false;        
//     }



//      /** 
//      * @param productList
//      * @param userYear
//      */
//     public boolean searchYear(ArrayList<Products> productList, String userYear){
//         int dashFound = 0;
//         int dashIndex = 0;
//         int userLength = userYear.length();

//         //find where dash is from the input 
//         for(int i = 0; i< userLength; i++){
//             if('-' == userYear.charAt(i)){
//                 dashIndex = i;
//                 dashFound++;
//             }
//         }
        
//         int startYear = 0;
//         int endYear = 0;

//         if(dashFound == 0){ //year without '-'
//             String[] onlyYear = userYear.split("[ -]+");
//             startYear = Integer.parseInt(onlyYear[0]);
//             System.out.println("products from start year " + startYear + ":");

//             //find product
//             int found = 0;
//             for(Products product : productList){
//                 int year = Integer.parseInt(product.getYear());
//                 if(year >= startYear){
//                     System.out.println(product.toString());
//                     System.out.println();
//                     found++;
//                 }
//             }
//             if(found == 0){
//                 return false;
//             }else{
//                 return true;
//             }
            
//         }else if( userLength < 7 && dashIndex > 3){   //check for 'startYear-'
//             String[] onlyYear = userYear.split("[- ]+");
//             startYear = Integer.parseInt(onlyYear[0]);
//             System.out.println("products from start year " + startYear + ":");

//             //find product
//             int found = 0;
//             for(Products product : productList){
//                 int year = Integer.parseInt(product.getYear()); //get year for product
//                 if(year >= startYear){
//                     System.out.println(product.toString()); //print matching year
//                     System.out.println();
//                     found++;
//                 }
//             }
//             if(found == 0){
//                 System.out.println("no products found");
//                 return false;
//             }else{
//                 return true;
//             }


//         //check for '-endYear'
//         }else if(dashIndex < 2){ 
//             String[] onlyYear = userYear.split("[- ]+");
//             endYear = Integer.parseInt(onlyYear[1]);
//             System.out.println("products before the end year " + endYear + ":");

//             //find product
//             int found = 0;
//             for(Products product : productList){
//                 int year = Integer.parseInt(product.getYear());  //get year for product
//                 if(year <= endYear){
//                     System.out.println(product.toString());     //print matching year
//                     System.out.println();
//                     found++;
//                 }
//             }
//             if(found == 0){
//                 System.out.println("no products found");
//                 return false;
//             }else{
//                 return true;
//             }
        
//         //for 'startyear-endyear'
//         }else if(userLength > 7 && dashIndex > 3){
//             String[] onlyYear = userYear.split("[-, ]+");
//             startYear = Integer.parseInt(onlyYear[0]);
//             endYear = Integer.parseInt(onlyYear[1]);

//             System.out.println("product from " + startYear + "-" +  endYear + ":");

//             //find product
//             int found = 0;
//             for(Products product : productList){
//                 int year = Integer.parseInt(product.getYear()); //get year for product
//                 if(year >= startYear && year <= endYear){
//                     System.out.println(product.toString()); //print matching year
//                     System.out.println(); 
//                     found++;
//                 }
//             }
//             if(found == 0){
//                 System.out.println("no products found");
//                 return false;
//             }else{
//                 return true;
//             }

//         }

//         return false;
//     }
  

// //****************************************************************************** */

//  /** 
//      * @param productList
//      * @param userYear
//      * @param index
//      * @return boolean
//      */
//     //find if index of arrayList sent from searchDesc also matches a year 
//     private boolean searchYear(ArrayList<Products> productList, String userYear, int index){
//         int dashFound = 0;
//         int dashIndex = 0;
//         int userLength = userYear.length();

//         //find where dash is from the input 
//         for(int i = 0; i< userLength; i++){
//             if('-' == userYear.charAt(i)){
//                 dashIndex = i;
//                 dashFound++;
//             }
//         }
        
//         int startYear = 0;
//         int endYear = 0;

//         if(dashFound == 0){ //year without '-'
//             String[] onlyYear = userYear.split("[ -]+");
//             startYear = Integer.parseInt(onlyYear[0]);

//             //find product
//             for(Products product : productList){ 
//                 int year = Integer.parseInt(product.getYear());  //get year from stored list
//                 if(year >= startYear){  //make sure its in range for user entered time period 
//                     if(year == Integer.parseInt(productList.get(index).getYear())){  //product year matches index sent from searchDesc
//                         return true;
//                     }
//                 }
//             }
//             return false;

//         }else if( userLength < 7 && dashIndex > 3){   //check for 'startYear-'
//             String[] onlyYear = userYear.split("[- ]+");
//             startYear = Integer.parseInt(onlyYear[0]);

//             //find product
//             for(Products product : productList){ 
//                 int year = Integer.parseInt(product.getYear());  //get year from stored list
//                 if(year >= startYear){  //make sure its in range for user entered time period 
//                     if(year == Integer.parseInt(productList.get(index).getYear())){  //see if the year from the list is the same as the one from the matched product Description 
//                         return true;
//                     }
//                 }
//             }
//             return false;

//         //check for '-endYear'
//         }else if(dashIndex < 2){ 
//             String[] onlyYear = userYear.split("[- ]+");
//             endYear = Integer.parseInt(onlyYear[1]);

//             //find product
//             for(Products product : productList){
//                 int year = Integer.parseInt(product.getYear());  //get year from stored list
//                 if(year <= endYear){  //make sure its in range for user entered time period 
//                     if(year == Integer.parseInt(productList.get(index).getYear())){  //see if the year from the list is the same as the one from the matched product Description 
//                         return true;
//                     }
//                 }
//             }
//             return false;
        
//         //for 'startyear-endyear'
//         }else if(userLength > 7 && dashIndex > 3){
//             String[] onlyYear = userYear.split("[-, ]+");
//             startYear = Integer.parseInt(onlyYear[0]);
//             endYear = Integer.parseInt(onlyYear[1]);

//             //check products
//             for(Products product : productList){
//                 int year = Integer.parseInt(product.getYear()); //get year from stored list
//                 if(year >= startYear && year <= endYear){ //make sure its in range for user entered time period 
//                     if(year == Integer.parseInt(productList.get(index).getYear())){  //see if the year from the list is the same as the one from the matched product Description 
//                         return true;
//                     }
//                 }
//             }
            
//             return false;
//         }

//         return false;
//     }






//        /** 
//      * @param productList
//      * @param userDesc
//      * @param userPID
//      * @param userYear
//      */
//     //for search with description, year and productID 
//     public boolean searchDescription(ArrayList<Products> productList, String userDesc, String userPID, String userYear){
//         EStoreSearch test = new EStoreSearch();
//         ArrayList<Integer> matches = new ArrayList<Integer>();
//         matches = test.findIntersection(productList, userDesc);
//         int found = 0;
        
//         for(int i = 0; i<matches.size(); i++){
//             int matchIndex = matches.get(i);
//             if( !userPID.isEmpty() && !userYear.isEmpty()){ //if both year and productID included

//                 if(matches.isEmpty()){ //if there are no matched keys 
//                     System.out.println("No product found with description: " + userDesc + "," + "year: " + userYear + "product Id: " + userPID);
//                 }else{
//                     if(productList.get(matchIndex).getProductID().equals(userPID)){ //check if it matches productID
//                         if(searchYear(productList, userYear, matchIndex) == true){ //check if matches year
//                             System.out.println("Product found with description: " + userDesc + "," + "year: " + userYear + "," + "product Id: " + userPID);
//                             System.out.println();
//                             System.out.println(productList.get(matchIndex));
//                             found++;
//                         }
//                     }
//                 }
               
//             }else if(!userPID.isEmpty() && userYear.isEmpty()){ //check reduced list with productid for match
//                 if(matches.isEmpty()){
//                     System.out.println("No product found with description: " + userDesc + ","  + "product Id: " + userPID);
//                 }else{
//                     if(productList.get(matchIndex).getProductID().equals(userPID)){
//                         System.out.println("Product found with description: " + userDesc + "," + "product Id: " + userPID);
//                         System.out.println();
//                         System.out.println(productList.get(matchIndex));
//                         found++;
//                     }
//                 }
//             }else if(userPID.isEmpty() && !userYear.isEmpty()){ //check reduced list with year for match
//                 if(matches.isEmpty()){
//                     System.out.println("No product found with description: " + userDesc + "," + "year: " + userYear);
//                 }else{
//                     if(searchYear(productList, userYear, matchIndex) == true){
//                         System.out.println("Product found with description: " + userDesc + "," + "year: " + userYear);
//                         System.out.println();
//                         System.out.println(productList.get(matchIndex));
//                         found++;
//                     }
//                 }
//             }else{ //only description entered 
//                 if(matches.isEmpty()){
//                     System.out.println("No product(s) found with description: " + userDesc);
//                 }else{
//                     System.out.println("Product(s) found with description: " + userDesc);
//                     System.out.println();
//                     System.out.println(productList.get(matchIndex));
//                     System.out.println();
//                     found++;
//                 }
//             }
            
//         }

//         if(found == 0){
//             return false;
//         }else{
//             return true;
//         }
        
//     }
// }
