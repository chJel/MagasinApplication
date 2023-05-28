package ma.mgs.magasinapplication.service;

import ma.mgs.magasinapplication.dao.MagasinDaoImpl;
import ma.mgs.magasinapplication.dao.MagasinDao;
import ma.mgs.magasinapplication.entities.Magasin;

import java.util.List;

public class MagasinService {
    MagasinDao magasinDao =new MagasinDaoImpl();
    public void insert (Magasin magasin){
       magasinDao.insert(magasin);
    }
    public List<Magasin> findAll (){
        return magasinDao.findAll();
    }
    public void delete (int id){
        magasinDao.deleteById(id);
    }
    public void upadate (Magasin magasin){
        magasinDao.update(magasin);
    }
    public Magasin findById (int id){
       return magasinDao.findById(id);
    }

}
