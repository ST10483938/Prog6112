public class  Product implements IProduct {

    private String[] productNames;
    private int[] productTotals;
    private int months;

    public Product(String[] productNames, int[] productTotals, int months) {
        this.productNames = productNames;
        this.productTotals = productTotals;
        this.months = months;

    }

    @Override
    public int GetTotalIProductsSold() {
        int total = 0;
        for (int i = 0; i < productTotals.length; i++) {
            total += productTotals[i];
        }
        return total;
    }


    @Override
    public String GetMostProduct() {
        int bestIndex = 0;
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > productTotals[bestIndex]) {
                bestIndex = i;
            }
        }
        return productNames[bestIndex] + " (" + productTotals[bestIndex] + ")";

    }

    @Override
    public double GetAvaregeProductSold() {
        int count = productTotals.length * months;
        if (count == 0) {
            return 0;
        }
        return (double) GetTotalIProductsSold() / count;
    }
}
