package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Just an example. Make a toString for the GUI to be prettier. 
 * @author Dylan Leclair
 *
 */
public enum Department {
	EMERGENCY("Emergency"),
	CARDIOLOGY("Cardiology"),
	ICU("ICU"),
	IMAGING("Imaging"),
	ENT("ENT"), // Ear nose and throat
	GASTROENTEROLOGY("Gastro"),
	GENERAL("General"),
	FAMILY("Family"),
	MATERNITY("Maternity"),
	NEUROLOGY("Neurology"),
	NUTRITION("Nutrition"),
	OPHTALMOLOGY("Ophtalmology"),
	ORTHOPEDICS("Orthopedics"),
	PHYSIOTHERAPY("Physiotherapy"),
	DIAGNOSTICS("Diagnostics"),
	TBA("Other");
	
	
	private static final List<String> VALUES;
	
	private final String value;

	static {
		VALUES = new ArrayList<>();
		for (Department department : Department.values()) {
			VALUES.add(department.value);
		}
	}

	private Department(String value) {
		this.value = value;
	}
	
    public static List<String> getValues() {
        return Collections.unmodifiableList(VALUES);
    }
    
    public String getValue() {
    	return this.value;
    }
    
    public String toString() {
    	return this.value;
    }
	
}


