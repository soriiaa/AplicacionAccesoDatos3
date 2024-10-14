package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controlador.Controlador;
import modelo.Modelo;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class _01_AgregarRegistros extends JFrame implements Vista {

	private Controlador miControlador;
	private Modelo miModelo;
	private JTextField txtNombreUsuario;
	private JLabel lblTitulo;
	private JTextField txtApellidoUsuario;
	private JLabel lblApellido;
	private JLabel lblInstrucciones;
	private JLabel lblNombre;
	private JButton btnAnadir;
	private JLabel lblResultadoInsert;
	private JLabel lblVolver;

	@Override
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	@Override
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public _01_AgregarRegistros() {

		setResizable(false);
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Añadir Registro");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitulo.setBounds(308, 57, 270, 50);
		getContentPane().add(lblTitulo);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				activarBoton();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				activarBoton();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				activarBoton();
			}
		});
		txtNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNombreUsuario.setBounds(273, 223, 339, 30);
		txtNombreUsuario.setBorder(BorderFactory.createCompoundBorder(txtNombreUsuario.getBorder(),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		getContentPane().add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		
		txtApellidoUsuario = new JTextField();
		txtApellidoUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				activarBoton();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				activarBoton();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				activarBoton();
			}
		});
		txtApellidoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtApellidoUsuario.setBounds(273, 316, 339, 30);
		txtApellidoUsuario.setBorder(BorderFactory.createCompoundBorder(txtApellidoUsuario.getBorder(),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		getContentPane().add(txtApellidoUsuario);
		txtApellidoUsuario.setColumns(10);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(204, 231, 59, 13);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellido.setBounds(205, 324, 58, 13);
		getContentPane().add(lblApellido);
		
		lblInstrucciones = new JLabel("Añadir un Usuario a la Base de Datos");
		lblInstrucciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInstrucciones.setBounds(273, 142, 339, 25);
		getContentPane().add(lblInstrucciones);
		
		lblResultadoInsert = new JLabel("Usuario añadido con éxito");
		lblResultadoInsert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResultadoInsert.setBounds(356, 389, 173, 13);
		lblResultadoInsert.setVisible(false);
		getContentPane().add(lblResultadoInsert);
		setVisible(false);
		
		btnAnadir = new JButton("Añadir Usuario");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean respuesta = miControlador.anadirUsuario(txtNombreUsuario.getText(), txtApellidoUsuario.getText());
				if (respuesta) {
					limpiarCampos();
					lblResultadoInsert.setText("Usuario añadido con éxito");
					lblResultadoInsert.setVisible(true);
					btnAnadir.setEnabled(false);
				} else {
					lblResultadoInsert.setText("         Error");
					lblResultadoInsert.setVisible(true);
				}
			}
		});
		btnAnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnAnadir.isEnabled()) {
					btnAnadir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					btnAnadir.setBackground(new Color(70, 70, 70));
				}
			}
		});
		btnAnadir.setBackground(new Color(0, 0, 0));
		btnAnadir.setForeground(new Color(255, 255, 255));
		btnAnadir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnadir.setBounds(368, 430, 149, 37);
		btnAnadir.setEnabled(false);
		btnAnadir.setBorder(null);
		getContentPane().add(btnAnadir);
		
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAnadir.setBackground(new Color(0, 0, 0));
			}
		});
		
		lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(".\\adjuntos\\flechaAtras.png"));
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarCampos();
				btnAnadir.setEnabled(false);
				miControlador.cambiarVentana(1, 0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		lblVolver.setBounds(817, 11, 61, 44);
		getContentPane().add(lblVolver);
		
	}
	
	public void activarBoton() {
		
		if (txtNombreUsuario.getText().equals("") || txtApellidoUsuario.getText().equals("")) {
			btnAnadir.setEnabled(false);
		} else {
			btnAnadir.setEnabled(true);
		}
		
	}
	
	public void limpiarCampos() {
		txtNombreUsuario.setText("");
		txtApellidoUsuario.setText("");
	}
}
