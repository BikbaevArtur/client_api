package ru.bikbaev.client_v2.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import ru.bikbaev.client_v2.dto.DTOIp;
import ru.bikbaev.client_v2.mapper.JsonMapper;

import ru.bikbaev.client_v2.repository.IpRepository;
import ru.bikbaev.client_v2.repository.JdbcRepositoryByClient;


import java.io.IOException;


@WebServlet(name = "ip", value = "/ip")
public class IpController extends HttpServlet {

    private static final JdbcRepositoryByClient jdbcRepositoryByClient = new IpRepository();
    private static final Controller controller = new Controller();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.doGetController(req, resp, jdbcRepositoryByClient);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        DTOIp dto = gson.fromJson(String.valueOf(JsonMapper.doPost(req)), DTOIp.class);

        jdbcRepositoryByClient.save(dto);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.doDelete(req,resp, jdbcRepositoryByClient);
    }
}
