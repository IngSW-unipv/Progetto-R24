package Default;

import java.util.List;

public class SearchEngine {
    private Database database;

    //COSTRUTTORE
    public SearchEngine(Database database) {
        this.database = database;
    }

    public List<Profile> search(String keyword) {
        return database.searchProfiles(keyword);
    }
}
