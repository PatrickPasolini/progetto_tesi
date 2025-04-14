package it.unibs.view.atomicElements;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Combobox<E> extends JComboBox<E> {
	private static final long serialVersionUID = 1L;
	private Color DEFAULT_PLACEHOLDER_COLOR = Color.GRAY;
    private Color TEXT_COLOR = Color.BLACK;
    private Color BORDER_COLOR = Color.BLACK;
    private Color DEFAULT_BORDER_FOCUS_COLOR = new Color(8, 102, 255); // blu
    private Color borderFocusColor;
    private int BORDER_THICKNESS = 1;
    private int BORDER_FOCUS_THICKNESS = 2;
    
    private String placeholder;
    private Color placeholderColor;
    private boolean showingPlaceholder;
    
    public Combobox(String placeholder) {
        super();
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        this.borderFocusColor = DEFAULT_BORDER_FOCUS_COLOR;
        this.placeholderColor = DEFAULT_PLACEHOLDER_COLOR;
        // Rende la combobox editabile
        setEditable(true);
        setFont(new Font("Arial", Font.BOLD, 22));
        setOpaque(false); 
        
        // Configura l'editor (il JTextField interno)
        JTextField editorComponent = (JTextField) getEditor().getEditorComponent();
        editorComponent.setFont(new Font("Tahoma", Font.PLAIN, 28));
        editorComponent.setForeground(placeholderColor);
        editorComponent.setText(placeholder);
        setRoundedBorder(editorComponent, BORDER_THICKNESS, BORDER_COLOR);
        
        // Gestione del focus per mostrare/rimuovere il placeholder
        editorComponent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    editorComponent.setText("");
                    editorComponent.setForeground(TEXT_COLOR);
                    showingPlaceholder = false;
                }
                setRoundedBorder(editorComponent, BORDER_FOCUS_THICKNESS, borderFocusColor);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (editorComponent.getText().isEmpty()) {
                    editorComponent.setText(placeholder);
                    editorComponent.setForeground(placeholderColor);
                    showingPlaceholder = true;
                }
                setRoundedBorder(editorComponent, BORDER_THICKNESS, BORDER_COLOR);
            }
        });
        
        // Ascoltatore sul documento per gestire le modifiche
        editorComponent.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (showingPlaceholder) {
                    editorComponent.setForeground(TEXT_COLOR);
                    showingPlaceholder = false;
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!showingPlaceholder && !editorComponent.isFocusOwner()) {
                    SwingUtilities.invokeLater(() -> {
                        if (editorComponent.getText().isEmpty()) {
                            editorComponent.setText(placeholder);
                            editorComponent.setForeground(placeholderColor);
                            showingPlaceholder = true;
                        }
                    });
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                // Non necessario per JTextField standard
            }
        });
        
    }
    
    private void setRoundedBorder(JTextField editorComponent, int thickness, Color borderColor) {
        int radius = 15;
        editorComponent.setOpaque(false);
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
        editorComponent.setBorder(roundedBorder);
    }
    
    public String getPlaceholder() {
        return placeholder;
    }
    
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
    
    public void setBorderFocusColor(Color borderFocusColor) {
        this.borderFocusColor = borderFocusColor;
    }
    
    public void setBorderFocusColorToDefault() {
        this.borderFocusColor = DEFAULT_BORDER_FOCUS_COLOR;
    }
    
    public void setPlaceholderColor(Color placeholderColor) {
        this.placeholderColor = placeholderColor;
        JTextField editorComponent = (JTextField) getEditor().getEditorComponent();
        if (showingPlaceholder) {
            editorComponent.setForeground(placeholderColor);
            editorComponent.repaint();
        }
    }
    
    public void setPlaceholderColorToDefault() {
        setPlaceholderColor(DEFAULT_PLACEHOLDER_COLOR);
    }
    
    
    
}
