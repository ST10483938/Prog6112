import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public static class ProductApp extends JFrame {

    private JTextField txtCustomer = new JTextField(20);
    private JComboBox<String> cboProduct = new JComboBox<>(new String[]{
            "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private JTextField txtPrice = new JTextField(20);
    private JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        setTitle("Product App");
        setSize(450, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createMenu();
        createForm();
    }

    // ---------- Menu: File > Exit, Tools > Process / Clear ----------
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        exitItem.addActionListener(e -> System.exit(0));
        processItem.addActionListener(e -> processSale()); // same logic as the button
        clearItem.addActionListener(e -> clearForm());
    }

    // ---------- Form: labels, text boxes, combo box, button ----------
    private void createForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Customer Name:"));
        panel.add(txtCustomer);
        panel.add(new JLabel("Select Product:"));
        panel.add(cboProduct);
        panel.add(new JLabel("Price:"));
        panel.add(txtPrice);
        panel.add(new JLabel(""));
        panel.add(btnProcess);

        add(panel);

        btnProcess.addActionListener(e -> processSale());
    }

    // ---------- Main logic (used by the button AND the Process menu item) ----------
    private void processSale() {
        String customer = txtCustomer.getText().trim();
        String product = (String) cboProduct.getSelectedItem();

        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price (numbers only).");
            return;
        }

        ProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        // Show the result
        JOptionPane.showMessageDialog(this,
                "Customer Name: " + customer + "\n" +
                        "Product: " + product + "\n" +
                        "VAT: R " + vat + "\n" +
                        "COMMISSION: R " + commission + "\n" +
                        "TOTAL: R " + total);

        // Save to data.txt
        saveToFile(customer, product, vat, commission, total);
    }

    private void saveToFile(String customer, String product,
                            double vat, double commission, double total) {
        // true = add to the end of the file so earlier sales are kept
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
            writer.println("DATA LOG");
            writer.println("*****************************");
            writer.println("Customer Name: " + customer);
            writer.println("Product: " + product);
            writer.println("VAT: R " + vat);
            writer.println("COMMISSION: R " + commission);
            writer.println("TOTAL: R " + total);
            writer.println("*****************************");
            writer.println();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save to data.txt");
        }
    }

    // ---------- Clear: empty the text boxes and reset the combo box ----------
    private void clearForm() {
        txtCustomer.setText("");
        txtPrice.setText("");
        cboProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new ProductApp().setVisible(true);
    }
}
void main() {
}