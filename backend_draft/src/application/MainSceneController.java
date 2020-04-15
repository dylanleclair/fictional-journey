package application;


import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import javafx.scene.control.ChoiceBox;
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
	private TimeSlot selectedTestSlot;
	private StackPane selectedTestElement;
	private Booking selectDelete;
	
	private VBox manager = new VBox();
	private BorderPane apptBooking;
	private BorderPane cancelBooking;

	
	private StackPane selectedApptElement;
	
	private boolean testSlotsLoaded = false;
	
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
    	scrollPane.setVvalue(0.4);
    	LocalTime start = LocalTime.of(0, 0);
    	
    	//lol.setGridLinesVisible(true);
    	
    	lol.setMinSize(760, 2700);
    	lol.setMaxSize(760, 2700);
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
    	
    	for (int i = 0; i < 48; i++) {
    		BorderPane pretty = new BorderPane();
    		
    		if (i % 2 == 0) {
        		Label timeslot = new Label(start.plusHours(i/2).format(formatter));
        		pretty.setTop(timeslot);
        		BorderPane.setAlignment(timeslot, Pos.CENTER);
        		//timeslot.setAlignment(Pos.CENTER);
        		
    		}

    		lol.add(pretty, 0, i);
    		RowConstraints row = new RowConstraints();
    		row.setPercentHeight(2.083333);
    		
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
				
				if (typeOfBooking.contentEquals("Test")) {
					
					Test t = (Test) b;
					type.setText(t.getTypeOfTest() + "");
				}
				
				Label time = new Label(b.startTime.toLocalTime().toString() + "-" + b.endTime.toLocalTime().toString());
				Label location = new Label(b.location.toString());
				
				
				// styling
				Font smallText = new Font(10);
				type.setFont(smallText);
				time.setFont(smallText);
				location.setFont(smallText);
				
				if (typeOfBooking.contentEquals("Appointment")) {
					rect.setFill(Paint.valueOf("#817aff"));
				} else if (typeOfBooking.contentEquals("Meeting")) {
					rect.setFill(Paint.valueOf("#7aff9a"));
				} else if (typeOfBooking.contentEquals("Test")) {
					rect.setFill(Paint.valueOf("#7aff9a"));
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
		
    	
    	
    	
    	
    	 Label[] daylabels = {daySunday, dayMonday, dayTuesday,dayWednesday,dayThursday,dayFriday,daySaturday};
    	
    	getWeekLabel().setText("Week of "+ startDay.getMonth().toString().substring(0, 1) + startDay.getMonth().toString().substring(1).toLowerCase() + " " + startDay.getDayOfMonth() +" - " + startDay.plusDays(6).getMonth().toString().substring(0, 1) + startDay.plusDays(6).getMonth().toString().substring(1).toLowerCase()+ " " + startDay.plusDays(6).getDayOfMonth());
    	// fills in the proper days of the week for the labels above the gridview
    	for (int i = 0; i <= daylabels.length -1; i++) {
    		
    		String day = Integer.toString(startDay.getDayOfMonth());
    		
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
    		manager.getChildren().addAll(generateEditInfoPanel(), generateManageUsersPanel());
    		
    	} else if (typeOfSignedIn.contentEquals("Doctor")) {
    		
    	// add doctor/staff elements	
    		
    		manager.getChildren().addAll(generateEditInfoPanel(), generateTestRecommendingPanel());
    	
    	} else if (typeOfSignedIn.contentEquals("Patient")) {
    		
    	// add patient elements
    		
    		
    		Patient s = (Patient) signedIn;
    		
    		Pane test = generateEditInfoPanel();
    		apptBooking = generateBookAppointmentPanel();
    		BorderPane t = generateBookTestPanel(s);
    		cancelBooking = generateCancelBookingPanel();
    		manager.getChildren().add(test); // 0
    		manager.getChildren().add(apptBooking); // 1
    		manager.getChildren().add(t); // 2
    		manager.getChildren().add(cancelBooking); // 3
    	
    		
    	}
    

    	cpanel = pane;
    	
    	

    	
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
		
		HBox checkBoxes = new HBox(8);
		// doctor specific elements
		if (GUI.selectedRole == Roles.DOCTOR) {
			
			
			Label daysLabel = new Label("Days you wish to take appointments: (only affects future bookings)");
			internal.getChildren().add(daysLabel);
			
			Doctor signInCopy = (Doctor) signedIn;
			List<Boolean> wdays = signInCopy.workingDays;
			
			
			int j = 0;
			for (String item : days) {
				
				CheckBox dayCheck = new CheckBox(item);
				
				dayCheck.setSelected(wdays.get(j));
				
				checkBoxes.getChildren().add(dayCheck);
				
				j++;
				
			}
			internal.getChildren().add(checkBoxes);
			
			Label appointmentHours = new Label("Appointment hours (list in intervals, ex: (8-11) use military time, separate with commas.");
			
			TextField appointmentSet = new TextField();
			appointmentSet.setPromptText("(8-11),(13-15)");
			
			internal.getChildren().addAll(appointmentHours, appointmentSet);
			
		}

		Button update = new Button("Update my information");
		
		update.setOnAction(e -> {
			// read entered data
				
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
			
			// read the days of week selected if Doctor
			
			if (GUI.selectedRole == Roles.DOCTOR) {
				// iterate over the elements
				
				Doctor signInCopy = (Doctor) signedIn;
				List<Boolean> wdays = new ArrayList<Boolean>(Arrays.asList(new Boolean[7]));
				
				for (int i = 0; i < 7; i++) {
					CheckBox cBox = (CheckBox) checkBoxes.getChildren().get(i);
					wdays.set(i, cBox.isSelected());
				}
				
				signInCopy.setWorkingDays(wdays);
				
				
			}
			
			// update the database
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
	 * TODO: clear the elements & refresh after booking!
	 * @return a Borderpane to be used as a component in a VBox housing a user's actions
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
			
			if(initialSlotsLoaded) refreshSlotPreview(previewSlots,department.getValue(),doctor.getValue(),dayPicker.getValue(), dayTimeslot, timeTimeslot, availableTitle);
			
		});
		
		doctor.setOnAction(e -> {
			
			
			//doctor.setDisable(true); // remove when bug testing
			// enable daypicker
			dayPicker.setDisable(false); 
			
			if (initialSlotsLoaded) refreshSlotPreview(previewSlots,department.getValue(),doctor.getValue(),dayPicker.getValue(), dayTimeslot, timeTimeslot, availableTitle);
			
		});
		
		
		
		dayPicker.setOnAction(e -> {
			
			
			// might have to make it so this code is called again when the doctor / dept is changed after a date is selected
			initialSlotsLoaded = true;
			refreshSlotPreview(previewSlots,department.getValue(),doctor.getValue(),dayPicker.getValue(), dayTimeslot, timeTimeslot, availableTitle);
		
		});
		
		
		
		// (this includes generating the timeslots lol)
		
		book.setOnAction(e -> {
			
			
			if (selectedApptSlot != null) {
				
				Patient p = (Patient) signedIn;
				// add booking
				GUI.datab.addAppointment(p, doctor.getValue(), selectedApptSlot);
				
				// remove timeslot from preview
				
				previewSlots.getChildren().remove(selectedApptElement);
				
				refreshBookings();
				
				manager.getChildren().set(3, generateCancelBookingPanel());
				
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
		
	
		
		// Checks if the doctor is already booked for a given time slot
		// not the fastest implementation but only so much optimizations
		// can be done for a project worth a measly 25%
		for(Booking item : temp) {
			
			for (TimeSlot slot : slots) {
				if (item.startTime.equals(slot.startTime) ) {
					// if doctor is booked, remove the timeslot from potential candidates
					
					toRet.remove(slot);
				}
			}
		
			
		}
		
		return toRet;
		
	}
	
	public ArrayList<TimeSlot> generateTestSlots(LocalDate day,TestType type) {
		
		ArrayList<TimeSlot> slots = GUI.datab.genTimeSlots(day);
		ArrayList<Booking> temp = GUI.datab.getTestBookings(day);
		
		ArrayList<TimeSlot> toRet = new ArrayList<TimeSlot>(slots);
		
		

			
			for (Booking item : temp) {
				
				Test t = (Test) item;
				
				for (TimeSlot slot : slots) {
					if (item.startTime.equals(slot.startTime) && t.getTypeOfTest() == type) {
						toRet.remove(slot);
					}
				}
				
			}
			
		

		
		return toRet;
		
	}
	
	
	
	
	public void refreshSlotPreview(FlowPane previewSlots, Department dept,Doctor doctor, LocalDate day, Label dayLabel, Label timeLabel, Label availableTitle) {
		
		previewSlots.getChildren().clear();
		int i = 1;
		
		ArrayList<TimeSlot> available = generateAppointmentSlots(doctor,day);
		
		if (available.size() > 0) {
		
			availableTitle.setText("Available time slots:");
			
			for (TimeSlot tslot : available) {
				
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
			
			
		} else {
			
			availableTitle.setText("No appointments are available that day.");
		}
		


	
		
	}
	
	
	
	public void refreshTestSlotPreview(FlowPane previewSlots, TestType type, LocalDate day, Label dayLabel, Label timeLabel, Label availableTitle) {
		
		previewSlots.getChildren().clear();
		int i = 1;
		
		ArrayList<TimeSlot> available = generateTestSlots(day, type);
		
		if (available.size() > 0) {
		
			availableTitle.setText("Available time slots:");
			
			for (TimeSlot tslot : available) {
				
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
				Label location = new Label(Department.DIAGNOSTICS + ""); 
				
				
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
				            	
				            	selectedTestSlot = tslot;
				            	selectedTestElement = appt;
				            	
				            }
				        }
				    }
				});
				
				
				
				
			}
			
			
		} else {
			
			availableTitle.setText("No tests of this type are available that day.");
		}
		


	
		
	}
	
	
	/**
	 * TODO: NOT EVEN IMPLEMENTED LOL I HAVE NO IDEA HOW TO RESTRICT BOOKINGS FOR THIS
	 * @return
	 */
	public BorderPane generateBookTestPanel (Patient p) {
		
		//initialSlotsLoaded = false;
		
		
		BorderPane pane = new BorderPane();
		
		pane.setMaxWidth(760);
		pane.setMinWidth(760);
		
		pane.setMaxHeight(350);
		pane.setMinHeight(350);
		
		VBox internal = new VBox();
		
		
		Label title = new Label("Book A Test");
		title.setFont(new Font(19));
		

		
		// add contents to internal
		
		
		Label testPrompt = new Label("Pick a test:");
		
		ComboBox<TestRecommendation> type = new ComboBox<TestRecommendation>();
		type.setItems(FXCollections.observableArrayList(p.getTests()));
		
		if (type.getItems().isEmpty()) {
			type.setDisable(true);
			Label noTests = new Label("Please ask your doctor to recommend a test for you!");
			noTests.setWrapText(true);
			noTests.setMaxWidth(200);
			internal.getChildren().add(noTests);
		}
		
		Label datePrompt = new Label("Pick a date:");
		
		DatePicker dayPicker = new DatePicker();
		dayPicker.setDisable(true);
		
		
		Label sTimeslot = new Label("Selected timeslot:");
		Label dayTimeslot = new Label();
		Label timeTimeslot = new Label();
		Button book = new Button("Book");
		
		
		
		
		internal.getChildren().addAll(testPrompt, type, datePrompt,dayPicker,sTimeslot, dayTimeslot, timeTimeslot, book);
		
		
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
		
		
		type.setOnAction(e -> {
			
			
			//doctor.setDisable(true); // remove when bug testing
			// enable daypicker
			dayPicker.setDisable(false); 
			
			if (testSlotsLoaded) refreshTestSlotPreview(previewSlots,type.getValue().getType(),dayPicker.getValue(), dayTimeslot, timeTimeslot, availableTitle);
			
		});
		
		
		
		dayPicker.setOnAction(e -> {
			
			
			// might have to make it so this code is called again when the doctor / dept is changed after a date is selected
			testSlotsLoaded = true;
			refreshTestSlotPreview(previewSlots,type.getValue().getType(),dayPicker.getValue(), dayTimeslot, timeTimeslot, availableTitle);
		
		});
		
		
		
		// (this includes generating the timeslots lol)
		
		book.setOnAction(e -> {
			
			
			if (selectedTestSlot != null) {
				
				// add booking
				//GUI.datab.addAppointment(signedIn, doctor.getValue(), selectedApptSlot);
				
				p.getTests().remove(type.getValue());
				
				GUI.datab.addTest(p,selectedTestSlot, type.getValue());
				
				// remove timeslot from preview
				
				previewSlots.getChildren().remove(selectedTestElement);
				
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
	
	
	
public Pane generateTestRecommendingPanel () {
		
		//BorderPane pane = new BorderPane();
		
		BorderPane canvas = new BorderPane();
		
		//pane.setManaged(false);
		
		//pane.setMaxWidth(760);
		//pane.setMinWidth(760);
		
		VBox internal = new VBox();
		
		internal.setMaxWidth(760);
		internal.setMinWidth(760);
		
		Label title = new Label("Recommend a Test");
		title.setFont(new Font(19));
		

		
		// Build the labels and textfields for their corresponding thing
	
		Label promptText = new Label("Your test recommendation has been sent!");
		
		Label typeTest = new Label("Type of test:");
	
		ComboBox<TestType> type = new ComboBox<TestType>();
		type.setItems(FXCollections.observableArrayList(TestType.values()));
		
		type.setOnAction(e-> {
			internal.getChildren().remove(promptText);
		});
		
		Label reasonTest = new Label("Reason for the test");
		
		TextField reason = new TextField();
		
		Label prompt = new Label("Pick a recent or upcoming patient of yours:");
		
		ComboBox<Patient> possiblePatients = new ComboBox<Patient>();
		
		possiblePatients.setItems(FXCollections.observableArrayList(GUI.datab.getRecentPatients(signedIn, LocalDate.now())));
		
		Button recommend = new Button("Recommend test");
		
		internal.getChildren().addAll(typeTest,type,reasonTest,reason,prompt,possiblePatients, recommend);
		
		recommend.setOnAction(e -> {
			// read entered data
			
			TestType typeOfTest = type.getValue();
			String reasonForTest = reason.getText();
			Patient selectedPatient = possiblePatients.getValue();
			
			// recommend the test
			
			selectedPatient.recommendTest(new TestRecommendation(typeOfTest, reasonForTest));
			
			// clear the fields
			
			type.setValue(null);
			reason.clear();
			possiblePatients.setValue(null);
			
			// update the database
			try {
				GUI.datab.saveData("data/system.dat");
				internal.getChildren().add(promptText);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			
		});
		
		// Styling
		
		title.setPadding(new Insets(15,0,5,15));
		
		canvas.setTop(title);
		canvas.setCenter(internal);
		
		
		internal.setPadding(new Insets(0,15,0,15));
		internal.setSpacing(5);
		
		VBox.setMargin(recommend, new Insets(8,0,0,0));
		
		
		return canvas;
		
		}

		

public BorderPane generateManageUsersPanel() {
	
	BorderPane canvas = new BorderPane();
	
	canvas.setMaxWidth(760);
	canvas.setMinWidth(760);
	
	// add the first bits / no fields
	
	Label title = new Label("Manage Users");
	title.setFont(new Font(19));
	
	VBox leftContainer = new VBox(8);
	
	BorderPane centerContainer = new BorderPane();
	
	ListView<User> usersList = new ListView<User>();
	
	BorderPane bottomContainer = new BorderPane();
	
	// components for left container
	
	Label userLabel = new Label("Pick a user type:");
	
	ChoiceBox<Roles> userType = new ChoiceBox<Roles>();
	userType.setItems(FXCollections.observableArrayList(Roles.values()));
	
	Label actionLabel = new Label("Pick an action:");
	
	ChoiceBox<Action> actionType = new ChoiceBox<Action>();
	actionType.setItems(FXCollections.observableArrayList(Action.values()));
	
	Label prompt = new Label();
	prompt.setMaxWidth(75);
	prompt.wrapTextProperty().setValue(true);
	
	Button refresh = new Button("Reset");
	
	refresh.setDisable(true);
	
	leftContainer.getChildren().addAll(userLabel,userType,actionLabel,actionType,prompt, refresh);
	
	refresh.setOnAction(e-> {
		userType.setValue(null);
		actionType.setValue(null);
		//bottomContainer.setCenter(generateEditInfoAdminPanel(usersList.getSelectionModel().getSelectedItem(),userType.getValue(),actionType.getValue()));
		//refreshListView(userType.getValue(),usersList);
		
		
		userType.setDisable(false);
		actionType.setDisable(false);
		
		bottomContainer.setCenter(null);
		usersList.setItems(null);
	});
	
	
	// components for centerContainer
	
	centerContainer.setCenter(usersList);
	usersList.setDisable(true);
	
	// code for the bottom -- edit / enter info components
	
	actionType.setDisable(true);
	
	userType.setOnAction(e -> {
		if (userType.getValue() != null) {
			 actionType.setDisable(false);
			 userType.setDisable(true);
		} else {
			// now action type is enabled so if this is changed, we need to refresh
			
		}
			
	});
	
	
	actionType.setOnAction(e -> {
		Action act = actionType.getValue();
		if (act == Action.ADD) {
			bottomContainer.setCenter(generateEditInfoAdminPanel(usersList.getSelectionModel().getSelectedItem(),userType.getValue(),actionType.getValue(), usersList));
			prompt.setText("Please fill in the details below!");
			
		} else {
			prompt.setText("Please select a user!");
		}
		
		Roles role=  userType.getValue();
		
		if (role == Roles.DOCTOR) {
			usersList.setItems(FXCollections.observableArrayList(GUI.datab.doctors));
		} else if (role == Roles.PATIENT) {
			usersList.setItems(FXCollections.observableArrayList(GUI.datab.patients));
		} else if (role == Roles.ADMIN) {
			usersList.setItems(FXCollections.observableArrayList(GUI.datab.administrators));
		} else if (role == Roles.PENDING) {
			usersList.setItems(FXCollections.observableArrayList(GUI.datab.pendingRegistrations));
		}
		
		usersList.setDisable(false);
		
		actionType.setDisable(true);
		
		refresh.setDisable(false);
		
	});
	
	usersList.setOnMouseClicked(e -> {
		if (usersList.getSelectionModel().isEmpty()) {
			// do nothing
		} else {
			usersList.getSelectionModel();
			
			bottomContainer.setCenter(generateEditInfoAdminPanel(usersList.getSelectionModel().getSelectedItem(),userType.getValue(),actionType.getValue(), usersList));
			
		}
	});
	
	
	// Styling / spacing
	
	title.setPadding(new Insets(15,0,5,15));
	
	canvas.setTop(title);
	canvas.setLeft(leftContainer);
	canvas.setCenter(centerContainer);
	canvas.setBottom(bottomContainer);
	
	centerContainer.setMinSize(617,162);
	centerContainer.setMaxSize(617,162);
	
	leftContainer.setPadding(new Insets(10,0,15,15));
	
	
	
	
	return canvas; 
}
		


public BorderPane generateEditInfoAdminPanel (User user, Roles role, Action action, ListView<User> lv) {
	
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
	
	Label title = new Label(action + " " + role);
	title.setFont(new Font(19));
	
	ChoiceBox<Department> dept = new ChoiceBox<Department>();
	dept.setItems(FXCollections.observableArrayList(Department.values()));

	//Button refresh = new Button("Refresh");
	
	// Build the labels and textfields for their corresponding thing

	if (action == Action.EDIT) {
		
		String username = user.getName();
		
		title.setText("Edit " + username + "'s Information");
		
		TextField[] tfs = {new TextField(user.getName()), new TextField(user.getEmailAddress()), new TextField(user.getPhoneNumber())};
		
		int ind = 0;
		
		for (String item : list) {
			Label itemTitle = new Label(user + "'s " + item);
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
		
		HBox checkBoxes = new HBox(8);
		// doctor specific elements
		if (role == Roles.DOCTOR) {
			
			
			Label daysLabel = new Label("Days you wish to take appointments: (only affects future bookings)");
			internal.getChildren().add(daysLabel);
			
			Doctor signInCopy = (Doctor) user;
			List<Boolean> wdays = signInCopy.workingDays;
			
			
			int j = 0;
			for (String item : days) {
				
				CheckBox dayCheck = new CheckBox(item);
				
				dayCheck.setSelected(wdays.get(j));
				
				checkBoxes.getChildren().add(dayCheck);
				
				j++;
				
			}
			internal.getChildren().add(checkBoxes);
			
			Label appointmentHours = new Label("Appointment hours (list in intervals, ex: (8-11) use military time, separate with commas.");
			
			TextField appointmentSet = new TextField();
			appointmentSet.setPromptText("(8-11),(13-15)");
			
			internal.getChildren().addAll(appointmentHours, appointmentSet);
			
		}

		Button update = new Button("Update " + user + "'s information");
		
		update.setOnAction(e -> {
			// read entered data
				
			TextField tf = (TextField) internal.getChildren().get(1);				
			user.setName(tf.getText());
			
			tf = (TextField) internal.getChildren().get(3);
			user.setEmailAddress(tf.getText());	

			tf = (TextField) internal.getChildren().get(5);
			user.setPhoneNumber(tf.getText());
			
			PasswordField pf = (PasswordField) internal.getChildren().get(7);
			if (!pf.getText().contentEquals("")) {
				user.setPassword(pf.getText());
			}
			
			// read the days of week selected if Doctor
			
			if (role == Roles.DOCTOR) {
				// iterate over the elements
				
				Doctor signInCopy = (Doctor) user;
				List<Boolean> wdays = new ArrayList<Boolean>(Arrays.asList(new Boolean[7]));
				
				for (int i = 0; i < 7; i++) {
					CheckBox cBox = (CheckBox) checkBoxes.getChildren().get(i);
					wdays.set(i, cBox.isSelected());
				}
				
				signInCopy.setWorkingDays(wdays);
				
				
			}
			
			// update the database
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
		
		
		internal.setPadding(new Insets(0,15,20,15));
		internal.setSpacing(5);
		
		VBox.setMargin(update, new Insets(8,0,0,0));
		
		
		return canvas;
		
		
	} else if (action == Action.DELETE) {
		String username = user.getName();
		title.setText("Are you sure you want to delete " + username + " from the system?");
		
		Button confirm = new Button("Yes, delete this user");
		
		confirm.setOnAction(e -> {
			if (role == Roles.DOCTOR) {
				
			} else if (role == Roles.PATIENT) {
				GUI.datab.patients.remove(user);
			}  else if (role == Roles.ADMIN) {
				GUI.datab.administrators.remove(user);
			} else if (role == Roles.PENDING ) {
				GUI.datab.pendingRegistrations.remove(user);
			}
			
			try {
				GUI.datab.saveData(Database.filepath);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			refreshListView(role, lv);
		});
		
		
		internal.getChildren().add(confirm);
		
		// Styling
		
		title.setPadding(new Insets(15,0,20,15));
		
		canvas.setTop(title);
		canvas.setCenter(internal);
		
		
		internal.setPadding(new Insets(0,15,0,15));
		internal.setSpacing(5);
		
		
		return canvas;
		
	} else if (action == Action.ADD) {
		
		// generate edit user field but empty
		
		
		if (role == Roles.PENDING) {
			
			
			
			
			Button approve = new Button("Approve");
			Button deny = new Button("Deny");
			
			// set actions
			
			approve.setOnAction(e-> {
				// create a patient from their information
				// (other users can only be added by an admin thru add)
				Patient toAdd = new Patient(user.getName(), user.getEmailAddress(), user.getPhoneNumber(), user.getPassword());
				
				// add the patient to the database
				
				GUI.datab.patients.add(toAdd);
				GUI.datab.pendingRegistrations.remove(user);
				refreshListView(role, lv);
				try {
					GUI.datab.saveData(Database.filepath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			});
			
			deny.setOnAction(e->{
				GUI.datab.pendingRegistrations.remove(user);
				refreshListView(role, lv);
			});
			
			internal.getChildren().addAll(approve, deny);
			
			title.setPadding(new Insets(15,0,20,15));
			
			canvas.setTop(title);
			canvas.setCenter(internal);
			
			
			internal.setPadding(new Insets(0,15,0,15));
			internal.setSpacing(5);
			
		} else {		
			
			
			title.setText("Enter Information for the New " + role);
			
			TextField[] tfs = {new TextField(), new TextField(), new TextField()};
			
			int ind = 0;
			
			for (String item : list) {
				Label itemTitle = new Label("New User's " + item);
				if (!item.contentEquals("Password")) {
					
					tfs[ind].setPromptText(item);
					// add the components to the VBox
					internal.getChildren().addAll(itemTitle,tfs[ind]);
				} else {
					PasswordField pfield = new PasswordField();
					pfield.setPromptText("Enter a new password.");
					
					internal.getChildren().addAll(itemTitle,pfield);
				}
				
				ind++;
			}
			
			HBox checkBoxes = new HBox(8);
			// doctor specific elements
			if (role == Roles.DOCTOR) {
				
				
				Label daysLabel = new Label("Days the new user will take appointments:");
				internal.getChildren().add(daysLabel);
				
				
				boolean[] workdays = new boolean[7];
				Arrays.fill(workdays, true);
				
				int j = 0;
				for (String item : days) {
					
					CheckBox dayCheck = new CheckBox(item);
					
					dayCheck.setSelected(workdays[j]);
					
					checkBoxes.getChildren().add(dayCheck);
					
					j++;
					
				}
				internal.getChildren().add(checkBoxes);
				
				Label appointmentHours = new Label("Appointment hours (list in intervals, ex: (8-11) use military time, separate with commas.");
				
				TextField appointmentSet = new TextField();
				appointmentSet.setPromptText("(8-11),(13-15)");
				
				internal.getChildren().addAll(appointmentHours, appointmentSet);
				
				Label deptLabel = new Label("Choose a department for the new doctor:");
				
				internal.getChildren().addAll(deptLabel,dept);

			}

			Button update = new Button("Add new " + role.toString().toLowerCase() +" to system");
			
			update.setOnAction(e -> {
				// read entered data
					
				TextField tf = (TextField) internal.getChildren().get(1);	
				String name = tf.getText();
				//user.setName(tf.getText());
				
				tf = (TextField) internal.getChildren().get(3);
				String email = tf.getText();
				//user.setEmailAddress(tf.getText());	

				tf = (TextField) internal.getChildren().get(5);
				String phoneNumber = tf.getText();
				//user.setPhoneNumber(tf.getText());
				
				
				String password = "";
				PasswordField pf = (PasswordField) internal.getChildren().get(7);
				if (!pf.getText().contentEquals("")) {
					password = (pf.getText());
				}
				
				// read the days of week selected if Doctor
				
				if (role == Roles.DOCTOR) {
					// iterate over the elements
					boolean fieldsEntered = name.length() > 0 && email.length() > 0 && phoneNumber.length() > 0 && password.length() > 0;
					// create the new patient / admin
					// create the new doctor
					//Doctor signInCopy = (Doctor) signedIn;
					
					if (fieldsEntered) {
						int defaultHours[][] = {{8,12}, {13,16}};
						
						Doctor add = new Doctor(defaultHours,dept.getValue(), name, email,phoneNumber,password);
						
						List<Boolean> wdays = new ArrayList<Boolean>(Arrays.asList(new Boolean[7]));
						
						for (int i = 0; i < 7; i++) {
							CheckBox cBox = (CheckBox) checkBoxes.getChildren().get(i);
							wdays.set(i, cBox.isSelected());
						}
						
						add.setWorkingDays(wdays);
						
						GUI.datab.doctors.add(add);
					} else {
						title.setText("Looks like you missed a field! Please try again.");
					}
					

					
				} else {
					
					
					boolean fieldsEntered = name.length() > 0 && email.length() > 0 && phoneNumber.length() > 0 && password.length() > 0;
					// create the new patient / admin
					
					if (!fieldsEntered) {
						title.setText("Looks like you missed a field! Please try again.");
					} else {
						if (role == Roles.ADMIN && fieldsEntered) {
							
							Admin add = new Admin(name, email, phoneNumber,password);
							GUI.datab.administrators.add(add);
							
						} else if (role == Roles.PATIENT && fieldsEntered) {
							Patient add = new Patient(name, email, phoneNumber,password);
							GUI.datab.patients.add(add);
						}
					}
					

					
					
				}
				
				// update the database
				try {
					GUI.datab.saveData("data/system.dat");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				refreshListView(role, lv);
				
			});
			
			internal.getChildren().add(update);
			
			// Styling
			
			title.setPadding(new Insets(15,0,5,15));
			
			canvas.setTop(title);
			canvas.setCenter(internal);
			
			
			internal.setPadding(new Insets(0,15,20,15));
			internal.setSpacing(5);
			
			VBox.setMargin(update, new Insets(8,0,0,0));
			
			
			
			return canvas;
			
		}
		
		
	}
	
	return canvas;
	
	
}
		
		
		
	public void refreshListView(Roles role, ListView<User> lv) {
		if (role == Roles.DOCTOR) {
			lv.setItems(FXCollections.observableArrayList(GUI.datab.doctors));
		} else if (role == Roles.PATIENT) {
			lv.setItems(FXCollections.observableArrayList(GUI.datab.patients));
		} else if (role == Roles.ADMIN) {
			lv.setItems(FXCollections.observableArrayList(GUI.datab.administrators));
		} else if (role == Roles.PENDING) {
			lv.setItems(FXCollections.observableArrayList(GUI.datab.pendingRegistrations));
		}
		
	}
	
	
	
	public BorderPane generateCancelBookingPanel() {
		
		
		BorderPane canvas = new BorderPane();
		
		// outer components
		
		Label title = new Label("Cancel a Booking");
		title.setFont(new Font(19));
		
		
		VBox internal = new VBox(8);
		
		// Internal components
		
		Label yourBookings = new Label("Your bookings:");
			
		ListView<Booking> book = new ListView<Booking>();
		book.setMaxHeight(200);
		book.setItems(FXCollections.observableArrayList(GUI.datab.getBookings(signedIn)));
		
		Label areYouSure = new Label("Are you sure you want to delete this booking?");
		
		Label bookingDetails = new Label();
		
		Button confirm = new Button("Confirm");
		
		
		internal.getChildren().addAll(yourBookings,book);
		
		// event handling logic
		
		book.setOnMouseClicked(e -> {
			
			Booking b = book.getSelectionModel().getSelectedItem();
			
			if (b != null) {
				selectDelete = b;
				bookingDetails.setText(selectDelete.toString());
			}

			
			if (!internal.getChildren().contains(areYouSure) && !book.getSelectionModel().isEmpty()) {
				internal.getChildren().addAll(areYouSure,bookingDetails, confirm);
			}
			

			
		});
		
		confirm.setOnAction(e -> {
			
			if (book.getSelectionModel().getSelectedItem() != null) {
				int index = book.getSelectionModel().getSelectedIndex();
				int bookingID = signedIn.bookingIDs.get(index);
				signedIn.bookingIDs.remove((Object) bookingID);
				
				
				Booking b = GUI.datab.bookings.get(bookingID);
				String typeOf = b.getClass().getName().substring(12);
				
				if (typeOf.contentEquals("Appointment")) {
				Appointment a = (Appointment) b;
				
				a.getDoctor().getBookingIDs().remove((Object) bookingID);
				
				}
				

				internal.getChildren().remove(areYouSure);
				internal.getChildren().remove(bookingDetails);
				internal.getChildren().remove(confirm);
				
				book.getSelectionModel().clearSelection();

				
				try {
					
					GUI.datab.saveData(Database.filepath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				bookings.setItems(GUI.datab.getBookings(signedIn));
				book.setItems(FXCollections.observableList(GUI.datab.getBookings(signedIn)));
				
				manager.getChildren().set(1, generateBookAppointmentPanel()) ;
				
			}
			
			// clear everything else
			
			
		});
		
		
		// alignment
		title.setPadding(new Insets(15,0,5,15));
		
		canvas.setTop(title);
		canvas.setCenter(internal);
		
		
		internal.setPadding(new Insets(0,15,20,15));
		internal.setSpacing(5);
		
		
		
		
		return canvas;
		
		
	}
	
	
	
}





