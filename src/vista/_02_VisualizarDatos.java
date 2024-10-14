package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Modelo;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class _02_VisualizarDatos extends JFrame implements Vista {

	private Controlador miControlador;
	private Modelo miModelo;
	private JTable tableUsuarios;
	private JLabel lblTItulo;
	private JScrollPane scrollPane;
	private JTable table;
	private Object[][] data;
	private JLabel lblVolver;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblEliminarResultado;
	private JLabel lblModificadoResultado;
	private JButton btnRecargar;

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

		String[] columnNames = { "ID", "Nombre", "Apellido" };

		DefaultTableModel model = new DefaultTableModel(new String[0][0], columnNames);
		table = new JTable(model);

		scrollPane = new JScrollPane(table);
		
		scrollPane.setBounds(50, 115, 510, 400);
		getContentPane().add(scrollPane);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String[][] data = miControlador.recogerRegistros();
				DefaultTableModel model = new DefaultTableModel(data, columnNames) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setModel(model);
			}
		});

		lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(".\\adjuntos\\flechaAtras.png"));
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetearCampos();
				miControlador.cambiarVentana(2, 0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		lblVolver.setBounds(817, 11, 61, 44);
		getContentPane().add(lblVolver);
		
		btnModificar = new JButton("Modificar");
		
		btnModificar.setBounds(760, 292, 96, 33);
		btnModificar.setBorder(null);
		btnModificar.setEnabled(false);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		
		btnEliminar.setBounds(760, 408, 96, 33);
		btnEliminar.setBorder(null);
		getContentPane().add(btnEliminar);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				comprobarModificar();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				comprobarModificar();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				comprobarModificar();
			}
		});
		txtNombre.setBounds(710, 210, 146, 19);
		txtNombre.setText("");
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(710, 249, 146, 19);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		txtApellido.setText("");
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				comprobarModificar();
			}
			@Override
			public void keyReleased(KeyEvent e) {
				comprobarModificar();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				comprobarModificar();
			}
		});
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(648, 213, 52, 13);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(648, 252, 52, 13);
		getContentPane().add(lblApellido);
		
		lblEliminarResultado = new JLabel("Eliminado con Éxito");
		lblEliminarResultado.setBounds(724, 451, 138, 13);
		lblEliminarResultado.setVisible(false);
		getContentPane().add(lblEliminarResultado);
		
		lblModificadoResultado = new JLabel("Modificado con Éxito");
		lblModificadoResultado.setBounds(724, 335, 140, 13);
		lblModificadoResultado.setVisible(false);
		getContentPane().add(lblModificadoResultado);
		
		btnRecargar = new JButton("Recargar");
		
		btnRecargar.setBounds(50, 72, 85, 21);
		btnRecargar.setBorder(null);
		getContentPane().add(btnRecargar);
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int filaSeleccionada = table.getSelectedRow();
				String id = "";

				if (filaSeleccionada != -1) {
				    id = table.getValueAt(filaSeleccionada, 0).toString();
				}
				
				String nuevoNombre = txtNombre.getText();
				String nuevoApellido = txtApellido.getText();
				
				boolean resultado = miControlador.editarRegistro(id, nuevoNombre, nuevoApellido);
				
				if (resultado) {
					lblModificadoResultado.setText("Modificado con Éxito");
					lblModificadoResultado.setVisible(true);
					resetearCampos();
				} else {
					lblModificadoResultado.setText("   Error papichulo");
					lblModificadoResultado.setVisible(true);
				}
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int filaSeleccionada = table.getSelectedRow();
				String id = "";

				if (filaSeleccionada != -1) {
				    id = table.getValueAt(filaSeleccionada, 0).toString();
				}
				
				int resultado = miControlador.eliminarRegistro(id);
				
				if (resultado != -1) {
					lblEliminarResultado.setText("Eliminado con Éxito");
					lblEliminarResultado.setVisible(true);
				} else {
					lblEliminarResultado.setText("      Error");
					lblEliminarResultado.setVisible(true);
				}
			}
		});
		
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				comprobarEliminar();
				comprobarModificar();
			}
		});
		
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				comprobarEliminar();
			}
		});
		
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] data = miControlador.recogerRegistros();
				DefaultTableModel model = new DefaultTableModel(data, columnNames) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setModel(model);
			}
		});

	}
	
	public void resetearCampos() {
		txtNombre.setText("");
		txtApellido.setText("");
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		lblEliminarResultado.setVisible(false);
		lblModificadoResultado.setVisible(false);
	}
	
	public void comprobarEliminar() {
		if (table.getSelectedRow() == -1) {
			btnEliminar.setEnabled(false);
		} else {
			btnEliminar.setEnabled(true);
		}
	}
	
	public void comprobarModificar() {
		
		if ((txtNombre.getText().equals("")) || (txtApellido.getText().equals("")) || (table.getSelectedRow() == -1)) {
			btnModificar.setEnabled(false);
		} else {
			btnModificar.setEnabled(true);
		}
		
	}
	
}
