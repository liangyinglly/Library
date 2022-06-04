import javax.swing.*;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;


public class Library {

    public static ArrayList<Book> books = new ArrayList<Book>();

    public  ArrayList <User> users = new ArrayList<User>();

    //Main
    public static void main(String args[]) throws IOException{
        new Library();
    }// End Main

       //Constructor
    public Library()throws IOException{
        //db_import_book();

        //books.add(new Book("Kinder", "Admin", "Admin","available"));
        users.add(new Administrator("Admin", "abc", "123"));
        users.add(new Student("Daniel", "danwan", "123"));
        create_signin_window();
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



    public  User signin_checking(String acc, String pwd){
        for (User user : users){
            if(acc.equals(user.getAccount()) && pwd.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    //以下都是設定海綿寶寶的介面
    public  void create_signin_window(){
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        JLabel account_lb = new JLabel();
        JLabel password_lb = new JLabel();
        JLabel welcome_lb = new JLabel();
        JTextField account_tf = new JTextField();
        JPasswordField password_tf = new JPasswordField();
        JLabel icon_lb = new JLabel();
        JPanel input_panel = new JPanel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("login_img.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

        JButton signin_bn = new JButton();
        JButton signup_bn  =new JButton();

        icon_lb.setIcon(imageIcon);
        welcome_lb.setText("Welcome to Center Library");
        welcome_lb.setFont(new Font("Serif", Font.PLAIN, 16));
        welcome_lb.setSize(200,50);
        welcome_lb.setLocation(290, 40);

        input_panel.setLayout(null);
        input_panel.setSize(800,800);
        input_panel.setLocation(140,90);

        account_tf.setSize(140,25);
        password_tf.setSize(140,25);

        account_tf.setLocation(190, 10);
        password_tf.setLocation(190, 60);

        account_lb.setText("Account: ");
        password_lb.setText("Password: ");

        account_lb.setSize(150,25);
        password_lb.setSize(150,25);

        account_lb.setLocation(130, 10);
        password_lb.setLocation(120, 60);

        icon_lb.setSize(200,200);
        icon_lb.setLocation(30,25);

        signin_bn.setText("Sign In");
        signup_bn.setText("Sign Up");
        signin_bn.setSize(90,30);
        signup_bn.setSize(90,30);
        signin_bn.setLocation(140,100);
        signup_bn.setLocation(240,100);


        //點擊 SIGNIN 的 BOTTON 會做的事
        signin_bn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                
                User user = signin_checking((String)account_tf.getText(), new String(password_tf.getPassword()));
                if(user != null){
                    user.mainPage();
                }
                // 要加如果是NULL跳視窗輸入錯誤
            }
        });

        //點擊註冊的BOTTON 會做的事
        signup_bn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                prepareGUI();
            }
        });

        jp.setLayout(null);
        jf.setLayout(null);


        jf.setSize(520, 300);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

        jf.setContentPane(jp);

