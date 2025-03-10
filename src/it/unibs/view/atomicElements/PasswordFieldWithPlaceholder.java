package it.unibs.view.atomicElements;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PasswordFieldWithPlaceholder extends JPasswordField {
    private String placeholder;
	private Color DEFAULT_PLACEHOLDER_COLOR = Color.GRAY;
    private Color placeholderColor;
    private boolean showingPlaceholder;
    private Color BORDER_COLOR = Color.BLACK;
    private Color DEFAULT_BORDER_FOCUS_COLOR = new Color(8, 102, 255);
    private Color borderFocusColor;
    private int borderThickness = 1;
    private int borderFocusThickness = 2;
    
    public PasswordFieldWithPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        this.borderFocusColor=DEFAULT_BORDER_FOCUS_COLOR;
        this.placeholderColor=DEFAULT_PLACEHOLDER_COLOR;
        
        setBorder(null);
        setFont(new Font("Tahoma", Font.PLAIN, 22));
        
        // Inizialmente mostra il placeholder come testo normale
        setText(placeholder);
        setEchoChar((char) 0); // Non nascondere il testo del placeholder
        setForeground(placeholderColor);
        setRoundedBorder(borderThickness, BORDER_COLOR);
        
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    setEchoChar('•'); // Imposta il carattere di mascheramento quando ottiene il focus
                    setForeground(Color.BLACK);
                    showingPlaceholder = false;
                }
                setRoundedBorder(borderFocusThickness, borderFocusColor); // Bordo più spesso e colorato
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (getPassword().length == 0) {
                    setText(placeholder);
                    setEchoChar((char) 0); // Rimuovi mascheramento
                    setForeground(placeholderColor);
                    showingPlaceholder = true;
                }
                setRoundedBorder(borderThickness, BORDER_COLOR); // Torna al bordo normale
            }
        });
        
        // Aggiungi listener per modifiche al documento
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (showingPlaceholder) {
                    setEchoChar('•');
                    setForeground(Color.BLACK);
                    showingPlaceholder = false;
                }
            }
            //invokeLater per evitare modifiche contemporanee del contenuto, facendo setText("") 
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!showingPlaceholder && !isFocusOwner()) {
                    SwingUtilities.invokeLater(() -> {
                        setText(placeholder);
                        setEchoChar((char) 0); // Rimuove i pallini
                        setForeground(placeholderColor);
                        showingPlaceholder = true;
                        
                    });
                }
            }

            
            @Override
            public void changedUpdate(DocumentEvent e) {
                // Questo metodo non viene chiamato per JTextField/JPasswordField
            }
        });
        
        
        
    }
    
    private void setRoundedBorder(int thickness, Color borderColor) {
        int radius = 15;
        setOpaque(false); 

        Border roundedBorder = BorderFactory.createCompoundBorder(
            new LineBorder(borderColor, thickness, true) {
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

	// Metodo per ottenere la password reale (escludendo il placeholder)
    public char[] getRealPassword() {
        if (showingPlaceholder) {
            return new char[0];
        } else {
            return getPassword();
        }
    }
    public void setBorderFocusColor(Color borderFocusColor) {
		this.borderFocusColor=borderFocusColor;
	}
	public void setBorderFocusColorToDafault() {
		this.borderFocusColor=DEFAULT_BORDER_FOCUS_COLOR;
	}
	
	public void setPlaceholderColor(Color placeholderColor) {
		this.placeholderColor = placeholderColor;
		 if (showingPlaceholder) {
		        setForeground(placeholderColor); // Aggiorna il colore immediatamente
		        repaint(); // Ridisegna il componente
		 }
	}
	public void setPlaceholderColorToDefault() {
		setPlaceholderColor(DEFAULT_PLACEHOLDER_COLOR);
	}

}