package com.calvin.Repository;

import com.calvin.model.GiftItem;
import com.calvin.model.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemRepository {
    private static final String Item_Table = "gc_item";
    private static final String Order_Table = "gc_order";
    private Dao dao;

    public ItemRepository() throws SQLException {
        dao = new Dao();
    }

    public ArrayList<GiftItem> getAllGiftItems() throws SQLException {
        ArrayList<GiftItem> items = new ArrayList<>();

        String query = String.format("SELECT * FROM %s", Item_Table);
        Statement statement = dao.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            items.add(new GiftItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5)));
        }
        return items;
    }

    public GiftItem getItemById(int id) throws SQLException {
        GiftItem item = null;

        String query = String.format("SELECT * FROM %s WHERE id = ?", Item_Table);
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            item = new GiftItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4), resultSet.getString(5));
        }
        return item;
    }

    public int createUserOrder(int userId, int itemId, String hashcode) throws SQLException {
        String query = String.format("INSERT INTO %s(u_id, u_item_id, u_generated_code) VALUES(?, ?, ?)", Order_Table);
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, itemId);
        preparedStatement.setString(3, hashcode);

        return preparedStatement.executeUpdate();
    }

    public ArrayList<Transaction> GetAllTransaction(int userId) throws SQLException {
        ArrayList<Transaction> items = new ArrayList<>();

        String query = "SELECT DISTINCT u.u_id, it.item_name, it.price, u.u_generated_code, u.u_date FROM gc_item it INNER JOIN gc_order u ON \n" +
                "u.`u_item_id` = it.`id` WHERE u_id = ? ORDER by u.u_date DESC";

        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            items.add(new Transaction(resultSet.getDate(5), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4)));
        }
        return items;
    }

}
