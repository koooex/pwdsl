package dsl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateMachine {
    private State current;
    private Channel channel;

    private State start;
    private List<Event> resetEvents = new ArrayList<Event>();

    public StateMachine(State start, Channel channel) {
        this.start = start;
        this.channel = channel;
    }

    public Collection<State> getStates() {
        List<State> result = new ArrayList<State>();
        collectStates(result, start);
        return result;
    }

    private void collectStates(Collection<State> result, State state) {
        if(result.contains(state))
            return;
        for(State next : state.getAllTargets())
            collectStates(result, next);
    }

    public void addResetEvents(Event... events) {
        for(Event e : events) {
            this.resetEvents.add(e);
        }
    }

    private List<String> resetEventCodes() {
        List<String> result = new ArrayList<String>();
        for(Event e : this.resetEvents)
            result.add(e.getCode());
        return result;
    }

    private boolean isResetEvent(String code) {
        return this.resetEventCodes().contains(code);
    }

    private void transit(State target) {
        this.current = target;
        this.current.execute(this.channel);
    }

    public void process(String code) {
        if(this.current.hasTransition(code)) {
            this.transit(this.current.getTarget(code));
        } else if(this.isResetEvent(code)) {
            this.transit(this.start);
        }
    }
}
