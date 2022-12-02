package task2;

public class TransportOffice {

	private static TransportOffice instance;

	private TransportOffice() {
		instance = null;
	}

	public static TransportOffice getInstance() {

		// TODO
		// START YOUR CODE

		// YOU ARE ALLOWED TO CHANGE THIS RETURNED VALUE
		if(instance==null){
			instance = new TransportOffice();
		}
		return instance;

		// END YOUR CODE
	}

	public Transport transitBy(Parcel parcel) {

		// TODO
		// START YOUR CODE
		parcel.setStatus(State.IN_TRANSIT);

		// YOU ARE ALLOWED TO CHANGE THIS RETURNED VALUE
		if(parcel.getAllowedDays()<=3){
			if(parcel.getWeight()<=22){
				return Transport.AIR_CARRIER;
			} else {

			}
		} else if (parcel.getAllowedDays()<=6){

			return Transport.TRAIN;
		} else if  (parcel.getAllowedDays()<=8) {
			if(parcel.getWeight()<=40){
				return Transport.VAN;
			} else {
				return Transport.TRAIN;
			}
		} else {
			if (parcel.getWeight()<=40){
				return Transport.VAN;
			} else {
				return Transport.TRAIN;
			}
		}
		parcel.setStatus(State.RETURNED);
		return null;
		// END YOUR CODE
	}
}
