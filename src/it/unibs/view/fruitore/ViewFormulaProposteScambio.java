package it.unibs.view.fruitore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import it.unibs.domain.Foglia;
import it.unibs.domain.Gerarchia;
import it.unibs.view.atomicElements.*;
import it.unibs.view.configuratore.ViewSceltaFoglia;

public class ViewFormulaProposteScambio extends ViewSceltaFoglia {
	private static final long serialVersionUID = 1L;
	private RoundedButton btnConfermaRichiesta;
	private RoundedButton btnConfermaOfferta;
	private RoundedButton btnSi;
	private RoundedButton btnNo;
	private RoundedButton btnHome;
	private NumericFieldWithPlaceholder oreRichiestaField;
	
	public ViewFormulaProposteScambio(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,gerarchie);
		inizializzaComponenti();
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	protected void inizializzaComponenti() {
		super.inizializzaComponenti();
		btnConfermaRichiesta = new RoundedButton("Conferma richiesta e prosegui", new Color(8, 102, 255));
		btnConfermaOfferta = new RoundedButton("Conferma offerta e prosegui", new Color(8, 102, 255));
		btnSi = new RoundedButton("SI", new Color(8, 102, 255));
		btnNo = new RoundedButton("NO", new Color(8, 102, 255));
		btnHome = new RoundedButton("Home", new Color(8, 102, 255));
	}
 
	public void visualizzaRichiesta(Foglia richiesta) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        
        JLabel lblRichiesta = new JLabel(
        	    "<html>" +
        	      "<div align='center'>" +
        	        "Hai scelto la prestazione d'opera <b>richiesta</b>:<br>" +
        	        "<span style='color:#085FFF;'><b>" + richiesta.getNome() + "</b></span><br>" +
        	        "Indica il numero di ore di cui hai bisogno per completare la richiesta" +
        	      "</div>" +
        	    "</html>"
        	);
    	lblRichiesta.setFont(new Font("Tahoma", Font.PLAIN, 50));
    	Dimension size = lblRichiesta.getPreferredSize();
    	lblRichiesta.setBounds((contentWidth - size.width) / 2, 70, size.width, size.height);
        contentPanel.add(lblRichiesta);
        
        oreRichiestaField = new NumericFieldWithPlaceholder("ore richiesta");
//        ((AbstractDocument) oreRichiesta.getDocument()).setDocumentFilter(new IntegerDocumentFilter());

        oreRichiestaField.setColumns(10);
        oreRichiestaField.setMargin(new Insets(10, 10, 10, 10));
        oreRichiestaField.setFont(new Font("Tahoma", Font.PLAIN, 35));
        oreRichiestaField.setBounds(contentWidth / 2 - 260, size.height+200, 520, 120);
        contentPanel.add(oreRichiestaField); 

       
        btnConfermaRichiesta.setFont(new Font("Tahoma", Font.BOLD, 33));
        btnConfermaRichiesta.setBorder(null);
        btnConfermaRichiesta.setMargin(new Insets(0, 10, 0, 0));
        btnConfermaRichiesta.setForeground(Color.WHITE);
        btnConfermaRichiesta.setBounds(contentWidth / 2 - 260, size.height+350, 520, 120);
	    contentPanel.add(btnConfermaRichiesta);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	public void setBtnConfermaRichiestaListener(ActionListener event) {
		btnConfermaRichiesta.addActionListener(event);
	}
	public int getOreRichiesta() {
		return oreRichiestaField.getNumericValue();
	}
	
