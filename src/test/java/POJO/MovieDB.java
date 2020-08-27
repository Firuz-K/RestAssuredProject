package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDB {
    @JsonProperty("Title")
    private String Title;
    @JsonProperty("Year")
    private String Year;
    private String imdbID;
    @JsonProperty("Type")
    private String Type;
    @JsonProperty("Poster")
    private String Poster;

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        Type = Type;
    }




    public MovieDB(){}

    public MovieDB(String Title, String Year,String imdbID,String Type, String Poster){
        this.Title=Title;
        this.Year=Year;
        this.imdbID=imdbID;
        this.Type=Type;
        this.Poster= Poster;
    }

    @Override
    public String toString(){
        return " MovieDB { "+
                " Title = "+Title+
                " Year = "+Year+
                " imdbID = "+imdbID+
                " Type = "+Type+
                " Poster = "+Poster+ " } ";
    }


}
