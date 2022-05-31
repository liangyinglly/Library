public class Book extends Date{
    private String title;
    private String author;
    private String publisher;
    private String status;// available & lend
   



    //Constructor
    public Book(String title, String author, String publisher, String status){
        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setStatus(status);
    }



    // 查看書集資料
    public String toString(){
        String output =  "Title: "+ title +"\nAuthor: " + author + "\nPublisher: " + publisher + "\nStatus: " + status;
        return output ;
    } // End toString



    //Getter and Setter
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
