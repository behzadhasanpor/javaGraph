package Classes;
/*
-----------------------------------------------------------
virtualSurface

 the word 'virtual + surface' indicates and virtual panel that 
simulate an plot panel that has a dimention and the points on 
this panel has a diffrent point values from pixel of screan.
 
 i use AffineTransform class to map virtual surface and real screan
pixel to each other,there is diffrent ways that we can use and even we 
can deffine own basic virtual surface using xPoints(or any other points)

 i refuse to explain about affineTransforms in math,there is to many 
source about that,and in java it has a simple to known methods that i use
some of them there.

from this initializer method :::: i can explain structure of program

    public void VS_initialize(){
            erase_surface();
            render_axis();
            renderPlot();
    }

-Steps of plot
Step 1 : initialize some basic variables like dimention of surface and etc
        and also erase the surface  
        using initialize_basic() method
Step 2 : render the axis that is an array pf Line objects
        rendering axis is drawing that lines
        
Step 3 : render the ploting equation that is an array of xPoints
        and rendering that is to show them
    exp. that we use a condition in adding points to this list based on 
    comparing the equation(that has a replacement in x and y with some given
    array of x,y's)with zero(=0),if the condition sutisfied then the xPoint
    will be add to plot list if not do nothing


--------------------------------------------------------------
*/
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;
import View.Graph;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class virtualSurface {
    
    /*
    main graphical panel that we have to do some stuff on that
    */
    private final JPanel surface;
    
    /*
    VS : virtual Surface
    an array of xPoints 
    xPoints:-> is a class use for indicate virtual points and have a different 
        tools for us to have a better communiucation with real pixel points

    */
    private ArrayList<xPoint> VS;
    /*
    affine transform and inverse object
    */
    private AffineTransform C2P;
    private AffineTransform P2C;

    /*
    graphics component of surface
    there is two one simple graphics and one graphics2d 
    */
    protected Graphics oneDG;
    protected Graphics2D twoDG;
    /*
    Width and Height of surface
    */
    private final int width;
    private final int height;
    /*
    Band of Width that we want to show  
    Band of Height that we want to show 
    and the dimention of surface
    */
    private float BW_Band;
    private float BH_Band;
    private float dimention;
    /*
    accuracy that we give to for and compare method
    */
    private final double accuracy_for_x=0.1;
    private final double accuracy_for_y=0.1;
    private final double accuracy_plot=0.06;
    /*
    num_of_axis x and y and B_axis is a variable 
    */
    private int num_x_axis=5;
    private int num_y_axis=5;
    /*
    some color of defferent parts of plot
    */
    private Color grid_color=new Color(220,220,220);
    private Color axis_color=new Color(120,180,180);
    private Color origin_point_color=new Color(150,180,220);
    private Color BG_color=new Color(245,245,245); 
    private Color plot_color=Color.RED;
   
    /*
    thichnesses
    */
    private float axis_thickness=0;
    private float plot_thickness=1;
    /*
    two points used for dragging of plot
    */
    private Point dragStart;
    private Point dragEnd;
    /*
    a variable handle the equation
    */
    private String command;
    /*
    a list of xPoints handle grid xPoints 
    */
    private ArrayList<xPoint> gridValues=new ArrayList();
    /*
    array that handle parsed string from calculator 
    */
    private ArrayList<String> parsed;
    /*
    object of calculator class for evaluate the equation
    */
    private calculator solver;
    /*
    helper variable
    */
    private int axis_render_times;
    
    
    /*
    constructor get target JPanel and do some wanted stuf on that!
    */
    public virtualSurface(JPanel panel){
        /*
        set surface panel
        */
        this.surface=panel;
        /*
        set graphics Component
        */
        this.oneDG=panel.getGraphics();
        this.twoDG=(Graphics2D)panel.getGraphics();
        /*
        height and width
        */
        this.height=panel.getHeight();
        this.width=panel.getWidth();
        /*
        init BW ,Bh and epsUnit
        */
        this.dimention=10;
        this.axis_render_times=1;
    }
    /*
    initialize some stuf
    */
    private void init_basic_variables(){
            solver=new calculator(command);
            this.parsed=solver.parseMainString();
            BW_Band=dimention;BH_Band=dimention;
            AffineTransform translate=AffineTransform.getTranslateInstance(width/2, height/2);
            AffineTransform scale= AffineTransform.getScaleInstance(width/BW_Band,height/BH_Band);
            AffineTransform mirror_y = new AffineTransform(1, 0, 0, -1, 0, 0);
            C2P = new AffineTransform(translate);
            C2P.concatenate(scale);
            C2P.concatenate(mirror_y);
//            try{
//                P2C=C2P.createInverse();
//            }
//            catch(Exception exp){System.out.println("inverse not exists");}
    
    }
    /*
    initialize VS
    VS stands for:  Virtual Surface
    */
    public void VS_initialize(){
            erase_surface();
            render_axis();
            renderPlot();
    }
    /*
    initialize axis Lines and grid xPoints
    */
    public ArrayList<Line> initialize_axis(){
        ArrayList<Line> result=new ArrayList();
        gridValues.clear();
        ArrayList<xPoint> gridValues1=new ArrayList();
        double w=BW_Band/2;
        double wh=9*w;
        double h=0;
        double step=w/num_x_axis;
        while(h<wh){
            h+=step;
            double[] hash={h,-h};
            Line.LINE_TYPE[] types={Line.LINE_TYPE.VERTICAL,Line.LINE_TYPE.HORIZENTAL};
            for(double hi:hash){
                Point pixel=new Point();
                C2P.transform(new Point2D.Double(0,hi),pixel);
                result.add(makeLine(types[1], axis_thickness, pixel));
                gridValues1.add(makeXpoint(0, (float) hi,pixel).setType(xPoint.SHOW_TYPE.Y_SHOW));
                Point pixel1=new Point();
                C2P.transform(new Point2D.Double(hi,0),pixel1);
                result.add(makeLine(types[0], axis_thickness, pixel1));
                gridValues1.add(makeXpoint((float) hi,0,pixel1).setType(xPoint.SHOW_TYPE.X_SHOW));
                    }
        }
        this.gridValues=gridValues1;
        return result;
    }
    /*
    initialize plot xPoints
    */
    public ArrayList<xPoint> initialize_plot(){
        ArrayList<xPoint> result =new ArrayList();
        Point pixel1 = null;
        C2P.transform(new Point2D.Double(0,0),pixel1);
        for(float i=-BW_Band;i<BW_Band;i+=accuracy_for_x){
            for(float j=-BH_Band;j<BH_Band;j+=accuracy_for_y){
                if(compareTelorance((float) solver.solver(parsed,i,j),0,accuracy_plot)){
                    Point pixel=new Point();
                    C2P.transform(new Point2D.Double(i,j),pixel);
                    result.add(makeXpoint(i, j, pixel));
                }
            }
        }
        return result;
    }
    /*
    render axis xPoints
    */
    public void render_axis(){                
        Point pixel=new Point();
        C2P.transform(new Point2D.Double(0,0),pixel);
        makeLine(Line.LINE_TYPE.VERTICAL, axis_thickness,pixel).Draw(axis_color);
        Point pixel1=new Point();
        C2P.transform(new Point2D.Double(0,0),pixel1);
        makeLine(Line.LINE_TYPE.HORIZENTAL, axis_thickness,pixel1).Draw(axis_color);
        initialize_axis().stream().forEach((l) ->{l.Draw(grid_color);});
        gridValues.stream().forEach((xp)->{xp.showDetails(Color.BLACK);});
    }
    /*
    render plot xPoints
    */
    public void renderPlot(){
        initialize_plot().stream().forEach((xp)->{xp.showPoint(plot_color);});
    }
    /*
    ------------------------------------------------------------------
    
    
    
    
    
    
    below is some event handling methods
    
    -------------------------------------------------------------------
    */
    public void left_mouse_relized(java.awt.event.MouseEvent evt){
        dragStart=new Point(0,0);
        default_mouse_cursor();
    }
    /*
    mouse pressed
    */
    public void left_mouse_pressed(java.awt.event.MouseEvent evt){
        dragStart=evt.getPoint();
    }
    /*
    earese surface
    */
    public void mouse_wheele(java.awt.event.MouseWheelEvent evt){
        
        int zoomMode=evt.getWheelRotation();
        if(zoomMode==1){
            if(dimention<50){
                this.dimention+=0.5;
                }
        }else{
            if(dimention>0.5){
                this.dimention-=0.5;
        }
            }
        if(axis_render_times>=0){
            axis_render_times--;
            num_x_axis--;
            num_y_axis--;
        }else if(axis_render_times<1){
            axis_render_times++;
            num_x_axis++;
            num_y_axis++;
        }
                init_basic_variables();
                VS_initialize();
    }
    private void erase_surface(){
        Color lastColor=oneDG.getColor();
        oneDG.setColor(BG_color);
        oneDG.fillRect(0, 0, (int)width,(int)height);
        oneDG.setColor(lastColor);
    }
    /*
    mouse dragged
    */
    public void left_mouse_dragged(java.awt.event.MouseEvent evt){
        dragEnd=evt.getPoint();
        surface.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        double x=dragEnd.x-dragStart.x;
        double y=dragEnd.y-dragStart.y;
        twoDG.translate(x, y);
        VS_initialize();
        dragStart=dragEnd;
    }
    public void mouse_click(java.awt.event.MouseEvent evt){
        // TODO
    }
    public void plotClicked(java.awt.event.MouseEvent evt){
            init_basic_variables();
    }
    private void default_mouse_cursor(){
        surface.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    /*
    campare with given telorance
    */
    public boolean compareTelorance(float x1,float x2,double telorance){
        return (x1>(x2-telorance))&&(x1<(x2+telorance));
    }
    /*
    make an xPoint using current Graphics g
    */
    private xPoint makeXpoint(float x,float y,Point real){
        return new xPoint(x,y,real,twoDG);
    }
    /*
    make a line with given Point
    */
    private Line makeLine(Line.LINE_TYPE type,float thickness,Point...ps){
        return new Line(twoDG,thickness,surface, type, ps);
    }
    /*
    run for this class instance
    */
    public void test(String command){
        this.command=command;
        init_basic_variables();
        VS_initialize();
    }
    /*
    ovverride test 
    */
    public void test(String command,float dimention){
        this.dimention=dimention;
        this.command=command;
        init_basic_variables();
        VS_initialize();
    }
}






/*
--------------------------------------------------------------------------------------
MIT licence@KBase -> https://github.com/behzadhasanpor
---------------------------------------------------------------------------------------
*/



















