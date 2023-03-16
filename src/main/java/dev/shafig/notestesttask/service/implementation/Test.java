package dev.shafig.notestesttask.service.implementation;

import dev.shafig.notestesttask.util.jwt.JwtUtil;

public class Test {

    public static void main(String[] args) {
        final String authHeader = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaGFmaWciLCJleHAiOjE2Nzg5MzgxMzUsImlhdCI6MTY3ODkwMjEzNX0.ixHCeB_JMftC_MNeZ98bCNIwICkwko-0edaWFW-Zs1A";
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            jwt = authHeader.substring(7);
            username = new JwtUtil().extractUsername(jwt);
            System.out.println(username);
        }
    }
}
