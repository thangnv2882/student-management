package com.pmmnm.StudentManagement.application.utils;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DB4OUtil {

    private static final String FILENAME = "db/student-management.db4o"; // path to the data store

    public static ObjectContainer db = Db4oEmbedded.openFile(FILENAME);

    public static ObjectContainer getObjectContainer() {
        return db;
    }
}
