import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame
{
    public GraphicPanel graphicPanel;
    public TopPanel topPanel;
    public LeftPanel leftPanel;
    public BottomPanel bottomPanel;

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
        topPanel.setupTopPanel();
        topPanel.setBackground(Color.RED);
        add(topPanel, BorderLayout.NORTH);

        bottomPanel = new BottomPanel();
        bottomPanel.setupBottomPanel();
        bottomPanel.setBackground(Color.YELLOW);
        add(bottomPanel, BorderLayout.SOUTH);

        leftPanel = new LeftPanel();
        leftPanel.setupLeftPanel();
        leftPanel.setBackground(Color.GREEN);
        add(leftPanel, BorderLayout.WEST);

        setVisible(true);
        setLayout(new BorderLayout());
    }



    private class BottomPanel extends JPanel
    {
        public JSlider zoomSlider;

        public void setupBottomPanel()
        {
            int FPS_MIN = 10;
            int FPS_MAX = 400;
            int FPS_INIT = 40;
            zoomSlider = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
            zoomSlider.setPreferredSize(new Dimension(600, 40));
            zoomSlider.setMajorTickSpacing(40);
            zoomSlider.setMinorTickSpacing(10);
            zoomSlider.setPaintTicks(true);
            zoomSlider.setPaintLabels(true);
            SliderListener sliderListener = new SliderListener();
            zoomSlider.addChangeListener(sliderListener);
            add(zoomSlider);
        }

        private class SliderListener implements ChangeListener
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                JSlider source = (JSlider)e.getSource();
                if (graphicPanel.isToUseEquation()) {
                    if (!source.getValueIsAdjusting()) {
                        double _zoom = source.getValue();
                        graphicPanel.setZOOM(_zoom);
                        graphicPanel.repaint();
                    }
                } else {
                    double _zoom = source.getValue();
                    graphicPanel.setZOOM(_zoom);
                    graphicPanel.repaint();
                }
            }
        }
    }

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
//                System.out.println(topPanel.functionInputField.getText());
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
//            setLayout(new GridLayout(0,1));
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
