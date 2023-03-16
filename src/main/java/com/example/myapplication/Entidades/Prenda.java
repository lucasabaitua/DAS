package com.example.myapplication.Entidades;

import android.graphics.Color;
import android.media.Image;

import java.io.Serializable;

public class Prenda implements Serializable {
    private int img;
    private int colFondo;
    private String tituloPrenda;
    private String descripcion;
    private String fechaColgado;

    public Prenda(String pTit, String pDesc,String pFechaC, int pFoto){
        this.colFondo=0;
        this.tituloPrenda=pTit;
        this.descripcion=pDesc;
        this.fechaColgado=pFechaC;
        this.img=pFoto;
    }

    public void setTituloPrenda(String pTitulo){
        this.tituloPrenda=pTitulo;
    }

    public void setDescripcion(String pDesc){
        this.descripcion=pDesc;
    }

    public void setColFondo(int pCol){
        if (pCol!=0){
            this.colFondo=pCol-1;
        }
    }
    public void setFoto(int pFoto){
        this.img=pFoto;
    }

    public String getTituloPrenda(){
        return this.tituloPrenda;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public String getFechaColgado(){
        return this.fechaColgado;
    }

    public int getFoto(){
        return this.img;
    }
}
