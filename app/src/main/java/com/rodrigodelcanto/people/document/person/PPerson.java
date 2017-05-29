package com.rodrigodelcanto.people.document.person;

import com.couchbase.lite.Document;
import com.rodrigodelcanto.mobile.db.P;
import com.rodrigodelcanto.mobile.pojo.LogStamp;
import com.rodrigodelcanto.people.Application;

/**
 * Created by delkant on 5/26/17.
 *
 * Persistence Actions for Person
 */
public class PPerson extends P {
    private static final String TAG = "PPerson";
    private static final int THUMBNAIL_SIZE = 150;

    public static Person save(Person person) {
        person.setModified(LogStamp.newObject("demo"));
        Document doc = save(person.exportMap());
        return Person.wrap(doc);
    }

    public static void delete(Person person) {
        Document doc = Application.getPersistence().getDatabase().getDocument(person.getId());
        delete(doc);
    }
}
