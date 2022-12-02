package task2;

public class Parcel {

	private final int weight;

	private final int allowedDays;

	private final Sender sender;

	private final Recipient recipient;

	private State status;

	public Parcel(int weight, int allowedDays, Sender sender, Recipient recipient) {
		this.weight = weight;
		this.allowedDays = allowedDays;
		this.sender = sender;
		this.recipient = recipient;
		this.status = State.ORDERED;
	}

	public int getWeight() {
		return weight;
	}

	public int getAllowedDays() {
		return allowedDays;
	}

	public State getStatus() {
		return status;
	}

	public void setStatus(State status) {
		this.status = status;
	}

	public Sender getSender() {
		return sender;
	}

	public Recipient getRecipient() {
		return recipient;
	}
}
