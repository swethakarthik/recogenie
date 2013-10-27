package com.linkedin.hack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.reccomendationgenie.app.JsonParser;
import com.reccomendationgenie.app.LinkedInClient;
import com.reccomendationgenie.app.Member;

@Controller
public class LandingPageController
{

  @RequestMapping("/landingPage")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody
  ModelAndView getLandingPage() 
  {
    ModelAndView mv = new ModelAndView("landingpage");
    
    return mv;
  }
  

  @RequestMapping("/joblisting")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody
  ModelAndView getJobListing() 
  {
    ModelAndView mv = new ModelAndView("job-listing");
    
    return mv;
  }
  @RequestMapping("/popup-mockup")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody
  ModelAndView getPopUp() 
  {
    ModelAndView mv = new ModelAndView("popup-mockup");
    LinkedInClient api = new LinkedInClient();

    String m1 = api.getMemberDetails("I7Im5VzJrW");
    Member mem1 = JsonParser.getMember(m1);
    String m2 = api.getMemberDetails("3ftC2HhF02");
    Member mem2 = JsonParser.getMember(m2);
    api.postMessage(mem1, mem2, "aNgM20jA0k", "", "64748", 20.0);
    // send email :-D   
    return mv;
  }
}
