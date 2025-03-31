package it.unibs.view.atomicElements;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class CustomTree extends DefaultTreeCellRenderer {
    private final Font normalFont = new Font("Arial", Font.PLAIN, 30);
    private final Font boldFont = new Font("Arial", Font.BOLD, 30);

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
        JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        
        // Il nodo è una radice se il suo parent è il nodo invisibile
        if (node.getParent() != null && node.getParent().getParent() == null) {
            label.setFont(boldFont);
        } else {
            label.setFont(normalFont);
        }
        
        return label;
    }
}

