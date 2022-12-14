package com.daun.word.domain.study.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

@Service
public class DefaultHashService implements StudyHashService{

    public String sha256(String msg) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(msg.getBytes());
        } catch (NoSuchElementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytesToHex(md.digest());
    }
    
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
