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
public class FXML_InputDetailResepController implements Initializable {
    boolean editdata=false;
        
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnkembali;
    @FXML
    private TextField txtidresep;
    @FXML
    private TextField txtiditem;
    @FXML
    private TextField txtjumlah;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void simpanklik(ActionEvent event) {
    DetailResepModel d=new DetailResepModel();
        d.setIdResep(txtidresep.getText());
        d.setIdItem(txtiditem.getText());
        d.setJumlah(Integer.parseInt(txtjumlah.getText()));        
        FXMLDocumentController.dtdetresep.setDetailResepModel(d);
        if(editdata){
            if(FXMLDocumentController.dtdetresep.update()){
             
        Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtidresep.setEditable(true);
               hapusklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXMLDocumentController.dtdetresep.validasi(d.getIdResep(),d.getIdItem())<=0){
            if(FXMLDocumentController.dtdetresep.insert()){
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
            txtidresep.requestFocus();
        }
    }

    @FXML
    private void hapusklik(ActionEvent event) {
    txtidresep.setText("");
    txtiditem.setText("");
    txtjumlah.setText("");
    txtidresep.requestFocus();
    }

    @FXML
    private void kembaliklik(ActionEvent event) {
    btnkembali.getScene().getWindow().hide();
    }
    
    public void execute(DetailResepModel d){
        if(!d.getIdResep().isEmpty()&&!d.getIdItem().isEmpty()){
          editdata=true;
          txtidresep.setText(d.getIdResep());
          txtiditem.setText(d.getIdItem());         
          txtjumlah.setText(String.valueOf(d.getJumlah()));
          txtidresep.setEditable(false);
          txtiditem.setEditable(false);
          txtjumlah.requestFocus();
        }
    }
}
