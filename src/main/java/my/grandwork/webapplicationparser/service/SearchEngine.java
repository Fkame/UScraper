package my.grandwork.webapplicationparser.service;

import java.util.List;

import my.grandwork.webapplicationparser.dao.DatabaseInformationWorker;
import my.grandwork.webapplicationparser.dao.dataWrappers.UniversityName;

public class SearchEngine {

    private DatabaseInformationWorker db_worker;

    public SearchEngine (DatabaseInformationWorker db_worker) {
        this.db_worker = db_worker;
    }

    public int findNameIdInDb(String name) {
        String adaptedName = this.adaptString(name);
        List<UniversityName> list = db_worker.getUniversitiesNamesList();
        for (UniversityName universityName : list) {
            String adaptedFullName = this.adaptString(universityName.fullName);
            String adaptedShortName = this.adaptString(universityName.shortName);
            if (adaptedName.equals(adaptedFullName) | adaptedName.equals(adaptedShortName)) return universityName.id;
        }
        return -1;
    }

    public String adaptString(String toAdapt) {
        return toAdapt.toLowerCase().replaceAll("\\s", "");
    }
}
