import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddProductDialog extends JDialog {
    private JTextField nameField, descriptionField, priceField, quantityField;
    private JButton addButton;
    private InventoryManagementSystem inventorySystem;

    public AddProductDialog(JFrame parent, InventoryManagementSystem inventorySystem) {
        super(parent, "Add Product", true);
        this.inventorySystem = inventorySystem;

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        JLabel descriptionLabel = new JLabel("Description:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel quantityLabel = new JLabel("Quantity:");

        nameField = new JTextField();
        descriptionField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        // Set layout
        setLayout(new GridLayout(5, 2));
        add(nameLabel);
        add(nameField);
        add(descriptionLabel);
        add(descriptionField);
        add(priceLabel);
        add(priceField);
        add(quantityLabel);
        add(quantityField);
        add(new JLabel()); // Empty label for spacing
        add(addButton);

        // Set dialog properties
        setSize(300, 200);
        setLocationRelativeTo(parent);
    }

    private void addProduct() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        inventorySystem.addProduct(name, description, price, quantity);
        JOptionPane.showMessageDialog(this, "Product added successfully!");
        clearFields();
    }

    private void clearFields() {
        nameField.setText("");
        descriptionField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }
}
