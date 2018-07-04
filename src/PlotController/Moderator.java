package PlotController;

/*
--------------------------------------------------


Moderator class 
this class is an inter-class betwean Plotter and the mainFrom
this class are not necessary to be there and Plotter byself can
do moderators work,but i make it for next updating and growing 
project.


---------------------------------------------------
*/
import Classes.virtualSurface;
import View.mainFrame;

public class Moderator {
    /*
    object of plotter class
    */
    public static Plotter cartisianPlot;
    
    /*
    run main form
    */
    public static void run(String[] args){
        mainFrame.main(args);
    }
    /*
    make an virtual surface and add a panel to it and 
    the plot surface will be based on this panel
    */
    public static void addPanel(javax.swing.JPanel pnl){
        virtualSurface surf=new virtualSurface(pnl);
         cartisianPlot=new Plotter(pnl,surf);
    }
    /*
    invoke init method of current plotter object
    */
    public static void initPlotter(String command){
        cartisianPlot.init(command);
    }
    /*
    initialize the small panel showing in begin of program with 
    heart equation and plot that
    */
    public static void initPlotterSmall(Plotter pnl){
        pnl.init("x^2+((5/4)*y-sqrt(abs(x)))^2-1",(float)2.7);
    }
    /*
    --------------
    
    below is some mouse handle methods that invoked in forms
    ----------------
    */
    public static void left_mouse_released(java.awt.event.MouseEvent evt){
        cartisianPlot.left_mouse_released(evt);
    }
    public static void left_mouse_dragged(java.awt.event.MouseEvent evt){
        cartisianPlot.left_mouse_dragged(evt);
    }
    public static void left_mouse_pressed(java.awt.event.MouseEvent evt){
        cartisianPlot.left_mouse_pressed(evt);
    }
    public static void mouse_wheele(java.awt.event.MouseWheelEvent evt){
        cartisianPlot.mouse_wheele(evt);
    }
    public static void mouse_click(java.awt.event.MouseEvent evt){
        cartisianPlot.mouse_click(evt);
    }
    public static void plotClicked(java.awt.event.MouseEvent evt){
        cartisianPlot.plotClicked(evt);
    }
    /*
    run plotter test method
    */
    public static void runTest(){
         cartisianPlot.test();
    }
}



/*
--------------------------------------------------------------------------------------
MIT licence@KBase -> https://github.com/behzadhasanpor
---------------------------------------------------------------------------------------
*/
