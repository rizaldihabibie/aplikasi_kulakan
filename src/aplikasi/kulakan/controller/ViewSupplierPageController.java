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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author USER
 */
public class ViewSupplierPageController implements BasicController {
    private ViewSupplier viewSupplier;
    private SupplierService supplierService;
    private MainPageController mainPageController;
    private List<Supplier> listSupplier;
    private static Logger LOG = LogManager.getLogger(ViewSupplierPageController.class);
   
    public ViewSupplierPageController(MainPageController mainPageController){
        this.mainPageController = mainPageController;
    }
    public void viewDataOnTable(){
        try{
            LOG.info("Presenting data supplier on table");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kode");
            model.addColumn("Nama");
            model.addColumn("Alamat");
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
                    viewSupplier.getSupplierOptions().setModel(new DefaultComboBoxModel(supplierService.suplierName()));
                }
            };

            ButtonColumn buttonDelete = new ButtonColumn(viewSupplier.getViewTable(), delete, 4);
            buttonDelete.setMnemonic(KeyEvent.VK_D);
            viewSupplier.getViewTable().getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            viewSupplier.getViewTable().validate();
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
    }
    
    public void saveButton(java.awt.event.ActionEvent awt){
        try{
            LOG.info("saving data supplier");
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
                viewSupplier.getSupplierOptions().setModel(new DefaultComboBoxModel(supplierService.suplierName()));
                LOG.info("Save success");
            }else{
                LOG.info("Data invalid");
            }       
        }catch(Exception e){
            LOG.error(e);
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
        if(viewSupplier.getSupplierOptions().getSelectedIndex() != 0 ){
            listSupplier = supplierService.getAllDataByName(viewSupplier.getSupplierOptions().getSelectedItem().toString());
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

    @Override
    public void init() {
        try{
            LOG.info("Preparing view supplier page");
            viewSupplier = new ViewSupplier();
            supplierService = new SupplierServiceImpl();
            viewSupplier.getSupplierOptions().setModel(new DefaultComboBoxModel(supplierService.suplierName()));
            viewSupplier.getSaveButton().addActionListener(this::saveButton);
            viewSupplier.getSearchButton().addActionListener(this::findData);
            viewSupplier.getPhoneNumberField().addActionListener(this::saveButton);
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
    }

    @Override
    public void start() {
        this.getPage().setSize(mainPageController.getParent().getMenuLayer().getSize());
        this.getPage().setVisible(true);
        listSupplier = supplierService.getAllData();
        viewDataOnTable();
    }

    @Override
    public void stop() {
        this.getPage().setVisible(false);
        viewSupplier = null;
        supplierService = null;
    }

    @Override
    public JPanel getPage() {
        return viewSupplier;
    }
    
    
  
}
