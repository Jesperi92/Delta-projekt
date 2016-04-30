/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delta.projekt;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.awt.Font;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.WeakEventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class DeltaProjekt extends Application {
    Stage thestage;
    Scene startScene, statisticsScene,dailyBookingScene,alterResourcersScene, addResourceScene, alterAResourceScene,changeARecourceScene, bookingChooseShipScene, bookingChoosePersonnelScene, bookingChooseTrucksScene ;
    Database m = new Database();
    Booking daybooking;
    List<Person> persons;
    List<Truck> trucks;
    List<Ship> ships,shipsstatistics;
    ArrayList<Truck> booktrucks = new ArrayList<Truck>();
    ArrayList<Person> bookpersons = new ArrayList<Person>();
    GridPane grid;
    ArrayList<Integer> personnelids = new ArrayList<Integer>();
    Person changeperson;
    Truck changetruck;
    Ship changeship, bookedship;
    ObservableList oll,olll;
    Label messages;
    TableView tablev,tablev2;
    Label alterMessage,date,time,harbor,daystattime,daystatharbor,daystatship,daystatdate;
    File f;
    String bookingdate, bookingtime, bookingharbor;
    int bookingslot, amountOfRecoursesToBook;
    DatePicker datePicker = new DatePicker();
    ObservableList candidates,selected;
    double rightammountofpersonnel = 0;
    Label personnelmsg,trucksmsg, firstDock, secondDock, thirdDock;
    FadeTransition faderrr;
    String personstext,wagetext,trucktext,truckfeetext;
    Button firstDock0816,firstDock0008,firstDock1600,secondDock0816,secondDock0008,secondDock1600,thirdDock0816,thirdDock0008,thirdDock1600;
    ArrayList<Line> lines = new ArrayList<Line>();
    ArrayList<Label> shipnamelist = new ArrayList<Label>();
    ArrayList<Label> personnelliststat = new ArrayList<Label>();
    ArrayList<Label> wagepersonnellist = new ArrayList<Label>();
    ArrayList<Label> truckidlist = new ArrayList<Label>();
    ArrayList<Label> truckidfeelist = new ArrayList<Label>();
    
    ArrayList<Label> shipnamelistlabel = new ArrayList<Label>();
    ArrayList<Label> personnelliststatlabel = new ArrayList<Label>();
    ArrayList<Label> wagepersonnellistlabel = new ArrayList<Label>();
    ArrayList<Label> truckidlistlabel = new ArrayList<Label>();
    ArrayList<Label> truckidfeelistlabel = new ArrayList<Label>();
    int total = 0;
    ArrayList<Label> totalfees = new ArrayList<Label>();
    
    ArrayList<GridPane> gridpaneforstat;
    int totalday;
        Label totalfeeday = new Label();
    VBox statisticsPageVbox;
    ArrayList<Person> thispersonlist;
    ArrayList<Truck> thistrucklist;
    Label statperson = new Label();
        Label stattruck = new Label();
        Label personwage = new Label();
        Label truckfee = new Label();
        Label statpersoncolumn = new Label("Namn");
        Label stattruckcolumn = new Label("Truck");
        Label personwagecolumn = new Label("Pris");
        Label truckfeecolumn = new Label("Pris");
    
    
     public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        
        thestage = primaryStage;
        Group root = new Group();
        ScrollBar s1 = new ScrollBar();
        root.getChildren().add(s1);
        //thestage.initStyle(StageStyle.UNDECORATED);
        startScene = new Scene(getStartPageBorderPane(), 500, 600);
        statisticsScene = new Scene(getStatisticsPageBorderPane(), 500, 600);
        try {
            dailyBookingScene = new Scene(getDailyBookingBorderPane(), 500, 600);
        } catch (Throwable ex) {
            Logger.getLogger(DeltaProjekt.class.getName()).log(Level.SEVERE, null, ex);
        }
        alterResourcersScene = new Scene(getAlterResourcesPageBorderPane(), 500, 600);
        addResourceScene = new Scene(getAddRecourseBorderPane(), 500, 600);
        alterAResourceScene = new Scene(getAlterAResourceBorderPane(), 500, 600);
        try {
            bookingChooseShipScene = new Scene(getBookingChooseShipSceneBorderPane(),500,600 );
        } catch (Throwable ex) {
            Logger.getLogger(DeltaProjekt.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bookingChoosePersonnelScene = new Scene(getBookingChoosePersonnelSceneBorderPane(),500,600 );
        } catch (Throwable ex) {
            Logger.getLogger(DeltaProjekt.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bookingChooseTrucksScene = new Scene(getBookingChooseTrucksSceneBorderPane(),500,600 );
        } catch (Throwable ex) {
            Logger.getLogger(DeltaProjekt.class.getName()).log(Level.SEVERE, null, ex);
        }
        f = new File("color.css");
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
        bookingChooseShipScene.getStylesheets().clear();
        bookingChooseShipScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        bookingChoosePersonnelScene.getStylesheets().clear();
        bookingChoosePersonnelScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        bookingChooseTrucksScene.getStylesheets().clear();
        bookingChooseTrucksScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        
        primaryStage.setTitle("Botes!");
        primaryStage.setScene(startScene);
        primaryStage.show();
        
    }
    public BorderPane getBookingChoosePersonnelSceneBorderPane() throws Throwable{
        BorderPane bookingChoosePersonnelSceneBorderPane = new BorderPane();
        bookingChoosePersonnelSceneBorderPane.setCenter(addBookingChoosePersonnelMenuBtns());
        
        return bookingChoosePersonnelSceneBorderPane;
    }
    public BorderPane getBookingChooseTrucksSceneBorderPane() throws Throwable{
        BorderPane bookingChooseTrucksSceneBorderPane = new BorderPane();
        bookingChooseTrucksSceneBorderPane.setCenter(addBookingChooseTrucksMenuBtns());
        
        return bookingChooseTrucksSceneBorderPane;
    }
    public BorderPane getBookingChooseShipSceneBorderPane() throws Throwable{
        BorderPane bookingChooseShipSceneBorderPane = new BorderPane();
        bookingChooseShipSceneBorderPane.setCenter(addBookingChooseShipMenuBtns());
        
        return bookingChooseShipSceneBorderPane;
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
    public BorderPane getDailyBookingBorderPane() throws Throwable{
        BorderPane dailyBookingBorderPane = new BorderPane();
        dailyBookingBorderPane.setCenter(addDailyBookingMenuBtns());
        
        
        
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
        
        return starPageBorderPane;
    }
    public BorderPane getStatisticsPageBorderPane(){
        BorderPane statisticsPageBorderPane = new BorderPane();
        
        
        statisticsPageBorderPane.setCenter(addStatisticsMenuBtns());
        return statisticsPageBorderPane ;
    }
    
    public BorderPane getAlterResourcesPageBorderPane(){
        BorderPane alterResourcersPageBorderPane = new BorderPane();
        alterResourcersPageBorderPane.setCenter(addAlterResourcesMenuBtns());
        return alterResourcersPageBorderPane;
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
        Button quitBtn = new Button("Quit");
        quitBtn.setPrefSize(100, 20);
        quitBtn.setOnAction(e -> thestage.close());
        
        goToStatisticsBtn.setOnAction(e -> thestage.setScene(statisticsScene));
        goToDailyBookingBtn.setOnAction(e -> thestage.setScene(dailyBookingScene));
        goToAlterResourcesBtn.setOnAction(e -> thestage.setScene(alterResourcersScene));
        
        startPageVbox.getChildren().addAll(goToStatisticsBtn,goToDailyBookingBtn,goToAlterResourcesBtn,quitBtn);
        
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
        Button goBackBtn = new Button("Back");
        goBackBtn.setPrefSize(140, 20);
        goBackBtn.setOnAction(e -> thestage.setScene(startScene));
        
        alterResourcesVbox.getChildren().addAll(addNewResourcesBtn, alterAResourceBtn,goBackBtn);
        
        
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
                
                    if(comboBox.getSelectionModel().getSelectedItem() != null){
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
                comboBox.getSelectionModel().clearSelection();
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
        
        final ChoiceBox comboBox = new ChoiceBox();
        
        comboBox.getItems().addAll(
        "Person",
        "Truck",
        "Ship"
        );
        
        vbox.getChildren().addAll(comboBox,back);
          comboBox.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(comboBox.getSelectionModel().getSelectedItem() != null){
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
            }
        });
          back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox.getChildren().clear();
                comboBox.getSelectionModel().clearSelection();
                vbox.getChildren().addAll(comboBox,back);
                
                thestage.setScene(alterResourcersScene);
            }
        });
       
        return vbox;
    }
      public VBox addStatisticsMenuBtns(){
        VBox stat = new VBox(20);
        stat.setAlignment(Pos.TOP_CENTER);
        stat.setMaxWidth(400);
        stat.setPadding(new Insets(15, 12, 15, 12));
        ScrollPane scroll = new ScrollPane(stat);
        scroll.setPrefViewportWidth(400);
        
        statisticsPageVbox = new VBox(20);
        statisticsPageVbox.setAlignment(Pos.TOP_CENTER);
        statisticsPageVbox.setMaxWidth(400);
        statisticsPageVbox.setPadding(new Insets(15, 12, 15, 12));
        persons = new ArrayList<Person>();
        trucks = new ArrayList<Truck>();
        DatePicker datePicker2 = new DatePicker();
        datePicker2.setMaxWidth(120);
        daystatharbor = new Label();
        daystatharbor.setAlignment(Pos.TOP_LEFT);
        ScrollPane scrollday = new ScrollPane();
        scrollday.setPrefWidth(300);
        daystatship = new Label();
        daystatship.setAlignment(Pos.TOP_LEFT);
        daystattime = new Label();
        daystattime.setAlignment(Pos.TOP_LEFT);
        daystatdate = new Label();
        daystatdate.setAlignment(Pos.TOP_LEFT);
        Line sträck = new Line(0,0,300,0);
        Line sträck2 = new Line(0,0,300,0);
        ChoiceBox choicebox = new ChoiceBox();
        choicebox.getItems().addAll("Day Rapport", "Week Rapport");
        VBox daystat = new VBox(20);
        
        daystat.setAlignment(Pos.TOP_CENTER);
        daystat.setMaxWidth(300);
        Button back = new Button("Back");
        back.setPrefSize(120, 20);
        Button next = new Button(">");
        next.setPrefSize(120, 20);
        Button backpointer = new Button("<");
        backpointer.setPrefSize(120, 20);
        Label weekLabel = new Label("Week:");
        Label dateLabel = new Label("Date:");
        Label shipLabel = new Label("Ship:");
        
        ComboBox week = new ComboBox();
        
        for(int i = 1; i <=52; i++){
            week.getItems().add(i);
        }
        TextField date = new TextField();
        
        
        TableView daytableview = new TableView();
        daytableview.setMaxWidth(250);
        
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
        
        
        
        daytableview.getColumns().addAll(shipid,shipname,shipowner,shipvolymid);
        ObservableList tvships = FXCollections.observableArrayList();
       statisticsPageVbox.getChildren().addAll(choicebox,back);
        
       choicebox.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(choicebox.getSelectionModel().getSelectedItem() != null){
                if(choicebox.getSelectionModel().getSelectedItem().toString().equals("Day Rapport")){
                    
                   statisticsPageVbox.getChildren().clear();
                   statisticsPageVbox.getChildren().addAll(choicebox,dateLabel,datePicker2,back);
                }
                else if(choicebox.getSelectionModel().getSelectedItem().toString().equals("Week Rapport")){
                   statisticsPageVbox.getChildren().clear();
                   statisticsPageVbox.getChildren().addAll(choicebox,weekLabel,week,back);
                }
            }
            }
        });
         back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                statisticsPageVbox.getChildren().clear();
                statisticsPageVbox.getChildren().addAll(choicebox,back);
                        choicebox.getSelectionModel().clearSelection();
                thestage.setScene(startScene);
            }
        });
        datePicker2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                statisticsPageVbox.getChildren().clear();
                shipsstatistics = null;
                tvships.clear();
                shipsstatistics = m.getAllShipForStatistics(datePicker2.getValue().toString());
                for(Ship s : shipsstatistics)
                    tvships.add(new ListShip(s));
                daytableview.setItems(tvships);
                statisticsPageVbox.getChildren().addAll(choicebox,dateLabel,datePicker2,back,next,shipLabel,daytableview);
            }
        });
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                statisticsPageVbox.getChildren().removeAll(next,shipLabel,daytableview);
                Ship selected = shipsstatistics.get(daytableview.getSelectionModel().getSelectedIndex());
                daystatship.setText(selected.namn());
                totalday = 0;
                daybooking = m.getBooking(datePicker2.getValue().toString(), selected.getID());
                daystatdate.setText(daybooking.getDate());
                System.out.println(daybooking);
                if(daybooking.getSlot() == 1 ||daybooking.getSlot() == 4 ||daybooking.getSlot() == 7)
                    daystattime.setText("00-08");
                else if(daybooking.getSlot() == 2 ||daybooking.getSlot() == 5 ||daybooking.getSlot() == 8)
                    daystattime.setText("08-16");
                else if(daybooking.getSlot() == 3 ||daybooking.getSlot() == 6 ||daybooking.getSlot() == 9)
                    daystattime.setText("16-00");
                
                if(daybooking.getSlot() == 1 ||daybooking.getSlot() == 2 ||daybooking.getSlot() == 3)
                    daystatharbor.setText("K101");
                else if(daybooking.getSlot() == 4 ||daybooking.getSlot() == 5 ||daybooking.getSlot() == 6)
                    daystatharbor.setText("K201");
                else if(daybooking.getSlot() == 7 ||daybooking.getSlot() == 8 ||daybooking.getSlot() == 9)
                    daystatharbor.setText("K301");
                    
                for(Integer i : daybooking.getPersonid())
                    persons.add(m.getAllPersonsForStat(i));
                
                for (Integer i : daybooking.getTruckid())
                    trucks.add(m.getAllTrucksForStat(i));
                
                daybooking.setPersons(persons);
                daybooking.setTrucks(trucks);
                personstext = "";
                grid = new GridPane();
                grid.prefWidth(300);
                grid.setHgap(20);
                grid.add(statpersoncolumn, 0, 0);
                grid.add(personwagecolumn, 1, 0);
                grid.add(stattruckcolumn, 2, 0);
                grid.add(truckfeecolumn, 3, 0);
                for(Person p : persons)
                    personstext = personstext + (p.förnamn()+","+p.efternamn()+"\n");
                
                statperson.setText(personstext);
                grid.add(statperson, 0, 1);
                GridPane.setValignment(statperson, VPos.TOP);
                wagetext = "";
               
                for(Person p : persons)
                    if(p.status().equals("100%")){
                        totalday = totalday +(Integer.parseInt(p.getwage())*8);
                    wagetext = wagetext +((Integer.parseInt(p.getwage())*8)+"\n");
                    }
                    else if(p.status().equals("50%")){
                        totalday = totalday +(Integer.parseInt(p.getwage())*4);
                        wagetext = wagetext +((Integer.parseInt(p.getwage())*4)+"\n");
                    }
                personwage.setText(wagetext);
                grid.add(personwage, 1, 1);
                GridPane.setValignment(personwage, VPos.TOP);
                trucktext = "";
                
                for(Truck t : trucks)
                    trucktext = trucktext + (t.getID()+"\n");
                
                stattruck.setText(trucktext);
                grid.add(stattruck, 2, 1);
                GridPane.setValignment(stattruck, VPos.TOP);
                truckfeetext = "";
               
                for(Truck t : trucks){
                    totalday = totalday +(t.gettruckfee()*8);
                    truckfeetext = truckfeetext +((t.gettruckfee()*8)+"\n");
                }
                truckfee.setText(truckfeetext);
                grid.add(truckfee, 3, 1);
                GridPane.setValignment(truckfee, VPos.TOP);
                totalfeeday.setText("Total: "+totalday);
                daystat.getChildren().addAll(daystatdate,daystattime,daystatharbor,daystatship,sträck,grid,sträck2,totalfeeday);
                scrollday.setContent(daystat);
                
                statisticsPageVbox.getChildren().addAll(backpointer,scrollday);
                
            }
        });
        backpointer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                daystat.getChildren().clear();
                persons.clear();
                trucks.clear();
                totalday = 0;
                statisticsPageVbox.getChildren().removeAll(backpointer,scrollday);
                statisticsPageVbox.getChildren().addAll(next,shipLabel,daytableview);
                
            }
        });
        week.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                total = 0;
                totalfees.clear();
                gridpaneforstat = new ArrayList<GridPane>();
                shipnamelist.clear();
                personnelliststat.clear();
                wagepersonnellist.clear();
                truckidlist.clear();
                truckidfeelist.clear();
                shipnamelistlabel.clear();
                personnelliststatlabel.clear();
                wagepersonnellistlabel.clear();
                truckidlistlabel.clear();
                truckidfeelistlabel.clear();
                stat.getChildren().clear();
                statisticsPageVbox.getChildren().clear();
                statisticsPageVbox.getChildren().addAll(choicebox,weekLabel,week,back);
                ArrayList<String> dates = new ArrayList<String>();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.WEEK_OF_YEAR, week.getSelectionModel().getSelectedIndex());        
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    dates.add(format1.format(cal.getTime()));
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                    dates.add(format1.format(cal.getTime()));
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                    dates.add(format1.format(cal.getTime()));
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                    dates.add(format1.format(cal.getTime()));
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                    dates.add(format1.format(cal.getTime()));
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                    dates.add(format1.format(cal.getTime()));
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                    dates.add(format1.format(cal.getTime()));
                    List<Booking> bookings = m.getBookingList(dates);
                    Label dateslabel = new Label(dates.get(0)+" - "+dates.get(dates.size()-1));
                    stat.getChildren().add(dateslabel);
                    Label kaj1 = new Label("K101");
                    Label kaj2 = new Label("K201");
                    Label kaj3 = new Label("K301");
                    lines.add(new Line(0,0,300,0));
                    if(bookings.size() != 0){
                    stat.getChildren().add(kaj1);
                    stat.getChildren().add(lines.get(lines.size()-1));
                    for(Booking b : bookings){
                       thispersonlist = new ArrayList<Person>();
                       thistrucklist = new ArrayList<Truck>();
                        b.setRealship(m.getAllShipForStatisticsWeekList(b.getShip()));
                        for(int i : b.getPersonid()){
                            thispersonlist.add(m.getAllPersonsForStat(i));
                        }
                        for(int i : b.getTruckid()){
                            thistrucklist.add(m.getAllTrucksForStat(i));
                        }
                        b.setPersons(thispersonlist);
                        b.setTrucks(thistrucklist);
                    }
                    
                    
                    for(Booking b : bookings){
                        if(b.getSlot() == 1 || b.getSlot() == 2 || b.getSlot() == 3){
                            total = 0;
                            personstext = "";
                            wagetext = "";
                            trucktext = "";
                            truckfeetext = "";
                            shipnamelist.add(new Label("Ship: "+b.getRealship().namn()));
                            stat.getChildren().add(shipnamelist.get(shipnamelist.size()-1));
                            GridPane tempgrid = new GridPane();
                            grid = new GridPane();
                tempgrid.prefWidth(300);
                tempgrid.setHgap(20);
                personnelliststatlabel.add((new Label("Personal")));
                wagepersonnellistlabel.add(new Label("Pris"));
                truckidlistlabel.add(new Label("Truck"));
                truckidfeelistlabel.add(new Label("Pris"));
                tempgrid.add(personnelliststatlabel.get(personnelliststatlabel.size()-1), 0, 0);
                tempgrid.add(wagepersonnellistlabel.get(wagepersonnellistlabel.size()-1), 1, 0);
                tempgrid.add(truckidlistlabel.get(truckidlistlabel.size()-1), 2, 0);
                tempgrid.add(truckidfeelistlabel.get(truckidfeelistlabel.size()-1), 3, 0);
                for(Person p : b.getPersons())
                    personstext = personstext + (p.förnamn()+","+p.efternamn()+"\n");
                
                personnelliststat.add(new Label(personstext));
                tempgrid.add(personnelliststat.get(personnelliststat.size()-1), 0, 1);
                GridPane.setValignment(personnelliststat.get(personnelliststat.size()-1), VPos.TOP);
                wagetext = "";
               
                for(Person p : b.getPersons())
                    if(p.status().equals("100%")){
                        total = total + (Integer.parseInt(p.getwage())*8);
                    wagetext = wagetext +((Integer.parseInt(p.getwage())*8)+"\n");
                    }
                    else if(p.status().equals("50%")){
                        total = total + (Integer.parseInt(p.getwage())*4);
                        wagetext = wagetext +((Integer.parseInt(p.getwage())*4)+"\n");
                    }
                wagepersonnellist.add(new Label(wagetext));
                tempgrid.add(wagepersonnellist.get(wagepersonnellist.size()-1), 1, 1);
                GridPane.setValignment(wagepersonnellist.get(wagepersonnellist.size()-1), VPos.TOP);
                trucktext = "";
                
                for(Truck t : b.getTrucks())
                    trucktext = trucktext + (t.getID()+"\n");
                
                truckidlist.add(new Label(trucktext));
                tempgrid.add(truckidlist.get(truckidlist.size()-1), 2, 1);
                GridPane.setValignment(truckidlist.get(truckidlist.size()-1), VPos.TOP);
                truckfeetext = "";
               
                for(Truck t : b.getTrucks()){
                    total = total + (t.gettruckfee()*8);
                    truckfeetext = truckfeetext +((t.gettruckfee()*8)+"\n");
                }
                truckidfeelist.add(new Label(truckfeetext));
                tempgrid.add(truckidfeelist.get(truckidfeelist.size()-1), 3, 1);
                GridPane.setValignment(truckidfeelist.get(truckidfeelist.size()-1), VPos.TOP);
                
                        totalfees.add(new Label("\nTotal: "+total));
                        tempgrid.add(totalfees.get(totalfees.size()-1), 1, 3);
                        GridPane.setValignment(totalfees.get(totalfees.size()-1), VPos.TOP);
                        gridpaneforstat.add(tempgrid);
                        stat.getChildren().add(gridpaneforstat.get(gridpaneforstat.size()-1));
                        }
                        
                    }
                    
                    
                    lines.add(new Line(0,0,300,0));
                    stat.getChildren().add(lines.get(lines.size()-1));
                    stat.getChildren().add(kaj2);
                    lines.add(new Line(0,0,300,0));
                    stat.getChildren().add(lines.get(lines.size()-1));
                    for(Booking b : bookings){
                        if(b.getSlot() == 4 || b.getSlot() == 5 || b.getSlot() == 6){
                             total = 0;
                            personstext = "";
                            wagetext = "";
                            trucktext = "";
                            truckfeetext = "";
                            shipnamelist.add(new Label("Ship: "+b.getRealship().namn()));
                            stat.getChildren().add(shipnamelist.get(shipnamelist.size()-1));
                            GridPane tempgrid = new GridPane();
                            grid = new GridPane();
                tempgrid.prefWidth(300);
                tempgrid.setHgap(20);
                personnelliststatlabel.add((new Label("Personal")));
                wagepersonnellistlabel.add(new Label("Pris"));
                truckidlistlabel.add(new Label("Truck"));
                truckidfeelistlabel.add(new Label("Pris"));
                tempgrid.add(personnelliststatlabel.get(personnelliststatlabel.size()-1), 0, 0);
                tempgrid.add(wagepersonnellistlabel.get(wagepersonnellistlabel.size()-1), 1, 0);
                tempgrid.add(truckidlistlabel.get(truckidlistlabel.size()-1), 2, 0);
                tempgrid.add(truckidfeelistlabel.get(truckidfeelistlabel.size()-1), 3, 0);
                for(Person p : b.getPersons())
                    personstext = personstext + (p.förnamn()+","+p.efternamn()+"\n");
                
                personnelliststat.add(new Label(personstext));
                tempgrid.add(personnelliststat.get(personnelliststat.size()-1), 0, 1);
                GridPane.setValignment(personnelliststat.get(personnelliststat.size()-1), VPos.TOP);
                wagetext = "";
               
                for(Person p : b.getPersons())
                    if(p.status().equals("100%")){
                        total = total + (Integer.parseInt(p.getwage())*8);
                    wagetext = wagetext +((Integer.parseInt(p.getwage())*8)+"\n");
                    }
                    else if(p.status().equals("50%")){
                        total = total + (Integer.parseInt(p.getwage())*4);
                        wagetext = wagetext +((Integer.parseInt(p.getwage())*4)+"\n");
                    }
                wagepersonnellist.add(new Label(wagetext));
                tempgrid.add(wagepersonnellist.get(wagepersonnellist.size()-1), 1, 1);
                GridPane.setValignment(wagepersonnellist.get(wagepersonnellist.size()-1), VPos.TOP);
                trucktext = "";
                
                for(Truck t : b.getTrucks())
                    trucktext = trucktext + (t.getID()+"\n");
                
                truckidlist.add(new Label(trucktext));
                tempgrid.add(truckidlist.get(truckidlist.size()-1), 2, 1);
                GridPane.setValignment(truckidlist.get(truckidlist.size()-1), VPos.TOP);
                truckfeetext = "";
               
                for(Truck t : b.getTrucks()){
                    total = total + (t.gettruckfee()*8);
                    truckfeetext = truckfeetext +((t.gettruckfee()*8)+"\n");
                }
                truckidfeelist.add(new Label(truckfeetext));
                tempgrid.add(truckidfeelist.get(truckidfeelist.size()-1), 3, 1);
                GridPane.setValignment(truckidfeelist.get(truckidfeelist.size()-1), VPos.TOP);
                
                        totalfees.add(new Label("\nTotal: "+total));
                        tempgrid.add(totalfees.get(totalfees.size()-1), 1, 3);
                        GridPane.setValignment(totalfees.get(totalfees.size()-1), VPos.TOP);
                        gridpaneforstat.add(tempgrid);
                        stat.getChildren().add(gridpaneforstat.get(gridpaneforstat.size()-1));
                        }
                        
                    }
                    
                    
                    lines.add(new Line(0,0,300,0));
                    stat.getChildren().add(lines.get(lines.size()-1));
                    stat.getChildren().add(kaj3);
                    lines.add(new Line(0,0,300,0));
                    stat.getChildren().add(lines.get(lines.size()-1));
                    for(Booking b : bookings){
                        if(b.getSlot() == 7 || b.getSlot() == 8 || b.getSlot() == 9){
                           total = 0;
                            personstext = "";
                            wagetext = "";
                            trucktext = "";
                            truckfeetext = "";
                            shipnamelist.add(new Label("Ship: "+b.getRealship().namn()));
                            stat.getChildren().add(shipnamelist.get(shipnamelist.size()-1));
                            GridPane tempgrid = new GridPane();
                            grid = new GridPane();
                tempgrid.prefWidth(300);
                tempgrid.setHgap(20);
                personnelliststatlabel.add((new Label("Personal")));
                wagepersonnellistlabel.add(new Label("Pris"));
                truckidlistlabel.add(new Label("Truck"));
                truckidfeelistlabel.add(new Label("Pris"));
                tempgrid.add(personnelliststatlabel.get(personnelliststatlabel.size()-1), 0, 0);
                tempgrid.add(wagepersonnellistlabel.get(wagepersonnellistlabel.size()-1), 1, 0);
                tempgrid.add(truckidlistlabel.get(truckidlistlabel.size()-1), 2, 0);
                tempgrid.add(truckidfeelistlabel.get(truckidfeelistlabel.size()-1), 3, 0);
                for(Person p : b.getPersons())
                    personstext = personstext + (p.förnamn()+","+p.efternamn()+"\n");
                
                personnelliststat.add(new Label(personstext));
                tempgrid.add(personnelliststat.get(personnelliststat.size()-1), 0, 1);
                GridPane.setValignment(personnelliststat.get(personnelliststat.size()-1), VPos.TOP);
                wagetext = "";
               
                for(Person p : b.getPersons())
                    if(p.status().equals("100%")){
                        total = total + (Integer.parseInt(p.getwage())*8);
                    wagetext = wagetext +((Integer.parseInt(p.getwage())*8)+"\n");
                    }
                    else if(p.status().equals("50%")){
                        total = total + (Integer.parseInt(p.getwage())*4);
                        wagetext = wagetext +((Integer.parseInt(p.getwage())*4)+"\n");
                    }
                wagepersonnellist.add(new Label(wagetext));
                tempgrid.add(wagepersonnellist.get(wagepersonnellist.size()-1), 1, 1);
                GridPane.setValignment(wagepersonnellist.get(wagepersonnellist.size()-1), VPos.TOP);
                trucktext = "";
                
                for(Truck t : b.getTrucks())
                    trucktext = trucktext + (t.getID()+"\n");
                
                truckidlist.add(new Label(trucktext));
                tempgrid.add(truckidlist.get(truckidlist.size()-1), 2, 1);
                GridPane.setValignment(truckidlist.get(truckidlist.size()-1), VPos.TOP);
                truckfeetext = "";
               
                for(Truck t : b.getTrucks()){
                    total = total + (t.gettruckfee()*8);
                    truckfeetext = truckfeetext +((t.gettruckfee()*8)+"\n");
                }
                truckidfeelist.add(new Label(truckfeetext));
                tempgrid.add(truckidfeelist.get(truckidfeelist.size()-1), 3, 1);
                GridPane.setValignment(truckidfeelist.get(truckidfeelist.size()-1), VPos.TOP);
                
                        totalfees.add(new Label("\nTotal: "+total));
                        tempgrid.add(totalfees.get(totalfees.size()-1), 1, 3);
                        GridPane.setValignment(totalfees.get(totalfees.size()-1), VPos.TOP);
                        gridpaneforstat.add(tempgrid);
                        stat.getChildren().add(gridpaneforstat.get(gridpaneforstat.size()-1));
                        }
                        
                    }
                    } 
                    
                   statisticsPageVbox.getChildren().add(scroll);
                   
            }
            
        });
        
        
        return statisticsPageVbox;
        
    }
      public GridPane addDailyBookingMenuBtns(){
          
          messages = new Label();
          messages.setVisible(false);
          faderrr = createFader(messages);
                                SequentialTransition blinkThenFade = new SequentialTransition(
                                        messages,
                                      
                                        faderrr
                                );
          datePicker.setPrefSize(120, 20);
          GridPane gridpane = new GridPane();
          Button uppdate = new Button("Uppdate");
          uppdate.setPrefSize(120, 20);
          
          Button goBackBtn = new Button("Back");
            goBackBtn.setPrefSize(120, 20);
            goBackBtn.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  firstDock.setVisible(false);
                    secondDock.setVisible(false);
                    thirdDock.setVisible(false);
                    firstDock0008.setVisible(false);
                    firstDock0816.setVisible(false);
                    firstDock1600.setVisible(false);
                    secondDock0008.setVisible(false);
                    secondDock0816.setVisible(false);
                    secondDock1600.setVisible(false);
                    thirdDock0008.setVisible(false);
                    thirdDock0816.setVisible(false);
                    thirdDock1600.setVisible(false);
                  
    // First name label
    
    
   
   /* firstDock0816.getStyleClass().remove("action");
    firstDock1600.getStyleClass().remove("action");
    firstDock0008.getStyleClass().remove("action");
    secondDock0816.getStyleClass().remove("action");
    secondDock0816.getStyleClass().remove("action");
    secondDock0816.getStyleClass().remove("action");
    thirdDock0816.getStyleClass().remove("action");
    firstDock0816.getStyleClass().remove("action");
    firstDock0816.getStyleClass().remove("action");*/
    
                  thestage.setScene(startScene);
              }
          });
          
          firstDock = new Label("Dock 101");
          
          secondDock = new Label("Dock 201");
          
          thirdDock = new Label("Dock 301");
                    
                        
          firstDock0816 = new Button("08-16");
          firstDock0816.setPrefSize(60, 50);
                    

          firstDock1600 = new Button("16-00");
          firstDock1600.setPrefSize(60, 50);
                    

          firstDock0008 = new Button("00-08");
          firstDock0008.setPrefSize(60, 50);
                   

          secondDock0816 = new Button("08-16");
          secondDock0816.setPrefSize(60, 50);
                    

          secondDock1600 = new Button("16-00");
          secondDock1600.setPrefSize(60, 50);
                    

          secondDock0008 = new Button("00-08");
          secondDock0008.setPrefSize(60, 50);
                    

          thirdDock0816 = new Button("08-16");
          thirdDock0816.setPrefSize(60, 50);
                    

          thirdDock1600 = new Button("16-00");
          thirdDock1600.setPrefSize(60, 50);
                    

          thirdDock0008 = new Button("00-08");
          thirdDock0008.setPrefSize(60, 50);
                    

          
          gridpane.setAlignment(Pos.TOP_CENTER);
          
    gridpane.setPadding(new Insets(5));
    gridpane.setHgap(20);
    gridpane.setVgap(20);
   //ColumnConstraints column1 = new ColumnConstraints(100);
    //ColumnConstraints column2 = new ColumnConstraints(100);
    //column2.setHgrow(Priority.ALWAYS);
    //gridpane.getColumnConstraints().addAll(column1, column2);

    GridPane.setHalignment(messages, HPos.CENTER);
    gridpane.add(messages, 1, 0);
    GridPane.setHalignment(datePicker, HPos.CENTER);
    gridpane.add(datePicker, 1, 1);
    GridPane.setHalignment(uppdate, HPos.CENTER);
    gridpane.add(uppdate, 1, 2);
    // First name label
    
                GridPane.setHalignment(firstDock, HPos.CENTER);
    gridpane.add(firstDock, 1, 4);
    firstDock.setVisible(false);

    // Last name label
    GridPane.setHalignment(firstDock0008, HPos.CENTER);
    gridpane.add(firstDock0008, 0, 5);
    firstDock0008.setVisible(false);

    // First name field
    GridPane.setHalignment(firstDock0816, HPos.CENTER);
    gridpane.add(firstDock0816, 1, 5);
    firstDock0816.setVisible(false);

    // Last name field
    GridPane.setHalignment(firstDock1600, HPos.CENTER);
    gridpane.add(firstDock1600, 2, 5);
    firstDock1600.setVisible(false);

    // Save button
    GridPane.setHalignment(secondDock, HPos.CENTER);
    gridpane.add(secondDock, 1, 6);
    secondDock.setVisible(false);
    GridPane.setHalignment(secondDock0008, HPos.CENTER);
    gridpane.add(secondDock0008, 0, 7);
    secondDock0008.setVisible(false);
    GridPane.setHalignment(secondDock0816, HPos.CENTER);
    gridpane.add(secondDock0816, 1, 7);
    secondDock0816.setVisible(false);
    GridPane.setHalignment(secondDock1600, HPos.CENTER);
    gridpane.add(secondDock1600, 2, 7);
    secondDock1600.setVisible(false);
    GridPane.setHalignment(thirdDock, HPos.CENTER);
    gridpane.add(thirdDock, 1, 8);
    thirdDock.setVisible(false);
    GridPane.setHalignment(thirdDock0008, HPos.CENTER);
    gridpane.add(thirdDock0008, 0, 9);
    thirdDock0008.setVisible(false);
    GridPane.setHalignment(thirdDock0816, HPos.CENTER);
    gridpane.add(thirdDock0816, 1, 9);
    thirdDock0816.setVisible(false);
    GridPane.setHalignment(thirdDock1600, HPos.CENTER);
    gridpane.add(thirdDock1600, 2, 9);
    GridPane.setHalignment(goBackBtn, HPos.CENTER);
    thirdDock1600.setVisible(false);
    
    GridPane.setHalignment(goBackBtn, HPos.CENTER);
    gridpane.add(goBackBtn, 1, 3);
    
    uppdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(datePicker.getValue() == null){
                    firstDock.setVisible(false);
                    secondDock.setVisible(false);
                    thirdDock.setVisible(false);
                    firstDock0008.setVisible(false);
                    firstDock0816.setVisible(false);
                    firstDock1600.setVisible(false);
                    secondDock0008.setVisible(false);
                    secondDock0816.setVisible(false);
                    secondDock1600.setVisible(false);
                    thirdDock0008.setVisible(false);
                    thirdDock0816.setVisible(false);
                    thirdDock1600.setVisible(false);
                    messages.setText("Select a date!");
                    messages.setVisible(true);
                    blinkThenFade.play();
                }
                else{
                    firstDock.setVisible(true);
                    secondDock.setVisible(true);
                    thirdDock.setVisible(true);
                    firstDock0008.setVisible(true);
                    firstDock0816.setVisible(true);
                    firstDock1600.setVisible(true);
                    secondDock0008.setVisible(true);
                    secondDock0816.setVisible(true);
                    secondDock1600.setVisible(true);
                    thirdDock0008.setVisible(true);
                    thirdDock0816.setVisible(true);
                    thirdDock1600.setVisible(true);
                    
                    
                    
    
    
              
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
                    secondDock0008.getStyleClass().remove("action");
                }
                if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 5) == 1){
                    secondDock0816.getStyleClass().add("action");
                }
                else{
                    secondDock0816.getStyleClass().remove("action");
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
            }
        });
    
        firstDock0008.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                  
                  LocalDate date2 = datePicker.getValue();
                  System.out.println();
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                    
                  System.out.println(date2.getDayOfWeek());
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 1) == 0){
                   bookingslot = 1;
                   time.setText("00-08");
                   harbor.setText("Habor: 101");
                   date.setText(datePicker.getValue().toString());
                   ships = m.getAllShipBooking(date.getText());
                    
                    
                    for(Ship s : ships){
                        if(s.volymid().equals("A")||s.volymid().equals("AA"))
                        olll.add(new ListShip(s));
                    }
                   thestage.setScene(bookingChooseShipScene);
                }
                 
              }
            
        });
        firstDock0816.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 2) == 0){
                    bookingslot=2;
                    time.setText("08-16");
                    harbor.setText("Habor: 101");
                    date.setText(datePicker.getValue().toString());
                    ships = m.getAllShipBooking(date.getText());
                    
                    
                    for(Ship s : ships){
                        if(s.volymid().equals("A")||s.volymid().equals("AA"))
                        olll.add(new ListShip(s));
                    }
                    thestage.setScene(bookingChooseShipScene);
                }
              }
            
        });
        firstDock1600.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 3) == 0){
                    bookingslot=3;
                    time.setText("16-00");
                    harbor.setText("Habor: 101");
                    date.setText(datePicker.getValue().toString());
                    ships = m.getAllShipBooking(date.getText());
                    
                    
                    for(Ship s : ships){
                        if(s.volymid().equals("A")||s.volymid().equals("AA"))
                        olll.add(new ListShip(s));
                    }
                    thestage.setScene(bookingChooseShipScene);
                }
              }
            
        });
        secondDock0008.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 4) == 0){
                    bookingslot=4;
                    time.setText("00-08");
                    harbor.setText("Habor: 201");
                    date.setText(datePicker.getValue().toString());
                    ships = m.getAllShipBooking(date.getText());
                    
                    
                    for(Ship s : ships){
                        if(s.volymid().equals("B")||s.volymid().equals("BB")||s.volymid().equals("C"))
                        olll.add(new ListShip(s));
                    }
                    
                    thestage.setScene(bookingChooseShipScene);
                    
                }
              }
            
        });
        secondDock0816.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 5) == 0){
                    bookingslot=5;
                    time.setText("08-16");
                    harbor.setText("Habor: 201");
                    date.setText(datePicker.getValue().toString());
                    ships = m.getAllShipBooking(date.getText());
                    
                    
                    for(Ship s : ships){
                        if(s.volymid().equals("B")||s.volymid().equals("BB")||s.volymid().equals("C"))
                        olll.add(new ListShip(s));
                    }
                    thestage.setScene(bookingChooseShipScene);
                }
              }
            
        });
        secondDock1600.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 6) == 0){
                    bookingslot=6;
                    time.setText("16-00");
                    harbor.setText("Habor: 201");
                    date.setText(datePicker.getValue().toString());
                    ships = m.getAllShipBooking(date.getText());
                    
                    
                    for(Ship s : ships){
                        if(s.volymid().equals("B")||s.volymid().equals("BB")||s.volymid().equals("C"))
                        olll.add(new ListShip(s));
                    }
                    thestage.setScene(bookingChooseShipScene);
                }
              }
            
        });
        thirdDock0008.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 7) == 0){
                    bookingslot=7;
                    time.setText("00-08");
                    harbor.setText("Habor: 301");
                    date.setText(datePicker.getValue().toString());
                    ships = m.getAllShipBooking(date.getText());
                    
                    
                    for(Ship s : ships){
                        if(s.volymid().equals("CC")||s.volymid().equals("CCC")||s.volymid().equals("K"))
                        olll.add(new ListShip(s));
                    }
                    thestage.setScene(bookingChooseShipScene);
                }
              }
            
        });
        thirdDock0816.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 8) == 0){
                    bookingslot=8;
                    time.setText("08-16");
                    harbor.setText("Habor: 301");
                    date.setText(datePicker.getValue().toString());
                    ships = m.getAllShipBooking(date.getText());
                    
                    
                    for(Ship s : ships){
                        if(s.volymid().equals("CC")||s.volymid().equals("CCC")||s.volymid().equals("K"))
                        olll.add(new ListShip(s));
                    }
                    thestage.setScene(bookingChooseShipScene);
                }
              }
            
        });
        thirdDock1600.setOnAction(new EventHandler<ActionEvent>(){
              @Override
              public void handle(ActionEvent event) {
                 if(m.getBookingCountFromDateAndSlot(datePicker.getValue().toString(), 9) == 0){
                    bookingslot=9;
                    time.setText("16-00");
                    harbor.setText("Habor: 301");
                    date.setText(datePicker.getValue().toString());
                    ships = m.getAllShipBooking(date.getText());
                    
                    
                    for(Ship s : ships){
                        if(s.volymid().equals("CC")||s.volymid().equals("CCC")||s.volymid().equals("K"))
                        olll.add(new ListShip(s));
                    }
                    thestage.setScene(bookingChooseShipScene);
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
      public VBox addBookingChooseShipMenuBtns() throws Exception, Throwable {
          VBox vbox = new VBox(20);
          vbox.setAlignment(Pos.TOP_CENTER);
          vbox.setMaxWidth(300);
          vbox.setPadding(new Insets(15, 12, 15, 12));
          Button back = new Button("Back");
          back.setPrefSize(120, 20);
          date = new Label();
          time = new Label(bookingtime);
          harbor = new Label();
          tablev2 = new TableView();
          
          candidates = FXCollections.observableArrayList();
          selected = FXCollections.observableArrayList();
          
        Label message = new Label("Choose a Ship!");
        
        FadeTransition fader = createFader(message);
                                SequentialTransition blinkThenFade = new SequentialTransition(
                                        message,
                                      
                                        fader
                                );
        message.setVisible(false);
        
        Button next = new Button("Next");
        next.setPrefSize(120, 20);
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
        
        olll = FXCollections.observableArrayList();
        
        tablev2.getColumns().addAll(shipid,shipname,shipowner,shipvolymid);
                        olll.clear();
                        tablev2.setItems(olll);
                        
                        
        back.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  olll.clear();
                  
                  thestage.setScene(dailyBookingScene);
              }
          });
        next.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  if(tablev2.getSelectionModel().getSelectedIndex()==-1){
                      message.setVisible(true);
                                blinkThenFade.play();
                                
                  }else{
                      
                      bookedship = ships.get(tablev2.getSelectionModel().getFocusedIndex());
                      amountOfRecoursesToBook = m.getBookingRightAmountOfRecourses(bookedship.volymid());
                      personnelmsg.setText("Pick "+amountOfRecoursesToBook+" persons");
                      trucksmsg.setText("Pick "+amountOfRecoursesToBook+" trucks");
                      if(datePicker.getValue().getDayOfWeek().toString().equals("MONDAY")||datePicker.getValue().getDayOfWeek().toString().equals("TUESDAY")||datePicker.getValue().getDayOfWeek().toString().equals("WEDNESDAY")||datePicker.getValue().getDayOfWeek().toString().equals("THURSDAY")||datePicker.getValue().getDayOfWeek().toString().equals("FRIDAY")){
                          persons = m.getAllPersonsForBooking("M-F", bookedship.volymid(), date.getText());
                          
                          for(Person p : persons){
                              candidates.add(p);
                          }
                      }
                      else if(datePicker.getValue().getDayOfWeek().toString().equals("SUNDAY")){
                          persons = m.getAllPersonsForBooking("S", bookedship.volymid(), date.getText());
                          persons.addAll(m.getAllPersonsForBooking("L-S", bookedship.volymid(), date.getText()));
                          
                          for(Person p : persons){
                              candidates.add(p);
                          }
                      }
                      else if(datePicker.getValue().getDayOfWeek().toString().equals("SATURDAY")){
                          persons = m.getAllPersonsForBooking("L-S", bookedship.volymid(), date.getText());
                          
                          for(Person p : persons){
                              candidates.add(p);
                          }
                          
                          
                      }
                          
                      
                      
                      
                  
                  thestage.setScene(bookingChoosePersonnelScene);
                  }
              }
          });
                        
                        
                        
                        
                       
          
          vbox.getChildren().setAll(date,harbor,time,tablev2,message,next,back);
          return vbox;
      }
      public VBox addBookingChoosePersonnelMenuBtns()throws Throwable{
          
          
          VBox buttons = new VBox(20);
          VBox finalbox = new VBox(20);
          buttons.setAlignment(Pos.CENTER);
          finalbox.setAlignment(Pos.CENTER);
          GridPane gridpane = new GridPane();
    gridpane.setPadding(new Insets(5));
    gridpane.setHgap(10);
    gridpane.setVgap(10);
    
   gridpane.setAlignment(Pos.CENTER);
    Label candidatesLbl = new Label("Personnel");
    GridPane.setHalignment(candidatesLbl, HPos.CENTER);
    gridpane.add(candidatesLbl, 0, 0);
    Label personnel = new Label("Personnel");
    personnelmsg = new Label();
    Label rightp = new Label("Remember if you pick one what works 50% pick one more what works 50%");
    
    
    
    Label selectedLbl = new Label("Selected");
    gridpane.add(selectedLbl, 2, 0);
    GridPane.setHalignment(selectedLbl, HPos.CENTER);
    Button back = new Button("Back");
    back.setPrefSize(120,20);
    
    Button next = new Button("Next");
    next.setPrefSize(120, 20);

     Label message = new Label();
     
     
    
     FadeTransition fader = createFader(message);
                                SequentialTransition blinkThenFade = new SequentialTransition(
                                        message,
                                      
                                        fader
                                );
        message.setVisible(false);
     
    final ListView<Person> candidatesListView = new ListView<>(candidates);
    gridpane.add(candidatesListView, 0, 1);
    candidatesListView.setPrefSize(150, 300);
    
    final ListView<Person> heroListView = new ListView<>(selected);
    gridpane.add(heroListView, 2, 1);
    heroListView.setPrefSize(150, 300);
    Button sendRightButton = new Button(" > ");
    sendRightButton.setPrefSize(40, 40);
    sendRightButton.setOnAction((ActionEvent event) -> {
        
      Person potential = candidatesListView.getSelectionModel()
          .getSelectedItem();
      
      if (potential != null) {
        
                
        candidatesListView.getSelectionModel().clearSelection();
        
        candidates.remove(potential);
        selected.add(potential);
        
      }
    });

    Button sendLeftButton = new Button(" < ");
    sendLeftButton.setPrefSize(40, 40);
    sendLeftButton.setOnAction((ActionEvent event) -> {
      Person s = heroListView.getSelectionModel().getSelectedItem();
      if (s != null) {
          
          
        heroListView.getSelectionModel().clearSelection();
        selected.remove(s);
        candidates.add(s);
        
      }
    });
    VBox vbox = new VBox(5);
    vbox.setAlignment(Pos.TOP_CENTER);
    vbox.getChildren().addAll(sendRightButton, sendLeftButton);

    gridpane.add(vbox, 1, 1);
    GridPane.setHalignment(vbox, HPos.CENTER);
    back.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  candidates.clear();
                  selected.clear();
                  thestage.setScene(bookingChooseShipScene);
              }
          });
    next.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  
                  
                     bookpersons.addAll(heroListView.getItems());
                 
                  
                  for(Person p : bookpersons){
                      if(p.status().equals("100%")){
                          rightammountofpersonnel = rightammountofpersonnel +1;
                          
                      }
                      else if(p.status().equals("50%"))
                          rightammountofpersonnel = rightammountofpersonnel +0.5;
                      
                  }
                  
                 if(rightammountofpersonnel > amountOfRecoursesToBook){
                     rightammountofpersonnel = 0;
                     bookpersons.removeAll(heroListView.getItems());
                     message.setText("To meny Personnel");
                      message.setVisible(true);
                                blinkThenFade.play();
                          
                  }
                 else if(rightammountofpersonnel < amountOfRecoursesToBook){
                     rightammountofpersonnel=0;
                     bookpersons.removeAll(heroListView.getItems());
                     message.setText("To few Personnel");
                      message.setVisible(true);
                                blinkThenFade.play();
                      
                 }
                 else{
                     candidates.clear();
                     selected.clear();
                     trucks = m.getAllTrucksForBooking(bookedship.volymid());
                     for(Truck t : trucks){
                         candidates.add(t);
                     }
                     thestage.setScene(bookingChooseTrucksScene);
                 }
                 
              }
                 
          });
    buttons.getChildren().setAll(message,next,back);
    finalbox.getChildren().setAll(personnel, personnelmsg,rightp,gridpane,buttons);
    finalbox.setAlignment(Pos.CENTER);
    return finalbox;
  
      }
      public VBox addBookingChooseTrucksMenuBtns()throws Throwable{
          
          VBox buttons = new VBox(20);
          VBox finalbox = new VBox(20);
          buttons.setAlignment(Pos.CENTER);
          finalbox.setAlignment(Pos.CENTER);
          
          
          trucksmsg = new Label();
          GridPane gridpane = new GridPane();
    gridpane.setPadding(new Insets(5));
    gridpane.setHgap(10);
    gridpane.setVgap(10);
    gridpane.setAlignment(Pos.CENTER);

    Label candidatesLbl = new Label("Trucks");
    GridPane.setHalignment(candidatesLbl, HPos.CENTER);
    gridpane.add(candidatesLbl, 0, 0);

    Label selectedLbl = new Label("Selected");
    gridpane.add(selectedLbl, 2, 0);
    GridPane.setHalignment(selectedLbl, HPos.CENTER);
    Button back = new Button("Back");
    back.setPrefSize(120, 20);
    gridpane.add(back, 1, 4);
    // Candidates
    Button book = new Button("Book");
    book.setPrefSize(120, 20);
    gridpane.add(book, 1, 3);
    Label message = new Label();
    message.setVisible(false);
    
    Label trucks = new Label("Trucks");
    
    FadeTransition fader = createFader(message);
                                SequentialTransition blinkThenFade = new SequentialTransition(
                                        message,
                                      
                                        fader
                                );
    faderrr = createFader(messages);
                                SequentialTransition blinkThenFadee = new SequentialTransition(
                                        messages,
                                      
                                        faderrr
                                );
    
    final ListView<Truck> candidatesListView = new ListView<>(candidates);
    gridpane.add(candidatesListView, 0, 1);
    
    
    final ListView<Truck> heroListView = new ListView<>(selected);
    gridpane.add(heroListView, 2, 1);
    heroListView.setPrefSize(50, 300);
    candidatesListView.setPrefSize(50, 300);
    
    Button sendRightButton = new Button(" > ");
    sendRightButton.setPrefSize(50, 50);
    sendRightButton.setOnAction((ActionEvent event) -> {
      Truck potential = candidatesListView.getSelectionModel()
          .getSelectedItem();
      if (potential != null) {
          
        candidatesListView.getSelectionModel().clearSelection();
        candidates.remove(potential);
        selected.add(potential);
      }
    });

    Button sendLeftButton = new Button(" < ");
    sendLeftButton.setPrefSize(50, 50);
    sendLeftButton.setOnAction((ActionEvent event) -> {
      Truck s = heroListView.getSelectionModel().getSelectedItem();
      if (s != null) {
        heroListView.getSelectionModel().clearSelection();
        selected.remove(s);
        candidates.add(s);
      }
    });
    VBox vbox = new VBox(5);
    vbox.getChildren().addAll(sendRightButton, sendLeftButton);

    gridpane.add(vbox, 1, 1);
    back.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  candidates.clear();
                  selected.clear();
                  rightammountofpersonnel = 0;
                   if(datePicker.getValue().getDayOfWeek().toString().equals("MONDAY")||datePicker.getValue().getDayOfWeek().toString().equals("TUESDAY")||datePicker.getValue().getDayOfWeek().toString().equals("WEDNESDAY")||datePicker.getValue().getDayOfWeek().toString().equals("THURSDAY")||datePicker.getValue().getDayOfWeek().toString().equals("FRIDAY")){
                          persons = m.getAllPersonsForBooking("M-F", bookedship.volymid(), date.getText());
                          
                          for(Person p : persons){
                              candidates.add(p);
                          }
                      }
                      else if(datePicker.getValue().getDayOfWeek().toString().equals("SUNDAY")){
                          persons = m.getAllPersonsForBooking("S", bookedship.volymid(), date.getText());
                          persons.addAll(m.getAllPersonsForBooking("L-S", bookedship.volymid(), date.getText()));
                          for(Person p : persons){
                              candidates.add(p);
                          }
                      }
                      else if(datePicker.getValue().getDayOfWeek().toString().equals("SATURDAY")){
                          persons = m.getAllPersonsForBooking("L-S", bookedship.volymid(), date.getText());
                          for(Person p : persons){
                              candidates.add(p);
                          }
                          
                          
                      }
                   bookpersons.clear();
                  thestage.setScene(bookingChoosePersonnelScene);
              }
          });
    book.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  
                  booktrucks.addAll(heroListView.getItems());
                 if(selected.size()< amountOfRecoursesToBook){
                     message.setText("To few trucks!");
                     message.setVisible(true);
                                blinkThenFade.play();
                 }
                 else if(selected.size()>amountOfRecoursesToBook){
                     message.setText("To many trucks!");
                     message.setVisible(true);
                                blinkThenFade.play();
                 }
                 else{
                     ArrayList<Integer> personids = new ArrayList<Integer>();
                     ArrayList<Integer> trucksids = new ArrayList<Integer>();
                     for(Person p : bookpersons){
                         personids.add(p.getID());
                     }
                     for(Truck t : booktrucks){
                         trucksids.add(t.getID());
                     }
                     
                     Booking b = new Booking(bookedship.getID(),bookingslot,datePicker.getValue().toString());
                     b.setPersonid(personids);
                     b.setTruckid(trucksids);
                     m.addBooking(b);
                     
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
                     
                     messages.setText("Booked");
                     messages.setVisible(true);
                     blinkThenFadee.play();
                     candidates.clear();
                     selected.clear();
                     thestage.setScene(dailyBookingScene);
                 }
              }
          });
    buttons.getChildren().setAll(message,book,back);
    finalbox.getChildren().setAll(trucks, trucksmsg,gridpane,buttons);
    finalbox.setAlignment(Pos.CENTER);
    return finalbox;
  
      }
     
      private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(4), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }
    
}
/*
Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.WEEK_OF_YEAR, 23);        
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    System.out.println(sdf.format(cal.getTime()));
*/