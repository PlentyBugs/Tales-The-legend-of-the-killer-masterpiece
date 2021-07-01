package support;

public interface Sellable {
    String getName();
    default String getItemProperty() {return "";}
}
