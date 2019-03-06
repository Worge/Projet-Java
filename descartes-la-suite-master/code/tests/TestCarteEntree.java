


import modele.CarteEntree;

public class TestCarteEntree {

    public static void main(String[] args) {
        CarteEntree carteEntree1 = new CarteEntree();
        CarteEntree carteEntree2 = new CarteEntree("En quelle année a été couronné Charlemagne ?", "800", "Histoire");

        String resQ = "En quelle année a été couronné Charlemagne ?";
        String resR = "800";

        assert (carteEntree2.getQuestion().equals(resQ)) : "Bug dans les cartes";
        assert (carteEntree2.getReponse().equals(resR)) : "Bug dans les cartes";

        carteEntree1.editQuestion("Quand a finit la Première Guerre mondiale ? (jj/mm/aaaa)");
        carteEntree1.editReponse("11/11/1918");

        resQ = "Quand a finit la Première Guerre mondiale ? (jj/mm/aaaa)";
        resR = "11/11/1918";

        assert (carteEntree1.getQuestion().equals(resQ)) : "Bug dans les cartes";
        assert (carteEntree1.getReponse().equals(resR)) : "Bug dans les cartes";
        System.out.println(carteEntree1.getId());

        System.out.println("Tout va bien pour les cartes entrées");
    }
}