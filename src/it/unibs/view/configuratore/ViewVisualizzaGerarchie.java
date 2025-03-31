package it.unibs.view.configuratore;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.*;

import it.unibs.domain.Categoria;
import it.unibs.domain.Gerarchia;
import it.unibs.view.atomicElements.*;

public class ViewVisualizzaGerarchie extends BaseView{
	private JLabel lblProposte;
	private List<Gerarchia> gerarchie;
	private RoundedButton btnHome;
	private ActionListener leafDoubleClickListener;
	private Categoria categoriaSelezionata;
	
	public ViewVisualizzaGerarchie(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.gerarchie = gerarchie;
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblProposte = new JLabel("Gerarchie:");
		btnHome = new RoundedButton("Home", new Color(8, 102, 255));
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
	        JTree tree = CustomTree.createUnifiedTree(gerarchie,true);
	        tree.setBackground(contentPanel.getBackground());
	        
//	        tree.addMouseListener(new MouseAdapter() {
//	            @Override
//	            public void mouseClicked(MouseEvent e) {
//	                if (e.getClickCount() == 2) {  // Doppio click
//	                    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
//	                    if (selPath != null) {
//	                        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selPath.getLastPathComponent();
//	                        if (selectedNode.isLeaf() && leafDoubleClickListener != null) {
//	                            Object userObj = selectedNode.getUserObject();
//	                            if (userObj instanceof Categoria) {
//	                                // Salva l'oggetto Categoria selezionato
//	                                categoriaSelezionata = (Categoria) userObj;
//	                            }
//	                            // Genera un ActionEvent e comunica l'evento
//	                            ActionEvent event = new ActionEvent(selectedNode, ActionEvent.ACTION_PERFORMED, "LeafDoubleClick");
//	                            leafDoubleClickListener.actionPerformed(event);
//	                        }
//	                    }
//	                }
//	            }
//	        });
	        
	        JScrollPane scrollPane = new JScrollPane(tree);
	        scrollPane.setBorder(null);
	        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        scrollPane.setBackground(contentPanel.getBackground());
	        scrollPane.setBounds(contentPanel.getWidth()/2-400, 110, 800, contentPanel.getHeight()-250);
	        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
	        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
	        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
	        
	        contentPanel.add(scrollPane);
	    }
	    
	    btnHome.setBorder(null);
	    btnHome.setMargin(new Insets(0, 10, 0, 0));
	    btnHome.setFont(new Font("Tahoma", Font.BOLD, 30));
	    btnHome.setBounds(contentWidth / 2 - 150, contentHeight - 120, 300, 90);
	    btnHome.setForeground(Color.WHITE);
	    contentPanel.add(btnHome);
	    
	    contentPanel.revalidate();
	    contentPanel.repaint();
	}

	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener); // Riaggiungiamo il listener
	}
	
	
	public void setLeafDoubleClickListener(ActionListener listener) {
	    this.leafDoubleClickListener = listener;
	}
	
	public Categoria getCategoriaSelezionata() {
	    return categoriaSelezionata;
	}

}
