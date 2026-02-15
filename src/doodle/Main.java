package doodle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    private static final String TITLE     = ":)"; 
    private static final int    WIDTH     = 1080;
    private static final int    HEIGHT    = 950;

    public static void main(String[] args) {
         
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame(TITLE);
            DoodleDrawingSystem painel = new DoodleDrawingSystem();

            frame.add(painel);                         
            frame.setSize(WIDTH, HEIGHT);                   
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);         
            frame.setVisible(true);                    

            painel.requestFocusInWindow();             
        });
    }
}
