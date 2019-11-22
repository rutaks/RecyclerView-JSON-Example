package com.example.recyclerviewjsonexample.models;

/**
 * The CardItem is a representation of the card view item layout
 * which can be found in res/layout.
 */
public class CardItem {
    private String imageURL;
    private String creator;
    private int likes;


    /**
     * Constructor.
     *
     * @param imageURL (required) Image URL Of The Post's Picture To Be Displayed
     * must be a valid URL
     * @param creator (required) Full Names Of The Post Creator
     * @param likes (optional) Number Of Likes Given To Post. Can take
     * maximum value of 2,147,483,647
     */
    public CardItem(String imageURL, String creator, int likes) {
        this.imageURL = imageURL;
        this.creator = creator;
        this.likes = likes;
    }

    //MARK: -GETTERS AND SETTERS

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
