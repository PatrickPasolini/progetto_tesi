package it.unibs.view.atomicElements;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RoundedButton extends JButton {
	public Color getEffectColor() {
	    return effectColor;
	}
	
	public void setEffectColor(Color effectColor) {
	    this.effectColor = effectColor;
	}
	
	private float animatSize;
	private Point pressedPoint;
	private Color effectColor = new Color(255, 255, 255);
	
	public RoundedButton(String btnText,Color defaultColor) {
	    super(btnText);
	    setHorizontalAlignment(SwingConstants.CENTER);
	    setContentAreaFilled(false);
	    setBorder(new EmptyBorder(5, 0, 5, 0));
	    setBackground(defaultColor);
	    setCursor(new Cursor(Cursor.HAND_CURSOR));
	    setFocusPainted(false);
	    Color darkerColor = darkenColor(defaultColor, 0.9f); // 80% della luminosità originale
	
	    addFocusListener(new FocusListener() {
	        @Override
	        public void focusGained(FocusEvent e) {
	        	 setBackground(darkerColor);
	        }
	
	        @Override
	        public void focusLost(FocusEvent e) {
	        	setBackground(defaultColor);
	        }
	    });
	
	}
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 5, 5);
        if (pressedPoint != null) {
            g2.setColor(effectColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 15));
            g2.fillOval((int) (pressedPoint.x - animatSize / 2), (int) (pressedPoint.y - animatSize / 2), (int) animatSize, (int) animatSize);
        }
        g2.dispose();
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g);
	}
	
	 private static Color darkenColor(Color color, float factor) {
        int r = Math.max((int) (color.getRed() * factor), 0);
        int g = Math.max((int) (color.getGreen() * factor), 0);
        int b = Math.max((int) (color.getBlue() * factor), 0);
        return new Color(r, g, b);
    }
}
