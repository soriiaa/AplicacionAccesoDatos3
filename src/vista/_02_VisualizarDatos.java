package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Modelo;

public class _02_VisualizarDatos extends JFrame implements Vista {

	private Controlador miControlador;
	private Modelo miModelo;
	private JTable tableUsuarios;
	private JLabel lblTItulo;
	private JScrollPane scrollPane;
	private JTable table;
	private Object[][] data;

	@Override
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	@Override
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public _02_VisualizarDatos() {

		setResizable(false);
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);

		

		lblTItulo = new JLabel("Visualizar Datos");
		lblTItulo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTItulo.setBounds(300, 36, 284, 69);
		getContentPane().add(lblTItulo);
		setVisible(false);
		
		String[] columnNames = { "ID", "Nombre", "Edad" };
		data = new Object[][]{ { 1, "Alejandro", 19 }, { 2, "Aitor", 20 }, { 3, "María", 18 } };

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//data = new Object[][]{ { 1, "Alejandro", 19 }, { 2, "Aitor", 20 }, { 3, "María", 18 }, { 3, "María", 18 } };
			}
		});
		
		

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 115, 800, 283);
		getContentPane().add(scrollPane);
		
	}
}
