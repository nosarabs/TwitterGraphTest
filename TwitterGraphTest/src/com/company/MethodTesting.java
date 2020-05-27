package com.company;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;


public class MethodTesting {

    private final TwitterFactory tf;
    private final Twitter twitter;
    private final ConfigurationBuilder cb;

    public MethodTesting(){

        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("MZQWrOLfmrnPClJWGNJJlN2hH")
                .setOAuthConsumerSecret("mLbGHFeXpKimgTaKyCGr3siSHCGGauHgCgeiXibrE4aF0vA8Bd")
                .setOAuthAccessToken("288909021-tDAyzEMgo4TmwTPyzzqnOriDkrR2WWIkCK2jr4RA")
                .setOAuthAccessTokenSecret("E0Z870tGX3RXfdrPIpnEBw3FKUH71aiWspdfmAjIo0mrp");

        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();

    }

    public void getFeed() throws TwitterException {
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        printTweets(statuses);
    }

    public void searchTweets(String lookup) throws TwitterException {

        Query query = new Query(lookup);
        QueryResult result = twitter.search(query);
        List<Status> tweets = result.getTweets();

        printTweets(tweets);

    }

    public void printTweets (List<Status> tweets) {
        for (Status tweet : tweets) {

            String username = "@" + tweet.getUser().getScreenName();
            String displayName = tweet.getUser().getName();
            String status = tweet.getText();

            System.out.println(displayName + " (" + username + ") - " + status + "\n");
        }
    }

    public static void streamFeed() throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("MZQWrOLfmrnPClJWGNJJlN2hH")
                .setOAuthConsumerSecret("mLbGHFeXpKimgTaKyCGr3siSHCGGauHgCgeiXibrE4aF0vA8Bd")
                .setOAuthAccessToken("288909021-tDAyzEMgo4TmwTPyzzqnOriDkrR2WWIkCK2jr4RA")
                .setOAuthAccessTokenSecret("E0Z870tGX3RXfdrPIpnEBw3FKUH71aiWspdfmAjIo0mrp");
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        ConfigurationBuilder cb1 = new ConfigurationBuilder();
        cb1.setDebugEnabled(true)
                .setOAuthConsumerKey("MZQWrOLfmrnPClJWGNJJlN2hH")
                .setOAuthConsumerSecret("mLbGHFeXpKimgTaKyCGr3siSHCGGauHgCgeiXibrE4aF0vA8Bd")
                .setOAuthAccessToken("288909021-tDAyzEMgo4TmwTPyzzqnOriDkrR2WWIkCK2jr4RA")
                .setOAuthAccessTokenSecret("E0Z870tGX3RXfdrPIpnEBw3FKUH71aiWspdfmAjIo0mrp");
        TwitterFactory tf = new TwitterFactory(cb1.build());
        Twitter twitter = tf.getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
            @Override
            public void onDeletionNotice(StatusDeletionNotice arg) {
            }
            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
            }
            @Override
            public void onStallWarning(StallWarning warning) {
            }
            @Override
            public void onStatus(Status tweet) {
                String username = "@" + tweet.getUser().getScreenName();
                String displayName = tweet.getUser().getName();
                String status = tweet.getText();

                System.out.println(displayName + " (" + username + ") - " + status + "\n");
            }
            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }
        };

        twitterStream.addListener(listener);

        //twitterStream.sample();
        long cursor = -1;

        long[] users = twitter.getFriendsIDs(cursor).getIDs();

        twitterStream.addListener(listener);
        FilterQuery filter = new FilterQuery();
        filter.follow(users);

        twitterStream.filter(filter);
    }

}