import static org.junit.jupiter.api.Assertions.*;

import com.projet1.Carte;
import com.projet1.Lieu;
import com.projet1.Marcheur;
import com.projet1.Rue;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MarcheurTest {
  private Carte carte;
  private Lieu esti;
  private Marcheur marcheur;

  @BeforeEach
  void setUp() {
    carte = new Carte();
    initCarteEtLieux();
    marcheur = new Marcheur(carte.trouverLieuParNom("HEI"));
  }

  void initCarteEtLieux() {
    var hei = new Lieu("HEI");
    esti = new Lieu("ESTI");
    var pullman = new Lieu("Pullman");
    var balancoire = new Lieu("Balancoire");

    carte.ajouterLieu(hei);
    carte.ajouterLieu(esti);
    carte.ajouterLieu(pullman);
    carte.ajouterLieu(balancoire);

    var rue1 = new Rue(hei, pullman);
    var rue2 = new Rue(pullman, balancoire);
    var rue3 = new Rue(balancoire, esti);

    hei.ajouterRue(rue1);
    pullman.ajouterRue(rue1);
    pullman.ajouterRue(rue2);
    balancoire.ajouterRue(rue2);
    balancoire.ajouterRue(rue3);
    esti.ajouterRue(rue3);
  }

  @Nested
  class TestsSurLaMarche {
    @Test
    void testMarcheAleatoireJusquAVisiteEsti() {
      while (!marcheur.aVisite(esti)) {
        marcheur.marcherAleatoirement();
      }
      assertTrue(marcheur.aVisite(esti));
    }

    @Test
    void testVisiteDesLieux() {
      marcheur.marcherAleatoirement();
      assertTrue(marcheur.aVisite(marcheur.getPositionActuelle()));
    }

    @Test
    void testVisiterTousLesLieux() {
      while (!marcheur.aVisite(esti)
          || !marcheur.aVisite(carte.trouverLieuParNom("Pullman"))
          || !marcheur.aVisite(carte.trouverLieuParNom("Balancoire"))) {
        marcheur.marcherAleatoirement();
      }
      assertTrue(marcheur.aVisite(esti));
      assertTrue(marcheur.aVisite(carte.trouverLieuParNom("Pullman")));
      assertTrue(marcheur.aVisite(carte.trouverLieuParNom("Balancoire")));
    }
  }

  @Nested
  class TestsSurLesLieux {
    @Test
    void testRuesEntreLieux() {
      var hei = carte.trouverLieuParNom("HEI");
      var pullman = carte.trouverLieuParNom("Pullman");
      var balancoire = carte.trouverLieuParNom("Balancoire");

      List<Rue> ruesDeHei = hei.getRues();
      assertEquals(1, ruesDeHei.size());
      assertTrue(ruesDeHei.contains(new Rue(hei, pullman)));

      List<Rue> ruesDeBalancoire = balancoire.getRues();
      assertEquals(2, ruesDeBalancoire.size());
      assertFalse(ruesDeBalancoire.contains(new Rue(balancoire, pullman)));
      assertTrue(ruesDeBalancoire.contains(new Rue(balancoire, esti)));
    }

    @Test
    void testTrouverLieuParNom() {
      assertEquals(carte.trouverLieuParNom("HEI"), carte.trouverLieuParNom("HEI"));
      assertEquals(esti, carte.trouverLieuParNom("ESTI"));
      assertNull(carte.trouverLieuParNom("Inexistant"));
    }

    @Test
    void testAjoutLieu() {
      var nouveauLieu = new Lieu("Nouveau");
      carte.ajouterLieu(nouveauLieu);
      assertEquals(nouveauLieu, carte.trouverLieuParNom("Nouveau"));
    }
  }

  @Test
  void testPositionInitiale() {
    assertEquals("HEI", marcheur.getPositionActuelle().getNom());
  }
}
