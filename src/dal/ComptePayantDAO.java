package dal;

import bo.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ComptePayantDAO implements IDAO<Integer, ComptePayant> {

    private static final String INSERT_QUERY = "INSERT INTO comptepayant(tauxOperation, compteId) VALUES (?, ?)";

    @Override
    public void create(ComptePayant payant) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                ps.setDouble(1, ComptePayant.getTauxOperation());
                ps.setInt(2, payant.getCompteId());

                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        payant.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(ComptePayant object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void delete(ComptePayant object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public ComptePayant findById(Integer integer) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<ComptePayant> findAll() throws SQLException, IOException, ClassNotFoundException {
        return null;
    }
}