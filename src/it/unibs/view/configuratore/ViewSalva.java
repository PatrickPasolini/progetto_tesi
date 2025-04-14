package it.unibs.view.configuratore;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import it.unibs.view.atomicElements.BaseView;
import it.unibs.view.atomicElements.CircleHoverIconButton;

public class ViewSalva extends BaseView {
	private static final long serialVersionUID = 1L;
	private static final String HOME_PATH = "./Img/home.png";
	private  JLabel lblTitolo;
    private CircleHoverIconButton btnBack;
    
	public ViewSalva(JFrame frame) {
		super(frame,frame.getWidth()-200,frame.getHeight()-200);
	}

	@Override
	protected void inizializzaComponenti() {
		lblTitolo = new JLabel("Lista comprensori");
		btnBack = new CircleHoverIconButton(HOME_PATH, 50);
	}

	@Override
	protected void aggiornaComponenti(int w, int h) {
		contentPanel.removeAll();
        
        int contentWidth = contentPanel.getWidth();
        String txt = "<html><div align='center'>Salvataggio delle modifiche<br> effettuato con successo"
        		+ "<br>puoi tornare alla schermata home</div></html>";  
        lblTitolo.setText(txt);
        lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 55));
        Dimension size = lblTitolo.getPreferredSize();
        lblTitolo.setBounds((contentWidth - size.width) / 2, 50, size.width, size.height);
        contentPanel.add(lblTitolo);
		
        btnBack.setBounds(45, 45, 90, 90);
        contentPanel.add(btnBack);
        
        revalidate();
        repaint();
	}
	
	public void setBtnHomeListener(ActionListener listener) {
		btnBack.addActionListener(listener);
	}
}
