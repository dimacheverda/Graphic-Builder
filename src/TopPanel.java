import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel
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
