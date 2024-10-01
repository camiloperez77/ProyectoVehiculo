package repository.impl;

import exception.MarcaNoEncontradoException;
import exception.ModeloNoEncontradoException;
import exception.PrecioNoEncontradoException;
import model.Vehiculo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.VehiculoRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehiculoRepositoryImpl implements VehiculoRepository {

	private static final Logger logger = LoggerFactory.getLogger(VehiculoRepositoryImpl.class);

	private List<Vehiculo> vehiculos = null;

	public VehiculoRepositoryImpl(String ruta) {
		vehiculos = leerVehiculos(ruta);
	}

	@Override
	public void agregar(Vehiculo vehiculo) {
		vehiculos.add(vehiculo);
	}

	@Override
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	@Override
	public void escribirDatos(String ruta) {
		escribirVehiculos(vehiculos, ruta);
	}

	@Override
	public List<Vehiculo> buscar(String marca) throws MarcaNoEncontradoException {
		List<Vehiculo> result = vehiculos.stream()
				.filter(vehiculo -> vehiculo.marca().equalsIgnoreCase(marca))
				.toList();
		if (result.isEmpty()) {
			throw new MarcaNoEncontradoException(marca);
		}
		return result;
	}

	public List<Vehiculo> buscar(Double precio) throws PrecioNoEncontradoException {
		List<Vehiculo> result = vehiculos.stream()
				.filter(vehiculo -> vehiculo.precio().equals(precio))
				.toList();
		if (result.isEmpty()) {
			throw new PrecioNoEncontradoException(precio);
		}
		return result;
	}

	public List<Vehiculo> buscar(Integer modelo) throws ModeloNoEncontradoException {
		List<Vehiculo> result = vehiculos.stream()
				.filter(vehiculo -> vehiculo.modelo().equals(modelo))
				.toList();
		if (result.isEmpty()) {
			throw new ModeloNoEncontradoException(modelo);
		}
		return result;
	}

	private List<Vehiculo> leerVehiculos(String ruta) {
		Path rutaArchivo = Paths.get(ruta);   //"./data/carros.txt"
		try (BufferedReader reader = Files.newBufferedReader(rutaArchivo)) {
			List<Vehiculo> vehiculos =  reader.lines()
					.map(this::build)
					.collect(Collectors.toCollection(ArrayList::new));
			logger.info("La lectura de archivo ha sido exitosa.");
			return vehiculos;
		} catch (IOException e) {
			logger.info("Error en la lectura de archivo");
			throw new RuntimeException(e);
		}
	}

	private Vehiculo build(String text) {
		String[] vehiculoArray = text.split(",");
		return new Vehiculo(vehiculoArray[0].trim(),
				vehiculoArray[1].trim(),
				Integer.parseInt(vehiculoArray[2].trim()),
				vehiculoArray[3].trim(),
				Double.parseDouble(vehiculoArray[4].trim()));

	}

	public void escribirVehiculos(List<Vehiculo> vehiculos, String ruta) {
		Path rutaArchivo = Paths.get(ruta); // Archivo de salida : "./data/carros_nuevo.txt"
		try (BufferedWriter writer = Files.newBufferedWriter(rutaArchivo)) {
			for (Vehiculo vehiculo : vehiculos) {
				String linea = String.format("%s, %s, %s, %s, %.2f",  // Convertimos cada objeto Moto a una línea de texto separada por comas
						vehiculo.marca(),
						vehiculo.tipo(),
						vehiculo.modelo(),
						vehiculo.color(),
						vehiculo.precio());
				writer.write(linea);  // Escribimos la línea en el archivo
				writer.newLine(); // Agregamos una nueva línea
			}
			logger.info("La escritura de archivo ha sido exitosa.");
		} catch (IOException e) {
			logger.info("Error en la escritura de archivo");
			throw new RuntimeException("Error al escribir en el archivo", e);
		}
	}
}