        input_panel.add(account_tf);
        input_panel.add(password_tf);
        input_panel.add(account_lb);
        input_panel.add(password_lb);
        input_panel.add(signin_bn);
        input_panel.add(signup_bn);
        jp.add(icon_lb);
        jp.add(input_panel);
        jp.add(welcome_lb);
    }

    //以上都是設定海綿寶寶的介面

    //註冊的介面
   
    public void prepareGUI(){
        JFrame frame = new JFrame();
        JPanel panel_right = new JPanel();      
        JPanel panel_left = new JPanel();   
        Color left_panel = new Color(0,32,63);
        Color right_panel = new Color(173,239,209);
        String identity[] = {"Student", "Teacher" , "Staff", "Admin"};
        
       
        
        frame.setSize( 700, 400);
        frame.setLocationRelativeTo(null);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);



        panel_left.setSize(350,400);
        panel_left.setLayout(null);
        panel_left.setBackground(left_panel);            
        frame.getContentPane().add(panel_left);




        panel_right.setSize(350, 400);
        panel_right.setLayout(null);
        panel_right.setBackground(right_panel);            
        frame.getContentPane().add(panel_right);
        
        JLabel imagelabel = new JLabel( new ImageIcon("icon.png"));
        // imagelabel.setbounds(200,200,50, 50);
        imagelabel.setBounds(70, 100, 215, 210);
        panel_left.add(imagelabel);
        
       

        JLabel account = new JLabel("Account");
        account.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        account.setBounds(382,219,80,25);
        account.setForeground(Color.white);
        panel_right.add(account);
        
        JTextField acc = new JTextField();
        acc.setBounds(470,221,165,25);
        acc.setBackground(Color.white);
        acc.setBorder(BorderFactory.createEmptyBorder());
        panel_right.add(acc);
        
        JLabel password = new JLabel("Password");
        password.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        password.setBounds(382,256,80,25);
        password.setForeground(Color.white);
        panel_right.add(password);

        JPasswordField pw = new JPasswordField();
        pw.setBounds(470,258,165,25);
        pw.setBorder(BorderFactory.createEmptyBorder());
        pw.setBackground(Color.white);
        panel_right.add(pw);

        JLabel lblNewLabel = new JLabel("Sign Up");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 45));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(426, 54, 209, 60);
        panel_right.add(lblNewLabel);

        
        JTextField tf_name = new JTextField();
        tf_name.setBorder(BorderFactory.createEmptyBorder());
        tf_name.setBackground(Color.WHITE);
        tf_name.setBounds(470, 181, 165, 25);
        panel_right.add(tf_name);
        
        JLabel lb_name = new JLabel("Name");
        lb_name.setForeground(Color.WHITE);
        lb_name.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lb_name.setBounds(396, 182, 80, 25);
        panel_right.add(lb_name);
              
        JButton exit = new JButton("X");
        exit.setBounds(682,0,18,25);
        exit.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white, 1),
        BorderFactory.createEmptyBorder(0, 3, 0, 3)));
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });

        panel_right.add(exit);
        JComboBox comboBox = new JComboBox(identity);
        comboBox.setBounds(470, 142, 165, 27);
        panel_right.add(comboBox);
        String  id = (String) comboBox.getSelectedItem();
        
        JButton sign_up = new JButton("Sign up");
        sign_up.setBounds(575,317,60,25);
        sign_up.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.white, 1),
        BorderFactory.createEmptyBorder(0, 3, 0, 3)));
        sign_up.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                //加判斷
                boolean CAcc = CheckAccount((String)acc.getText());
                boolean CPw = CheckPassword(new String(pw.getPassword()));
                if(CAcc==true && CPw==true){
                    AddAccount(id,tf_name.getText(),acc.getText(),new String(pw.getPassword()));
                    create_signin_window();
                }
                else if(CAcc == false){
                    System.out.println("帳號已存在！");
                    //改看要跳什麼通知
                }
                else if(CPw == false){
                    System.out.println("密碼不符合格式！");
                    //改看要跳什麼通知
                }
        }});
        panel_right.add(sign_up);
        frame.setVisible(true);
        }

        public boolean CheckAccount(String acc) {
            for (User user : users){
                if(acc.equals(user.getAccount())){
                    return false;
                }
            }
            return true;
        }
        
        public boolean CheckPassword(String pw) {
            if(pw.length()<8) {
                return false;
            }
            else if( ! pw.matches(".*[0-9]+.*")) {
                return false;
            }
            else if( ! pw.matches(".*[A-Z]+.*")) {
                return false;
            }
            else {
                return true;
            }	
        }

        public void AddAccount(String id, String user_name , String account, String password){
            switch(id){
                case "Student" :
                users.add(new Student(user_name,account,password));
                break;
                case "Teacher":
                users.add(new Teacher(user_name,account,password));
                break;
                case "Staff" :
                users.add(new Staff(user_name,account,password));
                break;
                case "Admin" :
                users.add(new Administrator(user_name,account,password));
                break;
            }
            
        }
        
       
}
