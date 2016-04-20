/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delta.projekt;

import java.awt.Font;
import java.io.File;
import java.time.LocalTime;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DeltaProjekt extends Application {
    Stage thestage;
    Scene startScene, statisticsScene,dailyBookingScene,alterResourcersScene, addResourceScene, alterAResourceScene,changeARecourceScene, abookingscene;
    Database m = new Database();
    
    List<Person> persons;
    List<Truck> trucks;
    List<Ship> ships;
    
    Person changeperson;
    Truck changetruck;
    Ship changeship;
    ObservableList oll;
    
    TableView tablev;
    Label alterMessage,date,time;
    File f;
    String bookingdate, bookingtime, bookingharbor;
    int bookingslot;
     public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        
        thestage = primaryStage;
        //thestage.initStyle(StageStyle.UNDECORATED);
        startScene = new Scene(getStartPageBorderPane(), 500, 600);
        statisticsScene = new Scene(getStatisticsPageBorderPane(), 500, 600);
        dailyBookingScene = new Scene(getDailyBookingBorderPane(), 500, 600);
        alterResourcersScene = new Scene(getAlterResourcesPageBorderPane(), 500, 600);
        addResourceScene = new Scene(getAddRecourseBorderPane(), 500, 550);
        alterAResourceScene = new Scene(getAlterAResourceBorderPane(), 500, 600);
        abookingscene = new Scene(getABookingSceneBorderPane(),500,600 );
                
        f = new File("newfile.css");
        startScene.getStylesheets().clear();
        startScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        statisticsScene.getStylesheets().clear();
        statisticsScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        dailyBookingScene.getStylesheets().clear();
        dailyBookingScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        alterResourcersScene.getStylesheets().clear();
        alterResourcersScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        addResourceScene.getStylesheets().clear();
        addResourceScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        alterAResourceScene.getStylesheets().clear();
        alterAResourceScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        abookingscene.getStylesheets().clear();
        abookingscene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        
        primaryStage.setTitle("Botes!");
        primaryStage.setScene(startScene);
        primaryStage.show();
        
    }
    public BorderPane getABookingSceneBorderPane(){
        BorderPane abookingsceneBorderPane = new BorderPane();
        abookingsceneBorderPane.setCenter(addABookingSceneMenuBtns());
        return abookingsceneBorderPane;
    }
    
    public BorderPane getChangeAPersonSceneBorderPane(){
        BorderPane changeAPersonSceneBorderPane = new BorderPane();
        changeAPersonSceneBorderPane.setCenter(addChangeAPersonMenuBtns());
        return changeAPersonSceneBorderPane;
    }
    public BorderPane getChangeAShipSceneBorderPane(){
        BorderPane changeAShipSceneBorderPane = new BorderPane();
        changeAShipSceneBorderPane.setCenter(addChangeAShipMenuBtns());
        return changeAShipSceneBorderPane;
    }
    public BorderPane getChangeATruckSceneBorderPane(){
        BorderPane changeATruckSceneBorderPane = new BorderPane();
        changeATruckSceneBorderPane.setCenter(addChangeATruckMenuBtns());
        return changeATruckSceneBorderPane;
    }
    public BorderPane getDailyBookingBorderPane(){
        BorderPane dailyBookingBorderPane = new BorderPane();
        dailyBookingBorderPane.setCenter(addDailyBookingMenuBtns());
        dailyBookingBorderPane.setBottom(addGoBackToStartPageBtn());
        return dailyBookingBorderPane;
    }
    public BorderPane getAddRecourseBorderPane(){
        BorderPane addRecourseBorderPane = new BorderPane();
        addRecourseBorderPane.setCenter(addAddResourceMenuBtns());
        return addRecourseBorderPane;
    }
    public BorderPane getAlterAResourceBorderPane(){
        BorderPane alterAResourcePageBorderPane = new BorderPane();
        alterAResourcePageBorderPane.setCenter(addAlterAResourceMenuBtns());
        return alterAResourcePageBorderPane;
    }
    public BorderPane getStartPageBorderPane(){
        BorderPane starPageBorderPane = new BorderPane();
        starPageBorderPane.setCenter(addStartMenuBtns());
        starPageBorderPane.setBottom(addQuitBtn());
        return starPageBorderPane;
    }
    public BorderPane getStatisticsPageBorderPane(){
        BorderPane statisticsPageBorderPane = new BorderPane();
        
        statisticsPageBorderPane.setCenter(addStatisticsMenuBtns());
        return statisticsPageBorderPane ;
    }
    
    public BorderPane getAlterResourcesPageBorderPane(){
        BorderPane alterResourcersPageBorderPane = new BorderPane();
        alterResourcersPageBorderPane.setBottom(addGoBackToStartPageBtn());
        alterResourcersPageBorderPane.setCenter(addAlterResourcesMenuBtns());
        return alterResourcersPageBorderPane;
    }
    public HBox addGoBackToStartPageBtn() {
        
    HBox hbox = new HBox();
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setSpacing(10);
    hbox.setAlignment(Pos.BOTTOM_RIGHT);
    

    Button goBackBtn = new Button("Back");
    goBackBtn.setPrefSize(80, 20);
    goBackBtn.setOnAction(e -> thestage.setScene(startScene));
    
    hbox.getChildren().addAll(goBackBtn);
    return hbox;
    }
    
    public HBox addQuitBtn(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        
        Button quitBtn = new Button("Quit");
        quitBtn.setPrefSize(80, 20);
        quitBtn.setOnAction(e -> thestage.close());
        
        hbox.getChildren().addAll(quitBtn);
        return hbox;
    }
    public VBox addStartMenuBtns(){
        VBox startPageVbox = new VBox(20);
        startPageVbox.setAlignment(Pos.CENTER);
        
        Button goToStatisticsBtn = new Button();
        Button goToDailyBookingBtn = new Button();
        Button goToAlterResourcesBtn = new Button();
        goToStatisticsBtn.setText("Statistics");
        goToStatisticsBtn.setPrefSize(100, 20);
        goToDailyBookingBtn.setText("Daily Booking");
        goToDailyBookingBtn.setPrefSize(100, 20);
        goToAlterResourcesBtn.setText("Alter resourcers");
        goToAlterResourcesBtn.setPrefSize(100, 20);
        
        goToStatisticsBtn.setOnAction(e -> thestage.setScene(statisticsScene));
        goToDailyBookingBtn.setOnAction(e -> thestage.setScene(dailyBookingScene));
        goToAlterResourcesBtn.setOnAction(e -> thestage.setScene(alterResourcersScene));
        
        startPageVbox.getChildren().addAll(goToStatisticsBtn,goToDailyBookingBtn,goToAlterResourcesBtn);
        
        return startPageVbox;
    }
  
    public VBox addAlterResourcesMenuBtns(){
        VBox alterResourcesVbox = new VBox(20);
        alterResourcesVbox.setAlignment(Pos.CENTER);
        Button addNewResourcesBtn = new Button();
        Button alterAResourceBtn = new Button();
        addNewResourcesBtn.setText("Add a new resource");
        addNewResourcesBtn.setPrefSize(140, 20);
        alterAResourceBtn.setText("Alter a resource");
        alterAResourceBtn.setPrefSize(140, 20);
        alterAResourceBtn.setOnAction(e -> thestage.setScene(alterAResourceScene));
        addNewResourcesBtn.setOnAction(e -> thestage.setScene(addResourceScene));
        alterResourcesVbox.getChildren().addAll(addNewResourcesBtn, alterAResourceBtn);
        
        return alterResourcesVbox;
    } 
    public VBox addAlterAResourceMenuBtns(){
       
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setMaxWidth(450);
        vbox.setPadding(new Insets(15, 12, 15, 12));
        
        alterMessage = new Label();
                                
                                FadeTransition fader = createFader(alterMessage);
                                SequentialTransition blinkThenFade = new SequentialTransition(
                                        alterMessage,
                                      
                                        fader
                                );
        alterMessage.setVisible(false);
       
        Button back = new Button("Back");
        back.setPrefSize(120, 20);
        Button remove = new Button("Remove");
        remove.setPrefSize(120, 20);
        Button change = new Button("Change");
        change.setPrefSize(120, 20);
        

        final ChoiceBox comboBox = new ChoiceBox();
        comboBox.getItems().addAll(
        "Person",
        "Truck",
        "Ship"
        );
        comboBox.getSelectionModel().clearSelection();
        
        tablev = new TableView();
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        TableColumn förnamn = new TableColumn("Förnamn");
        förnamn.setCellValueFactory(
                new PropertyValueFactory<>("förnamn"));
        TableColumn efternamn = new TableColumn("Efternamn");
        efternamn.setCellValueFactory(
                new PropertyValueFactory<>("efternamn"));
        TableColumn körkort = new TableColumn("Körkort");
        körkort.setCellValueFactory(
                new PropertyValueFactory<>("körkort"));
        TableColumn status = new TableColumn("Status");
        status.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        TableColumn schema = new TableColumn("Schema");
        schema.setCellValueFactory(
                new PropertyValueFactory<>("schema"));
        TableColumn wage = new TableColumn("Lön");
        wage.setCellValueFactory(
                new PropertyValueFactory<>("wage"));
        
        TableColumn trucktype = new TableColumn("TruckType");
        trucktype.setCellValueFactory(
                new PropertyValueFactory<>("trucktyp"));
        
        TableColumn truckstatus = new TableColumn("Status");
        truckstatus.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        
        TableColumn truckid = new TableColumn("ID");
        truckid.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        
        TableColumn fee = new TableColumn("Fee");
        fee.setCellValueFactory(
                new PropertyValueFactory<>("fee"));
        
        TableColumn shipid = new TableColumn("ID");
        shipid.setCellValueFactory(
                new PropertyValueFactory<>("shipid"));
        
        TableColumn shipname = new TableColumn("Ship Name");
        shipname.setCellValueFactory(
                new PropertyValueFactory<>("namn"));
        
        TableColumn shipowner = new TableColumn("Owner");
        shipowner.setCellValueFactory(
                new PropertyValueFactory<>("bolag"));
        
        TableColumn shipvolymid = new TableColumn("VolymID");
        shipvolymid.setCellValueFactory(
                new PropertyValueFactory<>("volymid"));
        
        
        oll = FXCollections.observableArrayList();
        
      
        vbox.getChildren().addAll(comboBox,back);
          comboBox.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                    
                switch (comboBox.getSelectionModel().getSelectedItem().toString()) {
                    case "Person":
                        tablev.getColumns().clear();
                        vbox.getChildren().clear();
                        tablev.getColumns().addAll(id,förnamn,efternamn,körkort,status,schema,wage);
                        oll.clear();
                        persons = m.getAllPersons();
 
                        for (Person p : persons){
                            
                            oll.add(new ListPerson(p));
                            
                        }
                        tablev.setItems(oll);
                        vbox.getChildren().addAll(comboBox,tablev,alterMessage,change,remove,back);
                        
                        
                        break;
                    case "Truck":
                        tablev.getColumns().clear();
                        vbox.getChildren().clear();
                        
                        tablev.getColumns().addAll(truckid,trucktype,truckstatus,fee);
                        oll.clear();
                        trucks = m.getAllTrucks();
                       
                        for (Truck t : trucks){
                            oll.add(new ListTruck(t));
                        }
                       tablev.setItems(oll);
                        vbox.getChildren().addAll(comboBox,tablev,alterMessage,change,remove,back);
                        
                        break;
                    case "Ship":
                        tablev.getColumns().clear();
                        vbox.getChildren().clear();
                        
                        tablev.getColumns().addAll(shipid,shipname,shipowner,shipvolymid);
                        oll.clear();
                        ships = m.getAllShip();
                       
                        for (Ship s : ships){
                            oll.add(new ListShip(s));
                        }
                        
                        tablev.setItems(oll);
                        vbox.getChildren().addAll(comboBox,tablev,alterMessage,change,remove,back);
                        
                        break;
                    default:
                        break;
                }
                }
            
        });
          remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if(!(listv.getSelectionModel().getSelectedIndex() == -1)){
                 switch (comboBox.getSelectionModel().getSelectedItem().toString()) {
                    case "Person":
                        
                        m.removePerson(persons.get(tablev.getSelectionModel().getSelectedIndex()));
                        alterMessage.setText("Removed");
                        alterMessage.setVisible(true);
                                blinkThenFade.play();
                        persons = m.getAllPersons();
                        oll.clear();
                        for (Person p : persons){

                            oll.add(new ListPerson(p));
                            
                        }
                        tablev.setItems(oll);
                        break;
                        
                    case "Truck":
                        m.removeTruck(trucks.get(tablev.getSelectionModel().getSelectedIndex()));
                        alterMessage.setText("Removed");
                        alterMessage.setVisible(true);
                                blinkThenFade.play();
                        trucks = m.getAllTrucks();
                        oll.clear();
                         for (Truck t : trucks){
                            
                            oll.add(new ListTruck(t));
                            
                        }
                        tablev.setItems(oll);
                        
                        break;
                    case "Ship":
                        m.removeShip(ships.get(tablev.getSelectionModel().getSelectedIndex()));
                        alterMessage.setText("Removed");
                        alterMessage.setVisible(true);
                                blinkThenFade.play();
                        ships = m.getAllShip();
                        oll.clear();
                        for (Ship s : ships){
                            oll.add(new ListShip(s));
                        }
                        tablev.setItems(oll);
                        
                        break;
                    default:
                        break;
                }
                }
           // }
        });
          change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if(!(listv.getSelectionModel().getSelectedIndex() == -1)){
                 switch (comboBox.getSelectionModel().getSelectedItem().toString()) {
                    case "Person":
                        alterMessage.setText("Uppdated");
                        changeperson = persons.get(tablev.getSelectionModel().getSelectedIndex());
                        changeARecourceScene = new Scene(getChangeAPersonSceneBorderPane(),500,550);
                        changeARecourceScene.getStylesheets().clear();
                        changeARecourceScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                        thestage.setScene(changeARecourceScene);
                        break;
                    case "Truck":
                        alterMessage.setText("Uppdated");
                        changetruck = trucks.get(tablev.getSelectionModel().getSelectedIndex());
                        changeARecourceScene = new Scene(getChangeATruckSceneBorderPane(),500,550);
                        changeARecourceScene.getStylesheets().clear();
                        changeARecourceScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                        thestage.setScene(changeARecourceScene);
                        break;
                    case "Ship":
                        alterMessage.setText("Uppdated");
                        changeship = ships.get(tablev.getSelectionModel().getSelectedIndex());
                        changeARecourceScene = new Scene(getChangeAShipSceneBorderPane(),500,550);
                        changeARecourceScene.getStylesheets().clear();
                        changeARecourceScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
                        thestage.setScene(changeARecourceScene);
                        break;
                    default:
                        break;
                }
                }
           // }
        });
          back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox.getChildren().clear();
                vbox.getChildren().addAll(comboBox,back);
                
                thestage.setScene(alterResourcersScene);
            }
        });
        
        
        
        return vbox;
    }
    public VBox addAddResourceMenuBtns(){
       
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setMaxWidth(200);
        vbox.setPadding(new Insets(15, 12, 15, 12));
        TextField personFirstName = new TextField();
        TextField personLastName = new TextField();
        
        
        TextField shipName = new TextField();
        ComboBox shipCompany = new ComboBox();
        shipCompany.getItems().addAll("StenaLine", "FlyteTyg AB", "SiljaLine");
        
        
        
        ComboBox truckStatus = new ComboBox();
        truckStatus.getItems().addAll("Ok", "Reparation", "Reserv","Inaktiv");
        
        ComboBox trucktype = new ComboBox();
        trucktype.getItems().addAll("A","AA","B","BB","C","CC","CCC","K");
        ComboBox licence = new ComboBox();
        licence.getItems().addAll("A","AA","B","BB","C","CC","CCC","K");
        ComboBox volume = new ComboBox();
        volume.getItems().addAll("A","AA","B","BB","C","CC","CCC","K");
        
        ComboBox pstatus = new ComboBox();
        pstatus.getItems().addAll("100%","50%","Sjuk","VAB","Student","Semester");
        ComboBox pschedual = new ComboBox();
        pschedual.getItems().addAll("M-F","L-S","S");
        
        Label addMessage = new Label("Added");
                                
                                FadeTransition fader = createFader(addMessage);
                                SequentialTransition blinkThenFade = new SequentialTransition(
                                        addMessage,
                                      
                                        fader
                                );
        addMessage.setVisible(false);
        Label personFirstNameLabel = new Label("FirstName:");
        Label personLastNameLabel = new Label("LastName:");
        Label personDriverLicenseLabel = new Label("Driver License:");
        Label personStatusLabel = new Label("Status:");
        Label personSchedualLabel = new Label("Schedual:");
        
        Label shipNameLabel = new Label("Name:");
        Label shipCompanyLabel = new Label("Company:");
        Label shipVolumeLabel = new Label("Volume");
        
        Label truckTypeLabel = new Label("Type");
        Label truckStatusLabel = new Label("Status:");
        
        
        Button back = new Button("Back");
        back.setPrefSize(120, 20);
        Button add = new Button("Add");
        add.setPrefSize(120, 20);
        
        final ComboBox comboBox = new ComboBox();
        comboBox.setPromptText("Table");
        comboBox.getItems().addAll(
        "Person",
        "Truck",
        "Ship"
        );
        
        vbox.getChildren().addAll(comboBox,back);
          comboBox.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                switch (comboBox.getSelectionModel().getSelectedItem().toString()) {
                    case "Person":
                        vbox.getChildren().clear();
                        vbox.getChildren().addAll(comboBox,personFirstNameLabel,personFirstName,personLastNameLabel, personLastName,
                        personDriverLicenseLabel, licence, personStatusLabel, pstatus, personSchedualLabel,pschedual,addMessage,add,back);
                        add.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                               
                               
                                m.addPerson(new Person(personFirstName.getText(),personLastName.getText(),licence.getSelectionModel().getSelectedItem().toString(),pstatus.getSelectionModel().getSelectedItem().toString(),pschedual.getSelectionModel().getSelectedItem().toString()));
                                addMessage.setVisible(true);
                                blinkThenFade.play();
                            }
                        });
                        break;
                    case "Truck":
                        vbox.getChildren().clear();
                        vbox.getChildren().addAll(comboBox,truckTypeLabel,trucktype,truckStatusLabel,truckStatus,addMessage,add,back);
                        add.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                
        
                                m.addTruck(new Truck(trucktype.getSelectionModel().getSelectedItem().toString(),truckStatus.getSelectionModel().getSelectedItem().toString()));
                                addMessage.setVisible(true);
                                blinkThenFade.play();
                                
                            }
                        });
                        
                        break;
                    case "Ship":
                        vbox.getChildren().clear();
                        vbox.getChildren().addAll(comboBox,shipNameLabel, shipName,shipCompanyLabel,shipCompany,shipVolumeLabel,volume,addMessage,add,back);
                        add.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                m.addShip(new Ship(shipName.getText(),shipCompany.getSelectionModel().getSelectedItem().toString(), volume.getSelectionModel().getSelectedItem().toString()));
                                addMessage.setVisible(true);
                                blinkThenFade.play();
                                
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
          back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox.getChildren().clear();
                vbox.getChildren().addAll(comboBox,back);
                
                thestage.setScene(alterResourcersScene);
            }
        });
       
        return vbox;
    }
      public VBox addStatisticsMenuBtns(){
        VBox statisticsPageVbox = new VBox(20);
        statisticsPageVbox.setAlignment(Pos.TOP_CENTER);
        statisticsPageVbox.setMaxWidth(200);
        statisticsPageVbox.setPadding(new Insets(15, 12, 15, 12));
        
        DatePicker datePicker = new DatePicker();
        
        ChoiceBox choicebox = new ChoiceBox();
        choicebox.getItems().addAll("Day Rapport", "Week Rapport");
        choicebox.getSelectionModel().selectFirst();
        Button back = new Button("Back");
        back.setPrefSize(120, 20);
        Label weekLabel = new Label("Week:");
        Label dateLabel = new Label("Date:");
        Label shipLabel = new Label("Ship:");
        
        ComboBox week = new ComboBox();
        for(int i = 1; i <=52; i++){
            week.getItems().add(i);
        }
        TextField date = new TextField();
        TextField ship = new TextField();
        
       statisticsPageVbox.getChildren().addAll(choicebox,back);
        
       choicebox.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(choicebox.getSelectionModel().getSelectedItem().toString().equals("Day Rapport")){
                   statisticsPageVbox.getChildren().clear();
                   statisticsPageVbox.getChildren().addAll(choicebox,dateLabel,datePicker,shipLabel,ship,back);
                }
                else if(choicebox.getSelectionModel().getSelectedItem().toString().equals("Week Rapport")){
                   statisticsPageVbox.getChildren().clear();
                   statisticsPageVbox.getChildren().addAll(choicebox,weekLabel,week,back);
                }
            }
        });
         back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                statisticsPageVbox.getChildren().clear();
                statisticsPageVbox.getChildren().addAll(choicebox,back);
                thestage.setScene(startScene);
            }
        });
        return statisticsPageVbox;
        
    }
      public GridPane addDailyBookingMenuBtns(){
          
          Button uppdate = new Button("Uppdate");
          DatePicker datePicker = new DatePicker();
          Label firstDock = new Label("Dock 101");
          firstDock.setVisible(false);
          Label secondDock = new Label("Dock 201");
          secondDock.setVisible(false);
          Label thirdDock = new Label("Dock 301");
                    thirdDock.setVisible(false);

          Button firstDock0816 = new Button("08-16");
          firstDock0816.setPrefSize(60, 50);
                    firstDock0816.setVisible(false);

          Button firstDock1600 = new Button("16-00");
          firstDock1600.setPrefSize(60, 50);
                    firstDock1600.setVisible(false);

          Button firstDock0008 = new Button("00-08");
          firstDock0008.setPrefSize(60, 50);
                    firstDock0008.setVisible(false);

          Button secondDock0816 = new Button("08-16");
          secondDock0816.setPrefSize(60, 50);
                    secondDock0816.setVisible(false);

          Button secondDock1600 = new Button("16-00");
          secondDock1600.setPrefSize(60, 50);
                    secondDock1600.setVisible(false);

          Button secondDock0008 = new Button("00-08");
          secondDock0008.setPrefSize(60, 50);
                    secondDock0008.setVisible(false);

          Button thirdDock0816 = new Button("08-16");
          thirdDock0816.setPrefSize(60, 50);
                    thirdDock0816.setVisible(false);

          Button thirdDock1600 = new Button("16-00");
          thirdDock1600.setPrefSize(60, 50);
                    thirdDock1600.setVisible(false);

          Button thirdDock0008 = new Button("00-08");
          thirdDock0008.setPrefSize(60, 50);
                    thirdDock0008.setVisible(false);

          GridPane gridpane = new GridPane();
          gridpane.setAlignment(Pos.CENTER);
          
    gridpane.setPadding(new Insets(5));
    gridpane.setHgap(20);
    gridpane.setVgap(20);
    ColumnConstraints column1 = new ColumnConstraints(100);
    ColumnConstraints column2 = new ColumnConstraints(100);
    column2.setHgrow(Priority.ALWAYS);
    gridpane.getColumnConstraints().addAll(column1, column2);

    GridPane.setHalignment(datePicker, HPos.CENTER);
    gridpane.add(datePicker, 1, 0);
    GridPane.setHalignment(uppdate, HPos.CENTER);
    gridpane.add(uppdate, 1, 1);
    // First name label
    GridPane.setHalignment(firstDock, HPos.CENTER);
    gridpane.add(firstDock, 1, 2);

    // Last name label
    GridPane.setHalignment(firstDock0008, HPos.CENTER);
    gridpane.add(firstDock0008, 0, 3);

    // First name field
    GridPane.setHalignment(firstDock0816, HPos.CENTER);
    gridpane.add(firstDock0816, 1, 3);

    // Last name field
    GridPane.setHalignment(firstDock1600, HPos.CENTER);
    gridpane.add(firstDock1600, 2, 3);

    // Save button
    GridPane.setHalignment(secondDock, HPos.CENTER);
    gridpane.add(secondDock, 1, 4);
    
    GridPane.setHalignment(secondDock0008, HPos.CENTER);
    gridpane.add(secondDock0008, 0, 5);
    
    GridPane.setHalignment(secondDock0816, HPos.CENTER);
    gridpane.add(secondDock0816, 1, 5);
    
    GridPane.setHalignment(secondDock1600, HPos.CENTER);
    gridpane.add(secondDock1600, 2, 5);
    
    GridPane.setHalignment(thirdDock, HPos.CENTER);
    gridpane.add(thirdDock, 1, 6);
    
    GridPane.setHalignment(thirdDock0008, HPos.CENTER);
    gridpane.add(thirdDock0008, 0, 7);
    
    GridPane.setHalignment(thirdDock0816, HPos.CENTER);
    gridpane.add(thirdDock0816, 1, 7);
    
    GridPane.setHalignment(thirdDock1600, HPos.CENTER);
    gridpane.add(thirdDock1600, 2, 7);
          
    uppdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                firstDock0008.setVisible(true);
                firstDock0816.setVisible(true);
                firstDock1600.setVisible(true);
                secondDock0008.setVisible(true);
                secondDock0816.setVisible(true);
                secondDock1600.setVisible(true);
                thirdDock0008.setVisible(true);
                thirdDock0816.setVisible(true);
                thirdDock1600.setVisible(true);
                thirdDock.setVisible(true);
                secondDock.setVisible(true);
                firstDock.setVisible(true);
              
               bookingdate = datePicker.getValue().toString();
               
               
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 1) == 1){
                    firstDock0008.getStyleClass().add("action");
                }
                else{
                    firstDock0008.getStyleClass().remove("action");
                }
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 2) == 1){
                    firstDock0816.getStyleClass().add("action");
                }
                else{
                    firstDock0816.getStyleClass().remove("action");
                }
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 3) == 1){
                    firstDock1600.getStyleClass().add("action");
                }
                else{
                    firstDock1600.getStyleClass().remove("action");
                }
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 4) == 1){
                    secondDock0008.getStyleClass().add("action");
                }
                else{
                    
                }
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 5) == 1){
                    secondDock0816.getStyleClass().add("action");
                }
                else{
                    secondDock0008.getStyleClass().remove("action");
                }
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 6)==1){
                    secondDock1600.getStyleClass().add("action");
                }
                else{
                    secondDock1600.getStyleClass().remove("action");
                }
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 7) == 1){
                    thirdDock0008.getStyleClass().add("action");
                }
                else{
                    thirdDock0008.getStyleClass().remove("action");
                }
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 8) == 1){
                    thirdDock0816.getStyleClass().add("action");
                }
                else{
                    thirdDock0816.getStyleClass().remove("action");
                }
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 9) == 1){
                    thirdDock1600.getStyleClass().add("action");
                }
                else{
                    thirdDock1600.getStyleClass().remove("action");
                }

            }
        });
    
        firstDock0008.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 1) == 0){
                   bookingslot = 1;
                   time.setText("00-08");
                   bookingharbor ="101";
                   date.setText(datePicker.getValue().toString());
                   thestage.setScene(abookingscene);
                }
              }
            
        });
        firstDock0816.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 2) == 0){
                    bookingslot=2;
                    time.setText("08-16");
                    bookingharbor ="101";
                    date.setText(datePicker.getValue().toString());
                    thestage.setScene(abookingscene);
                }
              }
            
        });
        firstDock1600.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 3) == 0){
                    bookingslot=3;
                    time.setText("16-00");
                    bookingharbor ="101";
                    date.setText(datePicker.getValue().toString());
                    thestage.setScene(abookingscene);
                }
              }
            
        });
        secondDock0008.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 4) == 0){
                    bookingslot=4;
                    time.setText("00-08");
                    bookingharbor="201";
                    date.setText(datePicker.getValue().toString());
                    thestage.setScene(abookingscene);
                    
                }
              }
            
        });
        secondDock0816.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 5) == 0){
                    bookingslot=5;
                    time.setText("08-16");
                    bookingharbor="201";
                    date.setText(datePicker.getValue().toString());
                    thestage.setScene(abookingscene);
                }
              }
            
        });
        secondDock1600.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 6) == 0){
                    bookingslot=6;
                    time.setText("16-00");
                    bookingharbor="201";
                    date.setText(datePicker.getValue().toString());
                    thestage.setScene(abookingscene);
                }
              }
            
        });
        thirdDock0008.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 7) == 0){
                    bookingslot=7;
                    time.setText("00-08");
                    bookingharbor="301";
                    date.setText(datePicker.getValue().toString());
                    thestage.setScene(abookingscene);
                }
              }
            
        });
        thirdDock0816.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 8) == 0){
                    bookingslot=8;
                    time.setText("08-16");
                    bookingharbor="301";
                    date.setText(datePicker.getValue().toString());
                    thestage.setScene(abookingscene);
                }
              }
            
        });
        thirdDock1600.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 9) == 0){
                    bookingslot=9;
                    time.setText("16-00");
                    bookingharbor="301";
                    date.setText(datePicker.getValue().toString());
                    thestage.setScene(abookingscene);
                }
              }
            
        });
           return gridpane;
      }
      public VBox addChangeAPersonMenuBtns(){
        VBox changeARecoursePageVbox = new VBox(20);
        
        changeARecoursePageVbox.setAlignment(Pos.TOP_CENTER);
        changeARecoursePageVbox.setMaxWidth(200);
        changeARecoursePageVbox.setPadding(new Insets(15, 12, 15, 12));
        
        Label id = new Label("ID: "+Integer.toString(changeperson.getID()));
        FadeTransition fader = createFader(alterMessage);
                                SequentialTransition blinkThenFade = new SequentialTransition(
                                        alterMessage,
                                      
                                        fader
                                );
        
        TextField personFirstName = new TextField();
        TextField personLastName = new TextField();
        personFirstName.setText(changeperson.förnamn());
        personLastName.setText(changeperson.efternamn());
        
        ComboBox pdriverlicence = new ComboBox();
        pdriverlicence.getItems().addAll("A","AA","B","BB","C","CC","CCC","K");
        pdriverlicence.getSelectionModel().select(changeperson.körkort());
        
        ComboBox pstatus = new ComboBox();
        pstatus.getItems().addAll("100%","50%","Sjuk","VAB","Student","Semester");
        pstatus.getSelectionModel().select(changeperson.status());
        ComboBox pschedual = new ComboBox();
        pschedual.getItems().addAll("M-F","L-S","S");
        pschedual.getSelectionModel().select(changeperson.schema());
        
        Button save = new Button("Save");
        Button back = new Button("Back");
        back.setPrefSize(120, 20);
        save.setPrefSize(120, 20);
        changeARecoursePageVbox.getChildren().setAll(id, personFirstName,personLastName,pdriverlicence,pstatus,pschedual,save,back);
        
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeperson.setförnamn(personFirstName.getText());
                changeperson.setefternamn(personLastName.getText());
                changeperson.setkörkort(pdriverlicence.getSelectionModel().getSelectedItem().toString());
                changeperson.setstatus(pstatus.getSelectionModel().getSelectedItem().toString());
                changeperson.setschema(pschedual.getSelectionModel().getSelectedItem().toString());
                m.updatePerson(changeperson);
                persons = m.getAllPersons();
                
                oll.clear();
                 for (Person p : persons){
                            
                            oll.add(new ListPerson(p));
                            
                        }
                
                tablev.setItems(oll);
                blinkThenFade.play();
                thestage.setScene(alterAResourceScene);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                persons = m.getAllPersons();
                oll.clear();
                for (Person p : persons){
                            
                            oll.add(new ListPerson(p));
                            
                        }
                tablev.setItems(oll);
                thestage.setScene(alterAResourceScene);
            }
        });
        
        return changeARecoursePageVbox;
        
    }
      public VBox addChangeAShipMenuBtns(){
        VBox changeARecoursePageVbox = new VBox(20);
        
        changeARecoursePageVbox.setAlignment(Pos.TOP_CENTER);
        changeARecoursePageVbox.setMaxWidth(200);
        changeARecoursePageVbox.setPadding(new Insets(15, 12, 15, 12));
        
        Label id = new Label("ID: "+Integer.toString(changeship.getID()));
        
        
        TextField shipname = new TextField();
        
        shipname.setText(changeship.namn());
        
        FadeTransition fader = createFader(alterMessage);
                                SequentialTransition blinkThenFade = new SequentialTransition(
                                        alterMessage,
                                      
                                        fader
                                );
        ComboBox shipvolume = new ComboBox();
        shipvolume.getItems().addAll("A","AA","B","BB","C","CC","CCC","K");
        shipvolume.getSelectionModel().select(changeship.volymid());
        
        ComboBox shipCompany = new ComboBox();
        shipCompany.getItems().addAll("StenaLine", "FlyteTyg AB", "SiljaLine");
        shipCompany.getSelectionModel().select(changeship.bolag());
       
        Button save = new Button("Save");
        Button back = new Button("Back");
        back.setPrefSize(120, 20);
        save.setPrefSize(120, 20);
        changeARecoursePageVbox.getChildren().setAll(id, shipname,shipvolume,shipCompany,save,back);
        
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeship.setnamn(shipname.getText());
                
                changeship.setVolumeID(shipvolume.getSelectionModel().getSelectedItem().toString());
                changeship.setbolag(shipCompany.getSelectionModel().getSelectedItem().toString());
                
                m.updateShip(changeship);
                ships = m.getAllShip();
                oll.clear();
                for (Ship s : ships){
                            oll.add(new ListShip(s));
                        }
                tablev.setItems(oll);
                blinkThenFade.play();
                thestage.setScene(alterAResourceScene);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ships = m.getAllShip();
                oll.clear();
                for (Ship s : ships){
                            oll.add(new ListShip(s));
                        }
                tablev.setItems(oll);
                thestage.setScene(alterAResourceScene);
            }
        });
        
        return changeARecoursePageVbox;
        
    }
      public VBox addChangeATruckMenuBtns(){
        VBox changeARecoursePageVbox = new VBox(20);
        
        changeARecoursePageVbox.setAlignment(Pos.TOP_CENTER);
        changeARecoursePageVbox.setMaxWidth(200);
        changeARecoursePageVbox.setPadding(new Insets(15, 12, 15, 12));
        
        Label id = new Label("ID: "+Integer.toString(changetruck.getID()));
        
      
        ComboBox trucktyp = new ComboBox();
        trucktyp.getItems().addAll("A","AA","B","BB","C","CC","CCC","K");
        trucktyp.getSelectionModel().select(changetruck.gettrucktyp());
        
        ComboBox truckstatus = new ComboBox();
        truckstatus.getItems().addAll("Ok","Reparation","Reserv","Inaktiv");
        truckstatus.getSelectionModel().select(changetruck.gettruckstatus());
        
        FadeTransition fader = createFader(alterMessage);
                                SequentialTransition blinkThenFade = new SequentialTransition(
                                        alterMessage,
                                      
                                        fader
                                );
        
        Button save = new Button("Save");
        Button back = new Button("Back");
        back.setPrefSize(120, 20);
        save.setPrefSize(120, 20);
        changeARecoursePageVbox.getChildren().setAll(id, trucktyp,truckstatus,save,back);
        
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changetruck.settrucktyp(trucktyp.getSelectionModel().getSelectedItem().toString());
                changetruck.setstatus(truckstatus.getSelectionModel().getSelectedItem().toString());
                
                m.updateTruck(changetruck);
                trucks = m.getAllTrucks();
                oll.clear();
                for (Truck t : trucks){
                            
                            oll.add(new ListTruck(t));
                            
                        }
                tablev.setItems(oll);
                blinkThenFade.play();
                thestage.setScene(alterAResourceScene);
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                trucks = m.getAllTrucks();
                oll.clear();
                
                for (Truck t : trucks){
                            
                            oll.add(new ListTruck(t));
                            
                        }
                tablev.setItems(oll);
                thestage.setScene(alterAResourceScene);
            }
        });
        
        return changeARecoursePageVbox;
        
    }
      public VBox addABookingSceneMenuBtns(){
          VBox vbox = new VBox(20);
          vbox.setAlignment(Pos.TOP_CENTER);
          vbox.setMaxWidth(200);
          vbox.setPadding(new Insets(15, 12, 15, 12));
          
          date = new Label();
          time = new Label(bookingtime);
          
          tablev = new TableView();
     
        
        TableColumn shipid = new TableColumn("ID");
        shipid.setCellValueFactory(
                new PropertyValueFactory<>("shipid"));
        
        TableColumn shipname = new TableColumn("Ship Name");
        shipname.setCellValueFactory(
                new PropertyValueFactory<>("namn"));
        
        TableColumn shipowner = new TableColumn("Owner");
        shipowner.setCellValueFactory(
                new PropertyValueFactory<>("bolag"));
        
        TableColumn shipvolymid = new TableColumn("VolymID");
        shipvolymid.setCellValueFactory(
                new PropertyValueFactory<>("volymid"));
        
        oll = FXCollections.observableArrayList();
        
        tablev.getColumns().addAll(shipid,shipname,shipowner,shipvolymid);
                        oll.clear();
                        ships = m.getAllShip();
                        List<Integer> shipids = m.getShipIDFromBooking(date.getText());
                        for (Ship s : ships){
                            for(Integer i : shipids){
                                if(s.getID()==i){
                                    ships.remove(s);
                                }
                            }
                            oll.add(new ListShip(s));
                        }
                        
                        
                        tablev.setItems(oll);
          
          vbox.getChildren().setAll(date,time,tablev);
          return vbox;
      }
     
      private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(4), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }
    
}
