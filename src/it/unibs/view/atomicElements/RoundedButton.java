package it.unibs.view.atomicElements;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
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
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
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
	private Color darkerColor;
	public RoundedButton(String btnText,Color defaultColor) {
	    super(btnText);
	    setHorizontalAlignment(SwingConstants.CENTER);
	    setContentAreaFilled(false);
	    setBorder(new EmptyBorder(5, 0, 5, 0));
	    setBackground(defaultColor);
	    setCursor(new Cursor(Cursor.HAND_CURSOR));
	    setFocusPainted(false);
	    darkerColor = darkenColor(defaultColor, 0.8f); // 80% della luminosità originale
	
	    addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(defaultColor);
			    setBorder(new EmptyBorder(5, 0, 5, 0));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(darkerColor);
				Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
			    setBorder(blackBorder);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
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
     // Disegna il bordo stondato SOLO quando il mouse è sopra
//        if (getBackground().equals(darkerColor)) {  //sembra che rimanga fuori un pixel negli angoli
//        	g2.setStroke(new BasicStroke(2));
//            g2.setColor(Color.BLACK);
//            g2.drawRoundRect(1, 1, width - 3, height - 3, 2, 2);
//        }
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
