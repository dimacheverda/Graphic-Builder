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
            // numbers
            double number = i/ZOOM*spaceBetweenMarks;
            number = number*100.0/100.0;
            String mark1 = String.format("%2.2f ",number);
            g2d.drawString(mark1, i*(int)spaceBetweenMarks, 0-markWidth-5);

            // line
            g2d.drawLine(i*(int)spaceBetweenMarks, 0+markWidth,i*(int)spaceBetweenMarks ,0-markWidth);

            // numbers
            String mark2 = String.format("%2.2f ",-number);
            g2d.drawString(mark2, -i * (int)spaceBetweenMarks, 0 - markWidth - 5);

            // line
            g2d.drawLine(-i*(int)spaceBetweenMarks, 0+markWidth,-i*(int)spaceBetweenMarks ,0-markWidth);
        }

        // Marks for OY
        for (int i = 1; i < height/2/spaceBetweenMarks; i++)
        {
            // numbers
            double number = i/ZOOM*spaceBetweenMarks;
            number = number*100.0/100.0;
            String mark1 = String.format("%2.2f ",number);
            g2d.drawString(mark1,0-markWidth+10, i*(int)spaceBetweenMarks);

            // line
            g2d.drawLine(0+markWidth, i*(int)spaceBetweenMarks,0-markWidth ,i*(int)spaceBetweenMarks);

            // numbers
            String mark2 = String.format("%2.2f ",-number);
            g2d.drawString(mark2, 0 - markWidth + 10, -i * (int)spaceBetweenMarks);

            // line
            g2d.drawLine(0+markWidth, -i*(int)spaceBetweenMarks,0-markWidth ,-i*(int)spaceBetweenMarks);
        }
    }
}