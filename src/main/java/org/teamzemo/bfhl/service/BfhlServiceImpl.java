package org.teamzemo.bfhl.service;

import org.springframework.stereotype.Service;
import org.teamzemo.bfhl.config.BfhlProperties;
import org.teamzemo.bfhl.dto.BfhlRequest;
import org.teamzemo.bfhl.dto.BfhlResponse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private final BfhlProperties properties;

    public BfhlServiceImpl(BfhlProperties properties) {
        this.properties = properties;
    }

    @Override
    public BfhlResponse process(BfhlRequest request) {
        if (request == null || request.data() == null) {
            throw new IllegalArgumentException("data is required");
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        StringBuilder letters = new StringBuilder();
        BigInteger sum = BigInteger.ZERO;

        for (String item : request.data()) {
            if (item == null) {
                specialCharacters.add(null);
                continue;
            }

            collectLetters(item, letters);

            if (item.matches("-?\\d+")) {
                BigInteger number = new BigInteger(item);
                sum = sum.add(number);
                if (number.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (item.matches("[A-Za-z]+")) {
                alphabets.add(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        return new BfhlResponse(
                true,
                properties.userId(),
                properties.email(),
                properties.rollNumber(),
                oddNumbers,
                evenNumbers,
                alphabets,
                specialCharacters,
                sum.toString(),
                alternatingCapsReverse(letters)
        );
    }

    private void collectLetters(String item, StringBuilder letters) {
        for (int index = 0; index < item.length(); index++) {
            char current = item.charAt(index);
            if (Character.isLetter(current)) {
                letters.append(current);
            }
        }
    }

    private String alternatingCapsReverse(StringBuilder letters) {
        StringBuilder result = new StringBuilder(letters.length());
        boolean uppercase = true;

        for (int index = letters.length() - 1; index >= 0; index--) {
            char current = letters.charAt(index);
            result.append(uppercase ? Character.toUpperCase(current) : Character.toLowerCase(current));
            uppercase = !uppercase;
        }

        return result.toString();
    }
}
