package br.ufpb.ccae.dcx.lcc.tcc.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.json.RooJson;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Challenge {

    /**
     */
    @NotNull
    private String description;

    /**
     */
    @ManyToOne
    private Location location;
}
