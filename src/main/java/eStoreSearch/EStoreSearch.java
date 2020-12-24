package eStoreSearch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Set;




public class EStoreSearch{
    private HashMap<String, ArrayList<Integer>> matchedKeys;

    
    /** 
     * @param productList
     * @param userKey
     * @return ArrayList<Integer>
     */
    //find index in productList where the userKey is at and add it to the foundIndexes array
    public ArrayList<Integer> matchEachWord(ArrayList<Products> productList, String userKey){
        ArrayList<Integer> foundIndexes = new ArrayList<Integer>();
        int index  = 0; // keep track of the index when in the foreach loop

        for(Products product : productList){     //go through product list
            String desc = product.getDescription();     //get each description
            String[] eachWord = desc.split("[ ]+");     //get each word from the description

            for(int i = 0; i<eachWord.length ; i++){ 
                if(eachWord[i].equalsIgnoreCase(userKey)){  //see if the userinput is the same as a word in the description
                    foundIndexes.add(index);        // add the index of where the product is in the product list
                }
            }
            index++;
        }
        
        return foundIndexes;
    }

    
    /** 
     * @param productList
     * @param userKeys
     */
    //create hash array from the key words and the matched words of the product list 
    private void setMap(ArrayList<Products> productList, String userKeys){
        String[] parsedKeys = userKeys.split("[ ]+");
        HashMap<String,ArrayList<Integer>> matchedKeys = new HashMap<String,ArrayList<Integer>>();

        //put the key word and the indexes product where that key word is contained in the description 
        for(int i = 0; i<parsedKeys.length; i++){
            matchedKeys.put(parsedKeys[i], matchEachWord(productList, parsedKeys[i])); 
        }
        this.matchedKeys = matchedKeys;
    }


    
    /** 
     * @param productList
     * @param userKeys
     * @return ArrayList<Integer>
     */
    //find overlapping indexes of stored description based on user key
    public ArrayList<Integer> findIntersection(ArrayList<Products> productList, String userKeys){
        ArrayList<Integer> intersect = new ArrayList<Integer>();
        setMap(productList, userKeys); //make the hash map based on the productlist and the user keys 

        //create iterator to go through hashmap to get the key and value
        Set<HashMap.Entry<String, ArrayList<Integer>>> matches = matchedKeys.entrySet();
        Iterator<HashMap.Entry<String, ArrayList<Integer>>> matchIter = matches.iterator();

        ArrayList<Integer> prevValue = new ArrayList<Integer>();
        ArrayList<Integer> nextValue = new ArrayList<Integer>();

        if(matchedKeys.size() > 1){  //when there's more than 1 key word, find the indexes that point to the same product  
        
            //check the previous index in the hash map to the next index and check if they contain the same indexes in the integer array 
            while(matchIter.hasNext()){ //check if there's another element in the hashMap 

                prevValue = matchIter.next().getValue();    //use iterator to get the value (int array) in hash map to store the array as an previous
                if(matchIter.hasNext()){    //check if there's one more than previous

                    nextValue = matchIter.next().getValue();    //use iterator to get the value (int array) set previous+1 

                    // go through both integer arrays and check if they have the same 
                    for(int val1 : prevValue){  
                        for(int val2 : nextValue){
                            if(val1 == val2){ //if they have the same number add it to the reduced list
                                intersect.add(val1);
                            }
                        }
                    }
                }
            }
            return intersect;
            
        }else{ //if there's only one key word, don't need to get intersection of the key words in productlist 
            return matchEachWord(productList, userKeys);
        }

    } 

    
    
