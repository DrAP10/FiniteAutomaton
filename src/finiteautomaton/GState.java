/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finiteautomaton;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * State graphic class
 *
 * @author DrAP
 */
public class GState extends JComponent {

    JLabel label;
    String name;
    Color color;
    Color lines;
    boolean final_state;

    public Color getLines() {
        return lines;
    }

    public void setLines(Color lines) {
        this.lines = lines;
    }
    static final int WIDTH_FIGURA = 100;
    static final int HEIGHT_FIGURA = 100;
    static int full_size = WIDTH_FIGURA + 2;

    public static int getFull_size() {
        return full_size;
    }

    public static void setFull_size(int full_size) {
        GState.full_size = full_size;
    }

    public boolean isFinalState() {
        return final_state;
    }

    public void setFinalState(boolean is_final) {
        this.final_state = is_final;
    }

    public GState(String name, Color lines, boolean is_final) {
        this.setBackground(new Color(0f, 0f, 0f, 1f));
        //this.setBackground(Color.white);
        setOpaque(true);
        // this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.lines = lines;
        this.name = name;
        this.final_state = is_final;
        label = new JLabel(name, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setForeground(Color.white);
        label.setBackground(Color.black);
        label.setSize(40, 10);
        add(label);
        label.setVisible(true);
        color = Color.BLACK;
        this.setSize(full_size, full_size);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GState other = (GState) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public void setColor(Color color, Graphics g) {
        Path2D p = new Path2D.Float();
        this.color = color;
        int ox = 1;
        int oy = 1;
        p.moveTo(ox, oy);
        p.append(new Ellipse2D.Double(ox, oy, WIDTH_FIGURA, HEIGHT_FIGURA), true);
        p.closePath();

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.fill(p);
        g2d.dispose();
        drawOutline(g);
        label.repaint();
        label.setForeground((color == Color.BLACK) ? Color.WHITE : Color.BLACK);
        label.setBackground(color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawOutline(g);
    }

    protected void drawOutline(Graphics g) {
        int ox = 1;
        int oy = 1;
        Path2D p = new Path2D.Float();
        p.moveTo(ox, oy);
        p.append(new Ellipse2D.Double(ox, oy, WIDTH_FIGURA, HEIGHT_FIGURA), true);
        p.closePath();
        label.setLocation(this.getWidth() / 2 - label.getSize().width / 2, this.getHeight() / 2 - label.getSize().height / 2);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setColor(color);
        g2d.fill(p);
        g2d.setColor(lines);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(ox, oy, WIDTH_FIGURA, HEIGHT_FIGURA);
        if (isFinalState()) {
            g2d.drawOval(ox + 5, oy + 5, WIDTH_FIGURA - 10, HEIGHT_FIGURA - 10);
        }
        label.repaint();
        label.setForeground((color == Color.BLACK) ? Color.WHITE : Color.BLACK);
        label.setBackground(color);
        g2d.dispose();
    }
}
