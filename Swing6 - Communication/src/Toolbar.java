import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {
    private JButton helloButton;
    private JButton goodbyeButton;
    //problem, components in a mainframe should know as little about each other as possible. Since toolbar uses a textpanel, toolbar can use all of textpanels methods, making it more complex.
    //will be changed in Swing7 to be simpler. communication should only go through mainframe
    private TextPanel textPanel;

    public Toolbar() {
        helloButton = new JButton("Hello");
        goodbyeButton = new JButton("Goodbye");

        helloButton.addActionListener(this);
        goodbyeButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(helloButton);
        add(goodbyeButton);

    }

    public void setTextPanel(TextPanel textPanel) {
        this.textPanel = textPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if(clicked == helloButton) {
            textPanel.appendText("Hello\n");
        }
        else if(clicked == goodbyeButton) {
            textPanel.appendText("Goodbye\n");
        }
    }
}
