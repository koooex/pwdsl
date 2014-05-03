package dsl;

public class AbstractEvent {
    private String name, code;

    public AbstractEvent(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
