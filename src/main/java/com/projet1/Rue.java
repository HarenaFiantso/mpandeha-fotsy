package com.projet1;

public record Rue(Lieu lieuA, Lieu lieuB) {

  public Lieu getAutreLieu(Lieu lieu) {
    if (lieu.equals(lieuA)) {
      return lieuB;
    } else if (lieu.equals(lieuB)) {
      return lieuA;
    } else {
      return null;
    }
  }
}
