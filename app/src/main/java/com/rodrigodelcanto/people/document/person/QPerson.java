package com.rodrigodelcanto.people.document.person;

import android.app.Activity;
import android.content.Context;

import com.couchbase.lite.Emitter;
import com.couchbase.lite.LiveQuery;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.View;
import com.rodrigodelcanto.mobile.db.Q;
import com.rodrigodelcanto.people.activities.person.PeopleAdapter;

import java.util.List;
import java.util.Map;

import static android.R.attr.tag;

/**
 * Created by delkant on 4/10/17.
 */

public class QPerson extends Q {


    public static Person find(String id) {
        return Person.wrap(document(id).getProperties());
    }

//    private static Mapper mapperByTag(final String tag) {
//
//        return new Mapper() {
//            public void map(Map<String, Object> document, Emitter emitter) {
//                if (Person.SCHEMA.equals(document.get("schema"))) {
//                    Person person = Person.wrap(document);
//                    if (tag.equals(person.getTag())) {
//                        emitter.emit(person.getId(), person.getInserted().toTimeString());
//                    }
//                }
//            }
//        };
//    }

//    public static View getViewByTag(String tag) {
//        String v = "1.00";
//        View view = getView(Person.SCHEMA + "_v_" + v);
//        if (view.getMap() == null) {
//            view.setMap(mapperByTag(tag), v);
//        }
//        return view;
//    }

    public static View getViewAll() {
        String v = "1.00";
        View view = Q.getView(Person.SCHEMA + "_tag_" + tag + "_v_" + v);
        if (view.getMap() == null) {
            Mapper mapper = new Mapper() {
                public void map(Map<String, Object> document, Emitter emitter) {
                    if (Person.SCHEMA.equals(document.get("schema"))) {
                        Person person = Person.wrap(document);
                        emitter.emit(person.getId(), person.getInserted().toTimeString());
                    }
                }
            };
            view.setMap(mapper, v);
        }
        return view;
    }

//    public static List<Person> findByTag(final String tag) {
//        return findByTag(tag, true);
//    }

//    public static List<Person> findAll(final String tag, boolean descending) {
//        Query query = getViewByTag(tag).createQuery();
//        query.setDescending(descending);
//        return list(query, Person.class);
//    }

    public List<Person> findPeople() {
        Query query = getViewAll().createQuery();
        query.setDescending(true);
        return list(query, Person.class);
    }

    public static void findAll(boolean descending, final Context ctxt, final PeopleAdapter adapter) {
        Query query = getViewAll().createQuery();
        query.setDescending(descending);

        LiveQuery lq = query.toLiveQuery();
        lq.addChangeListener(new LiveQuery.ChangeListener() {
            @Override
            public void changed(final LiveQuery.ChangeEvent event) {
                ((Activity) ctxt).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setPeople(Q.toList(event.getRows(), Person.class));
                    }
                });
            }
        });
        lq.start();
    }

}
