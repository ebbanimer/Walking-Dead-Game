package com.dt181g.project.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * JFrame representing the start-view.
 * @author Ebba Nimér
 */
public class StartView extends JFrame {

    GridBagConstraints c = new GridBagConstraints();
    JLabel characterLbl = new JLabel("Pick a character...");
    JLabel charNameLabel = new JLabel();
    JLabel weaponLabel = new JLabel();
    JComboBox<String> characterCmb;
    JButton startBtn = new JButton("Start game");
    JButton instructionBtn = new JButton("Instructions");
    ImageIcon charImage = new ImageIcon();
    JLabel imgLbl = new JLabel(charImage);
    StartAnimationPanel startAnimationPanel;

    /**
     * Initialize start-view with BorderLayout as layout manager.
     * @param characters list of character-names.
     * @param size initial size of animation.
     * @param path image path for animation.
     */
    public StartView(String[] characters, int size, String path){
        this.setTitle("The walking dead");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        startAnimationPanel = new StartAnimationPanel(size, path);

        this.add(addStartPanel(), BorderLayout.CENTER);
        this.add(addImgPanel(), BorderLayout.WEST);
        this.add(addPickPanel(characters), BorderLayout.SOUTH);
        this.add(startAnimationPanel, BorderLayout.EAST);
        this.pack();
        this.setVisible(true);
    }

    /**
     * JPanel representing the combobox where user chooses character, using GridBagLayout.
     * @param characters list of characters
     * @return JPanel containing combobox
     */
    public JPanel addPickPanel(String[] characters){
        JPanel pickPanel = new JPanel();
        pickPanel.setLayout(new GridBagLayout());
        pickPanel.setBackground(Color.decode("#0f4c5c"));
        pickPanel.setPreferredSize(new Dimension(600, 150));

        characterLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        characterLbl.setForeground(Color.WHITE);

        characterCmb = new JComboBox<>(characters);     // display character names in combobox
        characterCmb.setPreferredSize(new Dimension(250, 40));
        characterCmb.setBackground(Color.decode("#cbf3f0"));

        // align label and combobox
        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;
        c.gridy = 1;
        pickPanel.add(characterLbl,c);
        c.gridx = 0;
        c.gridy = 2;
        pickPanel.add(characterCmb,c);
        return pickPanel;
    }

    /**
     * JPanel representing button to start game.
     * @return JPanel with components.
     */
    public JPanel addStartPanel(){
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridBagLayout());
        startPanel.setPreferredSize(new Dimension(300, 400));
        startPanel.setBackground(Color.decode("#0f4c5c"));
        startBtn.setPreferredSize(new Dimension(150,40));
        startBtn.setBackground(Color.decode("#cbf3f0"));
        instructionBtn.setPreferredSize(new Dimension(150,40));
        instructionBtn.setBackground(Color.decode("#cbf3f0"));

        // adding font-style and align using gridbag-layout.
        addFontStyle(startPanel, charNameLabel, charNameLabel);
        c.gridy = 3;
        startPanel.add(startBtn,c);

        c.gridy = 4;
        startPanel.add(instructionBtn,c);

        return startPanel;
    }

    /**
     * JPanel containing image of character.
     * @return JPanel with image
     */
    public JPanel addImgPanel(){
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new GridBagLayout());
        imgPanel.setPreferredSize(new Dimension(300, 400));
        imgPanel.setBackground(Color.decode("#277da1"));

        addFontStyle(imgPanel, weaponLabel, imgLbl);
        c.gridy = 2;
        imgPanel.add(weaponLabel,c);
        return imgPanel;
    }

    /**
     * Add font-style to passed panel with jlabels.
     * @param panel panel containing labels.
     * @param jLabel1 jlabel
     * @param jLabel2 jlabel
     */
    private void addFontStyle(JPanel panel, JLabel jLabel1, JLabel jLabel2) {
        jLabel1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        jLabel1.setForeground(Color.WHITE);

        c.insets = new Insets(20,10,20,10);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(jLabel2,c);
        c.gridx = 0;
    }

    /**
     * Provide chosen character to calling client.
     * @return string of name of character
     */
    public String getCharacter(){
        return (String) characterCmb.getSelectedItem();
    }

    /**
     * Set the character with values that was chosen.
     * @param character name of character
     * @param weapon name of weapon
     * @param imgPath image path
     */
    public void setCharacter(String character, String weapon, String imgPath){
        charImage = new ImageIcon(imgPath);
        imgLbl.setIcon(charImage);
        charNameLabel.setText("You have chosen character " + character);
        weaponLabel.setText("Weapon: " + weapon);
    }

    /**
     * display instructions when instruction button is clicked.
     * @param message message to be displayed.
     */
    public void showInstructions(String message){
        JOptionPane.showMessageDialog(this, displayMsg(message), "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * if invalid choice, display passed message.
     * @param msg message to be displayed.
     */
    public void displayError(String msg){
        JOptionPane.showMessageDialog(this, displayMsg(msg), "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * display passed message if character is already dead.
     * @param msg message to be displayed
     */
    public void displayDead(String msg){
        JOptionPane.showMessageDialog(this, displayMsg(msg), "Already dead...", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * design of JOptionpane.
     * @param message message to display
     * @return JPanel containing style and message
     */
    private JPanel displayMsg(String message) {
        return getjPanel(message);
    }

    /**
     * static method to provide access to other view-classes. containing styling of jpanel.
     * @param message message to be displayed.
     * @return JPanel containing style and message.
     */
    static JPanel getjPanel(String message) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        panel.setBackground(Color.decode("#0f4c5c"));
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        label.setForeground(Color.WHITE);
        panel.add(label);

        // add styling
        UIManager.put("OptionPane.background",Color.decode("#0f4c5c"));
        UIManager.put("Panel.background",Color.decode("#0f4c5c"));
        return panel;
    }

    public JPanel getStartAnimationPanel(){
        return startAnimationPanel;
    }

    /**
     * Add listener for instruction-button.
     * @param listener action-listener
     */
    public void addInstructionsListener(ActionListener listener){ instructionBtn.addActionListener(listener);}

    /**
     * Add listener for combo-box.
     * @param listener action-listener
     */
    public void addComboListener(ActionListener listener){
        characterCmb.addActionListener(listener);
    }

    /**
     * Add listener for start-button.
     * @param listener action listener
     */
    public void addStartListener(ActionListener listener){
        startBtn.addActionListener(listener);
    }

}