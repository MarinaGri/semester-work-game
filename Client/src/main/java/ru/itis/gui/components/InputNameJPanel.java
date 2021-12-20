package ru.itis.gui.components;

import lombok.Data;
import ru.itis.gui.listeners.InputNameListener;
import ru.itis.gui.utils.GuiConst;
import ru.itis.gui.utils.Loader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

@Data
public class InputNameJPanel extends JPanel {
    private Dimension dimension;
    private JButton button;

    public InputNameJPanel() {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Border lineBorder = new LineBorder(Color.BLACK, 3);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBackground(GuiConst.COLOR);

        Font titleFont = Loader.loadFont("name.otf").deriveFont(dimension.height/10f);

        this.add(Box.createVerticalStrut(dimension.height/20));
        JLabel label = new JLabel("Drivel");
        label.setFont(titleFont);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(label);

        this.add(Box.createVerticalStrut(dimension.height/10));

        JTextField textField = new HintTextField("Nickname");
        Dimension tfSize = new Dimension(dimension.width/4, dimension.height/20);
        textField.setMaximumSize(tfSize);
        textField.setMinimumSize(tfSize);
        textField.setBackground(GuiConst.COLOR);
        textField.setBorder(lineBorder);
        textField.addKeyListener(new InputNameListener(textField));
        Font hintFont = Loader.loadFont("default.otf").deriveFont(dimension.height/30f);
        textField.setFont(hintFont);
        this.add(textField, Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(dimension.height/20));
        Dimension size = new Dimension(dimension.width/10, dimension.height/20);
        JLabel fake = new JLabel();
        fake.setMinimumSize(size);
        fake.setMaximumSize(size);
        this.add(fake, Component.CENTER_ALIGNMENT);

        button = new JButton("Ввести");
        button.setBackground(GuiConst.COLOR);
        Dimension bSize = new Dimension(dimension.width/10, dimension.height/25);
        button.setFont(hintFont);
        button.setMinimumSize(bSize);
        button.setMaximumSize(bSize);
        button.setBorder(lineBorder);
        button.addActionListener(new InputNameListener(textField));
        this.add(button);

        this.setBorder(lineBorder);
        Dimension thisSize = new Dimension(dimension.width/2, dimension.height - dimension.height/3);
        this.setMinimumSize(thisSize);
        this.setMaximumSize(thisSize);
    }

    public void showInvalidNameTip(){
        JLabel jLabel = new JLabel(GuiConst.INVALID_NICKNAME);
        Dimension size = new Dimension(dimension.width/10, dimension.height/20);
        jLabel.setMinimumSize(size);
        jLabel.setMaximumSize(size);
        jLabel.setForeground(Color.RED);
        this.remove(4);
        this.add(jLabel, Component.RIGHT_ALIGNMENT, 4);

        this.validate();
        this.repaint();
    }
}
