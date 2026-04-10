import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KalkulatorMVC{

    // ===== MODEL (PRAWIE 1:1) =====
    static class KalkulatorModel{
        private int value1=0;
        private int value2=0;
        private String operator="";
        private int state=1;

        public String inputDigit(String digit,String current){
            switch(state){
                case 1:
                    return current+digit;
                case 2:
                    state=3;
                    return digit;
                case 3:
                    return current+digit;
                case 4:
                    operator="";
                    state=1;
                    return digit;
            }
            return current;
        }

        public String inputOperator(String op,String current){
            if(op.equals("=")){
                if(!operator.isEmpty()&&state==3){
                    value2=Integer.parseInt(current);
                    int result=calculate(value1,value2,operator);
                    state=4;
                    operator="";
                    return String.valueOf(result);
                }
                return current;
            }

            switch(state){
                case 1:
                    value1=Integer.parseInt(current);
                    operator=op;
                    state=2;
                    return value1+operator;
                case 2:
                    operator=op;
                    return value1+operator;
                case 3:
                    value2=Integer.parseInt(current);
                    int result=calculate(value1,value2,operator);
                    value1=result;
                    operator=op;
                    state=2;
                    return value1+operator;
                case 4:
                    value1=Integer.parseInt(current);
                    operator=op;
                    state=2;
                    return value1+operator;
            }
            return current;
        }

        private int calculate(int v1,int v2,String op){
            switch(op){
                case "+":return v1+v2;
                case "-":return v1-v2;
                case "*":return v1*v2;
                case "/":return v2!=0?v1/v2:0;
            }
            return 0;
        }

        public String clearAll(){
            value1=0;
            value2=0;
            operator="";
            state=1;
            return "";
        }
    }

    // ===== VIEW (TWÓJ UI) =====
    static class KalkulatorView extends JFrame{
        JTextField textFieldScreen=new JTextField(10);

        JButton button_0=new JButton("0");
        JButton button_1=new JButton("1");
        JButton button_2=new JButton("2");
        JButton button_3=new JButton("3");
        JButton button_4=new JButton("4");
        JButton button_5=new JButton("5");
        JButton button_6=new JButton("6");
        JButton button_7=new JButton("7");
        JButton button_8=new JButton("8");
        JButton button_9=new JButton("9");

        JButton buttonAdd=new JButton("+");
        JButton buttonSub=new JButton("-");
        JButton buttonMultiply=new JButton("*");
        JButton buttonDivide=new JButton("/");

        JButton buttonC=new JButton("C");
        JButton buttonCE=new JButton("CE");
        JButton buttonEquals=new JButton("=");
        JButton buttonBckspc=new JButton("<-");

        public KalkulatorView(){
            JPanel panelButtons=new JPanel(new GridLayout(5,4,5,5));

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

            JPanel panelMain=new JPanel(new BorderLayout());
            panelMain.add(textFieldScreen,BorderLayout.NORTH);
            panelMain.add(panelButtons,BorderLayout.CENTER);

            setContentPane(panelMain);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            pack();
            setVisible(true);
        }

        public String getText(){
            return textFieldScreen.getText();
        }

        public void setText(String text){
            textFieldScreen.setText(text);
        }

        public void addController(ActionListener c){
            JButton[] digitButtons={button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9};
            for(JButton b:digitButtons)b.addActionListener(c);

            JButton[] ops={buttonAdd,buttonSub,buttonMultiply,buttonDivide};
            for(JButton b:ops)b.addActionListener(c);

            buttonEquals.addActionListener(c);
            buttonC.addActionListener(c);
            buttonCE.addActionListener(c);
            buttonBckspc.addActionListener(c);
        }
    }

    // ===== CONTROLLER (TWÓJ actionPerformed) =====
    static class KalkulatorController implements ActionListener{
        private KalkulatorModel model;
        private KalkulatorView view;

        public KalkulatorController(KalkulatorModel m,KalkulatorView v){
            model=m;
            view=v;
            view.addController(this);
        }

        public void actionPerformed(ActionEvent e){
            String input=((JButton)e.getSource()).getText();
            String current=view.getText();

            if("0123456789".contains(input)){
                view.setText(model.inputDigit(input,current));
            }else if("+-*/".contains(input)){
                view.setText(model.inputOperator(input,current));
            }else if(input.equals("=")){
                view.setText(model.inputOperator("=",current));
            }else if(input.equals("C")){
                view.setText(model.clearAll());
            }else if(input.equals("CE")){
                view.setText("");
            }else if(input.equals("<-")){
                if(current.length()>0){
                    view.setText(current.substring(0,current.length()-1));
                }
            }
        }
    }

    // ===== MAIN =====
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                new KalkulatorController(new KalkulatorModel(),new KalkulatorView());
            }
        });
    }
}