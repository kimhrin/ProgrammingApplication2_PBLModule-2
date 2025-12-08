package CoreEngine;

import java.util.*;

/**
 * 책에 대한 정보를 TreeSet으로 저장하고 관리하는 클래스
 *
 * @author (2024320009 이나현, 2024320011 김혜린)
 * @version (2025.11.25)
 */
public class BookCollection
{    
    private TreeSet<Book> bookCollection;
    /**
     * BookCollection 클래스의 객체 생성자 
     */
    public BookCollection()
    {
        bookCollection = new TreeSet<>(Comparator.comparingInt(Book::getBookID));
    }

    /**
     * 이미 등록돼 있는지 book인지 검사하는 메소드
     *
     * @param  bookID : int
     * @return    등록돼 있으면 true, 등록돼 있지 않으면 false
     */
    public boolean checkBook(int bookID){
        Iterator<Book> BookIt = bookCollection.iterator();
        while(BookIt.hasNext()){
            if(BookIt.next().getBookID() == bookID){
                return true;
            }
        }
        return false;
    }

    /**
     * book을 Collection에 저장하는 메소드
     *
     * @param  book : Book
     * @return    저장완료 메세지
     */
    public String saveBook(Book book){
        bookCollection.add(book);
        return "책 등록 완료 - 제목: " + book.getBookTitle() + ", 고유번호: " + book.getBookID();
    }

    /**
     * Book을 하나 가져오는 메소드
     *
     * @return    Book
     */
    public Iterator<Book> getBook(){
        return bookCollection.iterator();
    }

    /**
     * UC5 - 책의 고유번호로 Book를 검색하는 메소드
     *
     * @param  bookID : int
     * @return   일치하는 Book, 없으면 null
     */
    public Book searchBook(int bookID){
        Iterator<Book> BookIt = bookCollection.iterator();
        while(BookIt.hasNext()){
            Book b = BookIt.next();
            if(b.getBookID() == bookID){
                return b;
            }
        }
        return null;
    }
}