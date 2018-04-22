package pl.aleksandradudojc.simpledeserializatinproject.model.nested;

import pl.aleksandradudojc.simpledeserializatinproject.model.nested.node.User;

public class GraphQL {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
