package fr.univrennes.istic.l2gen.geometrie;

import java.util.Objects;

public class Point {
    private Double x;
    private Double y;

    /**
     * Constructeur de la classe Point.
     *
     * @param x La coordonnée x du point.
     * @param y La coordonnée y du point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Vérifie si l'objet spécifié est égal à l'instance courante du Point,
     * ou si les coordonées des deux points sont identiques.
     *
     * @param obj L'objet à comparer.
     * @return true si les points ont les mêmes coordonnées, sinon false.
     * @throws IllegalArgumentException si l'objet n'est pas une instance de Point.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Point))
            throw new IllegalArgumentException("obj doit etre une instance de Point");

        return (Objects.equals(x, ((Point) obj).x)) && (Objects.equals(y, ((Point) obj).y));
    }

    /**
     * Additionne les coordonnées de ce point avec celles d'un autre point donné.
     * Renvois un nouveau Point de coordonnées pt1.x + pt2+y et pt1.y + pt2.y
     *
     * @param p Le point à additionner.
     * @return Un nouveau point résultant de l'addition des coordonnées.
     * @throws NullPointerException si le point passé en paramètre est null.
     */
    public Point plus(Point p) {
        if (p == null)
            throw new NullPointerException("p ne doit pas etre null");

        return new Point(this.x() + p.x(), this.y() + p.y());
    }

    /**
     * Additionne des valeurs scalaires aux coordonnées du point.
     * Renvois un nouveau Point de coordonnées pt1.x + y et pt1.y + y
     *
     * @param x La valeur à ajouter à la coordonnée x.
     * @param y La valeur à ajouter à la coordonnée y.
     * @return Un nouveau point résultant de l'addition des valeurs aux coordonnées actuelles.
     */
    public Point plus(double x, double y) {
        return new Point(this.x() + x, this.y() + y);
    }

    /**
     * Retourne la coordonnée x du point.
     *
     * @return La valeur de la coordonnée x.
     */
    public double x() {
        return this.x;
    }

    /**
     * Retourne la coordonnée y du point.
     *
     * @return La valeur de la coordonnée y.
     */
    public double y() {
        return this.y;
    }
}

