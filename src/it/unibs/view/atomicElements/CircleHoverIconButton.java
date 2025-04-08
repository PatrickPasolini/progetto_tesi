package it.unibs.view.atomicElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Pulsante personalizzato che mostra un'icona e visualizza un cerchio scuro
 * attorno all'icona quando il mouse passa sopra.
 */
public class CircleHoverIconButton extends JButton {
    private static final long serialVersionUID = 1L;
    private boolean isHovered = false;
    Image arrowLeftScaledImage;
//    ImageIcon arrowLeftIcon;
    private int iconSize;
    public CircleHoverIconButton(String path, int iconSize) {
        this.iconSize = iconSize;
        ImageIcon arrowLeftIconOriginal = new ImageIcon(path);
        arrowLeftScaledImage = arrowLeftIconOriginal.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        ImageIcon arrowLeftIcon = new ImageIcon(arrowLeftScaledImage);
        
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        
        // Aggiungi gli ascoltatori per gli eventi del mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w = getWidth();
        int h = getHeight();
        
        // Disegna il cerchio scuro se il mouse è sopra
        if (isHovered) {
            g2d.setColor(new Color(0, 0, 0, 30)); // Nero semi-trasparente
            int circleDiameter = Math.min(w, h);
            int x = (w - circleDiameter) / 2;
            int y = (h - circleDiameter) / 2;
            g2d.fillOval(x, y, circleDiameter, circleDiameter);
        }
        
        // Disegna l'icona centrata
        int x = (w -iconSize) / 2;
        int y = (h  -iconSize)/ 2;
        g2d.drawImage(arrowLeftScaledImage, x, y, this);
        
        g2d.dispose();
    }
    
}