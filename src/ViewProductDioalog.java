import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

class ViewProductsDialog extends JDialog {
    private JTable productsTable;
    private InventoryManagementSystem inventorySystem;

    public ViewProductsDialog(JFrame parent, InventoryManagementSystem inventorySystem) {
        super(parent, "View Products", true);
        this.inventorySystem = inventorySystem;

        // Create table model
        ProductTableModel tableModel = new ProductTableModel(inventorySystem.getAllProducts());

        // Create table
        productsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productsTable);

        // Set layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Set dialog properties
        setSize(500, 300);
        setLocationRelativeTo(parent);
    }
}

class ProductTableModel extends AbstractTableModel {
    private ArrayList<Product> products;
    private String[] columnNames = {"Name", "Description", "Price", "Quantity"};

    public ProductTableModel(ArrayList<Product> products) {
        this.products = products;
    }

    public int getRowCount() {
        return products.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int row, int column) {
        Product product = products.get(row);

        switch (column) {
            case 0:
                return product.name;
            case 1:
                return product.description;
            case 2:
                return product.price;
            case 3:
                return product.quantity;
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }
}