    /** 
     * @param productList
     * @param userDesc
     * @param userPID
     * @param startYear
     * @param endYear
     * @return ArrayList<Products>
     */
    //for search with description, year and productID 
    private ArrayList<Products> searchDescription(ArrayList<Products> productList, String userDesc, String userPID, String startYear, String endYear){
        ArrayList<Integer> matches = new ArrayList<Integer>();
        ArrayList<Products> foundList = new ArrayList<>();
        matches = findIntersection(productList, userDesc);
        
        if( !userPID.isEmpty() && !startYear.isEmpty() || !endYear.isEmpty()){ //if both year and productID included
            
        
            if(!matches.isEmpty()){ //if there are no matched keys 
                for(int i = 0; i<matches.size(); i++){
        
                    int matchIndex = matches.get(i);
                    
                    if(productList.get(matchIndex).getProductID().equals(userPID)){ //check if it matches productID
        
                        if(searchYear(productList, startYear, endYear, matchIndex) == true){ //check if matches year
        
                            foundList.add(productList.get(matchIndex));
        
                        }
        
                    }
        
                }
        
            }
        
        }else if(!userPID.isEmpty() && (startYear.isEmpty() || endYear.isEmpty())){ //check reduced list with productid for match
        
            if(!matches.isEmpty()){
        
                for(int i = 0; i<matches.size(); i++){
        
                    int matchIndex = matches.get(i);    
        
                    if(productList.get(matchIndex).getProductID().equals(userPID)){
        
                        foundList.add(productList.get(matchIndex));
        
                    }
        
                }
        
            }
        
        }else if(userPID.isEmpty() && (!startYear.isEmpty() || !endYear.isEmpty())){ //check reduced list with year for match
        
            if(!matches.isEmpty()){
        
                for(int i = 0; i<matches.size(); i++){
        
                    int matchIndex = matches.get(i);
        
                    if(searchYear(productList, startYear, endYear, matchIndex) == true){
        
                        foundList.add(productList.get(matchIndex));
        
                    }
                }
            }
        
        }else if(userPID.isEmpty() && (startYear.isEmpty() || endYear.isEmpty())){ //only description entered 
        
            if(!matches.isEmpty()){
                for(int i = 0; i<matches.size(); i++){
                    int matchIndex = matches.get(i);    
                    foundList.add(productList.get(matchIndex));
                }
        
            }
        
        }
        
        return foundList;
    }  
        
   
    
    /** 
     * @param productList
     * @param userProductID
     * @return ArrayList<Products>
     */
    private ArrayList<Products> findID(ArrayList<Products> productList, String userProductID){
        ArrayList<Products> foundList = new ArrayList<Products>();
        for(Products product : productList){
            if( (product.getProductID()).equals(userProductID)){
                foundList.add(product);
            }
        }

        return foundList;
    }

    
    
