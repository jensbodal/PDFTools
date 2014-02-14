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
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
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
    public static void main(String[] args) throws IOException {
        
        Splitter splitPDF = new Splitter();
                String PDF_Path = "/Users/akevit/Dropbox/Java Libraries/zProjectStuff/PDF_Stuff/12-13 Port Risk Binder.PDF";

        //String PDF_Path = "C:\\Users\\jensb\\Dropbox\\Java Libraries\\zProjectStuff\\PDF_Stuff\\12-13 Port Risk Binder.PDF";
        PDDocument.load(PDF_Path);
        File PDF_File = new File(PDF_Path);
        //File PDF_File = new File("I:\\Dropbox\\Java Libraries\\zProjectStuff\\PDF_Stuff\\test.PDF");
        //File PDF_File = new File("C:\\Users\\jensb\\Dropbox\\Java Libraries\\zProjectStuff\\PDF_Stuff\\test.PDF");
        //PDDocument inputPDF = PDDocument.load(PDF_File); 
        //testCustomDocument test;
        ICE_PDD test;
        PDDocument inputPDF = ICE_PDD.load(PDF_File);
        
        
        
//        //Begin ICEPDF
//        // build a controller
//        Panel_Controller controller = new Panel_Controller();
//       
//        Panel_Builder factory = new Panel_Builder(controller, false, false);
//        // Use the factory to build a JPanel that is pre-configured
//        //with a complete, active Viewer UI.
//        
//        // This is the JPanel which displays the PDF and menu bars
//        // Other option would be to use the viewerComponentFrame
//        JPanel viewerComponentPanel = factory.buildViewerPanel();
//        
//        JFrame viewerComponentFrame = factory.buildViewerFrame();
//        viewerComponentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        viewerComponentFrame.setBounds(factory.Get_X_BOUND(), factory.Get_Y_BOUND(), 
//                factory.Get_WIDTH(), factory.Get_HEIGHT());
//        viewerComponentFrame.pack();
//        viewerComponentFrame.setVisible(true);
//        // add copy keyboard command
//        // this adds control/copy ability
//        ComponentKeyBinding.install(controller, viewerComponentPanel);
//
//        // add interactive mouse link annotation support via callback
//        // have not figured out what this does
//        controller.getDocumentViewController().setAnnotationCallback(
//        new org.icepdf.ri.common.MyAnnotationCallback(
//             controller.getDocumentViewController()));
//
//        // Create a JFrame to display the panel in
//        JFrame frame = new JFrame();
//        //frame.setTitle(factory.Get_Title());
//        frame.setJMenuBar(factory.buildCompleteMenuBar());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(viewerComponentPanel);
//        frame.setBounds(factory.Get_X_BOUND(), factory.Get_Y_BOUND(), 
//                factory.Get_WIDTH(), factory.Get_HEIGHT());
//        frame.pack();
//        //frame.setVisible(true);
//        // Open a PDF document to view
//        //controller.openDocument(inputPDF);
//        
//        controller.openDocument(PDF_Path);
//        
        
                
        
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
        
        List<PDDocument> list = splitPDF.split(inputPDF); // This creates a list of PDDocuments
        System.out.println(list.size());
        for (PDDocument page : list) {
            List<PDPage> pageList = page.getDocumentCatalog().getAllPages();
           PDPage thisPage = (PDPage)pageList.get(0);
        }
        
        inputPDF.close();
        
//            PDPage thispage = new PDPage();
//            //thisPage = (PDPage)page;
//            StringBuilder newString = new StringBuilder();
//            newString.append(PDF_File);
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
