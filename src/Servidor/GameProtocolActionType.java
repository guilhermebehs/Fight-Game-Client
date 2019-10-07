package Servidor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 0151194
 */
public enum GameProtocolActionType {
    
    MOVE_UP(1), MOVE_DOWN(2), MOVE_LEFT(3), MOVE_RIGHT(4), PUNCH(5), STAND(6);

     private final int valor;
    
    GameProtocolActionType(int valor){
        this.valor = valor;
    }
    
}
