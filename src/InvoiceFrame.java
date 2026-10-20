import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InvoiceFrame extends JFrame {
    JTextField fullAddressField;
    JTextField productNameField, unitPriceField, quantityField;
    JTextArea invoiceTextArea;
    Invoice invoice;
    JPanel inputPnl, addressPnl, productPnl, buttonPnl;
    JScrollPane scrollPane;
    JButton addItemBtn, resetBtn;

    public InvoiceFrame() {
        setTitle("Invoice System");
        setSize(650, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        inputPnl = new JPanel();
        inputPnl.setLayout(new BoxLayout(inputPnl, BoxLayout.Y_AXIS));

        addressPnl = new JPanel(new GridLayout(1, 2, 5, 5));
        addressPnl.setBorder(BorderFactory.createTitledBorder("Customer Address Block"));
        fullAddressField = new JTextField();

        addressPnl.add(new JLabel("Customer Address:"));
        addressPnl.add(fullAddressField);
        inputPnl.add(addressPnl);

        productPnl = new JPanel(new GridLayout(3, 2, 5, 5));
        productPnl.setBorder(BorderFactory.createTitledBorder("Add Line Item"));
        productNameField = new JTextField();
        unitPriceField = new JTextField();
        quantityField = new JTextField();

        productPnl.add(new JLabel("Product Name:"));
        productPnl.add(productNameField);
        productPnl.add(new JLabel("Unit Price ($):"));
        productPnl.add(unitPriceField);
        productPnl.add(new JLabel("Quantity:"));
        productPnl.add(quantityField);
        inputPnl.add(productPnl);

        buttonPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addItemBtn = new JButton("Add Line Item");
        resetBtn = new JButton("New Invoice");
        buttonPnl.add(addItemBtn);
        buttonPnl.add(resetBtn);
        inputPnl.add(buttonPnl);

        add(inputPnl, BorderLayout.NORTH);

        invoiceTextArea = new JTextArea();
        invoiceTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        invoiceTextArea.setEditable(false);
        scrollPane = new JScrollPane(invoiceTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Formatted Invoice"));
        add(scrollPane, BorderLayout.CENTER);

        addItemBtn.addActionListener(this::handleAddLineItem);
        resetBtn.addActionListener(e -> resetInvoice());

        setLocationRelativeTo(null);
    }

    private void handleAddLineItem(ActionEvent e) {
        try {
            if (invoice == null) {
                String addressText = fullAddressField.getText().trim();

                if (addressText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter the customer address first.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Address address = new Address(addressText);
                invoice = new Invoice("INVOICE", address);

                fullAddressField.setEditable(false);
            }

            String prodName = productNameField.getText().trim();
            double price = Double.parseDouble(unitPriceField.getText().trim());
            int qty = Integer.parseInt(quantityField.getText().trim());

            if (prodName.isEmpty() || qty <= 0 || price < 0) {
                JOptionPane.showMessageDialog(this, "Please enter valid product details.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Product product = new Product(prodName, price);
            LineItem item = new LineItem(product, qty);
            invoice.addLineItem(item);

            invoiceTextArea.setText(invoice.formatForDisplay());

            productNameField.setText("");
            unitPriceField.setText("");
            quantityField.setText("");
            productNameField.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price must be a valid number and Quantity must be an integer.", "Number Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetInvoice() {
        invoice = null;
        fullAddressField.setEditable(true);

        fullAddressField.setText("");
        productNameField.setText("");
        unitPriceField.setText("");
        quantityField.setText("");
        invoiceTextArea.setText("");
    }
}