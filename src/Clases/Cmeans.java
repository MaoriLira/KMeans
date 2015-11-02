/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author JuanJose
 */
public class Cmeans {
    
    public static void main(String[] args) {

        float [][] objetos = {{1, 2}
                ,{2, 3} 
                ,{4, 5} 
                ,{5, 5} 
                ,{5, 6}};
        float[][] centros = {{3,6}
                ,{1,1}};

        float[][] distanciasE = new float[centros.length][objetos.length];
        float[][] matrizPertenencia = new float[centros.length][objetos.length];
        float costo=0;
                

        Cmeans cm = new Cmeans();
        distanciasE = cm.pointCenterDistance(objetos, centros);
        System.out.println("Hola");
        for(int i = 0; i < distanciasE.length; i++){
            for(int j = 0; j < distanciasE[0].length; j++){
                System.out.print(distanciasE[i][j]+" ");
            }
            System.out.println("");
        }

        matrizPertenencia = cm.memershipMatrix(distanciasE);

        costo = cm.costo(matrizPertenencia,distanciasE);

        System.out.println("Costo "+costo);

        centros = cm.nuevosCentros(matrizPertenencia,objetos);
        for (int i = 0; i < centros.length; i++){
            for (int j = 0; j < centros[0].length;j++){
                System.out.print(centros[i][j]+" ");
            }
            System.out.println();
        }

    }

    public float euclidianDistance(float[] p1, float[] p2){
        float dE = 0;
        float aux = 0;
        for(int i = 0; i < p1.length ; i++){
            aux = p1[i] - p2[i];
            aux = (float) Math.pow(aux,2);
            dE += aux;
        }
        System.out.print("de: "+Math.sqrt(dE));
        return (float) Math.sqrt(dE);
    }

    public float[][] pointCenterDistance(float [][] obj, float[][] ctr){
        float[][] distanciasE = new float[ctr.length][obj.length];
        System.out.println("objetos:"+obj.length);
        System.out.println("objetos:"+ctr.length);
        for(int i = 0; i < ctr.length; i++){
            for(int j = 0; j < obj.length; j++){
                distanciasE[i][j] = euclidianDistance(obj[j], ctr[i]);
            }
            System.out.println(" ");
        }
        return distanciasE;
    }
    
    public float[][] memershipMatrix(float[][] membMat){
        float [][]mbM = new float[membMat.length][membMat[0].length];
        float[] menores = new float[membMat[0].length];
        for(int i = 0; i <membMat[0].length; i++){
            float[] aux = new float[membMat.length];
            for (int j = 0; j < membMat.length; j++){
                aux[j] = membMat[j][i];
            }
            menores[i]=menor(aux);
        }
        for(int i = 0; i < menores.length; i++){
            System.out.println("menor "+menores[i]);
        }
        for(int i = 0; i <membMat.length; i++){
            for (int j = 0; j < membMat[0].length; j++){
                if(menores[j] == membMat[i][j]){
                    mbM[i][j] = 1;
                }else{
                    mbM[i][j] = 0;
                }
            }
        }
        System.out.println();
        for(int i =0; i <mbM.length; i++){
            for(int j=0; j<mbM[0].length; j++){
                System.out.print(mbM[i][j] + " ");
            }
            System.out.println();
        }
        return mbM;

    }

    public float menor(float[] col){
        float menor = col[0];
        for(int i =1; i<col.length; i++){
            if(col[i]<menor){
                menor = col[i];
            }
        }
        return menor;
    }

    public float costo(float[][] matPert, float[][] distancias){
        float cost=0;
        float[] costo = new float[matPert.length];
        for(int i = 0; i < distancias.length; i++){
            for(int j = 0; j < distancias[0].length; j++){
                if(matPert[i][j]==1){
                    costo[i] += distancias[i][j];
                }
            }
        }
        for (int i = 0; i < costo.length; i++){
            System.out.println("costo "+costo[i]);
            cost +=costo[i];
        }
        return cost;
    }

    public float[][] nuevosCentros(float[][] matPer, float[][] objetos){

        float[][] nCentroides = new float[matPer.length][objetos[0].length];
        for(int i = 0; i < matPer.length; i++){
            int num=0;
            for(int j = 0; j < matPer[0].length; j++){
                if(matPer[i][j]==1){
                    num++;
                }
            }
            System.out.println("numero de elementos " +num);
            float[][] nCentro = new float[num][objetos[0].length];
            int l=0;
            for (int j = 0; j < matPer[0].length; j++) {
                    if (matPer[i][j] == 1) {
                        //System.out.print(l);
                        //System.out.print(j);
                        //System.out.println();
                        nCentro[l]=objetos[j];
                        l++;
                    }
            }

            for(int c=0;c<nCentro.length;c++){
                for(int d=0;d<nCentro[0].length;d++){
                    System.out.print(nCentro[c][d] + " ");
                }
                System.out.println();
            }

            nCentroides[i] = valorCentro(nCentro);
            //valorCentro(nCentro);
        }
        return nCentroides;
    }

    public float[] valorCentro(float[][] obj){
        float[] vCentro = new float[obj[0].length];
        for (int i =0; i<obj[0].length; i++){
            for (int j =0; j<obj.length; j++){
              vCentro[i] += obj[j][i];
            }
        }
        for (int i =0;i <vCentro.length; i++){
            vCentro[i] = vCentro[i]/obj.length;
        }
        return vCentro;
    }

}
