package view;

import javax.swing.*;

public class MainPage extends JFrame {
    private JTabbedPane Input;
    private JPanel panel1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField1;
    private JButton button1;
    private JTextArea textArea1;

    public MainPage() {
        super();

        setContentPane(panel1);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        setVisible(true);
    }
}
