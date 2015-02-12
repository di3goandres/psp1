/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.controller;

import edu.uniandes.ecos.model.ParNumber;
import edu.uniandes.ecos.model.Regresion;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author COCO
 */
public class GestorRegresion {

    public String GestorRegresion(List<ParNumber> datosCalculos, double proxy) {

        Regresion re = new Regresion(datosCalculos, proxy);
        return re.gerResult();

    }

    public static void showHome(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<h1>PSP1 Programa que calcula Regresion lineal y "
                + "Coeficiente de variacion</h1>");
        pw.println("<p>ECOS 2015 <br>Diego Andres Montealegre Garcia</p>");
        pw.write("<html>");
        pw.println("Ingrese la cadena de datos de prueba separando x & y por comas");
        pw.println("<br>Separar las parejas con el pipe '|' ");
        pw.println("<br>los decimales con punto (10.9), no con coma<br>");
        pw.write("<form action=\"calc\" method=\"post\">"
                + "<table>"
                + "<tr><td>Cadena de valores</td><td><textarea rows=\"4\" cols=\"100\" name=\"calc\"></textarea></td></tr>"
                + "<tr><td>valor del proxy</td><td><input type=\"text\" name=\"proxy\"></td></tr>"
                + "</table> <input type=\"submit\" value=\"realizar calculos\">"
                + "</form> ");
        pw.println("<h2></h2>");
        pw.write("</html>");

    }

    public static void showResults(HttpServletRequest req, HttpServletResponse resp,
            String resultado)
            throws ServletException, IOException {

        resp.getWriter().println("<h2>Resultados</h2>");
        resp.getWriter().println(resultado);
        resp.getWriter().println("<hr>");
        resp.getWriter().println("<h2>Test Realizados</h2>");
        resp.getWriter().println("<b>Lista test1: <br></b>"
                + "130,186|650,699|99,132|150,272|128,291|302,331|95,199|945,1890|368,788|961,1601<br>");
        resp.getWriter().println("<b>Lista test2: <br></b>"
                + "130,15.0|650,69.9|99,6.5|150,22.4|128,28.4|302,65.9|95,19.4|945,198.7|368,38.8|961,138.2<br>");
        resp.getWriter().println("<b>Lista test3: <br></b>"
                + "163,186|765,699|141,132|166,272|137,291|355,331|136,199|1206,1890|433,788|1130,1601<br>");
        resp.getWriter().println("<b>Lista test4: <br></b>"
                + "163,15.0|765,69.9|141,6.5|166,22.4|137,28.4|355,65.9|136,19.4|1206,198.7|433,38.8|1130,138.2<br>");

    }

    public static void error(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("Error!!!");
    }

}
