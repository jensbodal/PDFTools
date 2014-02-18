package pdftools;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author jensb
 */
public class PDFTools_Model {
    
    private String PDF_Path;
    private PDDocument inputPDF;
    private PDPage inputPage;
/*
 * The model is the meat of the program, it does all the actual calculations
 * and such
 */
    PDFTools_Model() {
        setPath();
        setFile(this.PDF_Path);
        setPage();
    }
    
    private void setPath() {
        String pathWork = "C:\\Users\\jensb\\Dropbox\\Java Libraries\\zProjectStuff\\PDF_Stuff\\12-13 Port Risk Binder.PDF";
        //String pathHome = "I:\\Dropbox\\Java Libraries\\zProjectStuff\\PDF_Stuff\\test.PDF";
        String pathHome = "I:\\Dropbox\\Java Libraries\\zProjectStuff\\PDF_Stuff\\12-13 Port Risk Binder.PDF";//
        String path_OSX = "/Users/akevit/Dropbox/Java Libraries/zProjectStuff/PDF_Stuff/12-13 Port Risk Binder.PDF";
        
        if (new File(pathWork).exists()) {
            this.PDF_Path = pathWork;
        }
        else if (new File(pathHome).exists()) {
            this.PDF_Path = pathHome;
        }
        else if (new File(path_OSX).exists()) {
            this.PDF_Path = path_OSX;
        }
        else {
            JOptionPane.showMessageDialog(null, "Cannot set PDF_Path");
            System.exit(0);
        }
        System.out.println(PDF_Path);
    }
    
    public String getPath() {
        return this.PDF_Path;
    }
    
    private void setFile(String path) {
        File PDF_File = new File(path);
        try {
            this.inputPDF = PDDocument.load(PDF_File);
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setPage() {
        List<PDPage> allPages = 
                this.inputPDF.getDocumentCatalog().getAllPages();
        this.inputPage = (PDPage)allPages.get(0);
    }
    
    public PDPage getPage() {
        return this.inputPage;
    }
    
    public PDDocument getPDF() {
        return this.inputPDF;
    }
    
    public void Destroy() {
        try { 
            this.inputPDF.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
