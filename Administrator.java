import java.util.ArrayList;
import javax.swing.*;
public class Administrator extends User {

    private ArrayList <Book> books;


    // Constructor
    public Administrator(String name, String account, String password) {  
        super(name, account, password);
        books = Library.books;
    }// Constructor






    // mainPage_administrator
    public void mainPage_administrator(){
        String options [] = {"Add Book", "Modify Book", "Delete Book", "Information"};

        int function = JOptionPane.showOptionDialog(null, 
                                        "Welcome " + getName(),
                                        "Main Page",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]
                                        );
        if (function == -1){System.exit(0);}
        go_to_function(function);
    } // End mainPage_administrator







    // go_to_function
    public void go_to_function(int function){
        switch(function){
            case 0:
                addBook();
                break;
            case 1:
                modifyBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                break;
        }
     } // End go_to_function 







     // addBook
    public void addBook(){
        JTextField title = new JTextField();
        JTextField author = new JTextField();
        JTextField publisher = new JTextField();
        int x;
        boolean exist = false;
        String options [] = {"Add", "Cancel"};
        Object information[] = {"Please enter the information of the book.\n", 
                                "title",title,
                                "author", author,
                                "publisher", publisher};

        x = JOptionPane.showOptionDialog (null,information, 
        "登入",JOptionPane.DEFAULT_OPTION,1,null, options, options[0]) ;

        if(x == -1){System.exit(0);}

        for (Book recent: books){
            if(title.getText().equals(recent.getTitle())){
                exist = true;
            }
        }

        if(exist == false){ // 書不存在
            if(x == 0){ // add
                books.add( new Book( title.getText() , author.getText(), publisher.getText() , "available") );
                JOptionPane.showMessageDialog(null, "Add successfully\nGoing back to Main Page");
                mainPage_administrator();
                JOptionPane.showMessageDialog(null, "Error\nYou are at addBook-1");
            }
            else if(x == 1){ // cancel add
                mainPage_administrator();
                JOptionPane.showMessageDialog(null, "Error\nYou are at addBook-2");
            }
        }
        else{  // 書已經存在
            x = JOptionPane.showOptionDialog (null,"The book are already exist.\nKeep adding or Cancel.", 
            "登入",JOptionPane.DEFAULT_OPTION,1,null, options, options[0]) ;

            if(x == 0){ // add
                addBook();
                JOptionPane.showMessageDialog(null, "Error\nYou are at addBook-3");
            }
            else if (x == 1){ //cancel
                mainPage_administrator();
                JOptionPane.showMessageDialog(null, "Error\nYou are at addBook-4");
            }
            else{System.exit(0);}
        }
        

    }// End addBook





    // modifyBook
    public void modifyBook(){
        
        String input;
        Book temp = null;
        String options[] = {"Modify", "Cancel"};
        int x;
        JTextField title = new JTextField();
        JTextField author = new JTextField();
        JTextField publisher = new JTextField();
        Object information[] = {"Enter book's information.",
                                "Title",title,
                                "Author", author,
                                "Publisher",publisher};

        input = JOptionPane.showInputDialog(null, "What book do you want to make change");
        for (Book recent : books){
            if(input.equals(recent.getTitle())){
                temp = recent;
                break;
            }
        }
        if(temp == null){ // 沒找到書

            x = JOptionPane.showOptionDialog(null, 
                                        "The book doesn't exist.\ndo you still want to make change of the book or go back to main page",
                                            "Modify",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if(x == 0 ){ // modify
                modifyBook();
                JOptionPane.showMessageDialog(null, "Erroe\nYou are at modifyBook-1");
            }
            else if ( x == 1){ // back to main page
                mainPage_administrator();
                JOptionPane.showMessageDialog(null, "Erroe\nYou are at modifyBook-2");
            }
            else{System.exit(0);}
        }
        else{ // 有找到書
            title.setText( temp.getTitle() );
            author.setText( temp.getAuthor() );
            publisher.setText( temp.getPublisher());

            x = JOptionPane.showOptionDialog (null,information, // 輸入帳號密碼
            "登入",JOptionPane.DEFAULT_OPTION,1,null, options, options[0]) ;
            
            if(x == 0){ //確認更改
                temp.setTitle(title.getText());
                temp.setAuthor(author.getText());
                temp.setPublisher(publisher.getText());

                JOptionPane.showMessageDialog(null, "Modify successfully\nGo back to Main Page");
                mainPage_administrator();
                JOptionPane.showMessageDialog(null, "Error\nYou are at modifyBook-3");
            }
            else{ //取消更改
                mainPage_administrator();
                JOptionPane.showMessageDialog(null, "Error\nYou are at modifyBook-4");
                }           
            }
        
    } // End modyfyBook






    //deleteBook
    public void deleteBook(){
        String input;
        String options[] = {"Delete", "Cancel"};
        input = JOptionPane.showInputDialog(null, "Enter the book title you want to delete");
        Book temp = null;
        int x;

        for (Book recent : books){
            if(input.equals(recent.getTitle())){
                temp = recent;
                break;
            }
        }
        
        if(temp != null){ // 書存在
            books.remove( temp );
            JOptionPane.showMessageDialog(null, "Delete successfully\nGoing back to Main Page.");
            mainPage_administrator();
            JOptionPane.showMessageDialog(null, "Error\nYou are at deleteBook-1");
        }
        else{ // 書不存在
            x = JOptionPane.showOptionDialog(null, 
                                        "The book doesn't exist.\ndo you still want to delete book or go back to main page",
                                            "Modify",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if(x == 0){ // keep deleting
                deleteBook();
                JOptionPane.showMessageDialog(null, "Error\nYou are at deleteBook-2");
            }
            else if(x == 1){ // cancel
                mainPage_administrator();
                JOptionPane.showMessageDialog(null, "Error\nYou are at deleteBook-3");
            }
            else{System.exit(0);}
        }

    } // deleteBook


    
}
