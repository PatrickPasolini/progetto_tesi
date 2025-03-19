package it.unibs.view.configuratore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibs.domain.Comprensorio;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CustomScrollBarUI;
import it.unibs.view.atomicElements.RoundedButton;

public class ViewVisualizzaComprensori extends BaseView {
	private JFrame frame;
	private  JLabel lblTitolo;
	private RoundedButton btnHome;
	private List<Comprensorio>  listComprensori;
	public ViewVisualizzaComprensori(JFrame frame, List<Comprensorio> listComprensori) {
		super(frame,frame.getWidth()-200,650);
		this.frame=frame;
		this.listComprensori=listComprensori;
		aggiornaComponenti(frame.getWidth(), frame.getHeight());
	}

	@Override
	protected void inizializzaComponenti() {
		lblTitolo = new JLabel("Lista comprensori");
		btnHome = new RoundedButton("Home", new Color(8, 102, 255));
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        
        // Calcola le dimensioni del pannello interno
        int contentWidth = contentPanel.getWidth();
        int contentHeight = contentPanel.getHeight();
        
        lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 40));
        Dimension size = lblTitolo.getPreferredSize();
        lblTitolo.setBounds((contentWidth - size.width) / 2, 20, size.width, 70);
        contentPanel.add(lblTitolo);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBackground(contentPanel.getBackground());
        if (listComprensori!=null) {
        	for (Comprensorio c : listComprensori) {
    			System.out.println(c.stampaComprensorio().toString());
    			
    			JLabel lblComp = new JLabel();
                String item = c.getName();
                lblComp.setForeground(new Color(90, 90, 90));
                lblComp.setFont(new Font("Tahoma", Font.PLAIN, 25));
                lblComp.setText(" " + item+":");
                lblComp.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                panel.add(lblComp);
                for (String comune : c.getComuni()) {
                	JLabel lblComune = new JLabel();
                    lblComune.setForeground(Color.GRAY);
                    lblComune.setFont(new Font("Tahoma", Font.PLAIN, 20));
                    lblComune.setText("     - " + comune);
                    lblComune.setAlignmentX(JLabel.LEFT_ALIGNMENT);
                    panel.add(lblComune);
                }
    			
    		}
		}
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBackground(contentPanel.getBackground());
        scrollPane.setBounds(240, 120, contentWidth-480, contentHeight-btnHome.getHeight()-lblTitolo.getHeight()-100); 
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        contentPanel.add(scrollPane);
        
        
        btnHome.setMargin(new Insets(0, 10, 0, 0));
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnHome.setBounds(contentWidth / 2 - 170, contentHeight-100, 340, 60);
        btnHome.setForeground(Color.WHITE);
        contentPanel.add(btnHome);
        
        revalidate();
        repaint();
	}
	
   

	public void setBtnHomeListener(ActionListener listener) {
		btnHome.addActionListener(listener); // Riaggiungiamo il listener
	}

}
