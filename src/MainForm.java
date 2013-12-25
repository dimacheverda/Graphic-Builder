import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class MainForm extends JFrame
{
    public GraphicPanel graphicPanel;
    public TopPanel topPanel;
    public LeftPanel leftPanel;
    public JPanel mainPanel;

    public MainForm() throws HeadlessException
    {
        setTitle("Graphic Builder");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)dim.getWidth() - 400, (int)dim.getHeight() - 200);
        setLocation((dim.width/2) - (this.getWidth()/2), (dim.height/2) - (this.getHeight()/2));

        graphicPanel = new GraphicPanel();
        MouseDragListener mouseDragListener = new MouseDragListener();
        graphicPanel.addMouseListener(mouseDragListener);

        topPanel = new TopPanel();
        topPanel.setupTopPanel();
        topPanel.setBackground(Color.RED);

        leftPanel = new LeftPanel();
        leftPanel.setupLeftPanel();
        leftPanel.setBackground(Color.GREEN);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(graphicPanel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);

        WheelListener wheelListener = new WheelListener();
        addMouseWheelListener(wheelListener);

        add(mainPanel);

        setVisible(true);
    }

    private class WheelListener implements MouseWheelListener
    {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e)
        {
            double current = graphicPanel.getZOOM();
            current += e.getUnitsToScroll() * 2;
//            if (e.getUnitsToScroll() > 0) {
//                current = current + 2;
//            } else if (e.getUnitsToScroll() < 0) {
//                current = current - 2;
//            }
            graphicPanel.setZOOM(current);
        }
    }

    private class MouseDragListener implements MouseListener
    {
        boolean isDragging = false;
        int beginX, beginY, endX, endY;

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            isDragging = true;
            beginX = e.getX();
            beginY = e.getY();

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            isDragging = false;
            endX = e.getX();
            endY = e.getY();
            graphicPanel.setTranslationX((endX - beginX) + graphicPanel.getTranslationX());
            graphicPanel.setTranslationY((endY - beginY) + graphicPanel.getTranslationY());
            graphicPanel.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

//    private class BottomPanel extends JPanel
//    {
//        public JSlider zoomSlider;
//
//        public void setupBottomPanel()
//        {
//            int FPS_MIN = 1;
//            int FPS_MAX = 300;
//            int FPS_INIT = 20;
//            zoomSlider = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
//            zoomSlider.setPreferredSize(new Dimension(800, 40));
//            SliderListener sliderListener = new SliderListener();
//            zoomSlider.addChangeListener(sliderListener);
//            add(zoomSlider);
//        }
//
//        private class SliderListener implements ChangeListener
//        {
//            @Override
//            public void stateChanged(ChangeEvent e)
//            {
//                JSlider source = (JSlider)e.getSource();
//                double _zoom = source.getValue();
//                if (graphicPanel.isToUseEquation()) {
//                    if (!source.getValueIsAdjusting()) {
//                        graphicPanel.setZOOM(_zoom);
//                        graphicPanel.repaint();
//                    }
//                } else {
//                    graphicPanel.setZOOM(_zoom);
//                    graphicPanel.repaint();
//                }
//            }
//        }
//    }

    private class TopPanel extends JPanel implements ActionListener
    {
        public JTextField functionInputField;
        public JButton buildButton;

        public void setupTopPanel()
        {
            setLayout(new FlowLayout());

            JLabel text = new JLabel("y=");
            add(text);

            functionInputField = new JTextField(25);
            add(functionInputField);

            buildButton = new JButton();
            buildButton.setText("Build");
            buildButton.addActionListener(this);
            add(buildButton);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (!topPanel.functionInputField.getText().equals("")) {
                graphicPanel.setEquation(topPanel.functionInputField.getText());
                graphicPanel.setToUseEquation(true);
                graphicPanel.repaint();
            }
        }
    }

    private class LeftPanel extends JPanel implements ActionListener
    {
        public void setupLeftPanel()
        {
            setPreferredSize(new Dimension(80, 100));
            setSize(80, 400);
            setLayout(new FlowLayout());

            String[] buttonTitles = {"sin", "cos", "tg", "ctg"};
            for (int i = 0; i < buttonTitles.length; i++) {
                JButton button = new JButton(buttonTitles[i]);
                button.addActionListener(this);
                this.add(button);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JButton sender = (JButton)e.getSource();
            graphicPanel.setSimpleGraphic(sender.getText());
            graphicPanel.setToUseEquation(false);
            graphicPanel.repaint();
        }
    }

}
