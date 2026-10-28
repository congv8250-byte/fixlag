package com.fixlag;

/** Các mức giảm lag. Nhấn phím để chuyển vòng: TẮT -> NHẸ -> CỰC NHẸ -> TẮT. */
public enum LagLevel {
    OFF("OFF"),
    LOW("LOW"),
    ULTRA("ULTRA");

    private final String label;

    LagLevel(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public LagLevel next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
