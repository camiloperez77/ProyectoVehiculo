package exception;

public class ModeloNoEncontradoException extends VehiculoException {
    public ModeloNoEncontradoException(Integer modelo) {
        super("No se encontraron vehiculo que correspondan a el modelo: " + modelo );
    }
}
