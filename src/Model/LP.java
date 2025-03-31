package Model;

import java.math.BigDecimal;
import java.util.Date;

public class LP extends Product {
    private String artists;      // Danh sách nghệ sĩ
    private String recordLabel;  // Hãng thu âm
    private String tracklist;    // Danh sách bài hát
    private String genre;        // Thể loại
    private Date releaseDate;    // Ngày phát hành

    public LP(int productId, String title, BigDecimal value, BigDecimal price, String barcode,
              String description, int quantity, BigDecimal weight, String dimensions,
              Date warehouseEntryDate, String imageUrl, String artists, String recordLabel,
              String tracklist, String genre, Date releaseDate) {
        super(productId, title, "lp", value, price, barcode, description, quantity, weight,
              dimensions, warehouseEntryDate, imageUrl);
        this.artists = artists;
        this.recordLabel = recordLabel;
        this.tracklist = tracklist;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    // Getters & Setters
    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
