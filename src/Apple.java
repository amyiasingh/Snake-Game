public class Apple extends Food {
    private Position position;
    private String icon = "./assets/apple.png";

    public Apple(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public String getIcon() {
        return this.icon;
    }
}
