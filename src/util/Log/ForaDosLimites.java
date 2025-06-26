package util.Log;

/**
 * <h1>ForaDosLimites</h1> é uma classe {@code ForaDosLimites} exclusivamente
 * para ser uma exception
 * que seja ocasionada ao extrapolar os limites de espaço de um tipo de dado ou
 * estrutura de dado.
 */
public class ForaDosLimites extends RuntimeException {
    public ForaDosLimites(String msg) {
        super(msg);
    }
}
