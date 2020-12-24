package eStoreSearch;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menu extends JFrame {
    
    public static final int WIDTH =  800;
    public static final int HEIGHT = 600;
    private JPanel initialWindow, errorWindow;
    private JPanel searchWindow;
    private JPanel addWindow;
    private JPanel mainInput;
    private JPanel elecFormOptions;
    private JPanel bookFormOptions;
    private static String fileName;

    private JTextField pIDInput, descInput, priceInput, yearInput, authorInput, pubInput, makerInput, keywordInput,
            startYearInput, endYearInput;
    private JLabel productID, desc, price, year, type, author, publisher, maker;
    private JTextArea messageDisplay, searchDisplay;

    private static ArrayList<Products> productList = new ArrayList<Products>();

    private String productType;

    /************************************************************/

    private class InitialWindowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            initialWindow.setVisible(true);
            searchWindow.setVisible(false);
            addWindow.setVisible(false);
        }
    }

    /************************************************************/
    private class SearchWindowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            searchWindow.removeAll();
            add(searchWindow);
            searchPage();
            initialWindow.setVisible(false);
            searchWindow.setVisible(true);
            addWindow.setVisible(false);
        }
    }

    /************************************************************/
    private class QuitWindowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try{
                closeToFile();
            }catch(FileNotFoundException msg){
                setSize(800,200);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                getContentPane().setBackground(Color.RED);
                errorPage(msg.getLocalizedMessage());
            }
        }
    }

    /************************************************************/

    private class AddWindowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            addWindow.removeAll();
            add(addWindow);
            addPage();
            productType = "Book";
            initialWindow.setVisible(false);
            searchWindow.setVisible(false);
            addWindow.setVisible(true);
            elecFormOptions.setVisible(false);
            validate();
        }
    }

    /************************************************************/
    private class SendToFileOnExit extends JFrame implements WindowListener{
        public void windowOpened(WindowEvent e){}        
        
        public void windowClosing(WindowEvent e){

            try{
                closeToFile();
            }catch(FileNotFoundException msg){
                setSize(800,200);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                getContentPane().setBackground(Color.RED);
                errorPage(msg.getLocalizedMessage());
            }
        }

        public void windowClosed(WindowEvent e){
            
        }

        public void windowIconified(WindowEvent e){}

        public void windowDeiconified(WindowEvent e){}

        public void windowActivated(WindowEvent e){}

        public void windowDeactivated(WindowEvent e){}

    }

    /************************************************************/

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (productType.equals("Book")) {
                Books book = new Books();

                try {

                    ArrayList<Products> listCpy = new ArrayList<Products>(productList);
                    book = new Books(pIDInput.getText(), descInput.getText(), priceInput.getText(), yearInput.getText(),
                            authorInput.getText(), pubInput.getText(), listCpy);
                    productList.add(book);

                    for (Products product : productList) {
                        messageDisplay.setText("Added product:" + "\n\n" + product.toString());
                    }
                } catch (InputMismatchException c) {
                    // print in text area
                    messageDisplay.setText(c.getLocalizedMessage());
                }

            } else {
                Electronics elec = new Electronics();

                try {
                    ArrayList<Products> listCpy = new ArrayList<Products>(productList);
                    elec = new Electronics(pIDInput.getText(), descInput.getText(), priceInput.getText(),
                            yearInput.getText(), makerInput.getText(), listCpy);
                    productList.add(elec);

                    for (Products product : productList) {
                        messageDisplay.setText("Added product:" + "\n\n" + product.toString());
                    }
                } catch (InputMismatchException c) {
                    // print in text area
                    messageDisplay.setText(c.getLocalizedMessage());

                }
            }

            System.out.println("product list in menu:\n" + productList);
        }
    }

    /************************************************************/
    // clear all textfields
    private class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            pIDInput.setText("");
            yearInput.setText("");
            descInput.setText("");
            priceInput.setText("");

            authorInput.setText("");
            pubInput.setText("");

            makerInput.setText("");

            keywordInput.setText("");
            startYearInput.setText("");
            endYearInput.setText("");

            searchDisplay.setText("");

        }
    }

    /************************************************************/
    private class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            EStoreSearch search = new EStoreSearch();

            ArrayList<Products> listCpy = new ArrayList<Products>(productList);
            ArrayList<Products> foundList = new ArrayList<Products>();
            try {
                foundList = search.searchList(listCpy, pIDInput.getText(), keywordInput.getText(),
                        startYearInput.getText(), endYearInput.getText());
                if (foundList.isEmpty()) {
                    searchDisplay.setText("No products found");

                } else {
                    searchDisplay.append("products found:\n\n");
                    for (Products product : foundList) {
                        searchDisplay.append(product.toString() + "\n");
                        searchDisplay.append("\n");

                    }
                }
            } catch (InputMismatchException i) {
                searchDisplay.setText(i.getLocalizedMessage());

            }

        }
    }

    /************************************************************/
    private class DropdownListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String product = (String) cb.getSelectedItem();
            if (product.equals("Book")) {
                bookFormOptions.setVisible(true);
                elecFormOptions.setVisible(false);
                productType = "Book";
                validate();

            } else {
                productType = "Electronic";
                bookFormOptions.setVisible(false);
                elecFormOptions.setVisible(true);
                validate();
            }
        }
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Menu gui = new Menu(false, "");
       
        try{
            gui.setVisible(true);
            FileInOut fileIO = new FileInOut();
            ArrayList<Products> pList = new ArrayList<Products>();
            if (args.length == 1) {

                pList = fileIO.addFromFile(args[0], pList);
                productList.addAll(pList);
                fileName = args[0];

            } else {
                fileIO.createEmptyFile();
                fileName = "createdListFile.txt";
            }

        }catch(FileNotFoundException e){
            Menu window = new Menu(true, e.getLocalizedMessage());
            window.setVisible(true);
            gui.dispose();
        } catch (Exception e) {
            gui.dispose();
            Menu window = new Menu(true, e.getLocalizedMessage());
            window.setVisible(true);

        }



    }

    public Menu(Boolean error, String msg) {
        super("eStoreSearch");

        if (error == false && msg.equals("")) {
            setSize(WIDTH, HEIGHT);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new SendToFileOnExit());
            setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

            menuBar();

            // opening window
            openning();

            // initialize add window
            addPage();
            addWindow.setVisible(false);
            // initialize search window
            searchPage();
            searchWindow.setVisible(false);

        } else {
            
            setSize(800,200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(Color.RED);
            errorPage(msg);
        }
    }

    
    /** 
     * @throws FileNotFoundException
     */
    public void closeToFile()throws FileNotFoundException{

        FileInOut fileIO = new FileInOut();
        try{
            ArrayList<Products> listCpy = new ArrayList<Products>(productList);

            fileIO.printToFile(fileName, listCpy);
            System.exit(0);
        }catch(FileNotFoundException msg){
            throw new FileNotFoundException(msg.getLocalizedMessage());
        }
        
    }

    
    /** 
     * @param errorMsg
     */
    public void errorPage(String errorMsg) {
        errorWindow = new JPanel();
        errorWindow.setVisible(true);
        JLabel message = new JLabel(errorMsg);
        errorWindow.add(message);
        add(errorWindow);
    }

    public void openning(){

        initialWindow = new JPanel();
        initialWindow.setVisible(true);
        
        JPanel layout = new JPanel();
        layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
        JLabel welcome = new JLabel("Welcome to eStoreSeach");
        JLabel message = new JLabel("Choose a command from the \"Commands\" menu above for adding, searching products.");
        JLabel fileMsg = new JLabel("If a file was included at the start of the program the new list will be stored in the same file.");
        JLabel space = new JLabel("\n");
        JLabel fileMsg2 = new JLabel("If not the list will be stored in 'createdListFile.txt'.");

        layout.add(welcome);
        layout.add(message);
        layout.add(space);

        layout.add(fileMsg);
        layout.add(fileMsg2);

        initialWindow.add(layout);
        
        add(initialWindow);

    }

    public void menuBar(){

        JMenu storeMenu = new JMenu("Commands");

        JMenuItem addChoice = new JMenuItem("add product");
        addChoice.addActionListener(new AddWindowListener());
        storeMenu.add(addChoice);

        JMenuItem searchChoice = new JMenuItem("search product");
        searchChoice.addActionListener(new SearchWindowListener());
        storeMenu.add(searchChoice);

        JMenuItem quitChoice = new JMenuItem("quit");
        quitChoice.addActionListener(new QuitWindowListener());
        storeMenu.add(quitChoice);

        JMenuBar bar = new JMenuBar();
        bar.add(storeMenu);
        setJMenuBar(bar);

    }

    public void addPage(){
        //initialize add window

        addWindow = new JPanel();
        JLabel title = new JLabel("Adding Products");
        addWindow.add(title);
        add(addWindow, BorderLayout.NORTH);
        addWindow.setVisible(true);
        

        //form fields
        mainInput = new JPanel();

        //add the drop down 
        String[] productTypes = {"Book", "Electronic"};
        JComboBox productList = new JComboBox<>(productTypes);
        productList.setSelectedIndex(0);
        productList.addActionListener(new DropdownListener());

        type = new JLabel("Type:");
        mainInput.add(type);
        mainInput.add(productList);
        addWindow.add(mainInput);
        add(addWindow);

        //add fields for typing in info
        pIDInput = new JTextField(15);
        descInput = new JTextField(15);
        yearInput = new JTextField(15);
        priceInput = new JTextField(15);

        productID = new JLabel("Product ID:");
        desc = new JLabel("Description:");
        year = new JLabel("Year:");
        price = new JLabel("Price:");

        mainInput.setLayout(new BoxLayout(mainInput, BoxLayout.Y_AXIS));
        mainInput.add(productID); //label
        mainInput.add(pIDInput); //text field

        mainInput.add(desc);
        mainInput.add(descInput);

        mainInput.add(year);
        mainInput.add(yearInput);

        mainInput.add(price);
        mainInput.add(priceInput);

        addWindow.add(mainInput);
        add(addWindow, BorderLayout.EAST);

        //book specific fields
        authorInput = new JTextField(15);
        pubInput = new JTextField(15);

        author = new JLabel("Authors:");
        publisher = new JLabel("Publisher:");
        bookFormOptions = new JPanel();
        bookFormOptions.setLayout(new BoxLayout(bookFormOptions, BoxLayout.Y_AXIS));

        bookFormOptions.add(author);
        bookFormOptions.add(authorInput);
        bookFormOptions.add(publisher);
        bookFormOptions.add(pubInput);

        //add book fields to same layout as main fields
        mainInput.add(bookFormOptions);
        addWindow.add(mainInput);
        add(addWindow);


        //electronic specific fields
        makerInput = new JTextField(15);
        maker = new JLabel("Maker:");

        elecFormOptions = new JPanel();
        elecFormOptions.setLayout(new BoxLayout(elecFormOptions, BoxLayout.Y_AXIS));
        elecFormOptions.add(maker);
        elecFormOptions.add(makerInput);

        //add elec fields to same layout as main fields
        mainInput.add(elecFormOptions);
        addWindow.add(mainInput);
        add(addWindow);


        // add and reset buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        //reset
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonListener());
        buttonPanel.add(resetButton);

        //add
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());

        buttonPanel.add(addButton);
        addWindow.add(buttonPanel);
        add(addWindow, BorderLayout.WEST);


        //message field
        JPanel mPanel = new JPanel();
        JLabel message = new JLabel("Messages:");
        mPanel.add(message);
        messageDisplay = new JTextArea(15, 30);

        JScrollPane scrollText = new JScrollPane(messageDisplay);
        scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mPanel.add(scrollText);
        addWindow.add(mPanel, BorderLayout.SOUTH);
        add(addWindow);

        validate();

     
    }

    //initialize search window
    public void searchPage(){

        searchWindow = new JPanel();
        JLabel title = new JLabel("Search Products");
        searchWindow.add(title);
        add(searchWindow, BorderLayout.NORTH);
        searchWindow.setVisible(true);


        //form fields
        JPanel searchFields = new JPanel();
        pIDInput = new JTextField(15);
        keywordInput = new JTextField(15);
        startYearInput = new JTextField(15);
        endYearInput = new JTextField(15);

        productID = new JLabel("Product ID:");
        JLabel keyword = new JLabel("Description Keywords:");
        JLabel startYear = new JLabel("Start Year:");
        JLabel endYear = new JLabel("End Year:");

        searchFields.setLayout(new BoxLayout(searchFields, BoxLayout.Y_AXIS));
        searchFields.add(productID); //label
        searchFields.add(pIDInput); //tesearchFields

        searchFields.add(keyword);
        searchFields.add(keywordInput);
        
        searchFields.add(startYear);
        searchFields.add(startYearInput);
        
        searchFields.add(endYear);
        searchFields.add(endYearInput);

        searchWindow.add(searchFields);
        add(searchWindow);

        //search result field
        JPanel sPanel = new JPanel();
        JLabel searchResult = new JLabel("Search Results:");
        sPanel.add(searchResult);
        searchDisplay = new JTextArea(15, 30);

        JScrollPane scrollText = new JScrollPane(searchDisplay);
        scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        sPanel.add(scrollText);
        searchWindow.add(sPanel, BorderLayout.SOUTH);
        add(searchWindow);


        // search and reset buttons
        JPanel buttonPanel = new JPanel();
          buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
          
          //reset
          JButton resetButton = new JButton("Reset");
          resetButton.addActionListener(new ResetButtonListener());
          buttonPanel.add(resetButton);
  
          //add
          JButton searchButton = new JButton("Search/Print list");
          searchButton.addActionListener(new SearchButtonListener());
  
          buttonPanel.add(searchButton);
          searchWindow.add(buttonPanel);
          add(searchWindow, BorderLayout.WEST);

        validate();

    }

   
}
