package br.ufpb.ccae.dcx.lcc.tcc.controllers;
import br.ufpb.ccae.dcx.lcc.tcc.model.Location;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/locations")
@Controller
@RooWebScaffold(path = "locations", formBackingObject = Location.class)
@RooWebJson(jsonObject = Location.class)
public class LocationController {
}
