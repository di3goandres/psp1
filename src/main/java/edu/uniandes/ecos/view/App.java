package edu.uniandes.ecos.view;

import edu.uniandes.ecos.controller.GestorRegresion;
import edu.uniandes.ecos.model.ParNumber;
import edu.uniandes.ecos.model.Regresion;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

/**
 * Hello world!
 *
 */
public class App extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        GestorRegresion.showHome(req, resp);
        GestorRegresion.showResults(req, resp, "");

    }

    public void consoleInput(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String calc = req.getParameter("calc");
        String proxy = req.getParameter("proxy");
        GestorRegresion gestorRegresion = new GestorRegresion();
        String resultado = gestorRegresion.GestorRegresion((LinkedList) splitNumbers(calc), Double.valueOf(proxy));
        GestorRegresion.showResults(req, resp, resultado);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new App()), "/*");
        server.start();
        server.join();
    }

  

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            GestorRegresion.showHome(req, resp);
            consoleInput(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<ParNumber> splitNumbers(String parametros) {

        List<ParNumber> listaDeNumeros = new LinkedList<ParNumber>();

        String[] pares = parametros.split("\\|");

        for (String parXY : pares) {
            String[] xy = parXY.split(",");
            listaDeNumeros.add(new ParNumber(Double.valueOf(xy[0]), Double.valueOf(xy[1])));
        }

        return listaDeNumeros;
    }
}
