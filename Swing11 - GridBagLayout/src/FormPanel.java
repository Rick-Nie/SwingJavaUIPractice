import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;

    public FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        //new
        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);

        okBtn = new JButton("OK");

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;//how much space this cell takes compared to other cells, number doesn't matter but weight relative to other weight does
        gc.weighty = 0.1;

        // FIRST ROW
        gc.gridx = 0; //top left corner is 0,0, bottom right is max,max
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE; //take up all space in cell or not
        gc.anchor = GridBagConstraints.LINE_END; //side of the cell that the component sticks to in the cell. LINE_END aligns to middle right of cell.
        gc.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0; //typed again to keep track of what's happening
        gc.anchor = GridBagConstraints.LINE_START; //middle left of cell;
        gc.insets = new Insets(0, 0, 0, 0);
        add(nameField, gc);

        // SECOND ROW
        gc.weightx = 1;//how much space this cell takes compared to other cells, number doesn't matter but weight relative to other weight does
        gc.weighty = 0.1;

        gc.gridy = 1;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;

        add(occupationLabel, gc);

        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);

        // THIRD ROW
        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridy = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gc);
    }
}
