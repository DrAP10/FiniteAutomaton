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
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Transition graphic class
 * 
 * @author DrAP
 */
public class GTransition extends JComponent{
    private int purpose;
    private int origin;
    private int position;
    private int y_center;
    private char simbol;
    private Color color;
    private JLabel label;

    public GTransition(char simbol,int purpose, int origin, int position, 
            int y_center,int width,int height,Color color) {
        this.purpose = purpose;
        this.origin = origin;
        this.position = position;
        this.y_center = y_center;
        this.simbol = simbol;
        this.color = color;
        label = new JLabel(String.valueOf(simbol));
        label.setOpaque(true);
        label.setSize(10, 20);
        label.setForeground(Color.white);
        label.setBackground(new Color(0f,0f,0f,1f));
        this.setBackground(new Color(0f,0f,0f,1f));
        this.setSize(width, height);
        setOpaque(true);
        this.add(label);
    }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawOutline(g);
        }

        private void drawOutline(Graphics g) {
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
            g2d.setStroke(new BasicStroke(2));
            QuadCurve2D q = new QuadCurve2D.Float();
            if (purpose > origin) {
                q.setCurve(origin * position + GState.getFull_size(), y_center,
                        ((origin * position + GState.getFull_size() + purpose * position) / 2),
                        (purpose - origin == 1) ? y_center - 20 : (y_center - ((purpose - origin + 1)) * 50) - 40,
                        purpose * position, y_center);
                Point2D curve_center;
                QuadCurve2D left = new QuadCurve2D.Float();
                QuadCurve2D right = new QuadCurve2D.Float();
                QuadCurve2D.subdivide(q, left, right);
                curve_center = left.getP2();
                label.setLocation((int) curve_center.getX(), (int) curve_center.getY() - 10);
            } else if (origin > purpose) {
                q.setCurve(purpose * position + GState.getFull_size(), y_center,
                        ((purpose * position + GState.getFull_size() + origin * position) / 2),
                        (origin - purpose == 1) ? y_center + 20 : (y_center + ((origin - purpose + 1)) * 50) + 40,
                        origin * position, y_center);
                Point2D curve_center;
                QuadCurve2D left = new QuadCurve2D.Float();
                QuadCurve2D right = new QuadCurve2D.Float();
                QuadCurve2D.subdivide(q, left, right);
                curve_center = left.getP2();
                label.setLocation((int) curve_center.getX(), (int) curve_center.getY() - 10);
            } else if (origin == purpose) {
                g2d.drawOval(origin * position + (GState.getFull_size() / 4) + 2, y_center - (GState.getFull_size() / 6) * 5, 50, 50);
                label.setLocation(origin * position + (GState.getFull_size() / 2) - 2, 2 + y_center - (GState.getFull_size() / 6) * 5);
            }
            g2d.draw(q);
            g2d.dispose();
        }
}
