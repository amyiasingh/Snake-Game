import java.util.*;
import processing.core.*;

public class SnakeGame extends PApplet {
    public static final int SQUARE_SIZE = 20;
    public static final int NUM_FOOD = 3;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SPEED = 10;

    public Snake snake = new Snake();

    public HashMap<String, PImage> assets = new HashMap<>();

    public ArrayList<Food> foodList = new ArrayList<>();

    private PImage headImage;

    public boolean gameOver = false;

    public static void main(String[] args) {
        String[] appletArgs = new String[] { "SnakeGame" };
        PApplet.main(appletArgs);
    }

    public void settings() {
        smooth(0);
        size(800, 600);
    }

    public void setup() {
        // Change the framerate for a harder/easier game
        frameRate(SPEED);

        // Load all the assets that are used often (just the snake parts, not the food!)
        assets.put("head_up", loadAsset("./assets/head_up.png"));
        assets.put("head_down", loadAsset("./assets/head_down.png"));
        assets.put("head_left", loadAsset("./assets/head_left.png"));
        assets.put("head_right", loadAsset("./assets/head_right.png"));
        assets.put("body", loadAsset("./assets/body.png"));

        // The snake always moves to the right by default
        // Hence, the right head is set as starting head
        headImage = assets.get("head_right");

        // Grow the snake so we do not start at 0
        snake.grow();

        // Create food at random positions
        for (int i = 0; i < NUM_FOOD; i++) {
            foodList.add(Food.randomFood(Position.randomPosition()));
        }
    }

    public void draw() {
        background(0);
        if (gameOver) {
            gameOverScreen();
            return;
        }

        // Move once a frame
        Direction ai_move = snake.getMove(foodList);
        if (ai_move != null)
            snake.setDirection(ai_move);
        snake.move();

        // Draw food first so the snake covers it up
        drawFood();

        // Draw the snake
        drawBody();
        image(headImage, snake.getHead().getPosition().x * SQUARE_SIZE, snake.getHead().getPosition().y * SQUARE_SIZE,
                SQUARE_SIZE, SQUARE_SIZE);

        checkFoodCollision();
        checkGameOver();
    }

    // Check if the snake has eaten some food
    public void checkFoodCollision() {
        Food found = null;
        for (Food f : foodList) {
            if (snake.getHead().getPosition().equals(f.getPosition())) {
                snake.grow();
                found = f;
                break;
            }
        }
        if (found != null) {
            foodList.remove(found);
            Position randPos = Position.randomPosition();
            while (snake.isInSnake(randPos))
                randPos = Position.randomPosition();

            foodList.add(Food.randomFood(randPos));
        }
    }

    public void checkGameOver() {
        if (snake.isColliding())
            gameOver = true;
    }

    public void gameOverScreen() {
        textSize(64);
        text("Your Score: " + snake.getLength(), WIDTH / 4, HEIGHT / 3);
        textSize(32);
        text("space to restart....", WIDTH / 3.0f, HEIGHT * 0.8f);
    }

    public void restart() {
        gameOver = false;
        snake = new Snake();
        snake.setDirection(Direction.RIGHT);
        snake.grow();
    }

    public void drawFood() {
        // Save the food icons in the assets so we do not have to keep importing it
        for (Food food : foodList) {
            if (!assets.containsKey(food.getIcon())) {
                assets.put(food.getIcon(), loadAsset(food.getIcon()));
            }

            image(assets.get(food.getIcon()), food.getPosition().x * SQUARE_SIZE,
                    food.getPosition().y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    public void drawSegment(SnakeSegment segment) {
        image(assets.get("body"), segment.getPosition().x * SQUARE_SIZE,
                segment.getPosition().y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    // TODO: Draw the body
    public void drawBody() {
        // HINT: Make use of drawSegment()!
        SnakeSegment current = snake.getBody();

        while(current != null){
            drawSegment(current);
            current = current.getNext();
            // draws each segments by iterating through current 
        }

    }

    public PImage loadAsset(String path) {
        PImage image = loadImage(path);
        return image;
    }

    // TODO: Set the direction when a key is pressed
    public void keyPressed() {
        // keycode: There is an enum for all special keyboard keys
        // see
        // https://processing.github.io/processing-javadocs/core/processing/core/PApplet.html#keyCode
        // This enum is accessible in our current scope, so we don't
        // even have to use the '.' operator to access them
        // (don't compare 'keyCode' to 'Direction', it won't work!)
        switch (keyCode) {
            case UP:
                snake.setDirection(Direction.UP);
                headImage = assets.get("head_up");
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                headImage = assets.get("head_down");
                break;
            case LEFT:
                snake.setDirection(Direction.LEFT);
                headImage = assets.get("head_left");
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                headImage = assets.get("head_right");
                break;
            case TAB: 
                snake.pause();
                break;
            default:
                break;
        }
        
        if (key == ' ' && gameOver) {
            restart();
        } else if (key == ' ') {
            snake.pause();
        }
    }
}
