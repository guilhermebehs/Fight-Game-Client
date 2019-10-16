/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Tela.Player;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author guilherme.behs
 */
public class ActionManagement implements Runnable {

    BufferedReader in;
    PrintWriter out;
    Socket s;
    Player player;
    List<Player> players;
    Thread thPlayer;
    GameProtocolActionType player2lastAction;
    Container content;
    JTable placar;

    public ActionManagement(String host, int porta, Player player, JTable placar, Container content) {
        try {
            this.player = player;
            players = new ArrayList();
            s = new Socket(host, porta);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream());
            this.content = content;
            this.placar = placar;
            String id = in.readLine().split(":")[1];
            player.setId(Integer.parseInt(id));
            players.add(player);
            sendAction(player.getX(), player.getY(), GameProtocolActionType.STAND_RIGHT, 0);
            refreshScore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAction(int x, int y, GameProtocolActionType actionType, int points) {
        GameProtocol prot = new GameProtocol(player.getId(), x, y, actionType, points);
        String protStr = prot.toString();
        out.println(protStr);
        out.flush();
    }

    public void receiveAction() {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                if (msg.equals("newPlayerAlert")) {
                    sendAction(player.getX(), player.getY(), player.getLastAction(), player.getPoints());
                } else {
                    System.out.println(msg);
                    boolean encontrouPlayer = false;
                    String[] protArray = msg.split(";");
                    int id = Integer.parseInt(protArray[0]);
                    int x = Integer.parseInt(protArray[1]);
                    int y = Integer.parseInt(protArray[2]);

                    GameProtocolActionType action = GameProtocolActionType.valueOf(protArray[3]);
                    int points = Integer.parseInt(protArray[4]);

                    GameProtocol prot = new GameProtocol(id, x, y, action, points);
                    for (Player p : players) {
                        if (p.getId() == prot.getId()) {
                            encontrouPlayer = true;
                            p.setX(x);
                            p.setY(y);
                            setAction(p, prot.getActionType());        
                            break;
                        }

                    }
                    if (!encontrouPlayer) {
                        Player newPlayer = new Player();
                        newPlayer.setup(prot.getX(), prot.getY());
                        newPlayer.setId(prot.getId());
                        newPlayer.setPoints(points);
                       setAction(newPlayer, prot.getActionType());
                        players.add(newPlayer);
                   
                        content.add(newPlayer);
                        refreshScore();
                        
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciar() {
        thPlayer = new Thread(this);
        thPlayer.start();
    }

    public void restoreFromPunch(Player player) {

        for (Player p : players) {
            if (p.getIdPlayerPunch() == player.getId()) {
                p.stand();
            }

        }

    }

    public void validatePunch(Player player) {

        if(player.getLastAction() != GameProtocolActionType.PUNCH_LEFT && player.getLastAction() != GameProtocolActionType.PUNCH_RIGHT)
        for (Player p : players) {
            if (p.getId() != player.getId()) {
                int x1 = p.getX();
                int x2 = player.getX();
                int y1 = p.getY();
                int y2 = player.getY();
                int diferencaX = x1 - x2;
                int diferencaY = y1 - y2;
                diferencaX = diferencaX < 0 ? (diferencaX * -1) : diferencaX;
                diferencaY = diferencaY < 0 ? (diferencaY * -1) : diferencaY;

                if (diferencaX < 30 && diferencaY < 30) {
                    player.setPoints(player.getPoints() + 1);
                    p.punched(player.getId());
                    refreshScore();
                }

            }
        }

    }

    public void refreshScore() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Player");
        model.addColumn("Pontuação");
        for (Player p : players) {
            model.addRow(new Object[]{p.getId(), p.getPoints()});
        }
        placar.setModel(model);
        placar.setFocusable(false);

    }

    public void setAction(Player p, GameProtocolActionType action) {
        
        if (action == GameProtocolActionType.MOVE_UP) {

            p.moveUp();
        } else if (action == GameProtocolActionType.MOVE_DOWN) {
            p.moveDown();
        } else if (action == GameProtocolActionType.MOVE_LEFT) {
            p.moveLeft();
        } else if (action == GameProtocolActionType.MOVE_RIGHT) {
            p.moveRight();
        } else if (action == GameProtocolActionType.PUNCH_LEFT) {
            validatePunch(p);
            p.punchLeft();
        } else if (action == GameProtocolActionType.PUNCH_RIGHT) {
            validatePunch(p); 
            p.punchRight();
        } else if (action == GameProtocolActionType.STAND_LEFT) {
            p.standLeft();
            restoreFromPunch(p);
        } else if (action == GameProtocolActionType.STAND_RIGHT) {
            p.standRight();
            restoreFromPunch(p);
        }

    }

    @Override
    public void run() {
        receiveAction();
    }
}


