/**
 *
 */
package com.urpharm.sbrws.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Custmer gender
 *
 * @author HOJAFOJIALME
 * @version 1.0
 */
public enum Gender {

    /**
     * Masculin
     */
    M("Male"),
    
    /**
     * Feminin
     */
    F("Female");
    
    /**
    * Value
    */
    private String value;

    /**
    * Constructor
    * @param value
    */
    private Gender(String value){
           this.setValue(value);
    }

    /**
    * Retourn the list of values
    * @return
    */
    public static List<Gender> getValues() {

           // Initialization of collection to return
           List<Gender> list = new ArrayList<Gender>();

           // Add values
           list.add(M);
           list.add(F);

           // Return the collection
           return list;

    }

    /**
    * @return the value
    */
    public String getValue() {
           return value;
    }

    /**
    * @param value the value to set
    */
    public void setValue(String value) {
           this.value = value;
    }


}
