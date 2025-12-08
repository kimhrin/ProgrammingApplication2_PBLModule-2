package CoreEngine;

import java.util.*;
import java.util.Calendar;

/**
 * 대출이 진행될 때 저장돼야 하는 정보를 저장하고, 반납이 진행될 때 연결을 해제하는 작업을 진행하는 클래스.
 *
 * @author (2024320009 이나현, 2024320011 김혜린)
 * @version (2025.11.25)
 */
public class Loan
{
    private Book LoanBook;
    private Borrower LoanBorrower;

    private Date loanDate;
    private Date returnDate;

    private static final int dueDate = 15;

    /**
     * Loan 클래스의 객체 생성자
     * 
     * @ param  LoanBook : Book, LoanBorrower : ArrayList<Borrower>
     */
    public Loan(Book LoanBook, Borrower LoanBorrower){
        this.LoanBook = LoanBook;
        this.LoanBorrower = LoanBorrower;

        this.loanDate = new Date(); // 오늘을 대출 날짜로 지정
        Calendar cal = Calendar.getInstance(); // 반납 일자 계산을 위한 새로운 날짜 객체 생성
        cal.setTime(loanDate); // 날짜 객체에 오늘 날짜를 대입
        cal.add(Calendar.DATE, 15); // 오늘 날짜 + 15 = 반납 일자
        this.returnDate = cal.getTime(); // 반납 일자 변수에 위에서 구한 값을 대입

        LoanBook.connect(this);
        LoanBorrower.connect(this);
    }

    /**
     * Loan과 Book, Borrower 사이의 연결을 해제하는 메소드.
     */
    public void disconnect(){
        LoanBorrower = null;
        LoanBook = null;
    }

    /**
     * Borrower을 가져오는 메소드
     * 
     * @return Borrower
     */
    public Borrower getBorrower() {
        return LoanBorrower;
    }

    /**
     * Book을 가져오는 메소드
     * 
     * @return Book
     */
    public Book getBook() {
        return LoanBook;
    }

    /**
     * 출력 요청 메소드
     *
     * @return    대출 정보(책ID, 대출일, 반납예정일) 출력
     */
    public String display(){
        return "책 등록번호: " + LoanBook.getBookID() + "  |  대출일: " + loanDate + "  |  반납예정일: " + returnDate;
    }
}
