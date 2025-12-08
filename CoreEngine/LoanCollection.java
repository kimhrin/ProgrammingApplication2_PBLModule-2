package CoreEngine;

import java.util.ArrayList;
import java.util.*;

/**
 * Loan 클래스에서 생성된 대출 정보를 저장하는 클래스.
 *
 * @author (2024320009 이나현, 2024320011 김혜린)
 * @version (2025.11.25)
 */
public class LoanCollection
{
    private Loan loan;

    private ArrayList<Loan> loanCollection;
    /**
     * LoanCollection 클래스의 객체 생성자
     */
    public LoanCollection(){
        loanCollection = new ArrayList<Loan>();
    }

    /**
     * Loan을 Collection에 저장하는 메소드.
     *
     * @param   loan : Loan
     * @return  저장 완료 메세지
     */
    public String saveLoan(Loan loan){
        loanCollection.add(loan);
        return "[대출 등록 완료]\n이용자: " + loan.getBorrower().getName() + "  |  책 ID: " + loan.getBook().getBookID();
    }

    /**
     * Loan 객체를 Collection 내부에서 삭제하는 메소드.
     *
     * @param   loan : Loan
     * @return  반납 완료 메세지
     */
    public String deleteLoan(Loan loan){
        loanCollection.remove(loan);
        return "반납 완료";
    }

    /**
     * Loan을 하나 가져오는 메소드
     *
     * @return    Loan
     */
    public Iterator<Loan> getLoan(){
        return loanCollection.iterator();
    }
}