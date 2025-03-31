package it.unibs.view.fruitore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import it.unibs.domain.Categoria;
import it.unibs.domain.Gerarchia;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.CustomTree;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewFormulaProposteScambio extends BaseView {
	private JLabel lblProposte;
	private List<Gerarchia> gerarchie;
	private RoundedButton bntContinua;
	private ActionListener leafDoubleClickListener;
	private Categoria categoriaSelezionata;
	
	public ViewFormulaProposteScambio(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.gerarchie = gerarchie;
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblProposte = new JLabel("Seleziona la prestazione d'opera che necessiti:");
		bntContinua = new RoundedButton("Conferma scelta e prosegui", new Color(8, 102, 255));
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
	        JTree tree = CustomTree.createUnifiedTree(gerarchie,false);
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
//        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(contentPanel.getBackground());
        scrollPane.setBounds(contentPanel.getWidth()/2-400, 110, 800, contentPanel.getHeight()-250);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        contentPanel.add(scrollPane);
        
        bntContinua.setBorder(null);
	    bntContinua.setMargin(new Insets(0, 10, 0, 0));
	    bntContinua.setFont(new Font("Tahoma", Font.BOLD, 30));
	    bntContinua.setBounds(contentWidth / 2 - 225, contentHeight - 120, 450, 90);
	    bntContinua.setForeground(Color.WHITE);
	    contentPanel.add(bntContinua);
        
        
        contentPanel.revalidate();
	    contentPanel.repaint();
        }
	}
}
