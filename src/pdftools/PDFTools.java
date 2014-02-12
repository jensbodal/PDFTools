/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pdftools;

import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.Splitter;
import org.apache.pdfbox.PDFReader;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfviewer.PDFPagePanel;
import org.apache.pdfbox.pdfviewer.PageWrapper;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import java.awt.BorderLayout;
import java.awt.Font;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.ViewModel;
import org.icepdf.ri.util.PropertiesManager;



//http://www.icesoft.org/wiki/display/PDF/API+Documentation
/**
 *
 * @author jensb
 */
public class PDFTools 
{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, COSVisitorException 
    {
        
        Splitter splitPDF = new Splitter();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        
        File PDF_Path = new File("C:\\Users\\jensb\\Dropbox\\Java Libraries\\zProjectStuff\\PDF_Stuff\\12-13 Port Risk Binder.PDF");
        //File PDF_Path = new File("I:\\Dropbox\\Java Libraries\\zProjectStuff\\PDF_Stuff\\test.PDF");
        //File PDF_Path = new File("C:\\Users\\jensb\\Dropbox\\Java Libraries\\zProjectStuff\\PDF_Stuff\\test.PDF");
        
        //Begin ICEPDF
        // build a controller
        SwingController controller = new SwingController();
        
               
        
        // Build a SwingViewFactory configured with the controller
        // Optional settings to figure out:
        /*
         * SwingViewBuilder(SwingController c, java.awt.Font bf, boolean bt, int ts, float[] zl, int documentViewType, int documentPageFitMode) 
         * SwingViewBuilder(SwingController c, int documentViewType, int documentPageFitMode) 
         * ////*** DEFAULTS *** \\\\
         * this(c, null, null, false, SwingViewBuilder.TOOL_BAR_STYLE_FIXED, null,
                DocumentViewControllerImpl.ONE_PAGE_VIEW,
                DocumentViewController.PAGE_FIT_WINDOW_HEIGHT);
         * ///*** FINAL CONSTRUCTOR *** \\\
         *     public SwingViewBuilder(SwingController c, PropertiesManager properties,
                            Font bf, boolean bt, int ts,
                            float[] zl, final int documentViewType,
                            final int documentPageFitMode)
         */
        Float HALF = new Float(0.5);
        Font myFont = frame.getFont();
        boolean bt = false; //showButtonText
        int ts = 0; // toolbarStyle
        float[] zl = null; // zoomLevels
        int documentViewType = 1;
        int documentPageFitMode = 1;
        
        //SwingViewBuilder factory = new SwingViewBuilder(controller, myFont, bt, ts, zl, documentViewType, documentPageFitMode);
        Panel_Builder factory = new Panel_Builder(controller);
        // Use the factory to build a JPanel that is pre-configured
        //with a complete, active Viewer UI.
        
        JPanel viewerComponentPanel = factory.buildViewerPanel();
        
        // add copy keyboard command
        ComponentKeyBinding.install(controller, viewerComponentPanel);

        // add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
        new org.icepdf.ri.common.MyAnnotationCallback(
             controller.getDocumentViewController()));

        // Create a JFrame to display the panel in
        JFrame window = new JFrame("Using the Viewer Component");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(viewerComponentPanel);
        //window.getContentPane().add(viewerFrame);
        //window.add(viewerComponentPanel);
        window.pack();
        window.setVisible(true);
        // Open a PDF document to view
        controller.openDocument(PDF_Path.getAbsolutePath());
        
        
                
        
//        
//        List<PDPage> allPages = inputPDF.getDocumentCatalog().getAllPages();
//        PDPage testPage = (PDPage)allPages.get(0);
//        PDStream testStream = testPage.getContents();
        
        //BufferedImage testImage = testPage.convertToImage();
//        JFrame testFrame = new JFrame();
//        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        PDFPagePanel pdfPanel = new PDFPagePanel();
//        pdfPanel.setPage(testPage);
//        testFrame.add(pdfPanel);
//        testFrame.setBounds(40, 40, pdfPanel.getWidth(), pdfPanel.getHeight());
//        testFrame.setVisible(true);
        
//        List<PDDocument> list = splitPDF.split(inputPDF); // This creates a list of PDDocuments
//        System.out.println(list.size());
//        for (PDDocument page : list) {
//            List<PDPage> pageList = page.getDocumentCatalog().getAllPages();
//            PDPage thisPage = (PDPage)pageList.get(0);
//        }
        
        
        
//            PDPage thispage = new PDPage();
//            //thisPage = (PDPage)page;
//            StringBuilder newString = new StringBuilder();
//            newString.append(PDF_Path);
//            int length = newString.length();
//            newString.replace(length - 4, length, "");
//            newString.append(i);
//            newString.append(".PDF");
//            System.out.println(newString);
//            
//            //Implement Page viewer to choose when to split PDF
//            //Show page, choose to add or complete split and name file
//            //page.save(newString.toString());
//            i++;
//        }
        
 
        
        //frame.add(pagePanel);
        
        //frame.setVisible(true);
        
    }

}
