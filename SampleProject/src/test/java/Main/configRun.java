package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class configRun {

    private static final String CONFIG_FILE = System.getProperty("user.dir") + "\\Run.properties";

    private Properties properties;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new configRun().createAndShowGUI());
    }

    public configRun() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Configuration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 550); // Increased height for header and buttons
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel lblHeader = new JLabel("TESTAUTOTHON EXECUTOR", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeader.setForeground(Color.BLACK);

        // Browser Selection
        JLabel lblBrowserDesc = new JLabel("Select the browser to be used for testing.", SwingConstants.CENTER);
        JLabel lblBrowser = new JLabel("Browser:");
        String[] browsers = {"Chrome", "Firefox", "Edge","Incog_Chrome","Incog_firefox"};
        JComboBox<String> cmbBrowser = new JComboBox<>(browsers);
        cmbBrowser.setSelectedItem(properties.getProperty("Browser", "Chrome"));

        // Broken Link Validation
        JLabel lblBrokenLinkDesc = new JLabel("Enable or disable the validation of broken links on the website.", SwingConstants.CENTER);
        JLabel lblBrokenLinkValidation = new JLabel("Broken Link Validation:");
        JPanel pnlBrokenLinkValidation = new JPanel();
        JRadioButton rbtnBrokenLinkYes = new JRadioButton("Yes");
        JRadioButton rbtnBrokenLinkNo = new JRadioButton("No");
        ButtonGroup groupBrokenLink = new ButtonGroup();
        groupBrokenLink.add(rbtnBrokenLinkYes);
        groupBrokenLink.add(rbtnBrokenLinkNo);
        if ("Y".equals(properties.getProperty("BrokenLinkValidationFlag", "Y"))) {
            rbtnBrokenLinkYes.setSelected(true);
        } else {
            rbtnBrokenLinkNo.setSelected(true);
        }
        pnlBrokenLinkValidation.add(rbtnBrokenLinkYes);
        pnlBrokenLinkValidation.add(rbtnBrokenLinkNo);

        // API Response Value
        JLabel lblAPIResponseDesc = new JLabel("Enable or disable validation of API responses with given values.", SwingConstants.CENTER);
        JLabel lblAPIResponseValue = new JLabel("API Response Value:");
        JPanel pnlAPIResponseValue = new JPanel();
        JRadioButton rbtnAPIResponseYes = new JRadioButton("Yes");
        JRadioButton rbtnAPIResponseNo = new JRadioButton("No");
        ButtonGroup groupAPIResponse = new ButtonGroup();
        groupAPIResponse.add(rbtnAPIResponseYes);
        groupAPIResponse.add(rbtnAPIResponseNo);
        if ("Y".equals(properties.getProperty("APIResponseValue", "Y"))) {
            rbtnAPIResponseYes.setSelected(true);
        } else {
            rbtnAPIResponseNo.setSelected(true);
        }
        pnlAPIResponseValue.add(rbtnAPIResponseYes);
        pnlAPIResponseValue.add(rbtnAPIResponseNo);

        // APITest Entry
        JLabel lblAPITestDesc = new JLabel("Specify the API Test response codes to be tested (comma-separated).", SwingConstants.CENTER);
        JLabel lblAPITest = new JLabel("API Response Status Code Test:");
        JTextField txtAPITest = new JTextField(properties.getProperty("APITest", "200,400,415,404"));

        // Mobile Selection
        JLabel lblMobileDesc = new JLabel("Select the Emulator to be used for testing.", SwingConstants.CENTER);
        JLabel lblMobile = new JLabel("Mobile:");
        String[] mobiles = {"EmulatorAndroid", "Pixel 8 Pro", "Rotated Mode EmulatorAndroid"};
        JComboBox<String> cmbMobile = new JComboBox<>(mobiles);
        cmbMobile.setSelectedItem(properties.getProperty("Mobile", "EmulatorAndroid"));

        // Save Button
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                properties.setProperty("Browser", (String) cmbBrowser.getSelectedItem());
                properties.setProperty("BrokenLinkValidationFlag", rbtnBrokenLinkYes.isSelected() ? "Y" : "N");
                properties.setProperty("APIResponseValue", rbtnAPIResponseYes.isSelected() ? "Y" : "N");
                properties.setProperty("APITest", txtAPITest.getText()); // Save the API Test values
                properties.setProperty("Mobile", (String) cmbMobile.getSelectedItem()); // Save the selected mobile value

                try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
                    properties.store(out, null);
                    JOptionPane.showMessageDialog(frame, "Configuration saved successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving configuration.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Run Button
        JButton btnRun = new JButton("Run");
        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Path to the .bat file
                    String[] batFilePath = {"cmd.exe", "/C", "Start", System.getProperty("user.dir") + "\\runTestNg.bat"};
                    Process process = Runtime.getRuntime().exec(batFilePath);
                    process.waitFor(); // Wait for the process to complete

                    JOptionPane.showMessageDialog(frame, "Test execution starting..");
                } catch (IOException | InterruptedException ex) {
                    JOptionPane.showMessageDialog(frame, "Error running the test script.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Layout with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        frame.add(lblHeader, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(lblBrowser, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frame.add(cmbBrowser, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        frame.add(lblBrowserDesc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(lblBrokenLinkValidation, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frame.add(pnlBrokenLinkValidation, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        frame.add(lblBrokenLinkDesc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(lblAPIResponseValue, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frame.add(pnlAPIResponseValue, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        frame.add(lblAPIResponseDesc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(lblAPITest, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frame.add(txtAPITest, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        frame.add(lblAPITestDesc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(lblMobile, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        frame.add(cmbMobile, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 1;
        frame.add(lblMobileDesc, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        frame.add(btnSave, gbc);

        gbc.gridy = 7; // Place the "Run" button below the "Save" button
        gbc.gridwidth = 2;
        frame.add(btnRun, gbc);

        frame.setVisible(true);
    }
}
