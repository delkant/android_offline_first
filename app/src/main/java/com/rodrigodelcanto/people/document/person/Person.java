/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigodelcanto.people.document.person;

import com.rodrigodelcanto.mobile.pojo.EntityRef;
import com.rodrigodelcanto.mobile.pojo.LogStamp;
import com.rodrigodelcanto.mobile.pojo.MapBasedEntity;
import com.rodrigodelcanto.mobile.types.Times;
import com.rodrigodelcanto.people.Constants;

import java.util.Map;

/*
* Requirements:
* Fields: firstname, lastname, phone number, date of birth and zip-code
* 
* */
public class Person extends MapBasedEntity {

    public final static String SCHEMA = "person:person";

    public Person() {
        super();
        setId(newId());
        setPublicId(getId());
    }

    public Person(Map map) {
        super(map);
    }

    public String getSchema() {
        return (String) map.get("schema");
    }

    public void setSchema(String schema) {
        map.put("schema", SCHEMA);
    }

    public String getPublicId() {
        return (String) map.get("publicId");
    }

    public void setPublicId(String publicId) {
        map.put("publicId", publicId);
    }

    public String getLastName() {
        return (String) map.get("lastName");
    }

    public void setLastName(String name) {
        map.put("lastName", name);
    }

    public String getFirstName() {
        return (String) map.get("firstName");
    }

    public void setFirstName(String name) {
        map.put("firstName", name);
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public String getPhoneNumber() {
        return (String) map.get("phoneNumber");
    }

    public void setPhoneNumber(String phoneNumber) {
        map.put("phoneNumber", phoneNumber);
    }

    public String getZipCode() {
        return (String) map.get("zipCode");
    }

    public void setZipCode(String zipCode) {
        map.put("zipCode", zipCode);
    }

    public LogStamp getInserted() {
        return LogStamp.wrap(map.get("inserted"));
    }

    public void setInserted(LogStamp stamp) {
        putInternalMap(map, "inserted", stamp);
    }

    public LogStamp getModified() {
        return LogStamp.wrap(map.get("modified"));
    }

    public void setModified(LogStamp stamp) {
        putInternalMap(map, "modified", stamp);
    }

    public EntityRef toEntityRef() {
        return EntityRef.newObject(getId(), getFullName(), getPhoneNumber());
    }

    public static Person newEntity() {
        Person entity = new Person();
        entity.setInserted(LogStamp.newObject(Constants.DEMO));
        entity.setModified(LogStamp.newObject(Constants.DEMO));
        entity.setSchema(SCHEMA);
        return entity;
    }


    public static Person wrap(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof Map) {
            return new Person((Map) o);
        } else if (o instanceof Person) {
            return new Person(((Person) o).internalMap());
        }

        return new Person();
    }

    public static String newId() {
        return SCHEMA + "/" + Times.tsGlued();
    }

}
