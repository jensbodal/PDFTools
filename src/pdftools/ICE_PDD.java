package pdftools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author jensb
 */
public class ICE_PDD extends PDDocument {
    
    /**
     *
     */
    public ICE_PDD() throws IOException {
        super();
    }
    
    public static PDDocument load(File file) throws IOException {
        return load(new FileInputStream(file));
    }
}
