package ru.arhlib.app.actual;

public class Actual implements ActualItem {
    public String emoji;
    public String title;
    public String description;
    public String link;

    @Override
    public int getType() {
        return ActualItem.TYPE_LINK;
    }
}
