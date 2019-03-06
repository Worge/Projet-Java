

import modele.CarteQCM;
import modele.CarteEntree;
import modele.CarteVF;
import modele.Pile;

public class TestPile {

    public static void main(String[] args) {

        Pile pile1 = new Pile("premiere pile", "c'est la pile du début");
        Pile pile2 = new Pile();

        CarteEntree carteEntree1 = new CarteEntree("Quand a finit la Première Guerre mondiale ? (jj/mm/aaaa)", "11/11/1918", "Autres");
        CarteEntree carteEntree2 = new CarteEntree("Quand a finit la Première Guerre mondiale ? (jj/mm/aaaa)", "11/11/1918", "Histoire");
        CarteQCM carteQCM1 = new CarteQCM("En quelle année a été couronné Charlemagne ?", "800", "400", "1654", "796", "Histoire");
        CarteQCM carteQCM2 = new CarteQCM("En quelle année a eu lieu l'avénement de Clovis ?", "800", "481", "1654", "796", "Autres");
        CarteVF carteVF = new CarteVF("Quelle est la date de la Prise de la Bastille ?  (jj/mm/aaaa)", "15/07/1789", "Histoire");

        pile1.addCarte(carteEntree1);
        pile1.addCarte(carteEntree2);

        //tests sur la pile en elle-même
        String resNom = "premiere pile";
        String resDescription = "c'est la pile du début";
        assert(pile1.getNom().equals(resNom)) : "Bug dans la pile";
        assert(pile1.getDescription().equals(resDescription)) : "Bug dans la pile";
        System.out.println(pile1.getId());
        assert(pile1.getTaille() == 2) : "Bug dans la pile";


        pile2.editNom("deuxieme pile");
        pile2.editDescription(("c'est la pile d'après"));
        resNom = "deuxieme pile";
        resDescription = "c'est la pile d'après";

        assert(pile2.getNom().equals(resNom)) : "Bug dans la pile";
        assert(pile2.getDescription().equals(resDescription)) : "Bug dans la pile";

        //tests sur l'ajout de cartes
        pile2.addCarte(carteQCM1);
        pile2.addCarte(carteQCM2);

        assert(pile2.getTaille() == 2) : "Bug dans la pile";

        pile2.removeCarte(1);

        assert(pile2.getTaille() == 1) : "Bug dans la pile";

        int trouve = (pile2.getElement(0)).getId();
        assert( trouve == 4) : "Bug dans la pile";

        System.out.println("Tout va bien pour les piles");
    }
}