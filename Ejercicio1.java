package Metodo_Burbuja;

import javax.swing.*;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int arreglo[],nElementos,aux;

        nElementos = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de elementos del arreglo"));

        arreglo = new int[nElementos];

        for( int i=0; i<nElementos; i++){
            System.out.print((i+1)+". Digite un numero: ");
            arreglo[i] = entrada.nextInt();
        }
// Metodo burbuja
        for( int i=0; i<(nElementos-1); i++){
            for(int j=0; j<(nElementos-1); j++){
                if(arreglo[j] > arreglo[j+1]){
                    aux = arreglo[j];
                    arreglo[j] = arreglo[j+1];
                    arreglo[j+1] = aux;

                }

            }
//Mostrando arreglo ordenado en forma creciente
        }
        System.out.println("Arreglo ordenado en forma creciente");
        for( int i=0;i<nElementos; i++){
            System.out.print(arreglo[i]+" - ");
        }
//Mostrando arreglo ordenado en forma decreciente
        System.out.println("Arreglo ordenado en forma decreciente");
        for( int i=(nElementos-1); i>=0; i--){
            System.out.print(arreglo[i]+" - ");
        }
    }
}
