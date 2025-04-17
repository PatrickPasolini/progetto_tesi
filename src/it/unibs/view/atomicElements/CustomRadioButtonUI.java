package it.unibs.view.atomicElements;
import javax.swing.*;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CustomRadioButtonUI extends BasicRadioButtonUI {
    private final static int CIRCLE_DIAMETER = 24; // Diametro del cerchio esterno
    
    @Override
    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
    }
    
    @Override
    public synchronized void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Posizione del cerchio esterno
        int x = 4;
        int y = (c.getHeight() - CIRCLE_DIAMETER) / 2;
        
        // Disegna il cerchio esterno
        g2.setColor(Color.DARK_GRAY);
        g2.draw(new Ellipse2D.Double(x, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER));
        
        // Disegna il cerchio interno se selezionato
        if (model.isSelected()) {
            g2.setColor(new Color(50, 50, 200)); // Colore blu del cerchio interno
            
            // Calcola dimensione e posizione del cerchio interno
            double innerDiameter = CIRCLE_DIAMETER * 0.6; // 60% del diametro esterno
            double innerX = x + (CIRCLE_DIAMETER - innerDiameter) / 2.0 +0.5;
            double innerY = y + (CIRCLE_DIAMETER - innerDiameter) / 2.0+0.5;
            
            g2.fill(new Ellipse2D.Double(innerX, innerY, innerDiameter, innerDiameter));
        }
        
        // Disegna il testo
        FontMetrics fm = g2.getFontMetrics();
        String text = b.getText();
        int textX = x + CIRCLE_DIAMETER + 8;
        int textY = c.getHeight() / 2 + fm.getAscent() / 2 - 2;
        g2.setColor(b.getForeground());
        g2.drawString(text, textX, textY);
        
        g2.dispose();
    }
    
    @Override
    public Dimension getPreferredSize(JComponent c) {
        AbstractButton b = (AbstractButton) c;
        String text = b.getText();
        FontMetrics fm = c.getFontMetrics(c.getFont());
        int w = fm.stringWidth(text) + CIRCLE_DIAMETER + 16;
//        int h = Math.max(fm.getHeight() + 4, CIRCLE_DIAMETER + 8);
        int h = Math.max(CIRCLE_DIAMETER + 10, fm.getHeight() + 4);

        return new Dimension(w, h);
    }
}