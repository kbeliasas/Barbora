package com.example.Barbora;

public enum TimeEnum {
    EIGHT(1,"08-09"),
    NINE(2, "09-10"),
    TEN(3,"10-11"),
    ELEVEN(4,"11-12"),
    TWELVE(5,"12-13"),
    THIRTEEN(6,"13-14"),
    FOURTEEN(7,"14-15"),
    FIFTEEN(8,"15-16"),
    SIXTEEN(9,"16-17"),
    SEVENTEEN(10,"17-18"),
    EIGHTEEN(11,"18-19"),
    NINETEEN(12,"19-20"),
    TWENTY(13,"20-21");

    private int time;
    private String barboraTime;

    TimeEnum(int time, String barboraTime) {
        this.time = time;
        this.barboraTime = barboraTime;
    }

    public static String find(int time) {
        for (TimeEnum timeEnum : TimeEnum.values()) {
            if (timeEnum.time == time) {
                return timeEnum.barboraTime;
            }
        }
        return null;
    }
}
