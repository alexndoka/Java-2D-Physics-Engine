package renderer;

import physics.Body;
import physics.CircleShape;
import physics.RectShape; 
import physics.Shape;
import physics.World;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Renderer extends JPanel {

    private World world;

    public Renderer(World world) {
        this.world = world;
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Body body : world.bodies) {
            g2d.setColor(body.color != null ? body.color : Color.WHITE);
            
            if (body.shape.getType() == Shape.Type.CIRCLE) {
                CircleShape circle = (CircleShape) body.shape;

                int x = (int) (body.position.x - circle.radius);
                int y = (int) (body.position.y - circle.radius);
                int diameter = (int) (circle.radius * 2);
                
                g2d.fillOval(x, y, diameter, diameter);
            } 
            else if (body.shape.getType() == Shape.Type.RECTANGLE) {
                RectShape rect = (RectShape) body.shape;
                int x = (int) (body.position.x - rect.width / 2);
                int y = (int) (body.position.y - rect.height / 2);
                
                g2d.fillRect(x, y, (int) rect.width, (int) rect.height);
            }
        }
    }
}