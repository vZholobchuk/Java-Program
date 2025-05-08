import java.util.*;
public class DrinkComparators {
    public static final Comparator<Drink> BY_NAME_PRICE_VOLUME =
            Comparator.comparing((Drink d) -> d.name)
                    .thenComparing(d -> d.price)
                    .thenComparing(d -> d.volume);

    public static final Comparator<Drink> BY_PRICE_NAME_VOLUME =
            Comparator.comparing((Drink d) -> d.price)
                    .thenComparing(d -> d.name)
                    .thenComparing(d -> d.volume);

    public static final Comparator<Drink> BY_VOLUME_PRICE_NAME =
            Comparator.comparing((Drink d) -> d.volume)
                    .thenComparing(d -> d.price)
                    .thenComparing(d -> d.name);
}
