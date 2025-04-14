package it.unibs.view.atomicElements;

import javax.swing.*;
import javax.swing.table.*;

import it.unibs.domain.Foglia;
import it.unibs.domain.NestedMap;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.*;

/**
 * Componente personalizzato che mostra una tabella con bordi arrotondati
 * per la visualizzazione dei fattori di conversione tra prestazioni.
 * Include uno scroll panel con header fisso.
 */
public class FattoriConversionePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tabella;
    private DefaultTableModel modelloTabella;
    private NestedMap<Foglia, Foglia, Double> mapFattori;
    private Foglia fogliaSelezionata;
    private int cornerRadius = 25; // Raggio di arrotondamento
    private int padding = 20; // Padding interno
    private JScrollPane scrollPane;
    private int headerFontSize = 30; // Dimensione font dei titoli
    private int cellFontSize = 30;   // Dimensione font delle celle
  
    public FattoriConversionePanel(NestedMap<Foglia, Foglia, Double> mapFattori, Foglia fogliaIniziale) {
        this.mapFattori = mapFattori;
        this.fogliaSelezionata = fogliaIniziale;
        
        setLayout(new BorderLayout());
        inizializzaComponenti();
    }
    
    private void inizializzaComponenti() {
        // Crea la tabella
        String[] colonne = {"Prestazione", "Fattore"};
        modelloTabella = new DefaultTableModel(colonne, 0);
        tabella = new JTable(modelloTabella) {
			private static final long serialVersionUID = 1L;

			@Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                
                // Applica stile alle righe
                if (row % 2 == 0) {
                    comp.setBackground(new Color(250, 250, 250));
                } else {
                    comp.setBackground(new Color(240, 240, 240));
                }
                
                return comp;
            }
        };
        
        // Configura tabella
        applicaStileTabella();
        
        // Configura l'header della tabella
        configuraHeaderTabella();
        
        // Crea lo scroll pane personalizzato
        scrollPane = new JScrollPane(tabella) {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Disegna lo sfondo arrotondato
                g2.setColor(Color.WHITE);
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
                
                // Disegna il bordo arrotondato
                g2.setColor(new Color(150, 150, 150));
                g2.setStroke(new BasicStroke(2f));
                g2.draw(new RoundRectangle2D.Double(1, 1, getWidth()-2, getHeight()-2, cornerRadius, cornerRadius));
                
                g2.dispose();
                
                // Permette la trasparenza per i componenti interni
                paintChildren(g);
            }
        };
        
        // Configura lo scroll pane
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        scrollPane.setBackground(new Color(0, 0, 0, 0));
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
      scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        // Popola la tabella con i dati
        popolaTabella();
        
        // Aggiungi lo scroll pane al pannello
        add(scrollPane, BorderLayout.CENTER);
        
        // Imposta il pannello principale come trasparente
        setOpaque(false);
    }
    
    private void applicaStileTabella() {
        tabella.setRowHeight(50);
        tabella.setShowGrid(false);
        tabella.setFillsViewportHeight(true);
        tabella.setBackground(new Color(0, 0, 0, 0)); // Trasparente
        tabella.setPreferredScrollableViewportSize(new Dimension(1000, 800));
        tabella.setRowMargin(10);
        tabella.setIntercellSpacing(new Dimension(15, 10));
        tabella.setOpaque(false);
        
        // Imposta il padding alle celle
        for (int i = 0; i < tabella.getColumnCount(); i++) {
            final int colonna = i;
            tabella.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
				private static final long serialVersionUID = 1L;

				@Override
                public Component getTableCellRendererComponent(JTable table, Object value, 
                        boolean isSelected, boolean hasFocus, int row, int col) {
                    
                    JLabel label = (JLabel)super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                    label.setFont(new Font("Tahoma", Font.PLAIN, cellFontSize));
                    label.setBorder(BorderFactory.createEmptyBorder(5, padding, 5, padding));
                    
                    // Allineamento del testo
                    if (colonna == 0) {
                        label.setHorizontalAlignment(SwingConstants.LEFT);
                    } else {
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        // Aggiunta del prefisso per il fattore
                        if (value != null && value.toString().length() > 0) {
                            label.setText(value.toString() + " x");
                        }
                    }
                    
                    return label;
                }
            });
        }
        
        // Imposta le dimensioni delle colonne
        tabella.getColumnModel().getColumn(0).setPreferredWidth(700);
        tabella.getColumnModel().getColumn(1).setPreferredWidth(300);
    }
    
    private void configuraHeaderTabella() {
        JTableHeader header = tabella.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, headerFontSize));
        header.setPreferredSize(new Dimension(header.getWidth(), headerFontSize + 20)); // Altezza dell'header basata sulla dimensione del font
        header.setReorderingAllowed(false);
        header.setResizingAllowed(true);
        header.setBackground(new Color(230, 230, 230));
        header.setBorder(BorderFactory.createEmptyBorder());
        
        // Stile dell'header
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                
                label.setFont(new Font("Tahoma", Font.BOLD, headerFontSize));
                
                // Allineamento in base alla colonna
                if (column == 0) {
                    label.setHorizontalAlignment(SwingConstants.LEFT);
                } else {
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                }
                
                label.setBorder(BorderFactory.createEmptyBorder(5, padding, 5, padding));
                label.setBackground(new Color(230, 230, 230));
                return label;
            }
        });
    }
    
    /**
     * Popola la tabella con i fattori di conversione.
     */
    private void popolaTabella() {
        // Ottieni i fattori per la foglia selezionata
        Map<Foglia, Double> fattori = mapFattori.get(fogliaSelezionata);
        
        // Pulisci la tabella
        modelloTabella.setRowCount(0);
        
        if (fattori != null) {
            // Aggiungi righe alla tabella
            for (Map.Entry<Foglia, Double> entry : fattori.entrySet()) {
                Foglia fogliaDestinazione = entry.getKey();
                Double fattore = entry.getValue();
                
                Object[] riga = {fogliaDestinazione.getNome(), String.format("%.2f", fattore)};
                modelloTabella.addRow(riga);
            }
        }
    }
    

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }
    public void setPadding(int padding) {
        this.padding = padding;
        scrollPane.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        applicaStileTabella();
        configuraHeaderTabella();
        repaint();
    }
    public void setHeaderFontSize(int size) {
        this.headerFontSize = size;
        configuraHeaderTabella();
        repaint();
    }
    public void setCellFontSize(int size) {
        this.cellFontSize = size;
        applicaStileTabella();
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Il componente è trasparente, quindi non dipingiamo nulla qui
        // Lo sfondo e il bordo arrotondati vengono dipinti dal JScrollPane
    }
    
  
}