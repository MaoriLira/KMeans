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

    public FuzzyMeans(Double[][] objetos, Double[][] centros, int m) {
        this.objetos = objetos;
        this.centros = centros;
        this.m = m;
    }

    
    
    private Double[][] objetos;
    private Double[][] centros;
    private Double [][] nuevaMatrizObjetos;
    private Double [][] matrizGradosPertenencia;
    private Double costo;
    int m;
    
    private double calcularDistanciaEuclidiana(Double objetos[], Double[]centro){
        double distanciaEuclidiana = 0;
        for (int i = 0; i < objetos.length; i++) {
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
    private double gradosPertenencia(int i, int j){
        double grado = 0;
        double sumaDivisor = 0;
        
            for (int k = 0; k < nuevaMatrizObjetos[i].length; k++) {
                sumaDivisor += Math.pow(
                    (nuevaMatrizObjetos[i][j]/nuevaMatrizObjetos[i][k]), (2/(m-1)));
            }
            
        grado = 1/sumaDivisor;
        return grado;
    }
    public void matrizGrados(){
        matrizGradosPertenencia = new Double[objetos.length][centros.length];
        for (int i = 0; i < objetos.length; i++) {
            for (int j = 0; j < centros.length; j++) {
                matrizGradosPertenencia[i][j] = gradosPertenencia(i,j); 
            }
        }
    }
    public Double[][] mostarGrados(){
        System.out.println(Arrays.deepToString(matrizGradosPertenencia));
        return matrizGradosPertenencia;
    }
    private Double[] nuevoCentros(int centroide){
        Double centro[]=new Double[centros[0].length];
        double sumaDivisior = 0;
        
        
        for (int i = 0; i < matrizGradosPertenencia.length; i++) {
            sumaDivisior += Math.pow(matrizGradosPertenencia[i][centroide], m);
            
        }
        
         for (int i = 0; i < centro.length; i++) {
            double sumaDividendo = 0;
            for (int j = 0; j < matrizGradosPertenencia.length; j++) {
                sumaDividendo += ((Math.pow(matrizGradosPertenencia[j][centroide], m))* (objetos[j][i]));               
            }
             centro[i] = sumaDividendo/sumaDivisior;
             
        }
         return centro;
    }
    public void calcularCentros(){
        for (int i = 0; i < centros.length; i++) {
            centros[i] = nuevoCentros(i);
        }
    }
    public Double[][] mostrarNuevoCentros(){
        System.out.println(Arrays.deepToString(centros));
        return centros;
    }
    public Double costo(){
        Double costoTotal = 0.0;
        for (int i = 0; i < nuevaMatrizObjetos.length; i++) {
            Double suma=0.0;
            
            for (int j = 0; j < matrizGradosPertenencia[i].length; j++) {
                suma += Math.pow(nuevaMatrizObjetos[i][j], 2) + Math.pow(matrizGradosPertenencia[i][j], m);
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
