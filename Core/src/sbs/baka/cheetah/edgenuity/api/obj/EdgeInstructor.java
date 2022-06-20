package sbs.baka.cheetah.edgenuity.api.obj;

import java.util.*;

public class EdgeInstructor {

    private String first, last;
    private UUID id;

    public EdgeInstructor(String first, String last, UUID id) {
        this.first = first;
        this.last = last;
        this.id = id;
    }
    public EdgeInstructor(String name, UUID id) {
        String[] strArr;
        if((strArr = name.split(" ")).length >= 2) {
            this.first = strArr[0];
            this.last = strArr[1];
        }
        this.id = id;
    }
    public EdgeInstructor(String name, String uuid) {
        String[] strArr;
        if((strArr = name.split(" ")).length == 2) {
            this.first = strArr[0];
            this.last = strArr[1];
        }
        this.id = UUID.fromString(uuid);
    }

    public String getName() { return first + " " + last; }
    public String getOrderedName() { return last + ", " + first; }
    public String getFirst() { return first; }
    public String getLast() { return last; }
    public UUID getId() { return id; }
}
