package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import it.unibs.domain.Foglia;
import it.unibs.domain.Gerarchia;
import it.unibs.domain.NonFoglia;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CircleHoverIconButton;
import it.unibs.view.atomicElements.CustomRadioButtonUI;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.CustomTree;
import it.unibs.view.atomicElements.NumericFieldWithPlaceholder;
import it.unibs.view.atomicElements.RoundedButton;
import it.unibs.view.atomicElements.TextFieldWithPlaceholder;

public class ViewAddGerarchiaFoglia extends ViewAddGerarchia {
	private static final String ARROWLEFT_PATH = "./Img/arrowLeft.png";
	private static final String HOME_PATH = "./Img/home.png";
	private static final long serialVersionUID = 1L;
	private JLabel lblRadice;
	private TextFieldWithPlaceholder nomeField;
	private TextFieldWithPlaceholder descrizioneField;
	private RoundedButton btnAvanti;
	private RoundedButton btnTerminazione;
	private RoundedButton btnNodo;
	private RoundedButton btnFdc;
	private RoundedButton btnSi;
	private RoundedButton btnNo;
	private NonFoglia categoria;
	private NumericFieldWithPlaceholder fdcField;
	
	private JLabel lblSceltaFoglia;
    private CircleHoverIconButton btnBack;
    private CircleHoverIconButton btnHome;
	private JTree tree;
	private JLabel lblSceltaCategoria; 
	
	private boolean terminabile; 
	
