package ru.itis.gui.components;

import ru.itis.connection.Connection;
import ru.itis.general.entities.Player;
import ru.itis.gui.utils.ConnectionWrapper;
import ru.itis.gui.utils.GuiConst;
import ru.itis.gui.utils.Loader;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

import static ru.itis.gui.utils.GuiConst.*;

public class ResultsJPanel extends JPanel {
    private Font font;
    private MainJPanel mainJPanel;

    public ResultsJPanel(MainJPanel mainJPanel, List<Player> players, boolean isFinal) {
        this.setBackground(COLOR);
        this.setBorder(BLACK_BORDER);
        this.setLayout(new BorderLayout());

        JPanel main = new JPanel();
        main.setLayout(new GridBagLayout());
        main.setBackground(COLOR);

        JPanel top = new JPanel();
        top.setBackground(COLOR);
        Font titleFont = Loader.loadFont("name.otf").deriveFont(DIMENSION.height/10f);

        JLabel label = new JLabel("Результаты");
        label.setFont(titleFont);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        top.add(label);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.CENTER;

        font = Loader.loadFont("default.otf").deriveFont(DIMENSION.height/30f);
        int i = 1;
        for(Player player: players){
            JLabel nick = new JLabel(i + ". " + player.getNickname());
            nick.setFont(font);
            main.add(nick, constraints);
            constraints.gridx++;


            JPanel money = new JPanelWithBackground(Loader.loadImg("monetka.png"));
            money.setPreferredSize(new Dimension(DIMENSION.height/20, DIMENSION.height/20));
            main.add(money, constraints);
            constraints.gridx++;

            constraints.weightx = 1;

            JLabel moneyLabel = new JLabel(String.valueOf(player.getResult()));
            moneyLabel.setFont(font);
            main.add(moneyLabel, constraints);
            constraints.gridx++;

            JPanel time = new JPanelWithBackground(Loader.loadImg("clock.png"));
            time.setPreferredSize(new Dimension(DIMENSION.height/20, DIMENSION.height/20));
            main.add(time, constraints);
            constraints.gridx++;

            JLabel timeLabel = new JLabel(createTimeFormat(player.getTime()));
            timeLabel.setFont(font);
            main.add(timeLabel, constraints);

            constraints.gridx = 0;
            constraints.gridy++;
            i++;
        }

        this.add(top, BorderLayout.PAGE_START);
        this.add(main, BorderLayout.CENTER);

        if(isFinal) {
            addButtons();
        }

        Dimension thisSize = new Dimension(DIMENSION.width/2, DIMENSION.height - DIMENSION.height/3);
        this.setMinimumSize(thisSize);
        this.setMaximumSize(thisSize);
    }

    private void addButtons(){
        JPanel bottom = new JPanel();
        bottom.setBackground(COLOR);
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton button = new JButton("Назад");
        button.setBackground(COLOR);
        Dimension bSize = new Dimension(DIMENSION.width/10, DIMENSION.height/25);
        button.setFont(font);
        button.setMinimumSize(bSize);
        button.setMaximumSize(bSize);
        button.setBorder(BLACK_BORDER);

        JButton shop = new JButton("Магазин");
        shop.setFont(font);
        shop.setPreferredSize(bSize);
        shop.setBackground(Color.RED);
        shop.setForeground(Color.WHITE);

        bottom.add(shop);
        bottom.add(button);

        this.add(bottom, BorderLayout.PAGE_END);

        this.validate();
        this.repaint();
    }

    private String createTimeFormat(int n){
        return n/60 + ":" + n%60;
    }
}
