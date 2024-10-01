package repository;

import exception.MarcaNoEncontradoException;
import exception.ModeloNoEncontradoException;
import exception.PrecioNoEncontradoException;
import model.Vehiculo;
import java.util.List;

public interface VehiculoRepository {

    List<Vehiculo> buscar(String marca) throws MarcaNoEncontradoException;

    List<Vehiculo> buscar(Double precio) throws PrecioNoEncontradoException;

    List<Vehiculo> buscar(Integer modelo) throws ModeloNoEncontradoException;

    void agregar(Vehiculo vehiculo);

    List<Vehiculo> getVehiculos();

    void escribirDatos(String ruta);
}
