package it.unibs.view.configuratore;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import it.unibs.view.atomicElements.*;

public class ViewAddGerarchiaRadice extends ViewAddGerarchia {
	private static final long serialVersionUID = 1L;
	private JLabel lblRadice;
	private TextFieldWithPlaceholder radiceField;
	private TextFieldWithPlaceholder descrizioneField;

	public ViewAddGerarchiaRadice(JFrame frame) {
		super(frame);
	}

	@Override
	protected void inizializzaComponenti() {
		super.inizializzaComponenti();
		lblRadice = new JLabel();
		radiceField = new TextFieldWithPlaceholder("Categoria iniziale");
		descrizioneField = new TextFieldWithPlaceholder("Descrizione opzionale");
		btnAvanti = new RoundedButton("Crea categoria", new Color(8, 102, 255));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        String  txt = "<html><div align='center'>" 
				+ "Creazione di una nuova gerarchia<br> "
				+ "Inserisci i dati della categoria iniziale"
				+ "</div></html>";
        
        lblRadice.setText(txt);
        lblRadice.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblRadice.getPreferredSize();
        lblRadice.setBounds((contentWidth - size.width) / 2, 70, size.width, size.height);
        contentPanel.add(lblRadice);
        
        radiceField.setColumns(10);
        radiceField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        radiceField.setMargin(new Insets(10, 10, 10, 10));
        radiceField.setBounds(contentWidth / 2 - 250, (contentHeight-240)/2, 500, 100);
        contentPanel.add(radiceField); 
        
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
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}

	
	public void setBtnAvantiListener(ActionListener listener) {
		btnAvanti.addActionListener(listener);
	}
	
	public void setNomeRadiceNonUnivoco(ViewAddGerarchiaRadice viewAddGerarchiaRadice) {
		lblRadice.setForeground(Color.RED);
		lblRadice.setText("<html><div align='center'>Nome della categoria iniziale é giá in uso,<br> scegline un altro!</div></html>");  
	    Dimension size = lblRadice.getPreferredSize();
	    int contentWidth = contentPanel.getWidth();
		lblRadice.setBounds((contentWidth - size.width) / 2, 70, size.width, size.height);
	    revalidate();
	    repaint();
	}
	
	public String getRadiceField() {
		return radiceField.getText();
	}
	public String getDescrizioneField() {
		return descrizioneField.getText();
	}
	public String getRadicePlaceholder() {
		return radiceField.getPlaceholder();
	}
}
