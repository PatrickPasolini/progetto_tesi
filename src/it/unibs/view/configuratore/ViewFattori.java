package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibs.domain.Foglia;
import it.unibs.domain.Gerarchia;
import it.unibs.domain.NestedMap;
import it.unibs.view.atomicElements.CircleHoverIconButton;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.FattoriConversionePanel;

public class ViewFattori extends ViewSceltaFoglia {
	private static final long serialVersionUID = 1L;
	private JLabel lblScambi;
    private CircleHoverIconButton btnBack;
    private CircleHoverIconButton btnHome;
    
	public ViewFattori(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame, gerarchie);
	}

	@Override
	protected void inizializzaComponenti() {
		super.inizializzaComponenti();
		lblSceltaFoglia.setText("<html><div align='center'>" 
				+ "Seleziona la prestazione d'opera <br> "
				+ "di cui vuoi visualizzare i fattori di conversione"
				+ "</div></html>");
		lblScambi = new JLabel();
		btnHome = new CircleHoverIconButton(HOME_PATH, 50);
		btnBack = new CircleHoverIconButton(ARROWLEFT_PATH, 50);
	}
	
	public void visualizzaFattori(NestedMap<Foglia, Foglia, Double> mapFattori, Foglia foglia) {
		contentPanel.removeAll();
        int contentWidth = contentPanel.getWidth();
		
        String txt = "<html><div align='center'>" 
        			+ "Fattori di conversione della prestazione <br><span style='color:#085FFF;'><b>" 
        			+ foglia.getNome() + "</b></span><br>con le prestazioni:"
					+ "</div></html>";
        lblScambi.setText(txt);
		lblScambi.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblScambi.getPreferredSize();
        lblScambi.setBounds((contentWidth - size.width) / 2, 20, size.width, size.height);
        contentPanel.add(lblScambi);
		
        
		
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(contentPanel.getBackground());
        
        
        
        FattoriConversionePanel fp = new FattoriConversionePanel(mapFattori, foglia);
        fp.setBounds((contentWidth - 1000) / 2, 250,1000, 550 );
        contentPanel.add(fp);
        
//        JScrollPane scrollPane = fp.createCompatibleScrollPane();
//      scrollPane.setBackground(contentPanel.getBackground());
//      scrollPane.setBounds(contentWidth/2- 400 , size.height + 90, 800, 500); 
////      scrollPane.setBorder(null);
//      scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
//      scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
//      scrollPane.getVerticalScrollBar().setUnitIncrement(20);
//      contentPanel.add(scrollPane);
        
        
        
//        JLabel lblFattori = new JLabel();
//        String item = formattaStringFattori(mapFattori.get(foglia));
//        System.out.println(item);
//        lblFattori.setForeground(Color.BLACK);
//        lblFattori.setFont(new Font("Tahoma", Font.PLAIN, 35));
//        lblFattori.setText(item);
//        lblFattori.setAlignmentX(JLabel.LEFT_ALIGNMENT);
//        
//        panel.add(lblFattori);
//        panel.add(Box.createVerticalStrut(20)); 
//        
//		JScrollPane scrollPane = new JScrollPane(panel);
//        scrollPane.setBackground(contentPanel.getBackground());
//        scrollPane.setBounds(contentWidth/2- 400 , size.height + 90, 800, 500); 
////        scrollPane.setBorder(null);
//        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
//        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
//        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
//        contentPanel.add(scrollPane);
        
		btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
        
        revalidate();
        repaint();
	}
	
	private String formattaStringFattori(Map<Foglia, Double> fattoriF) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		for(Map.Entry<Foglia, Double> entry : fattoriF.entrySet()) {
			Foglia fogliaF2 = entry.getKey();
            Double fattore = entry.getValue();
            sb.append("<br>&nbsp;&nbsp;&nbsp;");
            sb.append(String.format(" %.2f x ", fattore));
            sb.append(fogliaF2.getNome());
            sb.append("<hr>"); 
        }
		sb.append("</html>");
		return sb.toString();
	}

	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener);
	} 
	public void setBtnBackListeners(ActionListener btnListener) {
		super.setBtnBackListeners(btnListener);
		for (ActionListener al : btnBack.getActionListeners()) {
			btnBack.removeActionListener(al);
		}
		btnBack.addActionListener(btnListener);
    }
}
