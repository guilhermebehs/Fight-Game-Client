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
    List<Player> otherPlayers;
    Thread thPlayer;
    GameProtocolActionType player2lastAction;
    Container content;
    JTable placar;
    
    
    public ActionManagement(String host, int porta, Player player, JTable placar, Container content){
        try {
            this.player = player;
            otherPlayers = new ArrayList();
            s = new Socket(host, porta);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream());
            this.content = content;
            this.placar = placar;
            String id = in.readLine().split(":")[1];
            player.setId(Integer.parseInt(id));
            otherPlayers.add(player);
            sendAction(player.getX(), player.getY(), GameProtocolActionType.STAND);
            refreshScore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendAction (int x, int y, GameProtocolActionType actionType){
        GameProtocol prot = new GameProtocol(player.getId(),x,y,actionType);
        String protStr = prot.toString();
        out.println(protStr);
        out.flush();
     }
    
    public void receiveAction(){
        try {
            String msg;
            while((msg = in.readLine()) != null){
                
               if(msg.equals("newPlayerAlert")){
                  sendAction(player.getX(), player.getY(), player.getLastAction()); 
               } 
               else{
               boolean encontrouPlayer = false;
               String[] protArray =  msg.split(";");
               int id = Integer.parseInt(protArray[0]);
               int x = Integer.parseInt(protArray[1]);
               int y = Integer.parseInt(protArray[2]);
              
               GameProtocolActionType action = GameProtocolActionType.valueOf(protArray[3]);
               GameProtocol prot = new GameProtocol(id,x,y, action);
               for(Player player: otherPlayers){
                   if(player.getId() == prot.getId()){
                     encontrouPlayer = true;  
                     if(action == GameProtocolActionType.MOVE_UP){
                   
                   player.moveUp();
               }
               else if(action == GameProtocolActionType.MOVE_DOWN){
                  player.moveDown();
               }
             else if(action == GameProtocolActionType.MOVE_LEFT){
                  player.moveLeft();
             }
              else if(action == GameProtocolActionType.MOVE_RIGHT){
                  player.moveRight();
              }
              else if(action == GameProtocolActionType.PUNCH){
                  player.punch();
                  validatePunch(player);
                }
              else if(action == GameProtocolActionType.STAND){
                     player.stand();
                     restoreFromPunch(player);
              }                                   
                 break;
               }
             
            }
             if(!encontrouPlayer){
                 Player newPlayer = new Player();
                 newPlayer.setId(prot.getId());
                 newPlayer.setup(prot.getX(), prot.getY());
                 otherPlayers.add(newPlayer);
                 content.add(newPlayer);
                 refreshScore();
             }
          }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
  }

    public void iniciar(){
        thPlayer = new Thread(this);
        thPlayer.start();
    }
    
    public void restoreFromPunch(Player player){
        
          for(Player p: otherPlayers){
             if(p.getIdPlayerPunch() == player.getId()){
                p.stand();
             }
               
         }
        
    }
    
    
    public void validatePunch(Player player){
     
         for(Player p: otherPlayers){
             if(p.getId() != player.getId()){
                 int x1 = p.getX();
                 int x2 = player.getX();
                 int y1 = p.getY();
                 int y2 = player.getY();
                 int diferencaX = x1 - x2;
                 int diferencaY = y1 - y2;
                 diferencaX = diferencaX < 0? (diferencaX * -1) : diferencaX;
                 diferencaY = diferencaY < 0? (diferencaY * -1) : diferencaY;
                 
                 if(diferencaX < 30 && diferencaY < 30){
                    player.setPoints(player.getPoints() + 1);
                    p.punched(player.getId());
                    refreshScore();
                 }
                 
             }
         }
         
    }
    
    public void refreshScore(){
       DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
       //all cells false
            return false;
    }
       };
       
       model.addColumn("Player");
       model.addColumn("Pontuação");
        for(Player p: otherPlayers){
            model.addRow(new Object[]{p.getId(), p.getPoints()});
         }
        placar.setModel(model);
        placar.setFocusable(false);

    }
    
    @Override
    public void run() {
      receiveAction();
    }
}



