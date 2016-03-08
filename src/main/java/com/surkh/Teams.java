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

  public static ArrayList<Player> players = new ArrayList<Player>();

  static {
    players.add(new Player("surkh"));
    players.add(new Player("darin"));
    players.add(new Player("Abhishek"));
    players.add(new Player("Al"));
    players.add(new Player("Aleks"));
    players.add(new Player("Anthony"));
    players.add(new Player("Chun"));
    players.add(new Player("Dan"));
    players.add(new Player("Darin"));
    players.add(new Player("DavidC"));
    players.add(new Player("DavidH"));
    players.add(new Player("DavidS"));
    players.add(new Player("Dom"));
    players.add(new Player("Ed"));
    players.add(new Player("Eric"));
    players.add(new Player("Gary"));
    players.add(new Player("Gowri"));
    players.add(new Player("Marcos"));
    players.add(new Player("MichaelS"));
    players.add(new Player("MikeH"));
    players.add(new Player("Ming"));
    players.add(new Player("NickH"));
    players.add(new Player("NikK"));
    players.add(new Player("Paul"));
    players.add(new Player("Peter"));
    players.add(new Player("Raghu"));
    players.add(new Player("Rudy"));
    players.add(new Player("Senthil"));
    players.add(new Player("Sibiao"));
    players.add(new Player("Surkhab"));
    players.add(new Player("Tanvir"));
    players.add(new Player("Zareh"));
  }

  public Player getGreeting(@Named("id") Integer id) throws NotFoundException {
    try {
      return players.get(id);
    } catch (IndexOutOfBoundsException e) {
      throw new NotFoundException("Greeting not found with an index: " + id);
    }
  }

  public ArrayList<Player> listGreeting() {
    return players;
  }

  public String[] myMessage() {
    return new String[]{"This is my message"};
  }

  @ApiMethod(name = "greetings.multiply", httpMethod = "post")
  public Player insertGreeting(@Named("times") Integer times, Player greeting) {
    Player response = new Player();
    StringBuilder responseBuilder = new StringBuilder();
    for (int i = 0; i < times; i++) {
      responseBuilder.append(greeting.getId());
    }
    response.setId(responseBuilder.toString());
    return response;
  }

  @ApiMethod(name = "greetings.authed", path = "hellogreeting/authed")
  public Player authedGreeting(User user) {
    Player response = new Player("hello " + user.getEmail());
    return response;
  }
}
