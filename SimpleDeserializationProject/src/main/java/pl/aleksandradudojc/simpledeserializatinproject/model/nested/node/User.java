package pl.aleksandradudojc.simpledeserializatinproject.model.nested.node;


import pl.aleksandradudojc.simpledeserializatinproject.model.nested.node.leafs.EdgeFollow;
import pl.aleksandradudojc.simpledeserializatinproject.model.nested.node.leafs.EdgeFollowedBy;

public class User {
    private String biography;
    private String full_name;
    private EdgeFollowedBy edge_followed_by;
    private EdgeFollow edge_follow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public EdgeFollowedBy getEdge_followed_by() {
        return edge_followed_by;
    }

    public void setEdge_followed_by(EdgeFollowedBy edge_followed_by) {
        this.edge_followed_by = edge_followed_by;
    }

    public EdgeFollow getEdge_follow() {
        return edge_follow;
    }

    public void setEdge_follow(EdgeFollow edge_follow) {
        this.edge_follow = edge_follow;
    }
}
