package sbs.baka.cheetah.canvas;

import consulting.freiheitsgrade.patched.commons.configuration2.*;
import consulting.freiheitsgrade.patched.commons.configuration2.ex.*;
import sbs.baka.cheetah.canvas.wrapper.*;
import sbs.baka.cheetah.io.*;
import sbs.baka.cheetah.storage.filesys.FileSystem;
import sbs.baka.cheetah.util.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class CanvasAPI implements Configurable {

    private CanvasController controlLib;

    public CanvasAPI() {
        controlLib = new CanvasController("heartland", "13535~m0pyg4609EdY0M228LWEMavT5ij9w4pfDNFYVz47Cz1u0pAZvKIZOoGNyOyHXjGe");
    }

    public List<CanvasCourse> getCourses() {
        return controlLib.getCourses().stream().map(e -> {
            CanvasCourse course;
            //if cache exists, check date or what not
            if(false) {

                return null;
            } else {
                return new CanvasCourse(controlLib, e);
            }
        }).collect(Collectors.toList());
    }

    public Object getCanvasCredentials() {
        /*
            url=https://instance.instructure.com/
            token=11537~nqSvdZ55SkdnlqGyAuk7JHspdjlX7tZrXmjrlDMNLDWj2syiIwp4kwbrwJRS0nXC
            user=73
            courses=26841,26841,26841,26841
         */
        return null;
    }

}
