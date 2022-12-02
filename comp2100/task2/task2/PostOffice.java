package task2;

public class PostOffice {

	public PostOffice() {
	}

	public void registerParcel(Parcel parcel) {
		State.REGISTERED.setStatus(parcel);
	}
}
