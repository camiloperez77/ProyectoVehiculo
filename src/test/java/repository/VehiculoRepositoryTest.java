package repository;

import exception.MarcaNoEncontradoException;
import exception.ModeloNoEncontradoException;
import exception.PrecioNoEncontradoException;
import model.Vehiculo;
import org.junit.jupiter.api.Test;
import repository.impl.VehiculoRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehiculoRepositoryTest {

    VehiculoRepository vehiculotest = new VehiculoRepositoryImpl("./data/carros.txt");

   @Test
    void testBuscarPorMarca() throws MarcaNoEncontradoException {
        List<Vehiculo> result = vehiculotest.buscar("BMW");  // Prueba exitosa
        assertEquals(1, result.size());
        assertEquals("BMW", result.get(0).marca());
        assertThrows(MarcaNoEncontradoException.class, () -> {   // Prueba de excepción
           vehiculotest.buscar("Buggatti");
        });
    }

    @Test
    void testBuscarPorPrecio() throws PrecioNoEncontradoException {
        List<Vehiculo> result =vehiculotest.buscar(15000.0);     // Prueba exitosa
        assertEquals(1, result.size());
        assertEquals(15000.0, result.get(0).precio());
        assertThrows(PrecioNoEncontradoException.class, () -> {   // Prueba de excepción
           vehiculotest.buscar(2000.0);
        });
    }

    @Test
    void testBuscarPorModelo() throws ModeloNoEncontradoException {
        List<Vehiculo> result =vehiculotest.buscar(2020);    // Prueba exitosa
        assertEquals(4, result.size());
        assertEquals(2020, result.get(0).modelo());
        assertThrows(ModeloNoEncontradoException.class, () -> {       // Prueba de excepción
           vehiculotest.buscar(1990);
        });
    }
}