package be.ehb.charging_points_brussel.model;

// 1

import androidx.room.Entity;

@Entity
public class ChargingEntityTable {

    private String typedut;         // LAADPALEN
    private String gemeente;        // GEMEENTE
    private long cp;                // POSTCODE
    private String rue;             // STRAATNAAM
    private long nr;                // HUISNR

    public ChargingEntityTable() {
    }
    public ChargingEntityTable(String typedut, String gemeente, long cp, String rue, long nr) {
        this.typedut = typedut;
        this.gemeente = gemeente;
        this.cp = cp;
        this.rue = rue;
        this.nr = nr;
    }

    public String getTypedut() {
        return typedut;
    }

    public void setTypedut(String typedut) {
        this.typedut = typedut;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public long getCp() {
        return cp;
    }

    public void setCp(long cp) {
        this.cp = cp;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public long getNr() {
        return nr;
    }

    public void setNr(long nr) {
        this.nr = nr;
    }
}
