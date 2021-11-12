package su.demands;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        JFrame form = new JFrame("VigeneraE&D");

        form.setResizable(false);

        form.setSize(380, 280);
        form.setLayout(null);
        form.setLocation(300,200);
        form.getContentPane().setBackground(Color.decode("#EEEEEE"));

        form.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(form,
                        "Close?", "Exit", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(1);
                    form.dispose();
                }
            }
        });

        JLabel labelKey = new JLabel("Key:");
        labelKey.setBounds(0,0,200, 20);
        form.getContentPane().add(labelKey);

        JLabel labelMain = new JLabel("ENCRYPT");
        labelMain.setBounds(10,25,100, 20);
        form.getContentPane().add(labelMain);

        JLabel labelSecond = new JLabel("DECRYPT");
        labelSecond.setBounds(110,25,100, 20);
        form.getContentPane().add(labelSecond);

        final JTextArea keyTextArea = new JTextArea(10, 1);
        keyTextArea.setBounds(25,2,280,20);
        form.getContentPane().add(keyTextArea);

        final JTextArea writeTextArea = new JTextArea(10, 40);
        writeTextArea.setBounds(10,50,160,150);
        form.getContentPane().add(writeTextArea);

        final JTextArea outputTextArea = new JTextArea(10, 40);
        outputTextArea.setBounds(190,50,160,150);
        form.getContentPane().add(outputTextArea);

        final JButton buttonSwap = new JButton("⇄"); //U+21C4
        buttonSwap.setBounds(65,25,45,20);

        final JButton buttonOptimize = new JButton("encrypt || decrypt");
        buttonOptimize.setBounds(10,205,150,30);

        buttonSwap.addActionListener(event -> {
            String buffer = labelMain.getText();
            labelMain.setText(labelSecond.getText());
            labelSecond.setText(buffer);

            if (!writeTextArea.getText().isEmpty() && !outputTextArea.getText().isEmpty()) {
                buffer = writeTextArea.getText();
                writeTextArea.setText(outputTextArea.getText());
                outputTextArea.setText(buffer);
            }
        });

        buttonOptimize.addActionListener(event -> {
            final Controller controller = new Controller(1072, 33);
            if (labelMain.getText().matches("ENCRYPT")) {
                outputTextArea.setText(controller.encrypt(writeTextArea.getText(),keyTextArea.getText()));
            } else {
                outputTextArea.setText(controller.decrypt(writeTextArea.getText(),keyTextArea.getText()));
            }
        });

        form.getContentPane().add(buttonSwap);
        form.getContentPane().add(buttonOptimize);

        form.setVisible(true);
    }
}
