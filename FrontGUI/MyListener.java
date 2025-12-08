package FrontGUI;

import java.awt.event.*;
import javax.swing.*;
import CoreEngine.*;

/**
 * MyPanel에서 넘겨 받은 Component들에 대한 Event 처리를 담당하는 클래스.
 *
 * @author (2024320011 김혜린, 2024320009 이나현)
 * @version (2025.12.03)
 */
public class MyListener implements ActionListener
{
    private MyPanel panel;
    private LibraryApplication app;

    /**
     * MyListener 클래스의 객체 생성자
     */
    public MyListener(MyPanel panel, LibraryApplication app){
        this.panel = panel;
        this.app = app;
    }

    /**
     * ActionEvent에 대해 각 UseCase별로 맞는 동작을 처리하도록 하는 메소드.
     *
     * @param  e : ActionEvent
     */
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();

        if(obj.equals(panel.myComboBox1)){
            JComboBox cb = (JComboBox)e.getSource();
            int selected = cb.getSelectedIndex();
            panel.index = 1 + selected;
            panel.setInputField();
        }
        else if(obj.equals(panel.myComboBox2)){
            JComboBox cb = (JComboBox)e.getSource();
            int selected = cb.getSelectedIndex();
            
            // menu2 = {"이용자 목록", "대출 가능 책 목록", "대출 중 책 목록", "현재 대출 내역 목록"}
            
            if(selected == 0){
                panel.index = 8;     // UC8 : 이용자 목록
            }else if(selected == 1){
                panel.index = 3;     // UC3 : 대출 가능 책 목록
            }else if(selected == 2){
                panel.index = 4;     // UC4 : 대출 중 책 목록
            }else if(selected == 3){
                panel.index = 7;     // UC7 : 현재 대출 내역 목록 (전화번호 기반)
            }

            panel.setInputField();
        }
        else if(obj.equals(panel.myComboBox3)){
            JComboBox cb = (JComboBox)e.getSource();
            int selected = cb.getSelectedIndex();
            panel.index = 5 + selected;
            panel.setInputField();
        }
        else if(obj.equals(panel.myButton_Run)){
            String result = "";

            try{
                if(panel.index == 1){
                    String name = panel.myTextField_BorrowerName.getText();
                    String phoneText = panel.myTextField_PhoneNumber.getText();

                    if(name.equals("") || phoneText.equals("")){
                        result = "** 이용자 이름과 전화번호를 모두 입력해주세요. **\n";
                    }else{
                        int phoneNumber = Integer.parseInt(phoneText);
                        result = app.registerOneBorrower(name, phoneNumber);
                    }
                }
                else if(panel.index == 2){
                    String title = panel.myTextField_BookTitle.getText();
                    String author = panel.myTextField_BookAuthor.getText();
                    String bookIDText = panel.myTextField_BookID.getText();

                    if(title.equals("") || author.equals("") || bookIDText.equals("")){
                        result = "** 책 제목, 저자, 등록번호를 모두 입력해주세요. **\n";
                    }else{
                        int bookID = Integer.parseInt(bookIDText);
                        result = app.registerOneBook(title, author, bookID);
                    }
                }
                else if(panel.index == 3){
                    result = app.displayBooksForLoan();
                }
                else if(panel.index == 4){
                    result = app.displayBooksOnLoan();
                }
                else if(panel.index == 5){
                    String phoneText = panel.myTextField_PhoneNumber.getText();
                    String bookIDText = panel.myTextField_BookID.getText();

                    if(phoneText.equals("") || bookIDText.equals("")){
                        result = "** 전화번호와 책 등록번호를 모두 입력해주세요. **\n";
                    }else{
                        int phoneNumber = Integer.parseInt(phoneText);
                        int bookID = Integer.parseInt(bookIDText);
                        result = app.loanOneBook(bookID, phoneNumber);
                    }
                }
                else if(panel.index == 6){
                    String phoneText = panel.myTextField_PhoneNumber.getText();
                    String bookIDText = panel.myTextField_BookID.getText();

                    if(phoneText.equals("") || bookIDText.equals("")){
                        result = "** 전화번호와 책 등록번호를 모두 입력해주세요. **\n";
                    }else{
                        int phoneNumber = Integer.parseInt(phoneText);
                        int bookID = Integer.parseInt(bookIDText);
                        result = app.returnOneBook(bookID, phoneNumber);
                    }
                }
                else if(panel.index == 7){
                    String phoneText = panel.myTextField_PhoneNumber.getText();

                    if(phoneText.equals("")){
                        result = "** 전화번호를 입력해주세요. **\n";
                    }else{
                        int phoneNumber = Integer.parseInt(phoneText);
                        result = app.displayLoansForBorrower(phoneNumber);
                    }
                }
                else if(panel.index == 8){
                    result = app.displayAllBorrowers();
                }
            }
            catch(NumberFormatException ex){
                result = "** 전화번호와 책 등록번호는 숫자로만 입력해주세요. **\n";
            }

            if(!result.equals("")){
                panel.myTextArea.append(result + "\n");
            }
        }
        else if(obj.equals(panel.myButton_Clear)){
            panel.myTextField_BorrowerName.setText("");
            panel.myTextField_PhoneNumber.setText("");
            panel.myTextField_BookTitle.setText("");
            panel.myTextField_BookAuthor.setText("");
            panel.myTextField_BookID.setText("");

            panel.myTextArea.setText("");
        }
    }
}
