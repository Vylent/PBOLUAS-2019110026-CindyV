/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts2019110026cindyv;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019110026CindyV
 */
public class DBDetailResep {
    private DetailResepModel dt=new DetailResepModel();    
    public DetailResepModel getDetailResepModel(){ return(dt);}
    public void setDetailResepModel(DetailResepModel d){ dt=d;}    

    public ObservableList<DetailResepModel>  Load() {
        try {   ObservableList<DetailResepModel> TableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
           con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select IdResep, IdItem, Jumlah from detailresep");
            int i = 1;
            while (rs.next()) {
                DetailResepModel d=new DetailResepModel();
                d.setIdResep(rs.getString("IdResep")); 
                d.setIdItem(rs.getString("IdItem"));
                d.setJumlah(rs.getInt("Jumlah"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public int validasi(String nomor, String kode) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select count(*) as jml from detailresep where IdResep = '" + nomor + 
                            "' and IdItem='"+kode+"'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    } 

     public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into detailresep (IdResep, IdItem, Jumlah) values (?,?,?)");
            con.preparedStatement.setString(1, getDetailResepModel().getIdResep());
            con.preparedStatement.setString(2, getDetailResepModel().getIdItem());
            con.preparedStatement.setInt(3, getDetailResepModel().getJumlah());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(String nomor,String kode) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from detailresep where IdResep  = ? and IdItem = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, kode);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "update detailresep set jumlah = ? where  IdResep = ? and IdItem = ? ");
            con.preparedStatement.setInt(1, getDetailResepModel().getJumlah());
            con.preparedStatement.setString(2, getDetailResepModel().getIdResep());
            con.preparedStatement.setString(3, getDetailResepModel().getIdItem());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
            }
        }
}
