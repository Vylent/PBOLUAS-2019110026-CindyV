/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts2019110026cindyv;

import java.sql.Date;

/**
 *
 * @author 2019110026CindyV
 */
public class CraftModel {
    String IdCraft,IdResep;
    Date TanggalCraft;
    Integer Jumlah;

    public String getIdCraft() {
        return IdCraft;
    }

    public void setIdCraft(String IdCraft) {
        this.IdCraft = IdCraft;
    }

    public String getIdResep() {
        return IdResep;
    }

    public void setIdResep(String IdResep) {
        this.IdResep = IdResep;
    }

    public Date getTanggalCraft() {
        return TanggalCraft;
    }

    public void setTanggalCraft(Date TanggalCraft) {
        this.TanggalCraft = TanggalCraft;
    }

    public Integer getJumlah() {
        return Jumlah;
    }

    public void setJumlah(Integer Jumlah) {
        this.Jumlah = Jumlah;
    }
}
