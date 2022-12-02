package task2;

public class Recipient {

	public Recipient() {
	}

	public void receive(Parcel parcel) {
		// TODO
		// START YOUR CODE
		if (parcel.getRecipient().equals(this)){
			parcel.setStatus(State.DELIVERED);
		}
		if (parcel.getRecipient()!=this){
			parcel.setStatus(State.RETURNED);

		}

		parcel.setStatus(State.DELIVERED);
//		parcel.setStatus(State.RETURNED);

		// END YOUR CODE
	}
}
