public interface RussianRoulette {
    void play (Gun gun);
    interface Gun {
        boolean fire();
    }
}
