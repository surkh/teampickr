package com.surkh.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Player {

  @Id public String id;

  public Player() {};

  public Player(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Player)) return false;

    Player player = (Player) o;

    return id.equals(player.id);

  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  public void setId(String id) {
    this.id = id;
  }
}
