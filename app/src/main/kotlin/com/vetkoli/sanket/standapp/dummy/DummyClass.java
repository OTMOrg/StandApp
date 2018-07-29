package com.vetkoli.sanket.standapp.dummy;

import com.vetkoli.sanket.standapp.models.Member;

public class DummyClass {

    public static int getSize(Member member) {
        int size = member.getMissCount();
        for (int i = 0; i < size; i++) {

        }
        return member.getMissCount();
    }
}