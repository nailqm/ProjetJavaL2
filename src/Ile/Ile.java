package Ile;
import Ile.modele.*;
import Ile.vue.*;

public class Ile {
    public static void main(String[] args) {
        Modele modele = new Modele();
        Vue vue = new Vue(modele);
    }
}