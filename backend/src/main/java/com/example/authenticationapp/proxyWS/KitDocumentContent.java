package com.example.authenticationapp.proxyWS;

import com.example.authenticationapp.model.sylobe.Kit;

import java.util.HashSet;
import java.util.Set;

public class KitDocumentContent {

    private Set<Kit> kits;

    public KitDocumentContent(){
        this.kits = new HashSet<>();
    }

    public Set<Kit> getKits() {
        return kits;
    }

    public void setKits(Set<Kit> kits) {
        this.kits = kits;
    }

    public void addKit(Kit kit){
        this.kits.add(kit);
    }
}
