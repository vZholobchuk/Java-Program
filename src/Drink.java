public class Drink {
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
