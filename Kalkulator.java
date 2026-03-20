import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kalkulator extends JFrame implements ActionListener {

    private JTextField textFieldScreen=new JTextField(10);
    private JButton button_1=new JButton("1");
    private JButton button_2=new JButton("2");
    private JButton button_3=new JButton("3");
    private JButton button_4=new JButton("4");
    private JButton button_5=new JButton("5");
    private JButton button_6=new JButton("6");
    private JButton button_7=new JButton("7");
    private JButton button_8=new JButton("8");
    private JButton button_9=new JButton("9");
    private JButton button_0=new JButton("0");
    private JButton buttonAdd=new JButton("+");
    private JButton buttonSub=new JButton("-");
    private JButton buttonDivide=new JButton("/");
    private JButton buttonMultiply=new JButton("*");
    private JButton buttonC=new JButton("C");
    private JButton buttonCE=new JButton("CE");
    private JButton buttonEquals=new JButton("=");
    private JButton buttonBckspc=new JButton("<-");

    private int value_1;
    private int value_2;
    private String operator = "";
    private boolean isZero = true;

    public Kalkulator(){
        
        DigitListener digitListener=new DigitListener();

        JPanel panelButtons=new JPanel(new GridLayout(5,4));
        panelButtons.add(button_7);
        button_7.addActionListener(this);
        panelButtons.add(button_8);
        button_8.addActionListener(this);
        panelButtons.add(button_9);
        button_9.addActionListener(this);
        panelButtons.add(buttonCE);
        buttonCE.addActionListener(this);

        panelButtons.add(button_4);
        button_4.addActionListener(this);
        panelButtons.add(button_5);
        button_5.addActionListener(this);
        panelButtons.add(button_6);
        button_6.addActionListener(this);
        panelButtons.add(buttonAdd);
        buttonAdd.addActionListener(digitListener);

        panelButtons.add(button_1);
        button_1.addActionListener(this);
        panelButtons.add(button_2);
        button_2.addActionListener(this);
        panelButtons.add(button_3);
        button_3.addActionListener(this);
        panelButtons.add(buttonSub);
        buttonSub.addActionListener(digitListener);

        panelButtons.add(new JLabel());
        panelButtons.add(button_0);
        button_0.addActionListener(this);
        panelButtons.add(new JLabel());
        panelButtons.add(buttonMultiply);
        buttonMultiply.addActionListener(digitListener);

        panelButtons.add(buttonBckspc);
        panelButtons.add(buttonC);
        buttonC.addActionListener(this);
        panelButtons.add(buttonEquals);
        buttonEquals.addActionListener(this);
        panelButtons.add(buttonDivide);
        buttonDivide.addActionListener(digitListener);
        
        JPanel panelMain=new JPanel(new BorderLayout());

        panelMain.add(BorderLayout.CENTER, panelButtons);
        panelMain.add(BorderLayout.NORTH,textFieldScreen);

        setContentPane(panelMain);



        //setSize(200,300);
        pack();
        setVisible(true);

    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
                public void run(){
                    new Kalkulator();
                }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String value = textFieldScreen.getText();
        String digit = ((JButton) e.getSource()).getText();

        if (digit.equals("0")) {
            if (isZero) {
                textFieldScreen.setText("0");
                    isZero = false;
            }
        } else if (digit.equals("<-")){
            if (value.length()>0) {
                textFieldScreen.setText(value.substring(0, value.length()-1));
            }
        } else if (digit.equals("=")){
            value_2 = Integer.parseInt(value);
            switch (operator) {
                case "+":
                    textFieldScreen.setText(String.valueOf(value_1+value_2));
                    break;
                case "-":
                    textFieldScreen.setText(String.valueOf(value_1-value_2));
                    break;
                case "*":
                    textFieldScreen.setText(String.valueOf(value_1*value_2));
                    break;
                case "/":
                    if (value_2 != 0) {
                        textFieldScreen.setText(String.valueOf(value_1/value_2));
                    } else {
                        textFieldScreen.setText("Error");
                    }
                    break;
            }
            operator="";
        } else if (digit.equals("C")) {
            textFieldScreen.setText("");
            value_1=0;
            value_2=0;
            operator="";
            isZero=true;
        } else if (digit.equals("CE")){
            textFieldScreen.setText("");
        } else {
            if (operator.isEmpty()) {
                textFieldScreen.setText(value+digit);
            } else {
                value_1=Integer.parseInt(value);
                operator=digit;
                textFieldScreen.setText("");
            }
        }
    }

    class DigitListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        operator=((JButton) e.getSource()).getText();
    }
}
}

