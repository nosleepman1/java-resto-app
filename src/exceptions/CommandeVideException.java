package exceptions;

public class CommandeVideException extends Exception {
    public CommandeVideException() {
        super("La commande ne peut pas être validée sans plat.");
    }
}
