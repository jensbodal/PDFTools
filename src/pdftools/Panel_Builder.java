package pdftools;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.text.AbstractDocument;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.views.DocumentViewController;
import org.icepdf.ri.common.views.AbstractDocumentView;
import org.icepdf.ri.images.Images;
import org.icepdf.ri.util.PropertiesManager;

/**
 *
 * @author jensb
 */
public class Panel_Builder extends SwingViewBuilder {
    
    private int PANEL_X;
    private int PANEL_Y;
    private int PANEL_WIDTH = 800;
    private int PANEL_HEIGHT = 800;
    private boolean disableToolBar = false;
    private boolean disableStatusPanel = false;
    
    public void SetBounds() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screen_width = (int)screenSize.getWidth();
        int screen_height = (int)screenSize.getHeight();
        PANEL_X = (screen_width-PANEL_WIDTH) - 20;
        PANEL_Y = (screen_height - PANEL_HEIGHT) - 100;
    }
    
    public int Get_X_BOUND() {
        return PANEL_X;
    }
    
    public int Get_Y_BOUND() {
        return PANEL_Y;
    }
    
    public int Get_WIDTH() {
        return PANEL_WIDTH;
    }
    
    public int Get_HEIGHT() {
        return PANEL_HEIGHT;
    }
    
    public String Get_Title() {
        System.out.println(super.messageBundle.getString("this.window.title.default"));
        return messageBundle.getString("viewer.window.title.default");
        
    }
    
    public Panel_Builder(Panel_Controller c, 
            boolean disableToolBar, boolean disableStatusPanel) {
        super(c);
        this.disableToolBar = disableToolBar;
        this.disableStatusPanel = disableStatusPanel;
    }
    
    @Override
    /**
     * This is a standard method for creating a standalone JFrame, that would
     * behave as a fully functional PDF Viewer application.
     *
     * @return a JFrame containing the PDF document's current page visualization,
     *         menu bar, accelerator buttons, and document outline if available.
     * @see #buildViewerPanel
     */
    public JFrame buildViewerFrame() {
        JFrame viewer = new JFrame();
        viewer.setIconImage(new ImageIcon(Images.get("icepdf-app-icon-64x64.png")).getImage());
        viewer.setTitle(messageBundle.getString("viewer.window.title.default"));
        viewer.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        JMenuBar menuBar = buildCompleteMenuBar();
        if (menuBar != null)
            viewer.setJMenuBar(menuBar);
        Container contentPane = viewer.getContentPane();
        buildContents(contentPane, false);
        //viewerController is a SwingController
        if (viewerController != null)
            viewerController.setViewerFrame(viewer);
        return viewer;
    }
    
    @Override
    /**
     * Used by the Applet and Pilot RI code to create an embeddable panel
     * for viewing PDF files, as opposed to buildViewerFrame(), which makes a
     * standalone JFrame
     *
     * @return JPanel containing the PDF document's current page visualization,
     *         menu bar, accelerator buttons, and document outline if available.
     * @see #buildViewerFrame
     */
    public JPanel buildViewerPanel() {
        JPanel panel = new JPanel();
        SetBounds();
        buildContents(panel, true);
        panel.setPreferredSize(new Dimension(800, 800));
        return panel;
    }
    
    @Override
    /**
     * The Container will contain the PDF document's current page visualization
     * and document outline if available.
     * 
     * @param cp Container in which to put components for viewing PDF documents
     * @Override check to disable toolbar and status panel
     */
    public void buildContents(Container cp, boolean embeddableComponent) {
        cp.setLayout(new BorderLayout());
        JToolBar toolBar;
        if (disableToolBar) {
            toolBar = null;
        }
        else {
            toolBar = buildCompleteToolBar(embeddableComponent);
        }
        if (toolBar != null)
            cp.add(toolBar, BorderLayout.NORTH);
        // Builds the utility pane as well as the main document View, important
        // code entry point.
        JSplitPane utilAndDocSplit =
                buildUtilityAndDocumentSplitPane(embeddableComponent);
        if (utilAndDocSplit != null)
            cp.add(utilAndDocSplit, BorderLayout.CENTER);

        JPanel statusPanel;
        if (disableStatusPanel) {
            statusPanel = null;
        }
        else {
            statusPanel = buildStatusPanel();
        }
        if (statusPanel != null)
            cp.add(statusPanel, BorderLayout.SOUTH);
    }
    
    public JPanel documentPage(boolean embeddableComponent) {
        JPanel panel = new JPanel();
        
        
        DocumentViewController viewController = 
                viewerController.getDocumentViewController();
        viewerController.setIsEmbeddedComponent(embeddableComponent);
        
        
        panel.add(viewController.getViewContainer());
        
        return panel;
    }
    
    @Override
    public JSplitPane buildUtilityAndDocumentSplitPane(boolean embeddableComponent) {
        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitpane.setOneTouchExpandable(false);
        splitpane.setDividerSize(8);
        splitpane.setContinuousLayout(true);
        // set the utility pane the left of the split pane
        splitpane.setLeftComponent(buildUtilityTabbedPane());

        // set the viewController embeddable flag.
        DocumentViewController viewController =
                viewerController.getDocumentViewController();
        
        AbstractDocumentView jens;
        // will add key event listeners
        viewerController.setIsEmbeddedComponent(embeddableComponent);

        // remove F6 focus management key from the splitpane
        splitpane.getActionMap().getParent().remove("toggleFocus");

        // add the viewControllers doc view container to the split pain
        splitpane.setRightComponent(viewController.getViewContainer());

        // apply previously set divider location, default is -1
        int dividerLocation = PropertiesManager.checkAndStoreIntegerProperty(
                propertiesManager,
                PropertiesManager.PROPERTY_DIVIDER_LOCATION, 260);
        
        splitpane.setDividerLocation(dividerLocation);

        // Add the split pane component to the view controller so that it can
        // manipulate the divider via the controller, hide, show, etc. for
        // utility pane.
        if (viewerController != null) {
            viewerController.setUtilityAndDocumentSplitPane(splitpane);
        }
        System.out.println(viewerController.getDocument());
        return splitpane;
    }
}
