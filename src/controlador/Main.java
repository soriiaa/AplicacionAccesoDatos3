package controlador;

import modelo.Modelo;
import vista.Vista;
import vista._00_InicioAplicacion;
import vista._01_AgregarRegistros;

public class Main {

	public static void main(String[] args) {

		Modelo miModelo = new Modelo();
		Vista[] misVistas = new Vista[2];
		Controlador miControlador = new Controlador();

		misVistas[0] = new _00_InicioAplicacion();
		misVistas[1] = new _01_AgregarRegistros();

		miModelo.setVista(misVistas);
		miControlador.setVista(misVistas);
		miControlador.setModelo(miModelo);

		for (Vista vista : misVistas) {
			vista.setModelo(miModelo);
			vista.setControlador(miControlador);
		}

	}

}
