package com.pmmnm.StudentManagement.application.repository;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.pmmnm.StudentManagement.domain.entity.Classroom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.pmmnm.StudentManagement.application.utils.DB4OUtil.getObjectContainer;

@Repository
public class ClassroomRepository {

    private final ObjectContainer db = getObjectContainer();

    public void save(Classroom classroom) {
        db.store(classroom);
    }

    public void delete(Classroom classroom) {
        db.delete(classroom);
    }

    public List<Classroom> findAll() {
        ObjectSet<Classroom> result = db.query(Classroom.class);
        List<Classroom> classrooms = new ArrayList<>();
        while (result.hasNext()) {
            classrooms.add(result.next());
        }
        return classrooms;
    }

    public Classroom findById(String id) {
        Classroom classroom = new Classroom(id);
        ObjectSet<Classroom> result = db.queryByExample(classroom);
        return result.hasNext() ? result.next() : null;
    }


}
