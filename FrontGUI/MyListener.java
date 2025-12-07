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
            panel.index = 1 + selected; // UC1, UC2
            panel.setInputField();
        }
        else if(obj.equals(panel.myComboBox2)){
            JComboBox cb = (JComboBox)e.getSource();
            int selected = cb.getSelectedIndex();
            panel.index = 3 + selected; // UC3, UC4
            panel.setInputField();
        }
        else if(obj.equals(panel.myComboBox3)){
            JComboBox cb = (JComboBox)e.getSource();
            int selected = cb.getSelectedIndex();
            panel.index = 5 + selected; // UC5, UC6
            panel.setInputField();
        }
        else if(obj.equals(panel.myButton_Run)){
            String result = "";

            if(panel.index == 1){
                // 현재는 이름만 LibraryApplication에 전달.
                // 전화번호는 새로운 Core랑 연결할 때 LibraryApplication에 메소드 오버로드해서 함께 넘기면 됨.
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

            panel.myTextArea.append(result + "\n");
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
