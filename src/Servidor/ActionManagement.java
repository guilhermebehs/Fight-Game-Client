/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Tela.Player;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 *
 * @author guilherme.behs
 */
public class ActionManagement implements Runnable {
    
    BufferedReader in;
    PrintWriter out;
    Socket s;
    Player player;
    Player player2;
    Thread thPlayer;
    GameProtocolActionType player2lastAction;
    
    
    public ActionManagement(String host, int porta, Player player,Player player2){
        try {
            this.player = player;
            this.player2 = player2;
            s = new Socket(host, porta);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendAction (int x, int y, GameProtocolActionType actionType){
        GameProtocol prot = new GameProtocol(x,y,actionType);
        out.println(prot.getActionType().name());
        out.flush();
     }
    
    public void receiveAction(){
        try {
            String msg;
            while((msg = in.readLine()) != null){
               GameProtocolActionType action = GameProtocolActionType.valueOf(msg);
               if(action == GameProtocolActionType.MOVE_UP){
                   player2.moveUp();
                   player2lastAction =  GameProtocolActionType.MOVE_UP;
               }
               else if(action == GameProtocolActionType.MOVE_DOWN){
                  player2.moveDown();
                  player2lastAction =  GameProtocolActionType.MOVE_DOWN;
               }
             else if(action == GameProtocolActionType.MOVE_LEFT){
                  player2.moveLeft();
                  player2lastAction =  GameProtocolActionType.MOVE_LEFT;
             }
              else if(action == GameProtocolActionType.MOVE_RIGHT){
                  player2.moveRight();
                  player2lastAction =  GameProtocolActionType.MOVE_RIGHT;
              }
              else if(action == GameProtocolActionType.PUNCH){
                  player2.punch();
                  int distanciaX = player.x - player2.x;
                   int distanciaY = player.y - player2.y;
                if(distanciaX < 0)
                    distanciaX = distanciaX * -1;
                 if(distanciaY < 0)
                    distanciaY = distanciaY * -1;

                if(distanciaX < 45 &&  distanciaY < 17){
                   if(player2lastAction != GameProtocolActionType.PUNCH){
                   player2.points++;
                   player.punched();
                   }
                }
               player2lastAction =  GameProtocolActionType.PUNCH;

              }
              else if(action == GameProtocolActionType.STAND){
                  if(player.wasPunched)
                     player.stand(); 
                  
                  player2.stand();
                player2lastAction =  GameProtocolActionType.STAND;      
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
    
    @Override
    public void run() {
      receiveAction();
    }
}



