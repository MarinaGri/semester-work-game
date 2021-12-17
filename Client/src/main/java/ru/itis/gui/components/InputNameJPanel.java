package ru.itis.gui.components;

import lombok.Data;
import ru.itis.gui.listeners.InputNameListener;
import ru.itis.gui.utils.Constants;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

@Data
public class InputNameJPanel extends JPanel {

    public InputNameJPanel() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Border lineBorder = new LineBorder(Color.BLACK, 3);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Color color = new Color(220,193,138);
        this.setBackground(color);

        Font titleFont = new Font("font", Font.BOLD, dimension.height/20);

        this.add(Box.createVerticalStrut(dimension.height/20));
        JLabel label = new JLabel(Constants.GAME_NAME);
        label.setFont(titleFont);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(label);

        this.add(Box.createVerticalStrut(dimension.height/10));

        JTextField textField = new HintTextField("Nickname");
        Dimension tfSize = new Dimension(dimension.width/4, dimension.height/20);
        textField.setMaximumSize(tfSize);
        textField.setMinimumSize(tfSize);
        textField.setBackground(color);
        textField.setBorder(lineBorder);
        textField.addKeyListener(new InputNameListener(textField));
        Font hintFont = new Font("font", Font.PLAIN, dimension.height/40);
        textField.setFont(hintFont);
        this.add(textField, Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(dimension.height/10));

        JButton button = new JButton("Ввести");
        button.setBackground(color);
        Dimension bSize = new Dimension(dimension.width/10, dimension.height/25);
        button.setFont(hintFont);
        button.setMinimumSize(bSize);
        button.setMaximumSize(bSize);
        button.setBorder(lineBorder);
        button.addActionListener(new InputNameListener(textField));
        this.add(button);

        this.setBorder(lineBorder);
        Dimension thisSize = new Dimension(dimension.width/2, dimension.height/2);
        this.setMinimumSize(thisSize);
        this.setMaximumSize(thisSize);
    }
}
