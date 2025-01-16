package com.funfit.controller;

import com.funfit.dao.BatchDao;
import com.funfit.model.Batch;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/batch")
public class BatchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BatchDao batchDao;

    @Override
    public void init() {
        batchDao = new BatchDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            if ("new".equals(action)) {
                showNewForm(request, response);
            } else if ("insert".equals(action)) {
                insertBatch(request, response);
            } else if ("delete".equals(action)) {
                deleteBatch(request, response);
            } else if ("edit".equals(action)) {
                showEditForm(request, response);
            } else if ("update".equals(action)) {
                updateBatch(request, response);
            } else {
                listBatches(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBatches(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Batch> listBatch = batchDao.getAllBatches();
        request.setAttribute("listBatch", listBatch);
        request.getRequestDispatcher("batch-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("add-batch.html").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Batch existingBatch = batchDao.getBatchById(id);
        request.setAttribute("batch", existingBatch);
        request.getRequestDispatcher("update-batch.html").forward(request, response);
    }

    private void insertBatch(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String batchName = request.getParameter("batchName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String dayOfWeek = request.getParameter("dayOfWeek");

        Batch newBatch = new Batch(0, batchName, startTime, endTime, dayOfWeek);
        batchDao.addBatch(newBatch);
        response.sendRedirect("batch?action=list");
    }

    private void updateBatch(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String batchName = request.getParameter("batchName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String dayOfWeek = request.getParameter("dayOfWeek");

        Batch batch = new Batch(id, batchName, startTime, endTime, dayOfWeek);
        batchDao.updateBatch(batch);
        response.sendRedirect("batch?action=list");
    }

    private void deleteBatch(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        batchDao.deleteBatch(id);
        response.sendRedirect("batch?action=list");
    }
}