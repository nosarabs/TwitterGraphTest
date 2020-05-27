package com.company;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class Streamer {

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
            public void onStatus(Status status) {
                String username = "@" + status.getUser().getScreenName();
                String displayName = status.getUser().getName();
                String tweet = status.getText();

                System.out.println(displayName + " (" + username + ") - " + tweet + "\n");
            }
            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }
        };

        twitterStream.addListener(listener);

        //twitterStream.sample();
        long cursor = -1;
        long[] users = twitter.getFriendsIDs(cursor).getIDs();
        long[] user = new long [] {twitter.getId()};

        twitterStream.addListener(listener);
        FilterQuery filter = new FilterQuery();
        filter.follow( twitter.getFriendsIDs(cursor).getIDs());

        twitterStream.filter(filter);
    }
}
