package ru.itis.gui.components;

import lombok.Getter;
import ru.itis.connection.Connection;
import ru.itis.general.entities.Player;
import ru.itis.general.helpers.MessageParser;
import ru.itis.general.helpers.TextParser;
import ru.itis.gui.utils.ConnectionWrapper;
import ru.itis.gui.utils.GuiConst;
import ru.itis.gui.utils.Loader;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RoomJPanel extends JPanel {
    private RoomPlayersJPanel playersJPanel;
    private Dimension dimension;
    private List<Player> players;
    private JPanel bottom;
    private Font font;

    public RoomJPanel() {
        playersJPanel = new RoomPlayersJPanel(new ArrayList<>());
        dimension = Toolkit.getDefaultToolkit().getScreenSize();

        setEmptyRoom();

        Dimension thisSize = new Dimension(dimension.width/2, dimension.height - dimension.height/3);
        this.setMinimumSize(thisSize);
        this.setMaximumSize(thisSize);
    }

    public void changePlayers(List<Player> playerList){
        this.players = playerList;
        this.removeAll();
        this.add(new RoomPlayersJPanel(playerList), BorderLayout.CENTER);

        if(players != null) {
            for (Player player : playerList) {
                System.out.println(player);
            }
        }
        JButton button = new JButton("Выйти из комнаты");
        button.addActionListener(e -> {
            this.removeAll();
            setEmptyRoom();

            Connection connection = ConnectionWrapper.getConnection();
            Player player = connection.getPlayer();
            MessageParser<String> parser = new TextParser();
            Message message = new Message(Constants.EXIT_ROOM,
                    parser.serializeMessage(String.valueOf(player.getId())));
            try {
                connection.sendMessage(message);
            } catch (IOException ioException) {
                JOptionPane.showInternalMessageDialog(null, "Не удалось совершить действие");
            }

            this.validate();
            this.repaint();
        });

        bottom.removeAll();
        button.setFont(font);

        Dimension bSize = new Dimension(dimension.width/5, dimension.height/20);
        button.setPreferredSize(bSize);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);

        bottom.add(button);
        this.add(bottom, BorderLayout.PAGE_END);

        this.validate();
        this.repaint();
    }

    public void addReadyButton(){
        JButton button = new JButton("Ready!");
        Dimension bSize = new Dimension(dimension.width/7, dimension.height/20);
        button.setPreferredSize(bSize);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFont(font);

        button.addActionListener(e -> {
            Connection connection = ConnectionWrapper.getConnection();
            Player p = connection.getPlayer();
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).getId().equals(p.getId())){
                    players.get(i).setStatus(true);
                }
            }
            this.removeAll();
            changePlayers(players);

            try {
                connection.sendMessage(new Message(Constants.READY_RESPONSE, new byte[0]));
            } catch (IOException ioException) {
                JOptionPane.showInternalMessageDialog(null, "Не удалось совершить действие");
            }
        });

        bottom.add(button, FlowLayout.LEFT);

        bottom.validate();
        bottom.repaint();
    }

    public void setEmptyRoom(){
        this.setBorder(new LineBorder(Color.BLACK, 3));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBackground(GuiConst.COLOR);
        this.setLayout(new BorderLayout());

        this.add(new RoomPlayersJPanel(new ArrayList<>()));

        font = Loader.loadFont("default.otf").deriveFont(dimension.height/30f);
        JButton button = new JButton("Присоединиться к комнате");
        button.setFont(font);
        button.addActionListener(e -> {
            Connection connection = ConnectionWrapper.getConnection();
            try {
                connection.sendMessage(new Message(Constants.JOIN_ROOM));
            } catch (IOException ioException) {
                JOptionPane.showInternalMessageDialog(null, "Не удалось присоединиться к комнате");
            }
        });

        bottom = new JPanel();
        bottom.setBackground(GuiConst.COLOR);
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));

        Dimension bSize = new Dimension(dimension.width/4, dimension.height/20);
        button.setPreferredSize(bSize);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);

        bottom.add(button);
        this.add(bottom, BorderLayout.PAGE_END);
    }
}
