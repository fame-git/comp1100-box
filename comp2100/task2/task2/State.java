package task2;

public enum State {

	ORDERED, REGISTERED, IN_TRANSIT, DELIVERED, RETURNED;

	private boolean called;

	public void setStatus(Parcel parcel) {
		parcel.setStatus(this);

		if (this == State.IN_TRANSIT || this == State.DELIVERED || this == State.RETURNED) {
			called = true;
		}
	}

	public boolean isCalled() {
		return called;
	}
}
