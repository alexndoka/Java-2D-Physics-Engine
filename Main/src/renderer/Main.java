package renderer;

import physics.Body;
import physics.CircleShape;
import physics.Vector2D;
import physics.World;

import javax.swing.JFrame;
import java.awt.Color;
import java.util.Random; // generate random numbers

public class Main {
    
    public static void main(String[] args) {
        World world = new World(new Vector2D(0, 9.81 * 15)); 

        // floor
        Body floor = new Body();
        floor.shape = new CircleShape(1000);
        floor.position = new Vector2D(400, 1500); 
        floor.velocity = new Vector2D(0, 0);
        floor.force = new Vector2D(0, 0);
        floor.isStatic = true;
        floor.mass = 0;
        floor.invMass = 0; 
        floor.restitution = 0.2; 
        floor.color = Color.DARK_GRAY;
        world.addBody(floor);

        Random rand = new Random();
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.CYAN, Color.MAGENTA, Color.PINK};

        // spawns x number of balls
        for (int i = 0; i < 60; i++) {
            Body ball = new Body();
            
            // random radius between 10 and 35
            double radius = 10 + rand.nextDouble() * 25;
            ball.shape = new CircleShape(radius);
            
            // random x position across the screen
            double startX = 100 + rand.nextDouble() * 600;
            // random y position up
            double startY = -50 - rand.nextDouble() * 2000; 
            
            ball.position = new Vector2D(startX, startY);
            ball.velocity = new Vector2D(0, 0);
            ball.force = new Vector2D(0, 0);
            ball.isStatic = false;
            
            // make mass proportional to the size of the circle
            ball.mass = Math.PI * radius * radius / 100.0; 
            ball.invMass = 1.0 / ball.mass; 
            
            // randomize bounce
            ball.restitution = 0.3 + rand.nextDouble() * 0.6; 
            
            // random color from array
            ball.color = colors[rand.nextInt(colors.length)];
            
            world.addBody(ball);
        }

        // window
        JFrame frame = new JFrame("Java Physics Engine - Chaos Mode!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        
        Renderer renderer = new Renderer(world);
        frame.add(renderer);
        frame.setVisible(true);

        // main loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while (delta >= 1) {
                world.step(1.0 / 60.0);
                renderer.repaint();     
                delta--;
            }
        }
    }
}