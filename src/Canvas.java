import javax.swing.*;
import java.awt.*;

public class Canvas
{
    double spaceBetweenMarks;

    public void setupCanvas(Graphics graphics, int width, int height)
    {
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.drawLine(-width/2, 0, width/2, 0);   // X axis
        g2d.drawLine(0, -height/2, 0, height/2);  // Y axis

        g2d.drawLine(0-5, -height/2+5, 0, -height/2);   // Y arrow
        g2d.drawLine(0+5, -height/2+5, 0, -height/2);

        g2d.drawLine(width/2-5, 0-5, width/2, 0);  // X arrow
        g2d.drawLine(width/2-5, 0+5, width/2, 0);
    }

    public void drawMarksWithZoom(Graphics graphics, double ZOOM, int width, int height)
    {
        Graphics2D g2d = (Graphics2D)graphics;

        int markWidth = 3;
        int maximumMarkDistance = 80;
        int minimumMarkDistance = 40;

        spaceBetweenMarks = (int)ZOOM;

        if (ZOOM <= minimumMarkDistance) {
            while (spaceBetweenMarks < minimumMarkDistance) {
                spaceBetweenMarks *= 2;
            }
        }
        if (ZOOM >= maximumMarkDistance) {
            while (spaceBetweenMarks > maximumMarkDistance) {
                spaceBetweenMarks /= 2;
            }
        }

        // Marks for OX
        for (int i = 1; i < width/2/spaceBetweenMarks; i++)
        {
            int x = i*(int)spaceBetweenMarks;

            // DRAW GRID
            graphics.setColor(Color.LIGHT_GRAY);
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(x,height/2,x,-height/2);
            g2d.drawLine(-x,height/2,-x,-height/2);

            // MARKS nad NUMBERS
            graphics.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            // numbers
            double number = i/ZOOM*spaceBetweenMarks;
            number = number*100.0/100.0;
            String mark1 = String.format("%2.2f ",number);
            g2d.drawString(mark1, x, 0-markWidth-5);
            String mark2 = String.format("%2.2f ",-number);
            g2d.drawString(mark2, -x, 0 - markWidth - 5);

            // line
            g2d.drawLine(x, 0+markWidth,x ,0-markWidth);
            g2d.drawLine(-x, 0+markWidth,-x ,0-markWidth);
        }

        // Marks for OY
        for (int i = 1; i < height/2/spaceBetweenMarks; i++)
        {
            int y = i*(int)spaceBetweenMarks;

            // DRAW GRID
            graphics.setColor(Color.LIGHT_GRAY);
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(width/2,y,-width/2,y);
            g2d.drawLine(width/2,-y,-width/2,-y);

            // MARKS nad NUMBERS
            graphics.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            // numbers
            double number = i/ZOOM*spaceBetweenMarks;
            number = number*100.0/100.0;
            String mark1 = String.format("%2.2f ",number);
            g2d.drawString(mark1, 0-markWidth+10, y);
            String mark2 = String.format("%2.2f ",-number);
            g2d.drawString(mark2, 0- markWidth+10, -y);

            // line
            g2d.drawLine(0+markWidth, y,0-markWidth ,y);
            g2d.drawLine(0+markWidth, -y,0-markWidth ,-y);
        }
    }
}