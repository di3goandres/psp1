package edu.uniandes.ecos.view;

import edu.uniandes.ecos.model.DesviacionEstandar;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        try {
            DesviacionEstandar desviacionEstandar = new DesviacionEstandar();
            int numeroIngresos = 0;
            List<Double> listaNumeros = new LinkedList<Double>();
            Scanner entradaingresoDatos = new Scanner(System.in);
            System.out.println("Ingrese la cantidad de datos que seran ingresados: ");

            String datosIngresados = "";
            double valorIngresado = 0;
            int i = 1;
            numeroIngresos = entradaingresoDatos.nextInt();

            while (numeroIngresos > 0) {
                System.out.print("Numero # " + i + ": ");
                Scanner entradaDatos = new Scanner(System.in);
                datosIngresados = entradaDatos.nextLine();
                valorIngresado = Double.parseDouble(datosIngresados);
                listaNumeros.add(valorIngresado);
                i++;
                numeroIngresos--;

            }

            System.out.println("Listado de números:");
            System.out.println(listaNumeros);
            System.out.println("Media Aritmética");
//            System.out.println(desviacionEstandar.MediaAritmetica());
//            System.out.println("Desviacion Estandar");
//            System.out.println(desviacionEstandar.DesviacionStandard());
        } catch (NumberFormatException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
