package com.api.investmanager.adapters.output.cryptography;

import com.api.investmanager.core.application.port.output.cryptography.HashCompare;

public class HashCompareAdapter implements HashCompare {
    @Override
    public boolean compare(String plaintext, String hash) {
        return false;
    }
}
