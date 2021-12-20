package ru.itis.gui.components;

import ru.itis.gui.utils.Constants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RoomJPanel extends JPanel {
    private RoomPlayersJPanel playersJPanel;
    private Dimension dimension;

    public RoomJPanel() {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBorder(new LineBorder(Color.BLACK, 3));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBackground(Constants.COLOR);
        this.setLayout(new BorderLayout());

        this.add(new RoomPlayersJPanel());

        JButton button = new JButton("Присоединиться к комнате");
        JPanel panel = new JPanel();
        panel.setBackground(Constants.COLOR);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        Dimension bSize = new Dimension(dimension.width/5, dimension.height/20);
        button.setPreferredSize(bSize);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);

        panel.add(button);
        this.add(panel, BorderLayout.PAGE_END);


        Dimension thisSize = new Dimension(dimension.width/2, dimension.height - dimension.height/3);
        this.setMinimumSize(thisSize);
        this.setMaximumSize(thisSize);
    }
}
