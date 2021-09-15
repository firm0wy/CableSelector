import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PowerFromCurrent implements ActionListener {
    private final int appWidth=300;
    private double current=0;
    private final double voltage=230;
    private double powerFactor=1;
    private double activePower;
    private double reactivePower;
    private double apparentPower;
    public JPanel powerFromCurrentPanel;
    private JFrame frame;
    private JLabel label1, label2, label3,label4,label5;


    private JTextArea activePowerArea, reactivePowerArea, apparentPowerArea, currentFromActivePowerArea;
    private JTextField currentField;

    private JTextField powerFactorField;
    private JButton calculateButton;
    private JComboBox selectPhases;





    public static void changeFont(Component component, Font font){
        component.setFont(font);
        if(component instanceof Container){
            for(Component child : ((Container)component).getComponents()){
                changeFont(child, font);
            }

        }
    }
    public PowerFromCurrent(){
        currentField =new JTextField(30);
        currentField.addActionListener(this);

        powerFactorField =new JTextField(30);
        powerFactorField.addActionListener(this);


        label1=new JLabel("Type current: ");
        label2=new JLabel("Type power factor: ");
        label3=new JLabel("Active power is: ");
        label4=new JLabel("Reactive power is: ");
        label5=new JLabel("Apparent power is: ");

        calculateButton =new JButton("Calculate");
        calculateButton.setBackground(Color.cyan);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.addActionListener(this);

        activePowerArea =new JTextArea(6,20);
        reactivePowerArea =new JTextArea(6,20);
        apparentPowerArea =new JTextArea(6,20);
        currentFromActivePowerArea=new JTextArea(6,20);




        selectPhases=new JComboBox();
        selectPhases.addItem("1");
        selectPhases.addItem("3");
        selectPhases.addActionListener( this);


        powerFromCurrentPanel =new JPanel();
        powerFromCurrentPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        powerFromCurrentPanel.setLayout((new GridLayout(0,1)));



        powerFromCurrentPanel.add(selectPhases);
        powerFromCurrentPanel.add(label1);
        powerFromCurrentPanel.add(currentField);
        powerFromCurrentPanel.add(label2);
        powerFromCurrentPanel.add(powerFactorField);
        powerFromCurrentPanel.add(label3);
        powerFromCurrentPanel.add(activePowerArea);
        powerFromCurrentPanel.add(label4);
        powerFromCurrentPanel.add(reactivePowerArea);
        powerFromCurrentPanel.add(label5);
        powerFromCurrentPanel.add(apparentPowerArea);
        powerFromCurrentPanel.add(new JLabel(""));




        frame=new JFrame();
        frame.add(powerFromCurrentPanel, BorderLayout.CENTER);
        //frame.add(new  CurrentFromPowerPanel, BorderLayout.CENTER);
        frame.setLayout(new GridLayout(1,2));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("POWER CALCULATOR");

        frame.setPreferredSize(new Dimension(appWidth,400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        changeFont(frame, new Font("Arial", Font.BOLD, 12));

        frame.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String currentString = currentField.getText();
        String powerFactorString = powerFactorField.getText();
        String selectPhasesString=(String)selectPhases.getSelectedItem();

        try {
            current = Double.parseDouble(currentString);
            powerFactor = Double.parseDouble(powerFactorString);
        }catch (NumberFormatException nfe){System.err.println("This is not a number");};
        double tangentFromPowerFactor=Math.tan(Math.acos(powerFactor));

        if (selectPhasesString.equals("1")) {
            activePower = current * voltage * 1/1000;
            reactivePower= current * voltage * 1/1000*tangentFromPowerFactor;
            apparentPower= current * voltage * 1/1000/powerFactor;

        }
        else if(selectPhasesString.equals("3")){
            activePower = current * voltage * 3/1000;
            reactivePower= current * voltage * 3/1000*tangentFromPowerFactor;
            apparentPower= current * voltage * 3/1000/powerFactor;
        }

        activePowerArea.setText(String.format("%.2f",activePower)+" kW\n");
        reactivePowerArea.setText(String.format("%.2f",reactivePower)+" kVAr\n");
        apparentPowerArea.setText(String.format("%.2f",apparentPower)+" kVA\n");



    }


}