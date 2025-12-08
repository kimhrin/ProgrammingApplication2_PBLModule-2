package CoreEngine;
import java.util.*;

/**
 * 책에 대한 정보를 담은 클래스
 *
 * @author (2024320009 이나현, 2024320011 김혜린)
 * @version (2025.11.25)
 */
public class Book
{
    private Loan LoanList;

    private String title;
    private String author;
    private int bookID;

    /**
     * Book 클래스의 객체 생성자
     * 
     * @param  title : String, author : String, bookID : int
     */
    public Book(String title, String author, int bookID)
    {
        this.title = title;
        this.author = author;
        this.bookID = bookID;

        this.LoanList = LoanList;
    }

    /**
     * 출력 요청 메소드
     *
     * @return    책의 정보(제목, 저자, 고유번호) 출력
     */
    public String display(){
        return "책의 제목 : " + title + ", 책의 저자 : " + author + ", 책의 고유번호 : " + bookID;
    }

    /**
     * 대출가능한 책인지 검사하는 메소드
     * 
     * @return    boolean 대출가능이면 true, 대출 불가능이면 false
     */
    public boolean checkBook(){
        return LoanList == null;
    }

    /**
     * 현재 Book과 연결된 Loan을 검색하는 메소드
     *
     * @return  연결된 Loan, 없으면 null
     */
    public Loan searchLoan(){
        return LoanList;
    }

    /**
     * Book과 Loan의 연결을 해제하는 메소드
     */
    public void disconnect(){
        LoanList = null;
    }

    /**
     * Book의 고유번호를 가져오는 메소드
     * 
     * @return Book의 고유번호
     */
    public int getBookID() {
        return this.bookID;
    }

    /**
     * Book의 제목를 가져오는 메소드
     * 
     * @return Book의 제목
     */
    public String getBookTitle() {
        return this.title;
    }

    /**
     * Book과 Loan의 연결하는 메소드
     * 
     * @param   loan : Loan
     */
    public void connect(Loan loan){
        this.LoanList = loan;
    }
}