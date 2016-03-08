package com.surkh;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.users.User;

import java.util.ArrayList;

import javax.inject.Named;

/**
 * Defines v1 of a helloworld API, which provides simple "greeting" methods.
 */
@Api(
    name = "helloworld",
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE},
    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
    audiences = {Constants.ANDROID_AUDIENCE}
)
public class Teams {

  public static ArrayList<HelloGreeting> players = new ArrayList<HelloGreeting>();

  static {
    players.add(new HelloGreeting("surkh"));
    players.add(new HelloGreeting("darin"));
    players.add(new HelloGreeting("Abhishek"));
    players.add(new HelloGreeting("Al"));
    players.add(new HelloGreeting("Aleks"));
    players.add(new HelloGreeting("Anthony"));
    players.add(new HelloGreeting("Chun"));
    players.add(new HelloGreeting("Dan"));
    players.add(new HelloGreeting("Darin"));
    players.add(new HelloGreeting("DavidC"));
    players.add(new HelloGreeting("DavidH"));
    players.add(new HelloGreeting("DavidS"));
    players.add(new HelloGreeting("Dom"));
    players.add(new HelloGreeting("Ed"));
    players.add(new HelloGreeting("Eric"));
    players.add(new HelloGreeting("Gary"));
    players.add(new HelloGreeting("Gowri"));
    players.add(new HelloGreeting("Marcos"));
    players.add(new HelloGreeting("MichaelS"));
    players.add(new HelloGreeting("MikeH"));
    players.add(new HelloGreeting("Ming"));
    players.add(new HelloGreeting("NickH"));
    players.add(new HelloGreeting("NikK"));
    players.add(new HelloGreeting("Paul"));
    players.add(new HelloGreeting("Peter"));
    players.add(new HelloGreeting("Raghu"));
    players.add(new HelloGreeting("Rudy"));
    players.add(new HelloGreeting("Senthil"));
    players.add(new HelloGreeting("Sibiao"));
    players.add(new HelloGreeting("Surkhab"));
    players.add(new HelloGreeting("Tanvir"));
    players.add(new HelloGreeting("Zareh"));
  }

  public HelloGreeting getGreeting(@Named("id") Integer id) throws NotFoundException {
    try {
      return players.get(id);
    } catch (IndexOutOfBoundsException e) {
      throw new NotFoundException("Greeting not found with an index: " + id);
    }
  }

  public ArrayList<HelloGreeting> listGreeting() {
    return players;
  }

  @ApiMethod(name = "greetings.multiply", httpMethod = "post")
  public HelloGreeting insertGreeting(@Named("times") Integer times, HelloGreeting greeting) {
    HelloGreeting response = new HelloGreeting();
    StringBuilder responseBuilder = new StringBuilder();
    for (int i = 0; i < times; i++) {
      responseBuilder.append(greeting.getMessage());
    }
    response.setMessage(responseBuilder.toString());
    return response;
  }

  @ApiMethod(name = "greetings.authed", path = "hellogreeting/authed")
  public HelloGreeting authedGreeting(User user) {
    HelloGreeting response = new HelloGreeting("hello " + user.getEmail());
    return response;
  }
}
