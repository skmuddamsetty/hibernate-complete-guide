package com.skm.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String courseTitle;
    /**
     * Here we are telling that Many Courses can be mapped to an instructor
     * We are joining the Instructor and Course table using the instructor_id column inside Course table
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    /**
     * The JoinColumn is actually fairly complex, and it goes through a number of advanced steps to find the desired column.
     * The table in which it is found depends upon the context.
     * If the join is for a unidirectional OneToMany mapping using a foreign key mapping strategy,
     * the foreign key is in the table of the target entity.
     * So in the below example, we are using @OneToMany uni-directional (course has one-to-many reviews).
     * As a result, the join column / foreign key column is in the target entity.
     * In this case, the target entity is the Review class. So, you will find the join column "course_id" in the "review" table.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviewList;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public Course(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Course() {
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void addReview(String comment) {
        if (reviewList == null) reviewList = new ArrayList<>();
        reviewList.add(new Review(comment));
    }

    public void addStudent(Student student) {
        if (students == null) students = new ArrayList<>();
        students.add(student);
    }
}
