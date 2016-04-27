package finiteautomaton;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DrAP
 */
public class DFA implements Process, Cloneable {

    private ArrayList<Integer> final_states;
    private ArrayList<DFATransition> transitions;

    /**
     * CONSTRUCTOR
     */
    public DFA() {
        this.transitions = new ArrayList<>();
        this.final_states = new ArrayList<>();
    }

    public boolean addTransition(int origin, char simbol, int purpose) {
        DFATransition aux_transition = new DFATransition(origin, simbol, purpose);
        if (!transitions.contains(aux_transition)) {
            transitions.add(new DFATransition(origin, simbol, purpose));
            return true;
        } else {
            return false;
        }
    }

    public boolean removeTransition(int origin, char simbol, int purpose) {
        DFATransition aux_transition = new DFATransition(origin, simbol, purpose);
        if (transitions.contains(aux_transition)) { //Si se encuentra la transación
            transitions.remove(aux_transition);
            return true;
        } else {
            return false;
        }
    }

    public int transition(int state, char simbol) {
        for (int i = 0; i < transitions.size(); i++) {
            DFATransition aux_transition = transitions.get(i);
            if ((aux_transition.getOrigin_state()== state) && (aux_transition.getSimbol() == simbol)) {
                return aux_transition.getPurpose_state();
            }
        }
        return -1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        DFA obj = null;
        try {
            obj = (DFA) super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
        } finally {
            obj.final_states = (ArrayList<Integer>) this.final_states.clone();
            obj.transitions = (ArrayList<DFATransition>) this.transitions.clone();
            for (int i = 0; i < final_states.size(); i++) {
                obj.final_states.set(i, this.final_states.get(i));
            }
            for (int i = 0; i < transitions.size(); i++) {
                obj.transitions.set(i, this.transitions.get(i));
            }
        }
        return obj;
    }

    @Override
    public boolean isFinal(int state) {
        for (int i = 0; i < final_states.size(); i++) {
            if (final_states.get(i) == state) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String line_separator = System.getProperty("line.separator");
        String aux = new String();
        for (int i = 0; i < transitions.size(); i++) {
            aux = aux + transitions.get(i).toString() + line_separator;
        }
        return aux;
    }

    @Override
    public boolean recognize(String string) {
        char[] simbols = string.toCharArray();
        int state = 0; 
        for (int i = 0; i < simbols.length; i++) {
            state = transition(state, simbols[i]);
        }
        return isFinal(state);
    }

    public int stateAt(String string, int n) {
        char[] simbols = string.toCharArray();
        int state = 0; 
        int aux = 0;
        for (int i = 0; i < n; i++) {
            aux = state;
            state = transition(state, simbols[i]);
        }
        return aux;
    }

    public boolean addFinalState(int state) {
        if (!isFinal(state)) {
            final_states.add(state);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeFinalState(int state) {
        if (isFinal(state)) {
            final_states.remove(state);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Integer> getFinalStates() throws CloneNotSupportedException {
        DFA obj = new DFA();
        try {
            obj = (DFA) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(DFA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj.final_states;
    }

    public ArrayList<DFATransition> getTransitions() throws CloneNotSupportedException {
        DFA obj = new DFA();
        try {
            obj = (DFA) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(DFA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj.transitions;
    }

}
