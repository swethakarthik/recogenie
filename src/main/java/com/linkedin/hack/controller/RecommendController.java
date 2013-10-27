package com.linkedin.hack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.reccomendationgenie.app.ConnectionStrength;
import com.reccomendationgenie.app.JsonParser;
import com.reccomendationgenie.app.LinkedInClient;
import com.reccomendationgenie.app.Member;

@RequestMapping("/recommend")
@Controller
public class RecommendController
{

  public RecommendController()
  {
    // TODO Auto-generated constructor stub
  }
/*
  @RequestMapping("/")
  public @ResponseBody
  ModelAndView getIndex()
  {
    ModelAndView mv = new ModelAndView("index");
    mv.addObject("name", "Swetha");
    return mv;
  }
*/
  
  
  
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody
  ModelAndView recommend(@RequestParam(value = "memberId1", required = true) String memberId1,
                                     @RequestParam(value = "memberId2", required = true) String memberId2,
                                     @RequestParam(value = "jobId", required = true) String jobId,
                                     @RequestParam(value = "comment", required = false) String comment) 
  {
    ModelAndView mv = new ModelAndView("index");
    boolean success = false;
    success = calculate(memberId1, memberId2, "", jobId, comment, success);
    mv.addObject("success", success);
    return mv;
  }



  public boolean calculate(String memberId1,
                            String memberId2,
                            String jobPoster,
                            String jobId,
                            String comment,
                            boolean success)
  {
    try
    {
      LinkedInClient api = new LinkedInClient();
      String m1 = api.getMemberDetails(memberId1);
      Member mem1 = JsonParser.getMember(m1);
      String m2 = api.getMemberDetails(memberId2);
      Member mem2 = JsonParser.getMember(m2);
      double strength = ConnectionStrength.getConnectionStrength(mem1, mem2);
      System.out.println(strength);
      api.postMessage(mem1, mem2, jobPoster, comment, jobId, strength);
      success = true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return success;
  }
  
}
