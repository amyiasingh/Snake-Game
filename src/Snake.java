import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;

//import javax.sound.midi.SoundbankResource;

public class Snake {
    private SnakeSegment head;
    private Direction direction = Direction.RIGHT; // Default direction
    private boolean isPaused = false;

    public Snake() {
        // You may change this code for extra credit (implement some fancy stuff!)
        Position startingPosition = new Position(10, 10); // Feel free to make this more random
        head = new SnakeSegment(startingPosition);
    }

    // TODO: The snake should grow whenever it "eats" some food
    public void grow() {
        grow = true;
    }

    // TODO: Remove the last node in the snake's body, leave head untouched
    private void removeTail() {
        SnakeSegment current = head;
        SnakeSegment previous = null;

        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }

        // Set the second-to-last node as the new tail
        if (previous != null) {
            previous.setNext(null);
        }
    }

    // Returns true if the snake is colliding with itself
    public boolean isColliding() {
        if (isInSnake(head.getPosition())) {
            return true;
        }
        return false;
    }

    // TODO: Return false if the specified position is inside the BODY of the snake
    public boolean isInSnake(Position position) {
        SnakeSegment curr = getBody();
        while (curr != null) {
            Position pos = curr.getPosition();
            if (position.equals(pos)) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    // TODO: Set the direction the snake will move in
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // TODO: Get the length of the snake either by counter or traversal
    public int getLength() {
        int count = 0;
        SnakeSegment current = head;

        while (current != null) {
            count++;
            current = current.getNext();
        }

        return count;
        // iterates through the linked list and returns the total count of each segment
        // which is the total length of the snake.
    }

    // Moves the snake by one in the next direction
    // TODO: Implement the move method
    private boolean grow = false;

    public void move() {
        // HINT: You may add and remove nodes here
        Position newPosition = head.getPosition().add(direction.deltaPosition());
        SnakeSegment newHead = new SnakeSegment(newPosition);
        newHead.setNext(head);

        // ...
        head = newHead;

        if (!grow) {
            removeTail();
        } else {
            grow = false;
        }
    }

    // TODO: Return the head node
    public SnakeSegment getHead() {
        return head;
        // returns the head which is the first segment
    }

    // TODO: Return the start of the body (NOT the head!)
    public SnakeSegment getBody() {
        if (head != null) {
            return head.getNext();
            // returns the segment that is after the head.
        } else {
            return null; // snake is only head?
        }
    }

    public void pause() {
        // OPTIONAL: Pause/unpause the snake
        isPaused = !isPaused;
        long a = 3000;
        if (isPaused) {
            // Logic to pause the snake
            System.out.println("Snake paused");
            try {
              Thread.sleep(a);  
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        } else {
            // Logic to unpause the snake
            System.out.println("Snake unpaused");
        }
    }

    public Direction getMove(ArrayList<Food> food) {
        // OPTIONAL: Implement an algorithm that moves the food for us!
        return null;
    }

}

class SnakeSegment {
    private Position position;
    private SnakeSegment next;

    public SnakeSegment(Position pos) {
        this.position = pos;
    }

    public Position getPosition() {
        return this.position;
    }

    public SnakeSegment getNext() {
        return this.next;
    }

    public void setNext(SnakeSegment next) {
        this.next = next;
    }
}
