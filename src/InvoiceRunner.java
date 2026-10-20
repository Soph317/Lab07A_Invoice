import javax.swing.SwingUtilities;

public class InvoiceRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InvoiceFrame frame = new InvoiceFrame();
            frame.setVisible(true);
        });
    }
}