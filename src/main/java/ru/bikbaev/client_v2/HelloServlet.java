package ru.bikbaev.client_v2;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.bikbaev.client_v2.dto.DTO;
import ru.bikbaev.client_v2.dto.DTOCompany;
import ru.bikbaev.client_v2.repository.CompanyRepository;
import ru.bikbaev.client_v2.repository.JdbcRepositoryByClient;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {

        message = "dtoCompany.getName()";

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}