package eStoreSearch;
import java.util.ArrayList;
import java.util.InputMismatchException;


public abstract class Products {
    private String productID;
    private String description;
    private String year;
    private String price;
    private ArrayList<Products> productList = new ArrayList<>();


    public Products(String productID, String description, String price, String year, ArrayList<Products> productList) throws InputMismatchException{
        if(checkProductID(productList, productID) == false){
            throw new InputMismatchException("Enter a product ID that's 6 digit number");
        }

        if(productIDDuplicate(productList, productID) == true){
            throw new InputMismatchException("Product ID already entered, please enter a new one");
        }

        if(setDescription(description) == false ){
            throw new InputMismatchException("Must enter a description");
        }

        if(setYear(year) == false){
            throw new InputMismatchException("Must enter a 4 digit year, from range 1000 - 9999");
        }

        setPrice(price);
        setProductID(productID);
    }

    public Products(String productID, String description, String price, String year){
        this.productID = productID;
        this.description = description;
        this.year = year;
        this.price = price;
    } 

    public Products(){
        productID = null;
        description = null;
        year = null;
        price = null;
    }

    
    /** 
     * @param productList
     */
    public void setArray(ArrayList<Products> productList){
        this.productList = productList;
    }

    
    /** 
     * @return ArrayList<Products>
     */
    public ArrayList<Products> getArray(){
        return productList;
    }

    public void printList(){
        for(Products product: getArray()){
            System.out.println(product.toString());
        }
    }

    /** 
     * @return String
     */
    //productID
    public String getProductID(){
        return productID;
    }

        
    /** 
     * @param bookList
     * @param elecList
     * @param newProductID
     * @return boolean
     */
    public void setProductID(String newProductID){
        productID = newProductID;
    }

    /** 
     * @return String
     */
    public String toString(){
        return "Product ID = " + "\"" + productID + "\"" + "\nDescription = " + "\"" + description + "\"" + "\nPrice = " + "\""  + price + "\"" + "\nYear = " + "\"" + year + "\"" ;
    } 

       /** 
     * @param bookList
     * @param elecList
     * @param userProductID
     * @return boolean
     */
    public boolean checkProductID(ArrayList<Products> productList, String userProductID){

        if(userProductID.isEmpty()){
            return false;
        }
        String[] productID = userProductID.split("[ ]+");

        int length = productID[0].length();
        if(length != 6 ){
          return false;
        }
        if(!productID[0].matches("[0-9]*$")){
          return false;
        }

        if(productList.isEmpty()){
            return true;
        }


        return true;
    }
 
     /** 
     * @param bookList
     * @param elecList
     * @param userProductID
     * @return boolean
     */
    public boolean productIDDuplicate(ArrayList<Products> productList, String userProductID){
        //check for duplicate in both lists
        for(Products product : productList){
            if(userProductID.equals(product.getProductID()) ){
                return true;
            }
        }
    
        return false;
    }

     /** 
     * @return String
     */
    //description
    public String getDescription(){
        return description;
    }
    
    
    /** 
     * @param newDescription
     * @return boolean
     */
    public boolean setDescription(String newDescription){
        if(newDescription.isEmpty()){
            return false;
        }else{
            description = newDescription;
            return true;
        }
    }

    
    /** 
     * @return String
     */
    //price
    public String getPrice(){
        return price;
    }
    
    
    /** 
     * @param price
     * @return boolean
     */
    public boolean setPrice(String price){
        String[] notDollerSign = price.split("/[$ ]");
        String newPrice = notDollerSign[0];
        if(newPrice.isEmpty() == false ){
            this.price = newPrice;
        }else{
            this.price = "N/A";
        }
        return true;
    }
   
    
    /** 
     * @return String
     */
    //year
    public String getYear(){
        return year;
    }

    
    /** 
     * @param newYear
     * @return boolean
     */
    public boolean setYear(String newYear){
        if(newYear.isEmpty() ||  checkYear(newYear) == false){
            return false;
        }else{
            year = newYear;
            return true;
        }
    }

    
    /** 
     * @param userYear
     * @return boolean
     */
    //check if year is 4digits long and between 1000 & 9999
    private boolean checkYear(String userYear){
        String[] year = userYear.split("[ ]+");
        int length = year[0].length();
        int yearVal = Integer.parseInt(year[0]);
        
        if( length != 4 || yearVal < 1000 || yearVal > 9999 ){
            return false;
        }else{
            return true;
        }
    }

    
}
