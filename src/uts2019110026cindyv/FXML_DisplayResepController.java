/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts2019110026cindyv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2019110026CindyV
 */
public class FXML_DisplayResepController implements Initializable {

    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btntambah;
    @FXML
    private TableView<ResepModel> tbvresep;
    @FXML
    private Button btndetail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    
    public void showdata(){
        ObservableList<ResepModel> data=FXMLDocumentController.dtresep.Load();
        if(data!=null){            
            tbvresep.getColumns().clear();
            tbvresep.getItems().clear();
            TableColumn col=new TableColumn("IdResep");
            col.setCellValueFactory(new PropertyValueFactory<ResepModel, String>("IdResep"));
            tbvresep.getColumns().addAll(col);
            col=new TableColumn("NamaResep");
            col.setCellValueFactory(new PropertyValueFactory<ResepModel, String>("NamaResep"));
            tbvresep.getColumns().addAll(col);
            col=new TableColumn("SuccessRate");
            col.setCellValueFactory(new PropertyValueFactory<ResepModel, String>("SuccessRate"));
            tbvresep.getColumns().addAll(col);
            tbvresep.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvresep.getScene().getWindow().hide();
        }                
    }


    @FXML
    private void awalklik(ActionEvent event) {
       tbvresep.getSelectionModel().selectFirst();
       tbvresep.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
       tbvresep.getSelectionModel().selectAboveCell();
       tbvresep.requestFocus();   
    }

    @FXML
    private void akhirklik(ActionEvent event) {
       tbvresep.getSelectionModel().selectLast();
       tbvresep.requestFocus();  
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
       tbvresep.getSelectionModel().selectBelowCell();
       tbvresep.requestFocus();
    }

    @FXML
    private void ubahklik(ActionEvent event) {
    ResepModel r = new ResepModel();
        r=tbvresep.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputResep.fxml"));    
        Parent root = (Parent)loader.load();
        FXML_InputResepController isidt=(FXML_InputResepController)loader.getController();
        isidt.execute(r);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
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
    private void keluarklik(ActionEvent event) {
       btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
    ResepModel r= new ResepModel();
        r=tbvresep.getSelectionModel().getSelectedItem();
            Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",
                    ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            if(a.getResult()==ButtonType.YES){
                if(FXMLDocumentController.dtbahan.delete(r.getIdResep())){
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
    private void tambahklik(ActionEvent event) {
    try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputResep.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);        
            Stage stg=new Stage();
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
    private void detailklik(ActionEvent event) {
        try{  
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_DisplayDetailResep.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();        
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
