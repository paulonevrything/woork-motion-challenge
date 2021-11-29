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

    public String toString(){

        switch(this){

            case ADDED :
                return "ADDED";

            case IN_CHECK :
                return "IN-CHECK";

            case APPROVED :
                return "APPROVED";

            case ACTIVE :
                return "ACTIVE";
        }
        return null;
    }

    public static EmployeeState enumValue(String value){

        if(value.equalsIgnoreCase(ADDED.toString())) {

            return EmployeeState.ADDED;
        }
        else if(value.equalsIgnoreCase(IN_CHECK.toString())) {

            return EmployeeState.IN_CHECK;
        }
        else if(value.equalsIgnoreCase(APPROVED.toString())) {

            return EmployeeState.APPROVED;
        }
        else if(value.equalsIgnoreCase(ACTIVE.toString())) {

            return EmployeeState.ACTIVE;
        }
        else
            return null;
    }

}
