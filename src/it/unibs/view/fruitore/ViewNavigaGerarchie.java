package it.unibs.view.fruitore;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import it.unibs.domain.Categoria;
import it.unibs.domain.Gerarchia;
import it.unibs.view.atomicElements.*;

public class ViewNavigaGerarchie extends BaseView{
	private static final long serialVersionUID = 1L;
	private static final String HOME_PATH = "./Img/home.png";
	private JLabel lblProposte;
	private List<Gerarchia> gerarchie;
    private CircleHoverIconButton btnBack;
	private ActionListener leafDoubleClickListener;
	private Categoria categoriaSelezionata;
	
	public ViewNavigaGerarchie(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.gerarchie = gerarchie;
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblProposte = new JLabel("Gerarchie:");
		btnBack = new CircleHoverIconButton(HOME_PATH, 50);
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
	    contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
	    
	    lblProposte.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblProposte.getPreferredSize();
        lblProposte.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblProposte);
        
	    if (gerarchie != null && !gerarchie.isEmpty()) {
	        JTree tree = CustomTree.createUnifiedTree(gerarchie,false,false);
	        tree.setBackground(contentPanel.getBackground());
	        tree.setToggleClickCount(1);
	        tree.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2) {  // Doppio click
	                    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
	                    if (selPath != null) {
	                        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selPath.getLastPathComponent();
	                        if (selectedNode.isLeaf() && leafDoubleClickListener != null) {
	                            Object userObj = selectedNode.getUserObject();
	                            if (userObj instanceof Categoria) {
	                                // Salva l'oggetto Categoria selezionato
	                                categoriaSelezionata = (Categoria) userObj;
	                            }
	                            // Genera un ActionEvent e comunica l'evento
	                            ActionEvent event = new ActionEvent(selectedNode, ActionEvent.ACTION_PERFORMED, "LeafDoubleClick");
	                            leafDoubleClickListener.actionPerformed(event);
	                        }
	                    }
	                }
	            }
	        });
	        
	        JScrollPane scrollPane = new JScrollPane(tree);
	        scrollPane.setBorder(null);
	        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        scrollPane.setBackground(contentPanel.getBackground());
	        scrollPane.setBounds(contentPanel.getWidth()/2-400, 110, 800, contentPanel.getHeight()-140);
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

	public void setBtnHomeListener(ActionListener listener) {
		btnBack.addActionListener(listener); // Riaggiungiamo il listener
	}
	
	public void setLeafDoubleClickListener(ActionListener listener) {
	    this.leafDoubleClickListener = listener;
	}
	
	public Categoria getCategoriaSelezionata() {
	    return categoriaSelezionata;
	}

}
