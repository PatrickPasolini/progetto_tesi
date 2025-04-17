package it.unibs.view.atomicElements;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.List;
import java.util.Map;

import it.unibs.domain.Gerarchia;
import it.unibs.domain.Categoria;
import it.unibs.domain.Foglia;

public class CustomTree {

    private static TreePath hoveredPath = null;
    /**
     * Custom cell renderer per il JTree con maggiore spazio tra le gerarchie.
     */
    public static class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
        private static final long serialVersionUID = 1L;
        private static final String ARROW_PATH = "./Img/arrowRight.png";
        private static final String ARROWDOWN_PATH = "./Img/arrowDown.png";
        private final Font normalFont = new Font("Arial", Font.PLAIN, 30);
        private final Font boldFont = new Font("Arial", Font.BOLD, 35);
        private final boolean locked;
        private final boolean creation;
        
        ImageIcon arrowDownIconOriginal = new ImageIcon(ARROW_PATH);
        Image arrowDownScaledImage = arrowDownIconOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon arrowDownIcon = new ImageIcon(arrowDownScaledImage);
        
        ImageIcon arrowRightIconOriginal = new ImageIcon(ARROWDOWN_PATH);
        Image arrowRightScaledImage = arrowRightIconOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon arrowRightIcon = new ImageIcon(arrowRightScaledImage);

        
        public CustomTreeCellRenderer(boolean locked, boolean creation) {
            this.locked = locked;
            this.creation = creation;
            setOpenIcon(arrowRightIcon);
            setClosedIcon(arrowDownIcon);           
            setLeafIcon(null);
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                      boolean expanded, boolean leaf, int row, boolean hasFocus) {
            JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            label.setOpaque(true);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object userObj = node.getUserObject();
            String text;
            
            // Check if the category is actually a leaf or non-leaf type
            boolean isActualLeaf = false;
            if (userObj instanceof Categoria) {
                Categoria categoria = (Categoria) userObj;
                text = categoria.getNome();
                isActualLeaf = (categoria instanceof Foglia);
                
             // Set icon based on category type
                if (!isActualLeaf) {
                    // This is a NonFoglia type, set folder icon
                    setIcon(arrowDownIcon);
                } else {
                    // This is a Foglia type, no icon
                    setIcon(null);
                }
                
            } else {
                text = userObj.toString();
                setIcon(null);
            }
            label.setText(text);

            // Style for root-level nodes (hierarchies)
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
            
            // Mouse over effect
            if (tree.getPathForRow(row) != null && tree.getPathForRow(row).equals(CustomTree.hoveredPath)) {
                label.setBackground(new Color(207, 207, 207));
                // Don't apply hover effect on leaf nodes during creation
                if (creation && isActualLeaf || creation&&locked&&!isActualLeaf )
                    label.setBackground(tree.getBackground());
            } else {
                label.setBackground(tree.getBackground());
            }
            
            // Selection highlighting for non-locked leaf nodes or non-leaf nodes during creation
            if ((isActualLeaf && sel && !locked && !creation) || (creation && sel && !isActualLeaf &&!locked)
            		|| (creation && sel && locked && isActualLeaf)
            		) {
                label.setForeground(new Color(8, 95, 255));
                label.setBackground(new Color(207, 207, 207));
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
    public static JTree createUnifiedTree(List<Gerarchia> gerarchie, boolean expandedAndLocked, boolean creation) {
        DefaultMutableTreeNode invisibleRoot = new DefaultMutableTreeNode("invisible");//per avere le gerarchie tutte unite
        for (Gerarchia g : gerarchie) {
            invisibleRoot.add(buildNode(g.getRadice()));
        }

        JTree tree = new JTree(invisibleRoot);
        tree.setCellRenderer(new CustomTreeCellRenderer(expandedAndLocked,creation));
        tree.setUI(new CustomTreeUI());
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setCellRenderer(new CustomTreeCellRenderer(expandedAndLocked,creation));
        tree.putClientProperty("Tree.paintLines", Boolean.FALSE);
        
        // Espansione automatica dei nodi interni, lasciando chiuse le radici
        
        if(expandedAndLocked&&creation) {
//        	expandAll(tree,tree.getModel());
//        	// impedisce di compattare il tree, voglio che rimanga completamente espanso 
//        	tree.addTreeWillExpandListener(new TreeWillExpandListener() {
//                 @Override
//                 public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
//                     throw new ExpandVetoException(event, "Expansion not allowed");
//                 }
//                 
//                 @Override
//                 public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
//                     throw new ExpandVetoException(event, "Collapse not allowed");
//                 }
//             });
        }else if (expandedAndLocked) {
            expandAllExceptRoot(tree, tree.getModel());
        }else if(creation) {
        	expandAll(tree,tree.getModel());
        	
        	// impedisce di compattare il tree, voglio che rimanga completamente espanso 
        	tree.addTreeWillExpandListener(new TreeWillExpandListener() {
                 @Override
                 public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                     throw new ExpandVetoException(event, "Expansion not allowed");
                 }
                 
                 @Override
                 public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                     throw new ExpandVetoException(event, "Collapse not allowed");
                 }
             });
        }
       
        
        
        tree.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                if (path != hoveredPath) {
                    hoveredPath = path;
                    tree.repaint();
                }
            }
        });

        return tree;
    }

    private static void expandAll(JTree tree, TreeModel model) {
    	DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        
        // Prima espandiamo tutto l'albero
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) root.getChildAt(i);
            expandAllNodesRecursively(tree, rootNode);
        }
        
        tree.setEditable(false);
	}

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
