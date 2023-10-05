package org.springframework.samples.minesweeper.board;

public enum DifficultyLevel {
    BEGINNER, INTERMEDIATE, ADVANCED, CUSTOM;

    public static DifficultyLevel parse(String difficulty){
        DifficultyLevel res= null;
        switch(difficulty){
            case "Beginner": 
                res=DifficultyLevel.BEGINNER;
                break;
            case "Intermediate":
                res=DifficultyLevel.INTERMEDIATE;
                break;
            case "Advanced":
                res=DifficultyLevel.ADVANCED;
                break;
            case "Custom":
                res=DifficultyLevel.CUSTOM;
                break;
        }
        return res;
    }
}
