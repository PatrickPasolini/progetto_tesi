package it.unibs.view.configuratore;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import it.unibs.domain.Categoria;
import it.unibs.domain.Gerarchia;
import it.unibs.view.atomicElements.*;

public class ViewVisualizzaGerarchie extends BaseView{
	private static final long serialVersionUID = 1L;
	private JLabel lblProposte;
	private List<Gerarchia> gerarchie;
	private Categoria categoriaSelezionata;
	
	public ViewVisualizzaGerarchie(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.gerarchie = gerarchie;
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblProposte = new JLabel("Gerarchie:");
		}

	@Override
	protected void aggiornaComponenti(int w, int h) {
	    contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
	    
	    lblProposte.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblProposte.getPreferredSize();
        lblProposte.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblProposte);
        
	    if (gerarchie != null && !gerarchie.isEmpty()) {
	        JTree tree = CustomTree.createUnifiedTree(gerarchie,true,false);
	        tree.setBackground(contentPanel.getBackground());
	        tree.setToggleClickCount(1);
	        
	        JScrollPane scrollPane = new JScrollPane(tree);
	        scrollPane.setBorder(null);
	        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        scrollPane.setBackground(contentPanel.getBackground());
	        scrollPane.setBounds(contentPanel.getWidth()/2-400, 110, 800, contentHeight-size.height-120);
	        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
	        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
	        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
	        
	        contentPanel.add(scrollPane);
	    }
	    
	    btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
	    
	    contentPanel.revalidate();
	    contentPanel.repaint();
	}
	
//	public void setLeafDoubleClickListener(ActionListener listener) {
//	    this.leafDoubleClickListener = listener;
//	}
	
	public Categoria getCategoriaSelezionata() {
	    return categoriaSelezionata;
	}

}
