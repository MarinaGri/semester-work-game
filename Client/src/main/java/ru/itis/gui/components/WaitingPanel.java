package ru.itis.gui.components;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.itis.connection.Connection;
import ru.itis.general.entities.Player;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.general.helpers.PlayerParser;
import ru.itis.gui.utils.ConnectionWrapper;
import ru.itis.gui.utils.GuiConst;
import ru.itis.gui.utils.Loader;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import static ru.itis.gui.utils.GuiConst.*;

@Getter
@Setter
public class WaitingPanel extends JPanelWithBackground{
    private Dimension dimension;
    private boolean isOver;

    public WaitingPanel(Image image, boolean flag) {
        super(image, flag);
        this.dimension = GuiConst.DIMENSION;
        this.setPreferredSize(dimension);
        this.setMinimumSize(new Dimension(dimension.width/4, dimension.height/4));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Font titleFont = Loader.loadFont("default.otf").deriveFont(DIMENSION.height/10f);
        Font titleFontForName = Loader.loadFont("name.otf").deriveFont(DIMENSION.height/10f);
        this.add(Box.createVerticalStrut(DIMENSION.height/5));
        JLabel label = new JLabel("Drivel");
        label.setForeground(Color.white);
        label.setFont(titleFontForName);
        this.add(label, Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(DIMENSION.height/40));
        JLabel text = new JLabel("Please wait for the end of the round");

        text.setFont(titleFont);
        text.setForeground(Color.white);
        this.add(text, Component.CENTER_ALIGNMENT);

    }

    public void sendResults() {
        Connection connection = ConnectionWrapper.getConnection();
        Player player = connection.getPlayer();
        ObjectParser<Player> parser = new PlayerParser();
        System.out.println(player.getResult());
        System.out.println(player.getTime());
        Message message = new Message(Constants.RESULT,
                parser.serializeObject(player));
        try {
            connection.sendMessage(message);
        } catch (IOException ex) {
            JOptionPane.showInternalMessageDialog(null, "Не удалось отправить результаты");
        }
    }
}
