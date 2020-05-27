package com.company;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class Streamer {

    public static void streamFeed() throws TwitterException {

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        Twitter twitter = new TwitterFactory().getInstance();

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

        // Filter
        long cursor = -1;
        long[] friends = twitter.getFriendsIDs(cursor).getIDs();
        FilterQuery filter = new FilterQuery(friends);
        twitterStream.filter(filter);
    }
}
