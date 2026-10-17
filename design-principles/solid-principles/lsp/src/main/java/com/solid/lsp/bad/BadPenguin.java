package com.solid.lsp.bad;

/** VIOLATION: cannot replace BadBird because fly() blows up. */
public class BadPenguin extends BadBird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}
