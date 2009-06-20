/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package opc.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

/**
 *
 * @author ZHAO QINGHUA
 */
public class StockOptionsTabbedPane extends OPCTabbedPane {

    public StockOptionsTabbedPane()
    {
        super();
        initComponent();
    }

    private void initComponent()
    {
        JComponent panel1 = makeTextPanel("Panel #1");
        this.addTab("Tab 1", null, panel1, "Does nothing");
        this.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("Panel #2");
        this.addTab("Tab 2", null, panel2, "Does twice as much nothing");
        this.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = makeTextPanel("Panel #3");
        this.addTab("Tab 3", null, panel3, "Still does nothing");
        this.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent panel4 = makeTextPanel("Panel #4 (has a preferred size of 410 x 50).");
        panel4.setPreferredSize(new Dimension(410, 50));
        this.addTab("Tab 4", null, panel4, "Does nothing at all");
        this.setMnemonicAt(3, KeyEvent.VK_4);
    }

    private JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}
