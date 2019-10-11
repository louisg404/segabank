package dal;

import bo.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO implements IDAO<Integer, Operation> {

    private static final String INSERT_QUERY = "INSERT INTO operation(montant, transaction, compteId, agenceId) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM operation";

    public void create(Operation operation) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

                ps.setDouble(1, operation.getMontant());
                ps.setString(2, operation.getTransaction());
                ps.setInt(3, operation.getCompteId());
                ps.setInt(4, operation.getAgenceId());

                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        operation.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public void update(Operation object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void delete(Operation object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Operation findById(Integer integer) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Operation> findAll() throws SQLException, IOException, ClassNotFoundException {

        List<Operation> liste = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (Statement st = connection.createStatement();
                 ResultSet rs = st.executeQuery(SELECT_QUERY)) {

                while(rs.next()){

                    Operation operation = new Operation(rs.getDouble("montant"), rs.getString("transaction"), rs.getInt("compteId"), rs.getInt("agenceId"));
                    liste.add(operation);
                }
            }
        }
        return liste;
    }
}