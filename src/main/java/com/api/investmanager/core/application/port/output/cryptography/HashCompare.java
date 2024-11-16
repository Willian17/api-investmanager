package com.api.investmanager.core.application.port.output.cryptography;

public interface HashCompare {
    boolean compare(String plaintext, String hash);
}
