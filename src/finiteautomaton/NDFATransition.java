package finiteautomaton;

import java.util.ArrayList;

/**
 *
 * @author DrAP
 */
public class NDFATransition {

    private int origin_state;
    private ArrayList<Integer> purpose_states;
    private char simbol;

    public NDFATransition(int origin_state, char simbol, int[] purpose_states) {
        this.origin_state = origin_state;
        this.simbol = simbol;
        this.purpose_states = new ArrayList<Integer>();
        for (int i = 0; i < purpose_states.length; i++) {
            if (!this.purpose_states.contains(purpose_states[i])) {
                this.purpose_states.add(purpose_states[i]);
            }
        }
    }

    public String toString() {
        String aux = origin_state + "   " + simbol + "   ";
        for (int i = 0; i < purpose_states.size(); i++) {
            aux = aux + purpose_states.get(i) + ", ";
        }
        return aux;
    }

    public boolean equals(Object obj) {
        if ((origin_state == ((NDFATransition) obj).origin_state) && (simbol == ((NDFATransition) obj).simbol)) {
            return true;
        } else {
            return false;
        }
    }

    public int getOrigin_state() {
        return origin_state;
    }

    public ArrayList<Integer> getPurpose_states() {
        return purpose_states;
    }

    public char getSimbol() {
        return simbol;
    }
}
