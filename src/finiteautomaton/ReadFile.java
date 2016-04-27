package finiteautomaton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//FORMAT:
/*
origin-simbol-purpose[-purpose..]
...
EF 
EFT
final_state
 */
/**
 * 
 * @author DrAP
 */
public class ReadFile {

    private File file = null;
    private FileReader fr = null;
    private BufferedReader br = null;

    public ReadFile(String f) {
        try {
            file = new File(f);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DFA ReadDFA() {
        DFA automaton_aux = new DFA();
        if (br != null) {
            try {
                String res[];
                String aux = br.readLine();
                //mete las transiciones
                while (aux != null && !aux.equals("EF")) {
                    res = aux.split("-");
                    int origin = Integer.parseInt(res[0]);
                    int purpose = Integer.parseInt(res[2]);
                    char simbol = res[1].charAt(0);
                    automaton_aux.addTransition(origin, simbol, purpose);
                    aux = br.readLine();
                }
                aux = br.readLine();
                while (aux != null && !aux.equals("EOF")) {
                    automaton_aux.addFinalState(Integer.parseInt(aux));
                    aux = br.readLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return automaton_aux;
    }

    public NDFA ReadNDFA() {
        NDFA automaton_aux = new NDFA();
        if (br != null) {
            try {
                String res[];
                String aux = br.readLine();
                while (aux != null && !aux.equals("EF")) {
                    res = aux.split("-");
                    int origin = Integer.parseInt(res[0]);
                    char simbol = res[1].charAt(0);
                    int[] purposes = new int[res.length - 2];
                    for (int i = 2; i < res.length; i++) {
                        purposes[i - 2] = Integer.parseInt(res[i]);
                    }
                    automaton_aux.addTransition(origin, simbol, purposes);
                    aux = br.readLine();
                }
                aux = br.readLine();
                while (aux != null && !aux.equals("EFT")) {
                    res = aux.split("-");
                    int origin = Integer.parseInt(res[0]);
                    int[] purpose = new int[res.length - 1];
                    for (int i = 1; i < res.length; i++) {
                        purpose[i - 1] = Integer.parseInt(res[i]);
                    }
                    automaton_aux.addLambdaTransition(origin, purpose);
                    aux = br.readLine();
                }
                aux = br.readLine();
                while (aux != null && !aux.equals("EOF")) {
                    automaton_aux.addFinalState(Integer.parseInt(aux));
                    aux = br.readLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return automaton_aux;
    }
    
    public void closeFile() {
        if (fr != null) {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
