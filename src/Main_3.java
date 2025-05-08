import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Main_3 {
    static class Drink {
        String name;
        double volume;
        double price;

        public Drink(String name, double volume, double price) {
            this.name = name;
            this.volume = volume;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + " " + volume + " " + price;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Drink> drinks = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            try (BufferedReader br = new BufferedReader(new FileReader("files/drinks" + i + ".txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts.length == 4 && !parts[0].equals("N")) {
                        drinks.add(new Drink(parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
                    }
                }
            }
        }

        List<Drink> lowPriceDrinks = new ArrayList<>();
        List<Drink> midPriceDrinks = new ArrayList<>();
        List<Drink> highPriceDrinks = new ArrayList<>();

        for (Drink drink : drinks) {
            if (drink.price <= 0.5) {
                lowPriceDrinks.add(drink);
            } else if (drink.price <= 5.0) {
                midPriceDrinks.add(drink);
            } else {
                highPriceDrinks.add(drink);
            }
        }

        writeSortedDrinks(lowPriceDrinks, "files/low_price_drinks.txt");
        writeSortedDrinks(midPriceDrinks, "files/mid_price_drinks.txt");
        writeSortedDrinks(highPriceDrinks, "files/high_price_drinks.txt");

        System.out.println("Обробка завершена.");
    }

    public static void writeSortedDrinks(List<Drink> drinks, String fileName) throws IOException {
        String[] options = {
                "1 - по Drinks, при рівності - по Price, при рівності - по Volume",
                "2 - по Price, при рівності - по Drinks, при рівності - по Volume",
                "3 - по Volume, при рівності - по Price, при рівності - по Drinks"
        };

        String selectedOption = (String) JOptionPane.showInputDialog(
                null,
                "Оберіть тип сортування для " + fileName + ":",
                "Вибір сортування",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        int sortOption = 1;
        if (selectedOption != null) {
            sortOption = Integer.parseInt(selectedOption.substring(0, 1));
        }

        Comparator<Drink> comparator;
        switch (sortOption) {
            case 1:
                comparator = Comparator.comparing((Drink d) -> d.name)
                        .thenComparing(d -> d.price)
                        .thenComparing(d -> d.volume);
                break;
            case 2:
                comparator = Comparator.comparing((Drink d) -> d.price)
                        .thenComparing(d -> d.name)
                        .thenComparing(d -> d.volume);
                break;
            case 3:
                comparator = Comparator.comparing((Drink d) -> d.volume)
                        .thenComparing(d -> d.price)
                        .thenComparing(d -> d.name);
                break;
            default:
                System.out.println("Неправильний вибір. Використовується сортування за замовчуванням (по Drinks).");
                comparator = Comparator.comparing((Drink d) -> d.name)
                        .thenComparing(d -> d.price)
                        .thenComparing(d -> d.volume);
                break;
        }

        drinks.sort(comparator);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Drink drink : drinks) {
                bw.write(drink.toString());
                bw.newLine();
            }
        }
    }
}
