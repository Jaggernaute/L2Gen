package fr.univrennes.istic.l2gen.scratchpad;

import fr.univrennes.istic.l2gen.geometrie.Groupe;
import fr.univrennes.istic.l2gen.geometrie.IForme;
import fr.univrennes.istic.l2gen.geometrie.Triangle;

public class testTriangle {
    static Groupe arbre(IForme figure) {
        Groupe groupe = new Groupe(figure);
        IForme mini = figure.dupliquer();
        mini.redimensionner(0.5 , 0.5);
        groupe.ajouter (mini);
        IForme minigroupe = groupe.dupliquer();
        minigroupe.redimensionner(0.25, 0.25);
        groupe.ajouter(minigroupe);
        return groupe;
    }
    public static void main(String[] args) {
        IForme f = new Triangle(192, 128, 256, 128, 256 , 256);
        System.out.println(f.description(1));

        Groupe arbre = arbre( new Triangle(192 , 128 , 256 , 128 , 256 , 256));
        System.out.println(arbre.enSVG());
    }
}
