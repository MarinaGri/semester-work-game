package ru.itis.gui.components;

import ru.itis.general.entities.Player;
import ru.itis.gui.utils.GuiConst;
import ru.itis.gui.utils.Loader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class RoomPlayersJPanel extends JPanel {
    private Dimension dimension;
    private Border lineBorder;
    private Font font;

    public RoomPlayersJPanel(List<Player> players) {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        lineBorder = new LineBorder(new Color(169,159,98), 6);
        font = Loader.loadFont("default.otf").deriveFont(dimension.height/30f);
        this.setBackground(GuiConst.COLOR);


        this.setLayout(new GridLayout(2, 3, dimension.width/20, dimension.height/20));
        for (Player player: players){
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBackground(GuiConst.COLOR);
            panel.setBorder(lineBorder);

            JLabel nick = new JLabel(player.getNickname());
            nick.setFont(font.deriveFont(dimension.height/30f));
            panel.add(nick, BorderLayout.CENTER);

            JLabel status = new JLabel(player.isStatus()? "READY!": "WAITING..");
            status.setFont(font.deriveFont(dimension.height/35f));
            panel.add(status, BorderLayout.PAGE_END);

            this.add(panel);
        }

        for (int i = 0; i < 6-players.size(); i++){
            JPanel panel = new JPanel();
            panel.setBackground(GuiConst.COLOR);
            panel.setBorder(lineBorder);
            this.add(panel);
        }
    }
}
