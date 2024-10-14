package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controlador.Controlador;
import modelo.Modelo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class _00_InicioAplicacion extends JFrame implements Vista {

	private Controlador miControlador;
	private Modelo miModelo;
	private JLabel lblTitulo;
	private JButton btnVisualizarDatos;
	private JButton btnAgregarRegistro;

	@Override
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	@Override
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public _00_InicioAplicacion() {
		

		setResizable(false);
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);

		lblTitulo = new JLabel("Gestor de Base de Datos");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTitulo.setBounds(221, 58, 443, 130);
		getContentPane().add(lblTitulo);

		btnAgregarRegistro = new JButton("Añadir Usuario");
		btnAgregarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarVentana(0, 1);
			}
		});
		btnAgregarRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAgregarRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnAgregarRegistro.setBackground(new Color(150, 150, 150));
			}
		});
		btnAgregarRegistro.setBackground(new Color(192, 192, 192));
		btnAgregarRegistro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAgregarRegistro.setBounds(179, 267, 184, 54);
		btnAgregarRegistro.setBorder(null);
		getContentPane().add(btnAgregarRegistro);

		btnVisualizarDatos = new JButton("Visualizar Datos");
		btnVisualizarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarVentana(0, 2);
			}
		});
		btnVisualizarDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVisualizarDatos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnVisualizarDatos.setBackground(new Color(150, 150, 150));
			}
		});
		btnVisualizarDatos.setBackground(new Color(192, 192, 192));
		btnVisualizarDatos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVisualizarDatos.setBounds(515, 267, 184, 54);
		btnVisualizarDatos.setBorder(null);
		getContentPane().add(btnVisualizarDatos);
		setVisible(true);
		
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVisualizarDatos.setBackground(new Color(192, 192, 192));
				btnAgregarRegistro.setBackground(new Color(192, 192, 192));
			}
		});

	}
}
