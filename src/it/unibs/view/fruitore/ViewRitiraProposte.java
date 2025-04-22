package it.unibs.view.fruitore;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import it.unibs.domain.Proposta;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CircleHoverIconButton;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewRitiraProposte extends BaseView {
	private static final long serialVersionUID = 1L;
	private static final String ARROWLEFT_PATH = "./Img/arrowLeft.png";
	private static final String HOME_PATH = "./Img/home.png";
	private JLabel lblRitira;
    private ArrayList<Proposta> scambiAperti;
    private RoundedButton btnRitira;
    private CircleHoverIconButton btnBack;
    private CircleHoverIconButton btnHome;
    private RoundedButton btnSi;
	private RoundedButton btnNo;
    private DefaultListModel<String> listModel;
    private JList<String> scambiList;
	
	public ViewRitiraProposte(JFrame frame,ArrayList<Proposta> scambiAperti) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.scambiAperti=scambiAperti;
		aggiornaComponenti(frame.getWidth(),frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblRitira = new JLabel("Seleziona una proposta da ritirare:");
		listModel = new DefaultListModel<>();
		scambiList = new JList<>(listModel);
		btnRitira = new RoundedButton("Ritira proposta", new Color(8, 102, 255));
//		btnSi = new RoundedButton("Ritira", new Color(8, 102, 255));
//		btnNo = new RoundedButton("Annulla", new Color(8, 102, 255));
		
		btnSi = new RoundedButton("Ritira", new Color(0, 143, 57));
		btnNo = new RoundedButton("Annulla",new Color(165, 32, 25));
		btnHome = new CircleHoverIconButton(HOME_PATH, 50);
		btnBack = new CircleHoverIconButton(ARROWLEFT_PATH, 50);
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
	    contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    int contentHeight = contentPanel.getHeight();
	    int blockHeight = frame.getHeight() / 10;
	    int offsetH = 20;
	    
	    int currentY = offsetH; 

	    lblRitira.setFont(new Font("Tahoma",  Font.BOLD, 55));
	    Dimension size = lblRitira.getPreferredSize();
	    lblRitira.setBounds((contentWidth - size.width) / 2, currentY, size.width, blockHeight);
	    contentPanel.add(lblRitira);
	    currentY += blockHeight + offsetH;

	    
	    if (scambiAperti != null) {
	        aggiornaListaScambi(scambiAperti);
	    }
	    scambiList.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    scambiList.setBackground(contentPanel.getBackground());
	    scambiList.setCellRenderer(new DefaultListCellRenderer() { //interlinea 
			private static final long serialVersionUID = 1L;
			@Override
	        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
	                boolean isSelected, boolean cellHasFocus) {
	            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	            return label;
	        }
	    });
	    JScrollPane scrollPane = new JScrollPane(scambiList);
	    scrollPane.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(Color.BLACK, 1),
	            BorderFactory.createEmptyBorder(10, 20, 10, 10)
	    ));
	    scrollPane.setBounds(contentWidth / 2 - 500, currentY+30, 1000, blockHeight * 5);
	    scrollPane.setBackground(contentPanel.getBackground());
	    scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
	    scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
	    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
	    contentPanel.add(scrollPane);

	    btnRitira.setBorder(null);
	    btnRitira.setMargin(new Insets(0, 10, 0, 0));
	    btnRitira.setFont(new Font("Tahoma", Font.BOLD, 30));
	    btnRitira.setBounds(contentWidth / 2 - 190, contentHeight - 130, 380, 100);
	    btnRitira.setForeground(Color.WHITE);
	    contentPanel.add(btnRitira);

	    btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
	    
	    revalidate();
	    repaint();
	}
	
	public void visualizzaConfermaRitiro (Proposta propDaRitirare) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    String txtConferma = "<html><div align='center'>Vuoi confermare il ritiro della proposta selezionata:</div><br><br>" +
				"Richiesta: <span style='color:#085FFF;'><b>" + propDaRitirare.getRichiesta().getNome() + "</b></span>" +
    	        " di <span style='color:#085FFF;'><b>"+ propDaRitirare.getOreRichiesta() + "</b></span> ore<br>" +
    	        "Offerta: <span style='color:#085FFF;'><b>" + propDaRitirare.getOfferta().getNome() + "</b></span>" +
    	        " di <span style='color:#085FFF;'><b>"+ propDaRitirare.getOreOfferta() + "</b></span> ore" +
	    		 "</html>";
	    
	    
        JLabel lblScambio = new JLabel(txtConferma);
    	lblScambio.setFont(new Font("Tahoma", Font.PLAIN, 50));
    	Dimension size = lblScambio.getPreferredSize();
    	lblScambio.setBounds((contentWidth - size.width) / 2, 60, size.width, size.height);
        contentPanel.add(lblScambio);
        
        btnSi.setFont(new Font("Tahoma", Font.BOLD, 38));
        btnSi.setBorder(null);
        btnSi.setMargin(new Insets(0, 10, 0, 0));
        btnSi.setForeground(Color.WHITE);
        btnSi.setBounds(contentWidth / 2 - 260, size.height+150, 250, 120);
	    contentPanel.add(btnSi);
	    
	    btnNo.setFont(new Font("Tahoma", Font.BOLD, 38));
	    btnNo.setBorder(null);
	    btnNo.setMargin(new Insets(0, 10, 0, 0));
	    btnNo.setForeground(Color.WHITE);
	    btnNo.setBounds(contentWidth / 2 + 10, size.height+150, 250, 120);
	    contentPanel.add(btnNo);
        
	    btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
	    
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
	    
	    contentPanel.revalidate();
	    contentPanel.repaint();
	}
	

	public void aggiornaListaScambi(List<Proposta> scambiAperti) {
	    listModel.clear();
	    for (Proposta s : scambiAperti) {
	        listModel.addElement(formattaStringProposta(s));
	    }
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
	
	public void setBtnRitiraListener(ActionListener listener) {
		btnRitira.addActionListener(listener);
	}
	public void setBtnBackListeners(ActionListener btnListener) {
		for (ActionListener al : btnBack.getActionListeners()) {
			btnBack.removeActionListener(al);
		}
		btnBack.addActionListener(btnListener);
    }
	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener);
	}

	public Proposta getPropostaSelezionata() {
	    int selectedIndex = scambiList.getSelectedIndex();
	    if (selectedIndex != -1) {
	        return scambiAperti.get(selectedIndex);
	    }
	    return null;
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

	public void visualizzaRitiroEffettuato(Proposta propRitirata) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    
	    String txtRitirato = "<html><div align='center'><span style='color:#008F39;'>"
	    		+ "<b>Ritiro della seguente proposta avvenuto con successo:</b></span></div><br><br>"+
	    		"Richiesta: <span style='color:#085FFF;'><b>" + propRitirata.getRichiesta().getNome() + "</b></span>" +
    	        " di <span style='color:#085FFF;'><b>"+ propRitirata.getOreRichiesta() + "</b></span> ore<br>" +
    	        "Offerta: <span style='color:#085FFF;'><b>" + propRitirata.getOfferta().getNome() + "</b></span>" +
    	        " di <span style='color:#085FFF;'><b>"+ propRitirata.getOreOfferta() + "</b></span> ore" +
	    		 "</html>";
	    
        JLabel lblScambio = new JLabel(txtRitirato);
    	lblScambio.setFont(new Font("Tahoma", Font.PLAIN, 50));
    	Dimension size = lblScambio.getPreferredSize();
    	lblScambio.setBounds((contentWidth - size.width) / 2, 60, size.width, size.height);
        contentPanel.add(lblScambio);
        
        btnHome.setBounds(45, 45, 90, 90);
        contentPanel.add(btnHome);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}

	public void setSelezioneFallita() {
	    lblRitira.setForeground(Color.RED);
	    lblRitira.setText("<html><div align='center'>Seleziona una proposta da ritirare"
	    		+ "<br> prima di continuare!</div></html>");
	    
	    Dimension size = lblRitira.getPreferredSize();
	    int contentWidth = contentPanel.getWidth();
	    lblRitira.setBounds((contentWidth - size.width) / 2, 20, size.width, size.height);
	    
	    revalidate();
	    repaint();
	}

	public void visualizzaNessunoScambioRitirabile() {
		contentPanel.removeAll();
		System.out.println("ciao");
	    int contentWidth = contentPanel.getWidth();
	    
	    String txtRitirato = "<html><div align='center'><b>"
	    		+ "Non ci sono scambi aperti da ritirare<br>"
	    		+ "torna al menu principale"
	    		+ "</b></div></html>";
	    
        JLabel lblScambio = new JLabel(txtRitirato);
    	lblScambio.setFont(new Font("Tahoma", Font.PLAIN, 50));
    	Dimension size = lblScambio.getPreferredSize();
    	lblScambio.setBounds((contentWidth - size.width) / 2, 60, size.width, size.height);
        contentPanel.add(lblScambio);
        
        btnHome.setBounds(45, 45, 90, 90);
        contentPanel.add(btnHome);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
		
	}
	
	
}