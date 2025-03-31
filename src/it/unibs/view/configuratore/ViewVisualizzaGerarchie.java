package it.unibs.view.configuratore;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import it.unibs.domain.Categoria;
import it.unibs.domain.Gerarchia;
import it.unibs.domain.NonFoglia;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CustomTree;

public class ViewVisualizzaGerarchie extends BaseView{
	private List<Gerarchia> gerarchie;
	public ViewVisualizzaGerarchie(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.gerarchie = gerarchie;
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
	    contentPanel.removeAll();

	    if (gerarchie != null && !gerarchie.isEmpty()) {
	        JTree tree = createUnifiedJTreeFromGerarchie(gerarchie);
	        JScrollPane scrollPane = new JScrollPane(tree);
	        scrollPane.setBounds(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
	        contentPanel.add(scrollPane);
	    }

	    contentPanel.revalidate();
	    contentPanel.repaint();
	}

	public static JTree createUnifiedJTreeFromGerarchie(List<Gerarchia> gerarchie) {
	    // Creiamo un nodo radice "invisibile"
	    DefaultMutableTreeNode invisibleRoot = new DefaultMutableTreeNode("invisible");
	    for (Gerarchia g : gerarchie) {
	        // Aggiungiamo ciascuna gerarchia come figlio del nodo invisibile
	        invisibleRoot.add(buildNode(g.getRadice()));
	    }
	    
	    // Creiamo il JTree e nascondiamo il nodo radice
	    JTree tree = new JTree(invisibleRoot);
	    tree.setCellRenderer(new CustomTree());
	    tree.setRootVisible(false);
	    
	    // Facoltativo: mostra le icone di espansione anche se il nodo radice non è visibile
	    tree.setShowsRootHandles(true);
	    
	    return tree;
	}

	
	private static DefaultMutableTreeNode buildNode(Categoria cat) {
        String label = cat.getNome();

        // Se il nodo è un NonFoglia, aggiungiamo il campo
        if (cat instanceof NonFoglia) {
            NonFoglia n = (NonFoglia) cat;
//          label += " - campo :[ " + n.getCampo() + " ]";
        }
//        // Aggiungiamo la descrizione se presente
//        if (!cat.getDescrizione().isEmpty()) {
//            label += " - descrizione :[ " + cat.getDescrizione() + " ]";
//        }

        DefaultMutableTreeNode node = new DefaultMutableTreeNode(label);

        // Ricorsione per ogni figlio
        for (Categoria child : cat.getChilds()) {
            node.add(buildNode(child));
        }
        return node;
    }

    /**
     * Crea un JTree a partire da una Gerarchia.
     */
    public static JTree createJTreeFromGerarchia(Gerarchia g) {
        DefaultMutableTreeNode root = buildNode(g.getRadice());
        return new JTree(root);
    }

    /**
     * Dato un Iterable di Gerarchia, crea una lista di JTree.
     */
    public static List<JTree> createJTreesFromGerarchie(Iterable<Gerarchia> gerarchie) {
        List<JTree> trees = new ArrayList<>();
        for (Gerarchia g : gerarchie) {
            trees.add(createJTreeFromGerarchia(g));
        }
        return trees;
    }
}
