/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Supplier;
import aplikasi.kulakan.service.SupplierService;
import aplikasi.kulakan.service.impl.SupplierServiceImpl;
import aplikasi.kulakan.util.ButtonColumn;
import aplikasi.kulakan.view.ViewSupplier;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class ViewSupplierPageController {
    private ViewSupplier viewSupplier = new ViewSupplier();
    private SupplierService supplierService = new SupplierServiceImpl();
    private List<Supplier> listSupplier;
    
    public ViewSupplierPageController(){
        viewSupplier.getMonthOption().setModel(new DefaultComboBoxModel(supplierService.supllierName()));
        listSupplier = supplierService.getAllData();
        viewDataOnTable();
        viewSupplier.getSaveButton().addActionListener(this::saveButton);
        viewSupplier.getSearchButton().addActionListener(this::findData);
        viewSupplier.getPhoneNumberField().addActionListener(this::saveButton);
    }
    public void viewDataOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Suppplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Alamat Supplier");
        model.addColumn("Nomor Telpon");
        model.addColumn("Hapus");
        for(Supplier supplier : listSupplier){
            Object[] obj = new Object[5];
            obj[0] = supplier.getKodeSupplier();
            obj[1] = supplier.getNamaSupplier();
            obj[2] = supplier.getAlamatSupplier();
            obj[3] = supplier.getTelponSupplier();
            obj[4] = "Hapus";
            model.addRow(obj);
        }
        viewSupplier.getViewTable().setModel(model);
        Action delete  = new AbstractAction()
        {
        @Override
        public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf(e.getActionCommand());
                deleteData(listSupplier.get(modelRow));
                viewDataOnTable();
                viewSupplier.getMonthOption().setModel(new DefaultComboBoxModel(supplierService.supllierName()));
            }
        };
               
        ButtonColumn buttonDelete = new ButtonColumn(viewSupplier.getViewTable(), delete, 4);
        buttonDelete.setMnemonic(KeyEvent.VK_D);
        
        viewSupplier.getViewTable().validate();
    }
    public ViewSupplier showSupplierPage(){
        return viewSupplier;
    }
    
    public void saveButton(java.awt.event.ActionEvent awt){
        Supplier supplier = new Supplier();
        supplier.setAlamatSupplier(viewSupplier.getSupplierAddressField().getText());
        supplier.setNamaSupplier(viewSupplier.getSupplierNameField().getText());
        supplier.setTelponSupplier(viewSupplier.getPhoneNumberField().getText());
        supplier.setKodeSupplier(viewSupplier.getCodeField().getText());
        if(checkValidation(supplier)){
            supplierService.addSupplier(supplier);
            listSupplier = supplierService.getAllData();
            viewDataOnTable();
            empty();
            viewSupplier.getCodeField().requestFocus();
            viewSupplier.getMonthOption().setModel(new DefaultComboBoxModel(supplierService.supllierName()));
        }
    }
    
    public boolean checkValidation(Supplier supplier){
        boolean passed = false;
        if(supplierService.findBySupplierCode(supplier.getKodeSupplier()) != null){
            JOptionPane.showMessageDialog(viewSupplier, "Kode Supplier Tidak Boleh Sama","Warning", JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(!viewSupplier.getSupplierNameField().getText().equals("") | !viewSupplier.getSupplierNameField().getText().isEmpty()){
                if(!viewSupplier.getSupplierAddressField().getText().equals("") | !viewSupplier.getSupplierAddressField().getText().isEmpty() ){
                        if(!viewSupplier.getCodeField().getText().equals("") | !viewSupplier.getCodeField().getText().isEmpty()){
                           passed = true; 
                        }else{
                            JOptionPane.showMessageDialog(null, "Error","Error On Form", JOptionPane.ERROR_MESSAGE);
                        }
                }else{
                    JOptionPane.showMessageDialog(null, "Error","Error On Form", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Error","Error On Form", JOptionPane.ERROR_MESSAGE);
            }
        }
        return passed;
    }
    public void findData(java.awt.event.ActionEvent e){
        if(viewSupplier.getMonthOption().getSelectedIndex() != 0 ){
            listSupplier = supplierService.getAllDataByName(viewSupplier.getMonthOption().getSelectedItem().toString());
            viewDataOnTable();
        }
        
    }
    public void empty(){
        viewSupplier.getSupplierAddressField().setText("");
        viewSupplier.getSupplierNameField().setText("");
        viewSupplier.getPhoneNumberField().setText("");
        viewSupplier.getCodeField().setText("");
    }
    
    public void deleteData(Supplier suppier){
        supplierService.deleteData(suppier);
        listSupplier = supplierService.getAllData();
    }
    
    
  
}
