package com.zzxx.system.beans;

import java.util.List;

public class Rules {
    private List<String> rules;

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return rules.toString() ;
    }
}
