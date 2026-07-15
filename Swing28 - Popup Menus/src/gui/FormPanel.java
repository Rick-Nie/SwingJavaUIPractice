package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormListener formListener;
    private JList ageList;
    private JComboBox empCombo;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

    public FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        //new
        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList();
        empCombo = new JComboBox();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");
        okBtn = new JButton("OK");

        //Set up mnemonic
        okBtn.setMnemonic(KeyEvent.VK_O);

        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");

        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        genderGroup = new ButtonGroup();

        maleRadio.setSelected(true);

        //Set up gender radios
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        //Set up tax ID
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = citizenCheck.isSelected();
                taxLabel.setEnabled(isTicked);
                taxField.setEnabled(isTicked);
            }
        });

        //Set up list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18-65"));
        ageModel.addElement(new AgeCategory(2, "65 or older"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(110,70));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        //Set up combo box.
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);



        okBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { //called whenever button is clicked
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
                String empCat = (String) empCombo.getSelectedItem();
                String taxId = taxField.getText();
                boolean usCitizen = citizenCheck.isSelected();

                String gender = genderGroup.getSelection().getActionCommand();

                FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), empCat, taxId, usCitizen, gender);

                if (formListener != null){
                    formListener.formEventOccurred(ev);
                }

                nameField.setText("");
                occupationField.setText("");
                ageList.setSelectedIndex(1);
                empCombo.setSelectedIndex(0);
                citizenCheck.setSelected(false);
                taxLabel.setEnabled(false);
                taxField.setEnabled(false);
                taxField.setText("");
                maleRadio.setSelected(true);
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        layoutComponents();
    }

    public void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();



        // FIRST ROW: name

        gc.weightx = 1; //how much space this cell takes compared to other cells, number doesn't matter but weight relative to other weight does
        gc.weighty = 0.1;

        gc.gridy = 0;

        gc.gridx = 0; //top left corner is 0,0, bottom right is max,max
        gc.fill = GridBagConstraints.NONE; //take up all space in cell or not
        gc.anchor = GridBagConstraints.LINE_END; //side of the cell that the component sticks to in the cell. LINE_END aligns to middle right of cell.
        gc.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0; //typed again to keep track of what's happening
        gc.anchor = GridBagConstraints.LINE_START; //middle left of cell;
        gc.insets = new Insets(0, 0, 0, 0);
        add(nameField, gc);

        // NEXT ROW: occupation
        gc.gridy += 1;

        gc.weightx = 1;//how much space this cell takes compared to other cells, number doesn't matter but weight relative to other weight does
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);

        // NEXT ROW: ageList
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(ageList, gc);

        // NEXT ROW: empCombo
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Employment: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(empCombo, gc);

        // NEXT ROW: citizenCheck
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("US Citizen: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(citizenCheck, gc);

        // NEXT ROW: taxField
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(taxLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(taxField, gc);

        // NEXT ROW: maleRadioButton
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(maleRadio, gc);

        // NEXT ROW: femaleRadioButton
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(femaleRadio, gc);

        // NEXT ROW: ok button
        gc.gridy += 1;

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(okBtn, gc);
    }
    public void setFormListener(FormListener listener){
        this.formListener = listener;
    }
}

class AgeCategory{
    private int id;
    private String text;

    public AgeCategory(int id, String text){
        this.id = id;
        this.text = text;
    }

    public String toString(){
        return text;
    }

    public int getId(){
        return id;
    }
}
