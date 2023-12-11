package com.pmmnm.StudentManagement.application.constants;

public class UrlConstant {

    public static class Student {
        private static final String PRE_FIX = "/students";
        public static final String GET_ALL = PRE_FIX;
        public static final String GET = PRE_FIX + "/{idStudent}";
        public static final String CREATE = PRE_FIX;
        public static final String UPDATE = PRE_FIX;
        public static final String DELETE = PRE_FIX + "/{idStudent}";

        private Student() {
        }

    }

}
