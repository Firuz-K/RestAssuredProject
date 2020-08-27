package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDB {


    private String title;
    private String year;
    private String imdbID;
    private String type;
    private String Poster;

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
    }




    public MovieDB(){}

    public MovieDB(String Title, String Year,String imdbID,String Type, String Poster){
        this.title=Title;
        this.year=Year;
        this.imdbID=imdbID;
        this.type=Type;
        this.Poster= Poster;
    }

    @Override
    public String toString(){
        return " MovieDB { "+
                " Title = "+title+
                " Year = "+year+
                " imdbID = "+imdbID+
                " Type = "+type+
                " Poster = "+Poster+ " } ";
    }


}
