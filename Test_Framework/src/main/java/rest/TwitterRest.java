package rest;

import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.mapper.ObjectMapperType;
import rest.templates.tweet.Tweet;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TwitterRest {
    // outh one string class variable
    private static final String oauth_consumer_key = "sjd4l7mMnI6Aj8aMYCPiwnFnP";
    private static final String oauth_consumer_secret = "FgfZUwAFBWHDfWkKoiUkS4tbhlDJTUncOzx6TSenEZqmrqR0m0";
    private static final String oauth_token = "1140683958734282757-Z5RFrZMWhRkFX8jTdScmrWb7VR0FDx";
    private static final String oauth_token_secret = "kR0jO0U4hkWBvPjtIBjblbZBX9LQoxwJuuvzwvt8GOaAc";
    Type listOfMyClassObject = new TypeToken<ArrayList<Tweet>>() {
    }.getType();


    public TwitterRest() {
        RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON));

    }

    //post tweet method here
    public void PostTweet(String tweet) {
        given()
                .auth()
                .oauth(oauth_consumer_key, oauth_consumer_secret, oauth_token, oauth_token_secret)
                .when().post("https://api.twitter.com/1.1/statuses/update.json?status=" + tweet)
                .then()
                .assertThat()
                .statusCode(200);
    }

    private List<Tweet> getAllTimelineTweets() {
        return given()
                .auth()
                .oauth(oauth_consumer_key, oauth_consumer_secret, oauth_token, oauth_token_secret)
                .when().get("https://api.twitter.com/1.1/statuses/home_timeline.json").then().extract().as(listOfMyClassObject);
    }

    private List<Long> getAllTweetIds() {
        List<Tweet> tweetList = getAllTimelineTweets();
        ArrayList<Long> tweetIds = new ArrayList<>();
        for (Tweet tweet : tweetList) {
            tweetIds.add(tweet.getId());
        }
        return tweetIds;
    }

    public void deleteAllTweets() {
        List<Long> tweetIds = getAllTweetIds();
        for (Long id : tweetIds) {
            Formatter fmt = new Formatter();
            String formattedURI = fmt.format("https://api.twitter.com/1.1/statuses/destroy/%s.json", id.toString()).toString();
            given()
                    .auth()
                    .oauth(oauth_consumer_key, oauth_consumer_secret, oauth_token, oauth_token_secret)
                    .when().post(formattedURI)
                    .then()
                    .assertThat()
                    .statusCode(200);
        }
    }
}
