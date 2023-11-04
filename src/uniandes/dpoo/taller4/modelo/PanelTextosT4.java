package uniandes.dpoo.taller4.modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;



public class PanelTextosT4 extends JPanel
{
	private JTextArea txtArea;
	private DefaultListModel<Click> modelo;
	
	public PanelTextosT4()
	{
		setLayout(new GridLayout(2,1));
		JPanel panelArriba = new JPanel(new BorderLayout());
		panelArriba.setBorder(new CompoundBorder(new TitledBorder("Tamaño"), new LineBorder(Color.BLUE)));
		add(panelArriba);
		
		txtArea = new JTextArea();
		panelArriba.add(txtArea);
		
		modelo = new DefaultListModel<Click>();
		JList<Click> listaDatos = new JList<Click>(modelo);
		
		JScrollPane scroll = new JScrollPane(listaDatos);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroll);
		
	}
		public void agregarTexto(String nuevoTexto)
		{
			txtArea.setText(txtArea.getText() + "\n" + nuevoTexto);
		}

		public void agregarClick(Click numero)
		{
			modelo.addElement(numero);
		}
	
	
	
}
