import java.util.ArrayList;

public class Invoice {
    private String title;
    private Address customerAddress;
    private ArrayList<LineItem> lineItems;

    public Invoice(String title, Address customerAddress) {
        this.title = title;
        this.customerAddress = customerAddress;
        this.lineItems = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public double calculateTotalAmountDue() {
        double total = 0.0;
        for (LineItem item : lineItems) {
            total += item.calculateTotalPrice();
        }
        return total;
    }

    public String formatForDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====================================================\n");
        sb.append(String.format("%30s%n", title));
        sb.append("=====================================================\n");
        sb.append(customerAddress.formatAddressBlock()).append("\n");
        sb.append("=====================================================\n");
        sb.append(String.format("%-20s %5s   %9s   %9s%n", "Item", "Qty", "Price", "Total"));
        sb.append("-----------------------------------------------------\n");
        for (LineItem item : lineItems) {
            sb.append(item.formatForDisplay()).append("\n");
        }
        sb.append("-----------------------------------------------------\n");
        sb.append(String.format("%-37s $%8.2f%n", "AMOUNT DUE:", calculateTotalAmountDue()));
        sb.append("=====================================================\n");
        return sb.toString();
    }
}