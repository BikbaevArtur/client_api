package ru.bikbaev.client_v2.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import ru.bikbaev.client_v2.dto.DTORetail;
import ru.bikbaev.client_v2.mapper.JsonMapper;

import ru.bikbaev.client_v2.repository.JdbcRepositoryByClient;
import ru.bikbaev.client_v2.repository.RetailRepository;


import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "retail", value = "/retail")
public class RetailController extends HttpServlet {

    private static final JdbcRepositoryByClient jdbcRepositoryByClient = new RetailRepository();
    private static final Controller controller = new Controller();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.doGetController(req,resp,jdbcRepositoryByClient);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        DTORetail dto = gson.fromJson(String.valueOf(JsonMapper.doPost(req)),DTORetail.class);

        jdbcRepositoryByClient.save(dto);

    }





    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.doDelete(req,jdbcRepositoryByClient);
    }
}
