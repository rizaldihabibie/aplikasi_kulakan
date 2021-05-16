/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Rizaldi Habibie
 */
public class TableHeaderRenderer extends JLabel implements TableCellRenderer {

    public TableHeaderRenderer(){
        setFont(new Font("Lucida Sans", Font.BOLD, 12));
        setOpaque(true);
        setForeground(Color.WHITE);
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createEtchedBorder());
        setHorizontalAlignment(SwingConstants.CENTER);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }
    
}
