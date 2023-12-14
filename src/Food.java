import java.util.Random;

public abstract class Food {
    public abstract Position getPosition();

    public abstract String getIcon();

    public static Food randomFood(Position position) {
        int numFood = 3; // Represents the number of food items

        int randomNumber = new Random().nextInt(numFood);

        switch (randomNumber) {
            // TODO: Add two more food items!
            case 0:
                return new Cherry(position);
            case 1:
                return new Lemon(position);
            case 2:
                return new Apple(position);
            default:
                return null;
        }

    }
}
