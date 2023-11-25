
 class Product {
    private String productName;
    private double unitCost;
    private double gstPercentage;
    private int quantity;

    public Product(String name, double cost, double gst, int qty) {
        this.unitCost = cost;
        this.gstPercentage = gst;
        this.quantity = qty;
        this.productName = name;
    }

    public String getProductName() {
        return productName;
    }

    public double calculateTotalCost() {
        return unitCost * quantity * (1 + gstPercentage / 100);
    }

    public double calculateGstAmount() {
        return unitCost * quantity * (gstPercentage / 100);
    }

    public double calculateDiscountedTotalCost() {
        return (unitCost >= 500) ? calculateTotalCost() * 0.95 : calculateTotalCost();
    }
}

 class Main {
    public static Product findProductWithMaxGst(Product[] productList) {
        Product maxGstProduct = productList[0];
        double maxGstAmount = maxGstProduct.calculateGstAmount();

        for (int i = 1; i < productList.length; ++i) {
            double currentGstAmount = productList[i].calculateGstAmount();
            maxGstProduct = (currentGstAmount > maxGstAmount) ? productList[i] : maxGstProduct;
            maxGstAmount = (currentGstAmount > maxGstAmount) ? currentGstAmount : maxGstAmount;
        }

        return maxGstProduct;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("Leather Wallet", 1100, 18, 1),
            new Product("Umbrella", 900, 12, 4),
            new Product("Cigarette", 200, 28, 3),
            new Product("Honey", 100, 0, 2)
        };

        Product maxGstProduct = findProductWithMaxGst(products);

        double totalAmountToPay = 0.0;
        for (Product product : products) {
            totalAmountToPay += product.calculateDiscountedTotalCost();
        }

        System.out.println("Product with maximum GST amount: " + maxGstProduct.getProductName());
        System.out.println("Total amount to be paid: Rs. " + totalAmountToPay);
    }
}