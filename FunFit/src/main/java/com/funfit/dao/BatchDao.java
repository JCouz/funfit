package com.funfit.dao;

import com.funfit.model.Batch;
import com.funfit.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchDao {

	public void addBatch(Batch batch) throws SQLException {
		String sql = "insert into batches (batch_name, start_time, end_time, day_of_week) values (?, ?, ?, ?)";
		try (Connection con = DBUtil.getConnection(); PreparedStatement prepState = con.prepareStatement(sql)) {

			prepState.setString(1, batch.getBatchName());
			prepState.setString(2, batch.getStartTime());
			prepState.setString(3, batch.getEndTime());
			prepState.setString(4, batch.getDayOfWeek());
			prepState.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when adding batch");
		}
	}

	public void updateBatch(Batch batch) throws SQLException {
		String sql = "update batches set batch_name =?, start_time =?, end_time =?, day_of_week =? where batch_id =?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement prepState = con.prepareStatement(sql)) {

			prepState.setString(1, batch.getBatchName());
			prepState.setString(2, batch.getStartTime());
			prepState.setString(3, batch.getEndTime());
			prepState.setString(4, batch.getDayOfWeek());
			prepState.setInt(5, batch.getBatchId());
			prepState.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when updating batch");
		}
	}

	public void deleteBatch(int batchId) throws SQLException {
		String sql = "delete from batches where batch_id =?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement prepState = con.prepareStatement(sql)) {

			prepState.setInt(1, batchId);
			prepState.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when deleting batch");
		}
	}

	public Batch getBatchById(int batchId) throws SQLException {
		String sql = "select * from batches where batch_id =?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement prepState = con.prepareStatement(sql)) {

			prepState.setInt(1, batchId);
			try (ResultSet rs = prepState.executeQuery()) {
				if (rs.next()) {
					return new Batch(rs.getInt("batch_id"), rs.getString("batch_name"), rs.getString("start_time"),
							rs.getString("end_time"), rs.getString("day_of_week"));
				}
			}

			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error when getting batch by id");
			}
		}
		return null;
	}

	public List<Batch> getAllBatches() throws SQLException {
		List<Batch> batches = new ArrayList<>();
		String sql = "select * from batches";
		try (Connection con = DBUtil.getConnection();
				PreparedStatement prepState = con.prepareStatement(sql);
				ResultSet rs = prepState.executeQuery()) {

			while (rs.next()) {
				batches.add(new Batch(rs.getInt("batch_id"), rs.getString("batch_name"), rs.getString("start_time"),
						rs.getString("end_time"), rs.getString("day_of_week")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error when getting all batches");
		}
		return batches;
	}
}