package controlador;

import modelo.Modelo;
import vista.Vista;
import vista._00_InicioAplicacion;
import vista._01_AgregarRegistros;
import vista._02_VisualizarDatos;

public class Main {

	public static void main(String[] args) {

		Modelo miModelo = new Modelo();
		Vista[] misVistas = new Vista[3];
		Controlador miControlador = new Controlador();

		misVistas[0] = new _00_InicioAplicacion();
		misVistas[1] = new _01_AgregarRegistros();
		misVistas[2] = new _02_VisualizarDatos();

		miModelo.setVista(misVistas);
		miControlador.setVista(misVistas);
		miControlador.setModelo(miModelo);

		for (Vista vista : misVistas) {
			vista.setModelo(miModelo);
			vista.setControlador(miControlador);
		}

	}

}
