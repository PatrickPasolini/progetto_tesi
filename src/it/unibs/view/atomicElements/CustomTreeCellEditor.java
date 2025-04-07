package it.unibs.view.atomicElements;

import java.awt.*;

import javax.swing.AbstractCellEditor;
import javax.swing.*;
import javax.swing.tree.*;

import it.unibs.domain.Categoria;

public class CustomTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {
    private JPanel panel;
    private JLabel label;
    private JButton addButton;
    private DefaultMutableTreeNode currentNode;

    public CustomTreeCellEditor(JTree tree) {
        panel = new JPanel(new BorderLayout());
        label = new JLabel();
        addButton = new JButton("+");
        addButton.setMargin(new Insets(0, 5, 0, 5));
        addButton.setFocusable(false);
        addButton.setFont(new Font("Arial", Font.BOLD, 12));

        panel.setOpaque(false);
        panel.add(label, BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.EAST);

        addButton.addActionListener(e -> {
//            if (currentNode != null) {
//                Categoria parent = (Categoria) currentNode.getUserObject();
//                Categoria newChild = new Categoria("Nuovo nodo"); // o usa un dialog per inserire il nome
//                parent.addChild(newChild);
//                currentNode.add(new DefaultMutableTreeNode(newChild));
//                ((DefaultTreeModel) tree.getModel()).reload(currentNode);
//                tree.expandPath(new TreePath(currentNode.getPath()));
//            }
        });
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean selected,
                                                boolean expanded, boolean leaf, int row) {
        currentNode = (DefaultMutableTreeNode) value;
        Object userObj = currentNode.getUserObject();
        if (userObj instanceof Categoria) {
            label.setText(((Categoria) userObj).getNome());
        } else {
            label.setText(userObj.toString());
        }
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return currentNode.getUserObject();
    }

	
}
