package doodle;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * --> Main drawing panel responsible for generating random doodle lines.
 */
public class DoodleDrawingSystem extends JPanel {

    // ==========================
    // ======= CONSTANTS ========
    // ==========================

    private static final int LINES_PER_FRAME = 1;           // --> Number of lines generated per drag event
    private static final int MIN_LENGTH      = 0;           // --> Minimum random line length
    private static final int MAX_LENGTH      = 1;           // --> Maximum random line length
    private static final int ERASER_RADIUS   = 30;          // --> Radius used to erase nearby lines

    // ==========================
    // ======== FIELDS ==========
    // ==========================

    private final   List<Linha> lines;    // --> Stores all drawn lines
    private final   Random random;        // --> Random generator
    private boolean eraserMode;           // --> Controls whether eraser mode is active
    private boolean mousePressed;         // --> Tracks if mouse is currently pressed

    /**
     * --> Constructor initializes panel configuration and events.
     */
    public DoodleDrawingSystem() {

        this.lines          = new ArrayList<>();
        this.random         = new Random();
        this.eraserMode     = false;
        this.mousePressed   = false;

        setBackground(Color.WHITE); 
        setFocusable(true);         

        configureEvents();
    }

    /**
     * --> Configures mouse and keyboard events.
     */
    private void configureEvents() {

        // --> Detects mouse press and release
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
            }
        });

        // --> Detects mouse drag movement
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                if (mousePressed) {

                    if (eraserMode) {
                        eraseNearby(e.getX(), e.getY());
                    } else {
                        generateLines(e.getX(), e.getY());
                    }

                    repaint(); 
                }
            }
        });

        // --> Detects keyboard input
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    eraserMode = !eraserMode; 
                }

                if (e.getKeyCode() == KeyEvent.VK_C) {
                    lines.clear();
                }

                repaint();
            }
        });
    }

    /**
     * --> Generates loose circular scribble lines from a given point.
     */
    private void generateLines(int x, int y) {

        for (int i = 0; i < LINES_PER_FRAME; i++) {

            int     length          = MIN_LENGTH + random.nextInt(MAX_LENGTH - MIN_LENGTH + 1);
            double  angle           = random.nextDouble() * 2 * Math.PI;           // --> Random angle
            double  circularOffset  = random.nextDouble() * 15;                   // --> Add slight circular drift

            int x2 = x + (int) (Math.cos(angle) * length + circularOffset * Math.cos(angle));
            int y2 = y + (int) (Math.sin(angle) * length + circularOffset * Math.sin(angle));

            lines.add(new Linha(x, y, x2, y2));
        }
    }

    /**
     * --> Removes lines close to the specified coordinates.
     */
    private void eraseNearby(int x, int y) {

        lines.removeIf(line ->
                Math.hypot(line.getX1() - x, line.getY1() - y) < ERASER_RADIUS ||
                Math.hypot(line.getX2() - x, line.getY2() - y) < ERASER_RADIUS
        );
    }

    /**
     * --> Responsible for rendering everything on screen.
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // --> Enables smooth rendering
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2d.setColor(Color.BLACK); 

        // --> Draw stored lines
        for (Linha line : lines) {
            g2d.drawLine(
                    line.getX1(),
                    line.getY1(),
                    line.getX2(),
                    line.getY2()
            );
        }

        // --> UI information
        g2d.setColor(Color.GRAY);
        g2d.drawString("Hold mouse to draw | Q - Eraser | C - Clear", 10, 20);

        if (eraserMode) {
            g2d.setColor(Color.RED);
            g2d.drawString("Eraser Mode ACTIVE", 10, 40);
        }
    }
}