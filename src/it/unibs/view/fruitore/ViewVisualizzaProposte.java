package it.unibs.view.fruitore;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import it.unibs.domain.Proposta;
import it.unibs.view.atomicElements.*;

public class ViewVisualizzaProposte extends BaseView {
	private JLabel lblProposte;
	private RoundedButton bntAperti;
	private RoundedButton bntChiusi;
	private RoundedButton bntRitirati;
    private RoundedButton btnHome;
	public ViewVisualizzaProposte(JFrame frame) {
		super(frame,frame.getWidth()-400,750);
		aggiornaComponenti(frame.getWidth(),frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblProposte = new JLabel("Scegli che proposte visualizzare:");
		bntAperti = new RoundedButton("Scambi aperti",new Color(8, 102, 255));
		bntChiusi = new RoundedButton("Scambi chiusi", new Color(8, 102, 255));
		bntRitirati = new RoundedButton("Scambi ritirati",new Color(8, 102, 255));
		btnHome = new RoundedButton("Home", new Color(8, 102, 255));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
		
		lblProposte.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblProposte.getPreferredSize();
        lblProposte.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblProposte);
        
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
        
        revalidate();
        repaint();
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

	public void visualizzaAperti(ArrayList<Proposta> scambiAperti, String nameUser) {
		visualizzaScambi(scambiAperti,"Scambi aperti di "+nameUser+":");
	}
	public void visualizzaChiusi(ArrayList<Proposta> scambiChiusi, String nameUser) {
		visualizzaScambi(scambiChiusi,"Scambi chiusi di "+nameUser+":");
	}
	public void visualizzaRitirati(ArrayList<Proposta> scambiRitirati, String nameUser) {
		visualizzaScambi(scambiRitirati,"Scambi ritirati di "+nameUser+":");
	}
	
	private void visualizzaScambi(ArrayList<Proposta> scambi, String string) {
		contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
		
		JLabel lblScambi = new JLabel();
		lblScambi.setText(string);
		lblScambi.setForeground(Color.BLACK);
		lblScambi.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblScambi.getPreferredSize();
        lblScambi.setBounds((contentWidth - size.width) / 2, 50, size.width, 70);
        contentPanel.add(lblScambi);
        
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
        scrollPane.setBounds(contentWidth/2- 525 , 160, 1050, 400); 
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        contentPanel.add(scrollPane);

        btnHome.setMargin(new Insets(0, 10, 0, 0));
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnHome.setBounds(contentWidth / 2 - 180, contentHeight-150, 360, 90);
        btnHome.setForeground(Color.WHITE);
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
