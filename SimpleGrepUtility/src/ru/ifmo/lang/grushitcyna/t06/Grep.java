package ru.ifmo.lang.grushitcyna.t06;

import java.util.*;

public interface Grep {
    List<String> findLines(String regex);

    List<String> findParts(String regex);

    List<String> findInvertMatch(String regex);
}
