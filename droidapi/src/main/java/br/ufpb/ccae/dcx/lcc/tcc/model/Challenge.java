package br.ufpb.ccae.dcx.lcc.tcc.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.json.RooJson;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class Challenge {

    /**
     */
    @OneToMany(targetEntity = Answer.class, cascade = CascadeType.ALL, mappedBy = "challenge")
    private Set<Answer> answers = new HashSet<Answer>();
    
    /**
     */
    @NotNull
    private String description;

    /**
     */
    @ManyToOne
    private Location location;

    /**
     */
    @NotNull
    private String difficulty;

}
