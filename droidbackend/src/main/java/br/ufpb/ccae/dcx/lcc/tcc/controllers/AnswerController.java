package br.ufpb.ccae.dcx.lcc.tcc.controllers;
import br.ufpb.ccae.dcx.lcc.tcc.model.Answer;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/answers")
@Controller
@RooWebScaffold(path = "answers", formBackingObject = Answer.class)
@RooWebJson(jsonObject = Answer.class)
public class AnswerController {
}
