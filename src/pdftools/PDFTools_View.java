package pdftools;

import java.io.IOException;
import javax.swing.JFrame;
import org.apache.pdfbox.pdfviewer.PDFPagePanel;



/**
 *
 * @author jensb
 */
public class PDFTools_View extends JFrame {
    /* this View doesn't know about the Controller, except that it provides methods 
     * for registering a Controller's listeners. Other organizations are possible 
     * (eg, the Controller's listeners are non-private variables that can be referenced by 
     * the View, the View calls the Controller to get listeners, the View 
     * calls methods in the Controller to process actions, ...).
     */
 
    private PDFTools_Model model;
    private PDFPagePanel pagePanel;
    
    PDFTools_View(PDFTools_Model model) throws IOException {
        // Set up Logic
        this.model = model;
        
        // Initialize Components
        this.pagePanel = PDFPanel();
        
        // Layout Components
        this.add(pagePanel);
        
        // Finalize Layout
        setBounds(0, 0, this.pagePanel.getWidth(), this.pagePanel.getHeight());
    }
    
    private PDFPagePanel PDFPanel() throws IOException {
        PDFPagePanel panel = new PDFPagePanel();
        panel.setPage(model.getPage());
        return panel;
    }
    

}
