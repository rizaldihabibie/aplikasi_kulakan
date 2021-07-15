/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.util;

import aplikasi.kulakan.model.Barang;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author guest
 */
public class BarangGlazedFilterator implements TextFilterator<Barang> {
    @Override
    public void getFilterStrings(List<String> list, Barang e) {
        String[] splitted= e.getNamaBarang().split(" ");
        list.add(e.getNamaBarang());
        list.add(e.getKodeBarang());
        for(int i=0; i<splitted.length; i++){
            list.add(splitted[i]);
        }
        list.add(e.getNamaBarang());
    }
}
