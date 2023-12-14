public class Cherry extends Food {
    private Position position;
    private String icon = "./assets/cherries.png";

    public Cherry(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public String getIcon() {
        return this.icon;
    }
}
