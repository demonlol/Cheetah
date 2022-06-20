package sbs.baka.cheetah.checker;

import java.io.*;

public interface AccountChecker {

    boolean check(AccountCombo accountCombo);

    void loadFile(File comboFile);

}
