package GUI;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class GUI extends JFrame {
	
	protected JPanel panelPrincipal;
	protected JTextField campo1, campo2;
	protected JButton operar, actualizar;
	protected JPopupMenu menuDesplegable;
	private JTextField resultado;

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
		
		inicializarGUI();
		inicializarPaneles();
		inicializarCamposDeTexto();
		inicializarBotones();
		inicializarMenuDesplegable();
		agregarComponentesAlPanel();
	}

	private void inicializarGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 200, 300);
	}

	private void inicializarPaneles() {
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(6,1));
		setContentPane(panelPrincipal);
	}

	private void inicializarCamposDeTexto() {
		
		campo1 = new JTextField();
		campo1.setText("Operando 1");
		
		campo2 = new JTextField();
		campo2.setText("Operando 2");
		
		resultado = new JTextField();
		resultado.setEditable(false);
	}
	

	private void inicializarBotones() {
		
		operar = new JButton("operar");
		actualizar = new JButton("actualizar");
		
	}	
	
	
	private void inicializarMenuDesplegable() {
		
		menuDesplegable = new JPopupMenu();
	}
	
	
	private void agregarComponentesAlPanel() {
		
		panelPrincipal.add(campo1);
		panelPrincipal.add(menuDesplegable);
		panelPrincipal.add(campo2);
		panelPrincipal.add(operar);
		panelPrincipal.add(resultado);
		panelPrincipal.add(actualizar);
	}
	
}
