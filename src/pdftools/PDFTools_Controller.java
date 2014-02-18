package pdftools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 *
 * @author jensb
 */
public final class PDFTools_Controller {

    private PDFTools_Model model;
    private PDFTools_View view;
    
    PDFTools_Controller(PDFTools_Model model, PDFTools_View view) {
        this.model = model;
        this.view = view;
        
        // Add listeners to the view
        this.view.addWindowListener(windowListener);
        
        
    }
    
    
    public void updateView() {
        view.getButton().addActionListener(testListen);
        view.getNextPageButton().addActionListener(testListen);
        this.view.setVisible(true);
    }
    
    ActionListener testListen = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println(ae.getActionCommand());
        }
    };
    
    WindowListener windowListener = new WindowAdapter() {
        @Override
        public void windowActivated(WindowEvent we) {
            System.out.println("Activated");
        }
        
        @Override
        public void windowClosing(WindowEvent we) {
            try { 
                model.Destroy();
                System.exit(0);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            //model.Destroy();
            
        }
    };





}
