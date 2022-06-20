package sbs.baka.cheetah.checker;

public class AccountCombo {

    public String user, pass;

    public boolean working;
    public Object[] data;

    public AccountCombo(String line) {
        if(line.indexOf(':') != -1) {
            String[] sides = line.split(":");

            user = sides[0];
            pass = sides[1];
        } else {
            throw new RuntimeException("Failed to parse combo");
        }
    }

    public AccountCombo(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public void setData(Object...objs) {
        this.data = objs;
    }

    public void printData() {
        for (Object datum : data)
            System.out.print(datum.toString() + (datum.equals(data.length - 1) ? "" : "..."));
    }

    @Override
    public String toString() {
        return this.user + ":" + this.pass;
    }
}
