/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts2019110026cindyv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2019110026CindyV
 */
public class FXML_DisplayBahanController implements Initializable {

    @FXML
    private TableView<BahanModel> tbvbahan;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnawal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    
    public void showdata(){
        ObservableList<BahanModel> data=FXMLDocumentController.dtbahan.Load();
        if(data!=null){            
            tbvbahan.getColumns().clear();
            tbvbahan.getItems().clear();
            TableColumn col=new TableColumn("IdItem");
            col.setCellValueFactory(new PropertyValueFactory<BahanModel, String>("IdItem"));
            tbvbahan.getColumns().addAll(col);
            col=new TableColumn("NamaItem");
            col.setCellValueFactory(new PropertyValueFactory<BahanModel, String>("NamaItem"));
            tbvbahan.getColumns().addAll(col);
            col=new TableColumn("JenisItem");
            col.setCellValueFactory(new PropertyValueFactory<BahanModel, String>("JenisItem"));
            tbvbahan.getColumns().addAll(col);
            tbvbahan.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvbahan.getScene().getWindow().hide();
        }        
        
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputBahan.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);        Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e){
            e.printStackTrace();
        }
        showdata();
        awalklik(event);

    }

    @FXML
    private void hapusklik(ActionEvent event) {
        BahanModel s= new BahanModel();
        s=tbvbahan.getSelectionModel().getSelectedItem();
            Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",
                    ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            if(a.getResult()==ButtonType.YES){
                if(FXMLDocumentController.dtbahan.delete(s.getIdItem())){
                    Alert b=new Alert(Alert.AlertType.INFORMATION,
                            "Data berhasil dihapus", ButtonType.OK);
                    b.showAndWait();
                } else {
                    Alert b=new Alert(Alert.AlertType.ERROR,
                            "Data gagal dihapus", ButtonType.OK);
                    b.showAndWait();               
                }    
                showdata();
                awalklik(event);
            }        
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void ubahklik(ActionEvent event) {
    BahanModel s= new BahanModel();
        s=tbvbahan.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputBahan.fxml"));    
        Parent root = (Parent)loader.load();
        FXML_InputBahanController isidt=(FXML_InputBahanController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  awalklik(event);

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


    @FXML
    private void sebelumklik(ActionEvent event) {
      tbvbahan.getSelectionModel().selectAboveCell();
      tbvbahan.requestFocus();    

    }

    @FXML
    private void awalklik(ActionEvent event) {
      tbvbahan.getSelectionModel().selectFirst();
      tbvbahan.requestFocus();
    }      
    
}
