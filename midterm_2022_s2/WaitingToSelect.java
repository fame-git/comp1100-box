public class WaitingToSelect extends State {


    public WaitingToSelect(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void handle(Event event) {
        if(event.equals(Event.ItemSelected)){
            machine.setState(new WaitingToPay(machine));
        }

        if(event.equals(Event.Cancelled) || event.equals(Event.Timeout)){
            machine.setState(new Idle(machine));
        }
    }
}
