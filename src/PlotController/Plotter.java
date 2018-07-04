package PlotController;
/*
------------------------------------------------------
a class betwean moderator and surface handler or (virtualSurface class)
------------------------------------------------------
*/
import Classes.virtualSurface;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Plotter {
    /*
     * graph panel object  
     */
    private JPanel pnl;
    /*
    surface object
    */
    private virtualSurface surface;
    /**
     * constructor
     * 
     * @param graphPanel 
     *  plot panel getting from JFrame using moderator class 
     * @param srf 
     * virtual surface object
     */
    public Plotter(JPanel graphPanel,virtualSurface srf){
        this.pnl=graphPanel;
        this.surface=srf;
    }
    /*
    --------------
    below is some event handling method invoked by moderator class
    
    --------------
    */
    public void left_mouse_released(java.awt.event.MouseEvent evt){
        if(SwingUtilities.isLeftMouseButton(evt))
            surface.left_mouse_relized(evt);
    }
    public void left_mouse_dragged(java.awt.event.MouseEvent evt){
        if(SwingUtilities.isLeftMouseButton(evt))
            surface.left_mouse_dragged(evt);
    }
    public void left_mouse_pressed(java.awt.event.MouseEvent evt){
        if(SwingUtilities.isLeftMouseButton(evt))
            surface.left_mouse_pressed(evt);
    }
    public void mouse_wheele(java.awt.event.MouseWheelEvent evt){
            surface.mouse_wheele(evt);
    }
    public void mouse_click(java.awt.event.MouseEvent evt){
            surface.mouse_click(evt);
    }
    public void plotClicked(java.awt.event.MouseEvent evt){
        surface.plotClicked(evt);
    }
    /**
     * initialize plot 
     * this method invoke surface runner method that there is in 'test' name
     * @param command
     * the equation that is default of textbox.
     */
    public void init(String command){
        surface.test(command);
    }
    /*
    overriding method for init(String cmd)
    */
    public void init(String command,float dimention){
        surface.test(command,dimention);
    }
    /**
     * test method
     */
    public void test(){
        pnl.getGraphics().setColor(Color.RED);
        pnl.getGraphics().drawLine(0, 0, 500, 500);
    }
}




/*
--------------------------------------------------------------------------------------
MIT licence@KBase -> https://github.com/behzadhasanpor
---------------------------------------------------------------------------------------
*/
