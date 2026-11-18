public class PrintProduct {
    private IProduct product;

    public PrintProduct(IProduct product) {
        this.product = product;
    }

    public void printHeader() {
        System.out.println();
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("***************************************");
    }

    public void  printTotalProductSales() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalIProductsSold());
    }

    public  void  printAverageProductSales() {
        System.out.printf("AVERAGE PRODUCT SALES: %.0f%n", product.GetAvaregeProductSold());
    }

    public void printMostPopularProduct() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostProduct());
    }
}
