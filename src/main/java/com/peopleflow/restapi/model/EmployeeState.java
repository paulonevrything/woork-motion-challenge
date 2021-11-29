package com.peopleflow.restapi.model;

public enum EmployeeState {

    ADDED {
        @Override
        public int stringencyOrder() {
            return 0;
        }
    },

    IN_CHECK {
        @Override
        public int stringencyOrder() {
            return 2;
        }
    },

    APPROVED {
        @Override
        public int stringencyOrder() {
            return 3;
        }
    },

    ACTIVE {
        @Override
        public int stringencyOrder() {
            return 4;
        }
    };

    public abstract int stringencyOrder();

    }
