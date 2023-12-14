public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public Position deltaPosition() {
        switch (this) {
            case UP:
                return new Position(0, -1);
            case DOWN:
                return new Position(0, 1);
            case LEFT:
                return new Position(-1, 0);
            case RIGHT:
                return new Position(1, 0);
            default:
                return new Position(0, 0);
        }
    }
}
