/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdftools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author jensb
 */
public class PDFPanel extends JPanel {
    
    private int DESIRED_WIDTH = 750;
    private int DESIRED_HEIGHT = 1000;
    private int WIDTH_PAD = 20;
    private int HEIGHT_PAD = 50;
    
    private Graphics2D g2;
    private BufferedImage image;
    
    
    public PDFPanel(PDPage pdf) {
        try {
            image = pdf.convertToImage();
        }
        catch (IOException e) {
            System.out.println("Error converting: " + e);
        }
        try {
            image = fixZoom(image);
        }
        catch (IOException e) {
            System.out.println(e);
        }
        this.image = image;
    }
    
    public int imageWidth() {
        return this.image.getWidth() + WIDTH_PAD;
    }
    
    public int imageHeight() {
        return this.image.getHeight() + HEIGHT_PAD;
    }
    
    private BufferedImage fixZoom(BufferedImage newImage) throws IOException {
        int newWidth = DESIRED_WIDTH;
        int newHeight = DESIRED_HEIGHT;
        return Thumbnails.of(newImage).size(newWidth, newHeight)
                .asBufferedImage();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.drawImage(this.image, 0, 0, null);
    }

}
