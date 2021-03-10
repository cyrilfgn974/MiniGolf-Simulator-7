package golf;

import java.io.Serializable;
import java.util.ArrayList;

public enum TypeSurface implements Serializable {
    DEPART,
    TROU,
    GREEN,
    FAIRWAY,
    ROUGH,
    BUNKER,
    EAU,
    TAPISROULANT,
    OBSTACLE,
	BOUTON;
    public static ArrayList<TypeSurface> boostable = new ArrayList<>();

    static {
        boostable.add(GREEN);
        boostable.add(FAIRWAY);
        boostable.add(ROUGH);
        boostable.add(BUNKER);
        boostable.add(EAU);
    }

}
