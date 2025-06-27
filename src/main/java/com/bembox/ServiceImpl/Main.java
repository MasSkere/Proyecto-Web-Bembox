package com.bembox.ServiceImpl;

import com.bembox.entity.Persona;

public class Main {
    public static void main(String[] args) {
        Persona p = new Persona();
        p.setNombre("Ronald");
        System.out.println(p.getNombre());
    }
}