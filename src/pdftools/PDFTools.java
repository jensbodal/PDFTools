package pdftools;

import java.io.IOException;

/**
 *
 * @author jensb
 */
public class PDFTools 
{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        PDFTools_Model model = new PDFTools_Model();
        PDFTools_View view = new PDFTools_View(model);
        PDFTools_Controller controller = new PDFTools_Controller(model, view);
        controller.updateView();
    }
}
