package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import it.unibs.domain.Gerarchia;
import it.unibs.domain.NonFoglia;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CircleHoverIconButton;
import it.unibs.view.atomicElements.CustomRadioButtonUI;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.CustomTree;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewAddGerarchia extends BaseView{
	protected static final String ARROWLEFT_PATH = "./Img/arrowLeft.png";
	protected static final String HOME_PATH = "./Img/home.png";
	protected CircleHoverIconButton btnBack;
	protected CircleHoverIconButton btnHome;
	protected JTree tree;
	public RoundedButton btnAvanti;
	private RoundedButton btnContinua;
	private JRadioButton rbCategoria;
	private JRadioButton rbPrestazione;
	protected JLabel lblSceltaCategoria;  
//	private ButtonGroup rbGroup;
	
	public ViewAddGerarchia(JFrame frame) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
	}

	@Override
	protected void inizializzaComponenti() {
		btnHome = new CircleHoverIconButton(HOME_PATH, 50);
		btnBack = new CircleHoverIconButton(ARROWLEFT_PATH, 50);
		lblSceltaCategoria = new JLabel("<html><div align='center'>" +
					"Seleziona una categoria di prestazione a cui <br>" +
					"aggiungere una categoria o una prestazione specifica</div></html>");
		rbCategoria = new JRadioButton("Categoria di prestazione");
        rbPrestazione = new JRadioButton("Prestazione specifica");
		btnContinua= new RoundedButton("Conferma scelta e prosegui", new Color(8, 102, 255));
//		rbGroup = new ButtonGroup();
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		// TODO Auto-generated method stub
	}
	
	public void visualizzaSceltaNodo(List<Gerarchia> gerarchiaInCostruzione) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        
		
        lblSceltaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 55));
        Dimension size = lblSceltaCategoria.getPreferredSize();
        lblSceltaCategoria.setBounds((contentWidth - size.width) / 2, 30, size.width, size.height);
        contentPanel.add(lblSceltaCategoria);
        
        if (gerarchiaInCostruzione != null && !gerarchiaInCostruzione.isEmpty()) {
	        tree = CustomTree.createUnifiedTree(gerarchiaInCostruzione,false,true);
	        tree.setBackground(contentPanel.getBackground());
	        tree.setEditable(false);
        }
        
        JScrollPane scrollPane = new JScrollPane(tree);
//        scrollPane.setBorder(null);
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(contentPanel.getBackground());
        scrollPane.setBounds(contentPanel.getWidth()/2-400, size.height+50, 800, 450);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        contentPanel.add(scrollPane);
        
        rbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 34));
        rbCategoria.setBounds(contentWidth/ 2 - 430, 650, 400, 100);
        rbCategoria.setUI(new CustomRadioButtonUI());
        contentPanel.add(rbCategoria);
        
        rbPrestazione.setFont(new Font("Tahoma", Font.PLAIN, 34));
        rbPrestazione.setBounds(contentWidth/ 2 + 30, 650, 400, 100);
        rbPrestazione.setUI(new CustomRadioButtonUI());
        contentPanel.add(rbPrestazione);
        
        
        ButtonGroup rbGroup = new ButtonGroup();
        rbGroup.add(rbCategoria);
        rbGroup.add(rbPrestazione);
        
        btnContinua.setBorder(null);
	    btnContinua.setMargin(new Insets(0, 10, 0, 0));
	    btnContinua.setFont(new Font("Tahoma", Font.BOLD, 28));
	    btnContinua.setBounds(contentWidth / 2 - 225, 760, 450, 90);
	    btnContinua.setForeground(Color.WHITE);
	    contentPanel.add(btnContinua);
        
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        btnHome.setBounds(140, 45, 90, 90);
        contentPanel.add(btnHome);
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	
	
	
	
	public void setSelezioneFallita() {
		lblSceltaCategoria.setForeground(Color.RED);
		
	    revalidate();
	    repaint();
	}
	
	public void setBtnContinuaListener(ActionListener listener) {
		for (ActionListener al : btnContinua.getActionListeners()) {
			btnContinua.removeActionListener(al);
		}
		btnContinua.addActionListener(listener);
	}
	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener);
	} 
	public void setBtnBackListeners(ActionListener btnListener) {
		for (ActionListener al : btnBack.getActionListeners()) {
			btnBack.removeActionListener(al);
		}
		btnBack.addActionListener(btnListener);
    }
	
	
	/**
	 * Restituisce un valore che rappresenta il tipo di elemento selezionato.
	 * @return 1 se è selezionata una categoria, 2 se è selezionata una prestazione specifica, 0 se nessun elemento è selezionato
	 */
	public int getTipoSelezionato() {
		if (rbCategoria.isSelected()) {
	        return 1;
	    } else if (rbPrestazione.isSelected()) {
	        return 2;
	    } else {
	        // Nessuna selezione
	        return 0;
	    }
	}
	public NonFoglia getFogliaSelezionata() {
        // Recupera il percorso di selezione nel tree
        TreePath selectionPath = tree.getSelectionPath();
        if (selectionPath != null) {
            // Ottiene l'ultimo nodo del percorso
            Object lastComponent = selectionPath.getLastPathComponent();
            if (lastComponent instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) lastComponent;
                Object userObject = node.getUserObject();

                if (userObject instanceof NonFoglia) {
                    return (NonFoglia) userObject;
                }
            }
        }
        return null;
    }

}
