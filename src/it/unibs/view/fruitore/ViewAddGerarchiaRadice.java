package it.unibs.view.fruitore;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import it.unibs.view.atomicElements.*;

public class ViewAddGerarchiaRadice extends BaseView {
	private static final long serialVersionUID = 1L;
	private JLabel lblRadice;
	private TextFieldWithPlaceholder radiceField;
	private TextFieldWithPlaceholder descrizioneField;
	private TextFieldWithPlaceholder campoField;
	private TextFieldWithPlaceholder dominioToAddField;
	private RoundedButton btnPlus;
	private DefaultListModel<String> listModel;
    private JList<String> dominiList;
	private RoundedButton btnAvanti;
	
	private RoundedButton btnFoglia;
	private RoundedButton btnNonFoglia;
	
	public ViewAddGerarchiaRadice(JFrame frame) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
	}

	@Override
	protected void inizializzaComponenti() {
		lblRadice = new JLabel("Creazione nuova gerarchia");
		radiceField = new TextFieldWithPlaceholder("Radice");
		descrizioneField = new TextFieldWithPlaceholder("Descrizione opzionale");
		campoField = new TextFieldWithPlaceholder("Campo");
		dominioToAddField = new TextFieldWithPlaceholder("Dominio da aggiungere");
		btnPlus = new RoundedButtonPlus(Color.GRAY);
		listModel = new DefaultListModel<>();
		dominiList = new JList<>(listModel);
		btnAvanti = new RoundedButton("Avanti", new Color(8, 102, 255));
		
		btnFoglia = new RoundedButton("Foglia", new Color(8, 102, 255));
		btnNonFoglia = new RoundedButton("Non Foglia", new Color(8, 102, 255));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        lblRadice.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblRadice.getPreferredSize();
        lblRadice.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblRadice);
        
        radiceField.setColumns(10);
        radiceField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        radiceField.setMargin(new Insets(10, 10, 10, 10));
        radiceField.setBounds(contentWidth / 2 - 250, 120, 500, 80);
        contentPanel.add(radiceField); 
        
        descrizioneField.setColumns(10);
        descrizioneField.setMargin(new Insets(10, 10, 10, 10));
        descrizioneField.setBounds(contentWidth / 2 - 250, 220, 500, 80);
        contentPanel.add(descrizioneField); 
        
        campoField.setColumns(10);
        campoField.setMargin(new Insets(10, 10, 10, 10));
        campoField.setBounds(contentWidth / 2 - 250, 320, 500, 80);
        contentPanel.add(campoField);
        
        dominioToAddField.setColumns(10);
        dominioToAddField.setMargin(new Insets(10, 10, 10, 10));
        dominioToAddField.setBounds(contentWidth / 2 - 250, 420, 500, 80);
        contentPanel.add(dominioToAddField); 
        
        btnPlus.setBorder(null);
        btnPlus.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnPlus.setBounds(contentWidth / 2 + 252, 422, 80-4, 80-4);
        btnPlus.setForeground(Color.WHITE);	
        contentPanel.add(btnPlus);
        
        dominiList.setFont(new Font("Tahoma", Font.PLAIN, 28));
		dominiList.setBackground(contentPanel.getBackground());
		JScrollPane scrollPane = new JScrollPane(dominiList);
		scrollPane.setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createLineBorder(Color.BLACK, 1),
			    BorderFactory.createEmptyBorder(10, 20, 10, 10)
			));
		scrollPane.setBounds(contentWidth / 2 - 250, 520, 500, 200);
		scrollPane.setBackground(contentPanel.getBackground());
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		contentPanel.add(scrollPane);
        
        btnAvanti.setBorder(null);
        btnAvanti.setMargin(new Insets(0, 10, 0, 0));
        btnAvanti.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnAvanti.setBounds(contentWidth / 2 - 200, contentHeight-110, 400, 80);
        btnAvanti.setForeground(Color.WHITE);
        contentPanel.add(btnAvanti);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	public void setBtnPlusListener(ActionListener listener) {
		btnPlus.addActionListener(listener);
	}
	public void setBtnAvantiListener(ActionListener listener) {
		btnAvanti.addActionListener(listener);
	}
	
	public void aggiornaListaComuni(List<String> domini) {
	    listModel.clear();
    	dominioToAddField.setPlaceholderColor(Color.GRAY);//x togliere il rosso se prima errore
	    for (String dominio : domini) {
	        listModel.addElement("- "+dominio);
	    }
	    dominioToAddField.setText("");
	}
	public String getRadiceField() {
		return radiceField.getText();
	}
	public String getDescrizioneField() {
		return descrizioneField.getText();
	}
	public String getCampoField() {
		return campoField.getText();
	}
	public String getDominioDaAggiungere() {
	    return dominioToAddField.getText();
	}

	public String getPlaceholderDominio() {
		return dominioToAddField.getPlaceholder();
	}

	public void visualizzaSceltaNodo(String nome, String campo, List<String> domini) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
		JLabel lblRadice = new JLabel("<html>" +
      	      							"<div align='center'>" +
      	      							"Nodo radice creato con successo<br>" +
      	      							"Nome radice: <span style='color:#085FFF;'><b>" + nome + "</b></span><br>" +
      	      							"Campo: <span style='color:#085FFF;'><b>" + campo + "</b></span><br>" +
      	      							"Dominio: <span style='color:#085FFF;'><b>" + domini.get(0) + "</b></span><br><br>" +
      	      							"Seleziona che tipologia di nodo aggiungere: " +
      	      							"</div>" +
									"</html>");
        lblRadice.setFont(new Font("Tahoma", Font.PLAIN, 55));
        Dimension size = lblRadice.getPreferredSize();
        lblRadice.setBounds((contentWidth - size.width) / 2, 20, size.width, size.height);
        contentPanel.add(lblRadice);

        btnFoglia.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnFoglia.setBorder(null);
        btnFoglia.setMargin(new Insets(0, 10, 0, 0));
        btnFoglia.setForeground(Color.WHITE);
        btnFoglia.setBounds(contentWidth / 2 - 330, size.height+80, 300, 130);
	    contentPanel.add(btnFoglia);
	    
	    btnNonFoglia.setFont(new Font("Tahoma", Font.BOLD, 40));
	    btnNonFoglia.setBorder(null);
	    btnNonFoglia.setMargin(new Insets(0, 10, 0, 0));
	    btnNonFoglia.setForeground(Color.WHITE);
	    btnNonFoglia.setBounds(contentWidth / 2 + 30, size.height+80, 300, 130);
	    contentPanel.add(btnNonFoglia);
        
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	public void setBtnConfermaCreazione(ActionListener listener) {
		  btnFoglia.setActionCommand("true");
		  btnNonFoglia.setActionCommand("false");
		  btnFoglia.addActionListener(listener);
		  btnNonFoglia.addActionListener(listener);
	}
}
