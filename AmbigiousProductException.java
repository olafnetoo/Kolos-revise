import java.util.List;

// Klasa wyjątku AmbigiousProductException
class AmbigiousProductException extends Exception {
    // Konstruktor przyjmujący listę nazw produktów
    public AmbigiousProductException(List<String> productNames) {
        super("Multiple products found: " + productNames);
    }
}


