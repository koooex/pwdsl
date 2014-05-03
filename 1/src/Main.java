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

        Command unlockPanelCommand = new Command("unlockPanel", "PNUL");
        Command lockPanelCommand = new Command("lockPanel", "PNLK");
        Command lockDoorCommand = new Command("lockDoor", "D1LK");
        Command unlockDoorCommand = new Command("unlockDoor", "D1UL");

        State idleState = new State("idle");
        State activeState = new State("active");
        State waitingForLightState = new State("WaitingForLight");
        State waitingForDrawerState = new State("WaitingForDrawer");
        State unlockedPanelState = new State("unlockedPanel");


        idleState.addTransition(doorClosed, activeState);
        idleState.addAction(unlockDoorCommand);
        idleState.addAction(lockPanelCommand);

        waitingForLightState.addTransition(drawerOpened, unlockedPanelState);

        activeState.addTransition(drawerOpened, waitingForLightState);
        activeState.addTransition(lightOn, waitingForDrawerState);

        waitingForDrawerState.addTransition(lightOn, unlockedPanelState);

        unlockedPanelState.addAction(unlockPanelCommand);
        unlockedPanelState.addAction(lockDoorCommand);
        unlockedPanelState.addTransition(panelClosed, idleState);

        StateMachine machine = new StateMachine(idleState, new CosoleChannel());
        machine.addResetEvents(doorOpened);

        machine.process("abcd");
    }
}