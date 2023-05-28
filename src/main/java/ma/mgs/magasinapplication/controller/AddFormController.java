package ma.mgs.magasinapplication.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ma.mgs.magasinapplication.entities.BooleanCellValueFactory;
import ma.mgs.magasinapplication.entities.Magasin;
import ma.mgs.magasinapplication.service.MagasinService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AddFormController {
    MagasinService magasinService = new MagasinService();
    @FXML
    private TextField nom;
    @FXML
    private TextField ville;
    @FXML


    private ComboBox pays;
    @FXML
    private TextField adress;
    @FXML
    private TextField telephone;
    @FXML
    private RadioButton ouvert;
    @FXML
    private RadioButton ferme;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Magasin> TableMagasin;
    @FXML
    private TableColumn<Magasin , String> nomCol;
   @FXML
    private TableColumn<Magasin, String > adresseCol;
    @FXML
    private TableColumn<Magasin,String> villeCol;
    @FXML
    private TableColumn<Magasin,String >paysCol;
    @FXML
    private TableColumn<Magasin,String> telCol;
    @FXML
    private TableColumn<Magasin,Boolean> ouvertCol;
    private TextField nomField ;
    private TextField adresseField;
    private TextField villeField ;
    private TextField paysField ;
    private TextField telephoneField;
    @FXML
    private Button exporterBtn;

    @FXML
    private Button importerBtn;
    List<String> lisPays = new ArrayList<>();


    List<Magasin> magasinList =  getMagasinList();
    @FXML
    public void initialize(){
        //Exporter Importer :
        exporterBtn.setOnAction(event -> exporterDonnees());
        importerBtn.setOnAction(event -> importerDonnees());

        // Ajoute les personnes à la TableView
        TableMagasin.getItems().addAll(magasinList);


        // Lie les colonnes aux propriétés de la classe Person

        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        villeCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        paysCol.setCellValueFactory(new PropertyValueFactory<>("Pays"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        //ouvertCol.setCellValueFactory(new PropertyValueFactory<>("estOuvert"));
        ouvertCol.setCellValueFactory(new BooleanCellValueFactory());

        // Créer une colonne pour les actions
        TableColumn<Magasin, Void> actionsCol = new TableColumn<>("Actions");

        // Définir une usine de cellules pour la colonne des actions
        actionsCol.setCellFactory(param -> new TableCell<Magasin ,Void>() {
            private final Button modifierButton = new Button("Modifier");
            private final Button supprimerButton = new Button("Supprimer");

            {
                modifierButton.setOnAction(event -> {
                    Magasin magasin = getTableView().getItems().get(getIndex());
                    modifierMagasin(magasin);
                });
                supprimerButton.setOnAction(event -> {
                    Magasin magasin = getTableView().getItems().get(getIndex());
                    supprimerMagasin(magasin);
                });
            }

            //Code Modifier et supprimer
            public void modifierMagasin(Magasin magasin) {
                // Afficher une fenêtre de modification ou effectuer toute autre logique nécessaire
                // par exemple, afficher une boîte de dialogue avec les champs pré-remplis pour la modification
                // Vous pouvez utiliser une nouvelle scène ou une boîte de dialogue JavaFX, selon vos besoins
                // Voici un exemple de boîte de dialogue JavaFX avec des champs pré-remplis :

                // Créer un nouveau stage pour la boîte de dialogue de modification
                Stage stage = new Stage();
                stage.setTitle("Modifier Magasin");

                // Créer les boutons radio pour les options "Ouvert" et "Fermé"
                RadioButton ouvertButton = new RadioButton("Ouvert");
                RadioButton fermeButton = new RadioButton("Fermé");

                // Créer un groupe de boutons radio pour les options "Ouvert" et "Fermé"
                ToggleGroup toggleGroup = new ToggleGroup();
                ouvertButton.setToggleGroup(toggleGroup);
                fermeButton.setToggleGroup(toggleGroup);
                // Sélectionner le bouton radio en fonction de la valeur du magasin
                if (magasin.EstOuvert()) {
                    ouvertButton.setSelected(true);
                } else {
                    fermeButton.setSelected(true);
                }

                // Créer les champs de saisie pour les informations du magasin
                 nomField = new TextField(magasin.getNom());
                adresseField = new TextField(magasin.getAdresse());
                 villeField = new TextField(magasin.getVille());
                 paysField =new TextField(magasin.getPays());
                telephoneField=new TextField(magasin.getNumTel());
                // Créer un bouton de validation pour sauvegarder les modifications
                Button validerButton = new Button("Valider");
                validerButton.setOnAction(event -> {
                    // Récupérer les nouvelles valeurs des champs
                    String nouveauNom = nomField.getText();
                    String nouvelleAdresse = adresseField.getText();
                    String nouvelleVille = villeField.getText();
                    String pays = paysField.getText();
                    String numTel = telephoneField.getText();
                    boolean estOuvert = ouvertButton.isSelected();

                    // Mettre à jour les informations du magasin
                    magasin.setNom(nouveauNom);
                    magasin.setAdresse(nouvelleAdresse);
                    magasin.setVille(nouvelleVille);
                    magasin.setPays(pays);
                    magasin.setNumTel(numTel);
                    magasin.setEstOuvert(estOuvert);

                    // Fermer la fenêtre de modification
                    stage.close();
                    magasinService.upadate(magasin);

                    // Actualiser la TableView après la modification
                    actualiserTableView();
                });

                // Créer un conteneur pour le formulaire de modification
                VBox container = new VBox();
                container.setSpacing(10);
                container.setPadding(new Insets(10));

                // Ajouter les champs de saisie et les boutons radio dans le conteneur
                container.getChildren().addAll(
                        new Label("Nom:"),
                        nomField,
                        new Label("Adresse:"),
                        adresseField,
                        new Label("Ville:"),
                        villeField,
                        new Label("Pays:"),
                        paysField,
                        new Label("Numéro de téléphone:"),
                        telephoneField,
                        new Label("Statut:"),
                        new HBox(10, ouvertButton, fermeButton),
                        validerButton
                );

                // Créer une nouvelle scène avec le conteneur et l'afficher dans le stage
                Scene scene = new Scene(container);
                stage.setScene(scene);
                stage.show();
            }

            public void supprimerMagasin(Magasin magasin) {
                // Afficher une confirmation de suppression
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Supprimer le magasin ?");
                alert.setContentText("Êtes-vous sûr de vouloir supprimer le magasin ?");

                // Attendre la réponse de l'utilisateur
                Optional<ButtonType> result = alert.showAndWait();

                // Vérifier si l'utilisateur a cliqué sur le bouton "OK"
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Supprimer le magasin
                    magasinService.delete(magasin.getId());

                    // Actualiser la TableView après la suppression
                    actualiserTableView();
                }
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttonsContainer = new HBox(modifierButton, supprimerButton);
                    setGraphic(buttonsContainer);
                }
            }
        });
        // Ajouter la colonne des actions à la TableView
        TableMagasin.getColumns().add(actionsCol);







        InitialiseCombobox();
        ajouter.setOnMouseClicked(arg->{
            ajouterMagasin(construireMagasin());


        });
    }
    public void ajouterMagasin(Magasin magasin){
        if(magasin!=null) {
            magasinService.insert(magasin);
            vider();
            actualiserTableView();
        }

    }

    public void vider() {
        nom.setText("");
        ville.setText("");
        adress.setText("");
        telephone.setText("");
        pays.setValue("");


    }
    public Magasin construireMagasin(){
        Magasin magasin= new Magasin();
        if(nom.getText().isEmpty() || ville.getText().isEmpty() || adress.getText().isEmpty() || pays.getValue().equals("") || telephone.getText().isEmpty())
            return  null;
        else {
            magasin.setNom(nom.getText());
            magasin.setVille(ville.getText());
            magasin.setAdresse(adress.getText());
            magasin.setPays(pays.getValue().toString());
            magasin.setNumTel(telephone.getText());
            if(ferme.isSelected()) magasin.setEstOuvert(false);
            else magasin.setEstOuvert(true);
        }

        return magasin;

    }
    public void InitialiseCombobox(){
        lisPays.add("Maroc");
        lisPays.add("Egypte");
        lisPays.add("Tunisie");
        lisPays.add("Algerie");


        pays.getItems().addAll(lisPays);
    }
    public List<Magasin> getMagasinList(){
        System.out.println(magasinService.findAll());
        return magasinService.findAll();
    }
    private void actualiserTableView() {
        TableMagasin.getItems().clear(); // Effacer les données actuelles de la TableView
        TableMagasin.getItems().addAll(getMagasinList()); // Récupérer et ajouter la nouvelle liste de magasins
        // hahia chtiha ? uii takeli far mechwi yalah tester ok
    }

    private void exporterDonnees() {


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Mes magasins");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(1).setCellValue("Nom");
        headerRow.createCell(2).setCellValue("Adresse");
        headerRow.createCell(3).setCellValue("Ville");
        headerRow.createCell(4).setCellValue("Pays");
        headerRow.createCell(5).setCellValue("Numero de Telephone");
        headerRow.createCell(6).setCellValue("Est-Ouvert?");
        int rowNum = 1;
        for (Magasin magasin : magasinList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(magasin.getId());
            row.createCell(1).setCellValue(magasin.getNom());
            row.createCell(2).setCellValue(magasin.getAdresse());
            row.createCell(3).setCellValue(magasin.getVille());
            row.createCell(4).setCellValue(magasin.getPays());
            row.createCell(5).setCellValue(magasin.getNumTel());
            row.createCell(6).setCellValue(magasin.EstOuvert());


        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("Mes magasins.xlsx");
        File file = fileChooser.showSaveDialog(exporterBtn.getScene().getWindow());

        if (file != null) {

            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Fichier Excel exporté avec succès.");
                alert.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


        try {
            workbook.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    private void importerDonnees() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Excel", "*.xlsx", "*.xls"));
        File file = fileChooser.showOpenDialog(importerBtn.getScene().getWindow());

        if (file != null) {
            try (FileInputStream fileIn = new FileInputStream(file);
                 Workbook workbook = new XSSFWorkbook(fileIn)) {

                Sheet sheet = workbook.getSheetAt(0);

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);

                    int id = (int) row.getCell(0).getNumericCellValue();
                    String nom = row.getCell(1).getStringCellValue();
                    String adresse = row.getCell(2).getStringCellValue();
                    String ville =  row.getCell(3).getStringCellValue();
                    String pays = row.getCell(4).getStringCellValue();
                    String tele = row.getCell(5).getStringCellValue();
                    Boolean estOuvert = row.getCell(6).getBooleanCellValue();

                    Magasin magasin = new Magasin();
                    magasin.setId(id);
                    magasin.setNom(nom);
                    magasin.setAdresse(adresse);
                    magasin.setVille(ville);
                    magasin.setPays(pays);
                    magasin.setNumTel(tele);
                    magasin.setEstOuvert(estOuvert);

                    ajouterMagasin(magasin);
                }

                actualiserTableView();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Importation des magasins à partir du fichier Excel réussie. ");
                alert.showAndWait();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }


}
