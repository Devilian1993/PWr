package visitor;

import items.*;

public interface KitchenVisitor {
    void visit(Fork fork);
    void visit(Spoon spoon);
    void visit(Glass glass);
}
