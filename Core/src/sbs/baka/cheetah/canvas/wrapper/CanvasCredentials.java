package sbs.baka.cheetah.canvas.wrapper;

public class CanvasCredentials {

    private String url; //https:// <...> .instructure.com
    private String token; //Generated token
    private Long userId; //user id
    private Long[] courses; //courses selected. Ignore dumbass ones we were added to

    public void loadFromConfig() {
        //...
    }

}
