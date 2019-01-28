package softLaunch.exceptionHandler;

public class ClientNotFoundInWhitelistException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public ClientNotFoundInWhitelistException(String mensagem) {
        super(mensagem);
    }

    public ClientNotFoundInWhitelistException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public ClientNotFoundInWhitelistException() {

    }
}