public class Idle extends State {



    public Idle(VendingMachine machine) {
        super(machine);
    }




    @Override
    public void handle(Event event) {
        if(event.equals(Event.AnyButton)){
            machine.setState(new WaitingToSelect(machine));
        }
    }
}
