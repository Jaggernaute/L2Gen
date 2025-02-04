package fr.univrennes.istic.l2gen.scratchpad;

import fr.univrennes.istic.l2gen.geometrie.*;

public class testFinal {
    public static void  main(String[] args) {
        Groupe tableau = new Groupe ();
        tableau . ajouter ( new Cercle(256 , 256 , 128));
        tableau . ajouter ( new Ligne(128 , 128 , 128 , 256 , 256 , 128 , 256 , 256));
        tableau . ajouter ( new Polygone(128 , 128 , 128 , 256 , 256 , 128 , 256 , 256));
        tableau . ajouter ( new Rectangle(256 , 256 , 256 , 128));
        tableau . ajouter ( new Triangle (192 , 128 , 256 , 128 , 256 , 256));
        System . out . println ( tableau . enSVG ());
    }
}
