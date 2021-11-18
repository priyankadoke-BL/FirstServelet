package com.bridgelabz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name="user", value="PriyankaDoke"),
                @WebInitParam(name="password",value = "Principal@1234")
        }
)
public class LoginServlet extends HttpServlet {
    private static final String FIRST_NAME_PATTERN="^[A-Z]{1}[a-zA-Z]{2}[a-zA-z0-9]*";
    private static final String passwordRegex="^(?=.*[@#$%^&+=])(?=.*[0-9])(?=.*[A-Z]).{8,}$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        boolean validateFirstName = validateFirstName(user);
        boolean checkFirstName = checkFirstName(request, response, validateFirstName);
        String pwd=request.getParameter("pwd");
        String userID=getServletConfig().getInitParameter("user");
        String password=getServletConfig().getInitParameter("password");

        if (checkFirstName==true) {
            if (userID.equals(user) && password.equals(pwd)) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("LoginSuccessful.jsp").forward(request, response);
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either user name or password is wrong.</font>");
                rd.include(request, response);
            }
        }
    }

    private boolean checkFirstName(HttpServletRequest request, HttpServletResponse response, boolean validateFirstName) throws IOException, ServletException {
        if (validateFirstName==false){
            RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out=response.getWriter();
            out.println("<font color=red>Incorrect username</font>");
            rd.include(request,response);
            return false;
        }
        return true;
    }

    private boolean validateFirstName(String firstName) {
        Pattern check= Pattern.compile(FIRST_NAME_PATTERN);
        boolean value=check.matcher(firstName).matches();
        return value;
    }
    public boolean validatePassword(String password) {
        Pattern check = Pattern.compile(passwordRegex);
        boolean value = check.matcher(password).matches();
        return value;
    }
}
