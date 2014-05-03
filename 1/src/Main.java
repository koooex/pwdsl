import dsl.*;

public class Main {
    private static class CosoleChannel implements Channel {
        public void send(String code) {
            System.out.println(code);
        }
    }

    public static void main(String[] args) {
        Event doorClosed = new Event("doorClosed", "D1CL");
        Event drawerOpened = new Event("drawOpened", "D2OP");
        Event lightOn = new Event("lightOn", "L1ON");
        Event doorOpened = new Event("doorOpened", "D1OP");
        Event panelClosed = new Event("panelClosed", "PNCL");

        Command unlockPanel = new Command("unlockPanel", "PNUL");
        Command lockPanel = new Command("lockPanel", "PNLK");
        Command lockDoor = new Command("lockDoor", "D1LK");
        Command unlockDoor = new Command("unlockDoor", "D1UL");

        State idle = new State("idle");
        State active = new State("active");
        State waitingForLight = new State("WaitingForLight");
        State waitingForDrawer = new State("WaitingForDrawer");
        State unlockedPanel = new State("unlockedPanel");

        StateMachine machine = new StateMachine(idle, new CosoleChannel());

        idle.addTransition(doorClosed, active);
        idle.addAction(unlockDoor);
        idle.addAction(lockPanel);

        active.addTransition(drawerOpened, waitingForLight);
        active.addTransition(lightOn, waitingForDrawer);

        waitingForLight.addTransition(drawerOpened, unlockedPanel);

        waitingForDrawer.addTransition(lightOn, unlockedPanel);

        unlockedPanel.addAction(unlockPanel);
        unlockedPanel.addAction(lockDoor);
        unlockedPanel.addTransition(panelClosed, idle);

        machine.addResetEvent(doorOpened);

        machine.process("abcd");
    }
}