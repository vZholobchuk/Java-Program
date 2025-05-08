import java.io.*;
import java.util.*;

public class DrinkFileManager {
    public static List<Drink> readDrinksFromFiles(int fileCount) {
        List<Drink> drinks = new ArrayList<>();

        for (int i = 1; i <= fileCount; i++) {
            try (BufferedReader br = new BufferedReader(new FileReader("files/drinks" + i + ".txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts.length == 4 && !parts[0].equals("N")) {
                        try {
                            drinks.add(new Drink(parts[1],
                                    Double.parseDouble(parts[2]),
                                    Double.parseDouble(parts[3])));
                        } catch (NumberFormatException e) {
                            System.err.println("Помилка парсингу рядка: " + line);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Помилка читання файлу: " + e.getMessage());
            }
        }
        return drinks;
    }

    public static void writeDrinksToFile(List<Drink> drinks, String fileName, Comparator<Drink> comparator) {
        drinks.sort(comparator);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Drink drink : drinks) {
                bw.write(drink.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Помилка запису до файлу: " + e.getMessage());
        }
    }
}
