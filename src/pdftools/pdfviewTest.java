package pdftools;
/**
 *
 * @author jensb
 */
public class pdfviewTest {
    //PDFFile file = new PDFFile();
    //PDFObject pdfO = new PDFObject();
}
//        Splitter splitPDF = new Splitter();
//
//        
//        
//        
//        
//        
//        PDFPagePanel panel = new PDFPagePanel();
//        JFrame frame = new JFrame();
//        WindowAdapter adapter = new WindowAdapter() {
//            public void asdf(PDPage page) {
//                
//            }
//            
//            @Override
//            public void windowActivated(WindowEvent we) {
//                System.out.println("Activated");
//            }
//        };
//        frame.addWindowListener(adapter);
//        frame.setVisible(true);

        
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