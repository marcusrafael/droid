// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.ccae.dcx.lcc.tcc.model;

import br.ufpb.ccae.dcx.lcc.tcc.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.model.Location;
import java.util.Set;

privileged aspect Location_Roo_JavaBean {
    
    public Set<Challenge> Location.getChallenges() {
        return this.challenges;
    }
    
    public void Location.setChallenges(Set<Challenge> challenges) {
        this.challenges = challenges;
    }
    
    public String Location.getDescription() {
        return this.description;
    }
    
    public void Location.setDescription(String description) {
        this.description = description;
    }
    
    public Double Location.getLatitude() {
        return this.latitude;
    }
    
    public void Location.setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double Location.getLongitude() {
        return this.longitude;
    }
    
    public void Location.setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public Double Location.getRadius() {
        return this.radius;
    }
    
    public void Location.setRadius(Double radius) {
        this.radius = radius;
    }
    
}
