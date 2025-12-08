package CoreEngine;

import java.util.*;

/**
 * 이용자에 대한 정보를 ArrayList로 저장하고 관리하는 클래스
 *
 * @author (2024320009 이나현, 2024320011 김혜린)
 * @version (2025.11.25)
 */
public class BorrowerCollection
{
    private Borrower borrower;

    private ArrayList<Borrower> borrowerCollection;

    /**
     * BorrowerCollection 클래스의 객체 생성자
     */
    public BorrowerCollection(){
        borrowerCollection = new ArrayList<Borrower>();
    }

    /**
     * 이미 등록돼 있는지 borrower인지 검사하는 메소드
     *
     * @param  phoneNumber : int
     * @return    등록돼 있으면 true, 등록돼있지 않으면 false
     */
    public boolean checkBorrower(int phoneNumber){
        Iterator<Borrower> BorrowerIt = borrowerCollection.iterator();
        while(BorrowerIt.hasNext()){
            Borrower br = BorrowerIt.next();
            if(br.getPhoneNumber() == phoneNumber){
                return true;
            }
        }
        return false;
    }

    /**
     * borrower를 Collection에 저장하는 메소드
     *
     * @param  borrower : Borrower
     * @return    저장완료 메세지
     */
    public String saveBorrower(Borrower borrower){
        borrowerCollection.add(borrower);
        return "[이용자 등록 완료]\n이름: " + borrower.getName() + "  |  전화번호: " + borrower.getPhoneNumber() + "\n";
    }

    /**
     * 전화번호로 Borrower를 검색하는 메소드
     *
     * @param  phoneNumber : int
     * @return   일치하는 Borrower, 없으면 null
     */
    public Borrower searchBorrower(int phoneNumber)
    {
        Iterator<Borrower> BorrowerIt = borrowerCollection.iterator();
        while(BorrowerIt.hasNext()){
            Borrower br = BorrowerIt.next();
            if(br.getPhoneNumber() == phoneNumber){
                return br;
            }
        }
        return null;
    }

    /**
     * Borrower을 하나 가져오는 메소드
     *
     * @return    Borrower
     */
    public Iterator<Borrower> getBorrower(){
        return borrowerCollection.iterator();
    }
}