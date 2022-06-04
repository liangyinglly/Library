public class Teacher extends Member {

    public Teacher(String name, String account, String password) {
        super(name, account, password);
        setFine(500);
        setLimit_book(3);
        setDue_date(21);
        this.member_type=2;

    }


    // toString 查看個人資料
    public String toString (){
        return "";
    } // End toString
}
