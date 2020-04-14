package application;

public enum TestType {
	XRAY,
	CTSCAN,
	MRI,
	BLOODTEST;
	
	public String toString () {
	
		if (this == XRAY) {
			return "Xray";
		} else if (this == CTSCAN) {
			return "CT Scan";
		} else if (this == MRI) {
			return "MRI";
		} else if (this == BLOODTEST) {
			return "Blood Test";
		}
		
		
		return null;
	}
}
