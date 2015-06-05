package ru.ifmo.lang.grushitcyna.t02;

public class WayToFile implements FileSplitter.SplitConfig {
    private String[] path = new String[3];
    public WayToFile (String path1, String path2, String path3 ) {
        this.path[0] = path1;
        this.path[1] = path2;
        this.path[2] = path3;
    }
    public String getSourceFilePath() {
        return path[0];
    }
    public String getOddLinesFilePath() {
        return path[1];
    }
    public String getEvenLinesFilePath() {
        return path[2];
    }
}