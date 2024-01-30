import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UpdateProductDialog extends JDialog {
    private JTextField nameField, priceField, quantityField;
    private JButton updateButton;
    private InventoryManagementSystem inventorySystem;

    public UpdateProductDialog(JFrame parent, InventoryManagementSystem inventorySystem) {
        super(parent, "Update Product", true);
        this.inventorySystem = inventorySystem;

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        JLabel priceLabel = new JLabel("New Price:");
        JLabel quantityLabel = new JLabel("Quantity Adjustment:");

        nameField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        updateButton = new JButton("Update Product");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        // Set layout
        setLayout(new GridLayout(4, 2));
        add(nameLabel);
        add(nameField);
        add(priceLabel);
        add(priceField);
        add(quantityLabel);
        add(quantityField);
        add(new JLabel()); // Empty label for spacing
        add(updateButton);

        // Set dialog properties
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    private void updateProduct() {
        String name = nameField.getText();
        double newPrice = Double.parseDouble(priceField.getText());
        int quantityAdjustment = Integer.parseInt(quantityField.getText());

        inventorySystem.updateProduct(name, newPrice, quantityAdjustment);
        JOptionPane.showMessageDialog(this, "Product updated successfully!");
        clearFields();
    }

    private void clearFields() {
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }
}
