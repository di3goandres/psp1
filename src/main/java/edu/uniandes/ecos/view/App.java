package edu.uniandes.ecos.view;

import edu.uniandes.ecos.controller.GestorRegresion;
import edu.uniandes.ecos.model.ParNumber;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getRequestURI().endsWith("/db")) {
            showDatabase(req, resp);
        } else {
            showHome(req, resp);
        }
    }

    private void showDatabase(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB: " + rs.getTimestamp("tick") + "\n";
            }

            resp.getWriter().print(out);
        } catch (Exception e) {
            resp.getWriter().print("There was an error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    private Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        int port = dbUri.getPort();

        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
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

    private void showHome(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<h2>PSP1 Program!</h2>");
        pw.println("Ingrese la cadena de datos de prueba separando x & y por comas");
        pw.println("Separar las parejas con el pipe '|' ");
        pw.println("los decimales con punto (10.9), no con coma");
        
        pw.println("<h3>Cadenas de ejemplo:</h3>");
        pw.println("<h3>Test 1: </h3>");
        pw.println("130,186|650,699|99,132|150,272|128,291|302,331|95,199|945,1890|368,788|961,1601");

        pw.println("<h3>Test 2: </h3>");
        pw.println("130,15.0|650,69.9|99,6.5|150,22.4|128,28.4|302,65.9|95,19.4|945,198.7|368,38.8|961,138.2");

        pw.println("<h3>Test 3: </h3>");
        pw.println("163,186|765,699|141,132|166,272|137,291|355,331|136,199|1206,1890|433,788|1130,1601");

        pw.println("<h3>Test 4: </h3>");
        pw.println("163,15.0|765,69.9|141,6.5|166,22.4|137,28.4|355,65.9|136,19.4|1206,198.7|433,38.8|1130,138.2");
        pw.println("<br>");
        pw.write("<form action=\"calc\" method=\"post\"> \n"
                + "    Cadena de valores: <input type=\"text\" name=\"calc\">\n"
                + "    Valor del proxy: <input type=\"text\" name=\"proxy\">\n"
                + "    <input type=\"submit\" value=\"Calc\">\n"
                + "</form> ");
        pw.write("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String calc = req.getParameter("calc");
        String proxy = req.getParameter("proxy");

        GestorRegresion gestorRegresion = new GestorRegresion();
        String resultado = gestorRegresion.GestorRegresion((LinkedList) splitNumbers(calc), Double.valueOf(proxy));
        resp.getWriter().print(resultado);
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
