public class Staff extends Member{

    public Staff(String name, String account, String password) {
        super(name, account, password);
        setFine(200);
        setLimit_book(2);
        setDue_date(7);
        this.member_type=3;

    }

 // toString 查看個人資料
 public String toString (){
    return "";
} // End toString

}
