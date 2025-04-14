package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import it.unibs.domain.Gerarchia;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.CustomTree;
import it.unibs.view.atomicElements.RoundedButton;
import it.unibs.view.atomicElements.RoundedButtonPlus;
import it.unibs.view.atomicElements.TextFieldWithPlaceholder;

public class ViewAddGerarchiaNonFoglia extends BaseView {
	private static final long serialVersionUID = 1L;
	private JLabel lblRadice;
	private TextFieldWithPlaceholder radiceField;
	private TextFieldWithPlaceholder descrizioneField;
	private TextFieldWithPlaceholder campoField;
	private TextFieldWithPlaceholder dominioToAddField;
	private RoundedButton btnPlus;
	private DefaultListModel<String> listModel;
    private JList<String> dominiList;
	private RoundedButton btnAvanti;
	
	private List<Gerarchia> gerarchie = new ArrayList<>();
	public ViewAddGerarchiaNonFoglia(JFrame frame, List<Gerarchia> gerarchie) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
		this.gerarchie.add(gerarchie.get(0));
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblRadice = new JLabel("Creazione nuova gerarchia");
		radiceField = new TextFieldWithPlaceholder("Radice");
		descrizioneField = new TextFieldWithPlaceholder("Descrizione opzionale");
		campoField = new TextFieldWithPlaceholder("Campo");
		dominioToAddField = new TextFieldWithPlaceholder("Dominio da aggiungere");
		btnPlus = new RoundedButtonPlus(Color.GRAY);
		listModel = new DefaultListModel<>();
		dominiList = new JList<>(listModel);
		btnAvanti = new RoundedButton("Avanti", new Color(8, 102, 255));
		
		
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
	    int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        lblRadice.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblRadice.getPreferredSize();
        lblRadice.setBounds(contentWidth / 3 - size.width/2, 20, size.width, 70);
        contentPanel.add(lblRadice);
        
        radiceField.setColumns(10);
        radiceField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        radiceField.setMargin(new Insets(10, 10, 10, 10));
        radiceField.setBounds(contentWidth / 3 - 250, 120, 500, 80);
        contentPanel.add(radiceField); 
        
        descrizioneField.setColumns(10);
        descrizioneField.setMargin(new Insets(10, 10, 10, 10));
        descrizioneField.setBounds(contentWidth / 3 - 250, 220, 500, 80);
        contentPanel.add(descrizioneField); 
        
        campoField.setColumns(10);
        campoField.setMargin(new Insets(10, 10, 10, 10));
        campoField.setBounds(contentWidth / 3 - 250, 320, 500, 80);
        contentPanel.add(campoField);
        
        dominioToAddField.setColumns(10);
        dominioToAddField.setMargin(new Insets(10, 10, 10, 10));
        dominioToAddField.setBounds(contentWidth / 3 - 250, 420, 500, 80);
        contentPanel.add(dominioToAddField); 
        
        btnPlus.setBorder(null);
        btnPlus.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnPlus.setBounds(contentWidth / 3 + 252, 422, 80-4, 80-4);
        btnPlus.setForeground(Color.WHITE);	
        contentPanel.add(btnPlus);
        
        dominiList.setFont(new Font("Tahoma", Font.PLAIN, 28));
		dominiList.setBackground(contentPanel.getBackground());
		JScrollPane scrollPane = new JScrollPane(dominiList);
		scrollPane.setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createLineBorder(Color.BLACK, 1),
			    BorderFactory.createEmptyBorder(10, 20, 10, 10)
			));
		scrollPane.setBounds(contentWidth / 3 - 250, 520, 500, 200);
		scrollPane.setBackground(contentPanel.getBackground());
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		contentPanel.add(scrollPane);
        
        btnAvanti.setBorder(null);
        btnAvanti.setMargin(new Insets(0, 10, 0, 0));
        btnAvanti.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnAvanti.setBounds(contentWidth / 3 - 200, contentHeight-110, 400, 80);
        btnAvanti.setForeground(Color.WHITE);
        contentPanel.add(btnAvanti);
        
        if (gerarchie != null && !gerarchie.isEmpty()) {
	        JTree tree = CustomTree.createUnifiedTree(gerarchie,false);
	        tree.setBackground(contentPanel.getBackground());
	        tree.setToggleClickCount(1);
        
        
	        JScrollPane scrollPaneTree = new JScrollPane(tree);
	//        scrollPane.setBorder(null);
	        scrollPaneTree.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        scrollPaneTree.setBackground(contentPanel.getBackground());
	        scrollPaneTree.setBounds(contentPanel.getWidth()/2+100, 120, 600, contentPanel.getHeight()-250);
	        scrollPaneTree.getVerticalScrollBar().setUI(new CustomScrollBarUI());
	        scrollPaneTree.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
	        scrollPaneTree.getVerticalScrollBar().setUnitIncrement(20);
	        contentPanel.add(scrollPaneTree);
        }
        
        contentPanel.revalidate();
	    contentPanel.repaint();
	}
	
//	public void aggiornaAlbero(Gerarchia gerarchia) {
//		gerarchie.clear();
//		gerarchie.add(gerarchia);
//		aggiornaComponenti(frame.getWidth(), frame.getWidth());
//		
//	}
}