	public ViewAddGerarchiaFoglia(JFrame frame, NonFoglia categoria) {
		super(frame);
		this.categoria = categoria;
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		super.inizializzaComponenti();
		lblRadice = new JLabel();
		lblSceltaCategoria = new JLabel();
		nomeField = new TextFieldWithPlaceholder("Nome prestazione specifica");
		descrizioneField = new TextFieldWithPlaceholder("Descrizione opzionale");
		btnAvanti = new RoundedButton("Avanti", new Color(8, 102, 255));
		btnTerminazione = new RoundedButton("Termina creazione", new Color(0, 143, 57));
		btnFdc = new RoundedButton("Conferma fattore", new Color(8, 102, 255));
		btnNodo= new RoundedButton("Conferma e prosegui", new Color(8, 102, 255));
		
		lblSceltaFoglia = new JLabel("Seleziona la prestazione d'opera che necessiti:");
		btnBack = new CircleHoverIconButton(ARROWLEFT_PATH, 50);
		btnHome = new CircleHoverIconButton(HOME_PATH, 50);
		
		btnSi = new RoundedButton("Crea", new Color(0, 143, 57));
		btnNo = new RoundedButton("Annulla", new Color(165, 32, 25));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        if(categoria!=null) {
        	 String  txt = "<html><div align='center'>" 
     				+ "Inserisci i dati della categoria di prestazione<br>"
     				+ "che stai aggiungendo alla categoria <span style='color:#085FFF;'>" + categoria.getNome()  
     				+ "</span></div></html>";
             
             lblRadice.setText(txt);
             lblRadice.setFont(new Font("Tahoma", Font.BOLD, 55));
             Dimension size = lblRadice.getPreferredSize();
             lblRadice.setBounds((contentWidth - size.width) / 2, 70, size.width, size.height);
             contentPanel.add(lblRadice);
        }
       
        
        nomeField.setColumns(10);
        nomeField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        nomeField.setMargin(new Insets(10, 10, 10, 10));
        nomeField.setBounds(contentWidth / 2 - 250, (contentHeight-240)/2, 500, 100);
        contentPanel.add(nomeField); 
	        																																																	
        descrizioneField.setColumns(10);
        descrizioneField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        descrizioneField.setMargin(new Insets(10, 10, 10, 10));
        descrizioneField.setBounds(contentWidth / 2 - 250, (contentHeight+40)/2, 500, 100);
        contentPanel.add(descrizioneField); 
        
        btnAvanti.setBorder(null);
        btnAvanti.setMargin(new Insets(0, 10, 0, 0));
        btnAvanti.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnAvanti.setBounds(contentWidth / 2 - 200, contentHeight-280, 400, 100);
        btnAvanti.setForeground(Color.WHITE);
        contentPanel.add(btnAvanti);
        
        
        
        
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	public void visualizzaSceltaNodo(List<Gerarchia> gerarchiaInCostruzione) {
		super.visualizzaSceltaNodo(gerarchiaInCostruzione);
	    int contentWidth = contentPanel.getWidth();
        
        if (terminabile) {
        	contentPanel.remove(super.lblSceltaCategoria);
        	contentPanel.remove(super.scrollPane);
        	
        	super.lblSceltaCategoria.setText("<html><div align='center'>" +
					"Seleziona una categoria di prestazione a cui <br>" +
					"aggiungere una categoria o una prestazione specifica<br>"
					+ "oppure termina la creazione</div></html>");
        	Dimension size = super.lblSceltaCategoria.getPreferredSize();
        	super.lblSceltaCategoria.setBounds((contentWidth - size.width) / 2, 30, size.width, size.height);
            contentPanel.add(super.lblSceltaCategoria);
        	
            scrollPane.setBounds(contentPanel.getWidth()/2-400, size.height+50, 800, 650 - size.height - 50);
            contentPanel.add(scrollPane);
            
        	btnTerminazione.setBorder(null);
	   	    btnTerminazione.setMargin(new Insets(0, 10, 0, 0));
	   	    btnTerminazione.setFont(new Font("Tahoma", Font.BOLD, 28));
	   	    btnTerminazione.setBounds(contentWidth  - 400, 760, 350, 90);
	   	    btnTerminazione.setForeground(Color.WHITE);
	   	    contentPanel.add(btnTerminazione);
	    }
        
	    contentPanel.revalidate();
	    contentPanel.repaint();
	}
	
	public void visualizzaSceltaFogliaFDC(List<Gerarchia> gerarchiaInCostruzione, Foglia foglia) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    
	    lblSceltaCategoria.setText("<html><div align='center'>" +
				"Selezionare una prestazione esistente<br>"
				+ "con la quale impostare il fattore di conversione<br>"+
				" relativo alla nuova prestazione "
				+ "<span style='color:#085FFF;'>"+foglia.getNome()+
				"</span></div></html>");
        lblSceltaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 55));
        Dimension size = lblSceltaCategoria.getPreferredSize();
        lblSceltaCategoria.setBounds((contentWidth - size.width) / 2, 30, size.width, size.height);
        contentPanel.add(lblSceltaCategoria);
        
        if (gerarchiaInCostruzione != null && !gerarchiaInCostruzione.isEmpty()) {
	        tree = CustomTree.createUnifiedTree(gerarchiaInCostruzione,true,true);
	        tree.setBackground(contentPanel.getBackground());
	        tree.setToggleClickCount(1);
	        tree.setEditable(false);
        }
        
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(contentPanel.getBackground());
        scrollPane.setBounds(contentPanel.getWidth()/2-400, size.height+50, 800, 450);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        contentPanel.add(scrollPane);

		btnNodo.setBorder(null);
	    btnNodo.setMargin(new Insets(0, 10, 0, 0));
	    btnNodo.setFont(new Font("Tahoma", Font.BOLD, 28));
	    btnNodo.setBounds(contentWidth / 2 - 225, 760, 450, 90);
	    btnNodo.setForeground(Color.WHITE);
	    contentPanel.add(btnNodo);
	    
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	
	
	
	public void visualizzaSceltaFogliaFDC(String fogliaInCreazione, String fogliaFdc, double min , double max) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    
	    double minRounded = new BigDecimal(min) // per eccesso 
	            .setScale(3, RoundingMode.CEILING)
	            .doubleValue();
	    double maxRounded = new BigDecimal(max) // per difetto 
	            .setScale(3, RoundingMode.FLOOR)
	            .doubleValue();

	    // formattazione con esattamente 3 decimali (virgola secondo locale italiano)
	    String formattedMin = String.format(Locale.ITALY, "%.3f", minRounded);
	    String formattedMax = String.format(Locale.ITALY, "%.3f", maxRounded);
	    
	    lblSceltaCategoria.setText("<html><div align='center'>"
				+ "Inserisci il fattore di conversione"
				+ "<br>dalla prestazione <span style='color:#085FFF;'>" + fogliaInCreazione
				+ "</span><br>alla prestazione <span style='color:#085FFF;'>" + fogliaFdc
				+ "</span><br>inserisci un valore compresto tra " + formattedMin 
				+ " e " + formattedMax
				+ "</div></html>");
        lblSceltaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 55));
        Dimension size = lblSceltaCategoria.getPreferredSize();
        lblSceltaCategoria.setBounds((contentWidth - size.width) / 2, 30, size.width, size.height);
        contentPanel.add(lblSceltaCategoria);
        
        fdcField = new NumericFieldWithPlaceholder("fattore di conversione",true,min,max);
        fdcField.setColumns(10);
        fdcField.setMargin(new Insets(10, 10, 10, 10));
        fdcField.setFont(new Font("Tahoma", Font.PLAIN, 35));
        fdcField.setBounds(contentWidth / 2 - 260, size.height+200, 520, 120);
        contentPanel.add(fdcField); 
		
		
		btnFdc.setBorder(null);
		btnFdc.setMargin(new Insets(0, 10, 0, 0));
		btnFdc.setFont(new Font("Tahoma", Font.BOLD, 35));
		btnFdc.setBounds(contentWidth / 2 - 225, 700, 450, 120);
		btnFdc.setForeground(Color.WHITE);
	    contentPanel.add(btnFdc);
        
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	
	public void confermaTerminazione(List<Gerarchia> gerarchiaInCostruzione) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    
	    lblSceltaCategoria.setText("<html><div align='center'>" +
				"Vuoi confermare la creazione<br> della seguente gerarchia<br>"
				+ "</span></div></html>");
        lblSceltaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 55));
        Dimension size = lblSceltaCategoria.getPreferredSize();
        lblSceltaCategoria.setBounds((contentWidth - size.width) / 2, 50, size.width, size.height);
        contentPanel.add(lblSceltaCategoria);
        
        if (gerarchiaInCostruzione != null && !gerarchiaInCostruzione.isEmpty()) {
	        tree = CustomTree.createUnifiedTree(gerarchiaInCostruzione,false,true);
	        tree.setBackground(contentPanel.getBackground());
	        tree.setToggleClickCount(1);
	        tree.setEditable(false);
        }
        
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(contentPanel.getBackground());
        scrollPane.setBounds(contentPanel.getWidth()/2-400, size.height+50, 800, 450);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        contentPanel.add(scrollPane);

        btnSi.setFont(new Font("Tahoma", Font.BOLD, 33));
        btnSi.setBorder(null);
        btnSi.setMargin(new Insets(0, 10, 0, 0));
        btnSi.setForeground(Color.WHITE);
        btnSi.setBounds(contentWidth / 2 - 260, size.height+550, 250, 120);
	    contentPanel.add(btnSi);
	    
	    btnNo.setFont(new Font("Tahoma", Font.BOLD, 33));
	    btnNo.setBorder(null);
	    btnNo.setMargin(new Insets(0, 10, 0, 0));
	    btnNo.setForeground(Color.WHITE);
	    btnNo.setBounds(contentWidth / 2 + 10, size.height+550, 250, 120);
	    contentPanel.add(btnNo);
	    
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	
	public void creazioneConclusa(List<Gerarchia> gerarchiaInCostruzione) {
		confermaTerminazione(gerarchiaInCostruzione);

		int contentWidth = contentPanel.getWidth();
		lblSceltaCategoria.setText("<html><div align='center'>" +
				"Creazione della seguente gerarchia <br>effettuata con successo<br>"
				+ "</span></div></html>");
		 Dimension size = lblSceltaCategoria.getPreferredSize();
        lblSceltaCategoria.setBounds((contentWidth - size.width) / 2, 30, size.width, size.height);
        contentPanel.add(lblSceltaCategoria);
		
		
		contentPanel.remove(btnNo);
		contentPanel.remove(btnSi);
		
		revalidate();
	    repaint();
	}
	
	
	
	
	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener);
	} 
	public void setBtnBackListeners(ActionListener btnListener) {
		for (ActionListener al : btnBack.getActionListeners()) {
			btnBack.removeActionListener(al);
		}
		btnBack.addActionListener(btnListener);
    }
	public void setBtnAvantiListener(ActionListener listener) {
		for (ActionListener al : btnAvanti.getActionListeners()) {
			btnAvanti.removeActionListener(al);
		}
		btnAvanti.addActionListener(listener);
	}
	public void setBtnTerminaListener(ActionListener listener) {
		for (ActionListener al : btnTerminazione.getActionListeners()) {
			btnTerminazione.removeActionListener(al);
		}
		btnTerminazione.addActionListener(listener);
	}
	public void setBtnNodoListener(ActionListener listener) {
		for (ActionListener al : btnNodo.getActionListeners()) {
			btnNodo.removeActionListener(al);
		}
		btnNodo.addActionListener(listener);
	}
	public void setBtnFdcListener(ActionListener listener) {
		for (ActionListener al : btnFdc.getActionListeners()) {
			btnFdc.removeActionListener(al);
		}
		btnFdc.addActionListener(listener);
	}
	
	public void setNomeNellaGerarchiaNonUnivoco(ViewAddGerarchiaFoglia viewAddGerarchiaRadice) {
		lblRadice.setForeground(Color.RED);
		lblRadice.setText("<html><div align='center'>Nome della categoria iniziale é giá in uso,<br> scegline un altro!</div></html>");  
	    Dimension size = lblRadice.getPreferredSize();
	    int contentWidth = contentPanel.getWidth();
		lblRadice.setBounds((contentWidth - size.width) / 2, 70, size.width, size.height);
	    revalidate();
	    repaint();
	}
	public void setBtnConfermaCreazione(ActionListener listener) {
	    for (ActionListener al : btnSi.getActionListeners()) {
	        btnSi.removeActionListener(al);
	    }
	    for (ActionListener al : btnNo.getActionListeners()) {
	        btnNo.removeActionListener(al);
	    }
	    btnSi.setActionCommand("true");
	    btnNo.setActionCommand("false");
	    btnSi.addActionListener(listener);
	    btnNo.addActionListener(listener);
	}
	public void setTerminabile(boolean value) {
		this.terminabile = value;
	}
	
	public Foglia getFogliaSelezionataa() {
        // Recupera il percorso di selezione nel tree
        TreePath selectionPath = tree.getSelectionPath();
        if (selectionPath != null) {
            // Ottiene l'ultimo nodo del percorso
            Object lastComponent = selectionPath.getLastPathComponent();
            if (lastComponent instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) lastComponent;
                Object userObject = node.getUserObject();
                // Verifica se l'oggetto utente è una Foglia (ipotizzando che Foglia estenda o sia compatibile con Categoria)
                if (userObject instanceof Foglia) {
                    return (Foglia) userObject;
                }
            }
        }
        return null;
    }
	
	public String getNomeField() {
		return nomeField.getText();
	}
	public String getDescrizioneField() {
		return descrizioneField.getText();
	}
	public String getNomePlaceholder() {
		return nomeField.getPlaceholder();
	}
	
	public double getFdc() {
		return (double) fdcField.getNumericValue();
	}
}
