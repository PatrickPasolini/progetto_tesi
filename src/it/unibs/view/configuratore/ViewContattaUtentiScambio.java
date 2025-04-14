package it.unibs.view.configuratore;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.LineBorder;

import it.unibs.domain.Foglia;
import it.unibs.domain.Fruitore;
import it.unibs.domain.Proposta;
import it.unibs.domain.Scambio;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CircleHoverIconButton;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewContattaUtentiScambio extends BaseView {
	private static final long serialVersionUID = 1L;
	private List<String> nomiScambi;
	private JComboBox<String> scambiComboBox;
    private JPanel dettagliScambioPanel;
    private RoundedButton selezionaButton;
    private JLabel titoloScambioLabel;
    private CircleHoverIconButton btnHome;
	protected static final String HOME_PATH = "./Img/home.png";
    
	public ViewContattaUtentiScambio(JFrame frame, ArrayList<String> nomiScambi) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.nomiScambi = nomiScambi;
		aggiornaComponenti(frame.getWidth(),frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		btnHome = new CircleHoverIconButton(HOME_PATH, 50);
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
	    contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    
	    if(nomiScambi==null)
	        return;
	                
	    JPanel topPanel = createTopPanel();
	    topPanel.setBounds(300, 50, contentWidth-600, 150);
	    topPanel.setBackground(contentPanel.getBackground());
	    contentPanel.add(topPanel);
	    
	    dettagliScambioPanel = new JPanel();
	    dettagliScambioPanel.setLayout(new BoxLayout(dettagliScambioPanel, BoxLayout.Y_AXIS));
	    dettagliScambioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
	    titoloScambioLabel = new JLabel("Seleziona uno scambio per vedere i dettagli");
	    titoloScambioLabel.setFont(new Font("Arial", Font.PLAIN, 30));
	    titoloScambioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    dettagliScambioPanel.add(titoloScambioLabel);
	    
	    JScrollPane scrollPane = new JScrollPane(dettagliScambioPanel);
	    scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
	    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
	    scrollPane.setBounds(300, 220, contentWidth-600, 600);
	    contentPanel.add(scrollPane);
	    
	    btnHome.setBounds(45, 45, 90, 90);
	    contentPanel.add(btnHome);
	    
	    JSeparator separator = new JSeparator();
	    separator.setForeground(new Color(180, 180, 180));
	    separator.setBounds(300, 200, contentWidth-600, 400);
	    contentPanel.add(separator);
	    
	    revalidate();
	    repaint();
	}
	
	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener);
	} 
	
	private JPanel createTopPanel() {
	    JPanel topPanel = new JPanel();
	    topPanel.setLayout(new BorderLayout(10, 10));
	    
	    // Titolo
	    JLabel titleLabel = new JLabel("Contatta gli utenti di uno scambio");
	    titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
	    titleLabel.setHorizontalAlignment(JLabel.CENTER);
	    
	    JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    titlePanel.setBackground(contentPanel.getBackground());
	    titlePanel.add(titleLabel);
	    
	    topPanel.add(titlePanel, BorderLayout.NORTH);
	    
	    // Pannello per la selezione dello scambio
	    JPanel selectionPanel = new JPanel();
	    selectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
	    selectionPanel.setBackground(contentPanel.getBackground());
	    
	    JLabel scambioLabel = new JLabel("Scelta scambio:");
	    scambioLabel.setFont(new Font("Arial", Font.PLAIN, 35));
	    selectionPanel.add(scambioLabel);
	    
	    List<String> scambiOptionsList = new ArrayList<>();
	    
	    for (String nome : nomiScambi) {
	        scambiOptionsList.add(nome);
	    }
	    String[] scambiOptions = scambiOptionsList.toArray(new String[0]);
	    scambiComboBox = new JComboBox<>(scambiOptions);
	    scambiComboBox.setFont(new Font("Arial", Font.PLAIN, 25));
	    scambiComboBox.setBorder(null);
	    scambiComboBox.setPreferredSize(new Dimension(200, 50));
	    selectionPanel.add(scambiComboBox);
	    
	    selezionaButton = new RoundedButton("Seleziona", new Color(8, 102, 255));
        selezionaButton.setForeground(Color.WHITE);
        selezionaButton.setFont(new Font("Arial", Font.PLAIN, 30));
        selezionaButton.setPreferredSize(new Dimension(150, 50));
       
        selectionPanel.add(selezionaButton);
	    
	    topPanel.add(selectionPanel, BorderLayout.CENTER);
	    
	   
	    return topPanel;
	}
	
	
	public void setBtnSelezioneListener(ActionListener listener) {
		selezionaButton.addActionListener(listener);
	}
	
	
	public int getIndexScambioSelezionato() {
		return scambiComboBox.getSelectedIndex();
	}


	public void mostraDettagliScambio(Scambio scambioSelezionato) {
	    dettagliScambioPanel.removeAll();
	    
	    String txtTitoloScambio = "<html>Scambio: <font color='0866FF'>"+scambioSelezionato.getNome()+"</font></html>";
	    
	    titoloScambioLabel = new JLabel(txtTitoloScambio);
	    titoloScambioLabel.setFont(new Font("Arial", Font.BOLD, 30));
	    titoloScambioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    dettagliScambioPanel.add(titoloScambioLabel);
	    dettagliScambioPanel.add(Box.createVerticalStrut(10));
	    
	    if (scambioSelezionato != null) {
	        Stack<Proposta> proposte = scambioSelezionato.getScambio();
	        if (proposte != null && !proposte.isEmpty()) {
	            for (Proposta p : proposte) {
	                JPanel utentePanel = createUtentePanel(scambioSelezionato,p);
	                utentePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	                dettagliScambioPanel.add(utentePanel);
	                dettagliScambioPanel.add(Box.createVerticalStrut(10));
	            }
	        } else {
	            JLabel noProposteLabel = new JLabel("Nessuna proposta disponibile per questo scambio");
	            noProposteLabel.setFont(new Font("Arial", Font.BOLD, 50));
	            noProposteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	            dettagliScambioPanel.add(noProposteLabel);
	        }
	    }
	    
	    dettagliScambioPanel.revalidate();
	    dettagliScambioPanel.repaint();
	}
	
	private JPanel createUtentePanel(Scambio scambioSelezionato,Proposta proposta) {
	    JPanel utentePanel = new JPanel(new BorderLayout());
	    utentePanel.setBorder(BorderFactory.createCompoundBorder(
	        new LineBorder(new Color(180, 180, 180), 1, true),
	        BorderFactory.createEmptyBorder(10, 10, 10, 10)
	    ));
	    utentePanel.setBackground(new Color(250, 250, 250));
	    utentePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    JLabel utenteLabel = new JLabel(stampaScambio(scambioSelezionato, proposta));
	    utenteLabel.setFont(new Font("Arial", Font.PLAIN, 30));
	    utenteLabel.setVerticalAlignment(JLabel.CENTER);
	    utenteLabel.setHorizontalAlignment(JLabel.LEFT);
	    
	    utentePanel.add(utenteLabel, BorderLayout.CENTER);
	    
	    return utentePanel;
	}
	
	
	private String stampaScambio(Scambio scambio, Proposta proposta) {
	    StringBuilder sb = new StringBuilder();
	    
	    Fruitore utente = (Fruitore)proposta.getFruitoreRichiedente();
	    Foglia richiesta = proposta.getRichiesta();
	    Foglia offerta = proposta.getOfferta();
	    Fruitore ricevente = null;
	    Fruitore offerente = null;
	    
	    for(Proposta p2 : scambio.getScambio()) {
	        if(offerta.equals(p2.getRichiesta()))
	            ricevente = (Fruitore)p2.getFruitoreRichiedente();
	        
	        if(richiesta.equals(p2.getOfferta()))
	            offerente = (Fruitore)p2.getFruitoreRichiedente();
	    }
	    
	    sb.append("<html><div style='text-align:left;'>");
	    sb.append("<b>Utente: ").append(utente.getNome()).
	        append("</b><br>mail: " + utente.getIndirizzoMail());
	    sb.append("<br><br><b>-riceve:</b> " + richiesta.getNome()).append(" per " + proposta.getOreRichiesta() + " ore")
	        .append("  dall'utente: " + offerente.getNome());
	    sb.append("<br><br><b>-offre:</b> " + offerta.getNome()).append(" per " + proposta.getOreOfferta() + " ore")
	        .append("  all'utente: " + ricevente.getNome());
	    sb.append("</div></html>");
	    return sb.toString();
	}
    
}
