package CoreEngine;

import java.util.*;

/**
 * 이용자에 대한 정보를 담은 클래스
 *
 * @author (2024320009 이나현, 2024320011 김혜린)
 * @version (2025.11.25)
 */
public class Borrower
{
    public ArrayList<Loan> LoanDetail;

    private String name;
    private int phoneNumber;

    /**
     * Borrower 클래스의 객체 생성자
     * 
     * @param  name : String, phoneNumber : int
     */
    public Borrower(String name, int phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;

        LoanDetail = new ArrayList<Loan>();
    }

    /**
     * 현재 Borrower와 연결된 Loan을 검색하는 메소드
     *
     * @param   book : int
     * @return   연결된 Loan, 없으면 null
     */
    public Loan searchLoan(int bookID){
        if (LoanDetail == null){
            return null;   
        }

        Iterator<Loan> it = LoanDetail.iterator();
        while(it.hasNext()){
            Loan loan = it.next();
            if(loan.getBook().getBookID() == bookID){
                return loan;  
            }
        }
        return null;
    }

    /**
     * Borrower와 Loan의 연결을 해제하는 메소드
     * 
     * @param   loan : Loan
     */
    public void disconnect(Loan loan){
        LoanDetail.remove(loan);
    }

    /**
     * Borrower의 전화번호를 가져오는 메소드
     * 
     * @return Borrower의 전화번호
     */
    public int getPhoneNumber(){
        return this.phoneNumber;
    }

    /**
     * Borrower의 이름을 가져오는 메소드
     * 
     * @return Borrower의 이름
     */
    public String getName(){
        return this.name;
    }

    /**
     * Borrower과 Loan의 연결하는 메소드
     * 
     * @param   loan : Loan
     */
    public void connect(Loan loan){
        LoanDetail.add(loan);
    }

    /**
     * 현재 Borrower가 대출 중인 책 권수를 알려주는 메소드
     * 
     * @return 대출 중인 권 수
     */
    public int getLoanCount(){
        return LoanDetail.size();
    }

    /**
     * 출력 요청 메소드
     *
     * @return    이용자의 정보(이름, 전화번호) 출력
     */
    public String display(){
        return "이용자 이름: " + name + " |  전화번호: " + phoneNumber;
    }
}