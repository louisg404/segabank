package dal;

import bo.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class CompteSimpleDAO implements IDAO<Integer, CompteSimple> {

    private static final String INSERT_QUERY = "INSERT INTO comptesimple(decouvert, compteId) VALUES (?, ?)";

    @Override
    public void create(CompteSimple simple) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                ps.setDouble(1, simple.getDecouvert());
                ps.setInt(2, simple.getCompteId());

                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        simple.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(CompteSimple object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void delete(CompteSimple object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public CompteSimple findById(Integer integer) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<CompteSimple> findAll() throws SQLException, IOException, ClassNotFoundException {
        return null;
    }
}