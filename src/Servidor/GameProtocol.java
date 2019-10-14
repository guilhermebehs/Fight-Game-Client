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
    
    private int id;
    private int x;
    private int y;
    private GameProtocolActionType actionType;
    private int points = 0;
    
    public GameProtocol(int id,int x, int y, GameProtocolActionType actionType, int points){
        this.id = id;
        this.x = x;
        this.y = y;
        this.actionType = actionType;
        this.points = points;
    }

    /**
     * @return the x
     */
    
     public int getId() {
        return id;
    }

    /**
     * @param x the x to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
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
    
    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }
    
    
    public boolean equals(Object obj){
        if(!(obj instanceof ActionManagement))
           return false;
        GameProtocol prot = (GameProtocol) obj;
        
        return this.id == prot.id;
           
    }
    
    @Override
     public String toString(){
         
         String str = id+";"+x+";"+y+";"+actionType.name()+";"+points;
         return str;
     }

}
