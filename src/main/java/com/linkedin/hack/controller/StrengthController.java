package com.linkedin.hack.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.linkedin.hack.model.ConnectionStrengthModel;
import com.reccomendationgenie.app.ConnectionStrength;
import com.reccomendationgenie.app.JsonParser;
import com.reccomendationgenie.app.LinkedInClient;
import com.reccomendationgenie.app.Member;

@Controller
@RequestMapping("/strength")
public class StrengthController
{
  public Map<String, String> nameMap = new HashMap<String, String>();

  public StrengthController()
  {
    nameMap.put("roojuta", "3ftC2HhF02");
    nameMap.put("khushboo", "aNgM20jA0k");
    nameMap.put("swetha", "I7Im5VzJrW");
    // TODO Auto-generated constructor stub
  }

  /*
   * @RequestMapping("/") public @ResponseBody ModelAndView getIndex() { ModelAndView mv =
   * new ModelAndView("index"); mv.addObject("name", "Swetha"); return mv; }
   */

  // Roojuta 3ftC2HhF02
  // Khushboo aNgM20jA0k
  // Swetha I7Im5VzJrW

  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody
  ModelAndView getConnectionStrength(@RequestParam(value = "memberId1", required = true) String memberId1,
                                     @RequestParam(value = "memberId2", required = true) String memberId2)
  {
    ModelAndView mv = new ModelAndView("score");
    Member mem1 =null, mem2 = null;
    ConnectionStrengthModel csm = new ConnectionStrengthModel();
    try
    {
      LinkedInClient api = new LinkedInClient();
      if (nameMap.get(memberId1) != null)
        mem1 = JsonParser.getMember(api.getMemberDetails(nameMap.get(memberId1.toLowerCase())));
      if (nameMap.get(memberId2) != null)
        mem2 = JsonParser.getMember(api.getMemberDetails(nameMap.get(memberId2.toLowerCase())));
      if(mem1!=null && mem2!=null){
        csm.setConnStrength(ConnectionStrength.getConnectionStrength(mem1, mem2));
        csm.setSuccess(true);
      }
      else 
        csm.setSuccess(false);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    mv.addObject("strength", csm);
    return mv;
  }

}
