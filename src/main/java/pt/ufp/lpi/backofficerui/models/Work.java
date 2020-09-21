package pt.ufp.lpi.backofficerui.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
class Autor {
    private String first;
    private String last;
}
@Data
class Location{
    private String city;
    private String country;
}
@Data
class Link{
    private String displayText;
    private String link;
}
@Data
class Artist{
    private String statement;
    private String bio;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Work {
    private String url;
    private String title;
    private String authorDisplayName;
    private List<Autor> authorData=new ArrayList<>();
    private String node;
    private String banner;
    private String icon;
    private Location location;
    private final List<String> tags=new ArrayList<>();
    private final List<String> language=new ArrayList<>();
    private final List<Link> beginLinks=new ArrayList<>();
    private String instructions;
    private String year;
    private String techDetails;
    private final List<String> requirements=new ArrayList<>();
    private Artist artist;
    private String editorialStatement;
    private String sourceFiles;
    private String video;
    private String externalUrl;
    private String copyright;
    private final List<String> previousPublication=new ArrayList<>();
    private final List<String> references=new ArrayList<>();
}
