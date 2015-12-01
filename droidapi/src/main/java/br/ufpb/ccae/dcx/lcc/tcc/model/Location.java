package br.ufpb.ccae.dcx.lcc.tcc.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.json.RooJson;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class Location {

    /**
     */
    @OneToMany(targetEntity = Challenge.class, cascade = CascadeType.ALL, mappedBy = "location")
    private Set<Challenge> challenges = new HashSet<Challenge>();
    
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
