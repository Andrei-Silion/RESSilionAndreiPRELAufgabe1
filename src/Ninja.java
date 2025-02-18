import java.time.LocalDate;

enum stufen{
    Genin, Chunin, Jonin, Kage
}
public class Ninja {
    private int id;
    private String charaktername;
    private stufen stufe;
    private String beschreibung;
    private LocalDate datum;
    private double kraftpunkte;

    public Ninja(int id, String charaktername, stufen stufe, String beschreibung, LocalDate datum, double kraftpunkte){
        this.id = id;
        this.charaktername = charaktername;
        this.stufe = stufe;
        this. beschreibung = beschreibung;
        this.datum = datum;
        this.kraftpunkte = kraftpunkte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharaktername() {
        return charaktername;
    }

    public void setCharaktername(String charaktername) {
        this.charaktername = charaktername;
    }

    public stufen getStufe() {
        return stufe;
    }

    public void setStufe(stufen stufe) {
        this.stufe = stufe;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getKraftpunkte() {
        return kraftpunkte;
    }

    public void setKraftpunkte(double kraftpunkte) {
        this.kraftpunkte = kraftpunkte;
    }

    @Override
    public String toString() {
        return id +
                " " + charaktername +
                " " + stufe +
                " " + beschreibung +
                " " + datum +
                " " + kraftpunkte ;
    }
}
