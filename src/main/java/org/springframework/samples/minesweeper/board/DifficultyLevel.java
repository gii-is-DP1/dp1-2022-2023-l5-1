package org.springframework.samples.minesweeper.board;

public enum DifficultyLevel {
    BEGINNER, INTERMEDIATE, ADVANCED, CUSTOM;

    public static DifficultyLevel parse(String difficulty){
        DifficultyLevel res= null;
        switch(difficulty){
            case "Beginner": res=DifficultyLevel.BEGINNER;
            case "Intermediate": res=DifficultyLevel.INTERMEDIATE;
            case "Advanced": res=DifficultyLevel.ADVANCED;
            case "Custom": res=DifficultyLevel.CUSTOM;
        }
        return res;
    }
}
