package org.example.gradeCalculate;

import java.util.List;

public class GradeCalculator {
    private final Courses courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    /**
     * 핵심포인트 
     */
    // 이수한 과목을 전달하여 평균학점 계산 요청 --> 학점 계산기 --> (학점수 X 교과목 평점)의 합계 --> 과목(코스) 일급 콜렉션
    //                                                   -->  수강신청 총학점 수          --> 과목(코스) 일급 콜렉션

    public double calculateGrade() {
        // (학점수 X 교과목 평점)의 합계
        double multipliedCreaditAndCourseGrade =courses.multiplyCreditAndCourseGrade();
        // 수강신청 총학점 수
        int totalCompletedCredit =courses.calculateTotalCompletedCredit();

        return multipliedCreaditAndCourseGrade / totalCompletedCredit;
    }
}
