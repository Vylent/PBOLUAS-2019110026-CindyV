/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uts2019110026cindyv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 2019110026CindyV
 */
public class FXML_InputRecipeController implements Initializable {

    @FXML
    private TextField txtidresep;
    @FXML
    private TextField txtnamaresep;
    @FXML
    private TextField txtsuccessrate;
    @FXML
    private Label lblnamaitem;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnclear;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnhapus;
    @FXML
    private TableView<DetailResepModel> tbvrecipe;
    
    private boolean editmode=false;
    private DBResep dt=new DBResep();
    @FXML
    private TextField txtiditem;
    @FXML
    private TextField txtjumlah;
    @FXML
    private Button btnpilih;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         dt.getDetailResepModel().clear();
         tbvrecipe.getColumns().clear();
         tbvrecipe.getItems().clear();
         TableColumn col=new TableColumn("idresep");
         col.setCellValueFactory(new PropertyValueFactory<DetailResepModel, String>("IdResep"));
         tbvrecipe.getColumns().addAll(col);
         col=new TableColumn("IdItem");
         col.setCellValueFactory(new PropertyValueFactory<DetailResepModel, String>("IdItem"));
         tbvrecipe.getColumns().addAll(col);
         col=new TableColumn("jumlah");
         col.setCellValueFactory(new PropertyValueFactory<DetailResepModel, Integer>("Jumlah"));
         tbvrecipe.getColumns().addAll(col);
    }    

    @FXML
    private void tambahklik(ActionEvent event) {
    DetailResepModel tmp=new DetailResepModel();
        tmp.setIdResep(txtidresep.getText());
        tmp.setIdItem(txtiditem.getText());
        tmp.setJumlah(Integer.parseInt(txtjumlah.getText()));
        if(dt.getDetailResepModel().get(txtiditem.getText()) ==null){
           dt.setDetailResepModel(tmp);
tbvrecipe.getItems().add(tmp);
       }  else {
                   int p=-1;
                   for(int i=0;i<tbvrecipe.getItems().size();i++){
                       if(tbvrecipe.getItems().get(i).getIdItem().equalsIgnoreCase(
                               txtiditem.getText()))
                          p=i; 
                   }
                   if(p>=0) tbvrecipe.getItems().set(p, tmp);
                   dt.getDetailResepModel().remove(txtiditem.getText());
                   dt.setDetailResepModel(tmp);
        }
        clearklik(event);
    }

    @FXML
    private void clearklik(ActionEvent event) {
        lblnamaitem.setText("");
        txtiditem.setText("");
        txtjumlah.setText("");
        txtiditem.requestFocus();
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        dt.getResepModel().setNamaResep(txtnamaresep.getText());
        dt.getResepModel().setIdResep(txtidresep.getText());
        if(dt.saveall()){
        Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan ",ButtonType.OK);
               a.showAndWait(); 
        } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan ",ButtonType.OK);
               a.showAndWait();             
        }
        batalklik(event);
    }

    @FXML
    private void batalklik(ActionEvent event) {
     clearklik(event);
        txtidresep.setText("");
        txtnamaresep.setText("");            
        tbvrecipe.getItems().clear();
        dt.getDetailResepModel().clear();
        txtidresep.setEditable(true);
        editmode=false;
        txtidresep.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
    System.exit(0);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
    DetailResepModel tmp=tbvrecipe.getSelectionModel().getSelectedItem();
        if(tmp!=null){
           tbvrecipe.getItems().remove(tmp);
           dt.getDetailResepModel().remove(tmp.getIdItem());
           clearklik(event);        
        } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Pilih data dulu",ButtonType.OK);
               a.showAndWait(); tbvrecipe.requestFocus();
        }  
    }

    @FXML
    private void idresepkey(KeyEvent event) {
    }

    @FXML
    private void tbvrecipeklik(MouseEvent event) {
     DetailResepModel tmp=tbvrecipe.getSelectionModel().getSelectedItem();
        if(tmp!=null){
           txtiditem.setText(tmp.getIdItem());
           txtjumlah.setText(String.valueOf(tmp.getJumlah()));
        }
    }

    @FXML
    private void pilihklik(ActionEvent event) {
     try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_PilihBahan.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        FXML_PilihBahanController isidt= (FXML_PilihBahanController)loader.getController();        
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        if(isidt.getHasil()==1) {
            BahanModel tmp=isidt.getData();
            txtiditem.setText(tmp.getIdItem());            
            lblnamaitem.setText(tmp.getNamaItem());
        }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
