package edu.ccrm.domain;

public enum Grade {
    S(10), A(9), B(8), C(7), D(6), E(5), F(0), I(0);

    private final int points;
    Grade(int p) { this.points = p; }
    public int getPoints() { return points; }
    public static Grade fromPercentage(double pct) {
        if (pct >= 90) return S;
        if (pct >= 80) return A;
        if (pct >= 70) return B;
        if (pct >= 60) return C;
        if (pct >= 50) return D;
        if (pct >= 40) return E;
        return F;
    }
}
