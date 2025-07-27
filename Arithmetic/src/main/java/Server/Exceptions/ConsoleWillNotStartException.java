package Server.Exceptions;

public class ConsoleWillNotStartException extends Exception{
    public ConsoleWillNotStartException() {
        super("Console input will not be registered because an error occurred when creating thread");
    }
}