    /** 
     * @param productList
     * @param startYear
     * @param endYear
     * @return ArrayList<Products>
     */
    private ArrayList<Products> searchYear(ArrayList<Products> productList, String startYear, String endYear){
        ArrayList<Products> foundList = new ArrayList<Products>();
        
        
        int startYearNum = 0;
        int endYearNum = 0;

        if(!startYear.isEmpty() && endYear.isEmpty()){   //check for 'startYear'
            String[] onlyYear = startYear.split("[ ]+");

            startYearNum = Integer.parseInt(onlyYear[0]);
            //find product
            for(Products product : productList){
                int year = Integer.parseInt(product.getYear()); //get year for product
                if(year >= startYearNum){
                    foundList.add(product);

                }
            }


        //check for 'endYear'
        }else if(!endYear.isEmpty() && startYear.isEmpty()){ 
            String[] onlyYear = endYear.split("[ ]+");
            endYearNum = Integer.parseInt(onlyYear[0]);

            //find product
            for(Products product : productList){
                int year = Integer.parseInt(product.getYear());  //get year for product
                if(year <= endYearNum){
                    foundList.add(product);
                }
            }


        
        //for 'startyear endyear'
        }else{
            String[] onlyYearS = startYear.split("[ ]+");
            String[] onlyYearE = endYear.split("[ ]+");

            startYearNum = Integer.parseInt(onlyYearS[0]);
            endYearNum = Integer.parseInt(onlyYearE[0]);

            //find product
            for(Products product : productList){
                int year = Integer.parseInt(product.getYear()); //get year for product
                if(year >= startYearNum && year <= endYearNum){
                    foundList.add(product);

                }
            }

        }

        return foundList;

    }
  

    
    /** 
     * @param productList
     * @param userYear
     * @param index
     * @return boolean
     */
    //find if index of arrayList sent from searchDesc also matches a year 
    private boolean searchYear(ArrayList<Products> productList, String startYear, String endYear, int index){
        int startYearNum = 0;
        int endYearNum = 0;
        
        if( !startYear.isEmpty() && endYear.isEmpty()){   //check for 'startYear'
            String[] onlyYear = startYear.split("[ ]+");
            startYearNum = Integer.parseInt(onlyYear[0]);

            //find product
            for(Products product : productList){ 
                int year = Integer.parseInt(product.getYear());  //get year from stored list
                if(year >= startYearNum){  //make sure its in range for user entered time period 
                    if(year == Integer.parseInt(productList.get(index).getYear())){  //see if the year from the list is the same as the one from the matched product Description 
                        return true;
                    }
                }
            }
            return false;

        //check for 'endYear'
        }else if(!endYear.isEmpty()&& startYear.isEmpty()){ 
            String[] onlyYear = endYear.split("[ ]+");
            endYearNum = Integer.parseInt(onlyYear[0]);

            //find product
            for(Products product : productList){
                int year = Integer.parseInt(product.getYear());  //get year from stored list
                if(year <= endYearNum){  //make sure its in range for user entered time period 
                    if(year == Integer.parseInt(productList.get(index).getYear())){  //see if the year from the list is the same as the one from the matched product Description
                        return true;
                    }
                }
            }
            return false;
        
        //for 'startyear endyear'
        }else if(!startYear.isEmpty() && !endYear.isEmpty()){
            String[] onlyYearS = startYear.split("[ ]+");
            String[] onlyYearE = endYear.split("[ ]+");

            startYearNum = Integer.parseInt(onlyYearS[0]);
            endYearNum = Integer.parseInt(onlyYearE[0]);

            //check products
            for(Products product : productList){
                int year = Integer.parseInt(product.getYear()); //get year from stored list
                if(year >= startYearNum && year <= endYearNum){ //make sure its in range for user entered time period 
                    if(year == Integer.parseInt(productList.get(index).getYear())){  //see if the year from the list is the same as the one from the matched product Description 
                        return true;
                    }
                }
            }
            return false;
        }

        return false;
    }
  
    
  
   
   /** 
    * @param productList
    * @param userProductID
    * @param userDescription
    * @param startYear
    * @param endYear
    * @return ArrayList<Products>
    * @throws InputMismatchException
    */
   public ArrayList<Products> searchList(ArrayList<Products> productList, String userProductID, String userDescription, String startYear, String endYear) throws InputMismatchException{
        String[] productID = userProductID.split("[ ]+");
        ArrayList<Products> foundList = new ArrayList<Products>();

        if(userDescription.isEmpty() && userProductID.isEmpty() && startYear.isEmpty() && endYear.isEmpty()){ // all three empty
            return productList;

        }else if(!userDescription.isEmpty()){
            foundList = searchDescription(productList, userDescription, productID[0], startYear, endYear);
            System.out.println("found list for when user description is not empty:\n" + foundList);
        }else{
            if(!userProductID.isEmpty()){
                foundList = findID(productList, productID[0]);
                System.out.println("found list for when user productId is not empty:\n" + foundList);

            }
            
            if(!startYear.isEmpty() || !endYear.isEmpty()){

                if(!startYear.isEmpty() && !endYear.isEmpty()){
                    int sYear = Integer.parseInt(startYear);
                    int eYear = Integer.parseInt(endYear);
                    if(sYear > eYear){
                        throw new InputMismatchException("start year must be less than or the same as end year");
                    }
                    foundList = searchYear(productList, Integer.toString(sYear), Integer.toString(eYear));
                    System.out.println("found list for when user start year and end year are not empty:\n" + foundList);

                }else{
                    foundList = searchYear(productList, startYear, endYear);
                    System.out.println("found list for when one of user start year or end year is not empty:\n" + foundList);
                }
            }
            
        }
        
        return foundList;
    }
    
    
    
    /** 
     * @param productList
     */
    public void printArray(ArrayList<Products> productList){
        if(productList.size() != 0){
            for(Products product: productList){
                System.out.println(product.toString());
                System.out.println();
            }
        }else{
            System.out.println("No products stored.");
        }
        
    }
   
}
