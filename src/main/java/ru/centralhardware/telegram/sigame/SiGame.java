package ru.centralhardware.telegram.sigame;

import com.vladimirkhil.ygpackage3_0.Pack;

import java.util.ArrayList;
import java.util.List;

public class SiGame {

    private final SiqArchive siq;
    private final Pack pack;

    private int currentRound;

    public SiGame(SiqArchive siq) {
        this.siq = siq;
        pack = siq.getPack();
    }

    public List<String> getThemes(){
        List<String> result = new ArrayList<>();
        pack.getRounds().getRound().get(currentRound).getThemes().getTheme().forEach(theme -> result.add(theme.getName()));
        return result;
    }


}
