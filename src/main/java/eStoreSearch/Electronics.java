package eStoreSearch;

import java.util.ArrayList;
import java.util.InputMismatchException;


public class Electronics extends Products{
    private String make;
    

    public Electronics(String productID, String description, String price,  String year, String make, ArrayList<Products> productList ) throws InputMismatchException{
        super(productID,description,price,year,productList);
        
        setMake(make);
    }

    public Electronics(String productID, String description, String price,  String year, String make){
        super(productID,description,price,year);
        this.make = make;
    }

    public Electronics(){
        super();
        make = null;
    }
       
    
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return "Type = \"electronics\"\n" + super.toString() + "\nMake = " + "\"" +  make + "\"";
    } 

    

    
    /** 
     * @return String
     */
    //make
    public String getMake(){
        return make;
    }
    
    
    /** 
     * @param newmake
     * @return boolean
     */
    public boolean setMake(String newmake){
        if(newmake.isEmpty() == false){
            make = newmake; 
        }else{
            make = "N/A";
        }
        return true;
    }

    
}
   