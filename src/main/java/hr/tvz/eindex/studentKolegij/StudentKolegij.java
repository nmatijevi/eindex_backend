package hr.tvz.eindex.studentKolegij;

public class StudentKolegij {

    private long studentId;
    private long kolegijId;
    private long ocjena;


    public StudentKolegij(long studentId, long kolegijId, long ocjena) {
        this.studentId = studentId;
        this.kolegijId = kolegijId;
        this.ocjena = ocjena;
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
}
