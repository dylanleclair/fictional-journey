package application;

import java.io.Serializable;

public class TestRecommendation implements Serializable {

	
	private TestType type;
	private String reason;
	
	public TestRecommendation () {
		
	}
	
	public TestRecommendation(TestType type, String reason) {
		this.type = type;
		this.reason = reason;
	}
	
	public String toString () {
		return type + ", Reason: " + reason;
	}

	public TestType getType() {
		return type;
	}

	public void setType(TestType type) {
		this.type = type;
	}
	
	
	
}
