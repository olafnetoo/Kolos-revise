import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class NonFoodProduct extends Product {
    public String getName() {
        return name;
    }

    private String name;
   private Double[] prices;

    private NonFoodProduct(String name, Double[] prices) {

        this.name = name;
        this.prices = prices;
    }

    public static NonFoodProduct fromCsv(Path path) {
        String name;
        Double[] prices;

        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine(); // odczytuję pierwszą linię i zapisuję ją jako nazwa
            scanner.nextLine();  // pomijam drugą linię z nagłówkiem tabeli
            prices = Arrays.stream(scanner.nextLine().split(";")) // odczytuję kolejną linię i dzielę ją na tablicę
                    .map(value -> value.replace(",",".")) // zamieniam polski znak ułamka dziesiętnego - przecinek na kropkę
                    .map(Double::valueOf) // konwertuję string na double
                    .toArray(Double[]::new); // dodaję je do nowo utworzonej tablicy

            scanner.close();

            return new NonFoodProduct(name, prices);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public NonFoodProduct(String name) {
        super(name);
    }

    // Nadpisanie metody getPrice
    @Override
    public double getPrice(int year, int month) throws IndexOutOfBoundsException {
        // Sprawdzenie czy rok i miesiąc mieszczą się w zakresie 01.2010 - 03.2022
        if (year < 2010 || year > 2022 || (year == 2022 && month > 3) || (year == 2010 && month < 1) || month < 1 || month > 12) {
            throw new IndexOutOfBoundsException("Podane dane miesiąca i roku wykraczają poza zakres.");
        }

        // Implementacja zwracająca przykładową cenę
        // Tutaj można wstawić rzeczywistą logikę zwracającą cenę produktu na dany rok i miesiąc
        return 10.0; // Przykładowa cena
    }
}
