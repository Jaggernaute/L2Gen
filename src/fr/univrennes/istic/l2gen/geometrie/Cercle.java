package fr.univrennes.istic.l2gen.geometrie;

public class Cercle implements IForme {
    Point centre;
    private double rayon;

    /**
     * Constructeur de la classe Cercle.
     *
     * @param centreX Coordonee x du centre
     * @param centreY Coordonee y du centre
     * @param rayon Rayon du cercle
     */
    public Cercle(double centreX, double centreY, double rayon) {
        this.centre = new Point(centreX, centreY);
        this.rayon = rayon;
    }

    /**
     * Retourne le centre du cercle
     * @return Un objet {@link Point} représentant le centre du cercle
     */
    @Override
    public Point centre() {
        return centre;
    }

    /**
     * Deplace le Cercle de x sur l'axe x et de y sur l'axe y
     * Le deplacement se fait du centre actuel vers le nouveau centre.
     *
     * @param x deplacement en x
     * @param y deplacement en y
     */
    @Override
    public void deplacer(double x, double y) {
        this.centre = new Point(centre().x() + x, centre().y() + y);
    }

    /**
     * Génère une description textuelle du cercle avec indentation spécifiée.
     *
     * @param indentation Le niveau d'indentation (chaque niveau ajoute 2 espaces).
     * @return Une chaîne de caractères représentant le cercle.
     */
    public String description (int indentation) {
        String indent = "  ";
        return indent.repeat(indentation) + "Cercle centre=" + centre().x() + "," + centre().y() + " r=" + this.hauteur() / 2;
    }

    /**
     * Génère une représentation SVG du Cercle.
     *
     * @return Une chaîne de caractères représentant le Cercle en balise SVG.
     */
    @Override
    public String enSVG() {
        return String.format(
                "<circle cx=\"%.1f\" cy=\"%.1f\" r=\"%.1f\" fill=\"white\" stroke=\"black\"/>",
                centre.x(), centre.y(), rayon
        );
    }

    /**
     * Clone le cercle et le stock dans un nouvel objet
     *
     * @return Un objet {@link Cercle} représentant le nouveau cercle (ayant exactement les memes proprietes)
     */
    @Override
    public IForme dupliquer() {
        return new Cercle(centre.x(), centre.y(), hauteur()/2);
    }

    /**
     * Retourne la valeur de la hauteur du cercle.
     * Autrement dit 2x son rayon ...
     *
     * @return Le rayon du cercle.
     */
    @Override
    public double hauteur() {
        return this.rayon*2;
    }

    /**
     * Retourne la valeur de la largeur du cercle.
     * Autrement dit 2x son rayon ...
     *
     * @return Le rayon du cercle.
     */
    @Override
    public double largeur() {
        return this.rayon*2;
    }

    /**
     * Change la taille du cercle d'un facteur hauteur. Puisque qu'un cercle n'a qu'une
     * mesure de distance entre son centre et ses points, nous utiliserons la meme valeur
     * pour les deux parametres.
     *
     * @throws IllegalArgumentException - Si les deux parametres n'ont pas la meme valeur
     * @param largeur Facteur de redimensionement
     * @param hauteur Facteur de redimensionement
     */
    @Override
    public void redimensionner(double largeur, double hauteur) {
        if (largeur != hauteur)
            throw new IllegalArgumentException("La largeur et la hauteur doivent etre les memes");
        this.rayon *= hauteur;
    }
}
