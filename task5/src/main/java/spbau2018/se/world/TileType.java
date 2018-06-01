package spbau2018.se.world;


/**
 * Enum для типов тайла
 */
public enum TileType {
    WALL("sprites/Wall33.gif"),
    FLOOR("sprites/floor22.gif"),
    UNKNOW("");

    public static boolean isFreeTile(TileType tile) {
        return tile != WALL;
    }

    /**
     * Путь до изображения, отвечающего типу тайла
     */
    private String path;

    public String getPath() {
        return path;
    }

    TileType(String path) {
        this.path = path;
    }
}
