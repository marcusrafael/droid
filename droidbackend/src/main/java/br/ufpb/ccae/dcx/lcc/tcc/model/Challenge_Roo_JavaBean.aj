// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.ccae.dcx.lcc.tcc.model;

import br.ufpb.ccae.dcx.lcc.tcc.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.model.Location;

privileged aspect Challenge_Roo_JavaBean {
    
    public String Challenge.getDescription() {
        return this.description;
    }
    
    public void Challenge.setDescription(String description) {
        this.description = description;
    }
    
    public Location Challenge.getLocation() {
        return this.location;
    }
    
    public void Challenge.setLocation(Location location) {
        this.location = location;
    }
    
}
