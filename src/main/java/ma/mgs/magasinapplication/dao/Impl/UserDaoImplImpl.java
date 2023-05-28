package ma.mgs.magasinapplication.dao.Impl;

import ma.mgs.magasinapplication.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImplImpl implements UserDaoImpl {
    private Connection conn= DB.getConnection();
    @Override
    public boolean auth(User user) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement("SELECT * FROM `users` WHERE Username=?");
            ps.setString(1, user.getUsername());
            rs = ps.executeQuery();



            if (rs.next()) {
                if(user.getPassword().equals(rs.getString("Password"))){
                    return true;
                }else {
                    return false;
                }
            }

        } catch (SQLException e) {
            System.err.println("problème de requête pour se connecter");;
            return false;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
        return false;
    }
}
