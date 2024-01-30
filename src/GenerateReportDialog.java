import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

class GenerateReportDialog extends JDialog {
    private JTextField startDateField, endDateField;
    private JButton generateButton;
    private InventoryManagementSystem inventorySystem;

    public GenerateReportDialog(JFrame parent, InventoryManagementSystem inventorySystem) {
        super(parent, "Generate Report", true);
        this.inventorySystem = inventorySystem;

        // Create components
        JLabel startDateLabel = new JLabel("Start Date (yyyy-MM-dd):");
        JLabel endDateLabel = new JLabel("End Date (yyyy-MM-dd):");

        startDateField = new JTextField();
        endDateField = new JTextField();

        generateButton = new JButton("Generate Report");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        // Set layout
        setLayout(new GridLayout(3, 2));
        add(startDateLabel);
        add(startDateField);
        add(endDateLabel);
        add(endDateField);
        add(new JLabel()); // Empty label for spacing
        add(generateButton);

        // Set dialog properties
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    private void generateReport() {
        try {
            String startDateStr = startDateField.getText();
            String endDateStr = endDateField.getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            inventorySystem.generateMonthlyReport(startDate, endDate);
            JOptionPane.showMessageDialog(this, "Report generated successfully!");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private void clearFields() {
        startDateField.setText("");
        endDateField.setText("");
    }
}
