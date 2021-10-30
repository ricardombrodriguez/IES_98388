package com.tomcat_21.app;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(name = "App", urlPatterns = {"/welcome"})
public class App extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException 
    {
         
        String user = request.getParameter("user");
        String color = request.getParameter("color");            
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        if (user == null && color == null) {
            throw new NullPointerException("Invalid username and color");
        }
        else if (color == null) {
            throw new NullPointerException("Invalid color");
        }
        else if (user == null)   {
            throw new NullPointerException("Invalid username");
        }
        else{
            try {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Welcome</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hello <b>" + user + "!</b></h1>");
                out.println("<h1>Fav color: <b>" + color + "!</b></h1>");
                out.println("<h3>Look at this great wallpaper:</h3>");
                out.println("<img src=https://www.enjpg.com/img/2020/desktop-backgrounds-1.png>");
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
        }
    }
}