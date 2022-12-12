/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts2019110026cindyv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 2019110026CindyV
 */
public class FXML_PilihBahanController implements Initializable {

    @FXML
    private ComboBox<String> cmbfield;
    @FXML
    private TextField txtisi;
    @FXML
    private Button btncari;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;

    int hasil=0;
    BahanModel dt=new BahanModel();
    @FXML
    private TableView<BahanModel> tbvbahan;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbfield.setItems(FXCollections.observableArrayList(
        "IdItem","NamaItem","JenisItem"));
        cmbfield.getSelectionModel().select(0);
        showdata("IdItem","");
    }    
    public int getHasil(){
        return(hasil);
    }
    public BahanModel getData(){
        return(dt);
    }
    
    public void showdata(String f,String i){
        ObservableList<BahanModel> data= FXMLDocumentController.dtbahan.LookUp(f, i);
        if(data.isEmpty()){
            data=FXMLDocumentController.dtbahan.Load();
            txtisi.setText("");
        }
        if(data!=null){
            tbvbahan.getColumns().clear();
            tbvbahan.getItems().clear();   
            TableColumn col=new TableColumn("IdItem");
            col.setCellValueFactory(new PropertyValueFactory<BahanModel, String>("IdItem"));
            tbvbahan.getColumns().addAll(col);  col=new TableColumn("NamaItem");
           col.setCellValueFactory(new PropertyValueFactory<BahanModel, String>("NamaItem"));
           tbvbahan.getColumns().addAll(col); col=new TableColumn("JenisItem");
           col.setCellValueFactory(new PropertyValueFactory<BahanModel, String>("JenisItem"));
           tbvbahan.getColumns().addAll(col); 
           tbvbahan.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();   tbvbahan.getScene().getWindow().hide();
        }                
        awalklik(null);  txtisi.requestFocus();  
    }
    
    @FXML
    private void cariklik(ActionEvent event) {
    showdata(cmbfield.getSelectionModel().getSelectedItem(), txtisi.getText());
    }

    @FXML
    private void pilihklik(ActionEvent event) {
     hasil=1;
        int pilihan=tbvbahan.getSelectionModel().getSelectedCells().get(0).getRow();
        dt=tbvbahan.getItems().get(pilihan);        
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
    hasil=0;
        btnbatal.getScene().getWindow().hide(); 
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvbahan.getSelectionModel().selectFirst();
        tbvbahan.requestFocus();    
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvbahan.getSelectionModel().selectAboveCell();
        tbvbahan.requestFocus();   
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvbahan.getSelectionModel().selectBelowCell();
        tbvbahan.requestFocus();
    }
    
    @FXML
    private void akhirklik(ActionEvent event) {
        tbvbahan.getSelectionModel().selectLast();
        tbvbahan.requestFocus();    
    }
    
}
