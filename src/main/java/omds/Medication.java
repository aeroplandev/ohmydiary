package omds;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Medication {

    private final String id;
    private final String name;
    private final Boolean isPrescripted;
    private final Type type;
    private final double dose;
    private final double tablet;

    public static enum Type {
        AdrenalHormone, Antiprotozoal, CirculatorySystem, AntiinflammatoryAnalgesic, PepticUlcer 
    }
/*
    public Medication(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
*/
}
