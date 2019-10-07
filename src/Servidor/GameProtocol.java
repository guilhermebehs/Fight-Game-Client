package Servidor;


import Servidor.GameProtocolActionType;
import java.io.Serializable;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 0151194
 */
public class GameProtocol implements Serializable {
    
    private int x;
    private int y;
    private GameProtocolActionType actionType;
    
    public GameProtocol(int x, int y, GameProtocolActionType actionType){
        this.x = x;
        this.y = y;
        this.actionType = actionType;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the actionType
     */
    public GameProtocolActionType getActionType() {
        return actionType;
    }

    /**
     * @param actionType the actionType to set
     */
    public void setActionType(GameProtocolActionType actionType) {
        this.actionType = actionType;
    }
    
    
}
