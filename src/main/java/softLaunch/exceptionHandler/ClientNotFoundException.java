package softLaunch.exceptionHandler;

public class ClientNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public ClientNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ClientNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public ClientNotFoundException() {

    }
}