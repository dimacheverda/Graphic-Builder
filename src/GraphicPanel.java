import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel
{
    private Canvas canvas;



    private String equation = "";
    private String simpleGraphic = "";
    private boolean toUseEquation = false;


    public GraphicPanel()
    {
        canvas = new Canvas();
    }

    public void setEquation(String equation)
    {
        this.equation = equation;
    }
    public String getEquation()
    {
        return equation;
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

        graphics.setColor(Color.GRAY);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        canvas.setupCanvas(graphics, getWidth(), getHeight());
        canvas.drawMarksWithZoom(graphics,ZOOM,getWidth(),getHeight());


        int lineWidth = 2;
        graphics.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (!toUseEquation && !simpleGraphic.equals("")) {
            for (int i = -getWidth()/2; i < getWidth()/2; i++) {
                double x1, y1, x2, y2;

                x1 = i / ZOOM;
                x2 = (i+1) / ZOOM;
                y1 = Brain.getYForX(x1, getSimpleGraphic());
                y2 = Brain.getYForX(x2, getSimpleGraphic());

                g2d.drawLine((int)(x1 * ZOOM), (int)(-y1 * ZOOM), (int)(x2 * ZOOM), (int)(-y2 * ZOOM));
            }
            System.out.println("Simple Graphic");
        } else  if (!getEquation().equals("")) {
            for (int i = -getWidth()/2; i < getWidth()/2; i++) {
                double x1, y1, x2, y2;

                x1 = i / ZOOM;
                x2 = (i+1) / ZOOM;
                y1 = Brain.getYFromXForEquatation(x1, equation);
                y2 = Brain.getYFromXForEquatation(x2, equation);

                g2d.drawLine((int) (x1 * ZOOM), (int) (-y1 * ZOOM), (int) (x2 * ZOOM), (int) (-y2 * ZOOM));
            }
            System.out.println("Equation used");
        }
    }

    public void setToUseEquation(boolean toUseEquation)
    {
        this.toUseEquation = toUseEquation;
    }

    public boolean isToUseEquation() {
        return toUseEquation;
    }

    public void setSimpleGraphic(String simpleGraphic) {
        this.simpleGraphic = simpleGraphic;
    }

    public String getSimpleGraphic() {

        return simpleGraphic;
    }
}
