package my.grandwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import my.grandwork.Data.UniversityInfoWrapper;
import my.grandwork.Parsers.emuns.University;

public class ParsersController {
   
    public ParsersController() { }

    public ArrayList<UniversityInfoWrapper> getAllUniversitiesInfo() {
        ArrayList<UniversityInfoWrapper> list = new ArrayList<UniversityInfoWrapper>();

        List<University> univers = Arrays.asList(University.values());
        for (University university : univers) {
            list.add(this.getTargetUniversityInfo(university));
        }

        return list;
    }

    public UniversityInfoWrapper getTargetUniversityInfo(University university) {
        UniversityInfoWrapper wrapper = new UniversityInfoWrapper();
        return wrapper;
    }
}
