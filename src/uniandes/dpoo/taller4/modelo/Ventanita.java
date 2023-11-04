package uniandes.dpoo.taller4.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Ventanita extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;
    private boolean[][] lights;
    private JLabel clickLabel;
    private int clickCount;
    private BufferedImage lightImage;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Lights Out!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Ventanita panel = new Ventanita();
        panel.setPreferredSize(new Dimension(500, 500));

        
        JPanel counterPanel = new JPanel();
        counterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.clickLabel = new JLabel("Clics: " + panel.clickCount);
        counterPanel.add(panel.clickLabel);

        Container c = frame.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(panel, BorderLayout.CENTER);
        c.add(counterPanel, BorderLayout.SOUTH); 

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Ventanita() {
        lights = new boolean[5][5];
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                lights[i][j] = random.nextBoolean();
            }
        }

        clickCount = 0;

        
        try {
            lightImage = ImageIO.read(new File("data/luz.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        setLayout(new BorderLayout());

        
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int anchoCaja = getWidth() / 5;
        int altoCaja = getHeight() / 5;
        int columna = mouseX / anchoCaja;
        int fila = mouseY / altoCaja;
        toggle(fila, columna);
        repaint();

        clickCount++;
        clickLabel.setText("Clics: " + clickCount);

        
        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "¡Has ganado!");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoCaja = getWidth() / 5;
        int altoCaja = getHeight() / 5;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (lights[i][j]) {
                    g.setColor(Color.YELLOW); 
                    g.fillRect(j * anchoCaja, i * altoCaja, anchoCaja, altoCaja);
                    
                    g.drawImage(lightImage, j * anchoCaja, i * altoCaja, anchoCaja, altoCaja, this);
                } else {
                    g.setColor(Color.BLACK); 
                    g.fillRect(j * anchoCaja, i * altoCaja, anchoCaja, altoCaja);
                }
                g.setColor(Color.BLUE);
                g.drawRect(j * anchoCaja, i * altoCaja, anchoCaja, altoCaja);
            }
        }
    }

    public void toggle(int fila, int columna) {
        if (fila >= 0 && columna >= 0 && fila < lights.length && columna < lights[0].length) {
            lights[fila][columna] = !lights[fila][columna];
            toggleAdjacent(fila - 1, columna);
            toggleAdjacent(fila + 1, columna);
            toggleAdjacent(fila, columna - 1);
            toggleAdjacent(fila, columna + 1);
        }
    }

    public void toggleAdjacent(int fila, int columna) {
        if (fila >= 0 && columna >= 0 && fila < lights.length && columna < lights[0].length) {
            lights[fila][columna] = !lights[fila][columna];
        }
    }

    
    public boolean checkWin() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!lights[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}















