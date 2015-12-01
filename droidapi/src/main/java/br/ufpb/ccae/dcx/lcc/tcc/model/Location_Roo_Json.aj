// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.ccae.dcx.lcc.tcc.model;

import br.ufpb.ccae.dcx.lcc.tcc.model.Location;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Location_Roo_Json {
    
    public String Location.toJson() {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(this);
    }
    
    public String Location.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(this);
    }
    
    public static Location Location.fromJsonToLocation(String json) {
        return new JSONDeserializer<Location>()
        .use(null, Location.class).deserialize(json);
    }
    
    public static String Location.toJsonArray(Collection<Location> collection) {
        return new JSONSerializer()
        .exclude("*.class").deepSerialize(collection);
    }
    
    public static String Location.toJsonArray(Collection<Location> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").deepSerialize(collection);
    }
    
    public static Collection<Location> Location.fromJsonArrayToLocations(String json) {
        return new JSONDeserializer<List<Location>>()
        .use("values", Location.class).deserialize(json);
    }
    
}
