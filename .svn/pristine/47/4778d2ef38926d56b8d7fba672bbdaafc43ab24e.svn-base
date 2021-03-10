package jeu;

import golf.Point2D;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Viseur {
    protected Balle balle;
    protected float axeAngle;
    private Scanner sc;

    public Viseur(Balle balle) {
        this.balle = balle;
        initScanner();
    }

    public Viseur(Viseur viseur) {
        this(viseur.balle);

    }

    public Balle getBalle() {
        return this.balle;
    }

    public void initScanner() {
        sc = new Scanner(System.in);;
    }


    public void viser(Point2D trou) {
        //On demande au joueur de rentrer la nouvelle vitesse de la balle
        this.axeAngle = (float) this.balle.angle(trou);
        try {
            System.out.print(" entrez la vitesse de la balle ( <" + this.balle.V_INI_MAX+ "):  ");
            float vitesse = sc.nextFloat();
            if (vitesse > this.balle.V_INI_MAX) {
                throw new InputMismatchException();
            }
            System.out.println("Le trou est à un angle : " + this.axeAngle);
            System.out.println(" choisissez l'angle de la balle en degr� (sens trigo avec 0 vers le bas :  ");
            float angle = sc.nextFloat();
            this.balle.initVitesse(vitesse, angle);
        } catch (InputMismatchException e) {
            System.out.println("Erreur! Couple de valeurs invalide!");
            this.viser(trou);
        }

    }


    public boolean isRendered() {return false;};

    public void render() {
    }


    public void initPosition() {
    }

    public void setBalle(Balle balle) {
        this.balle = balle;
    }
}
