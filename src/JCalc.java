import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created on 1/10/2015.
 * TODO comment here
 */
public class JCalc extends JFrame {

    JTextField display;
    JPanel  calcPanel;
    private boolean firstNum = true; //checks if a digit was already entered in the field
    private JCalcModel model = new JCalcModel();
    private String lastOp = "="; //holds the last entered operation
    private boolean hasDot = false;

    public static void main(String[] args) {

        //Try to use the OS look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            //Handle Exception
        } catch (InstantiationException ex) {
            //Handle Exception
        } catch (IllegalAccessException ex) {
            //Handle Exception
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //Handle Exception
        }

        JCalc calc = new JCalc();
        calc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        calc.setVisible(true);
    }

    public JCalc(){
        //Create display field
        display = new JTextField("0", 12);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        //Create numeric buttons
        String[] numArray = {"1","2","3","4","5","6","7","8","9", "0",".", "+/-"};
        ActionListener Numlistener = new NumListener();
        JPanel ButtonPannel = new JPanel();
        ButtonPannel.setLayout(new GridLayout(4,3,2,2));

        for(String s: numArray){
            JButton button = new JButton(s);
            button.addActionListener(Numlistener);
            ButtonPannel.add(button);
        }

        //Create the operation panel
        JPanel OpPanel = new JPanel();
        OpPanel.setLayout(new GridLayout(5,1,2,2));

        //Create Clear Buttons
        String[] clearArray = {"AC","CE"};
        ActionListener ClearListener = new ClearListener();

        for(String s: clearArray){
            JButton button = new JButton(s);
            button.addActionListener(ClearListener);
            OpPanel.add(button);
        }

        //Create Operation Buttons
        String[] opArray = {"+", "-", "*", "/", "pow", "="};
        ActionListener OperationListener = new OperationListener();


        for(String s: opArray){
            JButton button = new JButton(s);
            button.addActionListener(OperationListener);
            OpPanel.add(button);
        }


        //arrange panels on it buttons on it
        calcPanel = new JPanel();
        calcPanel.setLayout(new BorderLayout(5, 2));
        calcPanel.add(display, BorderLayout.NORTH);
        calcPanel.add(ButtonPannel, BorderLayout.CENTER);
        calcPanel.add(OpPanel, BorderLayout.EAST);
        calcPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        this.setContentPane(calcPanel);
        this.setTitle("JCalc");
        this.setResizable(false);
        this.pack();
    }

    //Listener for the numeric keys
    class NumListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();

            //first check if the negate button was pressed
            if(input.equals("+/-")) {
                display.setText(model.negate(display.getText()));
            }
            //then check if this is the first digit entered
            else if(firstNum){
                //check if it is not a dot
                if(!input.equals(".")){
                    display.setText(input);
                    firstNum = false;
                }
            }
            //then if it is not the first digit and is not the negate symbol
            else {
                //check if the input is a dot
                if(input.equals(".")){
                    //if a dot was not entered add a dot to the input
                    if(!hasDot) {
                        display.setText(display.getText() + input);
                        hasDot = true;
                    }
                }
                //otherwise append the digit to the text
                else {
                    display.setText(display.getText() + input);
                }
            }
        }
    }

    //Listener for the Operation Keys
    class OperationListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(!firstNum) {
                String text = display.getText();
                firstNum = true;

                if (lastOp.equals("=")) model.setTotal(text);
                else if (lastOp.equals("+")) model.add(text);
                else if (lastOp.equals("-")) model.subtract(text);
                else if (lastOp.equals("*")) model.multiply(text);
                else if (lastOp.equals("/")) model.divide(text);
                else if (lastOp.equals("pow")) model.power(text);

                display.setText("" + model.getTotal());
                hasDot = false;
                lastOp = event.getActionCommand();
            }
        }
    }

    //Listener for the Clear Keys
    class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(event.getActionCommand().equals("CE")) {
                display.setText("");
                firstNum = true;
                hasDot = false;
            }
            else if(event.getActionCommand().equals("AC")) {
                display.setText("");
                firstNum = true;
                hasDot = false;
                model.setTotal("0");
            }
        }
    }
}
