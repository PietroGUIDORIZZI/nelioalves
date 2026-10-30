package a08OrientacaoAObjetos.exer3;

public class Aluno {
    private String name;
    private double firstTrimester;
    private double secondTrimester;
    private double thirdTrimester;

    public Aluno(String name, double firstTrimester, double secondTrimester, double thirdTrimester){
        if(name.isBlank()){
            throw new IllegalArgumentException("Cannot use empty name;");
        }
        this.name = name.trim();
        if(firstTrimester < 0 || firstTrimester > 30){
            throw new IllegalArgumentException("First trimester should be beetween 0 and 30");
        }
        this.firstTrimester = firstTrimester;
        if(secondTrimester < 0 || secondTrimester > 35){
            throw new IllegalArgumentException("Second trimester should be beetween 0 and 35");
        }
        this.secondTrimester = secondTrimester;
        if(thirdTrimester < 0 || thirdTrimester > 35){
            throw new IllegalArgumentException("Third trimester should be beetween 0 and 35");
        }
        this.thirdTrimester = thirdTrimester;

    }

    public boolean passed(){
        return !(this.firstTrimester + this.secondTrimester + this.thirdTrimester < 60);
    }

    public double finalGrade(){
        return this.firstTrimester + this.secondTrimester + this.thirdTrimester;
    }

    public double missingPoints(){
        if(finalGrade() < 60){
            return 60 - finalGrade();
        }
        return 0;

    }
}
