import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;


public class AlgoFrame extends JFrame {
  private final int canvasWidth;
  private final int canvasHeight;

  
  public AlgoFrame (final String title, final int canvasWidth, final int canvasHeight ) {
    super(title);
    this.canvasHeight = canvasHeight;
    this.canvasWidth = canvasWidth;
    AlogCanvas canvas = new AlogCanvas();
    setContentPane(canvas);
    pack();

    setSize(canvasWidth, canvasHeight);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setVisible(true);
  }
  public AlgoFrame (final String title){
    this(title, 1024,768);
  }


  public int getCanvasWidth() {
    return canvasWidth;
  }
  
  public int getCanvasHeight() {
    return canvasHeight;
  }
  private MergeSortData data;

  public void render(MergeSortData sortedData) {
    data = sortedData;
    this.repaint();
  }
  public class AlogCanvas extends JPanel {
    public AlogCanvas(){
      // 双缓存
      super(true);
    }
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;
      // anti-alising
      RenderingHints hints = new RenderingHints(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON
      );
      hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

      g2d.addRenderingHints(hints);
      int w = (int)(canvasWidth / data.N());
   
      
      for(int i = 0; i<data.N(); i++){
        AlgoVizHelper.setColor(g2d, AlgoVizHelper.Grey);
        if(i>=data.l&&i<=data.r){
          AlgoVizHelper.setColor(g2d, AlgoVizHelper.Green);
        }
        if(i>=data.l&&i<=data.mergeIndex)
          AlgoVizHelper.setColor(g2d, AlgoVizHelper.Red);
        AlgoVizHelper.fillRectangle(g2d, i*w, canvasHeight-data.get(i), w-1, data.get(i));
      }
    }
    @Override
    public Dimension getPreferredSize() {
      return new Dimension(canvasWidth,canvasHeight);
    }
  }
}
