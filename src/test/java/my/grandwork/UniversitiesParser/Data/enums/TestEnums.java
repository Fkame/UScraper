package my.grandwork.UniversitiesParser.Data.enums;

import org.junit.Test;

public class TestEnums {
    
    @Test
    public void getInfoTypeClassificationNames() {
        for (InfoTypeClassification infoType : InfoTypeClassification.values()) {
            System.out.println(String.format("ordilal=%d + name=%s + toString=%s + title=%s", 
                                        infoType.ordinal(), infoType.name(), infoType.toString(), infoType.getTitle()));
        }
    }

    @Test
    public void getStudyGrades() {
        
    }
}
