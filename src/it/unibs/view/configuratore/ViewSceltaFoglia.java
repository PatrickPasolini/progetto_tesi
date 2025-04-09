package it.unibs.view.configuratore;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import it.unibs.domain.Foglia;
import it.unibs.domain.Gerarchia;
import it.unibs.view.atomicElements.*;
public class ViewSceltaFoglia extends BaseView {
	private static final long serialVersionUID = 1L;
	protected static final String ARROWLEFT_PATH = "./Img/arrowLeft.png";
	protected static final String HOME_PATH = "./Img/home.png";
	protected JLabel lblSceltaFoglia;
	private List<Gerarchia> gerarchie;
	private RoundedButton btnContinua;
    private CircleHoverIconButton btnArrowLeft;
	private JTree tree;
	
	public ViewSceltaFoglia(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.gerarchie = gerarchie;
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	} 

	@Override
	protected void inizializzaComponenti() {
		lblSceltaFoglia = new JLabel("Seleziona la prestazione d'opera che necessiti:");
		btnContinua = new RoundedButton("Conferma scelta e prosegui", new Color(8, 102, 255));
		btnArrowLeft = new CircleHoverIconButton(ARROWLEFT_PATH, 50);
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
	    
        sceltaFoglia(contentWidth);
        
        btnArrowLeft.setBounds(45, 45, 90, 90);
        contentPanel.add(btnArrowLeft);
        
        
        btnContinua.setBorder(null);
	    btnContinua.setMargin(new Insets(0, 10, 0, 0));
	    btnContinua.setFont(new Font("Tahoma", Font.BOLD, 28));
	    btnContinua.setBounds(contentWidth / 2 - 225, contentHeight - 120, 450, 90);
	    btnContinua.setForeground(Color.WHITE);
	    contentPanel.add(btnContinua);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}

	protected void sceltaFoglia(int contentWidth) {
		lblSceltaFoglia.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblSceltaFoglia.getPreferredSize();
        lblSceltaFoglia.setBounds((contentWidth - size.width) / 2, 20, size.width, size.height);
        contentPanel.add(lblSceltaFoglia);
        
        if (gerarchie != null && !gerarchie.isEmpty()) {
	        tree = CustomTree.createUnifiedTree(gerarchie,false);
	        tree.setBackground(contentPanel.getBackground());
	        tree.setToggleClickCount(1);
        }
        
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(contentPanel.getBackground());
        scrollPane.setBounds(contentPanel.getWidth()/2-400, size.height+40, 800, contentPanel.getHeight()-165 - size.height);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        contentPanel.add(scrollPane);
        
	}

	public void setSceltaFallita() {
	    lblSceltaFoglia.setForeground(Color.RED);
	    lblSceltaFoglia.setText("<html><div align='center'>Seleziona una prestazione d'opera<br> prima di continuare!</div></html>");
	    aggiornaComponenti(frame.getWidth(), frame.getHeight());
	    revalidate();
	    repaint();
	}
	
	public void setBtnBackListeners(ActionListener btnListener) {
		for (ActionListener al : btnArrowLeft.getActionListeners()) {
			btnArrowLeft.removeActionListener(al);
		}
		btnArrowLeft.addActionListener(btnListener);
    }
	public void setBtnContinuaListener(ActionListener event) {
		btnContinua.addActionListener(event);
	}
	
	public Foglia getFogliaSelezionata() {
        // Recupera il percorso di selezione nel tree
        TreePath selectionPath = tree.getSelectionPath();
        if (selectionPath != null) {
            // Ottiene l'ultimo nodo del percorso
            Object lastComponent = selectionPath.getLastPathComponent();
            if (lastComponent instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) lastComponent;
                Object userObject = node.getUserObject();
                // Verifica se l'oggetto utente è una Foglia (ipotizzando che Foglia estenda o sia compatibile con Categoria)
                if (userObject instanceof Foglia) {
                    return (Foglia) userObject;
                }
            }
        }
        return null;
    }
	
}
