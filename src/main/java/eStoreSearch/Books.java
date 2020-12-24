package eStoreSearch;
import java.util.ArrayList;
import java.util.InputMismatchException;



public class Books extends Products{
    private String author;
    private String publisher;
    

    public Books(String productID, String description, String price, String year, String author, String publisher, ArrayList<Products> productList ) throws InputMismatchException {
        super(productID,description,price,year,productList);
        setAuthor(author);
        setPublisher(publisher);
        
    }

    public Books(String productID, String description, String price, String year, String author, String publisher ){
        super(productID,description,price,year);
        this.author = author;
        this. publisher = publisher;
    }

    public Books(){
        super();
        author = null;
        publisher = null;
    }

   
    
    /** 
     * @return String
     */
    public String toString(){
        return "Type = \"book\"\n" + super.toString() + "\nAuthor = " + "\"" + author  + "\"" + "\nPublisher = " + "\"" + publisher + "\"";
    } 

      
    
  
    
    /** 
     * @return String
     */
    public String getAuthor(){
        return author;
    }
    
    
  
    
    /** 
     * @param newAuthor
     * @return boolean
     */
    public boolean setAuthor(String newAuthor){
        if(newAuthor.isEmpty() == false){
            author = newAuthor; 
        }else{
            author = "N/A";
        }
        return true;
    }

    
  
    
    /** 
     * @return String
     */
    public String getPublisher(){
        return publisher;
    }
    
    
   
    
    /** 
     * @param newPublisher
     * @return boolean
     */
    public boolean setPublisher(String newPublisher){
        if(newPublisher.isEmpty() == false){
            publisher = newPublisher; 
        }else{
            publisher = "N/A";
        }
        return true;
    }

}
   