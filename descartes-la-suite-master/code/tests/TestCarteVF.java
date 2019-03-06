

import modele.CarteVF;

    public class TestCarteVF {

        public static void main(String[] args) {
            CarteVF carteVF1 = new CarteVF();
            CarteVF carteVF2 = new CarteVF("Charlemagne a été couronné en l'an 800.", "Vrai", "Autres");

            String resQ = "Charlemagne a été couronné en l'an 800.";
            String resR = "Vrai";

            assert (carteVF2.getQuestion().equals(resQ)) : "Bug dans les cartes";
            assert (carteVF2.getReponse().equals(resR)) : "Bug dans les cartes";

            carteVF1.editQuestion("La première Guerre mondiale a fini le 01/11/1918");
            carteVF1.editReponse("Faux");

            resQ = "La première Guerre mondiale a fini le 01/11/1918";
            resR = "Faux";

            assert (carteVF2.getQuestion().equals(resQ)) : "Bug dans les cartes";
            assert (carteVF2.getReponse().equals(resR)) : "Bug dans les cartes";
            System.out.println(carteVF1.getId());

            System.out.println("Tout va bien pour les cartes VF");
        }
    }
