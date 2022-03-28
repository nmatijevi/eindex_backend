package hr.tvz.eindex.kolegij;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class KolegijCommand {


    @NotNull(message = "Must not be empty")
    private long id;

    @NotBlank(message = "Must not be empty")
    private String name;

    public KolegijCommand(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
