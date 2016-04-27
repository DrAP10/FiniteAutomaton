package finiteautomaton;

/**
 *
 * @author DrAP
 */
public interface Process {

    public abstract boolean isFinal(int state);

    public abstract boolean recognize(String string);

    public abstract String toString();
}
