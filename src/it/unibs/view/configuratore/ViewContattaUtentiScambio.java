package it.unibs.view.configuratore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.LineBorder;

import it.unibs.domain.Fruitore;
import it.unibs.domain.Proposta;
import it.unibs.domain.Scambio;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CircleHoverIconButton;

public class ViewContattaUtentiScambio extends BaseView {
	private List<Scambio> scambi;
	private JComboBox<String> scambiComboBox;
    private JPanel dettagliScambioPanel;
    private JButton selezionaButton;
    private JLabel titoloScambioLabel;
    private CircleHoverIconButton btnHome;
	protected static final String HOME_PATH = "./Img/home.png";
    
	public ViewContattaUtentiScambio(JFrame frame, List<Scambio> scambi) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.scambi = scambi;
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
	    
	    if(scambi==null)
	        return;
	                
	    JPanel topPanel = createTopPanel();
	    topPanel.setBounds(150, 50, contentWidth-300, 200);
	    contentPanel.add(topPanel);
	    
	    // Create the details panel with a vertical BoxLayout
	    dettagliScambioPanel = new JPanel();
	    dettagliScambioPanel.setLayout(new BoxLayout(dettagliScambioPanel, BoxLayout.Y_AXIS));
	    dettagliScambioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
	    // Initial message
	    titoloScambioLabel = new JLabel("Seleziona uno scambio per vedere i dettagli");
	    titoloScambioLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    titoloScambioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    dettagliScambioPanel.add(titoloScambioLabel);
	    
	    // Create and add the scroll pane to the content panel
	    JScrollPane scrollPane = new JScrollPane(dettagliScambioPanel);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
	    scrollPane.setBounds(150, 270, contentWidth-300, 400); // Adjust position to be below top panel
	    contentPanel.add(scrollPane);
	    
	    btnHome.setBounds(45, 45, 90, 90);
	    contentPanel.add(btnHome);
	    
	    revalidate();
	    repaint();
	}
	
	private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(10, 10));
        
        // Titolo
        JLabel titleLabel = new JLabel("Contatta gli utenti di uno scambio");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        topPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Pannello per la selezione dello scambio
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel scambioLabel = new JLabel("Scelta scambio:");
        scambioLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        selectionPanel.add(scambioLabel);
        
        // Creazione della combo box con gli scambi disponibili
        
        
        List<String> scambiOptionsList = new ArrayList<>();
        
        for (Scambio s : scambi) {
            scambiOptionsList.add(s.getNome());
        }
        String[] scambiOptions = scambiOptionsList.toArray(new String[0]);
        JComboBox<String> scambiComboBox = new JComboBox<>(scambiOptions);
        scambiComboBox.setFont(new Font("Arial", Font.PLAIN, 22));
        scambiComboBox.setPreferredSize(new Dimension(200, 30));
        selectionPanel.add(scambiComboBox);
        
        // Pulsante per selezionare lo scambio
        selezionaButton = new JButton("Seleziona");
        selezionaButton.setFont(new Font("Arial", Font.PLAIN, 16));
        selezionaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedScambio = (String) scambiComboBox.getSelectedItem();
                mostraDettagliScambio(selectedScambio);
            }
        });
        selectionPanel.add(selezionaButton);
        
        topPanel.add(selectionPanel, BorderLayout.CENTER);
        
        // Linea divisoria
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(180, 180, 180));
        topPanel.add(separator, BorderLayout.SOUTH);
        
        return topPanel;
    }

	private void mostraDettagliScambio(String nomeScambio) {
	    // Clear the details panel
	    dettagliScambioPanel.removeAll();
	    
	    // Set title with selected exchange name
	    titoloScambioLabel = new JLabel("Scambio: " + nomeScambio);
	    titoloScambioLabel.setFont(new Font("Arial", Font.BOLD, 18));
	    titoloScambioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    dettagliScambioPanel.add(titoloScambioLabel);
	    dettagliScambioPanel.add(Box.createVerticalStrut(10)); // Add spacing
	    
	    // Get exchange proposals
	    Scambio selectedScambio = null;
	    for (Scambio s : scambi) {
	        if (s.getNome().equals(nomeScambio)) {
	            selectedScambio = s;
	            break;
	        }
	    }
	    
	    if (selectedScambio != null) {
	        Stack<Proposta> proposte = selectedScambio.getScambio();
	        if (proposte != null && !proposte.isEmpty()) {
	            for (Proposta p : proposte) {
	                JPanel utentePanel = createUtentePanel(p);
	                utentePanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Ensure alignment
	                dettagliScambioPanel.add(utentePanel);
	                dettagliScambioPanel.add(Box.createVerticalStrut(10)); // Add spacing between user panels
	            }
	        } else {
	            JLabel noProposteLabel = new JLabel("Nessuna proposta disponibile per questo scambio");
	            noProposteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	            dettagliScambioPanel.add(noProposteLabel);
	        }
	    }
	    
	    // Update the UI
	    dettagliScambioPanel.revalidate();
	    dettagliScambioPanel.repaint();
	}
	
	private JPanel createUtentePanel(Proposta proposta) {
        JPanel utentePanel = new JPanel();
        utentePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(180, 180, 180), 1, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        utentePanel.setBackground(new Color(250, 250, 250));
        utentePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Intestazione con nome e email dell'utente
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        Fruitore utente = (Fruitore) proposta.getFruitoreRichiedente();
        
        JLabel utenteLabel = new JLabel("Utente: " + utente.getNome());
        utenteLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(utenteLabel);
        
        JLabel emailLabel = new JLabel("Mail: " + utente.getIndirizzoMail());
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(emailLabel);
        
        
        utentePanel.add(headerPanel);
        utentePanel.add(Box.createVerticalStrut(10));
        
//         Dettagli dello scambio
//        for (DettaglioScambio dettaglio : utente.dettagli) {
//            JPanel dettaglioPanel = new JPanel();
//            dettaglioPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
//            dettaglioPanel.setOpaque(false);
//            dettaglioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//            
//            String tipoScambio = dettaglio.isRiceve ? "-riceve: " : "-offre: ";
//            JLabel tipoLabel = new JLabel(tipoScambio);
//            tipoLabel.setFont(new Font("Arial", Font.BOLD, 14));
//            dettaglioPanel.add(tipoLabel);
//            
//            String dettaglioTesto = dettaglio.prestazione + " per [" + dettaglio.ore + " ore] ";
//            String altroUtenteText = dettaglio.isRiceve ? " dall'utente: " : " all'utente: ";
//            JLabel dettaglioLabel = new JLabel(
//                dettaglioTesto + altroUtenteText + dettaglio.altroUtente + 
//                " mail: " + dettaglio.altroEmail
//            );
//            dettaglioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//            dettaglioPanel.add(dettaglioLabel);
//            
//            utentePanel.add(dettaglioPanel);
//            utentePanel.add(Box.createVerticalStrut(5));
//        }
        
        return utentePanel;
    }
	
    
}
