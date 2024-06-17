package com.projet1;


import java.util.ArrayList;
import java.util.List;

public class Carte {
  private final List<Lieu> lieux;

  public Carte() {
    this.lieux = new ArrayList<>();
  }

  public void ajouterLieu(Lieu lieu) {
    lieux.add(lieu);
  }

  public Lieu trouverLieuParNom(String nom) {
    for (Lieu lieu : lieux) {
      if (lieu.getNom().equals(nom)) {
        return lieu;
      }
    }
    return null;
  }
}
