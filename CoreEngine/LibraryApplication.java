package CoreEngine;
import java.util.*;

/**
 * 6개의 UseCase의 작업을 총괄하는 클래스
 *
 * @author (2024320009 이나현, 2024320011 김혜린)
 * @version (2025.11.25)
 */
public class LibraryApplication
{
    private BookCollection bookDB;
    private LoanCollection loanDB;
    private BorrowerCollection borrowerDB;

    private String LibraryName;

    /**
     * LibraryApplication 클래스의 객체 생성자
     * 
     * @param  LibraryName : String
     */
    public LibraryApplication(String LibraryName){
        this.LibraryName = LibraryName;

        bookDB = new BookCollection();
        loanDB = new LoanCollection();
        borrowerDB = new BorrowerCollection();
    }

    /**
     * UC1 - 새로운 이용자를 등록하는 메소드
     *
     * @param  name : String, phoneNumber : int
     * @return    이용자 등록 결과 메세지
     */
    public String registerOneBorrower(String name, int phoneNumber){
        boolean result = borrowerDB.checkBorrower(phoneNumber);
        if(result != true){
            Borrower borrower = new Borrower(name, phoneNumber);
            return borrowerDB.saveBorrower(borrower);
        }
        else{
            return "이용자 등록 실패 - 전화번호 " + phoneNumber + " 는 이미 등록된 이용자입니다.\n";
        }
    }

    /**
     * UC2 - 새로운 책을 등록하는 메소드
     *
     * @param  title : String, author : String, bookID : int
     * @return   책 등록 결과 메세지
     */
    public String registerOneBook(String title, String author, int bookID){
        boolean result = bookDB.checkBook(bookID);
        if(result != true){
            Book book= new Book(title,author,bookID);
            return bookDB.saveBook(book);
        }
        else{
            return "책 등록 실패 - 고유번호 " + bookID + "는 이미 등록된 책입니다.";
        }
    }

    /**
     * UC3 - 대출가능한 책들을 화면에 출력하는 메소드
     *
     * @return    대출가능한 책들 출력 결과 메세지
     */
    public String displayBooksForLoan(){
        String result = "===== 대출 가능 책 목록 =====\n";

        Iterator<Book> BookIt = bookDB.getBook();
        while(BookIt.hasNext()){
            Book book = BookIt.next();
            if(book.checkBook() == true){
                result += book.display() + "\n";
            }
        }
        if(result.equals("===== 대출 가능 책 목록 =====\n")){
            result = "대출 가능한 책의 정보가 없습니다.";
        }
        return result;
    }

    /**
     * UC4 - 대출 중인 책들을 화면에 출력하는 메소드
     *
     * @return    대출중인 책들 출력 결과 메세지
     */
    public String displayBooksOnLoan(){
        String result = "===== 대출 중 책 목록 =====\n";

        Iterator<Book> BookIt = bookDB.getBook();
        while(BookIt.hasNext()){
            Book book = BookIt.next();
            if(book.checkBook() != true){
                result += book.display() + "\n";
            }
        }
        if(result.equals("===== 대출 중 책 목록 =====\n")){
            result = "대출 중인 책의 정보가 없습니다.";
        }
        return result;
    }

    /**
     * UC5 - 책을 대출하는 메소드
     *
     * @param  bookID : int, phoneNumber : int
     * @return    대출 결과 메세지
     */
    public String loanOneBook(int bookID, int phoneNumber){
        Book bookResult = bookDB.searchBook(bookID);
        if(bookResult == null){
            return "대출 실패 - 고유번호 " + bookID + " 는 등록되지 않은 책입니다.";
        }

        Borrower borrowerResult = borrowerDB.searchBorrower(phoneNumber);
        if(borrowerResult == null){
            return "대출 실패 - 전화번호 " + phoneNumber + " 는 등록되지 않은 이용자입니다.";
        }

        if(borrowerResult.getLoanCount() >=10){
            return "대출 실패 - 대출 가능 권수(최대 10권)를 초과했습니다.";
        }

        if(bookResult.searchLoan() != null){
            return "대출 실패 - 고유번호 " + bookID + " 인 책은 이미 대출 중입니다.";
        }
        Loan loan = new Loan(bookResult, borrowerResult);
        loanDB.saveLoan(loan);

        return "대출 완료 - 이용자: " + borrowerResult.getName() + ", 책 ID: " + bookID + "\n";
    }

    /**
     * UC6 - 책을 반납하는 메소드
     *
     * @param  bookID : int, phoneNumber : int
     * @return    반납 결과 메세지
     */
    public String returnOneBook(int bookID, int phoneNumber){
        Book bookResult = bookDB.searchBook(bookID);
        if(bookResult == null){
            return "반납 실패 - 고유번호 " + bookID + " 는 등록되지 않은 책입니다.";
        }

        Borrower borrowerResult = borrowerDB.searchBorrower(phoneNumber);
        if(borrowerResult == null){
            return "반납 실패 - 전화번호 " + phoneNumber + " 는 등록되지 않은 이용자입니다.";
        }

        Loan loanFromBook = bookResult.searchLoan();
        Loan loanFromBorrower = borrowerResult.searchLoan(bookID);

        if(loanFromBorrower == null || loanFromBook == null){
            return "반납 실패 - 대출 정보가 존재하지 않거나 일치하지 않습니다.";
        }

        if(loanFromBorrower != loanFromBook){
            return "반납 실패 - 대출 정보가 일치하지 않습니다.";
        }

        borrowerResult.disconnect(loanFromBorrower);
        loanFromBorrower.disconnect();
        bookResult.disconnect();
        loanFromBook.disconnect();

        loanDB.deleteLoan(loanFromBorrower);
        loanDB.deleteLoan(loanFromBook);

        return "반납 완료 - 이용자: " + borrowerResult.getName() + ", 책 ID: " + bookID + "\n";
    }

    /**
     * UC7 - Borrower의 대출 내역들을 출력하는 메소드
     *
     * @param  phoneNumber : int
     * @return   대출 내역 결과 메세지
     */
    public String displayLoansForBorrower(int phoneNumber)
    {
        String result = "===== " + phoneNumber + "의 현재 대출 내역 목록 =====\n";

        Iterator<Loan> LoanIt = loanDB.getLoan();
        while (LoanIt.hasNext()){
            Loan loan = LoanIt.next();
            Borrower borrower = loan.getBorrower();
            if (borrower != null && borrower.getPhoneNumber() == phoneNumber){
                result += loan.display() + "\n";
            }
        }
        if (result.equals("===== " + phoneNumber + "의 현재 대출 내역 목록 =====\n")){
            return phoneNumber + "의 대출 내역이 없습니다.";
        }
        return result;
    }

    /**
     * UC8 - Borrower들의 목록을 출력하는 메소드
     *
     * @return   Borrower 목록 결과
     */
    public String displayAllBorrowers(){
        String result = "===== 이용자 목록 =====\n";

        Iterator<Borrower> BorrowerIt = borrowerDB.getBorrower();
        while(BorrowerIt.hasNext()){
            Borrower borrower = BorrowerIt.next();
            result += borrower.display() + "\n";
        }
        if(result.equals("===== 이용자 목록 =====\n")){
            return "등록된 이용자가 없습니다.";
        }
        return result;
    }
}