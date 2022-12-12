/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts2019110026cindyv;

/**
 *
 * @author 2019110026CindyV
 */
public class ResepModel {
    private String IdResep,NamaResep;
    private Integer SuccessRate;
    
     public ResepModel() {
        
      }
    
    public ResepModel(String IdResep, String NamaResep, Integer SuccessRate) {
        this.IdResep=IdResep; 
        this.NamaResep=NamaResep;
        this.SuccessRate=SuccessRate;  
      }

    public String getIdResep() {
        return IdResep;
    }

    public void setIdResep(String IdResep) {
        this.IdResep = IdResep;
    }

    public String getNamaResep() {
        return NamaResep;
    }

    public void setNamaResep(String NamaResep) {
        this.NamaResep = NamaResep;
    }

    public Integer getSuccessRate() {
        return SuccessRate;
    }

    public void setSuccessRate(Integer SuccessRate) {
        this.SuccessRate = SuccessRate;
    }

   
}
