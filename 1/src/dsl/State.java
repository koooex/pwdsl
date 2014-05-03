package dsl;

import java.util.*;

public class State {
    private String name;
    private List<Command> actions = new ArrayList<Command>();
    private Map<String, Transition> transitions = new HashMap<String, Transition>();

    public State(String name) {
        this.name = name;
    }

    public void addAction(Command command) {
        this.actions.add(command);
    }

    public void addTransition(Event event, State target) {
        assert null != target;
        this.transitions.put(event.getCode(), new Transition(this, event, target));
    }

    Collection<State> getAllTargets() {
        List<State> result = new ArrayList<State>();
        for(Transition t : this.transitions.values())
            result.add(t.getTarget());
        return result;
    }

    public boolean hasTransition(String code) {
        return this.transitions.containsKey(code);
    }

    public State getTarget(String code) {
        return this.transitions.get(code).getTarget();
    }

    public void execute(Channel channel) {
        for(Command c : this.actions)
            channel.send(c.getCode());
    }
}
