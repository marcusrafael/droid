package br.ufpb.ccae.dcx.lcc.tcc.controllers;
import br.ufpb.ccae.dcx.lcc.tcc.model.Challenge;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/challenges")
@Controller
@RooWebScaffold(path = "challenges", formBackingObject = Challenge.class)
@RooWebJson(jsonObject = Challenge.class)
public class ChallengeController {
}
