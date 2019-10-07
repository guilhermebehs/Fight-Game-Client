/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tela;

import javax.swing.JLabel;

/**
 *
 * @author guilherme.behs
 */
public class PlacarUpdate implements Runnable {

    Player player;
    Player player2;
    JLabel placar;
    
    public PlacarUpdate(Player player, Player player2,JLabel placar){
        this.placar = placar;
        this.player = player;
        this.player2 = player2;
    }
    
    @Override
    public void run() {
      
        while(true){
            String texto = "Player1  "+player.points+"  x  "+player2.points+"  Player2";
            placar.setText(texto);
        }
        
    }
    
    
    
}
