import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// Klasa koszyka Cart
public class Cart {
    private Map<Product, Integer> products;

    // Konstruktor
    public Cart() {
        this.products = new HashMap<>();
    }

    // Metoda dodająca produkt do koszyka w określonej liczbie sztuk
    public void addProduct(Product product, int amount) {
        products.put(product, products.getOrDefault(product, 0) + amount);
    }

    // Metoda zwracająca wartość koszyka w danym roku i miesiącu
    public double getPrice(int year, int month) {
        double totalPrice = 0.0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int amount = entry.getValue();
            totalPrice += product.getPrice(year, month) * amount;
        }
        return totalPrice;
    }

    // Metoda zwracająca procentową wartość inflacji między dwoma wskazanymi miesiącami
    public double getInflation(int year1, int month1, int year2, int month2) {
        double price1 = getPrice(year1, month1);
        double price2 = getPrice(year2, month2);
        int months = (year2 - year1) * 12 + (month2 - month1);
        return (price2 - price1) / price1 * 100 / months * 12;
    }
}

