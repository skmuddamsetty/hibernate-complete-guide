package com.skm.hibernate.test;

import com.skm.hibernate.entity.Instructor;
import com.skm.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            deleteInstructor(session);
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static void deleteInstructor(Session session) {
        session.beginTransaction();
        Instructor instructor = session.get(Instructor.class, 1);
        session.delete(instructor);
        session.getTransaction().commit();
    }
}
