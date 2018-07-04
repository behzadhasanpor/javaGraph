package Classes;
/*
-=-----------=-----------=------------=----------=--------------=----------------=

this class is a new Type for a point that can print and show details of 
itself on a Graphics2D that we given to it in constructor.
there is diffrent advantages in this type 
-> can show point by a method
-> chainable
-> can save its virtual double point
-> can show its virtual point
every xPoint is belong to a Graphics2D and make scense with it
-=-----------=-----------=------------=----------=--------------=----------------=
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

class xPoint {
    /*
    real value of point at screan(real pixel dimentions on panel)
    */
    public Point realPoint;
    /*
    x,y of point in virtual surface
    */
    public float xValue;
    public float yValue;
    /*
    the Graphics2D that xPoint is belong to it
    */
    private Graphics2D graphics;
    /*
    some additional stuff for bettering view..
    */
    private String fontFamily="tahoma";
    private int fontSize=8;
    /*
    the type of xPoint for show time based on SHOW_TYPE enum
    */
    private SHOW_TYPE type;
    
    /*
    enum for indicate and save show type of xPoint
    show x or y or both of them
    */
    public enum SHOW_TYPE{
        
        X_SHOW(-1),
        ALL_SHOW(0),
        Y_SHOW(1);
        
        int value; 
        
        private SHOW_TYPE(int val){
            value=val;
        }
    }
    /*
    constructor
    */
    public xPoint(float x,float y,Point realPoint,Graphics g){
        this.realPoint=realPoint;
        this.xValue=x;
        this.yValue=y;
        this.graphics=(Graphics2D)g;
    }
    /*
    draw point on graphics
    */
    public xPoint showPoint(Color pointColor){
        Color lastColor=graphics.getColor();
        graphics.setColor(pointColor);
        graphics.drawOval(realPoint.x,realPoint.y,1,1);
        graphics.setColor(lastColor);
        return this;
    }
    /*
    show details of point in the exact place of point 
    */
    public xPoint showDetails(SHOW_TYPE type,Color textColor){
        Font lastFont=graphics.getFont();
        graphics.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
        Color lastColor=graphics.getColor();
        graphics.setColor(textColor);
        if(type==SHOW_TYPE.ALL_SHOW){
            graphics.drawString(String.format("%.1f,%.1f",xValue,yValue),realPoint.x,realPoint.y);
        }else if(type==SHOW_TYPE.X_SHOW){
            graphics.drawString(String.format("%.1f",xValue),realPoint.x,realPoint.y);
        }else if(type==SHOW_TYPE.Y_SHOW){
            graphics.drawString(String.format("%.1f",yValue),realPoint.x,realPoint.y);
        }
        graphics.setFont(lastFont);
        graphics.setColor(lastColor);
        return this;
    }
    /*
    chainable set type
    */
    public xPoint setType(SHOW_TYPE type){this.type=type;return this;}
    /*
    overrided show details for when that we set the type before
    */
    public xPoint showDetails(Color textColor){
        Font lastFont=graphics.getFont();
        graphics.setFont(new Font(fontFamily,Font.PLAIN,fontSize));
        Color lastColor=graphics.getColor();
        graphics.setColor(textColor);
        if(type==SHOW_TYPE.ALL_SHOW){
            graphics.drawString(String.format("%.1f,%.1f",xValue,yValue),realPoint.x,realPoint.y);
        }else if(type==SHOW_TYPE.X_SHOW){
            graphics.drawString(String.format("%.1f",xValue),realPoint.x,realPoint.y);
        }else if(type==SHOW_TYPE.Y_SHOW){
            graphics.drawString(String.format("%.1f",yValue),realPoint.x,realPoint.y);
        }
        graphics.setFont(lastFont);
        graphics.setColor(lastColor);
        return this;
    }
    /*
    get real point of xPoint
    */
    public Point getReal(){
        return realPoint;
    }
    /*
    overrided to toString
    */
    @Override
    public String toString(){
        return "[xValue : "+xValue+",yValue : "+yValue+",realX : "+realPoint.x+",realY : "+realPoint.y+"]";
    }
}



/*
--------------------------------------------------------------------------------------
MIT licence@KBase -> https://github.com/behzadhasanpor
---------------------------------------------------------------------------------------
*/

