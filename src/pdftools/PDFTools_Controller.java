package pdftools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 *
 * @author jensb
 */
public final class PDFTools_Controller implements PDFTools_Interface {

    private PDFTools_Model model;
    private PDFTools_View view;
    
    PDFTools_Controller(PDFTools_Model model, PDFTools_View view) {
        this.model = model;
        this.view = view;
        
        // Add listeners to the view
        this.view.addWindowListener(windowListener);
        this.view.getNextPageButton().addActionListener(L_NextPage());
        this.view.getPreviousPageButton().addActionListener(L_PreviousPage());
    }
    
    
    public void updateView() {

        this.view.setVisible(true);
    }
    
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

    @Override
    public ActionListener L_NextPage() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(ae.getActionCommand());
            }
        };
    }

    @Override
    public ActionListener L_PreviousPage() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(ae.getActionCommand());
            }
        };
    }


}
