/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts2019110026cindyv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 2019110026CindyV
 */
public class FXML_InputBahanController implements Initializable {
     boolean editdata=false;

    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnkembali;
    @FXML
    private TextField txtiditem;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtjenis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void simpanklik(ActionEvent event) {
        BahanModel b=new BahanModel();
        b.setIdItem(txtiditem.getText());
        b.setNamaItem(txtnama.getText());
        b.setJenisItem(txtjenis.getText());
        FXMLDocumentController.dtbahan.setBahanModel(b);
        if(editdata){
            if(FXMLDocumentController.dtbahan.update()){
             
        Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   txtiditem.setEditable(true);
               hapusklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXMLDocumentController.dtbahan.validasi(b.getIdItem())<=0){
            if(FXMLDocumentController.dtbahan.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();
               hapusklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtiditem.requestFocus();
        }
    }

    @FXML
    private void hapusklik(ActionEvent event) {
    txtiditem.setText("");
    txtnama.setText("");
    txtjenis.setText("");
    txtiditem.requestFocus();
    }

    @FXML
    private void kembaliklik(ActionEvent event) {
    btnkembali.getScene().getWindow().hide();
    }
    
     public void execute(BahanModel d){
        if(!d.getIdItem().isEmpty()){
          editdata=true;
          txtiditem.setText(d.getIdItem());
          txtnama.setText(d.getNamaItem());
          txtjenis.setText(d.getJenisItem());
          txtiditem.setEditable(false);
          txtnama.requestFocus();
        }
    }

    
}
