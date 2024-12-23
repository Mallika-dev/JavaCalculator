import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    // Components
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton decimalButton, equalsButton, clearButton, backspaceButton;

    // Operators and operands
    private String operator;
    private double num1, num2, result;

    // Constructor
    public Calculator() {
        // Frame setup
        setTitle("Java Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Display field
        display = new JTextField();
        display.setBounds(30, 40, 340, 50);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        add(display);

        // Number buttons (0-9)
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].addActionListener(this);
        }

        // Operation buttons (+, -, *, /)
        String[] operations = {"+", "-", "*", "/"};
        operationButtons = new JButton[operations.length];
        for (int i = 0; i < operations.length; i++) {
            operationButtons[i] = new JButton(operations[i]);
            operationButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            operationButtons[i].addActionListener(this);
        }

        // Other buttons
        decimalButton = new JButton(".");
        decimalButton.setFont(new Font("Arial", Font.BOLD, 20));
        decimalButton.addActionListener(this);

        equalsButton = new JButton("=");
        equalsButton.setFont(new Font("Arial", Font.BOLD, 20));
        equalsButton.addActionListener(this);

        clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.addActionListener(this);

        backspaceButton = new JButton("←");
        backspaceButton.setFont(new Font("Arial", Font.BOLD, 20));
        backspaceButton.addActionListener(this);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(30, 120, 340, 300);
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        // Adding buttons to the panel
        for (int i = 1; i <= 3; i++) buttonPanel.add(numberButtons[i]);
        buttonPanel.add(operationButtons[0]); // "+"

        for (int i = 4; i <= 6; i++) buttonPanel.add(numberButtons[i]);
        buttonPanel.add(operationButtons[1]); // "-"

        for (int i = 7; i <= 9; i++) buttonPanel.add(numberButtons[i]);
        buttonPanel.add(operationButtons[2]); // "*"

        buttonPanel.add(decimalButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(equalsButton);
        buttonPanel.add(operationButtons[3]); // "/"

        buttonPanel.add(clearButton);
        buttonPanel.add(backspaceButton);

        add(buttonPanel);

        // Show frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    display.setText(display.getText() + i);
                    return;
                }
            }

            if (e.getSource() == decimalButton) {
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
                return;
            }

            if (e.getSource() == clearButton) {
                display.setText("");
                num1 = num2 = result = 0;
                operator = null;
                return;
            }

            if (e.getSource() == backspaceButton) {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                return;
            }

            for (int i = 0; i < operationButtons.length; i++) {
                if (e.getSource() == operationButtons[i]) {
                    operator = operationButtons[i].getText();
                    num1 = Double.parseDouble(display.getText());
                    display.setText("");
                    return;
                }
            }

            if (e.getSource() == equalsButton) {
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            display.setText("Error");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));
                num1 = result; // Allow chaining calculations
            }
        } catch (NumberFormatException ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

