public class Address {
    private String addressBlock;

    public Address(String addressBlock) {
        this.addressBlock = addressBlock;
    }

    public String formatAddressBlock() {
        return addressBlock;
    }
}