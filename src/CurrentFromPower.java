import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class CurrentFromPower implements ActionListener {
    private final int appWidth=300;
    private double current=0;
    private final double voltage=230;
    private double powerFactor=1;
    private double activePower;
    private double cableLength=0;


    private double deviceNominalCurrent=0;
    private double deviceActingCurrent=0;
    private double deviceActingFactor;
    private int numberOfWires=0;

    boolean inflammable;
    boolean flameresistant;

    private int[] deviceCurrentArray={6,10,16,20,25,32,40,50,63,80,100,125,160,200,224,250,300,315,355,400,500,630,800,1000};

    public JPanel currentFromPowerPanel;
    private JFrame frame;

    private JTextArea  currentFromActivePowerArea, deviceNominalCurrentArea, deviceActingCurrentArea, cableTypeArea,voltageDropArea;
    private JTextField activePowerField,powerFactorField, cableLengthField;
    private JCheckBox inflammableBox,flameresistantBox, peBox;


    private JButton calculateButton;
    private JComboBox selectPhasesComboBox, selectDeviceComboBox, selectInsulationComboBox, selectMaterialComboBox, selectLayingTypeComboBox;

    private JSpinner maxdVSpinner,numberOfCablesSpinner;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;





    public static void changeFont(Component component, Font font){
        component.setFont(font);
        if(component instanceof Container){
            for(Component child : ((Container)component).getComponents()){
                changeFont(child, font);
            }

        }
    }
    public CurrentFromPower(){
        activePowerField =new JTextField(30);
        activePowerField.addActionListener(this);

        powerFactorField =new JTextField(30);
        powerFactorField.addActionListener(this);

        cableLengthField=new JTextField(30);
        cableLengthField.addActionListener(this);

        inflammableBox=new JCheckBox("Inflammable");
        inflammableBox.addActionListener(this);
        flameresistantBox=new JCheckBox("Flame resistant");
        flameresistantBox.addActionListener(this);
        peBox=new JCheckBox("Protective wire");
        peBox.addActionListener(this);



        calculateButton =new JButton("Calculate");
        calculateButton.setBackground(Color.cyan);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.addActionListener(this);


        currentFromActivePowerArea=new JTextArea(6,20);
        deviceNominalCurrentArea=new JTextArea(6,20);
        deviceActingCurrentArea=new JTextArea(6,20);
        cableTypeArea=new JTextArea(6,20);
        voltageDropArea=new JTextArea(6,20);




        selectPhasesComboBox =new JComboBox();
        selectPhasesComboBox.addItem("3");
        selectPhasesComboBox.addItem("1");
        selectPhasesComboBox.addActionListener( this);

        selectDeviceComboBox =new JComboBox();
        selectDeviceComboBox.addItem("FUSE");
        selectDeviceComboBox.addItem("CIRCUIT BREAKER");
        selectDeviceComboBox.addItem("INSTALLATION BREAKER");
        selectDeviceComboBox.addActionListener(this);

        selectInsulationComboBox =new JComboBox();
        selectInsulationComboBox.addItem("XLPE");
        selectInsulationComboBox.addItem("PVC");
        selectInsulationComboBox.addActionListener(this);

        selectMaterialComboBox =new JComboBox();
        selectMaterialComboBox.addItem("Cu");
        selectMaterialComboBox.addItem("Al");
        selectMaterialComboBox.addActionListener(this);

        selectLayingTypeComboBox =new JComboBox();
        selectLayingTypeComboBox.addItem("D1");
        selectLayingTypeComboBox.addItem("E");
        selectLayingTypeComboBox.addItem("F");
        selectLayingTypeComboBox.addActionListener(this);



        maxdVSpinner=new JSpinner(new SpinnerNumberModel(0.50,0.00,5.00,0.10));
        numberOfCablesSpinner=new JSpinner(new SpinnerNumberModel(1,1,10,1));



        currentFromPowerPanel =new JPanel();
        currentFromPowerPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        currentFromPowerPanel.setLayout((new GridLayout(0,1)));


        menuBar=new JMenuBar();
        menu=new JMenu("Options");
        menu.setMnemonic(KeyEvent.VK_T);

        JMenuItem exitProgram=new JMenuItem("Exit program");
        JMenuItem resetValues=new JMenuItem("Reset values");

        menu.add(resetValues);
        menu.add(exitProgram);
        menu.addActionListener(this);


        menuBar.add(menu);

        currentFromPowerPanel.add(new JLabel("Type active power [kW]: "));
        currentFromPowerPanel.add(activePowerField);
        currentFromPowerPanel.add(new JLabel("Choose number of phases: "));
        currentFromPowerPanel.add(selectPhasesComboBox);
        currentFromPowerPanel.add(new JLabel("Type power factor: "));
        currentFromPowerPanel.add(powerFactorField);
        currentFromPowerPanel.add(new JLabel("Current is: "));
        currentFromPowerPanel.add(currentFromActivePowerArea);
        currentFromPowerPanel.add(new JLabel("Select device:"));
        currentFromPowerPanel.add(selectDeviceComboBox);
        currentFromPowerPanel.add(new JLabel("Minimal device's nominal current: "));
        currentFromPowerPanel.add(deviceNominalCurrentArea);
        currentFromPowerPanel.add(new JLabel("Device's acting current: "));
        currentFromPowerPanel.add(deviceActingCurrentArea);
        currentFromPowerPanel.add(new JLabel("Choose insulation: "));
        currentFromPowerPanel.add(selectInsulationComboBox);
        currentFromPowerPanel.add(new JLabel("Choose material: "));
        currentFromPowerPanel.add(selectMaterialComboBox);
        currentFromPowerPanel.add(new JLabel("Choose laying type: "));
        currentFromPowerPanel.add(selectLayingTypeComboBox);
        currentFromPowerPanel.add(new JLabel("Type cable length [m]: "));
        currentFromPowerPanel.add(cableLengthField);
        currentFromPowerPanel.add(new JLabel("Select number of cables per phase"));
        currentFromPowerPanel.add(numberOfCablesSpinner);
        currentFromPowerPanel.add(new JLabel("Select maximum voltage drop [%]: "));
        currentFromPowerPanel.add(maxdVSpinner);
        currentFromPowerPanel.add(new JLabel("Calculated voltage drop [%]: "));
        currentFromPowerPanel.add(voltageDropArea);
        currentFromPowerPanel.add(new JLabel("Special requirement: "));
        currentFromPowerPanel.add(inflammableBox);
        currentFromPowerPanel.add(flameresistantBox);
        currentFromPowerPanel.add(peBox);
        currentFromPowerPanel.add(new JLabel("Cable type is: "));
        currentFromPowerPanel.add(cableTypeArea);
        currentFromPowerPanel.add(calculateButton);






        frame=new JFrame();
        frame.add(currentFromPowerPanel, BorderLayout.CENTER);
        frame.setJMenuBar(menuBar);
        frame.setLayout(new GridLayout(1,2));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("POWER CALCULATOR");

        frame.setPreferredSize(new Dimension(appWidth,800));
        frame.pack();
        frame.setLocationRelativeTo(null);
        changeFont(frame, new Font("Arial", Font.BOLD, 12));

        frame.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object action=e.getSource();
        String powerString = activePowerField.getText();
        String powerFactorString = powerFactorField.getText();
        String cableLengthString=cableLengthField.getText();
        double maxdV=(double) maxdVSpinner.getValue();
        int numberOfCables=(int)numberOfCablesSpinner.getValue();

        System.out.println(maxdV);
        String selectPhasesString = (String) selectPhasesComboBox.getSelectedItem();
        String selectDeviceString = (String) selectDeviceComboBox.getSelectedItem();
        String selectLayingTypeString = (String) selectLayingTypeComboBox.getSelectedItem();
        boolean inflammable = inflammableBox.isSelected();
        boolean flameresistant = flameresistantBox.isSelected();
        boolean pe = peBox.isSelected();

        if (pe == true) {
            if (selectPhasesString.equals("1")) numberOfWires = 3;
            else numberOfWires = 5;
        } else {
            if (selectPhasesString.equals("1")) numberOfWires = 2;
            else numberOfWires = 4;
        }

        String insulationString = (String) selectInsulationComboBox.getSelectedItem();
        String materialString = (String) selectMaterialComboBox.getSelectedItem();


        try {
            activePower = Double.parseDouble(powerString);
            powerFactor = Double.parseDouble(powerFactorString);
            cableLength=Double.parseDouble(cableLengthString);

        } catch (NumberFormatException nfe) {
            System.err.println("This is not a number");
        }


        if (selectPhasesString.equals("1")) {
            current = activePower * 1000 / voltage / powerFactor;

        } else if (selectPhasesString.equals("3")) {
            current = activePower * 1000 / voltage / powerFactor / 3;
        }


        if (selectDeviceString.equals("FUSE")) {
            FuseBreaker selectedDevice = new FuseBreaker();
            deviceActingFactor = selectedDevice.actingCurrentFactor;
            selectedDevice.selectDeviceNominalCurrent(current);
            currentFromActivePowerArea.setText(String.format("%.2f", current) + " A\n");
            deviceNominalCurrent = selectedDevice.nominalCurrent;
            deviceActingCurrent = deviceNominalCurrent * selectedDevice.actingCurrentFactor;
            deviceNominalCurrentArea.setText(String.format("%.2f", selectedDevice.nominalCurrent) + " A\n");
            deviceActingCurrentArea.setText(String.format("%.2f", selectedDevice.nominalCurrent * deviceActingFactor) + " A\n");
        } else if (selectDeviceString.equals("INSTALLATION BREAKER")) {
            InstallationBreaker selectedDevice = new InstallationBreaker();
            deviceActingFactor = selectedDevice.actingCurrentFactor;
            selectedDevice.selectDeviceNominalCurrent(current);
            currentFromActivePowerArea.setText(String.format("%.2f", current) + " A\n");
            deviceNominalCurrent = selectedDevice.nominalCurrent;
            deviceActingCurrent = deviceNominalCurrent * selectedDevice.actingCurrentFactor;
            deviceNominalCurrentArea.setText(String.format("%.2f", selectedDevice.nominalCurrent) + " A\n");
            deviceActingCurrentArea.setText(String.format("%.2f", selectedDevice.nominalCurrent * deviceActingFactor) + " A\n");
        } else if (selectDeviceString.equals("CIRCUIT BREAKER")) {
            CircuitBreaker selectedDevice = new CircuitBreaker();
            deviceActingFactor = selectedDevice.actingCurrentFactor;
            selectedDevice.selectDeviceNominalCurrent(current);
            currentFromActivePowerArea.setText(String.format("%.2f", current) + " A\n");
            deviceNominalCurrent = selectedDevice.nominalCurrent;
            deviceActingCurrent = deviceNominalCurrent * selectedDevice.actingCurrentFactor;
            deviceNominalCurrentArea.setText(String.format("%.2f", selectedDevice.nominalCurrent) + " A\n");
            deviceActingCurrentArea.setText(String.format("%.2f", selectedDevice.nominalCurrent * deviceActingFactor) + " A\n");
        }

        Cable cable = new Cable(insulationString, materialString, inflammable, flameresistant, selectLayingTypeString, deviceNominalCurrent, deviceActingCurrent, maxdV, current,cableLength,selectPhasesString,numberOfCables);
        voltageDropArea.setText(String.format("%.2f",cable.dV));
        if (selectLayingTypeString.equals("F")) {
            cableTypeArea.setText(numberOfWires +"x " + cable.type + " 1x" + cable.cableDiameter);
        }
        else {
            cableTypeArea.setText(cable.type + " " + numberOfWires + "x" + cable.cableDiameter);
        }
    }

}