public class Student extends Member {

    public Student(String name, String account, String password) {
        super(name, account, password);
        setFine(100);
        setLimit_book(1);
        setDue_date(14);
        this.member_type = 1;

    }




    // toString 查看個人資料
    public String toString (){
        return "";
    } // End toString

}
