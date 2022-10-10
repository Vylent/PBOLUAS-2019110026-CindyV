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
public class FXML_DisplayDetailResepController implements Initializable {

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
    private TableView<DetailResepModel> tbvdetresep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showdata();
    }    

    
    public void showdata(){
        ObservableList<DetailResepModel> data=FXMLDocumentController.dtdetresep.Load();
        if(data!=null){            
            tbvdetresep.getColumns().clear();
            tbvdetresep.getItems().clear();
            TableColumn col=new TableColumn("IdResep");
            col.setCellValueFactory(new PropertyValueFactory<DetailResepModel, String>("IdResep"));
            tbvdetresep.getColumns().addAll(col);
            col=new TableColumn("IdItem");
            col.setCellValueFactory(new PropertyValueFactory<DetailResepModel, String>("IdItem"));
            tbvdetresep.getColumns().addAll(col);
            col=new TableColumn("Jumlah");
            col.setCellValueFactory(new PropertyValueFactory<DetailResepModel, String>("Jumlah"));
            tbvdetresep.getColumns().addAll(col);
            tbvdetresep.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvdetresep.getScene().getWindow().hide();
        }                
    }
    @FXML
    private void awalklik(ActionEvent event) {
       tbvdetresep.getSelectionModel().selectFirst();
       tbvdetresep.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
       tbvdetresep.getSelectionModel().selectAboveCell();
       tbvdetresep.requestFocus();   
    }

    @FXML
    private void akhirklik(ActionEvent event) {
       tbvdetresep.getSelectionModel().selectLast();
       tbvdetresep.requestFocus();  
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
       tbvdetresep.getSelectionModel().selectBelowCell();
       tbvdetresep.requestFocus();
    }

    @FXML
    private void ubahklik(ActionEvent event) {
    DetailResepModel d = new DetailResepModel();
        d=tbvdetresep.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputDetailResep.fxml"));    
        Parent root = (Parent)loader.load();
        FXML_InputDetailResepController isidt=(FXML_InputDetailResepController)loader.getController();
        isidt.execute(d);                
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
    DetailResepModel d= new DetailResepModel();
        d=tbvdetresep.getSelectionModel().getSelectedItem();
            Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",
                    ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            if(a.getResult()==ButtonType.YES){
                if(FXMLDocumentController.dtbahan.delete(d.getIdResep())){
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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputDetailResep.fxml"));    
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
    
}
