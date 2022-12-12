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
public class DBCraft {
    private CraftModel dt=new CraftModel();    
    public CraftModel getCraftModel(){ return(dt);}
    public void setCraftModel(CraftModel b){ dt=b;}    

    public ObservableList<CraftModel>  Load() {
        try {   ObservableList<CraftModel> TableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
           con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select IdCraft, IdResep, TanggalCraft,Jumlah from craft");
            int i = 1;
            while (rs.next()) {
                CraftModel d=new CraftModel();
                d.setIdCraft(rs.getString("IdCraft")); 
                d.setIdResep(rs.getString("IdResep"));
                d.setTanggalCraft(rs.getDate("TanggalCraft"));
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
    
    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from craft where IdCraft = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into craft (IdCraft, IdResep, TanggalCraft, Jumlah) values (?,?,?,?)");
            con.preparedStatement.setString(1, getCraftModel().getIdCraft());
            con.preparedStatement.setString(2, getCraftModel().getIdResep());
            con.preparedStatement.setDate(3, getCraftModel().getTanggalCraft());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from craft where IdCraft  = ? ");
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
                    "update craft set IdResep = ?, TanggalCraft = ?, Jumlah = ?  where  IdCraft = ? ; ");
            con.preparedStatement.setString(1, getCraftModel().getIdCraft());
            con.preparedStatement.setString(2, getCraftModel().getIdResep());
            con.preparedStatement.setDate(3, getCraftModel().getTanggalCraft());
            con.preparedStatement.setInt(3, getCraftModel().getJumlah());
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
