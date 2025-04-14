package it.unibs.view.atomicElements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

// Classe interna per pannello con bordi arrotondati
    public class RoundedPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int cornerRadius;
        private Color borderColor = new Color(100, 100, 100);
        private int borderThickness = 2;
        private boolean drawShadow;
        private int shadowSize = 5;
        
        public RoundedPanel(int radius, boolean drawShadow) {
            super();
            this.cornerRadius = radius;
            this.drawShadow = drawShadow;
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Disegna l'ombra se richiesto
            if (drawShadow) {
                for (int i = 0; i < shadowSize; i++) {
                    float alpha = (float) (shadowSize - i) / shadowSize * 0.3f;
                    g2.setColor(new Color(0, 0, 0, (int) (alpha * 255)));
                    g2.fill(new RoundRectangle2D.Double(
                            i, i, 
                            getWidth() - i * 2, 
                            getHeight() - i * 2, 
                            cornerRadius + i, cornerRadius + i));
                }
            }
            
            // Disegna lo sfondo
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(
                    0, 0, 
                    getWidth() - shadowSize, 
                    getHeight() - shadowSize, 
                    cornerRadius, cornerRadius));
            
            // Disegna il bordo
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.draw(new RoundRectangle2D.Double(
                    borderThickness / 2, 
                    borderThickness / 2, 
                    getWidth() - shadowSize - borderThickness, 
                    getHeight() - shadowSize - borderThickness, 
                    cornerRadius, cornerRadius));
            
            g2.dispose();
        }
    }