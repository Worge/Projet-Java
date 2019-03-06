


import modele.CarteQCM;

public class TestCarteQCM {

    public static void main(String[] args) {
        CarteQCM carteQCM1 = new CarteQCM();
        CarteQCM carteQCM2 = new CarteQCM("En quelle année a été couronné Charlemagne ?", "800", "400", "1654", "796", "Histoire");

        String resQ = "En quelle année a été couronné Charlemagne ?";
        String resR = "800";


        assert (carteQCM2.getQuestion().equals(resQ)) : "Bug dans les cartes";
        assert (carteQCM2.getReponse().

                equals(resR)) : "Bug dans les cartes";

        carteQCM1.editQuestion("Quand a finit la Première Guerre mondiale ? (jj/mm/aaaa)");
        carteQCM1.editReponse("11/11/1918");
        carteQCM1.editFausseReponse1("11/01/1918");
        carteQCM1.editFausseReponse2("11/04/1918");
        carteQCM1.editFausseReponse3("11/08/1918");

        resQ = "Quand a finit la Première Guerre mondiale ? (jj/mm/aaaa)";
        resR = "11/11/1918";
        String resRF1 = "11/01/1918";
        String resRF2 = "11/04/1918";
        String resRF3 = "11/08/1918";

        assert (carteQCM1.getQuestion().equals(resQ)) : "Bug dans les cartes";
        assert (carteQCM1.getReponse().equals(resR)) : "Bug dans les cartes";
        assert (carteQCM1.getFausseReponse1().equals(resRF1)) : "Bug dans les cartes";
        assert (carteQCM1.getFausseReponse2().equals(resRF2)) : "Bug dans les cartes";
        assert (carteQCM1.getFausseReponse3().equals(resRF3)) : "Bug dans les cartes";
        System.out.println(carteQCM1.getId());

        System.out.println("Tout va bien pour les cartes QCM");
    }
}