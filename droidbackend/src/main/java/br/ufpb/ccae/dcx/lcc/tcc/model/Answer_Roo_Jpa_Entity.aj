// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.ccae.dcx.lcc.tcc.model;

import br.ufpb.ccae.dcx.lcc.tcc.model.Answer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Answer_Roo_Jpa_Entity {
    
    declare @type: Answer: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Answer.id;
    
    @Version
    @Column(name = "version")
    private Integer Answer.version;
    
    public Long Answer.getId() {
        return this.id;
    }
    
    public void Answer.setId(Long id) {
        this.id = id;
    }
    
    public Integer Answer.getVersion() {
        return this.version;
    }
    
    public void Answer.setVersion(Integer version) {
        this.version = version;
    }
    
}
