package omds;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force = true)
@Entity
public class Medication {

    @Id
    private final String id;
    private final String name;
    
    @Enumerated(EnumType.STRING)
    private final Type type;
    /*
    private final Boolean isPrescripted;
    private final double dose;
    private final double tablet;
    */
    
    public static enum Type {
        AdrenalHormone, AntiProtozoal, CirculatorySystem, AntiInflammatoryAnalgesic, PepticUlcer 
    }
/*
    public Medication(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
*/
}
