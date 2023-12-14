public class Lemon extends Food {
    private Position position;
    private String icon = "./assets/lemon.png";

    public Lemon(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public String getIcon() {
        return this.icon;
    }
}
