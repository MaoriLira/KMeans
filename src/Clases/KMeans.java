/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Arrays;

/**
 *
 * @author JuanJose
 */
public class KMeans {
    
    private Double[][] objetos;
    private Double[][] centros;
    private Double [][] nuevaMatrizObjetos;
    private Double [][] matrizPertenencia;
    private Double costo = 0.0;
    
    public KMeans(Double[][] objetos, Double[][] centros) {
        this.objetos = objetos;
        this.centros = centros;
    }
    
    private double calcularDistanciaEuclidiana(Double objetos[], Double[]centro){
        double distanciaEuclidiana = 0;
        for (int i = 0; i < objetos.length; i++) {
            //distanciaEuclidiana = Math.pow((objetos[i] - centro[i]) +(objetos[i+1]- centro[i+1]), 2);
            distanciaEuclidiana += Math.pow((objetos[i] - centro[i]), 2);
        }
        distanciaEuclidiana = Math.sqrt(distanciaEuclidiana);
        return distanciaEuclidiana;
    }
    
    public void matrizDistancia(){
        nuevaMatrizObjetos = new Double[objetos.length][centros.length];
        for (int i = 0; i < objetos.length; i++) {
            for (int j = 0; j < centros.length; j++) {
                nuevaMatrizObjetos[i][j] = calcularDistanciaEuclidiana(objetos[i], centros[j]); 
            }
        }
    }
    public Double[][] mostrarDistancia(){
        System.out.println(Arrays.deepToString(nuevaMatrizObjetos));
        return nuevaMatrizObjetos;
        
    }
    
    public void matrizPertenencia(){
        matrizPertenencia = new Double [objetos.length][centros.length];
        for (int i = 0; i < matrizPertenencia.length; i++) {
            for (int j = 0; j < matrizPertenencia[i].length; j++) {
                matrizPertenencia[i][j] = 0.0;
            }
            
        }
        
        for (int i = 0; i < nuevaMatrizObjetos.length; i++) {
            int variablePosicion  = 0;
            double variableAuxiliar = nuevaMatrizObjetos[i][0];
            for (int j = 1; j < nuevaMatrizObjetos[i].length; j++) {
                if(variableAuxiliar > nuevaMatrizObjetos[i][j]){
                    variableAuxiliar = nuevaMatrizObjetos[i][j];
                    variablePosicion = j;                
                }
            }
            matrizPertenencia[i][variablePosicion] = 1.0; 
        }
    }
    public void pertenencia(Double [][] matrizPertenencia){
        for (int i = 0; i < matrizPertenencia[0].length; i++) {
            for (int j = 0; j < matrizPertenencia.length; j++) {
                System.out.print(matrizPertenencia[j][i] + " ");
            }
        }
    }
    public Double[][] mostrarPertenencia(){
        System.out.println(Arrays.deepToString(matrizPertenencia));
        return matrizPertenencia;
    }
    private Double[] nuevoCentro(int centroide){
        Double centro[]=new Double[centros[0].length];
        System.out.println(centroide);
        
        for (int i = 0; i < centro.length; i++) {
            Double suma=0.0;
            Double divisor=0.0;
            for (int j = 0; j < matrizPertenencia.length; j++) {
                if(matrizPertenencia[j][centroide]==1.0)
                {
                    suma+=objetos[j][i];
                    divisor++;
                }
            }
            centro[i]=suma/divisor;
        }
        return centro;
    }
    public void calcularCentros(){
        for (int i = 0; i < centros.length; i++) {
            centros[i] = nuevoCentro(i);
        }
    }
    public Double[][] mostrarCentros(){
        System.out.println(Arrays.deepToString(centros));
        return centros;
    }
    public Double costo(){
        Double costoTotal = 0.0;
        for (int i = 0; i < matrizPertenencia.length; i++) {
            Double suma=0.0;
            
            for (int j = 0; j < matrizPertenencia[0].length; j++) {
                if(matrizPertenencia[i][j]==1.0)
                {
                    suma+=nuevaMatrizObjetos[i][j];
                    
                }
            }
            costoTotal+=suma;
            
        }
        return costoTotal;
    }
    public Double mostrarCosto(){
        costo = costo();
        System.out.println(costo);
        return costo;
    }
}
