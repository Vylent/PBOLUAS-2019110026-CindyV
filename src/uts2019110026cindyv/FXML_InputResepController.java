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
public class FXML_InputResepController implements Initializable {
    boolean editdata=false;
    
    @FXML
    private TextField txtsucrate;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtidresep;
    @FXML
    private Button btnkembali;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnsimpan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void kembaliklik(ActionEvent event) {
    btnkembali.getScene().getWindow().hide();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
    txtidresep.setText("");
    txtnama.setText("");
    txtsucrate.setText("");
    txtidresep.requestFocus();
    }

    @FXML
    private void simpanklik(ActionEvent event) {
    ResepModel r=new ResepModel();
        r.setIdResep(txtidresep.getText());
        r.setNamaResep(txtnama.getText());
        r.setSuccessRate(Integer.parseInt(txtsucrate.getText()));        
        FXMLDocumentController.dtresep.setResepModel(r);
        if(editdata){
            if(FXMLDocumentController.dtresep.update()){
             
        Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   txtidresep.setEditable(true);
               hapusklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXMLDocumentController.dtresep.validasi(r.getIdResep())<=0){
            if(FXMLDocumentController.dtresep.insert()){
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
    
    public void execute(ResepModel r){
        if(!r.getIdResep().isEmpty()){
          editdata=true;
          txtidresep.setText(r.getIdResep());
          txtnama.setText(r.getNamaResep());
          txtsucrate.setText(Integer.toString(r.getSuccessRate()));          
          txtidresep.setEditable(false);
          txtnama.requestFocus();
        }
    }
    
}
