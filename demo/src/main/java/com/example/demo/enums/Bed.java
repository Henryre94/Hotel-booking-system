package com.example.demo.enums;

public enum Bed {
    SINGLE("Single bed"),
    DOUBLE("Double bed"),
    KING("King size bed"),
    QUEEN("Queen size bed"),
    HEART("Heart shape bed"),
    WATER("Water bed"),
    CHILD("Child bed"),
    NOT_DEFINED("Please choose a type of bed");

    private  final  String name;

    Bed(String name){this.name=name;}

    public String getName() {return name;}

    public static Bed transformStringToEnum (String bed) {
        switch (bed){
            case "Single bed": return Bed.SINGLE;
            case "Double bed": return Bed.DOUBLE;
            case "King size bed": return Bed.KING;
            case "Queen size bed": return Bed.QUEEN;
            case "Heart shape bed": return Bed.HEART;
            case "Water bed": return Bed.WATER;
            case "Kids bed": return Bed.CHILD;
            default: return Bed.NOT_DEFINED;
        }
      }
    }



