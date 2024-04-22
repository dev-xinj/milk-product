package vn.shortsoft.products.enums;

public enum CategoryPriorityEnum {
    CORE(1), 
    DETAILCORE(2), 
    CHILDCORE(3),
    SMALLCORE(4);

    private int priority;

    private CategoryPriorityEnum(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
