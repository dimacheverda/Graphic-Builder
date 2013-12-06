import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel
{
    private Canvas canvas;

    public GraphicPanel()
    {
        canvas = new Canvas();
    }

    public void setZOOM(Double ZOOM) {
        this.ZOOM = ZOOM;
    }

    public Double ZOOM = 40.0;
    Graphics2D g2d;

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        g2d = (Graphics2D)graphics;
        graphics.translate(getWidth() / 2, getHeight() / 2);
        canvas.setupCanvas(graphics, getWidth(), getHeight());
        canvas.drawMarksWithZoom(graphics,ZOOM,getWidth(),getHeight());

        System.out.println("drawn\n\n");

        for (int i = -getWidth()/2; i < getWidth()/2; i++) {
            double x1, y1, x2, y2;

            x1 = i / ZOOM;
            x2 = (i+1) / ZOOM;
            y1 = Brain.getYForX(x1);
            y2 = Brain.getYForX(x2);

            g2d.drawLine((int)(x1 * ZOOM), (int)(-y1 * ZOOM), (int)(x2 * ZOOM), (int)(-y2 * ZOOM));
        }
    }
}
