package it.unibs.view.fruitore;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import it.unibs.domain.Proposta;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewRitiraProposte extends BaseView {

	private JLabel lblRitira;
    private ArrayList<Proposta> scambiAperti;
    private RoundedButton btnRitira;
    private RoundedButton btnHome;
    
    private DefaultListModel<String> listModel;
    private JList<String> scambiList;
	
	public ViewRitiraProposte(JFrame frame,ArrayList<Proposta> scambiAperti) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.scambiAperti=scambiAperti;
		aggiornaComponenti(frame.getWidth(),frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblRitira = new JLabel("Seleziona che proposta ritirare:");
		listModel = new DefaultListModel<>();
		scambiList = new JList<>(listModel);
		btnRitira = new RoundedButton("Ritira scambio", new Color(8, 102, 255));
    	btnHome = new RoundedButton("Home", new Color(54, 164, 32));
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
	    scrollPane.setBounds(contentWidth / 2 - 500, currentY, 1000, blockHeight * 4);
	    scrollPane.setBackground(contentPanel.getBackground());
	    scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
	    scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
	    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
	    contentPanel.add(scrollPane);
	    currentY += blockHeight * 4 + offsetH;

	    btnRitira.setBorder(null);
	    btnRitira.setMargin(new Insets(0, 10, 0, 0));
	    btnRitira.setFont(new Font("Tahoma", Font.BOLD, 30));
	    btnRitira.setBounds(contentWidth / 2 - 190, contentHeight - 270, 380, 100);
	    btnRitira.setForeground(Color.WHITE);
	    contentPanel.add(btnRitira);
	    currentY += 60 + 30 ;

	    JSeparator line = new JSeparator();
	    line.setBounds(contentWidth / 2 - 190, contentHeight - 150, 380, 10);
	    line.setForeground(Color.DARK_GRAY);
	    contentPanel.add(line);
	    currentY += 60 - 30 ;

	    btnHome.setBorder(null);
	    btnHome.setMargin(new Insets(0, 10, 0, 0));
	    btnHome.setFont(new Font("Tahoma", Font.BOLD, 30));
	    btnHome.setBounds(contentWidth / 2 - 150, contentHeight - 130, 300, 90);
	    btnHome.setForeground(Color.WHITE);
	    contentPanel.add(btnHome);
	    
	    revalidate();
	    repaint();
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
}