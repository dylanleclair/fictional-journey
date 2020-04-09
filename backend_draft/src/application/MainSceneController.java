package application;


import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout.Alignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MainSceneController {


	private LocalDate startDate;
	private User signedIn;
	private User selected;
	private View currentView;
	private String signedInRole;
    private TimeSlot selectedApptSlot;
	private boolean initialSlotsLoaded;
	
	private StackPane selectedApptElement;
	
	
    private LocalDate[] dates;

    
    @FXML 
    private SplitPane splitPane;
    
    @FXML 
    private ScrollPane scrollPane;
    
    @FXML
    private GridPane gridView;
    
	@FXML
    private Label name;

    @FXML
    private Label role;

    @FXML
    private ListView<Booking> bookings;

    @FXML
    private Button lastWeek;

    @FXML
    private Button nextWeek;

    @FXML
    private Label WeekLabel;

    @FXML
    private Label daySunday;

    @FXML
    private Label dayWednesday;

    @FXML
    private Label dayMonday;

    @FXML
    private Label dayTuesday;

    @FXML
    private Label dayThursday;

    @FXML
    private Label dayFriday;

    @FXML
    private Label daySaturday;

   @FXML 
   private VBox calendar;
   
   @FXML
   private Pane cpanel;
    
   
   @FXML
   private AnchorPane mainPane;
   
    @FXML
    private Button changeView;
    
   
    public User getSignedIn() {
		return signedIn;
	}


 
	public void setSignedIn(User signedIn) {
		this.signedIn = signedIn;
	}



	public Label getName() {
		return name;
	}



	public void setName(String name) {
		this.name.setText(name);
	}



	public Label getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role.setText(role);
	}



	public ListView<Booking> getBookings() {
		return bookings;
	}



	public void setBookings(ObservableList<Booking> bookings) {
		this.bookings.setItems(bookings);
	}


	public void refreshBookings() {
		this.setBookings(GUI.datab.getBookings(signedIn));
	}

    
    public void init() {
    	this.selected = signedIn;
    	this.setName(signedIn.name);
    	this.setRole(signedIn.title);
    	this.setBookings(GUI.datab.getBookings(signedIn));
    	
    	System.out.println(signedInRole = signedIn.getClass().getName().substring(11));
    	signedInRole = signedIn.getClass().getName().substring(11);
    	
    	LocalDate today = LocalDate.now();
    	LocalDate startOfWeek = LocalDate.now();
    	DayOfWeek dayOfWeek = today.getDayOfWeek();
    	
    	// backtrack until we reach the previous sunday
    	while (dayOfWeek != DayOfWeek.SUNDAY) {
    		startOfWeek = startOfWeek.minusDays(1);
    		dayOfWeek = startOfWeek.getDayOfWeek();
    	}
    	
    	currentView = View.CALENDAR;
    	startDate = startOfWeek;
    	populateWeek(startOfWeek);
    }
    
    /**
     * 
     * @param startDay - the sunday at the start of the week
     */
    public void populateWeek(LocalDate startDay) {
    	
    	GridPane lol = new GridPane();
    	
    	scrollPane.setContent(lol);
    	LocalTime start = LocalTime.of(0, 0);
    	
    	//lol.setGridLinesVisible(true);
    	
    	lol.setMinSize(760, 2700);
    	lol.setMaxSize(760, 2700);
    	for (int i = 0; i < 48; i++) {
    		BorderPane pretty = new BorderPane();
    		
    		if (i % 2 == 0) {
        		Label timeslot = new Label(start.plusHours(i/2).toString());
        		pretty.setTop(timeslot);
        		BorderPane.setAlignment(timeslot, Pos.CENTER);
        		//timeslot.setAlignment(Pos.CENTER);
        		
    		}

    		lol.add(pretty, 0, i);
    		RowConstraints row = new RowConstraints();
    		row.setPercentHeight(2.083333);
    		System.out.println(100/48);
    		lol.getRowConstraints().add(row);
    		

    	}
    	
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(9);
		lol.getColumnConstraints().add(col);
    	
		for (int i = 0; i < 7; i++) {
			
			// get list of aapointments
			
			for (Booking b : GUI.datab.getBookings(signedIn, startDay.plusDays(i))) {
				
				StackPane appt = new StackPane();
				Rectangle rect = new Rectangle(82,50);
				VBox info = new VBox();
				
				String typeOfBooking = b.getClass().getName().substring(12);
				Label type = new Label(typeOfBooking);
				Label time = new Label(b.startTime.toLocalTime().toString() + "-" + b.endTime.toLocalTime().toString());
				Label location = new Label(b.location.toString());
				
				
				// styling
				Font smallText = new Font(10);
				type.setFont(smallText);
				time.setFont(smallText);
				location.setFont(smallText);
				
				if (typeOfBooking == "Appointment") {
					rect.setFill(Paint.valueOf("#8e7aff"));
				} else if (typeOfBooking == "Meeting") {
					rect.setFill(Paint.valueOf("#7aff9a"));
				} else if (typeOfBooking == "Test") {
					rect.setFill(Paint.valueOf("#ff7aee"));
				} else {
					rect.setFill(Paint.valueOf("#ff7a7a"));
				}
				
				//rect.setFill(Paint.valueOf("#8e7aff"));
				rect.setArcHeight(15);
				rect.setArcWidth(15);
				rect.setOpacity(0.45);
				
				info.getChildren().addAll(type, time, location);
				info.setPadding(new Insets(5,0,0,15));
				
				appt.getChildren().addAll(rect, info);
				
				
				lol.add(appt,i+1,getRow(b.startTime.toLocalTime()));
			}
			
			ColumnConstraints col2 = new ColumnConstraints();
			col2.setPercentWidth(13);
			lol.getColumnConstraints().add(col2);
			
			
			
		}
		
    	System.out.println(lol.getRowCount()+ " " + lol.getColumnCount());
    	
    	
    	
    	 Label[] daylabels = {daySunday, dayMonday, dayTuesday,dayWednesday,dayThursday,dayFriday,daySaturday};
    	
    	getWeekLabel().setText("Week of "+ startDay.getMonth().toString().substring(0, 1) + startDay.getMonth().toString().substring(1).toLowerCase() + " " + startDay.getDayOfMonth() +" - " + startDay.plusDays(6).getMonth().toString().substring(0, 1) + startDay.plusDays(6).getMonth().toString().substring(1).toLowerCase()+ " " + startDay.plusDays(6).getDayOfMonth());
    	// fills in the proper days of the week for the labels above the gridview
    	for (int i = 0; i <= daylabels.length -1; i++) {
    		
    		String day = Integer.toString(startDay.getDayOfMonth());
    		System.out.println(day);
    		daylabels[i].setText(day);
    		
    		startDay = startDay.plusDays(1);
    	
    	
    		
    		
    		
    	
    	}
    	
    	
    }
    
   
    
    
    

    private int getRow(LocalTime localTime) {
    	
    	int hour = localTime.getHour();
    	
    	
    	int shift;
    	
    	if (localTime.isAfter(LocalTime.of(hour, 0)))
    		shift = 1;
    	else shift = 0;
    	
		// TODO Auto-generated method stub
		return hour * 2 + shift;
	}



	@FXML
    void lastWeekAction(ActionEvent event) {
    	startDate = startDate.minusDays(7);
    	populateWeek(startDate);
    }

    @FXML
    void nextWeekAction(ActionEvent event) {
    	startDate = startDate.plusDays(7);
    	populateWeek(startDate);
    }

    
    public enum View { 
    	CALENDAR,
    	CONTROL
    }
    
    @FXML
    public void toggleView(ActionEvent event) {
    	
    	if (currentView == View.CALENDAR) {
    	
    		changeView.setText("My Calendar View");
    		// switch to control
    		currentView = View.CONTROL;
    		buildControlView();
        	splitPane.getItems().remove(1);
        	splitPane.getItems().add(cpanel);
    	} else {
    		
    		changeView.setText("My Control Panel");
    		// switch to calendar
    		currentView = View.CALENDAR;
        	splitPane.getItems().remove(1);
        	splitPane.getItems().add(mainPane);
    		populateWeek(startDate);
    		
    	}
    	
    }

    
    
    public void buildControlView () {
    	
    	AnchorPane pane = new AnchorPane();
    	
    	ScrollPane cscroll = new ScrollPane();
    	

    	
    	VBox manager = new VBox();
    	cscroll.setContent(manager);
    	
    	
    	//manager.setMaxSize(780,640);
    	//manager.setMinSize(780,640);
    	
    	AnchorPane.setTopAnchor(cscroll, (double) 0);
    	AnchorPane.setBottomAnchor(cscroll, (double) 0);
    	AnchorPane.setLeftAnchor(cscroll, (double) 0);
    	AnchorPane.setRightAnchor(cscroll,(double)0);
    	
    	pane.getChildren().add(cscroll);
    	
    	//scrollPane.setContent(pane);
    	
    	String typeOfSignedIn = signedIn.getClass().getName().substring(12);
    	if (typeOfSignedIn.contentEquals("Admin")) {
    	
    	// add admin elements
    		manager.getChildren().add(generateEditInfoPanel());
    		
    	} else if (typeOfSignedIn.contentEquals("Doctor")) {
    		
    	// add doctor/staff elements	
    		
    		manager.getChildren().add(generateEditInfoPanel());
    	
    	} else if (typeOfSignedIn.contentEquals("Patient")) {
    		
    	// add patient elements
    		System.out.println("ugh");
    		
    		Pane test = generateEditInfoPanel();
    		BorderPane lol = generateBookAppointmentPanel();
    		manager.getChildren().add(test);
    		manager.getChildren().add(lol);
    		
    	
    		
    	}
    

    	cpanel = pane;
    	
    	System.out.println("hi");

    	
    }
    


	public Label getWeekLabel() {
		return WeekLabel;
	}



	public void setWeekLabel(Label weekLabel) {
		WeekLabel = weekLabel;
	}

	
	
	public Pane generateEditInfoPanel () {
		
		String[] list = {"Name", "Email (used for logins)", "Phone Number", "Password"};
		
		String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
		
		//BorderPane pane = new BorderPane();
		
		BorderPane canvas = new BorderPane();
		
		//pane.setManaged(false);
		
		//pane.setMaxWidth(760);
		//pane.setMinWidth(760);
		
		VBox internal = new VBox();
		
		internal.setMaxWidth(760);
		internal.setMinWidth(760);
		
		Label title = new Label("Edit My Information");
		title.setFont(new Font(19));
		
	
		//canvas.getChildren().add(title);
		
		//canvas.getChildren().add(internal);
		
		// Build the labels and textfields for their corresponding thing
	
		
		
		
		TextField[] tfs = {new TextField(signedIn.name), new TextField(signedIn.emailAddress), new TextField(signedIn.phoneNumber)};
		
		int ind = 0;
		
		for (String item : list) {
			Label itemTitle = new Label("Your "+ item);
			if (!item.contentEquals("Password")) {
				
				// add the components to the VBox
				internal.getChildren().addAll(itemTitle,tfs[ind]);
			} else {
				PasswordField pfield = new PasswordField();
				pfield.setPromptText("Enter a new password.");
				
				internal.getChildren().addAll(itemTitle,pfield);
			}
			
			ind++;
		}
		
		// doctor specific elements
		if (signedInRole == "Doctor") {
			Label daysLabel = new Label("Days you wish to take appointments:");
			internal.getChildren().add(daysLabel);
			
			
			HBox checkBoxes = new HBox(8);
			
			for (String item : days) {
				
				CheckBox dayCheck = new CheckBox(item);
				
				checkBoxes.getChildren().add(dayCheck);
				
			}
			
			Label appointmentHours = new Label("Appointment hours (list in intervals, ex: (8-11) use military time, separate with commas.");
			
			TextField appointmentSet = new TextField();
			appointmentSet.setPromptText("(8-11),(13-15)");
			
			internal.getChildren().addAll(appointmentHours, appointmentSet);
			
		}

		Button update = new Button("Update my information");
		
		update.setOnAction(e -> {
			// add the function for the button here!!
				
			TextField tf = (TextField) internal.getChildren().get(1);				
			signedIn.setName(tf.getText());
			
			tf = (TextField) internal.getChildren().get(3);
			signedIn.setEmailAddress(tf.getText());	

			tf = (TextField) internal.getChildren().get(5);
			signedIn.setPhoneNumber(tf.getText());
			
			PasswordField pf = (PasswordField) internal.getChildren().get(7);
			if (!pf.getText().contentEquals("")) {
				signedIn.setPassword(pf.getText());
			}
			
			try {
				GUI.datab.saveData("data/system.dat");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
		internal.getChildren().add(update);
		
		// Styling
		
		title.setPadding(new Insets(15,0,5,15));
		
		canvas.setTop(title);
		canvas.setCenter(internal);
		
		
		internal.setPadding(new Insets(0,15,0,15));
		internal.setSpacing(5);
		
		VBox.setMargin(update, new Insets(8,0,0,0));
		
		
		return canvas;
	}
	
	/**
	 * TODO: Clean up time formatting using DateTimeFormatter, add scrollpane to manage FlowView
	 * @return
	 */
	public BorderPane generateBookAppointmentPanel () {
		
		initialSlotsLoaded = false;
		
		
		BorderPane pane = new BorderPane();
		
		pane.setMaxWidth(760);
		pane.setMinWidth(760);
		
		pane.setMaxHeight(350);
		pane.setMinHeight(350);
		
		VBox internal = new VBox();
		
		
		Label title = new Label("Book An Appointment");
		title.setFont(new Font(19));
		

		
		// add contents to internal
		
		
		Label deptPrompt = new Label("Pick a department:");
		
		ComboBox<Department> department = new ComboBox<Department>();
		department.setItems(FXCollections.observableArrayList(Department.values()));
		
		Label doctPrompt = new Label("Pick a doctor:");
		
		ComboBox<Doctor> doctor = new ComboBox<Doctor>();
		doctor.setDisable(true);
		
		
		Label datePrompt = new Label("Pick a date:");
		
		DatePicker dayPicker = new DatePicker();
		dayPicker.setDisable(true);
		
		
		Label sTimeslot = new Label("Selected timeslot:");
		Label dayTimeslot = new Label();
		Label timeTimeslot = new Label();
		Button book = new Button("Book");
		
		
		
		
		internal.getChildren().addAll(deptPrompt, department, doctPrompt,doctor,datePrompt,dayPicker,sTimeslot, dayTimeslot, timeTimeslot, book);
		
		
		// make components for selecting a timeslot 
		
		VBox center = new VBox();
		
		center.setPadding(new Insets(0,0,0,15));
		
		Label availableTitle = new Label("Available time slots:");
		availableTitle.setFont(new Font(15));
		
		ScrollPane aTimeSlots = new ScrollPane();
		aTimeSlots.setMinSize(320, 210);
		aTimeSlots.setMaxSize(320, 210);
		
		FlowPane previewSlots = new FlowPane();
		previewSlots.setMaxSize(300.0, 200.0);
		
		aTimeSlots.setContent(previewSlots);
		
		// Program the functionality 
	
		center.getChildren().addAll(availableTitle,aTimeSlots);
		
		department.setOnAction( e -> {
			
			
			//department.setDisable(true); // remove when bug testing
			// allow a doctor to be chosen
			doctor.setItems(FXCollections.observableArrayList(GUI.datab.getDoctors(department.getValue())));
			doctor.setDisable(false);
			
			if(initialSlotsLoaded) refreshSlotPreview(previewSlots,department.getValue(),doctor.getValue(),dayPicker.getValue(), dayTimeslot, timeTimeslot);
			
		});
		
		doctor.setOnAction(e -> {
			
			
			//doctor.setDisable(true); // remove when bug testing
			// enable daypicker
			dayPicker.setDisable(false); 
			
			if (initialSlotsLoaded) refreshSlotPreview(previewSlots,department.getValue(),doctor.getValue(),dayPicker.getValue(), dayTimeslot, timeTimeslot);
			
		});
		
		
		
		dayPicker.setOnAction(e -> {
			
			
			// might have to make it so this code is called again when the doctor / dept is changed after a date is selected
			initialSlotsLoaded = true;
			refreshSlotPreview(previewSlots,department.getValue(),doctor.getValue(),dayPicker.getValue(), dayTimeslot, timeTimeslot);
		
		});
		
		
		
		// (this includes generating the timeslots lol)
		
		book.setOnAction(e -> {
			
			
			if (selectedApptSlot != null) {
				
				// add booking
				GUI.datab.addAppointment(signedIn, doctor.getValue(), selectedApptSlot);
				
				// remove timeslot from preview
				
				previewSlots.getChildren().remove(selectedApptElement);
				
				refreshBookings();
				
			}
			// create the appointment using the database function for it using data from fields
			
		
		});
		
		
		
		// Styling
		title.setPadding(new Insets(15,0,5,15));
		
		pane.setTop(title);
		pane.setLeft(internal);
		pane.setCenter(center);

		internal.setPadding(new Insets(0,15,0,15));
		internal.setSpacing(5);
		
		
		
		return pane;
	}
	
	
	
	public ArrayList<TimeSlot> generateAppointmentSlots(Doctor doctor,  LocalDate day) {
		
		
		ArrayList<TimeSlot> slots = doctor.genTimeSlots(day);
		
		ArrayList<Booking> temp = GUI.datab.getBookings(doctor, day);
		
		ArrayList<TimeSlot> toRet = new ArrayList<TimeSlot>(slots);
		
		System.out.println(temp);
		
		// Checks if the doctor is already booked for a given time slot
		// not the fastest implementation but only so much optimizations
		// can be done for a project worth a measly 25%
		for(Booking item : temp) {
			
			for (TimeSlot slot : slots) {
				if (item.startTime.equals(slot.startTime) ) {
					// if doctor is booked, remove the timeslot from potential candidates
					System.out.println("removing timeslot");
					toRet.remove(slot);
				}
			}
		
			
		}
		
		return toRet;
		
	}
	
	
	
	
	
	
	public void refreshSlotPreview(FlowPane previewSlots, Department dept,Doctor doctor, LocalDate day, Label dayLabel, Label timeLabel) {
		
		previewSlots.getChildren().clear();
		int i = 1;
		
		
		for (TimeSlot tslot : generateAppointmentSlots(doctor,day)) {
			
			// generate an element in the flowview of it!
			
			StackPane appt = new StackPane();
			
			appt.setMaxSize(100, 100);
			appt.setMinSize(100,100);
			
			Rectangle rect = new Rectangle(95,95);
			VBox info = new VBox();
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
			
			Label name = new Label("Slot " + i);
			Label startTime = new Label(tslot.startTime.toLocalTime().format(formatter) + " to");
			Label endTime = new Label(tslot.endTime.toLocalTime().format(formatter));
			Label location = new Label(dept.getValue()); 
			
			
			// styling
			Font bigText = new Font(17);
			name.setFont(bigText);
			

			rect.setFill(Paint.valueOf("#8e7aff"));

			
			//rect.setFill(Paint.valueOf("#8e7aff"));
			rect.setArcHeight(10);
			rect.setArcWidth(10);
			rect.setOpacity(0.45);
			
			info.getChildren().addAll(name, startTime, endTime, location);
			info.setPadding(new Insets(10,0,0,10));
			
			appt.getChildren().addAll(rect, info);
			
			previewSlots.getChildren().add(appt);
			
			i++;
			
			
			// code for making it possible to select a timeslot:
			
			// TEST THIS CODE ->  I'm not sure if it will make this for each specific timeslot or just the last one
			appt.setOnMouseClicked(new EventHandler<MouseEvent>() {
			    @Override
			    public void handle(MouseEvent mouseEvent) {
			        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
			            if(mouseEvent.getClickCount() == 2){

			            	
			            	String dayString = day.getMonth().toString().substring(0, 1) + day.getMonth().toString().toLowerCase().substring(1) + " "+ day.getDayOfMonth();
			            	String time = tslot.startTime.toLocalTime().format(formatter) + " - " + tslot.endTime.toLocalTime().format(formatter) ;
			            	
			            	dayLabel.setText(dayString);
			            	timeLabel.setText(time);
			            	
			            	selectedApptSlot = tslot;
			            	selectedApptElement = appt;
			            	
			            }
			        }
			    }
			});
			
			
			
			
		}

	
		
	}
	
	
	
	
	
}





