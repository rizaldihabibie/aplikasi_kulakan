/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.util;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Rizaldi Habibie
 */
public class Table {
    
    public static void resizeTable(JTable table, float[] size){
        int tW =  table.getWidth();
        TableColumn column;
        TableColumnModel jTableColumnModel =  table.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(size[i] * tW);
            column.setPreferredWidth(pWidth);
        }
        
    }
}
