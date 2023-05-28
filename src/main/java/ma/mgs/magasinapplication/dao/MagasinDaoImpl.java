package ma.mgs.magasinapplication.dao;

import ma.mgs.magasinapplication.dao.Impl.DB;
import ma.mgs.magasinapplication.dao.MagasinDao;
import ma.mgs.magasinapplication.entities.Magasin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagasinDaoImpl implements MagasinDao {
    private Connection conn= DB.getConnection();

    @Override
    public void insert(Magasin magasin) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "INSERT INTO `magasin`( `nom`, `adresse`, `ville`, `Pays`, `numTel`, `estOuvert`) VALUES  (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, magasin.getNom());
            ps.setString(2, magasin.getAdresse());
            ps.setString(3, magasin.getVille());
            ps.setString(4, magasin.getPays());
            ps.setString(5, magasin.getNumTel());
            ps.setBoolean(6,magasin.EstOuvert());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {

                    int id = rs.getInt(1);
                    magasin.setId(id);
                }


                DB.closeResultSet(rs);
            } else {
                System.out.println("Aucune ligne renvoyée");
            }
        } catch (SQLException e) {
            System.err.println("problème d'insertion d'un magasin");
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void update(Magasin magasin) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE magasin SET nom = ? WHERE id = ?");

            ps.setString(1, magasin.getNom());
            ps.setInt(2, magasin.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de mise à jour d'un magasin");
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM magasin WHERE id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de suppression d'un magasin");;
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public Magasin findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM magasin WHERE id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
               Magasin magasin = new Magasin();

                magasin.setId(rs.getInt("id"));
                magasin.setNom(rs.getString("nom"));
                magasin.setVille(rs.getString("ville"));
                magasin.setAdresse(rs.getString("adresse"));
                magasin.setPays(rs.getString("Pays"));
                magasin.setNumTel(rs.getString("numTel"));
                magasin.setEstOuvert(rs.getBoolean("estOuvert"));


                return magasin;
            }

            return null;
        } catch (SQLException e) {
            System.err.println("problème de requête pour trouver un département");;
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Magasin> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM magasin");
            rs = ps.executeQuery();

            List<Magasin> listMagasin = new ArrayList<>();

            while (rs.next()) {
                Magasin magasin = new Magasin();

                magasin.setId(rs.getInt("id"));
                magasin.setNom(rs.getString("nom"));
                magasin.setAdresse(rs.getString("adresse"));
                magasin.setVille(rs.getString("ville"));
                magasin.setPays(rs.getString("Pays"));
                magasin.setNumTel(rs.getString("numTel"));
                magasin.setEstOuvert(rs.getBoolean("EstOuvert"));

                listMagasin.add(magasin);
            }

            return listMagasin;
        } catch (SQLException e) {
            System.err.println("problème de requête pour sélectionner un département");;
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }
}
