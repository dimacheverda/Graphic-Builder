import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel
{
    private Canvas canvas;
    private String equation = "";
    private String simpleGraphic = "";
    private boolean toUseEquation = false;
    private double ZOOM = 20.0;
//    private JLabel activityInfoLabel;
    Graphics2D g2d;

    public GraphicPanel()
    {
        canvas = new Canvas();
        setLayout(new FlowLayout());
//        activityInfoLabel = new JLabel();
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

//        activityInfoLabel.setText("Calculating");
//        activityInfoLabel.setVisible(true);
//        add(activityInfoLabel);

        g2d = (Graphics2D)graphics;
        graphics.translate(getWidth() / 2, getHeight() / 2);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        canvas.setupCanvas(graphics, getWidth(), getHeight());
        canvas.drawMarksWithZoom(graphics,ZOOM,getWidth(),getHeight());

        int lineWidth = 2;

        int ANTIALIASING_COEFFICIENT = 2;

        if (!toUseEquation && !simpleGraphic.equals("")) {
            for (int i = -getWidth()/2 * ANTIALIASING_COEFFICIENT; i < getWidth()/2 * ANTIALIASING_COEFFICIENT; i++) {
                double x1, y1, x2, y2;

                graphics.setColor(Color.MAGENTA);
                g2d.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                x1 = i / ZOOM / ANTIALIASING_COEFFICIENT;
                x2 = (i+1) / ZOOM / ANTIALIASING_COEFFICIENT;
                y1 = Brain.getYForX(x1, getSimpleGraphic());
                y2 = Brain.getYForX(x2, getSimpleGraphic());

                // check for asymptotes
                switch (simpleGraphic) {
                    case "tg" :
                    {
                        // if asymptote
                        if ((((Math.abs(x1%Math.PI) < Math.PI/2.0) && (Math.abs(x2%Math.PI) > Math.PI/2.0)) ||
                            ((Math.abs(x2%Math.PI) < Math.PI/2.0) && (Math.abs(x1%Math.PI) > Math.PI/2.0))) &&
                             (Math.abs(y1-y2) > 10)) {

                            // draw asymptote
                            Stroke s = new BasicStroke(1.0f,
                                    BasicStroke.CAP_SQUARE,
                                    BasicStroke.JOIN_MITER,
                                    10.0f,
                                    new float[] { 10.0f, 10.0f },
                                    0.0f);

                            graphics.setColor(Color.GRAY);
                            g2d.setStroke(s);
                            g2d.drawLine((int)(x1 * ZOOM), (getHeight()/2), (int)(x1 * ZOOM), (-getHeight()/2));

                            continue;
                        }
                        break;
                    }

                    case "ctg" :
                    {
                        // if asymptote
                        if ((((Math.abs(x1%Math.PI) > (Math.abs(x2%Math.PI))) && x2 > 0) ||
                            ((Math.abs(x1%Math.PI) < (Math.abs(x2%Math.PI))) && x2 < 0)) ||
                             (x1 == 0.0 || x2 == 0.0)) {

                            // draw asymptote
                            Stroke s = new BasicStroke(1.0f,
                                    BasicStroke.CAP_SQUARE,
                                    BasicStroke.JOIN_MITER,
                                    10.0f,
                                    new float[] { 10.0f, 10.0f },
                                    0.0f);

                            graphics.setColor(Color.GRAY);
                            g2d.setStroke(s);
                            g2d.drawLine((int)(x1 * ZOOM), (getHeight()/2), (int)(x1 * ZOOM), (-getHeight()/2));

                            continue;
                        }
                        break;
                    }
                }

                y1 = Brain.getYForX(x1, getSimpleGraphic());
                y2 = Brain.getYForX(x2, getSimpleGraphic());

                g2d.drawLine((int)(x1 * ZOOM), (int)(-y1 * ZOOM), (int)(x2 * ZOOM), (int)(-y2 * ZOOM));
            }
        } else {
            if (!getEquation().equals("") && toUseEquation) {

                graphics.setColor(Color.MAGENTA);
                g2d.setStroke(new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                double x1, y1, x2, y2;

                for (int i = -getWidth()/2; i < getWidth()/2; i++) {
                    try {
                        x1 = i / ZOOM;
                        x2 = (i+1) / ZOOM;
                        y1 = Brain.getYFromXForEquation(x1, equation);
                        y2 = Brain.getYFromXForEquation(x2, equation);
                        g2d.drawLine((int) (x1 * ZOOM), (int) (-y1 * ZOOM), (int) (x2 * ZOOM), (int) (-y2 * ZOOM));
                    } catch (ScriptException e) {
                        System.out.println("script exception");
                        i = getWidth()/2;
//                        activityInfoLabel.setText("Input Error");
                        toUseEquation = false;
                        simpleGraphic = "";
                    }
                }
            }
        }
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getEquation() {
        return equation;
    }

    public void setZOOM(Double ZOOM) {
        if (ZOOM >= 1.0) {
            this.ZOOM = ZOOM;
        } else this.ZOOM = 1.0;
    }

    public void setToUseEquation(boolean toUseEquation) {
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