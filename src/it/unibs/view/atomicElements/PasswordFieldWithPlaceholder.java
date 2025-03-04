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
    private boolean showingPlaceholder;
    private Color BORDER_COLOR = Color.BLACK;    // Colore del bordo normale
    private Color BORDER_FOCUS_COLOR = new Color(8, 102, 255); // Colore del bordo in focus
    private int borderThickness = 1; // Spessore del bordo normale
    private int borderFocusThickness = 2; // Spessore del bordo  in focus
    
    public PasswordFieldWithPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        
        setBorder(null);
        setFont(new Font("Tahoma", Font.PLAIN, 22));
        
        // Inizialmente mostra il placeholder come testo normale
        setText(placeholder);
        setEchoChar((char) 0); // Non nascondere il testo del placeholder
        setForeground(Color.GRAY);
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
                setRoundedBorder(borderFocusThickness, BORDER_FOCUS_COLOR); // Bordo più spesso e colorato
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (getPassword().length == 0) {
                    setText(placeholder);
                    setEchoChar((char) 0); // Rimuovi mascheramento
                    setForeground(Color.GRAY);
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
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (getPassword().length == 0 && !showingPlaceholder && !isFocusOwner()) {
                    setText(placeholder);
                    setEchoChar((char) 0);
                    setForeground(Color.GRAY);
                    showingPlaceholder = true;
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
    

}