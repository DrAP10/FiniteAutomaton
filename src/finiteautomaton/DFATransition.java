package finiteautomaton;


/**
 *
 * @author DrAP
 */
public class DFATransition {

    //Los atributos seran los estados origen y destino y el simbol de la transicion
    private int origin_state;
    private int purpose_state;
    private int simbol;

    DFATransition(int origin_state, char simbol, int purpose_state) {
        this.origin_state = origin_state;
        this.purpose_state = purpose_state;
        this.simbol = simbol;
    }
    
    public String toString() {
        return (origin_state + "  " + simbol + "  " + purpose_state);
    }

    public boolean equals(Object obj) {
        if ((origin_state == ((DFATransition) obj).origin_state) && (this.simbol == ((DFATransition) obj).simbol)) {
            return true;
        } else {
            return false;
        }
    }

    public int getOrigin_state() {
        return origin_state;
    }

    public int getPurpose_state() {
        return purpose_state;
    }

    public int getSimbol() {
        return simbol;
    }

}
