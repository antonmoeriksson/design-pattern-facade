package util;

public enum Move {
    ROCK,
    PAPER,
    SCISSOR,
    UNKNOWN;

    public String toString(){
        switch(this){
            case ROCK :
                return "rock";
            case PAPER :
                return "paper";
            case SCISSOR :
                return "scissors";
        }
        return null;
    }
}
