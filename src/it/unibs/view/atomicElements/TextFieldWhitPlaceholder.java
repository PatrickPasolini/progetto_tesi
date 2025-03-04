package it.unibs.view.atomicElements;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextFieldWhitPlaceholder extends JTextField {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color PLACEHOLDER_COLOR = Color.GRAY;
    private Color TEXT_COLOR = Color.BLACK;
    private Color BORDER_COLOR = Color.BLACK;
    private Color BORDER_FOCUS_COLOR = new Color(8, 102, 255);//blu
    private int BORDER_THICKNESS = 1;
    private int BORDER_FOCUS_THICKNESS = 2;
    
    private String placeholder;
    private boolean showingPlaceholder;

    public TextFieldWhitPlaceholder(String placeholder) {
        this.setPlaceholder(placeholder);
        this.showingPlaceholder = true;

        setFont(new Font("Tahoma", Font.PLAIN, 22));
        setForeground(PLACEHOLDER_COLOR);
        setText(placeholder);
        
        setRoundedBorder(BORDER_THICKNESS, BORDER_COLOR);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    setForeground(TEXT_COLOR);
                    showingPlaceholder = false;
                }
                setRoundedBorder(BORDER_FOCUS_THICKNESS, BORDER_FOCUS_COLOR); // Bordo più spesso e colorato
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(PLACEHOLDER_COLOR);
                    showingPlaceholder = true;
                }
                setRoundedBorder(BORDER_THICKNESS, BORDER_COLOR); // Torna al bordo normale
            }
        });

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (showingPlaceholder) {
                    setForeground(TEXT_COLOR);
                    showingPlaceholder = false;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!showingPlaceholder && !isFocusOwner()) {
                    setText(placeholder);
                    setForeground(PLACEHOLDER_COLOR);
                    showingPlaceholder = true;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Questo metodo non viene chiamato per JTextField
            }
        });
    }

    private void setRoundedBorder(int thickness, Color borderColor) {
        int radius = 15;
        setOpaque(false); 

        Border roundedBorder = BorderFactory.createCompoundBorder(
            new LineBorder(borderColor, thickness, true) {
				private static final long serialVersionUID = 1L;

				@Override
                public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(borderColor);
                    g2.setStroke(new BasicStroke(thickness));
                    g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
                    g2.dispose();
                }
            },
            BorderFactory.createEmptyBorder(2, 8, 2, 8)
        );
        setBorder(roundedBorder);
    }

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
}
