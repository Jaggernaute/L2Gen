package fr.univrennes.istic.l2gen.geometrie;

public class Cercle implements IForme {
    Point centre;
    private double radius;

    /**
     * Constructeur de la classe Cercle.
     *
     * @param centreX Coordonee x du centre
     * @param centreY Coordonee y du centre
     * @param radius Rayon du cercle
     */
    public Cercle(double centreX, double centreY, double radius) {
        this.centre = new Point(centreX, centreY);
        this.radius = radius;
    }

    /**
     * Retourne le centre du cercle
     * @return le Point de centre du cercle
     */
    @Override
    public Point centre() {
        return centre;
    }

    /**
     * Génère une description textuelle du cercle avec indentation spécifiée.
     *
     * @param indentation Le niveau d'indentation (chaque niveau ajoute 2 espaces).
     * @return Une chaîne de caractères représentant le cercle.
     */
    public String description (int indentation) {
        String indent = "  ";
        return indent.repeat(indentation) + "Cercle centre=" + centre().x() + "," + centre().y() + " r=" + this.largeur();
    }

    /**
     * Retourne la valeur de la hauteur du cercle.
     * Autrement dit son rayon ...
     *
     * @return Le rayon du cercle.
     */
    @Override
    public double hauteur() {
        return this.radius;
    }

    /**
     * Retourne la valeur de la largeur du cercle.
     * Autrement dit son rayon ...
     *
     * @return Le rayon du cercle.
     */
    @Override
    public double largeur() {
        return this.radius;
    }
}
