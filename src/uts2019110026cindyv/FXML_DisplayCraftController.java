/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts2019110026cindyv;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
public class FXML_DisplayCraftController implements Initializable {

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
    private TableView<CraftModel> tbvcraft;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }   
    
     public void showdata(){
        ObservableList<CraftModel> data=FXMLDocumentController.dtcraft.Load();
        if(data!=null){            
            tbvcraft.getColumns().clear();
            tbvcraft.getItems().clear();
            TableColumn col=new TableColumn("IdCraft");
            col.setCellValueFactory(new PropertyValueFactory<CraftModel, String>("IdCraft"));
            tbvcraft.getColumns().addAll(col);
            col=new TableColumn("IdResep");
            col.setCellValueFactory(new PropertyValueFactory<CraftModel, String>("IdResep"));
            tbvcraft.getColumns().addAll(col);
            col=new TableColumn("TanggalCraft");
            col.setCellValueFactory(new PropertyValueFactory<CraftModel, Date>("TanggalCraft"));
            tbvcraft.getColumns().addAll(col);
            col=new TableColumn("Jumlah");
            col.setCellValueFactory(new PropertyValueFactory<CraftModel, Integer>("Jumlah"));
            tbvcraft.getColumns().addAll(col);
            tbvcraft.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvcraft.getScene().getWindow().hide();
        }           
    }

    @FXML
    private void awalklik(ActionEvent event) {
      tbvcraft.getSelectionModel().selectFirst();
      tbvcraft.requestFocus();
    } 
    
    @FXML
    private void sesudahklik(ActionEvent event) {
      tbvcraft.getSelectionModel().selectBelowCell();
      tbvcraft.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
      tbvcraft.getSelectionModel().selectAboveCell();
      tbvcraft.requestFocus();    
    }
    
    @FXML
    private void akhirklik(ActionEvent event) {
      tbvcraft.getSelectionModel().selectLast();
      tbvcraft.requestFocus();  
    } 

    @FXML
    private void ubahklik(ActionEvent event) {
    }

    @FXML
    private void keluarklik(ActionEvent event) {
     btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
     CraftModel s= new CraftModel();
        s=tbvcraft.getSelectionModel().getSelectedItem();
            Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",
                    ButtonType.YES,ButtonType.NO);
            a.showAndWait();
            if(a.getResult()==ButtonType.YES){
                if(FXMLDocumentController.dtcraft.delete(s.getIdCraft())){
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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputCraft.fxml"));    
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
    
}
