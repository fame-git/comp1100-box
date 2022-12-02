public class ReadyToCollect extends State{

    public ReadyToCollect(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void handle(Event event) {
        if(event.equals(Event.Timeout)){
            machine.setState(new Idle(machine));
        }
    }
}
