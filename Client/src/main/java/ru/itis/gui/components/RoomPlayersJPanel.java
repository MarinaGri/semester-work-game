package ru.itis.gui.components;

import lombok.Data;
import ru.itis.general.entities.Player;
import ru.itis.gui.utils.GuiConst;
import ru.itis.gui.utils.Loader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import static ru.itis.gui.utils.GuiConst.DIMENSION;

@Data
public class RoomPlayersJPanel extends JPanel {
    private Border lineBorder;
    private Font font;

    public RoomPlayersJPanel(List<Player> players) {
        lineBorder = new LineBorder(new Color(169,159,98), 6);
        font = Loader.loadFont("default.otf").deriveFont(DIMENSION.height/30f);
        this.setBackground(GuiConst.COLOR);


        this.setLayout(new GridLayout(2, 3, DIMENSION.width/20, DIMENSION.height/20));
        if(players != null) {
            for (Player player : players) {
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setBackground(GuiConst.COLOR);
                panel.setBorder(lineBorder);

                JLabel nick = new JLabel(player.getNickname());
                nick.setFont(font.deriveFont(DIMENSION.height / 30f));
                nick.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(nick, BorderLayout.CENTER);

                JLabel status = new JLabel(player.getStatus() ? "READY!" : "WAITING..");
                status.setFont(font.deriveFont(DIMENSION.height / 35f));
                panel.add(status, BorderLayout.PAGE_END);

                this.add(panel);
            }
        }

        int size = players == null? 0: players.size();

        for (int i = 0; i < 6-size; i++){
            JPanel panel = new JPanel();
            panel.setBackground(GuiConst.COLOR);
            panel.setBorder(lineBorder);
            this.add(panel);
        }
    }
}
