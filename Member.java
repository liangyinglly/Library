import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Member extends User{
    private int limit_book;
    private int due_date;
    private int fine;
    private LocalDateTime dateTime = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected  ArrayList<Book> borrowing;


    //Constructor
    public Member(String name, String account, String password) {
        super(name, account, password);
        borrowing = new ArrayList <Book>();
    }// End Constructo


    


    //mainPage_member
    public void mainPage(){
        String options [] = {"Borrow", "Return", "Search", "Information"};

        int input = JOptionPane.showOptionDialog(null,
                                        "Welcome " + getName(),
                                        "Main Page",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]
                                        );
        if(input == -1){System.exit(0);} // 左上角的叉叉
        switch (input){ // 進入功能
            case 0:
                borrowBook();
                break;
            case 1:
                returnBook();
                break;
            case 2:
                searchBook();
                break;
            case 3:
                show_information();
                break;
        }

    } // End mainPage_member




    //  Function method - go back to MainPage



    // BorrowBook
    public void borrowBook(){
        searchBook();
    } // End BorrowBook


    // BorrowBook_parameter
    public void borrowBook_parameter(Book found){


        if(check_limit_book() == false){ // 若借書數量已滿，回到 Main Page
            JOptionPane.showMessageDialog(null, "You reach your borrowing limit:" + limit_book+"\nGo back to main page.");
            mainPage();
            JOptionPane.showMessageDialog(null, "Error\nYour are at borrowBook_parameter-1");
        }



        borrowing.add( found );
        found.setBorrow_date(LocalDate.now());
        found.setReturn_date(LocalDate.now().plusDays(due_date));
        found.setStatus("lend");
        JOptionPane.showMessageDialog(null, "Borrow success\nYou should return the book by " + found.getReturn_date() + "\nGo back to Main Page");

        mainPage();
        JOptionPane.showMessageDialog(null, "Error\nYour are at borrowBook_parameter-2");
    } // End BorrowBook_parameter










    // ReturnBook
    public void returnBook(){
        String input;
        boolean did_borrow = false;
        Book temp = null;
        String options[] = {"return", "Main Page"};
        int x;

        input = JOptionPane.showInputDialog(null, "Which book do you want to return?");

        for (Book recent: borrowing){
            if(input.equals(recent.getTitle())){
                temp = recent;
                did_borrow = true;
                break;
            }
        }

        if(did_borrow == true){
            temp.setStatus("available");
            borrowing.remove(temp);
            JOptionPane.showMessageDialog(null, "Return success\t"+dateTime.format(formatter)+"\nGoing back to Main Page.");
            mainPage();
            JOptionPane.showMessageDialog(null,"Error\nYou are at returnBook-1");
        }
        else if (did_borrow == false){

            x = JOptionPane.showOptionDialog(null,
                                        "You didn't borrow the book\nDo you still want to return the book or go back to main page.",
                                            "Return book",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if(x == -1){System.exit(0);} //左上角的叉叉

            if(x == 0){ // keep returning
                returnBook();
                JOptionPane.showMessageDialog(null,"Error\nYou are at returnBook-2");
            }
            else if(x == 1){ // go back to main page
                mainPage();
                JOptionPane.showMessageDialog(null,"Error\nYou are at returnBook-3");
            }


        }

    }   // End ReturnBook

    // Search
    public void searchBook(){
        String target;
        String[] options_search = {"Yes","No"};
        String [] options_not_found = {"Search", "Main Page"};
        int x;
        Book found = null; // 找到的書
        while(true){


            target = JOptionPane.showInputDialog(null, "Enter book's title" );
            System.out.println(target);
            if (target == null){
                mainPage();
                JOptionPane.showMessageDialog(null, "Error\nYou are at searchBook-4");
            }// 左上角的叉叉

            found = search(target);

            if(found == null){ //沒找到書

                x = JOptionPane.showOptionDialog(null,
                                            "The book doesn't exist.\nKeeping look?",
                                                "Search",
                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_not_found, options_not_found[0]);
                if(x == 0){
                    continue;
                }
                else if( x == 1){
                    mainPage();
                }
                else{ // 左上角的叉叉
                    System.exit(0);
                }
            }


            if ( found != null ){ //有找到書

                if(found.getStatus().equals("lend")){ // 判斷書是否已借出
                    JOptionPane.showMessageDialog(null, "The book is not available now.\nGoing back to Main Page");
                    mainPage();
                    JOptionPane.showMessageDialog(null, "Error\nYou are at searchbook-3");
                }

                    x = JOptionPane.showOptionDialog(null,
                                            "Do you want to borrow the book?"+"\nTitle: " + found.getTitle()+"\nAuthor: " + found.getAuthor()+"\nPublisher: " + found.getPublisher(),
                                                "Search",
                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_search, options_search[0]);
                    if( x == 0){ // 要借書

                        borrowBook_parameter(found);
                        JOptionPane.showMessageDialog(null, "Error\nYou are at searchbook-1");
                    }
                    else if (x == 1){ // 不借書
                        mainPage();
                        JOptionPane.showMessageDialog(null, "Error\nYou are at searchbook-2");
                    }
                    else {System.exit(0);} //左上角的叉叉

                    break;
                }



        }
    } // End SearchBook


    public boolean check_limit_book(){
        if(  borrowing.size() < limit_book ){
            System.out.println(borrowing.size());
            return true;
        }
        else {
            return false;
        }

    }







    //show_information
    public void show_information(){
        String output = "";
        output += "Name:" + getName() + "\n";
        output += "Account:" + getAccount() + "\n";
        output += "Password:" + getPassword() + "\n";
        output += "\nThe books you borrowed\t\tReturn date\n";
        for(Book recent : borrowing){
            output += recent.getTitle() + "\t\t\t\t\t\t\t\t\t" + recent.getReturn_date();
        }
        JOptionPane.showMessageDialog(null, output);
        mainPage();
        JOptionPane.showMessageDialog(null, "Error\nYou are at Member's toString");

        } // End show_information








    //Getter and Setter
    public int getLimit_book() {
        return limit_book;
    }
    public void setLimit_book(int limit) {
        this.limit_book = limit;
    }
    public int getFine() {
        return fine;
    }
    public void setFine(int fine) {
        this.fine = fine;
    }
    public void setDue_date(int due_date){
        this.due_date = due_date;
    }
    public int getDue_date(){
        return due_date;
    }// End Getter and Setter

}
