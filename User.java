import javax.swing.*;
import java.util.ArrayList;
public abstract class User {
    private String name;
    private String account;
    private String password;
    private ArrayList <Book> books;
    public int member_type;
    //Constructor
    public User(String name, String account, String password) {
        setName(name);
        setAccount(account);
        setPassword(password);
        books = Library.books;
    }// End Constructor



    // Search
    public Book search(String search_title){
        for(Book recent : books){
            if (recent.getTitle().equals(search_title)){
                return recent;
            }
        }

        return null;
    } // End Search




    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public abstract void mainPage();
}
