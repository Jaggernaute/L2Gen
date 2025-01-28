package fr.univrennes.istic.l2gen.geometrie;

public interface IForme {
    Point centre();
    void deplacer(double x, double y);
    String description(int indentation);
    String enSVG();
    IForme dupliquer();
    double hauteur();
    double largeur();
    void redimensionner(double largeur, double hauteur);
    IForme fractale(IForme base, int profondeur);
}
