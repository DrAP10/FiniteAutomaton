/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finiteautomaton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DrAP
 */
public class ui extends javax.swing.JFrame {

    Vector<Color> v_colors;
    Vector<GState> states;
    Vector<GTransition> transitions;
    Vector<GTransition> lambda_transations;
    String string;
    int current_string;
    String path;
    DFA dfa;
    IntroduceStringDialog d_introduce_string;
    IntroduceAutomatonDialog d_introduce_automaton;
    private NDFA ndfa;
    boolean deterministic;

    public ui() {
        initComponents();
        deterministic = true;
        List<Color> colors = Arrays.asList(
                new Color[]{Color.RED, Color.YELLOW, Color.PINK, Color.BLUE, Color.ORANGE, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.WHITE, Color.DARK_GRAY});
        v_colors = new Vector<Color>(colors);
        this.getContentPane().setBackground(Color.BLACK);
        this.sp_content.setBackground(new Color(0f, 0f, 0f, 1f));
        this.sp_main.setBackground(new Color(0f, 0f, 0f, 1f));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        states = new Vector<GState>();
        transitions = new Vector<GTransition>();
        path = null;
        dfa = new DFA();
        ndfa = new NDFA();
        string = "";
        current_string = 0;
        d_introduce_string = new IntroduceStringDialog(this, true);
        d_introduce_string.setTitle("Introduce String");
        d_introduce_string.addConfirmListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                string = d_introduce_string.getTextFieldText();
                string = string + "-";
                current_string = 0;
                l_string_1.setText(string.substring(0, current_string));
                l_string_2.setText(string.substring(current_string, current_string + 1));
                l_string_3.setText(string.substring(current_string + 1));
                d_introduce_string.setVisible(false);
                drawCurrentState();
//            if(dfa!=null)
//                drawDFA(dfa);
            }
        });
        d_introduce_automaton = new IntroduceAutomatonDialog(this, true);
        d_introduce_automaton.setTitle("Introduce Automaton");
        d_introduce_automaton.setVisible(false);
        d_introduce_automaton.addTransitionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (d_introduce_automaton.isDeterministic()) {
                    dfa.addTransition(d_introduce_automaton.getOrigin(), d_introduce_automaton.getSimbol(), d_introduce_automaton.gerPurpose());
                    for (int i = sp_content.getComponentCount() - 1; i >= 0; i--) {
                        sp_content.remove(sp_content.getComponent(i));
                    }
                    drawDFA();
                } else {
                    int[] d = new int[1];
                    d[0] = d_introduce_automaton.gerPurpose();
                    if(d_introduce_automaton.getSimbol()!='\u03BB')
                        ndfa.addTransition(d_introduce_automaton.getOrigin(), d_introduce_automaton.getSimbol(), d);
                    else
                        ndfa.addLambdaTransition(d_introduce_automaton.getOrigin(), d);
                    for (int i = sp_content.getComponentCount() - 1; i >= 0; i--) {
                        sp_content.remove(sp_content.getComponent(i));
                    }
                    drawNDFA();
                }
                repaint();
                sp_content.updateUI();
            }
        });
        d_introduce_automaton.addRemoveAllListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = sp_content.getComponentCount() - 1; i >= 0; i--) {
                    sp_content.remove(sp_content.getComponent(i));
                }
                sp_content.repaint();
                dfa = new DFA();
                ndfa = new NDFA();
                deterministic = d_introduce_automaton.isDeterministic();
            }
        });
        d_introduce_automaton.addFinalStateListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (d_introduce_automaton.isDeterministic()) {
                    dfa.addFinalState(d_introduce_automaton.getFinal());
                    for (int i = sp_content.getComponentCount() - 1; i >= 0; i--) {
                        sp_content.remove(sp_content.getComponent(i));
                    }
                    drawDFA();
                } else {
                    ndfa.addFinalState(d_introduce_automaton.getFinal());
                    for (int i = sp_content.getComponentCount() - 1; i >= 0; i--) {
                        sp_content.remove(sp_content.getComponent(i));
                    }
                    drawNDFA();
                }
                repaint();
                sp_content.updateUI();
            }
        });
        this.sp_main.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                repaint();
