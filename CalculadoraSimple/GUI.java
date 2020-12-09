package CalculadoraSimple;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import java.awt.BorderLayout;

public class GUI extends JFrame {
	
	protected JPanel panelPrincipal, panel1, panel2, panel3, panel4;
	protected JTextField operando1, operando2;
	protected JButton operar, actualizar;
	protected JComboBox menuDesplegable;
	protected JTextField resultado;
	protected PluginDemo pd;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI() {
		
		inicializarPluginDemo();
		inicializarGUI();
		inicializarPaneles();
		inicializarMenuDesplegable();
		inicializarCamposDeTexto();
		inicializarBotones();
		agregarComponentesAlPanel();
		
	}


	private void inicializarPluginDemo() {
		
		pd = new PluginDemo();
		buscarPlugs();
		
	}

	private void inicializarGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 400, 400);
	}

	private void inicializarPaneles() {
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(5,1));
		setContentPane(panelPrincipal);
		
		
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(4, 1));
		
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		
		
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
	
		
		panel4 = new JPanel();
		panel4.setLayout(new FlowLayout());
		
		
		panelPrincipal.add(panel1);
		panelPrincipal.add(panel2);
		panelPrincipal.add(panel3);
		panelPrincipal.add(panel4);
	}

	private void inicializarCamposDeTexto() {
		
		operando1 = new JTextField ();
		operando1.setText("Operando 1");
		
		operando2 = new JTextField ();
		operando2.setText("Operando 2");
		
		resultado = new JTextField();
		resultado.setText("resultado");
		resultado.setEditable(false);
	}
	
	private void inicializarBotones() {
		
		operar = new JButton("operar");
		operar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int indiceDesplegable = menuDesplegable.getSelectedIndex();
				
				PluginFunction funcion = pd.getLista().get(indiceDesplegable);
				
				operar(funcion, obtenerOperando(operando1), obtenerOperando(operando2));
				
			}});
		
		
		actualizar = new JButton("actualizar");
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				buscarPlugs();
				menuDesplegable.removeAllItems();
				agregarElementos(menuDesplegable);
				repaint();
			}});
		
	}	
	
	private void inicializarMenuDesplegable() {
		
		menuDesplegable = new JComboBox();
		
		agregarElementos(menuDesplegable);
		
		
	}
	
	

	private void agregarComponentesAlPanel() {
		
		panel1.add(operando1);
		panel1.add(operando2);
		panel2.add(menuDesplegable);
		panel2.add(operar);
		panel3.add(resultado);
		panel4.add(actualizar);
	}
	
	private int obtenerOperando(JTextField o) {
		int toReturn;
		try {
	        toReturn = Integer.parseInt(o.getText());
	        System.out.println(""+toReturn);
	    } catch (NumberFormatException nfe) {
	        o.setText("");
	        JOptionPane.showMessageDialog(null,"Ingresa solo numeros enteros","Error",JOptionPane.ERROR_MESSAGE);
	        return 0;
	    }

		return toReturn; 
	}
	
	private void operar(PluginFunction funcion, int o1, int o2) {
		
		funcion.setParameter(o1, o2);
		String cadena = Float.toString(funcion.getResult());
		resultado.setText(cadena);
		
	}
	

	private void buscarPlugs() {
		pd.getPlugins();
	}


	private void agregarElementos(JComboBox menuDesplegable) {
		
		for (PluginFunction c : pd.getLista()) { 
			System.out.println(c.getPluginName());
			menuDesplegable.addItem(c.getPluginName());
		}
	}
}
