package com.surkh;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.surkh.model.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

  public static ArrayList<Player> players;

  static {
    ObjectifyService.register(Player.class);
    reloadList();


  }

  void bootstrapList() {
    reloadList();
    String[] x = new String[]{
        "Abhishek",
        "Al",
        "Aleks",
        "Anthony",
        "Chun",
        "Dan",
        "Darin",
        "DavidC",
        "DavidH",
        "DavidS",
        "Dom",
        "Ed",
        "Eric",
        "Gary",
        "Gowri",
        "Marcos",
        "MichaelS",
        "MikeH",
        "Ming",
        "NickH",
        "NikK",
        "Paul",
        "Peter",
        "Raghu",
        "Rudy",
        "Senthil",
        "Sibiao",
        "Surkhab",
        "Tanvir",
        "Zareh"};

    Set<Player> playerHashSet = new HashSet<>(players);
    for (String s : x) {
      Player p = new Player(s);
      if (!playerHashSet.contains(p)) {
        ObjectifyService.ofy().save().entities(new Player(s)).now();
      }
    }

  }


  private static void reloadList() {
    List<Player> list = ObjectifyService.ofy().load().type(Player.class).list();

    players = new ArrayList<Player>();
    players.addAll(list);

  }

  public Player getGreeting(@Named("id") Integer id) throws NotFoundException {
    try {
      return players.get(id);
    } catch (IndexOutOfBoundsException e) {
      throw new NotFoundException("Greeting not found with an index: " + id);
    }
  }

  public ArrayList<Player> listGreeting() {
    reloadList();
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
