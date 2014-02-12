/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdftools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.pdfbox.pdfviewer.PDFPagePanel;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author jensb
 */
public class PDFPanel_TEST extends JFrame {

    


    public PDFPanel_TEST() {
        this.setBounds(40, 40, 600, 600);
    }
    
    public PDFPanel_TEST(PDPage PDF_PAGE) {
        this();
        
        try {
            PDFPagePanel pdfPanel = new PDFPagePanel();
            pdfPanel.setPage(PDF_PAGE);
            pdfPanel.repaint();
            pdfPanel.updateUI();
            this.add(pdfPanel);
            this.repaint();
            
        }
        catch (IOException e) {
            System.out.println("MyError: " + e);
        }
        
        
        
    }
    
    
    
    //Override method tells what this is painting, maybe turn off?
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g2 = (Graphics2D) g;
//        
//        //g2.drawImage(this.image, 0, 0, null);
//    }


    
}
