package Classes;
/*
----------------------------------------------------------

Line
a new type for handle line drawing and set its parameters easier 
in the virtual surface,
every object of Line there is belong to a Graphics2D surface
Line has this advantages


-----------------------------------------------------------
*/
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import javax.swing.JPanel;

public class Line {
    /*
    line points for vertical ,horizental and .. lines
    */
    private Point startPoint=null;
    private Point endPoint=null;
    private Point singlePoint=null;
    
    /*
    graphics & panel
    */
    private Graphics2D graphics;
    private JPanel panel;
    /*
    stroke obj for line
    */
    private BasicStroke stroke;
    /*
    a LINE_TYPE indicates type of line
    */
    private LINE_TYPE type;
    
    /*
    enum for inidicate line type
    */
    public enum LINE_TYPE{
        
        VERTICAL(-1),
        DIAGONAL(0),
        HORIZENTAL(1);
        
        int value; 
        
        private LINE_TYPE(int val){
            value=val;
        }
    } 
    /*
    constructor
    */
    public Line(Graphics2D g,float thickness,JPanel panel,LINE_TYPE type,Point...p){
        this.graphics=g;
        this.stroke=new BasicStroke(thickness, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER);
        this.graphics.setStroke(new BasicStroke(thickness));
        this.panel=panel;
        this.type=type;
        if(type==LINE_TYPE.DIAGONAL){
            startPoint=p[0];
            endPoint=p[1];
        }else if(type==LINE_TYPE.HORIZENTAL || type==LINE_TYPE.VERTICAL){
            singlePoint=p[0];
        }
    }
    /*
    drawing the line
    */
    public void Draw(Color lineColor){
        Color temp=graphics.getColor();
        graphics.setColor(lineColor);
        Stroke lastStroke=graphics.getStroke();
        this.graphics.setStroke(stroke);
        if(this.type==LINE_TYPE.DIAGONAL){
            this.graphics.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
        else if(this.type==LINE_TYPE.HORIZENTAL){
            this.graphics.drawLine(-10*panel.getWidth(), singlePoint.y,10*panel.getWidth(),singlePoint.y);
        }
        else if(this.type==LINE_TYPE.VERTICAL){
            this.graphics.drawLine(singlePoint.x,-10*panel.getHeight() ,singlePoint.x,10*panel.getHeight());
        }
        graphics.setStroke(lastStroke);
        graphics.setColor(temp);
    }
    @Override
    public String toString(){
        String res="bad line type";
        try{
        if(type==LINE_TYPE.DIAGONAL){res="[startPoint : "+startPoint.x+","+startPoint.y+",endPoint : "+endPoint.x+","+endPoint.y+"]";}
        if(type==LINE_TYPE.HORIZENTAL){res="[horizental->point : "+singlePoint.x+","+singlePoint.y+"]";}
        if(type==LINE_TYPE.VERTICAL){res="[vertical->point : "+singlePoint.x+","+singlePoint.y+"]";}
        }
        catch(Exception exp){
            //do nothing
        }
        return res;
    }
}



/*
--------------------------------------------------------------------------------------
MIT licence@KBase -> https://github.com/behzadhasanpor
---------------------------------------------------------------------------------------
*/

