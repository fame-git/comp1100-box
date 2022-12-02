public class WaitingToPay extends State{

    public WaitingToPay(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void handle(Event event) {
        if(event.equals(Event.ItemPaid)){
            machine.setState(new WaitingToDispense(machine));
        }

        if(event.equals(Event.Cancelled) || event.equals(Event.Timeout)){
            machine.setState(new Idle(machine));
        }
    }
}
