import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
public class FoodProduct extends Product{

    public FoodProduct(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return null;
    }

    // Statyczna metoda wytwórcza fromCsv
    public static FoodProduct fromCsv(Path path) throws IOException {
        // Wczytanie danych z pliku CSV i utworzenie produktu
        // Ta część kodu należy zaimplementować zgodnie z formatem pliku CSV i danymi
        // Tutaj tylko tworzymy obiekt dla celów demonstracyjnych
        return new FoodProduct("Food Product");
    }

    // Metoda getPrice dla konkretnego województwa
    public double getPrice(int year, int month, String province) throws IndexOutOfBoundsException {
        // Sprawdzenie czy rok i miesiąc mieszczą się w zakresie 01.2010 - 03.2022
        if (year < 2010 || year > 2022 || (year == 2022 && month > 3) || (year == 2010 && month < 1) || month < 1 || month > 12) {
            throw new IndexOutOfBoundsException("Podane dane miesiąca i roku wykraczają poza zakres.");
        }

        // Implementacja zwracająca przykładową cenę dla danego województwa
        // Tutaj można wstawić rzeczywistą logikę zwracającą cenę produktu na dany rok, miesiąc i województwo
        // Wartości województw do przykładu
        List<String> provinces = List.of("MAZOWIECKIE", "MAŁOPOLSKIE", "WIELKOPOLSKIE");
        if (!provinces.contains(province.toUpperCase())) {
            throw new IndexOutOfBoundsException("Podane województwo jest nieprawidłowe.");
        }
        return 15.0; // Przykładowa cena
    }

    // Nadpisanie metody getPrice dla średniej arytmetycznej cen z wszystkich województw
    @Override
    public double getPrice(int year, int month) throws IndexOutOfBoundsException {
        // Implementacja obliczająca średnią arytmetyczną cen z wszystkich województw
        // Ta część kodu należy zaimplementować w zależności od danych i logiki
        // Tutaj tylko zwracamy przykładową średnią
        return 12.0; // Przykładowa średnia cena
    }

}
