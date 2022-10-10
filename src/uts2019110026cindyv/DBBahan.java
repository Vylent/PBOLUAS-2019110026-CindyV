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
public class DBBahan {
    private BahanModel dt=new BahanModel();    
    public BahanModel getBahanModel(){ return(dt);}
    public void setBahanModel(BahanModel b){ dt=b;}    

    public ObservableList<BahanModel>  Load() {
        try {   ObservableList<BahanModel> TableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
           con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select IdItem, NamaItem, JenisItem from bahan");
            int i = 1;
            while (rs.next()) {
                BahanModel d=new BahanModel();
                d.setIdItem(rs.getString("IdItem")); 
                d.setNamaItem(rs.getString("NamaItem"));
                d.setJenisItem(rs.getString("JenisItem"));
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
    
    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from bahan where IdItem = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into bahan (IdItem, NamaItem, JenisItem) values (?,?,?)");
            con.preparedStatement.setString(1, getBahanModel().getIdItem());
            con.preparedStatement.setString(2, getBahanModel().getNamaItem());
            con.preparedStatement.setString(3, getBahanModel().getJenisItem());
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

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from bahan where IdItem  = ? ");
            con.preparedStatement.setString(1, nomor);
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
                    "update bahan set NamaItem = ?, JenisItem = ?  where  IdItem = ? ; ");
            con.preparedStatement.setString(1, getBahanModel().getNamaItem());
            con.preparedStatement.setString(2, getBahanModel().getJenisItem());
            con.preparedStatement.setString(3, getBahanModel().getIdItem());
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
