package am.tarento.oditools.commands;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Rebuild {

    private boolean connected= false;

    @ShellMethod(key = "connect", value ="Connect to a specific environment.")
    public String connect(
            @ShellOption(value = {"-t"}) String target)
    {
        connected = true;
        return target;
    }


    @ShellMethod(key= "rebuild", value = "Rebuild the scenarios in the bom file")
    public String rebuild(
            @ShellOption(value = {"-b"}) String bom){
        return bom;
    }

    @ShellMethodAvailability({"rebuild"})
    public Availability availabilityCheck() {
        return connected
                ? Availability.available()
                : Availability.unavailable("you are not connected to any environmen");
    }
}
