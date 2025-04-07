package it.unibs.view.atomicElements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTreeUI;

public class CustomTreeUI extends BasicTreeUI {

    @Override
    protected void paintHorizontalLine(Graphics g, JComponent c, int y, int left, int right) {
        g.setColor(new Color(81, 81, 81));
        super.paintHorizontalLine(g, c, y, left, right);
    }

    @Override
    protected void paintVerticalLine(Graphics g, JComponent c, int x, int top, int bottom) {
        g.setColor(new Color(81, 81, 81));
        super.paintVerticalLine(g, c, x, top, bottom);
    }
    
    /**
     * Serve per non mostrare il pallino laterale dinamico sui nodi 
     */
    @Override
    protected void drawCentered(Component c, Graphics graphics, Icon icon, int x, int y) {
    }
}
