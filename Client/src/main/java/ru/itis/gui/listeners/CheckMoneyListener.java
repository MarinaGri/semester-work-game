package ru.itis.gui.listeners;

import ru.itis.connection.Connection;
import ru.itis.general.entities.Player;
import ru.itis.gui.utils.ConnectionWrapper;
import ru.itis.gui.utils.Loader;
import ru.itis.protocol.Message;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static ru.itis.gui.utils.GuiConst.COLOR;
import static ru.itis.gui.utils.GuiConst.DIMENSION;
import static ru.itis.protocol.Constants.CHOOSE_DESIGN;

public class CheckMoneyListener implements ActionListener {
    private Player player;

    public CheckMoneyListener(Player player) {
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();
        Dimension size = new Dimension(DIMENSION.width/2, DIMENSION.height/3);
        JFrame modal = new JFrame();
        Font font = Loader.loadFont("default.otf").deriveFont(DIMENSION.height/20f);

        JPanel modalPanel = new JPanel();
        Border line = new LineBorder(Color.BLACK, 3);
        modalPanel.setBorder(line);
        modalPanel.setPreferredSize(size);
        modalPanel.setBackground(COLOR);

        JPanel bottom = new JPanel();
        bottom.setBackground(COLOR);
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel message = new JLabel();
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setFont(font);

        modalPanel.setLayout(new BorderLayout());
        modalPanel.add(message);

        modal.getContentPane().add(modalPanel);

        if(player.getMoney() < Integer.parseInt(button.getText())){
            message.setText("У вас недостаточно средств");
            JButton ok = new JButton("ОК");
            ok.setBackground(COLOR);
            ok.setFont(font.deriveFont(DIMENSION.height/30f));
            ok.setBorder(line);
            ok.addActionListener(e -> {
                modal.dispatchEvent(new WindowEvent(modal, WindowEvent.WINDOW_CLOSING));
            });
            bottom.add(ok);

        } else {
            message.setText("Купить?");
            JButton yes = new JButton("ДА");
            yes.setFont(font.deriveFont(DIMENSION.height/30f));
            yes.setBackground(COLOR);
            yes.setBorder(line);
            yes.addActionListener(e -> {
                Connection connection = ConnectionWrapper.getConnection();
                try {
                    //что-то еще
                    connection.sendMessage(new Message(CHOOSE_DESIGN));
                } catch (IOException ioException) {
                    JOptionPane.showInternalMessageDialog(null, "Не удалось купить машинку");
                }
            });
            bottom.add(yes);

            JButton no = new JButton("НЕТ");
            no.setFont(font.deriveFont(DIMENSION.height/30f));
            no.setBackground(COLOR);
            no.setBorder(line);
            no.addActionListener(e -> {
                modal.dispatchEvent(new WindowEvent(modal, WindowEvent.WINDOW_CLOSING));
            });
            bottom.add(no);
        }

        modalPanel.add(bottom, BorderLayout.PAGE_END);

        modal.setSize(size);
        modal.setLocation(DIMENSION.width/2 - DIMENSION.width/4, DIMENSION.height/2 - DIMENSION.height/6);

        modal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modal.setVisible(true);
    }
}
