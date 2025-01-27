package fr.univrennes.istic.l2gen.geometrie;

public interface IForme {
    Point centre();
    String description(int indentation);
    double hauteur();
    double largeur();
}
