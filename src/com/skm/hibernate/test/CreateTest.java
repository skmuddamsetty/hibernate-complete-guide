package com.skm.hibernate.test;

import com.skm.hibernate.entity.Instructor;
import com.skm.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
//            createInstructorWithInstructorDetail(session);
            createInstructorWithInstructorDetail2(session);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static void createInstructorWithInstructorDetail(Session session) {
        Instructor instructor = new Instructor("Santhosh", "Muddamsetty", "skm@gmail.com");
        instructor.setInstructorDetail(new InstructorDetail("https://www.youtube.com/skm", "Learning"));
        session.beginTransaction();
        session.save(instructor);
        session.getTransaction().commit();
    }

    /**
     * this method uses the bidirectional setup between the Instructor and Instructor Detail
     * @TODO - instructor_detail_id is not getting saved inside Instructor table
     * @param session
     */
    private static void createInstructorWithInstructorDetail2(Session session) {
        InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/johndoe", "Learning");
        instructorDetail.setInstructor(new Instructor("John", "Doe", "john.doe@gmail.com"));
        session.beginTransaction();
        session.persist(instructorDetail);
        session.getTransaction().commit();
    }
}
