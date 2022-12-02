public class WaitingToDispense extends State{

    public WaitingToDispense(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void handle(Event event) {

        if(event.equals(Event.ItemDispensed)){
            machine.setState(new ReadyToCollect(machine));
        }
    }
}
