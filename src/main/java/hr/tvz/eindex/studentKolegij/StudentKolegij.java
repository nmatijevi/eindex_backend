package hr.tvz.eindex.studentKolegij;

public class StudentKolegij {

    private long studentId;
    private long kolegijId;
    private long ocjena;
    private boolean prijava;


    public StudentKolegij(long studentId, long kolegijId, long ocjena, boolean prijava) {
        this.studentId = studentId;
        this.kolegijId = kolegijId;
        this.ocjena = ocjena;
        this.prijava = prijava;
    }

    public StudentKolegij() {

    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getKolegijId() {
        return kolegijId;
    }

    public void setKolegijId(long kolegijId) {
        this.kolegijId = kolegijId;
    }

    public long getOcjena() {
        return ocjena;
    }

    public void setOcjena(long ocjena) {
        this.ocjena = ocjena;
    }

    public boolean isPrijava() {
        return prijava;
    }

    public void setPrijava(boolean prijava) {
        this.prijava = prijava;
    }
}
