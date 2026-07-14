import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        //makes a window that has nothing

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Hello World");
                frame.setSize(600,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}