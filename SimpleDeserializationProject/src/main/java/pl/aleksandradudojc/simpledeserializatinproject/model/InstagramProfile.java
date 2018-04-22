package pl.aleksandradudojc.simpledeserializatinproject.model;


import pl.aleksandradudojc.simpledeserializatinproject.model.nested.GraphQL;

public class InstagramProfile {

    private String show_suggested_profiles;

    private GraphQL graphql;






    public GraphQL getGraphql() {
        return graphql;
    }

    public void setGraphql(GraphQL graphql) {
        this.graphql = graphql;
    }

    public String getShow_suggested_profiles() {
        return show_suggested_profiles;
    }

    public void setShow_suggested_profiles(String show_suggested_profiles) {
        this.show_suggested_profiles = show_suggested_profiles;
    }
}
