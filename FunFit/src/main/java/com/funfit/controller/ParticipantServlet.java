package com.funfit.controller;

import com.funfit.dao.ParticipantDao;
import com.funfit.model.Participant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/participant")
public class ParticipantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ParticipantDao participantDao;

    @Override
    public void init() {
        participantDao = new ParticipantDao();
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
                insertParticipant(request, response);
            } else if ("delete".equals(action)) {
                deleteParticipant(request, response);
            } else if ("edit".equals(action)) {
                showEditForm(request, response);
            } else if ("update".equals(action)) {
                updateParticipant(request, response);
            } else if ("listByBatch".equals(action)) {
                listParticipantsByBatch(request, response);
            } else {
                listParticipants(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listParticipants(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Participant> listParticipant = participantDao.getAllParticipants();
        request.setAttribute("listParticipant", listParticipant);
        request.getRequestDispatcher("participant-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("add-participant.html").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Participant existingParticipant = participantDao.getParticipantById(id);
        request.setAttribute("participant", existingParticipant);
        request.getRequestDispatcher("update-participant.html").forward(request, response);
    }

    private void insertParticipant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        int batchId = Integer.parseInt(request.getParameter("batchId"));

        Participant newParticipant = new Participant(0, name, email, phoneNumber, batchId);
        participantDao.addParticipant(newParticipant);
        response.sendRedirect("participant?action=list");
    }

    private void updateParticipant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        int batchId = Integer.parseInt(request.getParameter("batchId"));

        Participant participant = new Participant(id, name, email, phoneNumber, batchId);
        participantDao.updateParticipant(participant);
        response.sendRedirect("participant?action=list");
    }

    private void deleteParticipant(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        participantDao.deleteParticipant(id);
        response.sendRedirect("participant?action=list");
    }
    
    private void listParticipantsByBatch(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int batchId = Integer.parseInt(request.getParameter("batchId"));
        List<Participant> participantsInBatch = participantDao.getParticipantsByBatchId(batchId);

        if (participantsInBatch == null) {
            participantsInBatch = new ArrayList<>();
        }

        request.setAttribute("participantsInBatch", participantsInBatch);
        request.getRequestDispatcher("participants-in-batch.jsp").forward(request, response);
    }
}