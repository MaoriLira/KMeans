/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Arrays;

/**
 *
 * @author mauricio
 */
public class FuzzyMeans {

    public FuzzyMeans(Double[][] objetosFuzzy, Double[][] centrosFuzzy, int m) {
        this.objetosFuzzy = objetosFuzzy;
        this.centrosFuzzy = centrosFuzzy;
        this.m = m;
    }

    
    
    private Double[][] objetosFuzzy;
    private Double[][] centrosFuzzy;
    private Double [][] nuevaMatrizObjetosFuzzy;
    private Double [][] matrizGradosPertenenciaFuzzy;
    private Double costoFuzzy;
    int m;
    
    private double calcularDistanciaEuclidiana(Double objetosFuzzy[], Double[]centroFuzzy){
        double distanciaEuclidiana = 0;
        for (int i = 0; i < objetosFuzzy.length; i++) {
            distanciaEuclidiana += Math.pow((objetosFuzzy[i] - centroFuzzy[i]), 2);
        }
        distanciaEuclidiana = Math.sqrt(distanciaEuclidiana);
        return distanciaEuclidiana;
    }
    
    public void matrizDistancia(){
        nuevaMatrizObjetosFuzzy = new Double[objetosFuzzy.length][centrosFuzzy.length];
        for (int i = 0; i < objetosFuzzy.length; i++) {
            for (int j = 0; j < centrosFuzzy.length; j++) {
                nuevaMatrizObjetosFuzzy[i][j] = calcularDistanciaEuclidiana(objetosFuzzy[i], centrosFuzzy[j]); 
            }
        }
    }
    public Double[][] mostrarDistancia(){
        System.out.println(Arrays.deepToString(nuevaMatrizObjetosFuzzy)); 
        return nuevaMatrizObjetosFuzzy;
    }
    private double gradosPertenencia(int i, int j){
        double grado = 0;
        double sumaDivisor = 0;
        
            for (int k = 0; k < nuevaMatrizObjetosFuzzy[i].length; k++) {
                sumaDivisor += Math.pow(
                    (nuevaMatrizObjetosFuzzy[i][j]/nuevaMatrizObjetosFuzzy[i][k]), (2/(m-1)));
            }
            
        grado = 1/sumaDivisor;
        return grado;
    }
    public void matrizGrados(){
        matrizGradosPertenenciaFuzzy = new Double[objetosFuzzy.length][centrosFuzzy.length];
        for (int i = 0; i < objetosFuzzy.length; i++) {
            for (int j = 0; j < centrosFuzzy.length; j++) {
                matrizGradosPertenenciaFuzzy[i][j] = gradosPertenencia(i,j); 
            }
        }
    }
    public Double[][] mostarGrados(){
        System.out.println(Arrays.deepToString(matrizGradosPertenenciaFuzzy));
        return matrizGradosPertenenciaFuzzy;
    }
    private Double[] nuevoCentros(int centroide){
        Double centro[]=new Double[centrosFuzzy[0].length];
        double sumaDivisior = 0;
        
        
        for (int i = 0; i < matrizGradosPertenenciaFuzzy.length; i++) {
            sumaDivisior += Math.pow(matrizGradosPertenenciaFuzzy[i][centroide], m);
            
        }
        
         for (int i = 0; i < centro.length; i++) {
            double sumaDividendo = 0;
            for (int j = 0; j < matrizGradosPertenenciaFuzzy.length; j++) {
                sumaDividendo += ((Math.pow(matrizGradosPertenenciaFuzzy[j][centroide], m))* (objetosFuzzy[j][i]));               
            }
             centro[i] = sumaDividendo/sumaDivisior;
             
        }
         return centro;
    }
    public void calcularCentros(){
        for (int i = 0; i < centrosFuzzy.length; i++) {
            centrosFuzzy[i] = nuevoCentros(i);
        }
    }
    public Double[][] mostrarNuevoCentros(){
        System.out.println(Arrays.deepToString(centrosFuzzy));
        return centrosFuzzy;
    }
    public Double costo(){
        Double costoTotal = 0.0;
        for (int i = 0; i < nuevaMatrizObjetosFuzzy.length; i++) {
            Double suma=0.0;
            
            for (int j = 0; j < matrizGradosPertenenciaFuzzy[i].length; j++) {
                suma += Math.pow(nuevaMatrizObjetosFuzzy[i][j], 2) + Math.pow(matrizGradosPertenenciaFuzzy[i][j], m);
            }
            costoTotal+=suma;
            
        }
        return costoTotal;
    }
    public Double mostrarCosto(){
        costoFuzzy = costo();
        System.out.println(costoFuzzy);
        return costoFuzzy;
    }
}
