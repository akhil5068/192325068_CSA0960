import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Robot extends Actor {
    private int startX = 48;
    private int startY = 50;
    private int moveSpeed = 4;
    private GreenfootImage robotimage1;
    private GreenfootImage robotimage2;
    private int lives;
    private int pizzaEaten;

    public Robot() {
        robotimage1 = new GreenfootImage("man01.png");
        robotimage2 = new GreenfootImage("man02.png");
        setImage(robotimage1); // Set the initial image
        lives = 3;             // Initialize lives
        pizzaEaten = 0;
        setLocation(startX, startY); // Set initial location
    }

    public void animate() {
        if (getImage() == robotimage1) {
            setImage(robotimage2);
        } else {
            setImage(robotimage1);
        }
    }

    public void robotMovement() {
        if (Greenfoot.isKeyDown("left")) {
            move(-1 * moveSpeed);
            animate();
        }
        if (Greenfoot.isKeyDown("right")) {
            move(moveSpeed);
            animate();
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - moveSpeed);
            animate();
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + moveSpeed);
            animate();
        }
    }



    public void removeLife() {
        lives--;
        if (lives <= 0) {
            Greenfoot.stop();
            System.out.println("Game Over!");
            // Optionally, display a game over screen or message here
        }
    }

    public void detectwallCollision() {
        if (isTouching(wall.class)) {
            setLocation(startX, startY);
            Greenfoot.playSound("hurt.wav");
            removeLife(); // Remove a life on collision
        }
    }

    public void detectblockCollision() {
        if (isTouching(block.class)) {
            setLocation(startX, startY);
            Greenfoot.playSound("hurt.wav");
            removeLife(); // Remove a life on collision
        }
    }
     public void eatPizza() {
        Actor pizza = getOneIntersectingObject(pizza.class);
        if (pizza != null) {
            getWorld().removeObject(pizza);
            pizzaEaten++; // Increment the pizzaEaten count
            Greenfoot.playSound("eat.wav"); // Play eating sound
        }
    }


    public void act() {
        robotMovement();
        detectwallCollision();
        detectblockCollision();
        eatPizza();// Other actions can be added here
    }
}