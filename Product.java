import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
abstract class Product {
    public Product(String name) {
        this.name = name;
    }

    private String name;

    public Product() {

    }
    // Prywatna, statyczna lista obiektów klasy Product
    private static List<Product> products = new ArrayList<>();

    // Statyczna metoda clearProducts czyszcząca listę products
    public static void clearProducts() {
        products.clear();
    }

    // Statyczna metoda addProducts dodająca do listy products obiekty na podstawie plików z danymi
    public static void addProducts(Function<Path, Product> fromCsv, Path directoryPath) throws IOException {
        // Pobranie listy plików z podanego katalogu
        List<Path> files = Files.list(directoryPath).toList();

        // Przetworzenie każdego pliku z katalogu
        for (Path file : files) {
            // Utworzenie obiektu Product na podstawie pliku za pomocą funkcji fromCsv
            Product product = fromCsv.apply(file);

            // Dodanie produktu do listy
            products.add(product);
        }
    }
    public static Product getProducts(List<Product> products, String prefix) throws AmbigiousProductException, IndexOutOfBoundsException {
        // Lista nazw produktów pasujących do prefiksu
        List<String> matchingProductNames = products.stream()
                .map(Product::getName)
                .filter(name -> name.startsWith(prefix))
                .toList();

        // Sprawdzenie ilości pasujących produktów
        if (matchingProductNames.size() == 0) {
            throw new IndexOutOfBoundsException("No product found with prefix: " + prefix);
        } else if (matchingProductNames.size() == 1) {
            // Jeśli tylko jeden produkt pasuje, zwróć go
            return products.stream()
                    .filter(product -> product.getName().equals(matchingProductNames.get(0)))
                    .findFirst()
                    .orElse(null);
        } else {
            // Jeśli więcej niż jeden produkt pasuje, rzucić wyjątek AmbigiousProductException
            throw new AmbigiousProductException(matchingProductNames);
        }
    }

    // Abstrakcyjna metoda getName
    public abstract String getName();
    public abstract double getPrice(int year, int month) throws IndexOutOfBoundsException;
}
