package com.lme.robots.positioning;

public enum Orientation {
    N,
    S,
    E,
    W;

    public Orientation getLeft() {
        switch (this) {
            case N:
                return W;
            case S:
                return E;
            case E:
                return N;
            case W:
                return S;
            default:
                throw new RuntimeException("orientation must allow left");
        }
    }

    public Orientation getRight() {
        switch (this) {
            case N:
                return E;
            case S:
                return W;
            case E:
                return S;
            case W:
                return N;
            default:
                throw new RuntimeException("orientation must allow left");
        }
    }
}
