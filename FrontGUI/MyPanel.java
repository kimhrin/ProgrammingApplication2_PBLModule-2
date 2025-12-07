package FrontGUI;

import javax.swing.*;
import java.awt.*;
import CoreEngine.*;

/**
 * GUI Component 구성을 담당하는 클래스.
 *
 * @author (2024320011 김혜린, 2024320009 이나현)
 * @version (2025.11.28)
 */
public class MyPanel extends JPanel
{
    protected JPanel titlePanel;
    protected JPanel menuPanel;
    protected JPanel buttonPanel;
    protected JPanel outputPanel;

    // 🔹 전화번호용 Label / TextField 추가
    protected JLabel myLabel_BorrowerName, myLabel_PhoneNumber,
                     myLabel_BookTitle, myLabel_BookAuthor, myLabel_BookID;
    protected JTextField myTextField_BorrowerName, myTextField_PhoneNumber,
                         myTextField_BookTitle, myTextField_BookAuthor, myTextField_BookID;

    protected JButton myButton_Run, myButton_Clear;
    protected JTextArea myTextArea;
    private LibraryApplication app;

    protected String[] menu1 = {"이용자 등록", "책 등록"};
    protected String[] menu2 = {"대출가능 목록", "대출중 목록"};
    protected String[] menu3 = {"책 대출", "책 반납"};

    protected JComboBox myComboBox1;
    protected JComboBox myComboBox2;
    protected JComboBox myComboBox3;

    protected int index = -1; // UC1~6 중, 선택된 UC 번호를 저장할 변수.

    /**
     * MyPanel 클래스의 객체 생성자
     */
    public MyPanel(){
        this.setLayout(new BorderLayout(5, 5));
        this.app = new LibraryApplication("LibraryApplication");

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());

        // ===== 상단 타이틀 영역 =====
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5)); // 상, 좌, 하, 우
        titlePanel.setBackground(Color.LIGHT_GRAY);

        ImageIcon book = new ImageIcon("book.png");
        JLabel myLabel_Title = new JLabel("  LibraryApplication", book, SwingConstants.LEFT);
        myLabel_Title.setFont(new Font("Dialog", Font.BOLD, 20));
        titlePanel.add(myLabel_Title, BorderLayout.WEST);

        northPanel.add(titlePanel, BorderLayout.NORTH);

        // ===== 메뉴 콤보박스 영역 =====
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 3, 10, 0));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        myComboBox1 = new JComboBox(menu1);
        myComboBox2 = new JComboBox(menu2);
        myComboBox3 = new JComboBox(menu3);

        menuPanel.add(myComboBox1);
        menuPanel.add(myComboBox2);
        menuPanel.add(myComboBox3);

        northPanel.add(menuPanel, BorderLayout.SOUTH);
        this.add(northPanel, BorderLayout.NORTH);

        // ===== 중앙 영역 (입력 + 출력) =====
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10, 10));

        // ---- 입력 패널 ----
        JPanel inputPanel = new JPanel();
        
        inputPanel.setLayout(new GridLayout(5, 2, 5, 5)); // 행, 열, 가로 간격, 세로 간격
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        myLabel_BorrowerName = new JLabel("이용자 이름");
        myLabel_PhoneNumber  = new JLabel("전화번호");
        myLabel_BookTitle    = new JLabel("책 제목");
        myLabel_BookAuthor   = new JLabel("책 저자");
        myLabel_BookID       = new JLabel("책 등록번호");

        myTextField_BorrowerName = new JTextField(15);
        myTextField_PhoneNumber  = new JTextField(15);
        myTextField_BookTitle    = new JTextField(15);
        myTextField_BookAuthor   = new JTextField(15);
        myTextField_BookID       = new JTextField(15);

        inputPanel.add(myLabel_BorrowerName);
        inputPanel.add(myTextField_BorrowerName);

        inputPanel.add(myLabel_PhoneNumber);
        inputPanel.add(myTextField_PhoneNumber);

        inputPanel.add(myLabel_BookTitle);
        inputPanel.add(myTextField_BookTitle);

        inputPanel.add(myLabel_BookAuthor);
        inputPanel.add(myTextField_BookAuthor);

        inputPanel.add(myLabel_BookID);
        inputPanel.add(myTextField_BookID);

        centerPanel.add(inputPanel, BorderLayout.WEST);

        // ---- 출력 패널 ----
        outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());

        myTextArea = new JTextArea(20, 30);
        myTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(myTextArea);

        outputPanel.add(scroll, BorderLayout.CENTER);
        centerPanel.add(outputPanel, BorderLayout.CENTER);

        this.add(centerPanel, BorderLayout.CENTER);

        // ===== 하단 버튼 영역 =====
        buttonPanel = new JPanel();

        myButton_Run = new JButton("실행");
        myButton_Clear = new JButton("초기화");

        buttonPanel.add(myButton_Run);
        buttonPanel.add(myButton_Clear);

        this.add(buttonPanel, BorderLayout.SOUTH);

        // ===== Listener 등록 =====
        MyListener ml = new MyListener(this, app);
        myComboBox1.addActionListener(ml);
        myComboBox2.addActionListener(ml);
        myComboBox3.addActionListener(ml);

        myButton_Run.addActionListener(ml);
        myButton_Clear.addActionListener(ml);
    }

    /**
     * TextField의 활성/비활성 상태를 설정하고, 상태에 따라 배경색을 지정하는 메소드.
     *
     * @param  tf : JTextField, enableField : boolean
     */
    private void setTextFieldEnable(JTextField tf, boolean enableField){
        tf.setEnabled(enableField); // enableField가 false일 시, 컴포넌트가 완전히 꺼짐.
        if(enableField){
            tf.setBackground(Color.WHITE);
        }else{
            tf.setBackground(Color.LIGHT_GRAY);
        }
    }

    /**
     * 현재 선택된 UseCase에 따라 필요한 TextField만 활성화하고, 나머지는 비활성화하는 메소드.
     */
    public void setInputField(){
        // UC가 바뀔 때마다 새로 세팅하기 위해 모두 비활성화.
        setTextFieldEnable(myTextField_BorrowerName, false);
        setTextFieldEnable(myTextField_PhoneNumber,  false);
        setTextFieldEnable(myTextField_BookTitle,    false);
        setTextFieldEnable(myTextField_BookAuthor,   false);
        setTextFieldEnable(myTextField_BookID,       false);

        // UC1(이용자 등록) : 이름 + 전화번호 활성화
        if(index == 1){
            setTextFieldEnable(myTextField_BorrowerName, true);
            setTextFieldEnable(myTextField_PhoneNumber,  true);
        }
        // UC2(책 등록) : 책 정보만 활성화
        else if(index == 2){
            setTextFieldEnable(myTextField_BookTitle,  true);
            setTextFieldEnable(myTextField_BookAuthor, true);
            setTextFieldEnable(myTextField_BookID,     true);
        }
        // UC5(책 대출) : 이용자 이름 + 책ID
        else if(index == 5){
            setTextFieldEnable(myTextField_BorrowerName, true);
            setTextFieldEnable(myTextField_BookID,       true);
        }
        // UC6(책 반납) : 책ID만
        else if(index == 6){
            setTextFieldEnable(myTextField_BookID, true);
        }
    }
}