	public void visualizzaSceltaOfferta() {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        lblSceltaFoglia.setText("Seleziona la prestazione d'opera che offri");
        sceltaFoglia(contentWidth);
        
        btnConfermaOfferta.setBorder(null);
        btnConfermaOfferta.setMargin(new Insets(0, 10, 0, 0));
        btnConfermaOfferta.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnConfermaOfferta.setBounds(contentWidth / 2 - 225, contentHeight - 120, 450, 90);
        btnConfermaOfferta.setForeground(Color.WHITE);
	    contentPanel.add(btnConfermaOfferta);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	public void setBtnConfermaOffertaListener(ActionListener listener) {
		btnConfermaOfferta.addActionListener(listener);
	}
	
	public void visualizzaPropostaFormulata(Foglia richiesta, int oreRichiesta, Foglia offerta, int oreOfferta) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    
        JLabel lblScambio = new JLabel(
        	    "<html>" +
        	      "<div align='center'>" +
        	        "Hai formulato la seguente proposta di scambio:<br><br>" +
        	        "Richiesta: <span style='color:#085FFF;'><b>" + richiesta.getNome() + "</b></span>" +
        	        " di: <span style='color:#085FFF;'><b>"+ oreRichiesta + "</b></span> ore<br>" +
        	        "Offerta: <span style='color:#085FFF;'><b>" + offerta.getNome() + "</b></span>" +
        	        " di: <span style='color:#085FFF;'><b>"+ oreOfferta + "</b></span> ore<br><br>" +
        	        "Vuoi confermare la creazione della proposta di scambio:" +
        	      "</div>" +
        	    "</html>"
        	);
    	lblScambio.setFont(new Font("Tahoma", Font.PLAIN, 50));
    	Dimension size = lblScambio.getPreferredSize();
    	lblScambio.setBounds((contentWidth - size.width) / 2, 70, size.width, size.height);
        contentPanel.add(lblScambio);
        
        
        btnSi.setFont(new Font("Tahoma", Font.BOLD, 33));
        btnSi.setBorder(null);
        btnSi.setMargin(new Insets(0, 10, 0, 0));
        btnSi.setForeground(Color.WHITE);
        btnSi.setBounds(contentWidth / 2 - 260, size.height+150, 250, 120);
	    contentPanel.add(btnSi);
	    
	    btnNo.setFont(new Font("Tahoma", Font.BOLD, 33));
	    btnNo.setBorder(null);
	    btnNo.setMargin(new Insets(0, 10, 0, 0));
	    btnNo.setForeground(Color.WHITE);
	    btnNo.setBounds(contentWidth / 2 + 10, size.height+150, 250, 120);
	    contentPanel.add(btnNo);
        
        
	    contentPanel.revalidate();
	    contentPanel.repaint();
	}
	public void setBtnConfermaCreazione(ActionListener listener) {
		  btnSi.setActionCommand("true");
		  btnNo.setActionCommand("false");
		  btnSi.addActionListener(listener);
		  btnNo.addActionListener(listener);
	}
	
	public void visualizzaCreazione(Foglia richiesta, Foglia offerta, int oreRichiesta, int oreOfferta) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    int contentHeight = contentPanel.getHeight();
	    
        JLabel lblScambio = new JLabel(
        	    "<html>" +
        	      "<div align='center'>" +
        	        "<span style='color:#33CC33;'><b>Formulazione del seguente scambio avvenuta con successo:</b></span><br><br>" +
        	        "Richiesta: <span style='color:#085FFF;'><b>" + richiesta.getNome() + "</b></span>" +
        	        " di: <span style='color:#085FFF;'><b>"+ oreRichiesta + "</b></span> ore<br>" +
        	        "Offerta: <span style='color:#085FFF;'><b>" + offerta.getNome() + "</b></span>" +
        	        " di: <span style='color:#085FFF;'><b>"+ oreOfferta + "</b></span> ore" +
        	      "</div>" +
        	    "</html>"
        	);
    	lblScambio.setFont(new Font("Tahoma", Font.PLAIN, 50));
    	Dimension size = lblScambio.getPreferredSize();
    	lblScambio.setBounds((contentWidth - size.width) / 2, 70, size.width, size.height);
        contentPanel.add(lblScambio);
        
        btnHome.setBorder(null);
        btnHome.setMargin(new Insets(0, 10, 0, 0));
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnHome.setBounds(contentWidth / 2 - 225, contentHeight - 200, 450, 90);
        btnHome.setForeground(Color.WHITE);
	    contentPanel.add(btnHome);
	    
	    contentPanel.revalidate();
	    contentPanel.repaint();
	}
	public void setBtnHome(ActionListener listener) {
		btnHome.addActionListener(listener);
	}
	
}
