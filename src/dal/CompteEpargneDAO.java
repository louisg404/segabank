package dal;

import bo.CompteEpargne;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class CompteEpargneDAO implements IDAO<Integer, CompteEpargne> {

    private static final String INSERT_QUERY = "INSERT INTO compteepargne(tauxInteret, compteId) VALUES (?, ?)";

    @Override
    public void create(CompteEpargne epargne) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                ps.setDouble(1, epargne.getTauxInteret());
                ps.setInt(2, epargne.getCompteId());

                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        epargne.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(CompteEpargne object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void delete(CompteEpargne object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public CompteEpargne findById(Integer integer) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<CompteEpargne> findAll() throws SQLException, IOException, ClassNotFoundException {
        return null;
    }
}