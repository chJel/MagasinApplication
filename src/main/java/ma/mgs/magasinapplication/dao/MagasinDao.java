package ma.mgs.magasinapplication.dao;

import ma.mgs.magasinapplication.entities.Magasin;

import java.util.List;

public interface MagasinDao  {
    void insert(Magasin magasin);

    void update(Magasin magasin);

    void deleteById(Integer id);

    Magasin findById(Integer id);

    List<Magasin> findAll();
}
