package com.jetty.app;
 
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class App {

    public static void main(String[] args) {

        Server server = new Server(8680);
        try {

            ServletHandler servletHandler = new ServletHandler();
            server.setHandler(servletHandler);

            servletHandler.addServletWithMapping(HelloServlet.class, "/welcome");

            server.start();
            server.dumpStdErr();
            server.join();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static class HelloServlet extends HttpServlet 
    {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {

            String user = request.getParameter("user");
            String color = request.getParameter("color");            
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            
            if (user == null && color == null) {
                throw new NullPointerException("Invalid username and color");
            }
            else if (color == null) {
                throw new NullPointerException("Invalid color");
            }
            else if (user == null)   {
                throw new NullPointerException("Invalid username");
            }
            else {
                response.getWriter().println("<html>");
                response.getWriter().println("<head>");
                response.getWriter().println("<title>Welcome</title>");
                response.getWriter().println("</head>");
                response.getWriter().println("<body>");
                response.getWriter().println("<h1>Hello <b>" + user + "!</b></h1>");
                response.getWriter().println("<h1>Fav color: <b>" + color + "!</b></h1>");
                response.getWriter().println("<h3>Look at this great wallpaper:</h3>");
                response.getWriter().println("<img src=https://www.enjpg.com/img/2020/desktop-backgrounds-1.png>");
                response.getWriter().println("</body>");
                response.getWriter().println("</html>");
            }
        } 
    }
}