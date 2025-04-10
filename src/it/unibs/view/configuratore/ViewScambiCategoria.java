package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibs.domain.Gerarchia;
import it.unibs.domain.Proposta;
import it.unibs.view.atomicElements.CircleHoverIconButton;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewScambiCategoria extends ViewSceltaFoglia {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblScambi;
	private RoundedButton bntAperti;
	private RoundedButton bntChiusi;
	private RoundedButton bntRitirati;
    private CircleHoverIconButton btnBack;
    private CircleHoverIconButton btnHome;
//    private CircleHoverIconButton btnBackToScelta;
   
	public ViewScambiCategoria(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,gerarchie);
	}

	@Override
	protected void inizializzaComponenti() {
		super.inizializzaComponenti();
		lblSceltaFoglia.setText("<html><div align='center'>" 
				+ "Seleziona la prestazione d'opera <br> di cui vuoi visualizzare gli scambi"
				+ "</div></html>");
		
		lblScambi = new JLabel("Scegli che proposte visualizzare:");
		bntAperti = new RoundedButton("Scambi aperti",new Color(8, 102, 255));
		bntChiusi = new RoundedButton("Scambi chiusi", new Color(8, 102, 255));
		bntRitirati = new RoundedButton("Scambi ritirati",new Color(8, 102, 255));
		btnHome = new CircleHoverIconButton(HOME_PATH, 50);
		btnBack = new CircleHoverIconButton(ARROWLEFT_PATH, 50);
//		btnBackToScelta = new CircleHoverIconButton(ARROWLEFT_PATH, 50);
	}
	
	
	public void visualizzaSceltaScambi() {
		contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
		
		lblScambi.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblScambi.getPreferredSize();
        lblScambi.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblScambi);
        
        int widthButton=contentWidth-600;
        int heightButton=contentHeight/6; //100
        
        bntAperti.setBorder(null);
        bntAperti.setFont(new Font("Tahoma", Font.BOLD, 40));
        bntAperti.setBounds(contentWidth/2-widthButton/2, 50+heightButton+20, widthButton, heightButton);
        bntAperti.setForeground(Color.WHITE);
        contentPanel.add(bntAperti);
        
        bntChiusi.setBorder(null);
        bntChiusi.setFont(new Font("Tahoma", Font.BOLD, 40));
        bntChiusi.setBounds(contentWidth/2-widthButton/2, 50+(heightButton+20)*2, widthButton, heightButton);
        bntChiusi.setForeground(Color.WHITE);
        contentPanel.add(bntChiusi);
        
        bntRitirati.setBorder(null);
        bntRitirati.setFont(new Font("Tahoma", Font.BOLD, 40));
        bntRitirati.setBounds(contentWidth/2-widthButton/2, 50+(heightButton+20)*3, widthButton, heightButton);
        bntRitirati.setForeground(Color.WHITE);
        contentPanel.add(bntRitirati);
        
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
        
        revalidate();
        repaint();
	}
	
	public void setBtnBackListeners(ActionListener btnListener) {
		super.setBtnBackListeners(btnListener);
		for (ActionListener al : btnBack.getActionListeners()) {
			btnBack.removeActionListener(al);
		}
		btnBack.addActionListener(btnListener);
    }
	
	public void setBtnApertiListeners(ActionListener btnListener) {
		bntAperti.addActionListener(btnListener);
    }
	public void setBtnChiusiListeners(ActionListener btnListener) {
		bntChiusi.addActionListener(btnListener);
    }
	public void setBtnRitiratiListeners(ActionListener btnListener) {
		bntRitirati.addActionListener(btnListener);
    }

	public void visualizzaAperti(ArrayList<Proposta> scambiAperti,String fogliaSelezionata) {
		String txt;
		if(scambiAperti.isEmpty()) {
			txt = "<html><div align='center'>Non é presente nessuna<br> proposta di scambio aperta di<br><span style='color:#085FFF;'>" 
					+ fogliaSelezionata + "</div></span></html>";
		}else {
			txt = "<html><div align='center'>Scambi aperti di <br><span style='color:#085FFF;'>" 
					+ fogliaSelezionata + " :</div></span></html>";
		}
		visualizzaScambi(scambiAperti,txt);
	}
	public void visualizzaChiusi(ArrayList<Proposta> scambiChiusi,String fogliaSelezionata) {
		String txt;
		if(scambiChiusi.isEmpty()) {
			txt = "<html><div align='center'>Non é presente nessuna<br> proposta di scambio chiusa di<br><span style='color:#085FFF;'>" 
					+ fogliaSelezionata + "</div></span></html>";
		}else {
			txt = "<html><div align='center'>Scambi chiusi di <br><span style='color:#085FFF;'>" 
					+ fogliaSelezionata + " :</div></span></html>";
		}
		visualizzaScambi(scambiChiusi,txt);
	}
	public void visualizzaRitirati(ArrayList<Proposta> scambiRitirati,String fogliaSelezionata) {
		String txt;
		if(scambiRitirati.isEmpty()) {
			txt = "<html><div align='center'>Non é presente nessuna<br> proposta di scambio ritirata di<br><span style='color:#085FFF;'>" 
					+ fogliaSelezionata + "</div></span></html>";
		}else {
			txt = "<html><div align='center'>Scambi ritirati di <br><span style='color:#085FFF;'>" 
					+ fogliaSelezionata + " :</div></span></html>";
		}
		visualizzaScambi(scambiRitirati,txt);
	}

	private void visualizzaScambi(ArrayList<Proposta> scambi, String string) {
		contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
		
        
		JLabel lblTitoloScambi = new JLabel();
		lblTitoloScambi.setText(string);
		lblTitoloScambi.setForeground(Color.BLACK);
		lblTitoloScambi.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblTitoloScambi.getPreferredSize();
        
        contentPanel.add(lblTitoloScambi);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBackground(contentPanel.getBackground());
        for (Proposta proposta : scambi) {
            JLabel lblComune = new JLabel();
            
            // Ottieni la stringa della proposta e formattala per includere una nuova riga
            String item = formattaStringProposta(proposta);
            
            lblComune.setForeground(Color.GRAY);
            lblComune.setFont(new Font("Tahoma", Font.PLAIN, 35));
            lblComune.setText(item);
            lblComune.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            
            panel.add(lblComune);
            panel.add(Box.createVerticalStrut(20)); 
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBackground(contentPanel.getBackground());
        scrollPane.setBounds(contentWidth/2- 525 , size.height + 90, 1050, 500); 
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        if(scambi.isEmpty()) {
        	lblTitoloScambi.setBounds((contentWidth - size.width) / 2, 60, size.width, size.height);
        }
        else {
        	lblTitoloScambi.setBounds((contentWidth - size.width) / 2, 60, size.width, size.height);
        	contentPanel.add(scrollPane);
        }
        	

        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
        
        
        
        revalidate();
        repaint();
	}
	
	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener);
	} 
	
	private static String formattaStringProposta(Proposta proposta) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>- richiesta: ");
		sb.append("<b>").append(proposta.getRichiesta().getNome()).append("</b>");
		sb.append(", di ").append(proposta.getOreRichiesta()).append(" ore");
		sb.append("<br>&nbsp;&nbsp;offerta: " );
		sb.append("<b>").append(proposta.getOfferta().getNome()).append("</b>");
		sb.append(", di ").append(proposta.getOreOfferta()).append(" ore");
		sb.append("</html>");
		
	    return sb.toString();
	}

	

	
	

}
