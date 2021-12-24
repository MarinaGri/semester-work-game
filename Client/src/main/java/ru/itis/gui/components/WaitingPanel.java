package ru.itis.gui.components;

import ru.itis.connection.Connection;
import ru.itis.general.entities.Player;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.general.helpers.PlayerParser;
import ru.itis.gui.utils.ConnectionWrapper;
import ru.itis.gui.utils.Loader;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import static ru.itis.gui.utils.GuiConst.*;

public class WaitingPanel extends JPanelWithBackground{

    public WaitingPanel(Image image, boolean flag) {
        super(image, flag);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Font titleFont = Loader.loadFont("default.otf").deriveFont(DIMENSION.height/10f);

        this.add(Box.createVerticalStrut(DIMENSION.height/5));
        JLabel label = new JLabel("Drivel");
        label.setFont(titleFont);
        this.add(label, Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(DIMENSION.height/40));
        JLabel text = new JLabel("Please wait for the end of the round");
        text.setFont(titleFont);
        this.add(text, Component.CENTER_ALIGNMENT);

        sendResults();
    }

    public void sendResults() {
        Connection connection = ConnectionWrapper.getConnection();
        Player player = connection.getPlayer();
        ObjectParser<Player> parser = new PlayerParser();
        Message message = new Message(Constants.RESULT,
                parser.serializeObject(player));
        try {
            connection.sendMessage(message);
        } catch (IOException ex) {
            JOptionPane.showInternalMessageDialog(null, "Не удалось отправить результаты");
        }
    }
}