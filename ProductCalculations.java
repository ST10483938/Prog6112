public class ProductCalculations implements IProductCalculations{

        private double price;

        public ProductCalculations(double price) {
            this.price = price;
        }

        // VAT is 15% of the price
        @Override
        public double GetVat() {
            return price * 0.15;
        }

        // Commission is 5% of the price
        @Override
        public double GetCommission() {
            return price * 0.05;
        }

        // Total = price + VAT + commission
        @Override
        public double GetTotal() {
            return price + GetVat() + GetCommission();
        }
    }

