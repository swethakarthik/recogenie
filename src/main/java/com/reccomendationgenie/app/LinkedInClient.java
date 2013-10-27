package com.reccomendationgenie.app;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class LinkedInClient
{

  public String search(String fname, String lname, String prevEmp, String currentEmp)
  {
    String url =
        String.format("https://api.linkedin.com/v1/people-search?first-name=%s&last-name=%s&company-name=%s&current-company=%s&sort=connections",
                      fname,
                      lname, prevEmp,
                      currentEmp);
    
    OAuthRequest request = new OAuthRequest(Verb.GET, url);
    getService().signRequest(getToken(), request);
    Response response = request.send();
    return response.getBody();
  }

  public String getMemberDetails(String id)
  {
    OAuthRequest request =
        new OAuthRequest(Verb.GET, "http://api.linkedin.com/v1/people/id=" + id + ":(id,first-name,last-name,industry,positions,site-standard-profile-request,skills,educations)?format=json");
    getService().signRequest(getToken(), request);
    Response response = request.send();
    return response.getBody();
  }

  public String postMessage(Member fromMember,
                            Member toMemberId,
                            String jobPoster,
                            String message,
                            String jobId,
                            double strength)
  {

    String msg =
        String.format("Hi Recuiter, I would like to recommend %s (%s) for jobId %s; %s   LinkedIn relationship confidence factor between %s and %s is %s",
                      toMemberId.firstName,
                      toMemberId.siteStandardProfileRequest.url,
                      jobId,
                      message,
                      fromMember.firstName,
                      toMemberId.firstName,
                      strength
                      );

    String jsonS =
        String.format("{\"recipients\":{\"values\":[{\"person\": {\"_path\": \"/people/%s\"}}]},\"subject\":\"Recommendation for job %s \",\"body\":\"%s\"}",
                      jobPoster,
                      jobId,
                      msg);

    OAuthRequest request =
        new OAuthRequest(Verb.POST, "http://api.linkedin.com/v1/people/~/mailbox");
    request.addPayload(jsonS);
    request.addHeader("content-type", "application/json");
    getService().signRequest(getToken(), request);
    Response response = request.send();
    return response.getBody();
  }

  private OAuthService getService()
  {
    return new ServiceBuilder().provider(LinkedInApi.class)
                               .apiKey("7gklm04sx1vp")
                               .apiSecret("wLiNuG3tJkyrz1FQ")
                               .build();
  }

  private Token getToken()
  {
 
   /* return new Token("e3706d58-a5da-4bbc-b1bf-52e2ea1737df",
                     "be32758c-302e-4202-95e6-ae987999bffc");*/
   return new Token("97afe074-6fd9-45d7-9e0b-5bec3e2c7d5d",
        "0fd28343-1216-41d2-af5d-111a4942c906");
  }

  public static void main(String[] args)
  {
    LinkedInClient lc = new LinkedInClient();
    String search = lc.search("ipsheeta", "furtado", "", "");
    System.out.println(search);
//    String memberDetails = lc.getMemberDetails("78iLJXLTA6");
//    System.out.println();
  }
}
