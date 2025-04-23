package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import it.unibs.domain.Comprensorio;
import it.unibs.domain.Proposta;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CircleHoverIconButton;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.RoundedButton;
import it.unibs.view.atomicElements.RoundedButtonPlus;
import it.unibs.view.atomicElements.TextFieldWithPlaceholder;

public class ViewNuovoComprensorio extends BaseView {
	private static final long serialVersionUID = 1L;
	private static final String HOME_PATH = "./Img/home.png";
	private JLabel lblNuovoComp;
	private TextFieldWithPlaceholder comprensorioField;
	private TextFieldWithPlaceholder comuneToAddField;
	private RoundedButtonPlus btnPlus;
	private RoundedButton btnConferma;
    private CircleHoverIconButton btnHome;
    private RoundedButton btnSi;
	private RoundedButton btnNo;
	private DefaultListModel<String> listModel;
    private JList<String> comuniList;
	
	public ViewNuovoComprensorio(JFrame frame) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
	}

	@Override
	protected void inizializzaComponenti() {
		lblNuovoComp = new JLabel("Creazione nuovo comprensorio");
		comprensorioField = new TextFieldWithPlaceholder("Nome Comprensorio");
		comuneToAddField = new TextFieldWithPlaceholder("Comune da aggiungere");
		btnPlus = new RoundedButtonPlus(Color.GRAY);
		btnConferma = new RoundedButton("Conferma", new Color(8, 102, 255));	
		listModel = new DefaultListModel<>();
		comuniList = new JList<>(listModel);
		btnHome = new CircleHoverIconButton(HOME_PATH, 50);
		btnSi = new RoundedButton("Crea", new Color(0, 143, 57));
		btnNo = new RoundedButton("Annulla",new Color(165, 32, 25));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        
        lblNuovoComp.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblNuovoComp.getPreferredSize();
        lblNuovoComp.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblNuovoComp);
        
        comprensorioField.setColumns(10);
        comprensorioField.setMargin(new Insets(10, 10, 10, 10));
        comprensorioField.setBounds(contentWidth / 2 - 200, 170, 400, 80);
        contentPanel.add(comprensorioField); 
        
        comuneToAddField.setColumns(10);
        comuneToAddField.setMargin(new Insets(10, 10, 10, 10));
        comuneToAddField.setBounds(contentWidth / 2 - 200, 270, 400, 80);
        contentPanel.add(comuneToAddField); 
        
        btnPlus.setBorder(null);
        btnPlus.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnPlus.setBounds(contentWidth / 2 + 202, 272, 80-4, 80-4);
        btnPlus.setForeground(Color.WHITE);	
        contentPanel.add(btnPlus);
        
		comuniList.setFont(new Font("Tahoma", Font.PLAIN, 28));
		comuniList.setBackground(contentPanel.getBackground());
		JScrollPane scrollPane = new JScrollPane(comuniList);
		scrollPane.setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createLineBorder(Color.BLACK, 1),
			    BorderFactory.createEmptyBorder(10, 20, 10, 10)
			));
		scrollPane.setBounds(contentWidth / 2 - 200, 370, 400, 300);
		scrollPane.setBackground(contentPanel.getBackground());
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		contentPanel.add(scrollPane);

		btnBack.setBounds(45, 45, 90, 90);
		contentPanel.add(btnBack);
		
        btnConferma.setBorder(null);
        btnConferma.setMargin(new Insets(0, 10, 0, 0));
        btnConferma.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnConferma.setBounds(contentWidth / 2 - 200, 700, 400, 90);
        btnConferma.setForeground(Color.WHITE);
        contentPanel.add(btnConferma);
        
        revalidate();
        repaint();
	}
	
	
	public List<String> getComuniInseriti() {
	    List<String> comuni = new ArrayList<>();
	    for (int i = 0; i < listModel.getSize(); i++) {
	        comuni.add(listModel.getElementAt(i));
	    }
	    return comuni;
	}
	
	
	
	public void setCreazioneFallita_NomeNonUnivoco() {
    	lblNuovoComp.setText("Nome comprensorio gia' presente,riprova:");
    	lblNuovoComp.setForeground(Color.RED);
    	comprensorioField.setText("");
    	comprensorioField.setPlaceholderColor(Color.RED);
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    	contentPanel.requestFocusInWindow();
    }
	public void setCreazioneFallita_AlmenoUnComune() {
    	lblNuovoComp.setText("Inserisci almeno un comune nel comprensorio:");
    	lblNuovoComp.setForeground(Color.RED);
    	comuneToAddField.setPlaceholderColor(Color.RED);
    	aggiornaComponenti(frame.getWidth(), frame.getHeight());
    	contentPanel.requestFocusInWindow();
    }
	
	public void visualizzaConfermaCreazione (Comprensorio comprensorio) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    int contentHeight = contentPanel.getHeight();
	    String txtConferma = "<html><div align='center'>"
	    		+ "Vuoi confermare la creazione <br>del comprensorio: "
	    		+ "<span style='color:#085FFF;'><b>" 
	        			+ comprensorio.getName() + "</b></span>, nei comuni:"
	    		+"</div></html>";
        JLabel lblScambio = new JLabel(txtConferma);
    	lblScambio.setFont(new Font("Tahoma", Font.PLAIN, 50));
    	Dimension size = lblScambio.getPreferredSize();
    	lblScambio.setBounds((contentWidth - size.width) / 2, 60, size.width, size.height);
        contentPanel.add(lblScambio);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));
        panel.setBackground(contentPanel.getBackground());
        for (String comune : comprensorio.getComuni()) {
        	JLabel lblComune = new JLabel();
            lblComune.setForeground(Color.BLACK);
            lblComune.setFont(new Font("Tahoma", Font.PLAIN, 35));
            lblComune.setText("    - " + comune);
            lblComune.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            panel.add(lblComune);
        }
		
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBackground(contentPanel.getBackground());
		scrollPane.setBorder(null);
        scrollPane.setBounds(contentWidth / 2 - 260, size.height+120, 520,400);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        contentPanel.add(scrollPane);
        
        
        btnSi.setFont(new Font("Tahoma", Font.BOLD, 38));
        btnSi.setBorder(null);
        btnSi.setMargin(new Insets(0, 10, 0, 0));
        btnSi.setForeground(Color.WHITE);
        btnSi.setBounds(contentWidth / 2 - 260, contentHeight - 200, 250, 120);
	    contentPanel.add(btnSi);
	    
	    btnNo.setFont(new Font("Tahoma", Font.BOLD, 38));
	    btnNo.setBorder(null);
	    btnNo.setMargin(new Insets(0, 10, 0, 0));
	    btnNo.setForeground(Color.WHITE);
	    btnNo.setBounds(contentWidth / 2 + 10, contentHeight - 200, 250, 120);
	    contentPanel.add(btnNo);
        
	    btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
	    
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
	    
	    contentPanel.revalidate();
	    contentPanel.repaint();
	}
	
	
	public void setCreazioneEseguita(Comprensorio comprensorio) {
		frame.setResizable(false);
		contentPanel.removeAll();
		int contentWidth = contentPanel.getWidth();
		
		JLabel lblCreazione = new JLabel();
		lblCreazione.setText("Creazione effettuata con successo");
		lblCreazione.setForeground(new Color(0,143,57));
		lblCreazione.setFont(new Font("Tahoma", Font.BOLD, 50));
        Dimension size = lblCreazione.getPreferredSize();
        lblCreazione.setBounds((contentWidth - size.width) / 2, 60, size.width, 70);
        contentPanel.add(lblCreazione);
        
        JLabel lblNomeComp = new JLabel();
        
        String txt = "<html>Comprensorio <span style='color:#085FFF;'><b>" 
        			+ comprensorio.getName() + "</b></span>, nei comuni:</html>";
        lblNomeComp.setText(txt);
        lblNomeComp.setForeground(Color.BLACK);
        lblNomeComp.setFont(new Font("Tahoma", Font.BOLD, 40));
        Dimension size2 = lblNomeComp.getPreferredSize();
        lblNomeComp.setBounds((contentWidth - size2.width) / 2, 190, size2.width, 70);
        contentPanel.add(lblNomeComp);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(20));
        panel.setBackground(contentPanel.getBackground());
        for (String comune : comprensorio.getComuni()) {
        	JLabel lblComune = new JLabel();
            lblComune.setForeground(Color.BLACK);
            lblComune.setFont(new Font("Tahoma", Font.PLAIN, 35));
            lblComune.setText("    - " + comune);
            lblComune.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            lblComune.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
            panel.add(lblComune);
        }
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBackground(contentPanel.getBackground());

		scrollPane.setBorder(null);
        scrollPane.setBounds(contentWidth / 2 - 260, 190+90, 520, 500);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		contentPanel.add(scrollPane);
        


		
		btnHome.setBounds(45, 45, 90, 90);
		contentPanel.add(btnHome);
		
        revalidate();
        repaint();
	}
	public void aggiornaListaComuni(List<String> comuni) {
	    listModel.clear();
    	comuneToAddField.setPlaceholderColor(Color.GRAY);//x togliere il rosso se prima errore
	    for (String comune : comuni) {
	        listModel.addElement("- "+comune);
	    }
	    comuneToAddField.setText("");
	}

	public void setBtnPlusListener(ActionListener listener) {
		btnPlus.addActionListener(listener);
	}
	public void setBtnCreazioneListener(ActionListener listener) {
		btnConferma.addActionListener(listener);
	}
	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener);
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
	
	
	public String getComuneDaAggiungere() {
	    return comuneToAddField.getText();
	}
	public String getPlaceholderComune() {
	    return comuneToAddField.getPlaceholder();
	}
	public String getNomeComprensorio() {
		return comprensorioField.getText();
	}
	public String getPlaceholderComp() {
		return comprensorioField.getPlaceholder();
	}
}
