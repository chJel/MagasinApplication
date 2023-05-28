package ma.mgs.magasinapplication.entities;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class BooleanCellValueFactory implements Callback<CellDataFeatures<Magasin, Boolean>, ObservableValue<Boolean>>{

    @Override
    public ObservableValue<Boolean> call(CellDataFeatures<Magasin, Boolean> param) {
        Magasin magasin = param.getValue();
        boolean estOuvert = magasin.EstOuvert();
        return new SimpleBooleanProperty(estOuvert);
    }
}

