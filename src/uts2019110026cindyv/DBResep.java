/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts2019110026cindyv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 2019110026CindyV
 */
public class DBResep {
    private ResepModel dt=new ResepModel();    
    private HashMap<String,DetailResepModel> dt2=new HashMap<String,DetailResepModel>();
    public ResepModel getResepModel(){ return(dt);}
    public void setResepModel(ResepModel r){ dt=r;}  
    public HashMap<String,DetailResepModel> getDetailResepModel(){ return(dt2); }
    public void setDetailResepModel(DetailResepModel d){ dt2.put(d.getIdItem(), d); }

    public ObservableList<ResepModel>  Load() {
        try {   ObservableList<ResepModel> TableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
           con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
            "Select IdResep, NamaResep, SuccessRate from resep");
            int i = 1;
            while (rs.next()) {
                ResepModel d=new ResepModel();
                d.setIdResep(rs.getString("IdResep")); 
                d.setNamaResep(rs.getString("NamaResep"));
                d.setSuccessRate(rs.getInt("SuccessRate"));
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
    
    public ObservableList<DetailResepModel>  LoadDetil() {
        try {
            ObservableList<DetailResepModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            dt2.clear();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "Select * from detailresep where idresep = '"+getResepModel().getIdResep()+"'");
            int i = 1;
            while (rs.next()) {
                DetailResepModel d=new DetailResepModel();
                d.setIdResep(rs.getString("IdResep"));
                d.setIdItem(rs.getString("IdItem"));
                d.setJumlah(rs.getInt("jumlah"));
                tableData.add(d);
                setDetailResepModel(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }        
 }
    public boolean saveall() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.dbKoneksi.setAutoCommit(false); // membuat semua perintah menjadi 1 transaksi
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                "delete from resep where idresep=?");
            con.preparedStatement.setString(1, getResepModel().getIdResep());
            con.preparedStatement.executeUpdate();           
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                "insert into resep (idresep,namaresep, successrate) values (?,?,?)");
            con.preparedStatement.setString(1, getResepModel().getIdResep());
            con.preparedStatement.setString(2, getResepModel().getNamaResep());
            con.preparedStatement.setInt(3, getResepModel().getSuccessRate());
            con.preparedStatement.executeUpdate();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                "delete from detailresep where idresep=?");
            con.preparedStatement.setString(1, getResepModel().getIdResep());
            con.preparedStatement.executeUpdate();           
            for(DetailResepModel sm:dt2.values()){
               con.preparedStatement = con.dbKoneksi.prepareStatement("insert into detailresep (idresep, iditem, jumlah) values (?,?,?)");
               con.preparedStatement.setString(1, sm.getIdResep());
               con.preparedStatement.setString(2, sm.getIdItem());
               con.preparedStatement.setInt(3, sm.getJumlah());
               con.preparedStatement.executeUpdate();
            }
            con.dbKoneksi.commit(); //semua perintah di transaksi dikerjakan
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public ResepModel getdata(String nomor) {
        ResepModel tmp=new ResepModel();
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(
                    "select * from resep where NoFaktur = '" +
                            nomor +"'");
            while (rs.next()) {
                tmp.setIdResep(rs.getString("idresep"));
                tmp.setNamaResep(rs.getString("namaresep"));
                tmp.setSuccessRate(rs.getInt("successrate"));
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;    
    }
    
    
    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from resep where IdResep = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into resep (IdResep, NamaResep, SuccessRate) values (?,?,?)");
            con.preparedStatement.setString(1, getResepModel().getIdResep());
            con.preparedStatement.setString(2, getResepModel().getNamaResep());
            con.preparedStatement.setInt(3, getResepModel().getSuccessRate());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from bahan where IdResep  = ? ");
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
                    "update resep set NamaResep= ?, SuccessRate= ?  where  IdResep= ? ; ");
            con.preparedStatement.setString(1, getResepModel().getNamaResep());
            con.preparedStatement.setInt(2, getResepModel().getSuccessRate());
            con.preparedStatement.setString(3, getResepModel().getIdResep());
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
