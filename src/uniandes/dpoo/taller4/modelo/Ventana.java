package uniandes.dpoo.taller4.modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Ventana extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	private Tablero tablero;
    private JButton[][] buttons;
    private JLabel jugadasLabel;
    
	public Ventana()
	{
		setSize(800,700);
		setTitle("Lights Out");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		int tamanoTablero = 5; // Tamaño del tablero
		
		tablero = new Tablero(tamanoTablero);
		createBoard(tamanoTablero);

        JButton nuevoJuegoButton = new JButton("Nuevo Juego");
        nuevoJuegoButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                tablero.desordenar(20); // Desordena el tablero
                actualizarTablero();
            }
        });
        
	}

        
        private void createBoard(int tamano) 
        {
            JPanel boardPanel = new JPanel();
            boardPanel.setLayout(new GridLayout(tamano, tamano));
            buttons = new JButton[tamano][tamano];

            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    buttons[i][j] = new JButton();
                    buttons[i][j].setBackground(Color.BLUE); // Configura el fondo de las casillas como amarillo
                    final int row = i;
                    final int col = j;
                    buttons[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            tablero.jugar(row, col);
                            actualizarTablero();
                        }
                    });
                    boardPanel.add(buttons[i][j]);
                }
            }

            add(boardPanel, BorderLayout.CENTER);
        }
        
        
        private void actualizarTablero() {
            boolean[][] estadoTablero = tablero.darTablero();

            for (int i = 0; i < estadoTablero.length; i++) {
                for (int j = 0; j < estadoTablero[i].length; j++) {
                    buttons[i][j].setBackground(estadoTablero[i][j] ? Color.YELLOW : Color.BLACK);
                }
            }

            jugadasLabel.setText("Jugadas: " + tablero.darJugadas());

            if (tablero.tableroIluminado()) {
                int puntaje = tablero.calcularPuntaje();
                String nombre = JOptionPane.showInputDialog("¡Has ganado! Ingresa tu nombre (3 caracteres):");
                nombre = nombre != null && nombre.length() >= 3 ? nombre.substring(0, 3) : "AAA";

                Top10 top10 = new Top10();
                top10.cargarRecords(new File("top10.txt"));

                if (top10.esTop10(puntaje)) {
                    top10.agregarRegistro(nombre, puntaje);
                    try {
                        top10.salvarRecords(new File("top10.txt"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(this, "Tu puntaje: " + puntaje);

                tablero.desordenar(20); // Comenzar un nuevo juego
                actualizarTablero();
            }
        }

        public static void main(String[] args)
        {
                    new Ventana();
        }
            
                
    
}
