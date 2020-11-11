package by.epamtc.aladzyin.dao.exception;

public class ConnectionPoolError extends Error {

	private static final long serialVersionUID = 141135163350627991L;

	public ConnectionPoolError() {
    }

    public ConnectionPoolError(String message) {
        super(message);
    }

    public ConnectionPoolError(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolError(Throwable cause) {
        super(cause);
    }
}
