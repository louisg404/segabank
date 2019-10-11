package dal;

import bo.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO implements IDAO<Integer, Compte> {

    private static final String SELECT_QUERY = "SELECT * FROM compte";
    private static final String INSERT_QUERY = "INSERT INTO compte(solde, agence) VALUES (?, ?)";
    //DELETE ON CASCADE SPECIFIED IN SQL DATABASE
    private static final String DELETE_QUERY = "DELETE FROM compte WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE compte SET solde = ? WHERE id = ?";
    private static final String FIND_QUERY = "SELECT * FROM compte WHERE id = ?";

    @Override
    public List<Compte> findAll() throws SQLException, IOException, ClassNotFoundException {

        List<Compte> liste = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (Statement st = connection.createStatement();
                 ResultSet rs = st.executeQuery(SELECT_QUERY)) {

                while(rs.next()){
                    Compte compte = new Compte(rs.getDouble(2));
                    compte.setId(rs.getInt(1));
                    compte.setAgence(rs.getInt(3));
                    liste.add(compte);
                }
            }
        }
        return liste;
    }

    @Override
    public void create(Compte compte) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                ps.setDouble(1, compte.getSolde());
                ps.setInt(2, compte.getAgence());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        compte.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(Compte compte) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                ps.setDouble(1, compte.getSolde());
                ps.setInt(2, compte.getId());

                ps.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Compte compte) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (PreparedStatement ps = connection.prepareStatement(DELETE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, compte.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Compte findById(Integer integer) throws SQLException, IOException, ClassNotFoundException {

        Compte compte = null;
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(FIND_QUERY)){
                ps.setInt(1, integer);
                try (ResultSet rs  = ps.executeQuery()){
                    if(rs.next()){
                        compte = new Compte(rs.getDouble(2));
                        compte.setId(rs.getInt(1));
                        compte.setAgence(rs.getInt(3));
                    }
                }
            }
        }
        return compte;
    }
}