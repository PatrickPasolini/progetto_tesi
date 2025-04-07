package it.unibs.view.atomicElements;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.List;
import java.util.Map;

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
            setLeafIcon(null);
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

            
            if (node.getParent() != null && node.getParent().getParent() == null) {
            	//underline
            	label.setFont(boldFont);
            	Font font = label.getFont();
            	Map attributes = font.getAttributes();
            	attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            	label.setFont(font.deriveFont(attributes));
            	
                label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            } else {
                label.setFont(normalFont);
                label.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
            }

            if (leaf && sel && !locked) {
//            	label.setFont(new Font("Arial", Font.BOLD, 30)); //TODO: sistemare le dimensioni del label
                label.setForeground(new Color(8, 95, 255));
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

        // Espansione automatica dei nodi interni, lasciando chiuse le radici
        if (expandedAndLocked) {
            expandAllExceptRoot(tree, tree.getModel());
        }

        

        return tree;
    }

//    /**
//     * Espande tutti i nodi interni, tranne le radici (nodi di livello 2).
//     */
//    private static void expandAllExceptRoot(JTree tree, TreePath parent, int rootLevel) {
//        DefaultMutableTreeNode node = (DefaultMutableTreeNode) parent.getLastPathComponent();
//        if (node.getChildCount() > 0) {
//            for (int i = 0; i < node.getChildCount(); i++) {
//                DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(i);
//                TreePath childPath = parent.pathByAddingChild(childNode);
//                expandAllExceptRoot(tree, childPath, rootLevel);
//            }
//        }
//
//        // Espandi solo se non è un nodo radice
//        if (parent.getPathCount() > rootLevel) {
//            tree.expandPath(parent);
//        }
//    }
    
    /**
     * Prepara l'albero in modo che quando una radice viene espansa, tutto il suo sottoalbero si apra automaticamente.
     * Il trucco è preespandere tutti i nodi, memorizzarli, e poi chiudere solo le radici.
     */
    private static void expandAllExceptRoot(JTree tree, TreeModel model) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        
        // Prima espandiamo tutto l'albero
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) root.getChildAt(i);
            expandAllNodesRecursively(tree, rootNode);
        }
        
        // Poi chiudiamo solo i nodi radice
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) root.getChildAt(i);
            TreePath rootPath = new TreePath(new Object[] {root, rootNode});
            tree.collapsePath(rootPath);
        }
    }

    /**
     * Espande ricorsivamente tutti i nodi nell'albero a partire dal nodo specificato.
     */
    private static void expandAllNodesRecursively(JTree tree, DefaultMutableTreeNode node) {
        if (node == null) return;
        
        TreePath path = new TreePath(node.getPath());
        tree.expandPath(path);
        
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            expandAllNodesRecursively(tree, child);
        }
    }
}
