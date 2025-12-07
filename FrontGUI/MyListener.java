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

            if(panel.index == 1){
                result = app.registerOneBorrower(panel.myTextField_BorrowerName.getText());
            }
            else if(panel.index == 2){
                result = app.registerOneBook(
                    panel.myTextField_BookTitle.getText(),
                    panel.myTextField_BookAuthor.getText(),
                    panel.myTextField_BookID.getText()
                );
            }
            else if(panel.index == 3){
                result = app.displayBookForLoan();
            }
            else if(panel.index == 4){
                result = app.displayBookOnLoan();
            }
            else if(panel.index == 5){
                result = app.loanOneBook(
                    panel.myTextField_BorrowerName.getText(),
                    panel.myTextField_BookID.getText()
                );
            }
            else if(panel.index == 6){
                result = app.returnOneBook(panel.myTextField_BookID.getText());
            }
            // UC7(현재 대출 내역 목록) → 이후 백엔드 구현 시:
            // else if(panel.index == 7){
            //     result = app.displayCurrentLoanHistory(panel.myTextField_PhoneNumber.getText());
            // }
            // UC8(이용자 목록) → 이후 백엔드 구현 시:
            // else if(panel.index == 8){
            //     result = app.displayBorrowerList();
            // }

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

            panel.myTextArea.setText(""); // 출력창 초기화
        }
    }
}
