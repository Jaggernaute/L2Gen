package fr.univrennes.istic.l2gen;

import fr.univrennes.istic.l2gen.geometrie.Cercle;
import fr.univrennes.istic.l2gen.geometrie.Groupe;
import fr.univrennes.istic.l2gen.geometrie.IForme;

public class App {
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
        Groupe arbre = arbre( new Cercle(256, 256,128));
        System.out.println(arbre.description(0));
        System.out.println(arbre.enSVG());
    }
}
