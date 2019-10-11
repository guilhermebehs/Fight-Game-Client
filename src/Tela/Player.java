package Tela;


import Servidor.GameProtocolActionType;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel
 */
public class Player extends JLabel{
    
    private int x = 50;
    private int y = 50;
    private int id;
    private GameProtocolActionType lastAction = GameProtocolActionType.STAND;
    private ImageIcon lastImage;
    private ImageIcon walkR;
    private ImageIcon standR;
    private ImageIcon punchR;
    private ImageIcon punchedR;
    private ImageIcon walkL;
    private ImageIcon standL;
    private ImageIcon punchL;;
    private ImageIcon punchedL;
    private boolean wasPunched = false;
    private int idPlayerPunch;
    private int points = 0;
    
    
    public Player(){
        setWalkR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/walk.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setStandR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/stand.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setPunchR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punch.png")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT)));
        setPunchedR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punched.png")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT)));
         setWalkL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/walk inverse.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setStandL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/stand inverse.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setPunchL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punch inverse.png")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setPunchedL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punched inverse.png")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT)));
        setIcon(getStandR());
        setLastImage(getStandR());
        setBounds(getX(), getY(), 90, 127);
    }
    
    public void setup(int x, int y){
        this.setX(x);
        this.setY(y);
        setWalkR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/walk.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setStandR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/stand.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setPunchR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punch.png")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT)));
        setPunchedR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punched.png")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT)));
         setWalkL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/walk inverse.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setStandL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/stand inverse.gif")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setPunchL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punch inverse.png")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT))); 
        setPunchedL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punched inverse.png")).getImage().getScaledInstance(88, 127, Image.SCALE_DEFAULT)));
        setIcon(getStandR());
        setLastImage(getStandR());
        setBounds(x, y, 90, 127);
    }
    
    public void moveLeft(){
        setX(getX() - 1);
        setLastImage(getWalkL());
     setIcon(getWalkL());
     setBounds(getX(), getY(), 90, 127);
        setLastAction(GameProtocolActionType.MOVE_LEFT);
    }
    
     public void moveRight(){
        setX(getX() + 1); 
        setLastImage(getWalkR());
        setIcon(getWalkR());
        setBounds(getX(), getY(), 90, 127);
        setLastAction(GameProtocolActionType.MOVE_RIGHT);
    }
     
    public void moveUp(){
        setY(getY() - 1);
        setBounds(getX(), getY(), 90, 127);
        setLastAction(GameProtocolActionType.MOVE_UP);
    }
    
    public void moveDown(){
         setY(getY() + 1);
        setBounds(getX(), getY(), 90, 127);
          setLastAction(GameProtocolActionType.MOVE_DOWN);
    }
    
    public void punch(){
      if(getLastImage() == getWalkR() || getLastImage() == getStandR() || getLastImage() == getPunchR() || getLastImage() == getPunchedR())
         setIcon(getPunchR());
      else
         setIcon(getPunchL()); 
        setLastAction(GameProtocolActionType.PUNCH);
    }
    
    public void punched(int id){
      if(getLastImage() == getWalkR() || getLastImage() == getStandR() || getLastImage() == getPunchR() || getLastImage() == getPunchedR())
         setIcon(getPunchedR());
      else
         setIcon(getPunchedL()); 
        setIdPlayerPunch(id);
        setWasPunched(true);
    }
    
    public void stand(){
      if(getLastImage() == getWalkR() || getLastImage() == getStandR() || getLastImage() == getPunchR() || getLastImage() == getPunchedR())
            setIcon(getStandR());
          else
            setIcon(getStandL());
         setWasPunched(false);
         setLastAction(GameProtocolActionType.STAND);
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the lastAction
     */
    public GameProtocolActionType getLastAction() {
        return lastAction;
    }

    /**
     * @param lastAction the lastAction to set
     */
    public void setLastAction(GameProtocolActionType lastAction) {
        this.lastAction = lastAction;
    }

    /**
     * @return the lastImage
     */
    public ImageIcon getLastImage() {
        return lastImage;
    }

    /**
     * @param lastImage the lastImage to set
     */
    public void setLastImage(ImageIcon lastImage) {
        this.lastImage = lastImage;
    }

    /**
     * @return the walkR
     */
    public ImageIcon getWalkR() {
        return walkR;
    }

    /**
     * @param walkR the walkR to set
     */
    public void setWalkR(ImageIcon walkR) {
        this.walkR = walkR;
    }

    /**
     * @return the standR
     */
    public ImageIcon getStandR() {
        return standR;
    }

    /**
     * @param standR the standR to set
     */
    public void setStandR(ImageIcon standR) {
        this.standR = standR;
    }

    /**
     * @return the punchR
     */
    public ImageIcon getPunchR() {
        return punchR;
    }

    /**
     * @param punchR the punchR to set
     */
    public void setPunchR(ImageIcon punchR) {
        this.punchR = punchR;
    }

    /**
     * @return the punchedR
     */
    public ImageIcon getPunchedR() {
        return punchedR;
    }

    /**
     * @param punchedR the punchedR to set
     */
    public void setPunchedR(ImageIcon punchedR) {
        this.punchedR = punchedR;
    }

    /**
     * @return the walkL
     */
    public ImageIcon getWalkL() {
        return walkL;
    }

    /**
     * @param walkL the walkL to set
     */
    public void setWalkL(ImageIcon walkL) {
        this.walkL = walkL;
    }

    /**
     * @return the standL
     */
    public ImageIcon getStandL() {
        return standL;
    }

    /**
     * @param standL the standL to set
     */
    public void setStandL(ImageIcon standL) {
        this.standL = standL;
    }

    /**
     * @return the punchL
     */
    public ImageIcon getPunchL() {
        return punchL;
    }

    /**
     * @param punchL the punchL to set
     */
    public void setPunchL(ImageIcon punchL) {
        this.punchL = punchL;
    }

    /**
     * @return the punchedL
     */
    public ImageIcon getPunchedL() {
        return punchedL;
    }

    /**
     * @param punchedL the punchedL to set
     */
    public void setPunchedL(ImageIcon punchedL) {
        this.punchedL = punchedL;
    }

    /**
     * @return the wasPunched
     */
    public boolean isWasPunched() {
        return wasPunched;
    }

    /**
     * @param wasPunched the wasPunched to set
     */
    public void setWasPunched(boolean wasPunched) {
        this.wasPunched = wasPunched;
    }

    /**
     * @return the idPlayerPunch
     */
    public int getIdPlayerPunch() {
        return idPlayerPunch;
    }

    /**
     * @param idPlayerPunch the idPlayerPunch to set
     */
    public void setIdPlayerPunch(int idPlayerPunch) {
        this.idPlayerPunch = idPlayerPunch;
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
    

}
