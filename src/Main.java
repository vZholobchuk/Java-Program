import java.util.*;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        List<Drink> allDrinks = DrinkFileManager.readDrinksFromFiles(3);

        List<Drink> low = new ArrayList<>();
        List<Drink> mid = new ArrayList<>();
        List<Drink> high = new ArrayList<>();

        for (Drink d : allDrinks) {
            if (d.price <= 0.5) low.add(d);
            else if (d.price <= 5.0) mid.add(d);
            else high.add(d);
        }

        DrinkFileManager.writeDrinksToFile(low, "files/low_price_drinks.txt", chooseComparator("low_price_drinks.txt"));
        DrinkFileManager.writeDrinksToFile(mid, "files/mid_price_drinks.txt", chooseComparator("mid_price_drinks.txt"));
        DrinkFileManager.writeDrinksToFile(high, "files/high_price_drinks.txt", chooseComparator("high_price_drinks.txt"));

        System.out.println("Обробка завершена.");
    }



    public static Comparator<Drink> chooseComparator(String fileName) {
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

        if (selectedOption == null) return DrinkComparators.BY_NAME_PRICE_VOLUME;

        switch (selectedOption.charAt(0)) {
            case '1': return DrinkComparators.BY_NAME_PRICE_VOLUME;
            case '2': return DrinkComparators.BY_PRICE_NAME_VOLUME;
            case '3': return DrinkComparators.BY_VOLUME_PRICE_NAME;
            default: return DrinkComparators.BY_NAME_PRICE_VOLUME;
        }
    }

}
