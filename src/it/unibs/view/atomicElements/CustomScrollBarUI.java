package it.unibs.view.atomicElements;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI {

    private static final int ARROW_BUTTON_SIZE = 16;

    @Override
    protected void configureScrollBarColors() {
        thumbColor = Color.GRAY;  // Colore della barra scorrevole
        trackColor = new Color(230, 230, 230);  // Colore dello sfondo della scrollbar
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new ArrowButton(orientation);
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new ArrowButton(orientation);
    }

    // Inner class per disegnare i triangoli centrati e con rollover
    private class ArrowButton extends JButton {
        private final int orientation;

        public ArrowButton(int orientation) {
            this.orientation = orientation;
            setPreferredSize(new Dimension(ARROW_BUTTON_SIZE, ARROW_BUTTON_SIZE));
            setMinimumSize(new Dimension(ARROW_BUTTON_SIZE, ARROW_BUTTON_SIZE));
            setMaximumSize(new Dimension(ARROW_BUTTON_SIZE, ARROW_BUTTON_SIZE));
            setOpaque(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            // Abilita rollover per il modello
            setRolloverEnabled(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // Non chiamare super per evitare sfondo bianco
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth();
            int h = getHeight();
            int arrowSize = Math.min(w, h) / 2;  // Dimensione del triangolo
            Polygon arrow = new Polygon();
            switch (orientation) {
                case SwingConstants.NORTH:
                    arrow.addPoint(w/2, (h - arrowSize) / 2);
                    arrow.addPoint((w - arrowSize) / 2, (h + arrowSize) / 2);
                    arrow.addPoint((w + arrowSize) / 2, (h + arrowSize) / 2);
                    break;
                case SwingConstants.SOUTH:
                    arrow.addPoint((w - arrowSize) / 2, (h - arrowSize) / 2);
                    arrow.addPoint((w + arrowSize) / 2, (h - arrowSize) / 2);
                    arrow.addPoint(w/2, (h + arrowSize) / 2);
                    break;
                case SwingConstants.WEST:
                    arrow.addPoint((w + arrowSize) / 2, (h - arrowSize) / 2);
                    arrow.addPoint((w + arrowSize) / 2, (h + arrowSize) / 2);
                    arrow.addPoint((w - arrowSize) / 2, h/2);
                    break;
                case SwingConstants.EAST:
                    arrow.addPoint((w - arrowSize) / 2, (h - arrowSize) / 2);
                    arrow.addPoint((w - arrowSize) / 2, (h + arrowSize) / 2);
                    arrow.addPoint((w + arrowSize) / 2, h/2);
                    break;
            }
            // Colore più chiaro quando non in rollover, più scuro se il mouse è sopra
            Color arrowColor = getModel().isRollover() ? Color.DARK_GRAY : new Color(180, 180, 180);
            g2d.setColor(arrowColor);
            g2d.fill(arrow);
            g2d.dispose();
        }
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(thumbColor);
        g2d.fillRoundRect(thumbBounds.x + 3, thumbBounds.y, thumbBounds.width - 6, thumbBounds.height, 10, 10);
        g2d.dispose();
    }
}
