package br.ufpb.ccae.dcx.lcc.tcc.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Location {

    /**
     */
    @NotNull
    private String description;

    /**
     */
    private Double latitude;

    /**
     */
    private Double longitude;

    /**
     */
    private Double radius;
}
