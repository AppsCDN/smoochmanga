package kishido.smoochmanga.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by syspaulo on 10/1/2015.
 */
public class MangaInfo implements Serializable {

    private String name;
    private List<String> genreList;
    private String description;
    private String imageUrl;

    public MangaInfo() {
        genreList = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    public void addGenre(String genre) {
        this.genreList.add(genre);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
