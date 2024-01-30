import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

class Product {
    String name;
    String description;
    double price;
    int quantity;

    public Product(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}

class InventoryManagementSystem {
    private ArrayList<Product> products;

    public InventoryManagementSystem() {
        this.products = new ArrayList<>();
    }

    public void addProduct(String name, String description, double price, int quantity) {
        Product newProduct = new Product(name, description, price, quantity);
        products.add(newProduct);
    }

    public void updateProduct(String name, double newPrice, int quantityAdjustment) {
        for (Product product : products) {
            if (product.name.equals(name)) {
                product.price = newPrice;
                product.quantity += quantityAdjustment;
                break;
            }
        }
    }

    public Product findProductByName(String name) {
        for (Product product : products) {
            if (product.name.equals(name)) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public void generateMonthlyReport(Date startDate, Date endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Monthly Report from " + dateFormat.format(startDate) + " to " + dateFormat.format(endDate) + ":");
        System.out.println("-----------------------------------------------------");
        System.out.println("Product Name\t\tQuantity Sold\t\tCurrent Stock");
        System.out.println("-----------------------------------------------------");

        // Here you can iterate through sales transactions within the given date range and calculate quantities sold

        for (Product product : products) {
            System.out.println(product.name + "\t\t\t\t" + 0 + "\t\t\t\t" + product.quantity);
        }

        System.out.println("-----------------------------------------------------");
    }
}

class GUI {
    private JFrame frame;
    private InventoryManagementSystem inventorySystem;

    public GUI() {
        this.inventorySystem = new InventoryManagementSystem();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Inventory Management System");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addProductButton = new JButton("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddProductDialog();
            }
        });

        JButton updateProductButton = new JButton("Update Product");
        updateProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showUpdateProductDialog();
            }
        });

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGenerateReportDialog();
            }
        });

        JButton viewProductsButton = new JButton("View Products");
        viewProductsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showViewProductsDialog();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(addProductButton);
        panel.add(updateProductButton);
        panel.add(generateReportButton);
        panel.add(viewProductsButton);

        frame.add(panel);
        frame.setVisible(true);
    }
    private void showAddProductDialog() {
        AddProductDialog addProductDialog = new AddProductDialog(frame, inventorySystem);
        addProductDialog.setVisible(true);
    }

    private void showUpdateProductDialog() {
        UpdateProductDialog updateProductDialog = new UpdateProductDialog(frame, inventorySystem);
        updateProductDialog.setVisible(true);
    }

    private void showGenerateReportDialog() {
        GenerateReportDialog generateReportDialog = new GenerateReportDialog(frame, inventorySystem);
        generateReportDialog.setVisible(true);
    }

    private void showViewProductsDialog() {
        ViewProductsDialog viewProductsDialog = new ViewProductsDialog(frame, inventorySystem);
        viewProductsDialog.setVisible(true);
    }

}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}
