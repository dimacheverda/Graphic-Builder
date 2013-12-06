import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainForm extends JFrame
{
    public GraphicPanel graphicPanel;
    public TopPanel topPanel;
    public LeftPanel leftPanel;
    public BottomPanel bottomPanel;

    private int ZOOM_INIT = 4;

    public void setupUI()
    {
        setTitle("Graphic Builder");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)dim.getWidth() - 100, (int)dim.getHeight() - 100);
//        setMinimumSize(new Dimension((int)dim.getWidth(), (int)dim.getHeight() - 26));
//        setMaximumSize(new Dimension((int) dim.getWidth(), (int) dim.getHeight() - 26));
        setLocation((dim.width/2) - (this.getWidth()/2), (dim.height/2) - (this.getHeight()/2));

        graphicPanel = new GraphicPanel();
        add(graphicPanel, BorderLayout.CENTER);

        topPanel = new TopPanel();
        topPanel.setupPanel();
        topPanel.setBackground(Color.RED);
        add(topPanel, BorderLayout.NORTH);

        bottomPanel = new BottomPanel();
        bottomPanel.setupBotttomPanel();
        bottomPanel.setBackground(Color.YELLOW);
        add(bottomPanel, BorderLayout.SOUTH);

        leftPanel = new LeftPanel();
        leftPanel.setBackground(Color.GREEN);
        add(leftPanel, BorderLayout.WEST);

        setVisible(true);
        setLayout(new BorderLayout());
    }

    private class SliderListener implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e)
        {
            JSlider source = (JSlider)e.getSource();
            double _zoom = source.getValue();
            graphicPanel.setZOOM(_zoom);
            graphicPanel.repaint();
        }
    }

    private class BottomPanel extends JPanel
    {
        public JSlider zoomSlider;

        public void setupBotttomPanel()
        {
            int FPS_MIN = 10;
            int FPS_MAX = 400;
            int FPS_INIT = 40;
            JSlider zoomSlider = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);

            zoomSlider.setPreferredSize(new Dimension(600, 40));
            zoomSlider.setMajorTickSpacing(40);
            zoomSlider.setMinorTickSpacing(10);
            zoomSlider.setPaintTicks(true);
            zoomSlider.setPaintLabels(true);
            SliderListener sliderListener = new SliderListener();
            zoomSlider.addChangeListener(sliderListener);
            add(zoomSlider);
        }
    }

    private class TopPanel extends JPanel
    {
        public JTextField functionInputField;
        public JButton buildButton;

        public void setupPanel()
        {
            setLayout(new FlowLayout());

            JLabel text = new JLabel("y=");
            add(text);

            functionInputField = new JTextField(25);
            add(functionInputField);

            buildButton = new JButton();
            buildButton.setText("Build");
            add(buildButton);
        }
    }
}
