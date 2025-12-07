package FrontGUI;

import javax.swing.*;

/**
 * Panel이 부착될 Frame을 생성하는 클래스.
 *
 * @author (2024320011 김혜린, 2024320009 이나현)
 * @version (2025.11.28)
 */
public class MyFrame extends JFrame
{
    /**
     * MyFrame 클래스의 객체 생성자
     */
    public MyFrame(){
        this.setTitle("LibraryApplication");
        this.setSize(800, 600);

        MyPanel myPanel = new MyPanel();
        this.add(myPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
