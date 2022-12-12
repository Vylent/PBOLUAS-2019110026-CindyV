/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 2019110026CindyV
 */
public class FXMLDocumentController implements Initializable {
    public static DBBahan dtbahan=new DBBahan();
    public static DBResep dtresep=new DBResep();
    public static DBDetailResep dtdetresep=new DBDetailResep();
    public static DBCraft dtcraft=new DBCraft();
    public static DBHasil dthasil=new DBHasil();

    private Label label;
    @FXML
    private Button btnlihatbahan;
    @FXML
    private Button btntambahbahan;
    @FXML
    private Button btnlihatresep;
    @FXML
    private Button btntambahresep;
    @FXML
    private Button btnhasil;
    @FXML
    private Button btnlihatcraft;
    @FXML
    private Button btntambahcraft;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void lihatbahanklik(ActionEvent event) {
    try{  
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_DisplayBahan.fxml"));    
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

    @FXML
    private void tambahbahanklik(ActionEvent event) {
     try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputBahan.fxml"));    
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

    @FXML
    private void lihatresepklik(ActionEvent event) {
    try{  
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_DisplayResep.fxml"));    
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

    @FXML
    private void tambahresepklik(ActionEvent event) {
    try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputRecipe.fxml"));    
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

    @FXML
    private void hasilklik(ActionEvent event) {
    
    }

    @FXML
    private void lihatcraftklik(ActionEvent event) {
     try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_DisplayCraft.fxml"));    
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

    @FXML
    private void btntambahklik(ActionEvent event) {
    }
    
}
