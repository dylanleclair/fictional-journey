package application;

public enum Roles {

	DOCTOR,
	PATIENT,
	PENDING,
	ADMIN;

	public String toString() {
		if (this == DOCTOR) {
			return "Doctor";
		} else if (this == PATIENT) {
			return "Patient";
		} else if (this == ADMIN) {
			return "Admin";
		} else if (this == PENDING) {
			return "Pending";
		}
		return null;
	}
}

