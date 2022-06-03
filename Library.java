import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;


public class Library {

    public int identity;
    public static ArrayList<Book> books = new ArrayList<Book>();
    public ArrayList <User> users = new ArrayList<User>();
    


    //Main 
    public static void main(String args[]) throws IOException{
        new Library();
    }// End Main


    //Constructor
    public Library()throws IOException{
        
        String[] options = {"Exit","Administrator", "Student", "Teacher", "Staff"};
       
        identity = JOptionPane.showOptionDialog(null, 
                                        "Returns the position of your choice on the array",
                                            "Click a button",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        db_import_book();
        // books.add(new Book("Kinder", "Admin", "Admin","available"));
        users.add(new Administrator("Admin", "abc", "123"));
        users.add(new Student("Daniel", "danwan", "123"));
        

        if(identity == 1 || identity == 2 || identity == 3 || identity == 4){
            log_in_or_sign_up(identity);
        }
        else {System.exit(0);} // 左上角的叉叉
   
    }// End Constructor


    public void db_import_book()throws IOException{
        File db_book = new File("db_book.txt");
        Scanner scanner = new Scanner (db_book);
        while( scanner.hasNextLine() == true){
            String temp = scanner.nextLine();
            String temps[] = temp.split(",");
            books.add( new Book(temps[0], temps[1], temps[2], temps[3]));
        }
    }








    //選擇登入或註冊，並跳到登入階段
    public void log_in_or_sign_up(int identity){
        String options[] = {"Sign up", "Log in"};

        int input = JOptionPane.showOptionDialog(null, "Sign up or Log In","Sign up or log In",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if(input == 0){
            sign_up_GUI(identity);
        }
        else if (input == 1){
            log_in_GUI(identity);
        }
        else {System.exit(0);}//左上角的叉叉
    } // End log_in_or_sign_up 



    //sign up GUI
    public void sign_up_GUI(int identity){
        JTextField name = new JTextField(); 
        JTextField acc = new JTextField();
        JPasswordField pw = new JPasswordField();
        int x;
        String options_sign_up[] = {"Sign Up"};
        Object information[] = {"Name",name,
                                "User Account", acc,
                                "Password", pw};
            while(true){
                
                x = JOptionPane.showOptionDialog (null,information, 
                "登入",JOptionPane.DEFAULT_OPTION,1,null,options_sign_up,options_sign_up[0]) ;

                if(x == -1){ System.exit(0);} // 左上角的叉叉

                for(User user : users){ //for 迴圈判斷帳號是否已經存在
                    if( acc.getText().equals( user.getAccount() ) == true ){
                        JOptionPane.showMessageDialog(null, "The account are already exist");
                        break;
                    }
                }
                
                switch(identity){//新增帳號
                    case 1:
                        users.add(new Administrator (name.getText() , acc.getText(), pw.getText() ) );
                        log_in_or_sign_up(identity);
                        break;
                    case 2:
                        users.add( new Student(name.getText() , acc.getText(), pw.getText()));
                        log_in_or_sign_up(identity);
                        break;
                    case 3:
                        users.add(new Teacher (name.getText() , acc.getText(), pw.getText() ) );
                        log_in_or_sign_up(identity);
                        break;
                    case 4:
                        users.add(new Staff (name.getText() , acc.getText(), pw.getText() ) );
                        log_in_or_sign_up(identity);
                        break;
                }
            }
    } // End sign up GUI



    // Log in GUI
    public void log_in_GUI(int identity){

        JTextField acc = new JTextField();
        JPasswordField pw = new JPasswordField();
        int x;
        String options_log_in[] = {"Log In"};
        String options_log_in_fail[] = {"Keep trying","Create an account"};
        Object information[] = {"User Account", acc,
                                    "Password", pw};
        
        while(true){    
            acc.setText("");
            pw.setText("");

            x = JOptionPane.showOptionDialog (null,information, // 輸入帳號密碼
            "登入",JOptionPane.DEFAULT_OPTION,1,null,options_log_in,options_log_in[0]) ;

            if(x == -1){System.exit(0);} // 左上角的叉叉

            if( checkIdentity(identity, acc.getText(), pw.getText())  == true){
                switch(identity){
                    case 1: //admin
                        for(User recent : users){
                            if (acc.getText().equals( recent.getAccount())){
                                Administrator temp = (Administrator) recent;
                                temp.mainPage_administrator();
                                break;
                            }
                        }
                        break;
                    case 2: // student
                        for(User recent : users){
                            if (acc.getText().equals( recent.getAccount())){
                                Student temp = (Student) recent;
                                temp.mainPage_member();
                                break;
                            }
                        }
                        break;
                    case 3: // teacher
                        for(User recent : users){
                            if (acc.getText().equals( recent.getAccount())){
                                Teacher temp = (Teacher) recent;
                                temp.mainPage_member();
                                break;
                            }
                        }
                        break;
                    case 4: // staff
                        for(User recent : users){
                            if (acc.getText().equals( recent.getAccount())){
                                Staff temp = (Staff) recent;
                                temp.mainPage_member();
                                break;
                            }
                        }
                        break;
                }
                JOptionPane.showMessageDialog(null, "Error\nYou are in log in GUI-1");
                break;
            } 

            // password wrong
            x = JOptionPane.showOptionDialog(null, "Your account or your password is wrong",
                "Keep trying??",JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, null, options_log_in_fail, options_log_in_fail[0]);

                if(x == -1){
                    System.exit(0);
                }
                else if(x == 0){
                    continue;
                }
                else if(x == 1){
                    sign_up_GUI(identity);
                    JOptionPane.showMessageDialog(null, "You are in Log_in_GUI-2");
                    System.out.println("You are in log in GUI-2");
                    break;
                }
        }
    } //End Log in GUI







    //驗證身份
    public Boolean checkIdentity(int identity, String account, String password){

        for (User recentUser : users ){
            if( recentUser.getAccount().equals(account) && recentUser.getPassword().equals(password)){
                return true;
            }
        }
        return false;

    } // End 驗證身份
    
}
