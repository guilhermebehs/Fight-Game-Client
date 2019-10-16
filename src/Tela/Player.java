package Tela;


import Servidor.GameProtocolActionType;
import java.awt.Font;
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
    private GameProtocolActionType lastAction = GameProtocolActionType.STAND_RIGHT;
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
    private int width = 95;
    private int height = 140;
    public boolean toTheLeft = false; 
    
    public Player(){
      
    }
    
    public void setup(int x, int y){
        this.setX(x);
        this.setY(y);
        setWalkR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/walk.gif")).getImage().getScaledInstance(width, height-20, Image.SCALE_DEFAULT))); 
        setStandR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/stand.gif")).getImage().getScaledInstance(width, height-20, Image.SCALE_DEFAULT))); 
        setPunchR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punch.png")).getImage().getScaledInstance(width, height-20, Image.SCALE_DEFAULT)));
        setPunchedR(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punched.png")).getImage().getScaledInstance(width, height-20, Image.SCALE_DEFAULT)));
        setWalkL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/walk inverse.gif")).getImage().getScaledInstance(width, height-20, Image.SCALE_DEFAULT))); 
        setStandL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/stand inverse.gif")).getImage().getScaledInstance(width, height-20, Image.SCALE_DEFAULT))); 
        setPunchL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punch inverse.png")).getImage().getScaledInstance(width, height-20, Image.SCALE_DEFAULT))); 
        setPunchedL(new ImageIcon(new ImageIcon(getClass().getResource("/imgs/punched inverse.png")).getImage().getScaledInstance(width, height-20, Image.SCALE_DEFAULT)));
        setIcon(getStandR());
        setLastImage(getStandR());
        setBounds(x, y,width, height);
    }
    
    public void moveLeft(){
        setX(getX() - 1);
        setLastImage(getWalkL());
        setIcon(getWalkL());
       setBounds(getX(), getY(), width, height);
        setLastAction(GameProtocolActionType.MOVE_LEFT);
        toTheLeft = true;
    }
    
     public void moveRight(){
        setX(getX() + 1); 
        setLastImage(getWalkR());
        setIcon(getWalkR());
        setBounds(getX(), getY(), width, height);
        setLastAction(GameProtocolActionType.MOVE_RIGHT);
        toTheLeft = false;
    }
     
    public void moveUp(){
        setY(getY() - 1);
        setBounds(getX(), getY(), width, height);
        setLastAction(GameProtocolActionType.MOVE_UP);
    }
    
    public void moveDown(){
         setY(getY() + 1);
        setBounds(getX(), getY(), width, height);
          setLastAction(GameProtocolActionType.MOVE_DOWN);
    }
    
    
    public void punch(){
      if(getLastImage() == getWalkR() || getLastImage() == getStandR() || getLastImage() == getPunchR() || getLastImage() == getPunchedR())
         punchRight(); 
      else
         punchLeft(); 
        
    }
    
    public void punchLeft(){
      setIcon(getPunchL());
      setLastAction(GameProtocolActionType.PUNCH_LEFT);
      toTheLeft = true;
    }
    
    public void punchRight(){
       setIcon(getPunchR());
      setLastAction(GameProtocolActionType.PUNCH_RIGHT);
      toTheLeft = false;
    }
    
    public void punched(int id){
      if(getLastImage() == getWalkR() || getLastImage() == getStandR() || getLastImage() == getPunchR() || getLastImage() == getPunchedR())
         setIcon(getPunchedR());
      else
         setIcon(getPunchedL()); 
         setBounds(getX(), getY(), width, height);
        setIdPlayerPunch(id);
        setWasPunched(true);
        
    }
    
    public void stand(){
        
      if(getLastImage() == getWalkR() || getLastImage() == getStandR() || getLastImage() == getPunchR() || getLastImage() == getPunchedR())
            standRight();
          else
            standLeft();
        
    }
    
    public void standLeft(){
        toTheLeft = true;
     setWasPunched(false);
      setIcon(getStandL());
      setLastAction(GameProtocolActionType.STAND_LEFT);
      setBounds(getX(), getY(), width, height); 

    }
    
    public void standRight(){
      toTheLeft = false;  
      setWasPunched(false);  
      setIcon(getStandR());
      setLastAction(GameProtocolActionType.STAND_RIGHT);
      setBounds(getX(), getY(), width, height); 
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
         setText(String.valueOf(id));
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM); 
        Font font = new Font("Courier", Font.BOLD,12);
        setFont(font);

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
