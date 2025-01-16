package com.funfit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.funfit.model.Participant;
import com.funfit.util.DBUtil;

public class ParticipantDao {

	public void addParticipant(Participant participant) throws SQLException {
		String sql = "insert into participants (name, email, phone_number, batch_id) values (?,?,?,?)";
		try (Connection con = DBUtil.getConnection(); PreparedStatement prepState = con.prepareStatement(sql)) {

			prepState.setString(1, participant.getName());
			prepState.setString(2, participant.getEmail());
			prepState.setString(3, participant.getPhoneNumber());
			prepState.setInt(4, participant.getBatchId());
			System.out.println(prepState);
			prepState.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when adding participant");
		}

	}

	public void updateParticipant(Participant participant) throws SQLException {
		String sql = "update participants set name=?, email=?, phone_number=?, batch_id=?) where participant_id =?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement prepState = con.prepareStatement(sql)) {

			prepState.setString(1, participant.getName());
			prepState.setString(2, participant.getEmail());
			prepState.setString(3, participant.getPhoneNumber());
			prepState.setInt(4, participant.getBatchId());
			prepState.setInt(5, participant.getParticipantId());
			prepState.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when updating participant");
		}

	}

	public void deleteParticipant(int participantId) throws SQLException {
		String sql = "delete from participants where participant_id =?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement prepState = con.prepareStatement(sql)) {

			prepState.setInt(1, participantId);

			prepState.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when deleting participant");
		}

	}

	public Participant getParticipantById(int participantId) throws SQLException {
		String sql = "select * from participants where participant_id =?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement prepState = con.prepareStatement(sql)) {

			prepState.setInt(1, participantId);
			try (ResultSet rs = prepState.executeQuery()) {
				if (rs.next()) {
					return new Participant(rs.getInt("participant_id"), rs.getString("name"), rs.getString("email"),
							rs.getString("phone_number"), rs.getInt("batch_id"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error when getting participant by id");
			}
		}
		return null;
	}

	public List<Participant> getAllParticipants() throws SQLException {
		List<Participant> participants = new ArrayList<>();
		String sql = "select * from participants";
		try (Connection con = DBUtil.getConnection();
				PreparedStatement prepState = con.prepareStatement(sql);
				ResultSet rs = prepState.executeQuery()) {

			while (rs.next()) {
				participants.add(new Participant(rs.getInt("participant_id"), rs.getString("name"),
						rs.getString("email"), rs.getString("phone_number"), rs.getInt("batch_id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when getting all participants");
		}
		return participants;
	}

	public List<Participant> getParticipantsByBatchId(int batchId) throws SQLException {
		List<Participant> participants = new ArrayList<>();
		String sql = "SELECT * FROM participants WHERE batch_id = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement prepState = con.prepareStatement(sql)) {

			prepState.setInt(1, batchId);
			try (ResultSet rs = prepState.executeQuery()) {
				while (rs.next()) {
					participants.add(new Participant(rs.getInt("participant_id"), rs.getString("name"),
							rs.getString("email"), rs.getString("phone_number"), rs.getInt("batch_id")));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when getting participants by batch id");
		}
		return participants;
	}

}
