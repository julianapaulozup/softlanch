package softLanch.client.exception;

public class ClientNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public ClientNotFoundException() {
        super();
    }
    public ClientNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ClientNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
