import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Kalkulator extends JFrame implements ActionListener{

    private JTextField textFieldScreen=new JTextField(10);

    private JButton button_0=new JButton("0");
    private JButton button_1=new JButton("1");
    private JButton button_2=new JButton("2");
    private JButton button_3=new JButton("3");
    private JButton button_4=new JButton("4");
    private JButton button_5=new JButton("5");
    private JButton button_6=new JButton("6");
    private JButton button_7=new JButton("7");
    private JButton button_8=new JButton("8");
    private JButton button_9=new JButton("9");

    private JButton buttonAdd=new JButton("+");
    private JButton buttonSub=new JButton("-");
    private JButton buttonMultiply=new JButton("*");
    private JButton buttonDivide=new JButton("/");

    private JButton buttonC=new JButton("C");
    private JButton buttonCE=new JButton("CE");
    private JButton buttonEquals=new JButton("=");
    private JButton buttonBckspc=new JButton("<-");

    private KalkulatorModel model;

    public Kalkulator(){

        model=new KalkulatorModel(textFieldScreen);

        JPanel panelButtons=new JPanel(new GridLayout(5, 4, 5, 5));

        panelButtons.add(button_7);
        panelButtons.add(button_8);
        panelButtons.add(button_9);
        panelButtons.add(buttonCE);

        panelButtons.add(button_4);
        panelButtons.add(button_5);
        panelButtons.add(button_6);
        panelButtons.add(buttonAdd);

        panelButtons.add(button_1);
        panelButtons.add(button_2);
        panelButtons.add(button_3);
        panelButtons.add(buttonSub);

        panelButtons.add(new JLabel());
        panelButtons.add(button_0);
        panelButtons.add(new JLabel());
        panelButtons.add(buttonMultiply);

        panelButtons.add(buttonBckspc);
        panelButtons.add(buttonC);
        panelButtons.add(buttonEquals);
        panelButtons.add(buttonDivide);

        JButton[] digitButtons={button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9};
        for (JButton b:digitButtons) b.addActionListener(this);

        JButton[] operatorButtons={buttonAdd, buttonSub, buttonMultiply, buttonDivide};
        for (JButton b:operatorButtons) b.addActionListener(this);

        buttonEquals.addActionListener(this);
        buttonC.addActionListener(this);
        buttonCE.addActionListener(this);
        buttonBckspc.addActionListener(this);

        JPanel panelMain=new JPanel(new BorderLayout());
        panelMain.add(textFieldScreen, BorderLayout.NORTH);
        panelMain.add(panelButtons, BorderLayout.CENTER);

        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> new Kalkulator());
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String input=((JButton) e.getSource()).getText();

        if ("0123456789".contains(input)){
            model.inputDigit(input);
        }else if ("+-*/".contains(input)){
            model.inputOperator(input);
        }else if (input.equals("=")){
            model.inputOperator("=");
        }else if (input.equals("C")){
            textFieldScreen.setText("");
            model=new KalkulatorModel(textFieldScreen);
        }else if (input.equals("CE")){
            textFieldScreen.setText("");
        }else if (input.equals("<-")){
            String value = textFieldScreen.getText();
            if (value.length()>0){
                textFieldScreen.setText(value.substring(0, value.length()-1));
            }
        }
    }

    class KalkulatorModel{
        private int value1=0;
        private int value2=0;
        private String operator="";
        private int state=1;
        private JTextField screen;

        public KalkulatorModel(JTextField screen){
            this.screen=screen;
        }

        public void inputDigit(String digit){
            switch (state){
                case 1->screen.setText(screen.getText()+digit);
                case 2->{
                    screen.setText(digit);
                    state=3;
                }
                case 3->screen.setText(screen.getText()+digit);
                case 4->{
                    screen.setText(digit); 
                    operator="";
                    state=1;
                }
            }
        }

        public void inputOperator(String op){
            if (op.equals("=")){
                if (!operator.isEmpty() && state==3) {
                    value2=Integer.parseInt(screen.getText());
                    int result=calculate(value1, value2, operator);
                    screen.setText(String.valueOf(result));
                    state=4;
                    operator="";
                }
                return;
            }

            switch (state){
                case 1->{
                    value1=Integer.parseInt(screen.getText());
                    operator=op;
                    state=2;
                    screen.setText(value1+operator);
                }
                case 2->{
                    operator=op;
                    screen.setText(value1+operator);
                }
                case 3->{
                    value2=Integer.parseInt(screen.getText());
                    int result=calculate(value1, value2, operator);
                    value1=result;
                    operator=op;
                    state=2;
                    screen.setText(value1+operator);
                }
                case 4->{
                    value1=Integer.parseInt(screen.getText());
                    operator=op;
                    state=2;
                    screen.setText(value1+operator);
                }
            }
        }

        private int calculate(int v1, int v2, String op){
            return switch (op){
                case "+" -> v1+v2;
                case "-" -> v1-v2;
                case "*" -> v1*v2;
                case "/" -> v2!=0?v1/v2:0;
                default -> 0;
            };
        }
    }
}