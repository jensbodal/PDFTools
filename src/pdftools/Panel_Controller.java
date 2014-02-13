package pdftools;

import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.icepdf.ri.common.SwingController;


/**
 *
 * @author jensb
 */
public class Panel_Controller extends SwingController {
   
    /**
     * Currently pointless to do this however testing that this is how to
     * add additional functionality to super methods
     * @param pathname pathname of file to open
     */
   @Override
   public void openDocument(String pathname) {
       super.openDocument(pathname);
   }
   
   public void openDocument(PDDocument pdf_file) {
       PDStream stream = new PDStream(pdf_file);
       InputStream inputStream = null;
       try {
           inputStream = stream.createInputStream();
       }
       catch (IOException e) {
           System.out.println("PDDocument error: " + e);
       }
       
       super.openDocument(inputStream, "", "");
   }
   
}
