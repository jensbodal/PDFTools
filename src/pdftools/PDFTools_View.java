package pdftools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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
    private PDFTools_Controller controller;
    private PDFPagePanel pagePanel;
    private int frameHeight;
    private int frameWidth;
    private int frameX = 0;
    private int frameY = 0;
    JButton nextPageButton;
    
    
    public PDFTools_View(PDFTools_Model model) {
        // Set up Logic
        this.model = model;
        
        // Initialize Components
        try { 
            this.pagePanel = PDFPanel();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        
        // Layout Components
        
        //this.add(pagePanel);
        this.setJMenuBar(JMenuBar());
        
        
        // Finalize Layout
        setFrameBounds();
    }
    

    
    private void setFrameBounds() {
        frameWidth = this.pagePanel.getWidth();
        frameHeight = (this.pagePanel.getHeight() + 
                (this.getPreferredSize().height * 2));
        this.setBounds(frameX, frameY, frameWidth, frameHeight);
    }
    
    private JMenuBar JMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // File Menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        // Action Menu
        JMenu actionMenu = new JMenu("Actions");
        menuBar.add(actionMenu);
        
        
        
        // Previous Page
        JButton previousPage = new JButton("Previous Page");
        menuBar.add(previousPage);
        
        // Next Page
        menuBar.add(nextPageButton());
        
        // Test Button
        testButton = new JButton("TESTING");
        menuBar.add(testButton);
        
        return menuBar;
    }
    
    public PDFPagePanel PDFPanel() throws IOException {
        PDFPagePanel panel = new PDFPagePanel();
        panel.setPage(model.getPage());
        return panel;
    }
    
    public JButton nextPageButton() {
        nextPageButton = new JButton("Next Page");
        return nextPageButton;
    }

    private JButton testButton;
    
    public JButton getButton() {
        return testButton;
    }
    
    public JButton getNextPageButton() {
        return nextPageButton();
    }

 


    

}
