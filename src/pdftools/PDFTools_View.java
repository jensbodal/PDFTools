package pdftools;

import java.io.IOException;
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
    private int PADDING = 25;
    
    private PDFTools_Model model;
    private PDFPagePanel pagePanel;
    private int frameHeight;
    private int frameWidth;
    private int frameX = 0;
    private int frameY = 0;
    private JButton nextPageButton;
    private JButton previousPageButton;
    
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
        
        this.add(pagePanel);
        this.setJMenuBar(JMenuBar());
        
        
        // Finalize Layout
        setFrameBounds();
    }
    

    
    private void setFrameBounds() {
        frameWidth = this.pagePanel.getWidth() + PADDING;
        frameHeight = (this.pagePanel.getHeight() + 
                (this.getPreferredSize().height + PADDING));
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
        if (previousPageButton == null) {
            setPreviousPageButton();
        }
        menuBar.add(previousPageButton);
        
        // Next Page
        if (nextPageButton == null) {
            setNextPageButton();
        }
        menuBar.add(nextPageButton);
        
        return menuBar;
    }
    
    private PDFPagePanel PDFPanel() throws IOException {
        PDFPagePanel panel = new PDFPagePanel();
        panel.setPage(model.getPage());
        return panel;
    }
    
    public void setNextPageButton() {
        nextPageButton = new JButton("Next Page");
    }
    
    public void setPreviousPageButton() {
        previousPageButton = new JButton ("Previous Page");
    }

    public JButton getPreviousPageButton() {
        return previousPageButton;
    }
    
    public JButton getNextPageButton() {
        return nextPageButton;
    }

 


    

}
