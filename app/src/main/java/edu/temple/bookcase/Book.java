package edu.temple.bookcase;
import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

public class Book implements Parcelable {
    private int id;
    private String author;
    private String coverURL;
    private String title;
    private String published;

    public Book(JSONObject jsonBook) throws JSONException {
        this.title = jsonBook.getString("title"); this.author = jsonBook.getString("author");
        this.id = jsonBook.getInt("book_id"); this.published = jsonBook.getString("published");
        this.coverURL = jsonBook.getString("cover_url");
    }

    protected Book(Parcel in) {
        author = in.readString();
        published = in.readString();
        id = in.readInt();
        coverURL = in.readString();
        title = in.readString();
    }
    public static final Parcelable.Creator<Book> CREATOR  = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public void setId(int id) {

        this.id = id;
    }

    public String getAuthor() {

        return author;
    }
    public int getId() {

        return id;
    }

    public String getCoverURL() {

        return coverURL;
    }
    public String getPublished() {

        return published;
    }

    public String getTitle() {

        return title;
    }


    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int t) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(coverURL);
        parcel.writeString(published);
    }
}
