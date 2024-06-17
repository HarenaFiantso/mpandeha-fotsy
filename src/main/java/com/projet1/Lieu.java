package com.projet1;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Lieu {
  private final String nom;
  private final List<Rue> rues;

  public Lieu(String nom) {
    this.nom = nom;
    this.rues = new ArrayList<>();
  }

  public void ajouterRue(Rue rue) {
    rues.add(rue);
  }
}
