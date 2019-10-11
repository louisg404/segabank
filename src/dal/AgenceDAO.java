package dal;

import bo.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenceDAO implements IDAO<Integer, Agence> {

    public static final String SELECT_QUERY = "SELECT * FROM agence";

    @Override
    public void create(Agence object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void update(Agence object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void delete(Agence object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Agence findById(Integer integer) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Agence> findAll() throws SQLException, IOException, ClassNotFoundException {

        List<Agence> liste = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (Statement st = connection.createStatement();
                 //Récupérer la requête SQL qui sélectionne toutes les agences
                 ResultSet rs = st.executeQuery(SELECT_QUERY)) {

                while(rs.next()){
                    Agence agence = new Agence(rs.getInt("code"), rs.getString("adresse"));
                    agence.setId(rs.getInt("id"));
                    liste.add(agence);
                }
            }
        }
        return liste;
    }
}