//                for(int i=0;i<sp_content.getComponentCount();i++){
//                    sp_content.getComponent(i).setVisible(false);
//                    sp_content.getComponent(i).repaint();
//                    sp_content.getComponent(i).setVisible(true);
//                }
            }
        });
        /*estado=new GState("AAA",false);
        estado.setLocation(100, 100);
        this.add(estado);
        GState final_state=new GState("FINAL",true);
        final_state.setLocation(250, 100);
        this.add(final_state);*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp_main = new javax.swing.JScrollPane();
        sp_content = new javax.swing.JPanel();
        b_restart = new javax.swing.JButton();
        b_previus_step = new javax.swing.JButton();
        b_next_step = new javax.swing.JButton();
        b_run = new javax.swing.JButton();
        l_string_3 = new javax.swing.JLabel();
        l_string_1 = new javax.swing.JLabel();
        l_string_2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_file = new javax.swing.JMenu();
        mi_load_DFA = new javax.swing.JMenuItem();
        mi_load_NDFA = new javax.swing.JMenuItem();
        mi_DFA_from_keyboard = new javax.swing.JMenuItem();
        mi_NDFA_from_keyboard = new javax.swing.JMenuItem();
        mi_introduce_string = new javax.swing.JMenuItem();
        menu_run = new javax.swing.JMenu();
        mi_restart = new javax.swing.JMenuItem();
        mi_previus_step = new javax.swing.JMenuItem();
        mi_next_step = new javax.swing.JMenuItem();
        mi_run_all = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sp_main.setBorder(null);

        javax.swing.GroupLayout sp_contentLayout = new javax.swing.GroupLayout(sp_content);
        sp_content.setLayout(sp_contentLayout);
        sp_contentLayout.setHorizontalGroup(
            sp_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        sp_contentLayout.setVerticalGroup(
            sp_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );

        sp_main.setViewportView(sp_content);

        b_restart.setText("Restart");
        b_restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_restartActionPerformed(evt);
            }
        });

        b_previus_step.setText("Previus Step");
        b_previus_step.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_previus_stepActionPerformed(evt);
            }
        });

        b_next_step.setText("Next Step");
        b_next_step.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_next_stepActionPerformed(evt);
            }
        });

        b_run.setText("Run All");
        b_run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_runActionPerformed(evt);
            }
        });

        l_string_3.setForeground(new java.awt.Color(0, 255, 0));
        l_string_3.setText(" ");

        l_string_1.setForeground(new java.awt.Color(0, 255, 0));
        l_string_1.setText(" ");

        l_string_2.setForeground(new java.awt.Color(255, 255, 255));
        l_string_2.setText(" ");

        menu_file.setText("Load Automaton");

        mi_load_DFA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        mi_load_DFA.setText("Load DFA");
        mi_load_DFA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_load_DFAActionPerformed(evt);
            }
        });
        menu_file.add(mi_load_DFA);

        mi_load_NDFA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mi_load_NDFA.setText("Load NDFA");
        mi_load_NDFA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_load_NDFAActionPerformed(evt);
            }
        });
        menu_file.add(mi_load_NDFA);

        mi_DFA_from_keyboard.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        mi_DFA_from_keyboard.setText("DFA from keyboard");
        mi_DFA_from_keyboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_DFA_from_keyboardActionPerformed(evt);
            }
        });
        menu_file.add(mi_DFA_from_keyboard);

        mi_NDFA_from_keyboard.setText("NDFA from keyboard");
        mi_NDFA_from_keyboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_NDFA_from_keyboardActionPerformed(evt);
            }
        });
        menu_file.add(mi_NDFA_from_keyboard);

        mi_introduce_string.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mi_introduce_string.setText("Introduce string");
        mi_introduce_string.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_introduce_stringActionPerformed(evt);
            }
        });
        menu_file.add(mi_introduce_string);

        jMenuBar1.add(menu_file);

        menu_run.setText("Run");

        mi_restart.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, 0));
        mi_restart.setText("Restart");
        mi_restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_restartActionPerformed(evt);
            }
        });
        menu_run.add(mi_restart);

        mi_previus_step.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, 0));
        mi_previus_step.setText("Previus Step");
        mi_previus_step.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_previus_stepActionPerformed(evt);
            }
        });
        menu_run.add(mi_previus_step);

        mi_next_step.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, 0));
        mi_next_step.setText("Next Step");
        mi_next_step.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_next_stepActionPerformed(evt);
            }
        });
        menu_run.add(mi_next_step);

        mi_run_all.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        mi_run_all.setText("Run All");
        mi_run_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_run_allActionPerformed(evt);
            }
        });
        menu_run.add(mi_run_all);

        jMenuBar1.add(menu_run);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp_main)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(b_restart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_previus_step)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_string_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_string_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_string_3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_next_step)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_run)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_previus_step)
                    .addComponent(l_string_3)
                    .addComponent(b_next_step)
                    .addComponent(l_string_1)
                    .addComponent(l_string_2)
                    .addComponent(b_run)
                    .addComponent(b_restart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_main, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mi_DFA_from_keyboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_DFA_from_keyboardActionPerformed
        deterministic = true;
        d_introduce_automaton.setDeterministic(deterministic);
        d_introduce_automaton.setVisible(true);
    }//GEN-LAST:event_mi_DFA_from_keyboardActionPerformed

    private void mi_load_DFAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_load_DFAActionPerformed
        //JOptionPane.showMessageDialog(null, "Desde Archivo", "InfoBox: " + "Archivo", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser file_chooser = new JFileChooser("./FA Examples");
        file_chooser.showOpenDialog(this);
        path = file_chooser.getSelectedFile().getAbsolutePath();
        drawDFA(path);
    }//GEN-LAST:event_mi_load_DFAActionPerformed

    public void drawDFA(String path) {
        ReadFile aux = new ReadFile(path);
        dfa = new DFA();
        dfa = aux.ReadDFA();
        deterministic = true;
        aux.closeFile();
        drawDFA();
        current_string = 0;
        drawCurrentState();
    }

    public void drawNDFA(String path) {
        ReadFile aux = new ReadFile(path);
        ndfa = new NDFA();
        ndfa = aux.ReadNDFA();
        deterministic = false;
        aux.closeFile();
        drawNDFA();
        current_string = 0;
        drawCurrentState();
    }

    public void drawDFA() {
        for (int i = 0; i < states.size(); i++) {
            this.sp_content.remove(states.elementAt(i));
        }
        for (int i = 0; i < transitions.size(); i++) {
            this.sp_content.remove(transitions.elementAt(i));
        }
        this.sp_content.repaint();
        int ox = 0;
        int oy = 350;
        int y_center = oy + GState.getFull_size() / 2;
        int position = GState.getFull_size() + 75;
        int max_x = this.sp_content.getWidth();
        int max_y = this.sp_content.getHeight();
        /**
         * *
         */
        try {
            ArrayList<DFATransition> arr = dfa.getTransitions();
            ArrayList<Integer> arr_final_states = dfa.getFinalStates();
            states = new Vector<GState>();
            transitions = new Vector<GTransition>();
            for (int i = 0; i < arr.size(); i++) {
                int origin = arr.get(i).getOrigin_state();
                int purpose = arr.get(i).getPurpose_state();
                char sim = (char) arr.get(i).getSimbol();
                if (!states.contains(new GState(String.valueOf(origin), v_colors.elementAt(origin % 10), false))) {
                    GState state = new GState(String.valueOf(origin), v_colors.elementAt(origin % 10),
                            arr_final_states.contains(origin));
                    state.setLocation(origin * position, oy);
                    if (origin * position + GState.getFull_size() > max_x) {
                        max_x = origin * position + GState.getFull_size();
                    }
                    states.add(state);
                }
                if (!states.contains(new GState(String.valueOf(purpose), v_colors.elementAt(purpose % 10), false))) {
                    GState state = new GState(String.valueOf(purpose), v_colors.elementAt(purpose % 10),
                            arr_final_states.contains(purpose));
                    state.setLocation(purpose * position, oy);
                    if (purpose * position + GState.getFull_size() > max_x) {
                        max_x = purpose * position + GState.getFull_size();
                    }
                    states.add(state);
                }
                GTransition transation = new GTransition(sim, purpose, origin, position, y_center,
                        max_x, max_y, v_colors.elementAt(origin % 10));
                transation.setLocation(0, 0);
                transitions.add(transation);
            }
            //this.sp_content.setSize(max_x,max_y);
            for (int i = 0; i < states.size(); i++) {
                this.sp_content.add(states.elementAt(i));
                states.elementAt(i).updateUI();
            }
            for (int i = 0; i < transitions.size(); i++) {
                this.sp_content.add(transitions.elementAt(i));
                transitions.elementAt(i).updateUI();
            }
            this.update(this.getGraphics());
            Dimension size = new Dimension(max_x, max_y);
            this.sp_content.setMaximumSize(size);
            this.sp_content.setMinimumSize(size);
            this.sp_content.setSize(size);
            this.sp_content.setPreferredSize(size);

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ui.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * **
         */
    }

    public void drawNDFA() {
        for (int i = 0; i < states.size(); i++) {
            this.sp_content.remove(states.elementAt(i));
        }
        for (int i = 0; i < transitions.size(); i++) {
            this.sp_content.remove(transitions.elementAt(i));
        }
        this.sp_content.repaint();
        int ox = 0;
        int oy = 200;
        int y_center = oy + GState.getFull_size() / 2;
        int position = GState.getFull_size() + 75;
        int max_x = this.sp_content.getWidth();
        int max_y = this.sp_content.getHeight();
        /**
         * *
         */
        try {
            ArrayList<NDFATransition> arr = ndfa.getTransitions();
            ArrayList<NDFALambdaTransition> arrl = ndfa.getLambda_transitions();
            ArrayList<Integer> arr_final_states = ndfa.getFinalStates();
            states = new Vector<GState>();
            transitions = new Vector<GTransition>();
            for (int i = 0; i < arr.size(); i++) {
                int origin = arr.get(i).getOrigin_state();
                ArrayList<Integer> purpose = arr.get(i).getPurpose_states();
                char sim = (char) arr.get(i).getSimbol();
                if (!states.contains(new GState(String.valueOf(origin), v_colors.elementAt(origin % 10), false))) {
                    GState state = new GState(String.valueOf(origin), v_colors.elementAt(origin % 10),
                            arr_final_states.contains(origin));
                    state.setLocation(origin * position, oy);
                    if (origin * position + GState.getFull_size() > max_x) {
                        max_x = origin * position + GState.getFull_size();
                    }
                    states.add(state);
                }
                for (int j = 0; j < purpose.size(); j++) {
                    if (!states.contains(new GState(String.valueOf(purpose.get(j)), v_colors.elementAt(purpose.get(j) % 10), false))) {
                        GState state = new GState(String.valueOf(purpose.get(j)), v_colors.elementAt(purpose.get(j) % 10),
                                arr_final_states.contains(purpose.get(j)));
                        state.setLocation(purpose.get(j) * position, oy);
                        if (purpose.get(j) * position + GState.getFull_size() > max_x) {
                            max_x = purpose.get(j) * position + GState.getFull_size();
                        }
                        states.add(state);
                    }
                    GTransition transition = new GTransition(sim, purpose.get(j), origin, position, y_center,
                            max_x, max_y, v_colors.elementAt(origin % 10));
                    transition.setLocation(0, 0);
                    transitions.add(transition);
                }
            }
            for (int i = 0; i < arrl.size(); i++) {
                int origin = arrl.get(i).getOrigin_state();
                ArrayList<Integer> purpose = arrl.get(i).getPurpose_states();
                char sim = '\u03BB';
                if (!states.contains(new GState(String.valueOf(origin), v_colors.elementAt(origin % 10), false))) {
                    GState state = new GState(String.valueOf(origin), v_colors.elementAt(origin % 10),
                            arr_final_states.contains(origin));
                    state.setLocation(origin * position, oy);
                    if (origin * position + GState.getFull_size() > max_x) {
                        max_x = origin * position + GState.getFull_size();
                    }
                    states.add(state);
                }
                for (int j = 0; j < purpose.size(); j++) {
                    if (!states.contains(new GState(String.valueOf(purpose.get(j)), v_colors.elementAt(purpose.get(j) % 10), false))) {
                        GState state = new GState(String.valueOf(purpose.get(j)), v_colors.elementAt(purpose.get(j) % 10),
                                arr_final_states.contains(purpose.get(j)));
                        state.setLocation(purpose.get(j) * position, oy);
                        if (purpose.get(j) * position + GState.getFull_size() > max_x) {
                            max_x = purpose.get(j) * position + GState.getFull_size();
                        }
                        states.add(state);
                    }
                    GTransition transition = new GTransition(sim, purpose.get(j), origin, position, y_center,
                            max_x, max_y, v_colors.elementAt(origin % 10));
                    transition.setLocation(0, 0);
                    transitions.add(transition);
                }
            }
            //this.sp_content.setSize(max_x,max_y);
            for (int i = 0; i < states.size(); i++) {
                this.sp_content.add(states.elementAt(i));
                states.elementAt(i).updateUI();
            }
            for (int i = 0; i < transitions.size(); i++) {
                this.sp_content.add(transitions.elementAt(i));
                transitions.elementAt(i).updateUI();
            }
            this.update(this.getGraphics());
            Dimension size = new Dimension(max_x, max_y);
            this.sp_content.setMaximumSize(size);
            this.sp_content.setMinimumSize(size);
            this.sp_content.setSize(size);
            this.sp_content.setPreferredSize(size);

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ui.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * **
         */
    }

    private void mi_introduce_stringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_introduce_stringActionPerformed
        if (!d_introduce_string.isVisible()) {
            d_introduce_string.setVisible(true);
        }
    }//GEN-LAST:event_mi_introduce_stringActionPerformed

    private void b_next_stepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_next_stepActionPerformed
        this.mi_next_step.doClick();
    }//GEN-LAST:event_b_next_stepActionPerformed

    private void b_previus_stepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_previus_stepActionPerformed
        this.mi_previus_step.doClick();
    }//GEN-LAST:event_b_previus_stepActionPerformed

    public void drawCurrentState() {
        if (deterministic) {
            if (string.length() > 0) {
                int current_state = dfa.stateAt(string, current_string + 1);
                GState state = new GState(String.valueOf(current_state), v_colors.elementAt(current_state % 10), false);
                for (int i = 0; i < states.size(); i++) {
                    if (states.elementAt(i).equals(state)) {
                        states.elementAt(i).setColor(Color.GREEN, states.elementAt(i).getGraphics());
                    } else {
                        states.elementAt(i).setColor(Color.BLACK, states.elementAt(i).getGraphics());
                    }
                }
            }
        } else if (string.length() > 0) {
            for (int i = 0; i < states.size(); i++) {
                states.elementAt(i).setColor(Color.BLACK, states.elementAt(i).getGraphics());
            }
            int[] current_state = ndfa.stateAt(string, current_string);
            for (int j = 0; j < current_state.length; j++) {
                GState state = new GState(String.valueOf(current_state[j]), v_colors.elementAt(current_state[j] % 10), false);
                for (int i = 0; i < states.size(); i++) {
                    if (states.elementAt(i).equals(state)) {
                        states.elementAt(i).setColor(Color.GREEN, states.elementAt(i).getGraphics());
                    }
                }
            }
        }
    }

    private void b_runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_runActionPerformed
        this.mi_run_all.doClick();
    }//GEN-LAST:event_b_runActionPerformed

    private void b_restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_restartActionPerformed
        this.mi_restart.doClick();
    }//GEN-LAST:event_b_restartActionPerformed

    private void mi_load_NDFAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_load_NDFAActionPerformed
        JFileChooser file_chooser = new JFileChooser("./FA Examples");
        file_chooser.showOpenDialog(this);
        path = file_chooser.getSelectedFile().getAbsolutePath();
        drawNDFA(path);
    }//GEN-LAST:event_mi_load_NDFAActionPerformed

    private void mi_restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_restartActionPerformed
        current_string = 0;
        l_string_1.setText(string.substring(0, current_string));
        l_string_2.setText(string.substring(current_string, current_string + 1));
        l_string_3.setText(string.substring(current_string + 1));
        this.repaint();
        drawCurrentState();
    }//GEN-LAST:event_mi_restartActionPerformed

    private void mi_previus_stepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_previus_stepActionPerformed
        if (current_string > 0) {
            current_string--;
            l_string_1.setText(string.substring(0, current_string));
            l_string_2.setText(string.substring(current_string, current_string + 1));
            l_string_3.setText(string.substring(current_string + 1));
            this.repaint();
        }
        drawCurrentState();
    }//GEN-LAST:event_mi_previus_stepActionPerformed

    private void mi_next_stepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_next_stepActionPerformed
        if (current_string < (string.length() - 1)) {
            current_string++;
            l_string_1.setText(string.substring(0, current_string));
            l_string_2.setText(string.substring(current_string, current_string + 1));
            l_string_3.setText(string.substring(current_string + 1));
            this.repaint();
        }
        drawCurrentState();
        if (current_string > (string.length() - 2)) {
            if (deterministic) {
                if (dfa.recognize(string.substring(0, string.length() - 1))) {
                    JOptionPane.showMessageDialog(this, "The string \'" + string.substring(0, string.length() - 1)
                            + "\' is valid for this AFD.", "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "The string \'" + string.substring(0, string.length() - 1)
                            + "\' isn't valid for this AFD.", "", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (ndfa.recognize(string.substring(0, string.length() - 1))) {
                JOptionPane.showMessageDialog(this, "The string \'" + string.substring(0, string.length() - 1)
                        + "\' is valid for this AFND.", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "The string \'" + string.substring(0, string.length() - 1)
                        + "\' isn't valid for this AFND.", "", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_mi_next_stepActionPerformed

    private void mi_run_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_run_allActionPerformed
        System.out.println(deterministic);
        current_string = string.length() - 1;
        l_string_1.setText(string.substring(0, current_string));
        l_string_2.setText(string.substring(current_string, current_string + 1));
        l_string_3.setText(string.substring(current_string + 1));
        drawCurrentState();
        if (deterministic) {
            if (dfa.recognize(string.substring(0, string.length() - 1))) {
                JOptionPane.showMessageDialog(this, "The string \'" + string.substring(0, string.length() - 1)
                        + "\' is valid for this AFD.", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "The string \'" + string.substring(0, string.length() - 1)
                        + "\' isn't valid for this AFD.", "", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (ndfa.recognize(string.substring(0, string.length() - 1))) {
            JOptionPane.showMessageDialog(this, "The string \'" + string.substring(0, string.length() - 1)
                    + "\' is valid for this AFND.", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "The string \'" + string.substring(0, string.length() - 1)
                    + "\' isn't valid for this AFND.", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_mi_run_allActionPerformed

    private void mi_NDFA_from_keyboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_NDFA_from_keyboardActionPerformed
        deterministic = false;
        d_introduce_automaton.setDeterministic(deterministic);
        d_introduce_automaton.setVisible(true);
    }//GEN-LAST:event_mi_NDFA_from_keyboardActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_next_step;
    private javax.swing.JButton b_previus_step;
    private javax.swing.JButton b_restart;
    private javax.swing.JButton b_run;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel l_string_1;
    private javax.swing.JLabel l_string_2;
    private javax.swing.JLabel l_string_3;
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenu menu_run;
    private javax.swing.JMenuItem mi_DFA_from_keyboard;
    private javax.swing.JMenuItem mi_NDFA_from_keyboard;
    private javax.swing.JMenuItem mi_introduce_string;
    private javax.swing.JMenuItem mi_load_DFA;
    private javax.swing.JMenuItem mi_load_NDFA;
    private javax.swing.JMenuItem mi_next_step;
    private javax.swing.JMenuItem mi_previus_step;
    private javax.swing.JMenuItem mi_restart;
    private javax.swing.JMenuItem mi_run_all;
    private javax.swing.JPanel sp_content;
    private javax.swing.JScrollPane sp_main;
    // End of variables declaration//GEN-END:variables
}
