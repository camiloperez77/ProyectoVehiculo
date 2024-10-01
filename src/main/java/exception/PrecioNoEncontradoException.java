package exception;

public class PrecioNoEncontradoException extends VehiculoException {
    public PrecioNoEncontradoException(Double precio) {
        super("No se encontraron vehiculo que correspondan a el tipo: " + precio );
    }
}
