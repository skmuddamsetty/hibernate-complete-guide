package com.skm.hibernate.test;

import com.skm.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).
                addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
//            createInstructorWithInstructorDetail(session);
//            createInstructorWithInstructorDetail2(session);
//            createCourses(session);
//            createCourseWithReviews(session);
//            associateExistingCourseToNewInstructor(session);
//            createCourseWithStudents(session);
//            addCoursesForStudent(session);
//            getCoursesForStudent(session);
            deleteCourse(session);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
     *
     * @param session
     */
    private static void createInstructorWithInstructorDetail2(Session session) {
        InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/johndoe", "Learning");
        instructorDetail.setInstructor(new Instructor("John", "Doe", "john.doe@gmail.com"));
        session.beginTransaction();
        session.persist(instructorDetail);
        session.getTransaction().commit();
    }

    private static void createCourses(Session session) {
        session.beginTransaction();
        Instructor instructor = session.get(Instructor.class, 1);
        if (instructor != null) {
            instructor.addCourse(new Course("Hibernate"));
            instructor.addCourse(new Course("Python"));
            session.persist(instructor);
        }
        session.getTransaction().commit();
    }

    private static void createCourseWithReviews(Session session) {
        session.beginTransaction();
        Course course = new Course("Angular In Practice");
        course.addReview("This Course is awesome!");
        course.addReview("Wonderful course!");
        session.save(course);
        session.getTransaction().commit();
    }

    private static void associateExistingCourseToNewInstructor(Session session) {
        session.beginTransaction();
        Instructor instructor = new Instructor("Jane", "Doe", "jane.doe@gmail.com");
        Course course = session.get(Course.class, 10);
        course.setInstructor(instructor);
        session.persist(course);
        session.getTransaction().commit();
    }

    private static void deleteCourse(Session session) {
        session.beginTransaction();
        Course course = session.get(Course.class, 10);
        if (course != null) {
            session.delete(course);
        }
        session.getTransaction().commit();
    }

    private static void createCourseWithStudents(Session session) {
        session.beginTransaction();
        Course course = new Course("Java In Practice");
        course.addStudent(new Student("John", "Doe", "john.doe@gmail.com"));
        course.addStudent(new Student("Jane", "Doe", "jane.doe@gmail.com"));
        session.persist(course);
        session.getTransaction().commit();
    }

    private static void addCoursesForStudent(Session session) {
        session.beginTransaction();
        Student student = session.get(Student.class, 1);
        if (student != null) {
            student.addCourse(new Course("Python"));
            student.addCourse(new Course("Physics"));
            session.persist(student);
        }
        session.getTransaction().commit();
    }

    private static void getCoursesForStudent(Session session) {
        session.beginTransaction();
        Student student = session.get(Student.class, 2);
        if (student != null) {
            System.out.println(student.getCourses());
        }
        session.getTransaction().commit();
    }

}
