package exception;

public class MarcaNoEncontradoException extends VehiculoException {
    public MarcaNoEncontradoException(String marca) {
        super("No se encontraron vehiculo que correspondan a la marca: " + marca );
    }
}
