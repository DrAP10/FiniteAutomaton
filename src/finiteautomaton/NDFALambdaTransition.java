package finiteautomaton;

import java.util.ArrayList;

/**
 *
 * @author DrAP
 */
public class NDFALambdaTransition {

    private int origin_state;
    private ArrayList<Integer> purpose_states;

    public NDFALambdaTransition(int origin_state, int[] purpose_states) {
        this.origin_state = origin_state;
        this.purpose_states = new ArrayList<Integer>();
        for (int i = 0; i < purpose_states.length; i++) {
            if (!this.purpose_states.contains(purpose_states[i])) {
                this.purpose_states.add(purpose_states[i]);
            }
        }
    }
    
    public String toString() {
        String aux = origin_state + "   ";
        for (int i = 0; i < purpose_states.size(); i++) {
            aux = aux + purpose_states.get(i) + ", ";
        }
        return aux;
    }

    public boolean equals(Object obj) {
        if (origin_state == ((NDFALambdaTransition) obj).origin_state) {
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
    
}
