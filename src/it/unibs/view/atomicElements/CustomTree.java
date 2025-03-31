package it.unibs.view.atomicElements;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import it.unibs.domain.Gerarchia;
import it.unibs.domain.Categoria;

public class CustomTree {

    /**
     * Custom cell renderer per il JTree con maggiore spazio tra le gerarchie.
     */
    public static class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
        private final Font normalFont = new Font("Arial", Font.PLAIN, 30);
        private final Font boldFont = new Font("Arial", Font.BOLD, 35);
        private final boolean locked;
        
        // Costruttore che riceve il parametro locked
        public CustomTreeCellRenderer(boolean locked) {
            this.locked = locked;
        }
        
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                      boolean expanded, boolean leaf, int row, boolean hasFocus) {
        	JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            label.setOpaque(true);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object userObj = node.getUserObject();
            String text;
            if (userObj instanceof Categoria) {
                text = ((Categoria) userObj).getNome();
            } else {
                text = userObj.toString();
            }
            label.setText(text);

            // Impostazioni per font e bordi come da logica già presente
            if (node.getParent() != null && node.getParent().getParent() == null) {
                label.setFont(boldFont);
                label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            } else {
                label.setFont(normalFont);
                label.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
            }
            
            if (leaf && sel && !locked) {
                label.setBackground(Color.GREEN);
            } else {
                label.setBackground(tree.getBackground());
            }
            
            return label;
        }
    }

    /**
     * Metodo ricorsivo per costruire i nodi dell'albero a partire da un oggetto Categoria.
     */
    private static DefaultMutableTreeNode buildNode(Categoria cat) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(cat);
        for (Categoria child : cat.getChilds()) {
            node.add(buildNode(child));
        }
        return node;
    }

    /**
     * Crea un JTree con maggiore spazio tra le gerarchie senza nodi extra.
     */
    public static JTree createUnifiedTree(List<Gerarchia> gerarchie, boolean expandedAndLocked) {
        DefaultMutableTreeNode invisibleRoot = new DefaultMutableTreeNode("invisible");

        for (Gerarchia g : gerarchie) {
            invisibleRoot.add(buildNode(g.getRadice()));
        }

        JTree tree = new JTree(invisibleRoot);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setCellRenderer(new CustomTreeCellRenderer(expandedAndLocked));

        
//        if (!expandedAndLocked) {
//	        // Espansione con un solo clic
//	        tree.addMouseListener(new MouseAdapter() {
//	            @Override
//	            public void mouseClicked(MouseEvent e) {
//	                int selRow = tree.getRowForLocation(e.getX(), e.getY());
//	                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
//	                if (selRow != -1 && selPath != null) {
//	                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
//	                    if (!node.isLeaf()) {
//	                        if (tree.isExpanded(selPath)) {
//	                            tree.collapsePath(selPath);
//	                        } else {
//	                            tree.expandPath(selPath);
//	                        }
//	                    }
//	                }
//	            }
//	        });
//        }
	        
        // Se il flag è attivo, espandi tutto e disabilita la selezione delle foglie
        if (expandedAndLocked) {
            // Espande tutte le righe
            for (int i = 0; i < tree.getRowCount(); i++) {
                tree.expandRow(i);
            }
           
        }
        
        return tree;
    }
}
