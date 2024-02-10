package rgo.tt.security.proxy.common.om.exception;

public class JsonRuntimeException extends RuntimeException {

    public JsonRuntimeException(Throwable cause) {
        super(cause);
    }

    public JsonRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
