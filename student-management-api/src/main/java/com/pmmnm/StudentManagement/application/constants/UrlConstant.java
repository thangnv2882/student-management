package com.pmmnm.StudentManagement.application.constants;

public class UrlConstant {


    public static class Auth {
        private static final String PRE_FIX = "/auth";
        public static final String LOGIN = PRE_FIX + "/login";

        private Auth() {
        }

    }

    public static class Statistical {
        private static final String PRE_FIX = "/statistical";

        public static final String STATISTICAL_ACADEMIC_ABILITY = PRE_FIX + "/{idClassroom}";

        private Statistical() {
        }

    }


    public static class User {
        private static final String PRE_FIX = "/users";
        public static final String GET_ALL = PRE_FIX;
        public static final String GET = PRE_FIX + "/{idUser}";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX;
        public static final String DELETE = PRE_FIX + "/{idUser}";
        public static final String GET_LIST_CLASS_OF_STUDENT = PRE_FIX + "/{idUser}/classrooms";
        public static final String ENTER_SCORE = PRE_FIX + "/enter-score";

        private User() {
        }

    }

    public static class Notification {
        private static final String PRE_FIX = "/notifications";
        public static final String GET_ALL = PRE_FIX;
        public static final String GET = PRE_FIX + "/{idNotification}";
        public static final String CREATE = PRE_FIX;
        public static final String SEND = PRE_FIX + "/{idNotification}/{idUser}";
        public static final String UPDATE = PRE_FIX;
        public static final String DELETE = PRE_FIX + "/{idNotification}";

        private Notification() {
        }

    }

    public static class Classroom {
        private static final String PRE_FIX = "/classrooms";
        public static final String GET_ALL = PRE_FIX;
        public static final String GET = PRE_FIX + "/{idClassroom}";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX;
        public static final String DELETE = PRE_FIX + "/{idClassroom}";
        public static final String ADD_STUDENT_TO_CLASSROOM = PRE_FIX + "/add-student-to-classroom";
        public static final String ADD_TEACHER_TO_CLASSROOM = PRE_FIX + "/add-teacher-to-classroom";
        public static final String GET_LIST_STUDENT_IN_CLASS = PRE_FIX + "/{idClassroom}/students";

        private Classroom() {
        }

    }

}
