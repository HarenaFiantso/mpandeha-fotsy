package com.projet1;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lombok.Getter;

public class Marcheur {
  @Getter private Lieu positionActuelle;
  private final Set<Lieu> lieuxVisites;
  private final Random random;

  public Marcheur(Lieu positionInitiale) {
    this.positionActuelle = positionInitiale;
    this.lieuxVisites = new HashSet<>();
    this.random = new Random();
    lieuxVisites.add(positionInitiale);
  }

  public void marcherAleatoirement() {
    List<Rue> rues = positionActuelle.getRues();
    Rue rueChoisie = rues.get(random.nextInt(rues.size()));
    Lieu prochainLieu = rueChoisie.getAutreLieu(positionActuelle);
    positionActuelle = prochainLieu;
    lieuxVisites.add(prochainLieu);
  }

  public boolean aVisite(Lieu lieu) {
    return lieuxVisites.contains(lieu);
  }
}
