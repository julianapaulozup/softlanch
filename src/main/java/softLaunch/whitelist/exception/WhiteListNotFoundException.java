package softLanch.whitelist.exception;

public class WhiteListNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public WhiteListNotFoundException() {
        super();
    }
    public WhiteListNotFoundException(String mensagem) {
        super(mensagem);
    }

    public WhiteListNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
