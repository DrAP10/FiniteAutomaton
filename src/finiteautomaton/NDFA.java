package finiteautomaton;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DrAP
 */
public class NDFA implements Process, Cloneable {

    private ArrayList<Integer> final_states;
    private ArrayList<NDFATransition> transitions;
    private ArrayList<NDFALambdaTransition> lambda_transitions;

    public NDFA() {
        final_states = new ArrayList<>();
        transitions = new ArrayList<>();
        lambda_transitions = new ArrayList<>();
    }

    public int addTransition(int origin, char simbol, int[] purposes) {
        NDFATransition aux_transition = new NDFATransition(origin, simbol, purposes);
        int i = this.transitions.indexOf(aux_transition);
        if (i != -1) {
            for (int j = 0; j < purposes.length; j++) {
                if (!transitions.get(i).getPurpose_states().contains(purposes[j])) {
                    transitions.get(i).getPurpose_states().add(purposes[j]);
                }
            }
            return i;
        } else {
            this.transitions.add(aux_transition);
            return transitions.size() - 1;
        }
    }

    public int addLambdaTransition(int origin, int[] purposes) {
        NDFALambdaTransition aux_transition = new NDFALambdaTransition(origin, purposes);
        int i = this.lambda_transitions.indexOf(aux_transition);
        if (i != -1) {
            for (int j = 0; j < purposes.length; j++) {
                if (!lambda_transitions.get(i).getPurpose_states().contains(purposes[j])) {
                    lambda_transitions.get(i).getPurpose_states().add(purposes[j]);
                }
            }
            return i;
        } else {
            lambda_transitions.add(aux_transition);
            return lambda_transitions.size() - 1;
        }
    }

    public int[] transition(int state, char simbol) {
        ArrayList<Integer> arrayaux = new ArrayList<Integer>();
        for (int i = 0; i < this.transitions.size(); i++) {
            if ((this.transitions.get(i).getOrigin_state()== state) 
                    && (this.transitions.get(i).getSimbol()== simbol)) {
                arrayaux = transitions.get(i).getPurpose_states();
                break;
            }
        }
        if (arrayaux != null) {
            int[] aux = new int[arrayaux.size()];
            for (int i = 0; i < arrayaux.size(); i++) {
                aux[i] = arrayaux.get(i);
            }
            return lambdaClosure(aux);
        } else {
            return new int[0];
        }
    }

    public int[] transition(int[] macrostate, char simbol) {
        int[] aux;
        ArrayList<Integer> arrayaux = new ArrayList<Integer>();
        for (int i = 0; i < macrostate.length; i++) {
            aux = NDFA.this.transition(macrostate[i], simbol);
            for (int j = 0; j < aux.length; j++) {
                if (!arrayaux.contains(aux[j])) {
                    arrayaux.add(aux[j]);
                }
            }
        }
        if (!arrayaux.isEmpty()) {
            aux = new int[arrayaux.size()];
            for (int i = 0; i < arrayaux.size(); i++) {
                aux[i] = arrayaux.get(i);
            }
            return aux;
        } else {
            return new int[0];
        }
    }

    public int[] lambdaTransition(int estado) {
        ArrayList<Integer> arrayaux = new ArrayList<Integer>();
        for (int i = 0; i < lambda_transitions.size(); i++) {
            if (lambda_transitions.get(i).getOrigin_state()== estado) {
                arrayaux = lambda_transitions.get(i).getPurpose_states();
                break;
            }
        }
        if (arrayaux != null) {
            int aux[] = new int[arrayaux.size()];
            for (int i = 0; i < arrayaux.size(); i++) {
                aux[i] = arrayaux.get(i);
            }
            return aux;
        } else {
            return new int[0];
        }
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

    public boolean isFinal(int[] macrostate) {
        for (int i = 0; i < macrostate.length; i++) {
            if (NDFA.this.isFinal(macrostate[i])) {
                return true;
            }
        }
        return false;
    }

    public int[] lambdaClosure(int[] macrostate) {
        ArrayList<Integer> arrayaux = new ArrayList<Integer>();
        int[] aux;
        for (int i = 0; i < macrostate.length; i++) {
            if (!arrayaux.contains(macrostate[i])) {
                arrayaux.add(macrostate[i]);
            }
            aux = lambdaTransition(macrostate[i]);
            for (int j = 0; j < aux.length; j++) {
                if (!arrayaux.contains(aux[j])) {
                    arrayaux.add(aux[j]);
                }
            }
        }
        if (!arrayaux.isEmpty()) {
            aux = new int[arrayaux.size()];
            for (int i = 0; i < arrayaux.size(); i++) {
                aux[i] = arrayaux.get(i);
            }
            return aux;
        } else {
            return new int[0];
        }
    }

    @Override
    public boolean recognize(String string) {
        char[] simbol = string.toCharArray();
        int[] state = {0}; 
        int[] macrostate = lambdaClosure(state);
        for (int i = 0; i < simbol.length; i++) {
            macrostate = transition(macrostate, simbol[i]);
        }
        return isFinal(macrostate);
    }

    public int[] stateAt(String string, int n) {
        char[] simbol = string.toCharArray();
        int[] state = {0}; 
        int[] macrostate = lambdaClosure(state);
        int[] aux = {0};
        for (int i = 0; i < n; i++) {
            aux = transition(macrostate, simbol[i]);
            macrostate = aux;
        }
        return aux;
    }

    public boolean addFinalState(int state) {
        if (!NDFA.this.isFinal(state)) {
            final_states.add(state);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        NDFA obj = null;
        try {
            obj = (NDFA) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(NDFA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            obj.transitions = (ArrayList<NDFATransition>) this.transitions.clone();
            obj.lambda_transitions = (ArrayList<NDFALambdaTransition>) this.lambda_transitions.clone();
            obj.final_states = (ArrayList<Integer>) final_states.clone();
            for (int i = 0; i < this.transitions.size(); i++) {
                obj.transitions.set(i, this.transitions.get(i));
            }
            for (int i = 0; i < this.lambda_transitions.size(); i++) {
                obj.lambda_transitions.set(i, this.lambda_transitions.get(i));
            }
            for (int i = 0; i < this.final_states.size(); i++) {
                obj.final_states.set(i, final_states.get(i));
            }
        }
        return obj;
    }

    public ArrayList<Integer> getFinalStates() throws CloneNotSupportedException {
        NDFA obj = new NDFA();
        try {
            obj = (NDFA) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(NDFA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj.final_states;
    }

    public ArrayList<NDFATransition> getTransitions() throws CloneNotSupportedException {
        NDFA obj = new NDFA();
        try {
            obj = (NDFA) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(NDFA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj.transitions;
    }

    public ArrayList<NDFALambdaTransition> getLambda_transitions() throws CloneNotSupportedException {
        NDFA obj = new NDFA();
        try {
            obj = (NDFA) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(NDFA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj.lambda_transitions;
    }

}
