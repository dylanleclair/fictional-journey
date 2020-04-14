package application;

import java.time.LocalDateTime;

public class Test extends Booking {

	private TestType typeOfTest;
	private String reason;
	
	public Test(LocalDateTime startTime) {
		super(startTime, Department.DIAGNOSTICS);
	}

	public Test(TimeSlot slot, TestType type) {
		super(slot);
		typeOfTest = type;
		this.location = Department.DIAGNOSTICS;
	}

	public TestType getTypeOfTest() {
		return typeOfTest;
	}

	public void setTypeOfTest(TestType typeOfTest) {
		this.typeOfTest = typeOfTest;
	}
	
	
	
}
