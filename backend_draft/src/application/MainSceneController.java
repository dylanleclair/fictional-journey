package application;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.GroupLayout.Alignment;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MainSceneController {

	private Database activeDB;
	private LocalDate startDate;
	private User signedIn;
	
    @FXML
    private MenuBar menuBar;

    private LocalDate[] dates;


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



	public Button getLastWeek() {
		return lastWeek;
	}



	public void setLastWeek(Button lastWeek) {
		this.lastWeek = lastWeek;
	}



	public Button getNextWeek() {
		return nextWeek;
	}



	public void setNextWeek(Button nextWeek) {
		this.nextWeek = nextWeek;
	}



	public Label getWeekLabel() {
		return WeekLabel;
	}



	public void setWeekLabel(Label weekLabel) {
		WeekLabel = weekLabel;
	}



	public Label getDaySunday() {
		return daySunday;
	}



	public void setDaySunday(Label daySunday) {
		this.daySunday = daySunday;
	}



	public Label getDayWednesday() {
		return dayWednesday;
	}



	public void setDayWednesday(Label dayWednesday) {
		this.dayWednesday = dayWednesday;
	}



	public Label getDayMonday() {
		return dayMonday;
	}



	public void setDayMonday(Label dayMonday) {
		this.dayMonday = dayMonday;
	}



	public Label getDayTuesday() {
		return dayTuesday;
	}



	public void setDayTuesday(Label dayTuesday) {
		this.dayTuesday = dayTuesday;
	}



	public Label getDayThursday() {
		return dayThursday;
	}



	public void setDayThursday(Label dayThursday) {
		this.dayThursday = dayThursday;
	}



	public Label getDayFriday() {
		return dayFriday;
	}



	public void setDayFriday(Label dayFriday) {
		this.dayFriday = dayFriday;
	}



	public Label getDaySaturday() {
		return daySaturday;
	}



	public void setDaySaturday(Label daySaturday) {
		this.daySaturday = daySaturday;
	}

    
    public void init() {
    	
    	this.setName(signedIn.name);
    	this.setRole(signedIn.title);
    	this.setBookings(GUI.datab.getBookings(signedIn));
    	
    	LocalDate today = LocalDate.now();
    	LocalDate startOfWeek = LocalDate.now();
    	DayOfWeek dayOfWeek = today.getDayOfWeek();
    	
    	// backtrack until we reach the previous sunday
    	while (dayOfWeek != DayOfWeek.SUNDAY) {
    		startOfWeek = startOfWeek.minusDays(1);
    		dayOfWeek = startOfWeek.getDayOfWeek();
    	}
    	

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

    /**
     * After clearing the specified GridPane, this is called to add the times back into it, before adding the appointment panes in.
     * @param pane
     */
    public void populateGridPaneTimes(GridPane pane) {
    	
    }
}