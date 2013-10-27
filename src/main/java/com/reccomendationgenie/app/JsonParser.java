package com.reccomendationgenie.app;

import com.google.gson.Gson;

public class JsonParser {

	
	public static Member getMember(String name) {
	  
	  Gson gson = new Gson();
	  Member member = null;
      try {
        /*URL url = JsonParser.class.getResource(name + ".json");
          BufferedReader br = new BufferedReader(new FileReader(url.getPath()));
        */  
          //convert the json string back to object
          member = gson.fromJson(name, Member.class);
   
      } catch (Exception e) {
         e.printStackTrace();
      }
      return member;
	}
}
