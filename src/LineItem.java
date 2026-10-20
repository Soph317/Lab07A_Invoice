public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double calculateTotalPrice() {
        return product.getUnitPrice() * quantity;
    }

    public String formatForDisplay() {
        return String.format("%s %5d   $%8.2f",
                product.formatForDisplay(), quantity, calculateTotalPrice());
    }
}