package hr.tvz.eindex.kolegij;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class KolegijCommand {


    @NotNull(message = "Must not be empty")
    private long id;

    @NotBlank(message = "Must not be empty")
    private String name;
}
